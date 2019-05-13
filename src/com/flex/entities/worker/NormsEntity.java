package com.flex.entities.worker;


import com.flex.dbmanager.attributes.RedisClassNameAliasAttribute;
import com.flex.dbmanager.attributes.RedisIndexAttribute;
import com.flex.dbmanager.attributes.RedisKeyAttribute;
import com.flex.entities.BaseEntity;
@RedisKeyAttribute(key="vehicle_id")
@RedisClassNameAliasAttribute(name="Nrm")
public class NormsEntity extends BaseEntity{
	
	private String imei;
	private int input_id;
	private int input_type;//0: input; 1: AD; 2: DFM
	private int norm_type;//nhan hien thi,
	private String factor;
	private String interpolation_fx;
	private double ratio_flw;
	
	public double getRatio_flw() {
		return ratio_flw;
	}
	public void setRatio_flw(double ratio_flw) {
		this.ratio_flw = ratio_flw;
	}
	public String getImei() {
		return imei;
	}
	public void setImei(String imei) {
		this.imei = imei;
	}
	public int getInput_id() {
		return input_id;
	}
	public void setInput_id(int input_id) {
		this.input_id = input_id;
	}
	public int getInput_type() {
		return input_type;
	}
	public void setInput_type(int input_type) {
		this.input_type = input_type;
	}
	public int getNorm_type() {
		return norm_type;
	}
	public void setNorm_type(int norm_type) {
		this.norm_type = norm_type;
	}
	public String getFactor() {
		return factor;
	}
	public void setFactor(String factor) {
		this.factor = factor;
	}
	public String getInterpolation_fx() {
		return interpolation_fx;
	}
	public void setInterpolation_fx(String interpolation_fx) {
		this.interpolation_fx = interpolation_fx;
	}
}
