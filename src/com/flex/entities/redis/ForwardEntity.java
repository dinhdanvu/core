package com.flex.entities.redis;

import com.flex.dbmanager.attributes.RedisClassNameAliasAttribute;
import com.flex.dbmanager.attributes.RedisIgnoreKeyAttributes;
import com.flex.dbmanager.attributes.RedisKeyAttribute;
@RedisClassNameAliasAttribute(name="DFW")
@RedisKeyAttribute(key="imei")
//@RedisIgnoreKeyAttributes(ignoreKey={"port"})
public class ForwardEntity {
	private String imei;
	private int port;
	private String ipAddress;
	public String getImei() {
		return imei;
	}
	public void setImei(String imei) {
		this.imei = imei;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
}
