package com.flex.entities;

import java.util.Date;

import com.flex.dbmanager.attributes.RedisClassNameAliasAttribute;
import com.flex.dbmanager.attributes.RedisIndexAttribute;
import com.flex.dbmanager.attributes.RedisKeyAttribute;

@RedisIndexAttribute(indexs={"user_name","plate"})
@RedisKeyAttribute(key="imei")
@RedisClassNameAliasAttribute(name="DV")
public class DeviceEntity extends BaseEntity{
	private int id;
	private String imei;
	private int device_type;
	private int vehicle_id;
	private String device_sim_no;
	private int sim_manager_type;
	private int active;
	private Date active_date;
	private String manage_user_name;
	private String firmware;
	private String description;
	private int is_forward;
	private int fw_port;
	private String fw_ip;
	/**
	 * bien co dong bo redis server tcp
	 */
	private int is_redis_sync;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getImei() {
		return imei;
	}
	public void setImei(String imei) {
		this.imei = imei;
	}
	public int getDevice_type() {
		return device_type;
	}
	public void setDevice_type(int device_type) {
		this.device_type = device_type;
	}
	public int getVehicle_id() {
		return vehicle_id;
	}
	public void setVehicle_id(int vehicle_id) {
		this.vehicle_id = vehicle_id;
	}
	public String getDevice_sim_no() {
		return device_sim_no;
	}
	public void setDevice_sim_no(String device_sim_no) {
		this.device_sim_no = device_sim_no;
	}
	public int getSim_manager_type() {
		return sim_manager_type;
	}
	public void setSim_manager_type(int sim_manager_type) {
		this.sim_manager_type = sim_manager_type;
	}
	public int getActive() {
		return active;
	}
	public void setActive(int active) {
		this.active = active;
	}
	public Date getActive_date() {
		return active_date;
	}
	public void setActive_date(Date active_date) {
		this.active_date = active_date;
	}
	public String getManage_user_name() {
		return manage_user_name;
	}
	public void setManage_user_name(String manage_user_name) {
		this.manage_user_name = manage_user_name;
	}
	public String getFirmware() {
		return firmware;
	}
	public void setFirmware(String firmware) {
		this.firmware = firmware;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getIs_forward() {
		return is_forward;
	}
	public void setIs_forward(int is_forward) {
		this.is_forward = is_forward;
	}
	public int getFw_port() {
		return fw_port;
	}
	public void setFw_port(int fw_port) {
		this.fw_port = fw_port;
	}
	public String getFw_ip() {
		return fw_ip;
	}
	public void setFw_ip(String fw_ip) {
		this.fw_ip = fw_ip;
	}
	public int getIs_redis_sync() {
		return is_redis_sync;
	}
	public void setIs_redis_sync(int is_redis_sync) {
		this.is_redis_sync = is_redis_sync;
	}

}
