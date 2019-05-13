package com.flex.entities;

import java.util.Date;

import com.flex.dbmanager.attributes.RedisClassNameAliasAttribute;
import com.flex.dbmanager.attributes.RedisIgnoreKeyAttributes;
import com.flex.dbmanager.attributes.RedisIndexAttribute;
import com.flex.dbmanager.attributes.RedisKeyAttribute;
import com.flex.utils.Lib;
@RedisIndexAttribute(indexs={"user_name"})
@RedisKeyAttribute(key="key")
@RedisClassNameAliasAttribute(name="Driver")
@RedisIgnoreKeyAttributes(ignoreKey={"is_delete","active","created_user","created_date","update_user","update_date"})
public class DriverEntity extends BaseEntity {

	private int id;
	private String key;
	private String name;
	private int group_id;
//	private String address;
	private String phone;
//	private String license_type;
	private String license_no;
	private Date expired;
	private String user_name;
	private int active;
	private int is_delete;


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getLicense_no() {
		return license_no;
	}

	public void setLicense_no(String license_no) {
		this.license_no = license_no;
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

	public int getGroup_id() {
		return group_id;
	}

	public void setGroup_id(int group_id) {
		this.group_id = group_id;
	}

	public int getIs_delete() {
		return is_delete;
	}

	public void setIs_delete(int is_delete) {
		this.is_delete = is_delete;
	}

	public String getKey() {
		key = user_name+"_"+license_no;
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public Date getExpired() {
		return expired;
	}

	public void setExpired(Date expired) {
		this.expired = expired;
	}


	/***
	 * Hàm kiểm tra dữ liệu với mỗi đối tượng Driver
	 * thompt add 08/01/2019
	 */
	public void validateDriver(){
		// Tên không quá 50 kí tự
		if(!Lib.isBlank(this.name)&& this.name.length()>50){
			String name1 = this.name.substring(0, Math.min(this.name.length(), 50));
			this.setName(name1);
		}
		
		// phone không quá 20 kí tự
		if(!Lib.isBlank(this.phone)&& this.phone.length()>20){
			String name1 = this.phone.substring(0, Math.min(this.phone.length(), 20));
			this.setPhone(name1);
		}
		
		// license_no không quá 20 kí tự
		if(!Lib.isBlank(this.license_no)&& this.license_no.length()>20){
			String name1 = this.license_no.substring(0, Math.min(this.license_no.length(), 20));
			this.setLicense_no(name1);
		}
				
		// Tài khoản đăng nhập ko quá 30 kí tự
		if(!Lib.isBlank(this.user_name)&& this.user_name.length()>30){
			String name1 = this.user_name.substring(0, Math.min(this.user_name.length(), 30));
			this.setUser_name(name1);
		}
		
	}
}
