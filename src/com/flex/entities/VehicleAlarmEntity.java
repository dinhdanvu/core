package com.flex.entities;

import java.util.Date;

import com.flex.dbmanager.attributes.RedisClassNameAliasAttribute;
import com.flex.dbmanager.attributes.RedisIndexAttribute;
import com.flex.dbmanager.attributes.RedisKeyAttribute;
import com.flex.utils.Constants;
import com.flex.utils.Lib;
@RedisKeyAttribute(key="key")
@RedisIndexAttribute(indexs={"user_name"})
@RedisClassNameAliasAttribute(name="Alarm")
public class VehicleAlarmEntity extends BaseEntity{
	private String db;
	private int id;
	private int data_group;
	private String alarm_code;
	private String alarm_title;
	private int vehicle_id;
	private String vehicle_name;
	private String imei;
	private String user_name;
	private String driver_code;
	private String driver_name;
	private int is_device_driver;
	private long location_id;
	private String location_poits;
	private Date trk_time;
	private double latitude;
	private double longitude;
	private String address;
	private String msg_content;
	private int sent_limit; // = send_time - số lần gửi tối đa
	private int sent_time_between; // = Thời gian giữa hai lần gửi
	private int sent_count;  // Số lần tạo cảnh báo
	
	
	private int index; // chỉ số này dùng cho các xe có nhiều cấu hình nhiệt độ, độ ẩm
	

	private int is_alarm_speaker;
	private int is_alarm_screen;
	private int is_read;
	private Date read_time;
	
	private int is_notification;
	private int is_alarm_device_screen; // Gửi màn hình thiết bị
	
	private int is_alarm_sms;
	private String phone_numbers;
	
	// ghi nhan ket qua gui tin nhan: 1:Gui tin nhan thanh cong. 0: Gui tin nhan that bai, 2: tin nhan bi timeout
	private int is_read_sms;
	private int result_status_sms;
	private Date read_time_sms; // thời gian gửi sms
	private int sent_count_sms; // Số lần gửi  sms
		
		
	private int is_alarm_email;
	private String emails;
	private int is_read_email;
	private Date read_time_email;
	private int result_status_email;
	private int sent_count_email; // Số lần gửi  email
	
	
	private String key;
		
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the alarm_code
	 */
	public String getAlarm_code() {
		return alarm_code;
	}
	/**
	 * @param alarm_code the alarm_code to set
	 */
	public void setAlarm_code(String alarm_code) {
		this.alarm_code = alarm_code;
	}
	/**
	 * @return the vehicle_id
	 */
	public int getVehicle_id() {
		return vehicle_id;
	}
	/**
	 * @param vehicle_id the vehicle_id to set
	 */
	public void setVehicle_id(int vehicle_id) {
		this.vehicle_id = vehicle_id;
	}
	/**
	 * @return the imei
	 */
	public String getImei() {
		return imei;
	}
	/**
	 * @param imei the imei to set
	 */
	public void setImei(String imei) {
		this.imei = imei;
	}
	/**
	 * @return the user_name
	 */
	public String getUser_name() {
		return user_name;
	}
	/**
	 * @param user_name the user_name to set
	 */
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	/**
	 * @return the trk_time
	 */
	public Date getTrk_time() {
		return trk_time;
	}
	/**
	 * @param trk_time the trk_time to set
	 */
	public void setTrk_time(Date trk_time) {
		this.trk_time = trk_time;
	}
	/**
	 * @return the latitude
	 */
	public double getLatitude() {
		return latitude;
	}
	/**
	 * @param latitude the latitude to set
	 */
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	/**
	 * @return the longitude
	 */
	public double getLongitude() {
		return longitude;
	}
	/**
	 * @param longitude the longitude to set
	 */
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * @return the description
	 */
	public String getMsg_content() {
		return msg_content;
	}
	/**
	 * @param description the description to set
	 */
	public void setMsg_content(String description) {
		this.msg_content = description;
	}
	
	public int getData_group() {
		return data_group;
	}
	public void setData_group(int data_group) {
		this.data_group = data_group;
	}
	public String getDriver_code() {
		return driver_code;
	}
	public void setDriver_code(String driver_code) {
		this.driver_code = driver_code;
	}
	public long getLocation_id() {
		return location_id;
	}
	public void setLocation_id(long location_id) {
		this.location_id = location_id;
	}
	public String getKey() {
		key =this.vehicle_id+"_"+this.alarm_code;
		int idx = this.alarm_code.indexOf("region");
		if(this.location_id>0 && idx>=0){
			key+="_"+location_id;
		}
		//thompt add
		idx = this.alarm_code.indexOf("humidity");
		if(idx>=0 && this.index>0 ){
			key+="_"+ this.index;
		}
		//thompt add
		idx = this.alarm_code.indexOf("temperature");
		if(idx>=0 && this.index>0){
			key+="_"+ this.index;
		}
		
		//thompt add
		idx = this.alarm_code.indexOf("concrete");
		if (idx >= 0 && this.index > 0) {
			key += "_" + this.index;
		}
	
		return key;
	}
	
	public int getSent_limit() {
		return sent_limit;
	}
	public void setSent_limit(int sent_limit) {
		this.sent_limit = sent_limit;
	}
	public int getIs_alarm_screen() {
		return is_alarm_screen;
	}
	public void setIs_alarm_screen(int is_alarm_screen) {
		this.is_alarm_screen = is_alarm_screen;
	}
	public int getIs_alarm_sms() {
		return is_alarm_sms;
	}
	public void setIs_alarm_sms(int is_alarm_sms) {
		this.is_alarm_sms = is_alarm_sms;
	}
	public String getPhone_numbers() {
		return phone_numbers;
	}
	public void setPhone_numbers(String phone_numbers) {
		this.phone_numbers = phone_numbers;
	}
	public int getIs_alarm_email() {
		return is_alarm_email;
	}
	public void setIs_alarm_email(int is_alarm_email) {
		this.is_alarm_email = is_alarm_email;
	}
	public String getEmails() {
		return emails;
	}
	public void setEmails(String emails) {
		this.emails = emails;
	}
	public int getIs_alarm_speaker() {
		return is_alarm_speaker;
	}
	public void setIs_alarm_speaker(int is_alarm_speaker) {
		this.is_alarm_speaker = is_alarm_speaker;
	}
	public int getIs_notification() {
		return is_notification;
	}
	public void setIs_notification(int is_notification) {
		this.is_notification = is_notification;
	}
	public String getAlarm_title() {
		return alarm_title;
	}
	public void setAlarm_title(String alarm_title) {
		this.alarm_title = alarm_title;
	}
	public int getIs_device_driver() {
		return is_device_driver;
	}
	public void setIs_device_driver(int is_device_driver) {
		this.is_device_driver = is_device_driver;
	}
	public String getVehicle_name() {
		return vehicle_name;
	}
	public void setVehicle_name(String vehicle_name) {
		this.vehicle_name = vehicle_name;
	}
	public String getDriver_name() {
		return driver_name;
	}
	public void setDriver_name(String driver_name) {
		this.driver_name = driver_name;
	}
	public String getLocation_poits() {
		return location_poits;
	}
	public void setLocation_poits(String location_poits) {
		this.location_poits = location_poits;
	}

	public String getDb() {
		int year = Lib.getYearByDate(this.trk_time);
		int month = Lib.getMonthByDate(this.trk_time);
		db=Constants.DB_PREFIX+year+Lib.padLeft(month+"",2);
		return db;
	}
	public int getIs_alarm_device_screen() {
		return is_alarm_device_screen;
	}
	public void setIs_alarm_device_screen(int is_alarm_device_screen) {
		this.is_alarm_device_screen = is_alarm_device_screen;
	}
	public int getSent_time_between() {
		return sent_time_between;
	}
	public void setSent_time_between(int sent_time_between) {
		this.sent_time_between = sent_time_between;
	}
	public int getIs_read_sms() {
		return is_read_sms;
	}
	public void setIs_read_sms(int is_read_sms) {
		this.is_read_sms = is_read_sms;
	}
	public int getResult_status_sms() {
		return result_status_sms;
	}
	public void setResult_status_sms(int result_status_sms) {
		this.result_status_sms = result_status_sms;
	}
	public Date getRead_time_sms() {
		return read_time_sms;
	}
	public void setRead_time_sms(Date read_time_sms) {
		this.read_time_sms = read_time_sms;
	}
	public int getSent_count_sms() {
		return sent_count_sms;
	}
	public void setSent_count_sms(int sent_count_sms) {
		this.sent_count_sms = sent_count_sms;
	}
	public int getIs_read_email() {
		return is_read_email;
	}
	public void setIs_read_email(int is_read_email) {
		this.is_read_email = is_read_email;
	}
	public Date getRead_time_email() {
		return read_time_email;
	}
	public void setRead_time_email(Date read_time_email) {
		this.read_time_email = read_time_email;
	}
	public int getResult_status_email() {
		return result_status_email;
	}
	public void setResult_status_email(int result_status_email) {
		this.result_status_email = result_status_email;
	}
	public int getSent_count_email() {
		return sent_count_email;
	}
	public void setSent_count_email(int sent_count_email) {
		this.sent_count_email = sent_count_email;
	}
	
	public int getSent_count() {
		return sent_count;
	}
	public void setSent_count(int sent_count) {
		this.sent_count = sent_count;
	}


	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public int getIs_read() {
		return is_read;
	}
	public void setIs_read(int is_read) {
		this.is_read = is_read;
	}
	public Date getRead_time() {
		return read_time;
	}
	public void setRead_time(Date read_time) {
		this.read_time = read_time;
	}


	/***
	 * Hàm kiểm tra dữ liệu với mỗi đối tượng Alarm
	 * thompt add 07/01/2019
	 */
	public void validateAlarm(){
		// Tên không quá 150 kí tự
		if(!Lib.isBlank(this.vehicle_name)&& this.vehicle_name.length()>150){
			String name1 = this.vehicle_name.substring(0, Math.min(this.vehicle_name.length(), 150));
			this.setVehicle_name(name1);
		}
		
		// Imei không quá 30 kí tự
		if(!Lib.isBlank(this.imei)&& this.imei.length()>30){
			String name1 = this.imei.substring(0, Math.min(this.imei.length(), 30));
			this.setImei(name1);
		}
				
		// Tài khoản đăng nhập ko quá 30 kí tự
		if(!Lib.isBlank(this.user_name)&& this.user_name.length()>30){
			String name1 = this.user_name.substring(0, Math.min(this.user_name.length(), 30));
			this.setUser_name(name1);
		}
		
		//Mã tài xế không quá 20 kí tự
		if(!Lib.isBlank(this.driver_code)&& this.driver_code.length()>20){
			String name1 = this.driver_code.substring(0, Math.min(this.driver_code.length(), 20));
			this.setDriver_code(name1);
		}
		
		//Tên tài xế không quá 255 kí tự
		if(!Lib.isBlank(this.driver_name)&& this.driver_name.length()>255){
			String name1 = this.driver_name.substring(0, Math.min(this.driver_name.length(), 255));
			this.setDriver_name(name1);
		}
		
		// alarm_code không quá 50 kí tự
		if(!Lib.isBlank(this.alarm_code)&& this.alarm_code.length()>50){
			String name1 = this.alarm_code.substring(0, Math.min(this.alarm_code.length(), 50));
			this.setAlarm_code(name1);
		}
				
		//location_poits không quá 1000 kí tự
		if(!Lib.isBlank(this.location_poits)&& this.location_poits.length()>1000){
			String name1 = this.location_poits.substring(0, Math.min(this.location_poits.length(), 1000));
			this.setLocation_poits(name1);
		}
			
		// emails không quá 255 kí tự
		if(!Lib.isBlank(this.emails)&& this.emails.length()>255){
			String name1 = this.emails.substring(0, Math.min(this.emails.length(), 255));
			this.setEmails(name1);
		}
		
		// phone_numbers không quá 255 kí tự
		if(!Lib.isBlank(this.phone_numbers)&& this.phone_numbers.length()>255){
			String name1 = this.phone_numbers.substring(0, Math.min(this.phone_numbers.length(), 255));
			this.setPhone_numbers(name1);
		}
		
		// address không quá 255 kí tự
		if(!Lib.isBlank(this.address)&& this.address.length()>255){
			String name1 = this.address.substring(0, Math.min(this.address.length(), 255));
			this.setAddress(name1);
		}
		
		//msg_content không quá 1000 kí tự
		if(!Lib.isBlank(this.msg_content)&& this.msg_content.length()>1000){
			String name1 = this.msg_content.substring(0, Math.min(this.msg_content.length(), 1000));
			this.setMsg_content(name1);
		}
				
	}
}
