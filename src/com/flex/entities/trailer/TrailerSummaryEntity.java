package com.flex.entities.trailer;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.flex.dbmanager.attributes.RedisClassNameAliasAttribute;
import com.flex.dbmanager.attributes.RedisIndexAttribute;
import com.flex.dbmanager.attributes.RedisKeyAttribute;
import com.flex.utils.Constants;
import com.flex.utils.Lib;
@RedisIndexAttribute(indexs={"user_name"})
@RedisKeyAttribute(key="key")
@RedisClassNameAliasAttribute(name="Trailer_SM")
public class TrailerSummaryEntity {
	private String db;
	private int data_group;
	private int trailer_id;
	private int vehicle_id;
	private String trailer_name;
	private String vehicle_name;
	private String user_name;
	private String driver_code;
	private String driver_name;
	private String plate;
	private Date date;
	private int move_time;
	private int stop_time;
	private double distance;
	private int associate_count;
	private int unassociate_count;
	private String key;

    // =========== mysql ================
    private List value_list = new LinkedList<TrailerTrackingEntity>();
	/**
	 * @return the trailer_id
	 */
	public int getTrailer_id() {
		return trailer_id;
	}
	/**
	 * @param trailer_id the trailer_id to set
	 */
	public void setTrailer_id(int trailer_id) {
		this.trailer_id = trailer_id;
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
	 * @return the trailer_name
	 */
	public String getTrailer_name() {
		return trailer_name;
	}
	/**
	 * @param trailer_name the trailer_name to set
	 */
	public void setTrailer_name(String trailer_name) {
		this.trailer_name = trailer_name;
	}
	/**
	 * @return the vehicle_name
	 */
	public String getVehicle_name() {
		return vehicle_name;
	}
	/**
	 * @param vehicle_name the vehicle_name to set
	 */
	public void setVehicle_name(String vehicle_name) {
		this.vehicle_name = vehicle_name;
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
	 * @return the driver_name
	 */
	public String getDriver_name() {
		return driver_name;
	}
	/**
	 * @param driver_name the driver_name to set
	 */
	public void setDriver_name(String driver_name) {
		this.driver_name = driver_name;
	}
	/**
	 * @return the plate
	 */
	public String getPlate() {
		return plate;
	}
	/**
	 * @param plate the plate to set
	 */
	public void setPlate(String plate) {
		this.plate = plate;
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
	 * @return the move_time
	 */
	public int getMove_time() {
		return move_time;
	}
	/**
	 * @param move_time the move_time to set
	 */
	public void setMove_time(int move_time) {
		this.move_time = move_time;
	}
	/**
	 * @return the stop_time
	 */
	public int getStop_time() {
		return stop_time;
	}
	/**
	 * @param stop_time the stop_time to set
	 */
	public void setStop_time(int stop_time) {
		this.stop_time = stop_time;
	}
	/**
	 * @return the distance
	 */
	public double getDistance() {
		return distance;
	}
	/**
	 * @param distance the distance to set
	 */
	public void setDistance(double distance) {
		this.distance = distance;
	}
	public String getKey() {
		try{
			key = this.user_name+"_"+this.trailer_id+"_"+Lib.dateToString(this.date,"yyyyMMdd");
			return key;
		}
		catch(Exception ex){
			return "";
		}
	}
	public String getDb() {
		int year = Lib.getYearByDate(this.date);
		int month = Lib.getMonthByDate(this.date);
		this.db =Constants.DB_PREFIX+year+Lib.padLeft(month+"",2); 
		return db;
	}
	public int getData_group() {
		return data_group;
	}
	public void setData_group(int data_group) {
		this.data_group = data_group;
	}
	public List getValue_list() {
		return value_list;
	}
	public void setValue_list(List value_list) {
		this.value_list = value_list;
	}
	/**
	 * @return the associate_count
	 */
	public int getAssociate_count() {
		return associate_count;
	}
	/**
	 * @param associate_count the associate_count to set
	 */
	public void setAssociate_count(int associate_count) {
		this.associate_count = associate_count;
	}
	public int getUnassociate_count() {
		return unassociate_count;
	}
	public void setUnassociate_count(int unassociate_count) {
		this.unassociate_count = unassociate_count;
	}
}
