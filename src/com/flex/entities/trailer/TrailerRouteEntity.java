package com.flex.entities.trailer;

import com.flex.dbmanager.attributes.RedisClassNameAliasAttribute;
import com.flex.dbmanager.attributes.RedisIndexAttribute;
import com.flex.dbmanager.attributes.RedisKeyAttribute;
import com.flex.utils.Constants;
import com.flex.utils.Lib;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@RedisKeyAttribute(key = "trailer_id")
@RedisIndexAttribute(indexs={"user_name"})
@RedisClassNameAliasAttribute(name="Trailer_Route")
public class TrailerRouteEntity {
	private String db;
	private int data_group;
	private int trailer_id;
	private int vehicle_id;
	private String trailer_name;
	private String vehicle_name;
	private String user_name;
	private String plate;
	private Date from;
	private Date to;
	private int run_time;
	private int stop_time;
	private double distance;
	private String start_driver_code;
	private String start_driver_name;
	private String start_address;
	private double start_latitude;
	private double start_longitude;
	private long start_map_id;
	private String end_address;
	private double end_latitude;
	private double end_longitude;
	private long end_map_id;
	private String end_driver_code;
	private String end_driver_name;
	

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
	 * @return the from
	 */
	public Date getFrom() {
		return from;
	}
	/**
	 * @param from the from to set
	 */
	public void setFrom(Date from) {
		this.from = from;
	}
	/**
	 * @return the to
	 */
	public Date getTo() {
		return to;
	}
	/**
	 * @param to the to to set
	 */
	public void setTo(Date to) {
		this.to = to;
	}
	/**
	 * @return the run_time
	 */
	public int getRun_time() {
		return run_time;
	}
	/**
	 * @param run_time the run_time to set
	 */
	public void setRun_time(int run_time) {
		this.run_time = run_time;
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
	/**
	 * @return the start_driver_code
	 */
	public String getStart_driver_code() {
		return start_driver_code;
	}
	/**
	 * @param start_driver_code the start_driver_code to set
	 */
	public void setStart_driver_code(String start_driver_code) {
		this.start_driver_code = start_driver_code;
	}
	/**
	 * @return the start_driver_name
	 */
	public String getStart_driver_name() {
		return start_driver_name;
	}
	/**
	 * @param start_driver_name the start_driver_name to set
	 */
	public void setStart_driver_name(String start_driver_name) {
		this.start_driver_name = start_driver_name;
	}
	/**
	 * @return the start_address
	 */
	public String getStart_address() {
		return start_address;
	}
	/**
	 * @param start_address the start_address to set
	 */
	public void setStart_address(String start_address) {
		this.start_address = start_address;
	}
	/**
	 * @return the start_latitude
	 */
	public double getStart_latitude() {
		return start_latitude;
	}
	/**
	 * @param start_latitude the start_latitude to set
	 */
	public void setStart_latitude(double start_latitude) {
		this.start_latitude = start_latitude;
	}
	/**
	 * @return the start_longitude
	 */
	public double getStart_longitude() {
		return start_longitude;
	}
	/**
	 * @param start_longitude the start_longitude to set
	 */
	public void setStart_longitude(double start_longitude) {
		this.start_longitude = start_longitude;
	}
	/**
	 * @return the start_map_id
	 */
	public long getStart_map_id() {
		return start_map_id;
	}
	/**
	 * @param start_map_id the start_map_id to set
	 */
	public void setStart_map_id(long start_map_id) {
		this.start_map_id = start_map_id;
	}
	/**
	 * @return the end_address
	 */
	public String getEnd_address() {
		return end_address;
	}
	/**
	 * @param end_address the end_address to set
	 */
	public void setEnd_address(String end_address) {
		this.end_address = end_address;
	}
	/**
	 * @return the end_latitude
	 */
	public double getEnd_latitude() {
		return end_latitude;
	}
	/**
	 * @param end_latitude the end_latitude to set
	 */
	public void setEnd_latitude(double end_latitude) {
		this.end_latitude = end_latitude;
	}
	/**
	 * @return the end_longitude
	 */
	public double getEnd_longitude() {
		return end_longitude;
	}
	/**
	 * @param end_longitude the end_longitude to set
	 */
	public void setEnd_longitude(double end_longitude) {
		this.end_longitude = end_longitude;
	}
	/**
	 * @return the end_map_id
	 */
	public long getEnd_map_id() {
		return end_map_id;
	}
	/**
	 * @param end_map_id the end_map_id to set
	 */
	public void setEnd_map_id(long end_map_id) {
		this.end_map_id = end_map_id;
	}
	/**
	 * @return the end_driver_code
	 */
	public String getEnd_driver_code() {
		return end_driver_code;
	}
	/**
	 * @param end_driver_code the end_driver_code to set
	 */
	public void setEnd_driver_code(String end_driver_code) {
		this.end_driver_code = end_driver_code;
	}
	/**
	 * @return the end_driver_name
	 */
	public String getEnd_driver_name() {
		return end_driver_name;
	}
	/**
	 * @param end_driver_name the end_driver_name to set
	 */
	public void setEnd_driver_name(String end_driver_name) {
		this.end_driver_name = end_driver_name;
	}
	public int getData_group() {
		return data_group;
	}
	public void setData_group(int data_group) {
		this.data_group = data_group;
	}
	public String getDb() {
		int year = Lib.getYearByDate(this.from);
		int month = Lib.getMonthByDate(this.from);
		this.db =Constants.DB_PREFIX+year+Lib.padLeft(month+"",2); 
		return db;
	}
	public List getValue_list() {
		return value_list;
	}
	public void setValue_list(List value_list) {
		this.value_list = value_list;
	}
}
