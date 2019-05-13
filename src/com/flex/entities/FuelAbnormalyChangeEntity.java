package com.flex.entities;

import java.util.Date;

import com.flex.dbmanager.attributes.RedisClassNameAliasAttribute;
import com.flex.dbmanager.attributes.RedisIndexAttribute;
import com.flex.dbmanager.attributes.RedisKeyAttribute;
import com.flex.utils.Constants;
import com.flex.utils.Lib;

@RedisKeyAttribute(key="key")
@RedisIndexAttribute(indexs={"vehicle_id"})
@RedisClassNameAliasAttribute(name="Fuel_abnormaly_change")
public class FuelAbnormalyChangeEntity extends BaseEntity{
	private String db;
	private int id;
	private int vehicle_id;
	private String vehicle_name;
	private String plate;
	private String imei;
	private String driver_code;
	private String driver_name;
	private int is_device_driver;
	private String user_name;
	private Date time_start;
	private Date time_end;
	private double volume_start_remaining;
	private double volume_end_remaining;
	private double volume_start;
	private double volume_end;
	private double latitude;
	private double longitude;
	private String address;
	private long location_id;
	private String location_poits;
	private int type;
	private int sensor_index;
	private String display_name;
	private String description;
	private int limit_change;
	private int data_group;
	private int is_doubt;
	private boolean done;
	private String key;
	public String getKey() {
		key = vehicle_id+"_"+sensor_index+"_"+type;
		return key;
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
	 * @return the driver_code
	 */
	public String getDriver_code() {
		return driver_code;
	}
	/**
	 * @param driver_code the driver_code to set
	 */
	public void setDriver_code(String driver_code) {
		this.driver_code = driver_code;
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
	 * @return the time_start
	 */
	public Date getTime_start() {
		return time_start;
	}
	/**
	 * @param time_start the time_start to set
	 */
	public void setTime_start(Date time_start) {
		this.time_start = time_start;
	}
	/**
	 * @return the time_end
	 */
	public Date getTime_end() {
		return time_end;
	}
	/**
	 * @param time_end the time_end to set
	 */
	public void setTime_end(Date time_end) {
		this.time_end = time_end;
	}
	/**
	 * @return the volume_start
	 */
	public double getVolume_start() {
		return volume_start;
	}
	/**
	 * @param volume_start the volume_start to set
	 */
	public void setVolume_start(double volume_start) {
		this.volume_start = volume_start;
	}
	/**
	 * @return the volume_end
	 */
	public double getVolume_end() {
		return volume_end;
	}
	/**
	 * @param volume_end the volume_end to set
	 */
	public void setVolume_end(double volume_end) {
		this.volume_end = volume_end;
	}
	/**
	 * @return the latitude
	 */
	public double getLatitude() {
		return latitude;
	}
	/**
	 * @param latitude the latitude to set
	 */
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	/**
	 * @return the longitude
	 */
	public double getLongitude() {
		return longitude;
	}
	/**
	 * @param longitude the longitude to set
	 */
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * @return the type
	 */
	public int getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(int type) {
		this.type = type;
	}
	/**
	 * @return the tank_index
	 */
	public int getSensor_index() {
		return sensor_index;
	}
	/**
	 * @param tank_index the tank_index to set
	 */
	public void setSensor_index(int tank_index) {
		this.sensor_index = tank_index;
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
	public int getLimit_change() {
		return limit_change;
	}
	public void setLimit_change(int limit_change) {
		this.limit_change = limit_change;
	}
	public int getData_group() {
		return data_group;
	}
	public void setData_group(int data_group) {
		this.data_group = data_group;
	}
	public int getIs_doubt() {
		return is_doubt;
	}
	public void setIs_doubt(int is_doubt) {
		this.is_doubt = is_doubt;
	}
	public int getIs_device_driver() {
		return is_device_driver;
	}
	public void setIs_device_driver(int is_device_driver) {
		this.is_device_driver = is_device_driver;
	}
	public String getDb() {
		int year = Lib.getYearByDate(this.time_end);
		int month = Lib.getMonthByDate(this.time_end);
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
	public long getLocation_id() {
		return location_id;
	}
	public void setLocation_id(long location_id) {
		this.location_id = location_id;
	}
	public String getLocation_poits() {
		return location_poits;
	}
	public void setLocation_poits(String location_poits) {
		this.location_poits = location_poits;
	}
	public String getDriver_name() {
		return driver_name;
	}
	public void setDriver_name(String driver_name) {
		this.driver_name = driver_name;
	}
	public String getDisplay_name() {
		return display_name;
	}
	public void setDisplay_name(String display_name) {
		this.display_name = display_name;
	}
	public boolean isDone() {
		return done;
	}
	public void setDone(boolean done) {
		this.done = done;
	}
	public double getVolume_start_remaining() {
		return volume_start_remaining;
	}
	public void setVolume_start_remaining(double volume_start_remaining) {
		this.volume_start_remaining = volume_start_remaining;
	}
	public double getVolume_end_remaining() {
		return volume_end_remaining;
	}
	public void setVolume_end_remaining(double volume_end_remaining) {
		this.volume_end_remaining = volume_end_remaining;
	}
	

	/***
	 * Hàm kiểm tra dữ liệu với mỗi đối tượng FuelAbnormalyChange
	 * thompt add 07/01/2019
	 */
	public void validateFuelAbnormalyChange(){
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
		
		//location_poits không quá 1000 kí tự
		if(!Lib.isBlank(this.location_poits)&& this.location_poits.length()>1000){
			String name1 = this.location_poits.substring(0, Math.min(this.location_poits.length(), 1000));
			this.setLocation_poits(name1);
		}
			
		// sensor_display_name không quá 150 kí tự
		if(!Lib.isBlank(this.display_name)&& this.display_name.length()>150){
			String name1 = this.display_name.substring(0, Math.min(this.display_name.length(), 150));
			this.setDisplay_name(name1);
		}
		
		// address không quá 255 kí tự
		if(!Lib.isBlank(this.address)&& this.address.length()>255){
			String name1 = this.address.substring(0, Math.min(this.address.length(), 255));
			this.setAddress(name1);
		}
				
	}
}
