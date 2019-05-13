package com.flex.entities.worker;


import java.util.Date;
import java.util.List;

import com.flex.dbmanager.attributes.RedisClassNameAliasAttribute;
import com.flex.dbmanager.attributes.RedisKeyAttribute;
import com.flex.entities.BaseEntity;
import com.flex.utils.Constants;
import com.flex.utils.Lib;

@RedisClassNameAliasAttribute(name="Event")
@RedisKeyAttribute(key="code")
public class EventEntity extends BaseEntity{
	private String db;
	private long id;
	private String event_code;
	private String event_name;
	private long vehicle_id;
	private String imei;
	private String user_name;
	private Date trk_time;
	private String driver_code;
	private double latitude;
	private double longitude;
	private long location_id;
	private String location_poits;
	private String address;
	private int report;
	private String description;
	private int type;
	private int data_group;
	private List value_list;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getEvent_code() {
		return event_code;
	}
	public void setEvent_code(String code) {
		this.event_code = code;
	}
	public long getVehicle_id() {
		return vehicle_id;
	}
	public void setVehicle_id(long vehicle_id) {
		this.vehicle_id = vehicle_id;
	}
	public String getImei() {
		return imei;
	}
	public void setImei(String imei) {
		this.imei = imei;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public Date getTrk_time() {
		return trk_time;
	}
	public void setTrk_time(Date trk_time) {
		this.trk_time = trk_time;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getData_group() {
		return data_group;
	}
	public void setData_group(int data_group) {
		this.data_group = data_group;
	}
	public int getReport() {
		return report;
	}
	public void setReport(int report) {
		this.report = report;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public List getValue_list() {
		return value_list;
	}
	public void setValue_list(List value_list) {
		this.value_list = value_list;
	}
	public String getDriver_code() {
		return driver_code;
	}
	public void setDriver_code(String driver_code) {
		this.driver_code = driver_code;
	}
	public String getDb() {
		int year = Lib.getYearByDate(this.trk_time);
		int month = Lib.getMonthByDate(this.trk_time);
		db=Constants.DB_PREFIX+year+Lib.padLeft(month+"",2);
		return db;
	}
	public String getEvent_name() {
		return event_name;
	}
	public void setEvent_name(String event_name) {
		this.event_name = event_name;
	}
	public long getLocation_id() {
		return location_id;
	}
	public void setLocation_id(long location_id) {
		this.location_id = location_id;
	}
	public String getLocation_poits() {
		return location_poits;
	}
	public void setLocation_poits(String location_poits) {
		this.location_poits = location_poits;
	}
	
//	event_code	varchar(100)
//	location_poits	varchar(1000)
//	description	varchar(1000)
//	event_name	varchar(150)
//	vehicle_name	varchar(255)
//	driver_name	varchar(255)
//	address	varchar(255)
//	imei	varchar(30)
//	user_name	varchar(30)
//	driver_code	varchar(30)
//	created_user	varchar(30)
//	updated_user	varchar(30)
	/***
	 * Hàm kiểm tra dữ liệu với mỗi đối tượng Event
	 * thompt add 07/01/2019
	 */
	public void validateEvents(){
			
		// Imei không quá 30 kí tự
		if(!Lib.isBlank(this.imei)&& this.imei.length()>30){
			String name1 = this.imei.substring(0, Math.min(this.imei.length(), 30));
			this.setImei(name1);
		}
				
		// Tài khoản đăng nhập ko quá 30 kí tự
		if(!Lib.isBlank(this.user_name) && this.user_name.length()>30){
			String name1 = this.user_name.substring(0, Math.min(this.user_name.length(), 30));
			this.setUser_name(name1);
		}
			
		//Mã tài xế không quá 30 kí tự
		if(!Lib.isBlank(this.driver_code)&& this.driver_code.length()>30){
			String name1 = this.driver_code.substring(0, Math.min(this.driver_code.length(), 30));
			this.setDriver_code(name1);
		}
				
		//location_poits không quá 1000 kí tự
		if(!Lib.isBlank(this.location_poits)&& this.location_poits.length()>1000){
			String name1 = this.location_poits.substring(0, Math.min(this.location_poits.length(), 1000));
			this.setLocation_poits(name1);
		}
					
		// address không quá 255 kí tự
		if(!Lib.isBlank(this.address)&& this.address.length()>255){
			String name1 = this.address.substring(0, Math.min(this.address.length(), 255));
			this.setAddress(name1);
		}
		
		// description không quá 1000 kí tự
		if(!Lib.isBlank(this.description)&& this.description.length()>1000){
			String name1 = this.description.substring(0, Math.min(this.description.length(), 1000));
			this.setDescription(name1);
		}
			
	}
}
