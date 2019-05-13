package com.flex.entities;

import java.util.Date;

import com.flex.utils.Lib;

public class DeviceActiveEntity extends BaseEntity {
	
	private String imei;
	private int device_type;
	private int vehicle_id;
	private int active; // tình trạng active của phương tiện gắn thiết bị
	private Date time_load;
		
	public DeviceActiveEntity(){}
	public DeviceActiveEntity createEntityFromVehicleEntity (VehicleEntity vehicle){
		DeviceActiveEntity obj = new DeviceActiveEntity();
		obj.imei = vehicle.getDevice_imei();
		obj.device_type = vehicle.getDevice_type();
		obj.vehicle_id = vehicle.getId();
		obj.active = vehicle.getActive();
		obj.time_load = Lib.getCurrentDate();
		
		return obj;
	}
	
	public int getVehicle_id() {
		return vehicle_id;
	}
	public void setVehicle_id(int vehicle_id) {
		this.vehicle_id = vehicle_id;
	}
	public int getActive() {
		return active;
	}
	public void setActive(int active) {
		this.active = active;
	}
	public Date getTime_load() {
		return time_load;
	}
	public void setTime_load(Date time_load) {
		this.time_load = time_load;
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
}
