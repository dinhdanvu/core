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
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;


import com.flex.entities.DeviceInfor;
import com.flex.socketmanagers.SocketManager.ConnectionHandler;
import com.flex.utils.Constants;
import com.flex.utils.FLLogger;
import com.flex.utils.Lib;

public abstract class ModuleTcpController {
	protected byte[] _startBit = new byte[] { 0x78, 0x78 };
	protected byte[] _stopBit = new byte[] { 0x0D, 0x0A };

	protected int _portNumber;
	protected int _numThreads = 1;// sau nay dua ra config roi doc vao
	// Thời gian timeout của client socket 5 phút
	protected int _socketClientTimeout = 1;

	// Kích thước tối đa cho 1 gói tin có thể xử lý
	private int _maxDataByte = 102400;

	public boolean _isStart = false;

	protected List<AsynchronousServerSocketChannel> _serverChanel=new ArrayList<AsynchronousServerSocketChannel>();

	protected static final FLLogger _imeiNumLog = FLLogger.getLogger(Constants.LOG_FILE_PROTOCOL_NUM);
//	public static final FLLogger _dataNumLog = FLLogger.getLogger("numLog/datalog");

	protected static final FLLogger _infoLog = FLLogger.getLogger(Constants.LOG_FILE_PROTOCOL_INFO);
	protected static final FLLogger _errorLog = FLLogger.getLogger(Constants.LOG_FILE_PROTOCOL_ERROR);
	protected static final FLLogger _outDeviceLog = FLLogger.getLogger(Constants.LOG_FILE_PROTOCOL_OUT_DEVICE);
	private AsynchronousServerSocketChannel _serverListener=null;
	private AsynchronousChannelGroup _threadGroup=null;
	
	
	// quan ly memory
	//private MemoryPoolMXBean permgenBean = null;
	/**
	 * contractor
	 * 
	 * @param port
	 */
	public ModuleTcpController(int port) {
		this._portNumber = port;
	}

	
	
	public void start(){
		try {
			_infoLog.info("module control " + _portNumber + " Start...");
			_threadGroup = AsynchronousChannelGroup.withFixedThreadPool(_numThreads, Executors.defaultThreadFactory());
	       _serverListener = AsynchronousServerSocketChannel.open(_threadGroup);
			_serverListener.setOption(StandardSocketOptions.SO_REUSEADDR, true);
	        InetAddress ipLocal= InetAddress.getLocalHost(); 
	        _errorLog.error("start ip:" + ipLocal.getHostAddress());
			InetSocketAddress sAddr = new InetSocketAddress(ipLocal,
					_portNumber);
			_serverListener.bind(sAddr,1);

			_isStart = true;
			acceptCallback(_serverListener);
			
			_infoLog.info("module control " + _portNumber + " Start ok");
		} catch (Exception e) {
			_errorLog.error("start Port:" + _portNumber + ": da bi loi roi",e);
			throw new RuntimeException("loi start tcp", e);
		}
	}

	/**
	 * Accept a connection client
	 */
	private void acceptCallback(AsynchronousServerSocketChannel serverListener) {
		try {
			ConnectInfor connectInfor = new ConnectInfor();
			connectInfor.socketManagerOwner = this;
			connectInfor.timeConnect = new Date();
			connectInfor.server = serverListener;
			serverListener.accept(connectInfor, new ConnectionHandler());
			Thread.currentThread().join();
		} catch (Exception odex) {
			_errorLog
					.error("AcceptCallback Port:"
							+ _portNumber
							+ ": da bi loi roi AcceptCallback - ObjectDisposedException",
							odex);
		}
	}

	/**
	 * su ly nghiep vu chinh
	 * 
	 * @param deviceInfo
	 * @param byteReceived
	 */
	private void mainProcessor(ConnectInfor deviceInfo, byte[] byteReceived) {
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
	protected abstract void doProcessBusiness(ConnectInfor deviceInfo,
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
	private boolean removeSocket(ConnectInfor deviceInfo) {
		try {
			--imeiNum;
			boolean blnHasSocket = false;
			AsynchronousSocketChannel sock = deviceInfo.client;


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
	 * Stop the threads for the port listener.
	 */
	public void stop() {
		try {
			_isStart = false;

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
			
		} catch (Exception ex) {
			_errorLog.error("Stop " + _portNumber + ":", ex);
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
			CompletionHandler<AsynchronousSocketChannel, ConnectInfor> {
		@Override
		public void completed(AsynchronousSocketChannel client,
				ConnectInfor attach) {
			try {

				_imeiNumLog.info(++imeiNum);
				client.setOption(StandardSocketOptions.SO_REUSEADDR, true);
				client.setOption(StandardSocketOptions.SO_KEEPALIVE, true);
				SocketAddress clientAddr = client.getRemoteAddress();
				attach.server.accept(attach, this);
				ConnectInfor newAttach = new ConnectInfor();
				newAttach.server = attach.server;
				newAttach.client = client;
				newAttach.socketManagerOwner=attach.socketManagerOwner;
				newAttach.isRead = true;
				newAttach.clientAddr = clientAddr;
				newAttach.timeConnect = new Date();
				if(Lib.byteIndexOf(_startBit, "#".getBytes(), 0)>=0){
					client.read(newAttach.buffer,_socketClientTimeout,TimeUnit.MINUTES, newAttach, new ReadHandler());
				}
			} catch (Exception e) {
				_errorLog.error("completed: Acception connection: " + _portNumber + ": Failed to accept a  connection.",e);
			}
		}

		@Override
		public void failed(Throwable e, ConnectInfor attach) {
			_errorLog.error("Acception connection: " + _portNumber + ": Failed to accept a  connection.",e);
		}
	}

	/**
	 * read socket handel
	 * 
	 * @author tichnguyen
	 *
	 */
	class ReadHandler implements CompletionHandler<Integer, ConnectInfor> {
		@Override
		public void completed(Integer result, ConnectInfor deviceInfo) {
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
		public void failed(Throwable e, ConnectInfor deviceInfo) {
			try {
				removeSocket(deviceInfo);
			} catch (Exception ex) {

			}
		}
	}
	public class ConnectInfor {
		public AsynchronousServerSocketChannel server;
		public AsynchronousSocketChannel client;
		public ModuleTcpController socketManagerOwner;
	     // Size of receive buffer.
	     public final int BUFFERSIZE = 10204;            
	   
	     public ByteBuffer buffer;
	     public SocketAddress clientAddr;
	     public boolean isRead;
	     public List<Byte> dataReceived;

	     public Date timeConnect;//thoi gian connect
	    
	     public boolean isTimeOut = false;//co time out hay ko? moi device chi dc phep bao nhieu phut
	     public static final int TIMEMAX = 120*15;//30 phut
	    
	     public boolean isEnscrypt = false;
	     
	     public String userId;
	     public String password;
	     public byte[] serialNumber;
	     public ConnectInfor()
	     {
	         buffer = ByteBuffer.allocate(BUFFERSIZE);
	         dataReceived = new ArrayList<Byte>();
	         userId = "";
	         password = "";
	         serialNumber = null;
	     }

	     public void clearBuffer()
	     {
	    	 buffer=ByteBuffer.allocate(BUFFERSIZE);
	    	 dataReceived = new ArrayList<Byte>();
	         
	     }
	     
	     public boolean isAuthentication()
	     {
	         if (Lib.isBlank(this.userId) || Lib.isBlank(this.password)) return false;
	         return true;
	     }
	}
}
