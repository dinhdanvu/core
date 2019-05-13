package com.flex.entities.worker;

import java.util.Date;


import com.flex.dbmanager.attributes.RedisClassNameAliasAttribute;
import com.flex.dbmanager.attributes.RedisIndexAttribute;
import com.flex.dbmanager.attributes.RedisKeyAttribute;
import com.flex.entities.BaseEntity;
import com.flex.utils.Constants;
import com.flex.utils.Lib;

@RedisClassNameAliasAttribute(name="Fuel_consumed")
@RedisKeyAttribute(key="key")
@RedisIndexAttribute(indexs={"user_name","vehicle_id"})
public class FuelConsumedSummaryEntity extends BaseEntity {
	private String db;
	private String key;
	private int id;
	private int vehicle_id;
	private String vehicle_name;
	private String plate;
	private String imei;
	private String user_name;
	private Date date;
	private int sensor_index;
	private String sensor_param_code;
	private String sensor_label;
	private double signal_start;
	private double signal_end;
	private int engine_on_time;
	private int engine_on_move_time;
	private String formular;
	private double fuel_consumed;
	private int data_group;
	public String getKey() {
		try{
			key = this.user_name+"_"+this.vehicle_id+"_"+this.sensor_index+"_"+Lib.dateToString(this.date,"yyyyMMdd");
			return key;
		}
		catch(Exception ex){
			return "";
		}
	}

	/**
	 * @return the signal_start
	 */
	public double getSignal_start() {
		return signal_start;
	}


	/**
	 * @param signal_start the signal_start to set
	 */
	public void setSignal_start(double signal_start) {
		this.signal_start = signal_start;
	}


	/**
	 * @return the signal_end
	 */
	public double getSignal_end() {
		return signal_end;
	}


	/**
	 * @param signal_end the signal_end to set
	 */
	public void setSignal_end(double signal_end) {
		this.signal_end = signal_end;
	}


	/**
	 * @return the engine_on_time
	 */
	public int getEngine_on_time() {
		return engine_on_time;
	}


	/**
	 * @param engine_on_time the engine_on_time to set
	 */
	public void setEngine_on_time(int engine_on_time) {
		this.engine_on_time = engine_on_time;
	}


	/**
	 * @return the engine_on_move_time
	 */
	public int getEngine_on_move_time() {
		return engine_on_move_time;
	}


	/**
	 * @param engine_on_move_time the engine_on_move_time to set
	 */
	public void setEngine_on_move_time(int engine_on_move_time) {
		this.engine_on_move_time = engine_on_move_time;
	}



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
	 * @return the imei
	 */
	public String getImei() {
		return imei;
	}


	/**
	 * @param imei the imei to set
	 */
	public void setImei(String imei) {
		this.imei = imei;
	}


	/**
	 * @return the user_name
	 */
	public String getUser_name() {
		return user_name;
	}


	/**
	 * @param user_name the user_name to set
	 */
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}


	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}


	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}


	/**
	 * @return the sensor_index
	 */
	public int getSensor_index() {
		return sensor_index;
	}


	/**
	 * @param sensor_index the sensor_index to set
	 */
	public void setSensor_index(int sensor_index) {
		this.sensor_index = sensor_index;
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
	 * @return the fuel_consumed
	 */
	public double getFuel_consumed() {
		return fuel_consumed;
	}


	/**
	 * @param fuel_consumed the fuel_consumed to set
	 */
	public void setFuel_consumed(double fuel_consumed) {
		this.fuel_consumed = fuel_consumed;
	}


	public int getData_group() {
		return data_group;
	}


	public void setData_group(int data_group) {
		this.data_group = data_group;
	}

	public String getFormular() {
		return formular;
	}

	public void setFormular(String formular) {
		this.formular = formular;
	}

	public String getSensor_label() {
		return sensor_label;
	}

	public void setSensor_label(String sensor_label) {
		this.sensor_label = sensor_label;
	}

	public String getDb() {
		int year = Lib.getYearByDate(this.date);
		int month = Lib.getMonthByDate(this.date);
		db=Constants.DB_PREFIX+year+Lib.padLeft(month+"",2);
		return db;
	}

	public String getVehicle_name() {
		return vehicle_name;
	}

	public void setVehicle_name(String vehicle_name) {
		this.vehicle_name = vehicle_name;
	}

	public String getPlate() {
		return plate;
	}

	public void setPlate(String plate) {
		this.plate = plate;
	}


	/***
	 * Hàm kiểm tra dữ liệu với mỗi đối tượng Fuel consumed Summary
	 * thompt add 08/01/2019
	 */
	public void validateFuelConsumedSummary(){
		// Tên không quá 150 kí tự
		if(!Lib.isBlank(this.vehicle_name)&& this.vehicle_name.length()>150){
			String name1 = this.vehicle_name.substring(0, Math.min(this.vehicle_name.length(), 150));
			this.setVehicle_name(name1);
		}
		
		// Biển số không quá 64 kí tự
		if(!Lib.isBlank(this.plate)&& this.plate.length()>64){
			String name1 = this.plate.substring(0, Math.min(this.plate.length(), 64));
			this.setPlate(name1);
		}
		
		// Imei không quá 30 kí tự
		if(!Lib.isBlank(this.imei)&& this.imei.length()>30){
			String name1 = this.imei.substring(0, Math.min(this.imei.length(), 30));
			this.setImei(name1);
		}
				
		// Tài khoản đăng nhập ko quá 30 kí tự
		if(!Lib.isBlank(this.user_name)&& this.user_name.length()>30){
			String name1 = this.user_name.substring(0, Math.min(this.user_name.length(), 30));
			this.setUser_name(name1);
		}
		
		//sensor_param_code không quá 30 kí tự
		if(!Lib.isBlank(this.sensor_param_code)&& this.sensor_param_code.length()>30){
			String name1 = this.sensor_param_code.substring(0, Math.min(this.sensor_param_code.length(), 30));
			this.setSensor_param_code(name1);
		}
		
		//formular không quá 255 kí tự
		if(!Lib.isBlank(this.formular)&& this.formular.length()>255){
			String name1 = this.formular.substring(0, Math.min(this.formular.length(), 255));
			this.setFormular(name1);
		}
		
		// sensor_label không quá 100 kí tự
		if(!Lib.isBlank(this.sensor_label)&& this.sensor_label.length()>100){
			String name1 = this.sensor_label.substring(0, Math.min(this.sensor_label.length(), 100));
			this.setSensor_label(name1);
		}
						
	}
}
