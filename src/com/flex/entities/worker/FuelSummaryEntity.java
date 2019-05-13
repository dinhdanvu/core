package com.flex.entities.worker;

import java.util.Date;

import javax.xml.crypto.Data;

import com.flex.dbmanager.attributes.RedisClassNameAliasAttribute;
import com.flex.dbmanager.attributes.RedisIndexAttribute;
import com.flex.dbmanager.attributes.RedisKeyAttribute;
import com.flex.entities.BaseEntity;
import com.flex.utils.Constants;
import com.flex.utils.Lib;

@RedisClassNameAliasAttribute(name="FuelSM")
@RedisKeyAttribute(key="key")
@RedisIndexAttribute(indexs={"user_name","vehicle_id"})
public class FuelSummaryEntity extends BaseEntity {
	private String db;
	private String key;
	private int id;
	private int vehicle_id;
	private String vehicle_name;
	private String plate;
	private String imei;
	private String user_name;
	private String driver_code;
	private String driver_name;
	private int is_device_driver;
	private Date date;
	private int sensor_index;
	private String sensor_param_code;
	private String sensor_display_name;
	private double fuel_start;
	private double fuel_end;
	private double fuel_add;
	private double fuel_loss;
	private double fuel_consumed;
	private int data_group;
	public String getKey() {
		try{
//			String code = Lib.isBlank(this.driver_code)?"":this.driver_code;
			key = this.user_name+"_"+this.vehicle_id+"_"+this.sensor_index+"_"+Lib.dateToString(this.date,"yyyyMMdd");
			return key;
		}
		catch(Exception ex){
			return "";
		}
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
	 * @return the fuel_start
	 */
	public double getFuel_start() {
		return fuel_start;
	}


	/**
	 * @param fuel_start the fuel_start to set
	 */
	public void setFuel_start(double fuel_start) {
		this.fuel_start = fuel_start;
	}


	/**
	 * @return the fuel_end
	 */
	public double getFuel_end() {
		return fuel_end;
	}


	/**
	 * @param fuel_end the fuel_end to set
	 */
	public void setFuel_end(double fuel_end) {
		this.fuel_end = fuel_end;
	}


	/**
	 * @return the fuel_add
	 */
	public double getFuel_add() {
		return fuel_add;
	}


	/**
	 * @param fuel_add the fuel_add to set
	 */
	public void setFuel_add(double fuel_add) {
		this.fuel_add = fuel_add;
	}


	/**
	 * @return the fuel_loss
	 */
	public double getFuel_loss() {
		return fuel_loss;
	}


	/**
	 * @param fuel_loss the fuel_loss to set
	 */
	public void setFuel_loss(double fuel_loss) {
		this.fuel_loss = fuel_loss;
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


	public String getDriver_code() {
		return driver_code;
	}


	public void setDriver_code(String driver_code) {
		this.driver_code = driver_code;
	}


	public int getIs_device_driver() {
		return is_device_driver;
	}


	public void setIs_device_driver(int is_device_driver) {
		this.is_device_driver = is_device_driver;
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


	public String getDriver_name() {
		return driver_name;
	}


	public void setDriver_name(String driver_name) {
		this.driver_name = driver_name;
	}


	public String getSensor_display_name() {
		return sensor_display_name;
	}


	public void setSensor_display_name(String sensor_display_name) {
		this.sensor_display_name = sensor_display_name;
	}


	/***
	 * Hàm kiểm tra dữ liệu với mỗi đối tượng Fuel Summary
	 * thompt add 08/01/2019
	 */
	public void validateFuelSummary(){
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
		
		//Mã tài xế không quá 20 kí tự
		if(!Lib.isBlank(this.driver_code)&& this.driver_code.length()>20){
			String name1 = this.driver_code.substring(0, Math.min(this.driver_code.length(), 20));
			this.setDriver_code(name1);
		}
		
		//Tên tài xế không quá 255 kí tự
		if(!Lib.isBlank(this.driver_name)&& this.driver_name.length()>255){
			String name1 = this.driver_name.substring(0, Math.min(this.driver_name.length(), 255));
			this.setDriver_name(name1);
		}
		
		// sensor_display_name không quá 150 kí tự
		if(!Lib.isBlank(this.sensor_display_name)&& this.sensor_display_name.length()>150){
			String name1 = this.sensor_display_name.substring(0, Math.min(this.sensor_display_name.length(), 150));
			this.setSensor_display_name(name1);
		}
						
	}
}
