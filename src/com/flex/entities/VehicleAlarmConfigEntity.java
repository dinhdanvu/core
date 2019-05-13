package com.flex.entities;

import java.util.Date;

import com.flex.dbmanager.attributes.RedisClassNameAliasAttribute;
import com.flex.dbmanager.attributes.RedisIndexAttribute;
import com.flex.dbmanager.attributes.RedisKeyAttribute;

@RedisClassNameAliasAttribute(name="Alarm_config")
@RedisKeyAttribute(key="id")
@RedisIndexAttribute(indexs={"vehicle_id"})
public class VehicleAlarmConfigEntity{
	private int id;
	private int vehicle_id;
	private String alarm_code;
	private String lst_loc_id;
	private String value;
	private int send_time_between;
	private int send_time;
	private int is_alarm_speaker;
	private int is_alarm_screen;
	private int is_alarm_sms;
	private String phone_numbers;
	private int is_alarm_email;
	private int is_alarm_device_screen;
	private String emails;
	private int is_delete;
	
//	private String key;
//	private String label;
////	private String value;
//	private String unit;
//	private String note;
//	private String max_length;
//	private String field_type;
//	//index
//	private String vehicle_alarm_code; 

	
//	[{"key":"tank_mixing","label":"Bồn trộn","value":"1","unit":"","note":"Cảnh báo khi bồn trộn","max_length":"1","field_type":"3"},
//	 {"key":"tank_discharge","label":"Bồn xả","value":"1","unit":"","note":"Cảnh báo khi bồn xả","max_length":"1","field_type":"3"},
//	 {"key":"tank_off","label":"Bồn dừng","value":"1","unit":"","note":"Cảnh báo khi bồn dừng","max_length":"1","field_type":"3"},
//	 {"key":"limit_duration","label":"Thời gian","value":"5","unit":"phút","note":"Chỉ cảnh báo khi thỏa các điều kiện lâu hơn thời gian này","max_length":"3","field_type":"1"},
//	 {"key":"vehicle_moving","label":"Xe chạy","value":"1","unit":"","note":"Chỉ cảnh báo khi xe đang chạy","max_length":"1","field_type":"3"},
//	 {"key":"message_content","label":"Nội dung cảnh báo ","value":"Hoạt động bồn bê tông đang vi phạm","unit":"","note":"Nội dung hiển thị khi cảnh báo","max_length":"300","field_type":"0"}
//	 ]
	
//	[{"key":"limit_duration","label":"Thời gian","value":"5","unit":"phút","note":"Chỉ cảnh báo khi dừng đến thời gian này","max_length":"3","field_type":"1"},
//	 {"key":"message_content","label":"Nội dung cảnh báo ","value":"Cảnh báo dừng lâu","unit":"","note":"Nội dung hiển thị khi cảnh báo","max_length":"300","field_type":"0"}]

//	public String getImei() {
//		return imei;
//	}
//	public void setImei(String imei) {
//		this.imei = imei;
//	}
	public int getVehicle_id() {
		return vehicle_id;
	}
	public void setVehicle_id(int vehicle_id) {
		this.vehicle_id = vehicle_id;
	}
	
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public int getIs_delete() {
		return is_delete;
	}
	public void setIs_delete(int is_delete) {
		this.is_delete = is_delete;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getAlarm_code() {
		return alarm_code;
	}
	public void setAlarm_code(String alarm_code) {
		this.alarm_code = alarm_code;
	}
	public String getLst_loc_id() {
		return lst_loc_id;
	}
	public void setLst_loc_id(String lst_loc_id) {
		this.lst_loc_id = lst_loc_id;
	}
	public int getSend_time_between() {
		return send_time_between;
	}
	public void setSend_time_between(int send_time_between) {
		this.send_time_between = send_time_between;
	}
	public int getIs_alarm_speaker() {
		return is_alarm_speaker;
	}
	public void setIs_alarm_speaker(int is_alarm_speaker) {
		this.is_alarm_speaker = is_alarm_speaker;
	}
	public int getIs_alarm_screen() {
		return is_alarm_screen;
	}
	public void setIs_alarm_screen(int is_alarm_screen) {
		this.is_alarm_screen = is_alarm_screen;
	}
	public int getSend_time() {
		return send_time;
	}
	public void setSend_time(int send_length) {
		this.send_time = send_length;
	}
	public int getIs_alarm_sms() {
		return is_alarm_sms;
	}
	public void setIs_alarm_sms(int is_alarm_sms) {
		this.is_alarm_sms = is_alarm_sms;
	}
	public int getIs_alarm_email() {
		return is_alarm_email;
	}
	public void setIs_alarm_email(int is_alarm_email) {
		this.is_alarm_email = is_alarm_email;
	}
	public String getPhone_numbers() {
		return phone_numbers;
	}
	public void setPhone_numbers(String phone_numbers) {
		this.phone_numbers = phone_numbers;
	}
	public String getEmails() {
		return emails;
	}
	public void setEmails(String emails) {
		this.emails = emails;
	}
	public int getIs_alarm_device_screen() {
		return is_alarm_device_screen;
	}
	public void setIs_alarm_device_screen(int is_alarm_device_screen) {
		this.is_alarm_device_screen = is_alarm_device_screen;
	}
	
//	public String getCfg_key() {
//		try{
//			cfg_key = this.vehicle_id+"_"+this.getAlarm_code()+"_"+this.key;
//			return cfg_key;
//		}catch (Exception e) {
//			// TODO: handle exception
//			return "";
//		}
//	}
}
