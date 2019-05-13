package com.flex.entities;

import java.util.Date;

import com.flex.utils.Lib;

public class SmsTransactionEntity extends BaseEntity {

	private String id;
	
	private String user_name;
	
	private int vehicle_id;
	private Date trk_time;
	private int type;
	private String content;
	private String phones;
	private double money;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getVehicle_id() {
		return vehicle_id;
	}
	public void setVehicle_id(int vehicle_id) {
		this.vehicle_id = vehicle_id;
	}
	public Date getTrk_time() {
		return trk_time;
	}
	public void setTrk_time(Date trk_time) {
		this.trk_time = trk_time;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getPhones() {
		return phones;
	}
	public void setPhones(String phones) {
		this.phones = phones;
	}
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}


	/***
	 * Hàm kiểm tra dữ liệu với mỗi đối tượng sms transaction
	 * thompt add 08/01/2019
	 */
	public void validateSmsTransaction(){
		// phones không quá 100 kí tự
		if(!Lib.isBlank(this.phones)&& this.phones.length()>100){
			String name1 = this.phones.substring(0, Math.min(this.phones.length(), 100));
			this.setPhones(name1);
		}
		
		// content không quá 1000 kí tự
		if(!Lib.isBlank(this.content)&& this.content.length()>1000){
			String name1 = this.content.substring(0, Math.min(this.content.length(), 1000));
			this.setContent(name1);
		}
	
		// Tài khoản đăng nhập ko quá 30 kí tự
		if(!Lib.isBlank(this.user_name)&& this.user_name.length()>30){
			String name1 = this.user_name.substring(0, Math.min(this.user_name.length(), 30));
			this.setUser_name(name1);
		}
						
	}
}
