package com.flex.entities;

import java.io.Serializable;



public class SettingEntity extends BaseEntity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6409139117790680903L;
	private String key;
	private String key_title;
	private String value;
	private String note;
	private String module_id;//All: su dung chung tat ca;SPBS: Smartphonebs; WAPP: CMS; PAPP: cong thong tin dt
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getKey_title() {
		return key_title;
	}
	public void setKey_title(String key_title) {
		this.key_title = key_title;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getModule_id() {
		return module_id;
	}
	public void setModule_id(String module_id) {
		this.module_id = module_id;
	}
	

}
