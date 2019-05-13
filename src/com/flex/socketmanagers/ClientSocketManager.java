package com.flex.socketmanagers;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.SocketOption;
import java.net.StandardSocketOptions;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.Future;

import com.flex.utils.Lib;

public class ClientSocketManager {
	private String _host = "localhost";
	private int _port = 0;
	private ManualResetEvent _await = new ManualResetEvent(false);
	private AsynchronousSocketChannel channel = null;
	private Date _last_work_time;
	/**
	 * second
	 */
	private int _life_time;

	public ClientSocketManager(String host, int port,int life_time) {
		this._host = host;
		this._port = port;
		this._life_time = life_time;
	}
	public ClientSocketManager(String host, int port) {
		this._host = host;
		this._port = port;
	}
	/**
	 * start listen
	 * 
	 * @return
	 */
	public boolean start() {//can lam them time out nua, hien gio chua dap ung
		try {
			this._last_work_time = Lib.getCurrentDate();
			channel = AsynchronousSocketChannel.open();
			SocketAddress serverAddr = new InetSocketAddress(this._host, this._port);
			channel.setOption(StandardSocketOptions.SO_KEEPALIVE, true);
			Future<Void> result = channel.connect(serverAddr);
			result.get();

		} catch (Exception e) {
			return false;
		}
		return true;
	}

	/**
	 * 
	 * @return
	 */
	public boolean isConnected() {
		if (channel == null)
			return false;
		if (channel.isOpen()) {
			return true;
//			try {
//				ByteBuffer buffer = ByteBuffer.wrap("0".getBytes());
//				Future result = channel.write(buffer);
//				if (null != result && null != result.get()) {
//					if ((int) result.get() <= 0) {
//						return false;
//					}
//				}
//				buffer.clear();
//				return true;
//			} catch (Exception ex) {
//				return false;
//			}
		}

		return false;
	}

	/**
	 * close socket client
	 */
	public void close() {
		if (channel == null)
			return;
		try {
			if(channel.isOpen()){
				try{
					channel.shutdownInput();
					channel.shutdownOutput();
				}catch (Exception e) {
					
				}
			}
			channel.close();
			channel=null;
			
		} catch (Exception e) {
		}
	}

	/**
	 * send mess toi server
	 * 
	 * @param byteSend
	 * @param timeout
	 *            milisecond
	 * @return
	 */
	public boolean write(byte[] byteSend, int timeout, boolean ping) {
		try {
			if(ping){
				if (!isConnected()) {
					return false;
				}
			}
			this._last_work_time = Lib.getCurrentDate();
			// _await.reset();
			Attachment attach = new Attachment();
			attach.channel = channel;
			attach.buffer = ByteBuffer.allocate(byteSend.length);
			attach.isSent = false;
			attach.mainThread = Thread.currentThread();
			attach.buffer.put(byteSend);
			attach.buffer.flip();
			String b = Lib.bytesToHexString(byteSend);
			ReadWriteHandler readWriteHandler = new ReadWriteHandler();
			channel.write(attach.buffer, attach, readWriteHandler);

			attach.mainThread.join(timeout);
			// _await.wait(timeout);

			boolean is_sent = attach.isSent;

			attach = null;

			return is_sent;
		} catch (Exception ex) {
			return false;
		}
	}

	public boolean is_time_out() {
		try {
			Date now = Lib.getCurrentDate();
			long lifeTime = Lib.timeDiffSecond(now, this._last_work_time);
			if (lifeTime > this._life_time) {
				return true;
			}
			return false;
		} catch (Exception e) {
			// TODO: handle exception
			return true;
		}
	}

	




	class Attachment {
		AsynchronousSocketChannel channel;
		ByteBuffer buffer;
		Thread mainThread;
		boolean isSent = false;

	}

	class ReadWriteHandler implements CompletionHandler<Integer, Attachment> {
		@Override
		public void completed(Integer result, Attachment attach) {
			try {
				if (attach != null) {
					attach.isSent = true;
					attach.buffer.clear();
				}
				attach.mainThread.notifyAll();
				// _await.set();
			} catch (Exception ex) {

			}
			// if (attach.isRead) {
			// attach.buffer.flip();
			// Charset cs = Charset.forName("UTF-8");
			// int limits = attach.buffer.limit();
			// byte bytes[] = new byte[limits];
			// attach.buffer.get(bytes, 0, limits);
			// String msg = new String(bytes, cs);
			// System.out.format("Server Responded: "+ msg);
			// }else {
			// attach.isRead = true;
			// attach.buffer.clear();
			// _await.set();
			// }
		}

		@Override
		public void failed(Throwable e, Attachment attach) {
			try {
				attach.isSent = false;
				// _await.set();
				attach.mainThread.notifyAll();
			} catch (Exception ex) {

			}
		}

	}
}
