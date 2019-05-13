package com.flex.utils;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
 
public class MacAddress {
 
    public static String getMacAddress() {
        try {
            InetAddress address = InetAddress.getLocalHost();
            //InetAddress address = InetAddress.getByName("192.168.46.53");
 
            /*
             * Get NetworkInterface for the current host and then read the
             * hardware address.
             */
            NetworkInterface ni = NetworkInterface.getByInetAddress(address);
            if (ni != null) {
                byte[] mac = ni.getHardwareAddress();
                if (mac != null) {
                    /*
                     * Extract each array of mac address and convert it to hexa with the
                     * following format 08-00-27-DC-4A-9E.
                     */
                	String str_address="";
                    for (int i = 0; i < mac.length; i++) {
                    	str_address+=String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : "");
                        //System.out.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : "");
                    }
                    return str_address;
                } else {
                	return "";
                    //System.out.println("Address doesn't exist or is not accessible.");
                }
            } else {
            	return "";
                //System.out.println("Network Interface for the specified address is not found.");
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
           
        } catch (SocketException e) {
            e.printStackTrace();
        }
        return "";
    }
    public static String getMac1() throws java.io.IOException {
    	Process p = Runtime.getRuntime().exec("getmac /fo csv /nh");
    	java.io.BufferedReader in = new java.io.BufferedReader(new java.io.InputStreamReader(p.getInputStream()));
    	String line;
    	line = in.readLine();
    	String[] result = line.split(",");
    	return result[0].replace('"', ' ').trim();
    	}
}