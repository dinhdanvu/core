package com.flex.entities;

import com.flex.dbmanager.attributes.RedisClassNameAliasAttribute;
import com.flex.dbmanager.attributes.RedisIndexAttribute;
import com.flex.dbmanager.attributes.RedisKeyAttribute;

@RedisKeyAttribute(key="vehicle_id")
@RedisClassNameAliasAttribute(name="IX_Alarm_config")
public class AlarmConfigInfoEntity{
//	private int id;
	private int vehicle_id;
	private String alarm_code;
	private String key;
	private String value;
	private String unit;
	private String note;
	private String phone_numbers;
	private String emails;
	private int send_time_between;
	private int is_alarm_speaker;
	private int is_alarm_screen;
	private int is_alarm_sms;
	private int is_alarm_email;
	private String alarm_title;
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
	 * @return the key
	 */
	public String getKey() {
		return key;
	}
	/**
	 * @param key the key to set
	 */
	public void setKey(String key) {
		this.key = key;
	}
	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}
	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}
	/**
	 * @return the unit
	 */
	public String getUnit() {
		return unit;
	}
	/**
	 * @param unit the unit to set
	 */
	public void setUnit(String unit) {
		this.unit = unit;
	}
	/**
	 * @return the note
	 */
	public String getNote() {
		return note;
	}
	/**
	 * @param note the note to set
	 */
	public void setNote(String note) {
		this.note = note;
	}
	/**
	 * @return the phone_numbers
	 */
	public String getPhone_numbers() {
		return phone_numbers;
	}
	/**
	 * @param phone_numbers the phone_numbers to set
	 */
	public void setPhone_numbers(String phone_numbers) {
		this.phone_numbers = phone_numbers;
	}
	/**
	 * @return the emails
	 */
	public String getEmails() {
		return emails;
	}
	/**
	 * @param emails the emails to set
	 */
	public void setEmails(String emails) {
		this.emails = emails;
	}
	/**
	 * @return the send_time_between
	 */
	public int getSend_time_between() {
		return send_time_between;
	}
	/**
	 * @param send_time_between the send_time_between to set
	 */
	public void setSend_time_between(int send_time_between) {
		this.send_time_between = send_time_between;
	}
	/**
	 * @return the is_alarm_speaker
	 */
	public int getIs_alarm_speaker() {
		return is_alarm_speaker;
	}
	/**
	 * @param is_alarm_speaker the is_alarm_speaker to set
	 */
	public void setIs_alarm_speaker(int is_alarm_speaker) {
		this.is_alarm_speaker = is_alarm_speaker;
	}
	/**
	 * @return the is_alarm_screen
	 */
	public int getIs_alarm_screen() {
		return is_alarm_screen;
	}
	/**
	 * @param is_alarm_screen the is_alarm_screen to set
	 */
	public void setIs_alarm_screen(int is_alarm_screen) {
		this.is_alarm_screen = is_alarm_screen;
	}
	/**
	 * @return the is_alarm_sms
	 */
	public int getIs_alarm_sms() {
		return is_alarm_sms;
	}
	/**
	 * @param is_alarm_sms the is_alarm_sms to set
	 */
	public void setIs_alarm_sms(int is_alarm_sms) {
		this.is_alarm_sms = is_alarm_sms;
	}
	/**
	 * @return the is_alarm_email
	 */
	public int getIs_alarm_email() {
		return is_alarm_email;
	}
	/**
	 * @param is_alarm_email the is_alarm_email to set
	 */
	public void setIs_alarm_email(int is_alarm_email) {
		this.is_alarm_email = is_alarm_email;
	}
	public String getAlarm_title() {
		return alarm_title;
	}
	public void setAlarm_title(String alarm_title) {
		this.alarm_title = alarm_title;
	}
	
//	private String label;
//	private String max_length;
//	private String field_type;

	
//	[{"key":"tank_mixing","label":"Bồn trộn","value":"1","unit":"","note":"Cảnh báo khi bồn trộn","max_length":"1","field_type":"3"},
//	 {"key":"tank_discharge","label":"Bồn xả","value":"1","unit":"","note":"Cảnh báo khi bồn xả","max_length":"1","field_type":"3"},
//	 {"key":"tank_off","label":"Bồn dừng","value":"1","unit":"","note":"Cảnh báo khi bồn dừng","max_length":"1","field_type":"3"},
//	 {"key":"limit_duration","label":"Thời gian","value":"5","unit":"phút","note":"Chỉ cảnh báo khi thỏa các điều kiện lâu hơn thời gian này","max_length":"3","field_type":"1"},
//	 {"key":"vehicle_moving","label":"Xe chạy","value":"1","unit":"","note":"Chỉ cảnh báo khi xe đang chạy","max_length":"1","field_type":"3"},
//	 {"key":"message_content","label":"Nội dung cảnh báo ","value":"Hoạt động bồn bê tông đang vi phạm","unit":"","note":"Nội dung hiển thị khi cảnh báo","max_length":"300","field_type":"0"}
//	 ]
	
//	[{"key":"limit_duration","label":"Thời gian","value":"5","unit":"phút","note":"Chỉ cảnh báo khi dừng đến thời gian này","max_length":"3","field_type":"1"},
//	 {"key":"message_content","label":"Nội dung cảnh báo ","value":"Cảnh báo dừng lâu","unit":"","note":"Nội dung hiển thị khi cảnh báo","max_length":"300","field_type":"0"}]

}
