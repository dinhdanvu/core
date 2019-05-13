package com.flex.entities;

import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.flex.socketmanagers.SocketManager;
import com.flex.utils.Lib;

public class DeviceInfor {
	public AsynchronousServerSocketChannel server;
	public AsynchronousSocketChannel client;
	public SocketManager socketManagerOwner;
     // Size of receive buffer.
     public final int BUFFERSIZE = 10204;            
   
     public ByteBuffer buffer;
     public SocketAddress clientAddr;
     public boolean isRead;
     public List<Byte> dataReceived;

     public String device_imei;
     public Date timeConnect;//thoi gian connect
    
     public boolean isTimeOut = false;//co time out hay ko? moi device chi dc phep bao nhieu phut
     public static final int TIMEMAX = 120*15;//30 phut
    
     public boolean isEnscrypt = false;
     
     public String userId;
     public String password;
     public byte[] serialNumber;
     public DeviceInfor()
     {
         device_imei = "";
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
