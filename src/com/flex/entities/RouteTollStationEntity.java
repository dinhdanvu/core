package com.flex.entities;

import java.util.Date;
import com.flex.dbmanager.attributes.RedisKeyAttribute;
import com.flex.utils.Constants;
import com.flex.utils.Lib;

@RedisKeyAttribute(key="id")
public class RouteTollStationEntity extends BaseEntity{
	private String db;
	private int id;
	private int vehicle_id;
	private String vehicle_name;
	private String plate;
	private String imei;
	private String user_name;
	private String driver_code;
	private String driver_name;
	private int is_device_driver;
	private int fee_station_id;
	private String fee_station_poits;
	private String fee_station_name;
	private Date trk_time;
	private int money;
	
	private double lat1;
	private double lon1;
	private double lat2;
	private double lon2;
	private String address1;
	private String address2;
	private int data_group;
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
	 * @return the fee_station_id
	 */
	public int getFee_station_id() {
		return fee_station_id;
	}
	/**
	 * @param fee_station_id the fee_station_id to set
	 */
	public void setFee_station_id(int fee_station_id) {
		this.fee_station_id = fee_station_id;
	}
	/**
	 * @return the trk_time
	 */
	public Date getTrk_time() {
		return trk_time;
	}
	/**
	 * @param trk_time the trk_time to set
	 */
	public void setTrk_time(Date trk_time) {
		this.trk_time = trk_time;
	}
	/**
	 * @return the lat1
	 */
	public double getLat1() {
		return lat1;
	}
	/**
	 * @param lat1 the lat1 to set
	 */
	public void setLat1(double lat1) {
		this.lat1 = lat1;
	}
	/**
	 * @return the lon1
	 */
	public double getLon1() {
		return lon1;
	}
	/**
	 * @param lon1 the lon1 to set
	 */
	public void setLon1(double lon1) {
		this.lon1 = lon1;
	}
	/**
	 * @return the lat2
	 */
	public double getLat2() {
		return lat2;
	}
	/**
	 * @param lat2 the lat2 to set
	 */
	public void setLat2(double lat2) {
		this.lat2 = lat2;
	}
	/**
	 * @return the lon2
	 */
	public double getLon2() {
		return lon2;
	}
	/**
	 * @param lon2 the lon2 to set
	 */
	public void setLon2(double lon2) {
		this.lon2 = lon2;
	}
	/**
	 * @return the address1
	 */
	public String getAddress1() {
		return address1;
	}
	/**
	 * @param address1 the address1 to set
	 */
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	/**
	 * @return the address2
	 */
	public String getAddress2() {
		return address2;
	}
	/**
	 * @param address2 the address2 to set
	 */
	public void setAddress2(String address2) {
		this.address2 = address2;
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
	public String getFee_station_name() {
		return fee_station_name;
	}
	public void setFee_station_name(String fee_station_name) {
		this.fee_station_name = fee_station_name;
	}
	public int getMoney() {
		return money;
	}
	public void setMoney(int price) {
		this.money = price;
	}
	public String getDb() {
		int year = Lib.getYearByDate(this.trk_time);
		int month = Lib.getMonthByDate(this.trk_time);
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
	public String getFee_station_poits() {
		return fee_station_poits;
	}
	public void setFee_station_poits(String fee_station_poits) {
		this.fee_station_poits = fee_station_poits;
	}
	public String getDriver_name() {
		return driver_name;
	}
	public void setDriver_name(String driver_name) {
		this.driver_name = driver_name;
	}
	

}
