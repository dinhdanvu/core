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

@RedisClassNameAliasAttribute(name="Route")
@RedisKeyAttribute(key="key")
@RedisIndexAttribute(indexs={"user_name","imei","idx_user_route_type"})
public class RouteEntity extends BaseEntity{
	private String db;
	private int id;
	private int vehicle_id;
	private String vehicle_name;
	private String plate;
	private String imei;
	private String user_name="System";
	/**
	 * loai route 0:chay,1:dung,2:mat gps,3:mat gprs,4:lai xe lien tuc
	 */
	private int route_type;
	private Date from;
	private Date to;
	//Dùng cho redis
//	String str_from = "";
//	String str_to = "";
	/**
	 * khoang cach
	 */
	private double distance;
	private String start_address;
	private double start_latitude;
	private double start_longitude;
	private long start_map_id;
	private String stop_address;
	private double stop_latitude;
	private double stop_longitude;
	private long stop_map_id;
	private String key;
	private String idx_user_route_type;
	/**
	 * nhien lieu tieu hao
	 */
	private double fuel_signal;
	private int alerted;
	/**
	 * license lai xe
	 */
	private String driver_code;
	private String driver_name;
	private int is_device_driver;
	
	private boolean is_update = false;
	
	private int data_group = 0;
	private List value_list = new ArrayList();
	
	public boolean isIs_update() {
		return is_update;
	}
	public void setIs_update(boolean is_update) {
		this.is_update = is_update;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public int getRoute_type() {
		return route_type;
	}
	public void setRoute_type(int route_type) {
		this.route_type = route_type;
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
	public String getDriver_code() {
		return driver_code;
	}
	public void setDriver_code(String driver_code) {
		this.driver_code = driver_code;
	}
	/**
	 * @return the data_group
	 */
	public int getData_group() {
		return data_group;
	}
	/**
	 * @param data_group the data_group to set
	 */
	public void setData_group(int data_group) {
		this.data_group = data_group;
	}

	public int getVehicle_id() {
		return vehicle_id;
	}
	public void setVehicle_id(int vehicle_id) {
		this.vehicle_id = vehicle_id;
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
	public String getIdx_user_route_type() {
		try {
			idx_user_route_type = this.user_name + "_" + this.route_type;
			return idx_user_route_type;
		} catch (Exception ex) {
			
		}
		return null;
	}
	public void setIdx_user_route_type(String idx_user_route_type) {
		this.idx_user_route_type = idx_user_route_type;
	}
	public double getFuel_signal() {
		return fuel_signal;
	}
	public void setFuel_signal(double fual_signal) {
		this.fuel_signal = fual_signal;
	}
//	public void setStr_from(String str_from) {
//		this.str_from = str_from;
//	}
//	public void setStr_to(String str_to) {
//		this.str_to = str_to;
//	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getKey() {
		try {//key: routetype_imei_vehicleid
			key = this.route_type+"_"+this.vehicle_id;
			if(this.route_type==4){
				String code = Lib.isBlank(this.driver_code)?"":this.driver_code;
				key += "_"+code;
			}
			return key;
		} catch (Exception ex) {
		}
		return null;
	}
	public int getIs_device_driver() {
		return is_device_driver;
	}
	public void setIs_device_driver(int is_device_driver) {
		this.is_device_driver = is_device_driver;
	}
	public List getValue_list() {
		return value_list;
	}
	public void setValue_list(List value_list) {
		this.value_list = value_list;
	}
	public String getDb() {
		int year = Lib.getYearByDate(this.from);
		int month = Lib.getMonthByDate(this.from);
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
	
	/***
	 * Hàm kiểm tra dữ liệu với mỗi đối tượng tracking
	 * thompt add 05/01/2019
	 */
	public void validateRoutes(){
		// Tên không quá 150 kí tự
		if(!Lib.isBlank(this.vehicle_name)&& this.vehicle_name.length()>150){
			String name1 = this.vehicle_name.substring(0, Math.min(this.vehicle_name.length(), 150));
			this.setVehicle_name(name1);
		}
		
		// Biển số không quá 150 kí tự
		if(!Lib.isBlank(this.plate)&& this.plate.length()>150){
			String name1 = this.plate.substring(0, Math.min(this.plate.length(), 150));
			this.setPlate(name1);
		}
		
		// Imei không quá 20 kí tự
		if(!Lib.isBlank(this.imei)&& this.imei.length()>20){
			String name1 = this.imei.substring(0, Math.min(this.imei.length(), 20));
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
		
		//stop_address không quá 200 kí tự
		if(!Lib.isBlank(this.stop_address)&& this.stop_address.length()>255){
			String name1 = this.stop_address.substring(0, Math.min(this.stop_address.length(), 255));
			this.setStop_address(name1);
		}
			
		// address không quá 200 kí tự
		if(!Lib.isBlank(this.start_address)&& this.start_address.length()>255){
			String name1 = this.start_address.substring(0, Math.min(this.start_address.length(), 255));
			this.setStart_address(name1);
		}
		
		// Quãng đường di chuyển không lớn hơn 9999,999km
		double maxValue = (Math.pow(10,7)-1)/Math.pow(10,3);
		
		if(this.distance < 0){
			this.setDistance(0);
		}
		if(this.distance > maxValue){
			this.setDistance(maxValue);
		}
		
		
	}
}
