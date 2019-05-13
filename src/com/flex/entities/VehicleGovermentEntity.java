package com.flex.entities;

public class VehicleGovermentEntity {
	private String device_imei;
	private int vehicle_id;
	private int is_goverment; 
	private String user_name;
	private int data_group; 
	private int is_process = 0;//đã được xử lý hay chưa
	private String range_from;
	private String range_to;
	private String db;
	
	
	public void setDb(String db) {
		this.db = db;
	}
	public String getDb() {
		return this.db;
	}
	public String getDevice_imei() {
		return device_imei;
	}
	public void setDevice_imei(String device_imei) {
		this.device_imei = device_imei;
	}
	public int getVehicle_id() {
		return vehicle_id;
	}
	public void setVehicle_id(int vehicle_id) {
		this.vehicle_id = vehicle_id;
	}
	public int getIs_goverment() {
		return is_goverment;
	}
	public void setIs_goverment(int is_goverment) {
		this.is_goverment = is_goverment;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public int getData_group() {
		return data_group;
	}
	public void setData_group(int data_group) {
		this.data_group = data_group;
	}
	public int getIs_process() {
		return is_process;
	}
	public void setIs_process(int is_process) {
		this.is_process = is_process;
	}
	public String getRange_from() {
		return range_from;
	}
	public void setRange_from(String range_from) {
		this.range_from = range_from;
	}
	public String getRange_to() {
		return range_to;
	}
	public void setRange_to(String range_to) {
		this.range_to = range_to;
	}
	
	
}
