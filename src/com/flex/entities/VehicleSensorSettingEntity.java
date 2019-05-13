package com.flex.entities;

import com.flex.dbmanager.attributes.RedisClassNameAliasAttribute;
import com.flex.dbmanager.attributes.RedisIndexAttribute;
import com.flex.dbmanager.attributes.RedisKeyAttribute;
import com.flex.utils.Lib;

@RedisKeyAttribute(key="key")
@RedisIndexAttribute(indexs={"vehicle_id","device_type"})
@RedisClassNameAliasAttribute(name="Vehicle_sensor")
public class VehicleSensorSettingEntity{
	private int id;
	private int device_type;
	private int vehicle_id;
	private String sensor_param_code;
	private String status_alias_map;
	private int sensor_type;
	private int sensor_index;
	private int is_calib;
	private String formular;
	private int is_dependent_sensor;
	private int dependent_sensor_index;
	private String dependent_sensor_code;
	private int dependent_sensor_type;
	private int dependent_is_calib;
	private String dependent_formular;
	private String  input_name;
	private String func_name;
	private String param_unit;
	private int is_delete;
	private int is_default;
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
	 * @return the sensor_param_code
	 */
	public String getSensor_param_code() {
		return sensor_param_code;
	}
	/**
	 * @param sensor_param_code the sensor_param_code to set
	 */
	public void setSensor_param_code(String sensor_param_code) {
		this.sensor_param_code = sensor_param_code;
	}
	/**
	 * @return the sensor_number
	 */
	public int getSensor_index() {
		return sensor_index;
	}
	/**
	 * @param sensor_number the sensor_number to set
	 */
	public void setSensor_index(int sensor_number) {
		this.sensor_index = sensor_number;
	}
	/**
	 * @return the is_calib
	 */
	public int getIs_calib() {
		return is_calib;
	}
	/**
	 * @param is_calib the is_calib to set
	 */
	public void setIs_calib(int is_calib) {
		this.is_calib = is_calib;
	}
	/**
	 * @return the formular
	 */
	public String getFormular() {
		return formular;
	}
	/**
	 * @param formular the formular to set
	 */
	public void setFormular(String formular) {
		this.formular = formular;
	}
	/**
	 * @return the is_dependent_sensor
	 */
	public int getIs_dependent_sensor() {
		return is_dependent_sensor;
	}
	/**
	 * @param is_dependent_sensor the is_dependent_sensor to set
	 */
	public void setIs_dependent_sensor(int is_dependent_sensor) {
		this.is_dependent_sensor = is_dependent_sensor;
	}
	/**
	 * @return the dependent_is_calib
	 */
	public int getDependent_is_calib() {
		return dependent_is_calib;
	}
	/**
	 * @param dependent_is_calib the dependent_is_calib to set
	 */
	public void setDependent_is_calib(int dependent_is_calib) {
		this.dependent_is_calib = dependent_is_calib;
	}
	/**
	 * @return the dependent_formular
	 */
	public String getDependent_formular() {
		return dependent_formular;
	}
	/**
	 * @param dependent_formular the dependent_formular to set
	 */
	public void setDependent_formular(String dependent_formular) {
		this.dependent_formular = dependent_formular;
	}
	/**
	 * @return the input_name
	 */
	public String getInput_name() {
		return input_name;
	}
	/**
	 * @param input_name the input_name to set
	 */
	public void setInput_name(String input_name) {
		this.input_name = input_name;
	}
	/**
	 * @return the func_name
	 */
	public String getFunc_name() {
		return func_name;
	}
	/**
	 * @param func_name the func_name to set
	 */
	public void setFunc_name(String func_name) {
		this.func_name = func_name;
	}
	/**
	 * @return the param_unit
	 */
	public String getParam_unit() {
		return param_unit;
	}
	/**
	 * @param param_unit the param_unit to set
	 */
	public void setParam_unit(String param_unit) {
		this.param_unit = param_unit;
	}
	/**
	 * @return the is_delete
	 */
	public int getIs_delete() {
		return is_delete;
	}
	/**
	 * @param is_delete the is_delete to set
	 */
	public void setIs_delete(int is_delete) {
		this.is_delete = is_delete;
	}
	/**
	 * @return the key
	 */
	public String getKey() {
		if(this.device_type>0){
			key = this.device_type+"_"+this.sensor_param_code+"_"+sensor_index;
		}else{
			key = this.vehicle_id+"_"+this.sensor_param_code+"_"+sensor_index;
		}
		return key;
	}
	public int getIs_default() {
		return is_default;
	}
	public void setIs_default(int is_default) {
		this.is_default = is_default;
	}
	public int getDevice_type() {
		if(getIs_default()>0){
			return device_type;
		}
		return -1;
	}
	public void setDevice_type(int device_type) {
		this.device_type = device_type;
	}
	public int getDependent_sensor_index() {
		return dependent_sensor_index;
	}
	public void setDependent_sensor_index(int dependent_sensor_number) {
		this.dependent_sensor_index = dependent_sensor_number;
	}
	public String getDependent_sensor_code() {
		return dependent_sensor_code;
	}
	public void setDependent_sensor_code(String dependent_sensor_code) {
		this.dependent_sensor_code = dependent_sensor_code;
	}
	public int getDependent_sensor_type() {
		return dependent_sensor_type;
	}
	public void setDependent_sensor_type(int dependent_sensor_type) {
		this.dependent_sensor_type = dependent_sensor_type;
	}
	public int getSensor_type() {
		return sensor_type;
	}
	public void setSensor_type(int sensor_type) {
		this.sensor_type = sensor_type;
	}
	public double getMinCalibVal() {
		try{
			if(Lib.isBlank(formular))
			{
				return -1;
			}
			String[] pairs = formular.trim().split(";");
			if(null==pairs||pairs.length<=0){
				return -1;
			}
			String[] tmp = pairs[0].split(",");
			if(null==tmp||tmp.length<=0){
				return -1;
			};
			return Lib.strToDouble(tmp[0]);
		}catch (Exception e) {
			return -1;
		}
	}
	public double getMaxCalibVal() {
		try{
			if(Lib.isBlank(formular))
			{
				return -1;
			}
			String[] pairs = formular.trim().split(";");
			if(null==pairs||pairs.length<=0){
				return -1;
			}
			String[] tmp = pairs[pairs.length-1].split(",");
			if(null==tmp||tmp.length<=0){
				return -1;
			};
			return Lib.strToDouble(tmp[0]);
		}catch (Exception e) {
			return -1;
		}
	}
	public String getStatus_alias_map() {
		return status_alias_map;
	}
	public void setStatus_alias_map(String status_alias_map) {
		this.status_alias_map = status_alias_map;
	}
}
