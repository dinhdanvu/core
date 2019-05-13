package com.flex.socketmanagers;

//import java.io.DataInputStream;
//import java.io.IOException;
//import java.lang.management.ManagementFactory;
//import java.lang.management.MemoryPoolMXBean;
//import java.lang.management.MemoryUsage;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.StandardSocketOptions;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.flex.dbmanager.redis.IRedisProvider;
import com.flex.entities.DeviceActiveEntity;
import com.flex.entities.DeviceInfor;
import com.flex.entities.VehicleEntity;
import com.flex.utils.Constants;
import com.flex.utils.FLLogger;
import com.flex.utils.Lib;

public abstract class SocketManager {
	protected byte[] _startBit = new byte[] { 0x78, 0x78 };
	protected byte[] _stopBit = new byte[] { 0x0D, 0x0A };
	public static int _save_spam_data=0;

	protected int _portNumber;
	protected int _numThreads = 10;// sau nay dua ra config roi doc vao
	// Thời gian timeout của client socket 5 phút
	protected int _socketClientTimeout = 1;

	// Kích thước tối đa cho 1 gói tin có thể xử lý
	private int _maxDataByte = 102400;

	public boolean _isStart = false;
	// protected String title;
	// mãng chứa device đã connect tới socket
//	protected List<DeviceInfor> _connectedDevice;
	protected HashMap<String,DeviceInfor> _connectedDevice;

	protected List<AsynchronousServerSocketChannel> _serverChanel=new ArrayList<AsynchronousServerSocketChannel>();

	protected static final FLLogger _imeiNumLog = FLLogger.getLogger(Constants.LOG_FILE_PROTOCOL_NUM);
//	public static final FLLogger _dataNumLog = FLLogger.getLogger("numLog/datalog");

	protected static final FLLogger _infoLog = FLLogger.getLogger(Constants.LOG_FILE_PROTOCOL_INFO);
	protected static final FLLogger _errorLog = FLLogger.getLogger(Constants.LOG_FILE_PROTOCOL_ERROR);
	protected static final FLLogger _outDeviceLog = FLLogger.getLogger(Constants.LOG_FILE_PROTOCOL_OUT_DEVICE);
	private AsynchronousServerSocketChannel _serverListener=null;
	private AsynchronousChannelGroup _threadGroup=null;
	
	//thompt bổ sung hash map chứa imei thiết bị, mã phương tiện, kích hoạt phương tiện
	protected static HashMap<String, DeviceActiveEntity> hashMapImeis = new HashMap<String, DeviceActiveEntity>();
	//Có ghi log các imei ngoài luồng hay không
	public static int _is_log_restricted_imei=0;
	//Thời gian timout với một imei trong hashMapImeis
	public static int _load_time_expired=0;
	
//	protected static int max_connection_timeout = 0;
	
	
	// quan ly memory
	//private MemoryPoolMXBean permgenBean = null;
	/**
	 * contractor
	 * 
	 * @param port
	 */
	public SocketManager(int port) {
		this._portNumber = port;
		if (_connectedDevice == null){
			_connectedDevice = new HashMap<String,DeviceInfor>();
//			_connectedDevice = new ArrayList<DeviceInfor>();
		}
		// iniMemory();
		// String memInfo = getMemInfo();
		// System.out.println(memInfo);
	}

//	private void iniMemory() {
//		try {
//			List<MemoryPoolMXBean> beans = ManagementFactory
//					.getMemoryPoolMXBeans();
//			for (MemoryPoolMXBean bean : beans) {
//				//MemoryUsage mu = bean.getUsage();
//				if (bean.getName().toLowerCase().indexOf("perm gen") >= 0) {
//					permgenBean = bean;
//					break;
//				}
//			}
//		} catch (Exception ex) {
//			_infoLog.error(ex);
//		}
//	}

	
	/**
	 * clear Memories
	 */
	private void taskClearMemory(){
		Thread threadStart = new Thread(new Runnable() {
			@Override
			public void run() {
				while(true){
					try{
						System.gc();
						
					}catch(Exception ex){
						
					}finally{
						try {
							//2 phut clear bo nho 1 lan
							Thread.sleep(2*60*1000);
						} catch (InterruptedException e) {
//							e.printStackTrace();
						}
					}
				}
			}
		});
		threadStart.start();
		
	}
	
	public void start() {
		try {
			_infoLog.info("Server " + _portNumber + " Start...");
			_threadGroup = AsynchronousChannelGroup.withFixedThreadPool(_numThreads, Executors.defaultThreadFactory());
	       _serverListener = AsynchronousServerSocketChannel.open(_threadGroup);
			_serverListener.setOption(StandardSocketOptions.SO_REUSEADDR, true);
	        InetAddress ipLocal= InetAddress.getLocalHost(); 
	        _errorLog.error("start ip:" + ipLocal.getHostAddress());
//			InetSocketAddress sAddr = new InetSocketAddress("192.168.2.34",
//					_portNumber);
	        
			InetSocketAddress sAddr = new InetSocketAddress(ipLocal,
					_portNumber);
//			_serverListener.bind(sAddr,10000);
			_serverListener.bind(new InetSocketAddress(_portNumber),1000);
			acceptCallback(_serverListener);
			//clear memory
			taskClearMemory();
			
			_isStart = true;
			_infoLog.info("Server " + _portNumber + " Start ok");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			_errorLog.error("start Port:" + _portNumber + ": da bi loi roi",e);
		}
	}

	/**
	 * Accept a connection client
	 */
	private void acceptCallback(AsynchronousServerSocketChannel serverListener) {
		try {
			DeviceInfor deviceInfo = new DeviceInfor();
			deviceInfo.socketManagerOwner = this;
			deviceInfo.timeConnect = new Date();
			deviceInfo.server = serverListener;
			serverListener.accept(deviceInfo, new ConnectionHandler());
			Thread.currentThread().join();
			
		} catch (Exception odex) {
			_errorLog
					.error("AcceptCallback Port:"
							+ _portNumber
							+ ": da bi loi roi AcceptCallback - ObjectDisposedException",
							odex);
		}
	}

//	/**
//	 * get memory info
//	 */
//	private String getMemInfo() {
//		System.gc();
//		if (permgenBean == null)
//			return "";
//		MemoryUsage currentUsage = permgenBean.getUsage();
//		int percentageUsed = (int) ((currentUsage.getUsed() * 100) / currentUsage
//				.getMax());
//		String memInfo = Lib.GetDateNow() + ": Permgen "
//				+ (currentUsage.getUsed() / 1024) / 1024 + "M of "
//				+ (currentUsage.getMax() / 1024) / 1024 + "M ("
//				+ percentageUsed + "%)";
//		System.gc();
//		return memInfo;
//	}
//	int dataNum=0;
	public static String print(byte[] bytes) {
	    StringBuilder sb = new StringBuilder();
	    sb.append("[ ");
	    for (byte b : bytes) {
	        sb.append(String.format("0x%02X ", b));
	    }
	    sb.append("]");
	    return sb.toString();
	}
	/**
	 * su ly nghiep vu chinh
	 * 
	 * @param deviceInfo
	 * @param byteReceived
	 */
	private void mainProcessor(DeviceInfor deviceInfo, byte[] byteReceived) {
	
		try {
			while (true) {
				if (byteReceived == null || byteReceived.length <= 0)
					return;
				// chua co nghiep vu
				int startBitIndex = Lib.byteIndexOf(byteReceived, _startBit, 0);
				if (startBitIndex <= -1)
					startBitIndex = 0;
				else
					startBitIndex += _startBit.length;
				int endBitIndex = Lib.byteIndexOf(byteReceived, _stopBit, 0);
				if (endBitIndex <= -1)
					endBitIndex = byteReceived.length;
				byte[] commandByte = Lib.subByteArray(byteReceived,
						startBitIndex, endBitIndex);
				byteReceived = Lib.subByteArray(byteReceived, endBitIndex
						+ _stopBit.length, byteReceived.length);
				
				if (commandByte != null && commandByte.length > 0) {
					if (Arrays.equals(commandByte, Constants.TCP_PING.getBytes())) {
						try {
							deviceInfo.socketManagerOwner.send(deviceInfo.client, Constants.TCP_REPLY.getBytes());
						} catch (Exception e) {
							_errorLog.error("TCP_REPLY ERROR", e);
						}
						return;
					}
							
					doProcessBusiness(deviceInfo, commandByte);
				}
			}

		} catch (Exception ex) {
			_errorLog.error("MainProcessor " + _portNumber + ":", ex);
		}
	}

	/**
	 * tuy theo thiet bi ma moi thiet bi implement khac nhau
	 * 
	 * @param deviceInfo
	 * @param byteReceived
	 */
	protected abstract void doProcessBusiness(DeviceInfor deviceInfo,
			byte[] byteReceived);

	/**
	 * send mess to client
	 * 
	 * @param sock
	 * @param byteData
	 */
	public boolean send(AsynchronousSocketChannel sockClient, byte[] byteData) {
		try {
			if(!sockClient.isOpen()){
				_infoLog.info("Send " + _portNumber + ": socket client closed");
				//socket is closed
				return false;
			}
			// Begin sending the data to the remote device.
			if (byteData.length > 0) {

				byte[] sendByteArr = Lib.byteArrayConcat(_startBit, byteData,
						_stopBit);
			      ByteBuffer buffer = ByteBuffer.wrap(sendByteArr);
			      sockClient.write(buffer);
				return true;
			}
			return false;
		} catch (Exception e) {
			_errorLog.error("Send " + _portNumber + ":", e);
			return false;
		}
	}

	/**
	 * Remove the socket contained in the given state object from the connected
	 * array list and hash table, then close the socket.
	 * 
	 * @param deviceInfo
	 * @return
	 */
	private boolean removeSocket(DeviceInfor deviceInfo) {
		try {
			--imeiNum;
			boolean blnHasSocket = false;
			AsynchronousSocketChannel sock = deviceInfo.client;
			synchronized (_connectedDevice) {
				try {
					if (_connectedDevice.containsKey(deviceInfo.device_imei)) {
						_connectedDevice.remove(deviceInfo.device_imei);
						blnHasSocket = true;
					}
//					if (_connectedDevice.contains(deviceInfo)) {
//						_connectedDevice.remove(deviceInfo);
//						blnHasSocket = true;
//					}
				} catch (Exception ex) {
				}
			}

			if (blnHasSocket && sock != null) {
				sock.close();
				sock = null;
				deviceInfo.client = null;
				deviceInfo = null;

			}
			return blnHasSocket;
		} catch (Exception ex) {
			_errorLog.error("RemoveSocket " + _portNumber + ": ", ex);
			return false;
		}
	}
	/**
	 * put socket vao map
	 * @param imei
	 * @param deviceInfo
	 * @return
	 */
	protected boolean putSocket(String imei, DeviceInfor deviceInfo){
		try{
			synchronized (_connectedDevice) {
				DeviceInfor old = _connectedDevice.get(imei);
				if(null!=old){
					try{
						old.client.close();
					}catch (Exception e) {
						_errorLog.error("Close clinet err: "+e);
					}
				}
				_connectedDevice.put(imei, deviceInfo);
			}
			return true;
		}catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}
	/**
	 * Stop the threads for the port listener.
	 */
	public void stop() {
		try {
			//_shuttingDown = true;
			_isStart = false;
			// _timerCheckSocket.cancel();
			// _timerCheckSocket = null;

			if (_threadGroup!=null && !_threadGroup.isShutdown()) {
			    // once the group is shut down no more channels can be created with it
			    _threadGroup.shutdown();
			}
			if (_threadGroup!=null &&!_threadGroup.isTerminated()) {
			    // forcibly shutdown, the channel will be closed and the accept will abort
			    _threadGroup.shutdownNow();
			}
			if (this._serverListener!=null &&this._serverListener.isOpen()) {
				this._serverListener.close();
				this._serverListener=null;
			}
			closeAllSocketClient();
			
		} catch (Exception ex) {
			_errorLog.error("Stop " + _portNumber + ":", ex);
		}
	}
	/**
	 * Hàm lọc IMEIS bất hợp lệ ở TCP. Những IMEIS ko có trên hệ thống, IMEIS chưa được gắn với phương tiện hoặc được gắn với phương tiện ngừng kích hoạt thì bỏ qua không xử lý
	 * @param deviceInfo: Thông tin loại thiết bị
	 * @param imei : IMEI của thiết bị
	 * @param _imeiIpLog: Ghi log theo loại thiết bị
	 * @param redis:  Đối tượng để lấy DL từ Redis
	 * @return: true nếu IMEI hợp lệ, false nếu là IMEI bất hợp lệ
	 * @author: Thompt
	 */
	public boolean checkValidImeisInTCP(DeviceInfor deviceInfo, String imei, FLLogger _imeiIpLog, IRedisProvider redis){
	
		try{
			if(hashMapImeis.size()>0){
				boolean imeiMap  = hashMapImeis.containsKey(imei);	
				if(!imeiMap){
					// Log lại thông tin nếu có
					if(_is_log_restricted_imei==1){									
					     String remoteIP = deviceInfo.client.getRemoteAddress().toString();
					     remoteIP = remoteIP.replaceAll("/", "");
					     _imeiIpLog.error(imei + ", "+ remoteIP);
					}
					return false;
				}
			}
				
			//Lọc qua các imei chưa được gắn phương tiện hoặc phương tiện gắn bị deactive							
			DeviceActiveEntity vehicle_device  = hashMapImeis.get(imei);
			
			//thompt ghi log de theo doi
//			if("2197103464".equals(imei) && _is_log_restricted_imei==1){
//				_imeiIpLog.info("hashMapImeis.size = " + hashMapImeis.size() );
//				_imeiIpLog.info(Lib.convertObj2JsonString(vehicle_device));
//				
//			}
			
			//Nếu ko có thông tin về phương tiện thì không làm gì
			if(vehicle_device.getTime_load()!=null){					
				// Xem đã hết thời gian time out chưa?
				long timeDiff = Lib.timeDiff(Lib.getCurrentDate(), vehicle_device.getTime_load(), TimeUnit.SECONDS);
				// trường hợp chưa time out thì kiểm tra có được active hay chưa?
				if(timeDiff<=_load_time_expired){
					if(vehicle_device.getVehicle_id()<=0 || vehicle_device.getActive()==0){
						if(_is_log_restricted_imei==1){		
							_imeiIpLog.error( "TH1 - IMEI BAT HOP LE - "+ imei);
						}
						return false;
					}
				}else{
					// trường hợp đã hết time out thì đọc DL từ Redis rồi mới kiểm tra có được active hay chưa?
					VehicleEntity vehicle = redis.get(imei, VehicleEntity.class);
					DeviceActiveEntity objDevice = new DeviceActiveEntity();
					if(vehicle==null){
						//Thay thông tin về imei trong hash bằng thông tin vừa lấy từ redis.
						objDevice.setTime_load(Lib.getCurrentDate());
						objDevice.setVehicle_id(0);
						objDevice.setActive(0);
						boolean is_replace = hashMapImeis.replace(imei,vehicle_device,objDevice);
							if(_is_log_restricted_imei==1){	
								_imeiIpLog.error( "TH2 - IMEI BAT HOP LE - "+ imei);
							}
						return false;
						
					}else{
						DeviceActiveEntity vehicle_info = objDevice.createEntityFromVehicleEntity(vehicle);
						boolean is_replace = hashMapImeis.replace(imei,vehicle_device,vehicle_info);
						if(vehicle.getId()<=0 || vehicle.getActive()==0){
							if(_is_log_restricted_imei==1){	
								_imeiIpLog.error( "TH3 - IMEI BAT HOP LE - "+ imei);
							}
							return false ;
							
						}
					}
					
					
				}
				
			}
			return true;
			
		}catch (Exception e) {
			// TODO: handle exception
			_errorLog.error("Loi trong khi check IMEIS hop le o TCP - " + imei);
			return true;
		}
		
									
	}

	 /**
	  * close all socket
	  */
	private void closeAllSocketClient() {
		try {
			_connectedDevice.clear();
		} catch (Exception ex) {
			_errorLog.error("CloseAllSocketClient " + _portNumber + ": ", ex);
		} finally {
		}

	}
	int imeiNum =0 ;
	/**
	 * connction handler
	 * 
	 * @author tichnguyen
	 *
	 */
	class ConnectionHandler implements
			CompletionHandler<AsynchronousSocketChannel, DeviceInfor> {
		@Override
		public void completed(AsynchronousSocketChannel client,
				DeviceInfor attach) {
			try {

				_imeiNumLog.info(++imeiNum);
				client.setOption(StandardSocketOptions.SO_REUSEADDR, true);
				client.setOption(StandardSocketOptions.SO_KEEPALIVE, true);
				SocketAddress clientAddr = client.getRemoteAddress();
//				System.out.format("Accepted a  connection from  %s%n",
//						clientAddr);
				attach.server.accept(attach, this);
				DeviceInfor newAttach = new DeviceInfor();
				newAttach.server = attach.server;
				newAttach.client = client;
				newAttach.socketManagerOwner=attach.socketManagerOwner;
				newAttach.isRead = true;
				newAttach.clientAddr = clientAddr;
				newAttach.timeConnect = new Date();
				//tam thoi ko de y toi quan ly socket
//				synchronized (_connectedDevice) {
//				
//					if (_connectedDevice.contains(newAttach)) {
//						// da ton tai device info trong list connected
//						// device nhung vi ly do gi do ko remove ra
//						// tien hanh remove ra truoc
//						_connectedDevice.remove(newAttach);
//					}
//					_connectedDevice.add(newAttach);
//					
//				}
				if(Lib.byteIndexOf(_startBit, Constants.BK86_START_BIT, 0)>=0){
//				if(_startBit==Constants.BK86_START_BIT){
					client.read(newAttach.buffer,_socketClientTimeout,TimeUnit.MINUTES, newAttach, new Bk86ReadHandler());
				}else{
					client.read(newAttach.buffer,_socketClientTimeout,TimeUnit.MINUTES, newAttach, new DF521ReadHandler());
				}
			} catch (Exception e) {
				_errorLog.error("completed: Acception connection: " + _portNumber + ": Failed to accept a  connection.",e);
			}
		}

		@Override
		public void failed(Throwable e, DeviceInfor attach) {
			_errorLog.error("Acception connection: " + _portNumber + ": Failed to accept a  connection.",e);
		}
	}

	/**
	 * read socket handel
	 * 
	 * @author tichnguyen
	 *
	 */
	class DF521ReadHandler implements CompletionHandler<Integer, DeviceInfor> {
		@Override
		public void completed(Integer result, DeviceInfor deviceInfo) {
			try{
			synchronized (deviceInfo) {

				if (result == -1) {
					try {
						//remove socket
						removeSocket(deviceInfo);
					} catch (Exception ex) {

					}
					return;
				}
				try{
					// begin get data from buffer
					deviceInfo.buffer.flip();
					int limits = deviceInfo.buffer.limit();
					byte receivedBytes[] = new byte[limits];
					deviceInfo.buffer.get(receivedBytes, 0, limits);
	//				Charset cs = Charset.forName("UTF-8");
	//				String msg = new String(receivedBytes, cs);
	//				System.out.format("Client at  %s  says: %s%n",
	//						deviceInfo.clientAddr, msg);
					// end get data from buffer
					
					//Xu ly  data
					deviceInfo.timeConnect = new Date();
					int index = Lib.byteLastIndexOf(receivedBytes, _stopBit);
					if (index == -1) {
						_infoLog.info("received bytes:"+Lib.bytesToHexString(receivedBytes));
						// luu tru qua nhieu thi bo di
						if (deviceInfo.dataReceived.size() > _maxDataByte)
							deviceInfo.dataReceived.clear();
						// chưa nhan du, add vo recieved buffer
						Lib.addToByteList(receivedBytes, deviceInfo.dataReceived);
					}
					else{
						// add noi dung cua cau lenh vo DataRecevied va xu ly
						byte[] firstBytes = Lib.subByteArray(receivedBytes, 0, index);
						if (firstBytes != null && firstBytes.length > 0) {
							Lib.addToByteList(firstBytes, deviceInfo.dataReceived);
						}
						byte[] goodBytes = Lib.getArrayFromList(deviceInfo.dataReceived);
						deviceInfo.clearBuffer();
						if (index + _stopBit.length < limits) {
							byte[] lastBytes = Lib.subByteArray(receivedBytes,
									index + _stopBit.length, limits);
							if (lastBytes != null && lastBytes.length > 0)
		
								Lib.addToByteList(lastBytes,
										deviceInfo.dataReceived);
						}
		
						// thuc hien xy ly nghiep vu
						 mainProcessor(deviceInfo, goodBytes);
					}

				}catch(Exception ex){
					_errorLog.error("Read data: " + _portNumber + ": co loi trong khi doc du lieu.",ex);
				}
				deviceInfo.buffer.clear();

			}
			//continue read data from socket
			deviceInfo.client.read(deviceInfo.buffer,_socketClientTimeout,TimeUnit.MINUTES, deviceInfo, this);
			}catch(Exception ex){
				_errorLog.error("completed: ReadHandler: " + _portNumber + ": loi ngoai y muon exception.",ex);
			}
		}

		@Override
		public void failed(Throwable e, DeviceInfor deviceInfo) {
			try {
				removeSocket(deviceInfo);
			} catch (Exception ex) {

			}
		}
	}
	public class Bk86ReadHandler implements CompletionHandler<Integer, DeviceInfor> {
		@Override
		public void completed(Integer result, DeviceInfor deviceInfo) {
			try{
			synchronized (deviceInfo) {

				if (result == -1) {
					try {
						//remove socket
						removeSocket(deviceInfo);
					} catch (Exception ex) {

					}
					return;
				}
				try{
					// begin get data from buffer
					deviceInfo.buffer.flip();
					int limits = deviceInfo.buffer.limit();
					byte receivedBytes[] = new byte[limits];
					deviceInfo.buffer.get(receivedBytes, 0, limits);
					
					//Xu ly  data
					deviceInfo.timeConnect = new Date();
					int index = Lib.byteLastIndexOf(receivedBytes, _stopBit);
					if (index == -1) {
						_infoLog.info("received bytes:"+Lib.bytesToHexString(receivedBytes));
						// luu tru qua nhieu thi bo di
						if (deviceInfo.dataReceived.size() > _maxDataByte)
							deviceInfo.dataReceived.clear();
						// chưa nhan du, add vo recieved buffer
						Lib.addToByteList(receivedBytes, deviceInfo.dataReceived);
						if(Lib.byteIndexOf(receivedBytes, Constants.BK86_START_BIT, 0)>=0){
							int indexCheckSum =7;
							byte[] checkSumBytes =  Lib.subByteArray(receivedBytes, indexCheckSum, indexCheckSum+2);
							_infoLog.info("check sum:"+checkSumBytes.length +", byte: "+Lib.bytesToHexString(checkSumBytes));
							int checkSum = Lib.hexStr2int(Lib.bytesToHexString(checkSumBytes));
							if(checkSum==receivedBytes.length){
								Lib.addToByteList(_stopBit, deviceInfo.dataReceived);
							}
						}
					}
//					else{
//						// add noi dung cua cau lenh vo DataRecevied va xu ly
//						byte[] firstBytes = Lib.subByteArray(receivedBytes, 0, index);
//						if (firstBytes != null && firstBytes.length > 0) {
//							Lib.addToByteList(firstBytes, deviceInfo.dataReceived);
//						}
//						byte[] goodBytes = Lib.getArrayFromList(deviceInfo.dataReceived);
//						deviceInfo.clearBuffer();
//						if (index + _stopBit.length < limits) {
//							byte[] lastBytes = Lib.subByteArray(receivedBytes,
//									index + _stopBit.length, limits);
//							if (lastBytes != null && lastBytes.length > 0)
//		
//								Lib.addToByteList(lastBytes,
//										deviceInfo.dataReceived);
//						}
//		
//						// thuc hien xy ly nghiep vu
//						 mainProcessor(deviceInfo, goodBytes);
//					}

					int index_stop = Lib.byteIndexOf(Lib.getArrayFromList(deviceInfo.dataReceived), _stopBit,0);
					if(deviceInfo.dataReceived.size()>0&&index_stop>0){
						byte[] goodBytes = Lib.getArrayFromList(deviceInfo.dataReceived);
						deviceInfo.clearBuffer();
						if (index_stop + _stopBit.length < limits) {
							byte[] lastBytes = Lib.subByteArray(receivedBytes,
									index_stop + _stopBit.length, limits);
							if (lastBytes != null && lastBytes.length > 0)
		
								Lib.addToByteList(lastBytes,
										deviceInfo.dataReceived);
						}
		
						// thuc hien xy ly nghiep vu
						 mainProcessor(deviceInfo, goodBytes);
					}
				}catch(Exception ex){
					_errorLog.error("Read data: " + _portNumber + ": co loi trong khi doc du lieu.",ex);
				}
				deviceInfo.buffer.clear();

			}
			//continue read data from socket
			deviceInfo.client.read(deviceInfo.buffer,_socketClientTimeout,TimeUnit.MINUTES, deviceInfo, this);
			}catch(Exception ex){
				_errorLog.error("completed: ReadHandler: " + _portNumber + ": loi ngoai y muon exception.",ex);
			}
		}

		@Override
		public void failed(Throwable e, DeviceInfor deviceInfo) {
			try {
				removeSocket(deviceInfo);
			} catch (Exception ex) {

			}
		}
	}

}
