package com.flex.entities;

import java.util.Date;

import com.flex.dbmanager.attributes.RedisClassNameAliasAttribute;
import com.flex.dbmanager.attributes.RedisIndexAttribute;
import com.flex.dbmanager.attributes.RedisKeyAttribute;

@RedisKeyAttribute(key="key")
@RedisClassNameAliasAttribute(name="DevCMD")
public class DeviceConfigCMDEntity{
	private int id;
	private int dev_type;
	private String cmd_code;
	private String cmd_text;
	private String description;
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
	 * @return the dev_type
	 */
	public int getDev_type() {
		return dev_type;
	}
	/**
	 * @param dev_type the dev_type to set
	 */
	public void setDev_type(int dev_type) {
		this.dev_type = dev_type;
	}
	/**
	 * @return the cmd_type
	 */
	public String getCmd_code() {
		return cmd_code;
	}
	/**
	 * @param cmd_type the cmd_type to set
	 */
	public void setCmd_code(String cmd_code) {
		this.cmd_code = cmd_code;
	}
	/**
	 * @return the cmd_text
	 */
	public String getCmd_text() {
		return cmd_text;
	}
	/**
	 * @param cmd_text the cmd_text to set
	 */
	public void setCmd_text(String cmd_text) {
		this.cmd_text = cmd_text;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the key
	 */
	public String getKey() {
		return key;
	}
}
