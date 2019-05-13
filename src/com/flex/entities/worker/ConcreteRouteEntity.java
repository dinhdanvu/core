package com.flex.entities.worker;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.flex.dbmanager.attributes.RedisClassNameAliasAttribute;
import com.flex.dbmanager.attributes.RedisIndexAttribute;
import com.flex.dbmanager.attributes.RedisKeyAttribute;
import com.flex.entities.BaseEntity;
import com.flex.utils.Constants;
import com.flex.utils.Lib;

@RedisClassNameAliasAttribute(name="ConcreteRoute")
@RedisKeyAttribute(key="vehicle_id")
@RedisIndexAttribute(indexs={"user_name"})
public class ConcreteRouteEntity extends BaseEntity{
	private String db;
	private int vehicle_id;
	private String vehicle_name;
	private String plate;
	private String imei;
	private String user_name;
	private Date from;
	private Date to;
	private int discharge_count;
	private int discharge_time;
	private int blend_time;
	private int stop_time;
	private int engine_off_time;
	private double distance;
	private String start_address;
	private double start_latitude;
	private double start_longitude;
	private long start_map_id;
	
	private long discharge_map_id;
	private String discharge_address;
	private Date discharge_time_point;
	private String stop_address;
	private double stop_latitude;
	private double stop_longitude;
	private long stop_map_id;
	private String driver_code;
	private String driver_name;
	private int data_group;
	
	// thompt bổ sung quãng đường bồn trộn, quãng đường bồn dừng
	private double blend_distance;
	private double stop_distance;
	
	public double getBlend_distance() {
		return blend_distance;
	}

	public void setBlend_distance(double blend_distance) {
		this.blend_distance = blend_distance;
	}

	public double getStop_distance() {
		return stop_distance;
	}

	public void setStop_distance(double stop_distance) {
		this.stop_distance = stop_distance;
	}

	
	
	public String getDb() {
		int year = Lib.getYearByDate(this.from);
		int month = Lib.getMonthByDate(this.from);
		db=Constants.DB_PREFIX+year+Lib.padLeft(month+"",2);
		return db;
	}

	public int getVehicle_id() {
		return vehicle_id;
	}

	public void setVehicle_id(int vehicle_id) {
		this.vehicle_id = vehicle_id;
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

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public Date getFrom() {
		return from;
	}

	public void setFrom(Date from) {
		this.from = from;
	}

	public Date getTo() {
		return to;
	}

	public void setTo(Date to) {
		this.to = to;
	}

	public int getDischarge_count() {
		return discharge_count;
	}

	public void setDischarge_count(int discharge_count) {
		this.discharge_count = discharge_count;
	}

	public int getDischarge_time() {
		return discharge_time;
	}

	public void setDischarge_time(int discharge_time) {
		this.discharge_time = discharge_time;
	}

	public int getBlend_time() {
		return blend_time;
	}

	public void setBlend_time(int blend_time) {
		this.blend_time = blend_time;
	}

	public int getStop_time() {
		return stop_time;
	}

	public void setStop_time(int stop_time) {
		this.stop_time = stop_time;
	}

	public int getEngine_off_time() {
		return engine_off_time;
	}

	public void setEngine_off_time(int engine_off_time) {
		this.engine_off_time = engine_off_time;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public String getStart_address() {
		return start_address;
	}

	public void setStart_address(String start_address) {
		this.start_address = start_address;
	}

	public double getStart_latitude() {
		return start_latitude;
	}

	public void setStart_latitude(double start_latitude) {
		this.start_latitude = start_latitude;
	}

	public double getStart_longitude() {
		return start_longitude;
	}

	public void setStart_longitude(double start_longitude) {
		this.start_longitude = start_longitude;
	}

	public long getStart_map_id() {
		return start_map_id;
	}

	public void setStart_map_id(long start_map_id) {
		this.start_map_id = start_map_id;
	}

	public long getDischarge_map_id() {
		return discharge_map_id;
	}

	public void setDischarge_map_id(long discharge_map_id) {
		this.discharge_map_id = discharge_map_id;
	}

	public String getDischarge_address() {
		return discharge_address;
	}

	public void setDischarge_address(String discharge_address) {
		this.discharge_address = discharge_address;
	}

	public Date getDischarge_time_point() {
		return discharge_time_point;
	}

	public void setDischarge_time_point(Date discharge_time_point) {
		this.discharge_time_point = discharge_time_point;
	}

	public String getStop_address() {
		return stop_address;
	}

	public void setStop_address(String stop_address) {
		this.stop_address = stop_address;
	}

	public double getStop_latitude() {
		return stop_latitude;
	}

	public void setStop_latitude(double stop_latitude) {
		this.stop_latitude = stop_latitude;
	}

	public double getStop_longitude() {
		return stop_longitude;
	}

	public void setStop_longitude(double stop_longitude) {
		this.stop_longitude = stop_longitude;
	}

	public long getStop_map_id() {
		return stop_map_id;
	}

	public void setStop_map_id(long stop_map_id) {
		this.stop_map_id = stop_map_id;
	}

	public String getDriver_code() {
		return driver_code;
	}

	public void setDriver_code(String driver_code) {
		this.driver_code = driver_code;
	}

	public String getDriver_name() {
		return driver_name;
	}

	public void setDriver_name(String driver_name) {
		this.driver_name = driver_name;
	}

	public int getData_group() {
		return data_group;
	}

	public void setData_group(int data_group) {
		this.data_group = data_group;
	}

	/***
	 * Hàm kiểm tra dữ liệu với mỗi đối tượng Concrete routes
	 * thompt add 08/01/2019
	 */
	public void validateConcreteRoutes(){
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
		
		// start_address không quá 255 kí tự
		if(!Lib.isBlank(this.start_address)&& this.start_address.length()>255){
			String name1 = this.start_address.substring(0, Math.min(this.start_address.length(), 255));
			this.setStart_address(name1);
		}
		
		// stop_address không quá 255 kí tự
		if(!Lib.isBlank(this.stop_address)&& this.stop_address.length()>255){
			String name1 = this.stop_address.substring(0, Math.min(this.stop_address.length(), 255));
			this.setStop_address(name1);
		}
		
		// discharge_address không quá 255 kí tự	
		if(!Lib.isBlank(this.discharge_address)&& this.discharge_address.length()>255){
			String name1 = this.discharge_address.substring(0, Math.min(this.discharge_address.length(), 255));
			this.setDischarge_address(name1);
		}
	}
}
