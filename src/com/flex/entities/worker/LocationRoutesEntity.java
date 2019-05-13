/**
 * @author Trần Gia Minh
 */
package com.flex.entities.worker;

import java.util.Date;

import com.flex.dbmanager.attributes.RedisClassNameAliasAttribute;
import com.flex.dbmanager.attributes.RedisIndexAttribute;
import com.flex.dbmanager.attributes.RedisKeyAttribute;
import com.flex.entities.BaseEntity;
import com.flex.utils.Constants;
import com.flex.utils.Lib;

/**
 * @author Trần Gia Minh
 *
 */
@RedisKeyAttribute(key = "key")
@RedisIndexAttribute(indexs = { "vehicle_id", "user_name", "location_id" })
@RedisClassNameAliasAttribute(name = "Loc_route")
public class LocationRoutesEntity extends BaseEntity {
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
	private long location_id;
	private String location_name;
	private String location_poits;
	private Date from;
	private Date to;
	private int dev_status;
	private double routes_time;
	private double distance;
	private int alerted;
	private int flag_after;
	private double start_latitude;
	private double start_longitude;
	private int data_group;
	private String key;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getVehicle_id() {
		return vehicle_id;
	}

	public void setVehicle_id(int vehicle_id) {
		this.vehicle_id = vehicle_id;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public long getLocation_id() {
		return location_id;
	}

	public void setLocation_id(long location_id) {
		this.location_id = location_id;
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

	public int getDev_status() {
		return dev_status;
	}

	public void setDev_status(int devstatus) {
		this.dev_status = devstatus;
	}

	public double getRoutes_time() {
		return routes_time;
	}

	public void setRoutes_time(double routestime) {
		this.routes_time = routestime;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public int getAlerted() {
		return alerted;
	}

	public void setAlerted(int alerted) {
		this.alerted = alerted;
	}

	public double getStart_latitude() {
		return start_latitude;
	}

	public void setStart_latitude(double startlatitude) {
		this.start_latitude = startlatitude;
	}

	public double getStart_longitude() {
		return start_longitude;
	}

	public void setStart_longitude(double start_longitude) {
		this.start_longitude = start_longitude;
	}

	public int getFlag_after() {
		return flag_after;
	}

	public void setFlag_after(int flag_after) {
		this.flag_after = flag_after;
	}

	public int getData_group() {
		return data_group;
	}

	public void setData_group(int data_group) {
		this.data_group = data_group;
	}

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public String getKey() {
		try {
			key = this.getVehicle_id() + "_" + this.getLocation_id();
			return key;
		} catch (Exception e) {
			// TODO: handle exception
			return "";
		}
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

	public int getIs_device_driver() {
		return is_device_driver;
	}

	public void setIs_device_driver(int is_device_driver) {
		this.is_device_driver = is_device_driver;
	}

	public String getDb() {
		int year = Lib.getYearByDate(this.from);
		int month = Lib.getMonthByDate(this.from);
		db = Constants.DB_PREFIX + year + Lib.padLeft(month + "", 2);
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

	public String getLocation_poits() {
		return location_poits;
	}

	public void setLocation_poits(String location_poits) {
		this.location_poits = location_poits;
	}

	public String getLocation_name() {
		return location_name;
	}

	public void setLocation_name(String location_name) {
		this.location_name = location_name;
	}

//	location_poits	varchar(1000)
//	location_name	varchar(150)
//	imei	varchar(20)
//	driver_code	varchar(20)
//	vehicle_name	varchar(255)
//	driver_name	varchar(255)
//	user_name	varchar(30)
//	created_user	varchar(30)
//	updated_user	varchar(30)
//	plate	varchar(64)
	
	/***
	 * Hàm kiểm tra dữ liệu với mỗi đối tượng Location Route
	 * thompt add 07/01/2019
	 */
	public void validateLocationRoute(){
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
			
		// location_name không quá 150 kí tự
		if(!Lib.isBlank(this.location_name)&& this.location_name.length()>150){
			String name1 = this.location_name.substring(0, Math.min(this.location_name.length(), 150));
			this.setLocation_name(name1);
		}
						
	}
	
	
}
