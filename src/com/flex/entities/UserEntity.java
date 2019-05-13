package com.flex.entities;

import java.util.Date;

import com.flex.dbmanager.attributes.RedisClassNameAliasAttribute;
import com.flex.dbmanager.attributes.RedisIgnoreKeyAttributes;
import com.flex.dbmanager.attributes.RedisIndexAttribute;
import com.flex.dbmanager.attributes.RedisKeyAttribute;

@RedisKeyAttribute(key="user_name")
@RedisClassNameAliasAttribute(name="User")
//@RedisIgnoreKeyAttributes(ignoreKey={"created_user","created_date","update_user","update_date"})
public class UserEntity extends BaseEntity {

	private String name;
	private String user_name;
	private String password;
	private int role_id;
	private String email;
	private String address;
	private String phone;
	private String mobile;
	private String server_group;
	private int data_group;
	private String operation_type;
	private double sms_money; // Tiền dịch vụ sms cho cả user
	
	private int active;
	private int is_delete;

	
	
	public double getSms_money() {
		return sms_money;
	}

	public void setSms_money(double sms_money) {
		this.sms_money = sms_money;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}
	
	public int getIs_delete() {
		return is_delete;
	}

	public void setIs_delete(int is_delete) {
		this.is_delete = is_delete;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getServer_group() {
		return server_group;
	}

	public void setServer_group(String server_group) {
		this.server_group = server_group;
	}

	public int getData_group() {
		return data_group;
	}

	public void setData_group(int data_group) {
		this.data_group = data_group;
	}

	public String getOperation_type() {
		return operation_type;
	}

	public void setOperation_type(String operation_type) {
		this.operation_type = operation_type;
	}

	public int getRole_id() {
		return role_id;
	}

	public void setRole_id(int role_id) {
		this.role_id = role_id;
	}

	
}
