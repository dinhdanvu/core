package com.flex.entities;

import java.util.Date;

import com.flex.dbmanager.attributes.RedisIndexAttribute;
import com.flex.dbmanager.attributes.RedisKeyAttribute;
import com.flex.utils.Lib;

public class BaseEntity {
	
	private Date created_date;
	private String created_user;
	private Date updated_date;
	private String updated_user;
	/**
	 * @return the created_date
	 */
	public Date getCreated_date() {
		return created_date;
	}
	/**
	 * @param created_date the created_date to set
	 */
	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}
	/**
	 * @return the created_user
	 */
	public String getCreated_user() {
		return created_user;
	}
	/**
	 * @param created_user the created_user to set
	 */
	public void setCreated_user(String created_user) {
		this.created_user = created_user;
	}
	/**
	 * @return the updated_date
	 */
	public Date getUpdated_date() {
		return updated_date;
	}
	/**
	 * @param updated_date the updated_date to set
	 */
	public void setUpdated_date(Date updated_date) {
		this.updated_date = updated_date;
	}
	/**
	 * @return the updated_user
	 */
	public String getUpdated_user() {
		return updated_user;
	}
	/**
	 * @param updated_user the updated_user to set
	 */
	public void setUpdated_user(String updated_user) {
		this.updated_user = updated_user;
	}

}
