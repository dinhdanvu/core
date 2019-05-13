package com.flex.entities.worker;

import java.util.Date;

import com.flex.dbmanager.attributes.RedisClassNameAliasAttribute;
import com.flex.dbmanager.attributes.RedisIndexAttribute;
import com.flex.dbmanager.attributes.RedisKeyAttribute;
import com.flex.entities.BaseEntity;
import com.flex.utils.Constants;
import com.flex.utils.Lib;

@RedisKeyAttribute(key="key")
@RedisIndexAttribute(indexs={"vehicle_id","user_name"})
@RedisClassNameAliasAttribute(name="ExceedSp")
public class ExceedspeedEntity extends BaseEntity{
	private String db;
	private String key;
	public int id;
	private int vehicle_id;
	private String vehicle_name;
	private String plate;
	private long location_id;
	private String location_poits;
	public String imei;
	public String user_name;
	public Date from;
	public Date to;
	//Dùng cho redis
//	String str_from = "";
//	String str_to = "";
	
	public double start_latitude;
	public int duration;//thoi gian bat dau tinh tu diem truoc cua thoi diem ghi nhan
	public double start_longitude;
	private String address;
	
	// Điểm kết thúc
	public double end_latitude;
	public double end_longitude;
	private String stop_address; // địa điểm cuối cùng vượt tốc
	
	
	public double distance;
	private String driver_code;
	private String driver_name;
	private int is_device_driver;
	
	public int exceed_speed_type;//0:tu 1~4km, 1:tu 5~10km, 2:10~20km, 3:20~35km, 4:tren 35km
	private double max_speed; // Vận tốc lớn nhật
	private double avg_speed;//van toc trung binh
	private double compared_speed;//van toc so sánh = avg_speed -3
	private double sum_speed;//tong van toc 
	private int count_exceedspeed=0;//so lan vuoc van toc trung binh
	private double limit_speed; // vận tốc tới hạn cho phép = v_limit_max
	
	private boolean update;
	private int data_group;
	

	
	public double getCompared_speed() {
		return compared_speed;
	}
	public void setCompared_speed(double compared_speed) {
		this.compared_speed = compared_speed;
	}
	public String getStop_address() {
		return stop_address;
	}
	public void setStop_address(String stop_address) {
		this.stop_address = stop_address;
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
	public void setImei(String device_imei) {
		this.imei = device_imei;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public double getStart_latitude() {
		return start_latitude;
	}
	public void setStart_latitude(double start_latitude) {
		this.start_latitude = start_latitude;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public double getStart_longitude() {
		return start_longitude;
	}
	public void setStart_longitude(double start_longitude) {
		this.start_longitude = start_longitude;
	}
	public double getEnd_latitude() {
		return end_latitude;
	}
	public void setEnd_latitude(double end_latitude) {
		this.end_latitude = end_latitude;
	}
	public double getEnd_longitude() {
		return end_longitude;
	}
	public void setEnd_longitude(double end_longitude) {
		this.end_longitude = end_longitude;
	}
	public double getDistance() {
		return distance;
	}
	public void setDistance(double distance) {
		this.distance = distance;
	}
	public int getExceedspeed_type() {
		return exceed_speed_type;
	}
	public void setExceedspeed_type(int exceed_speed_type) {
		this.exceed_speed_type = exceed_speed_type;
	}
	public String getDriver_code() {
		return driver_code;
	}
	public void setDriver_code(String drivercode) {
		this.driver_code = drivercode;
	}
	public double getMax_speed() {
		return max_speed;
	}
	public void setMax_speed(double maxSpeed) {
		this.max_speed = maxSpeed;
	}
	public boolean isUpdate() {
		return update;
	}
	public void setUpdate(boolean update) {
		this.update = update;
	}

//	public String getStr_from() {
//		return str_from;
//	}
//	public void setStr_from(String str_from) {
//		this.str_from = str_from;
//	}
//	public String getStr_to() {
//		return str_to;
//	}
//	public void setStr_to(String str_to) {
//		this.str_to = str_to;
//	}
	public double getLimit_speed() {
		return limit_speed;
	}
	public void setLimit_speed(double limit_speed) {
		this.limit_speed = limit_speed;
	}
	public double getAvg_speed() {
		return avg_speed;
	}
	public void setAvg_speed(double avg_speed) {
		this.avg_speed = avg_speed;
	}
	public double getSum_speed() {
		return sum_speed;
	}
	public void setSum_speed(double sum_speed) {
		this.sum_speed = sum_speed;
	}
	public int getCount_exceedspeed() {
		return count_exceedspeed;
	}
	public void setCount_exceedspeed(int count_exceedspeed) {
		this.count_exceedspeed = count_exceedspeed;
	}
	public String getKey() {
		try{
			String key = "";
			String code = Lib.isBlank(this.driver_code)?"":this.driver_code;
			key = code+"_"+this.vehicle_id;
			return key;
		}catch (Exception e) {
			// TODO: handle exception
			return "";
		}
	}
	public int getVehicle_id() {
		return vehicle_id;
	}
	public void setVehicle_id(int vehicleId) {
		this.vehicle_id = vehicleId;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
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
	

	/***
	 * Hàm kiểm tra dữ liệu với mỗi đối tượng Exceed speed
	 * thompt add 05/01/2019
	 */
	public void validateExceedSpeed(){
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
		//Tên tài xế không quá 150 kí tự
		if(!Lib.isBlank(this.driver_name)&& this.driver_name.length()>150){
			String name1 = this.driver_name.substring(0, Math.min(this.driver_name.length(), 150));
			this.setDriver_name(name1);
		}
		
		//location_poits không quá 1000 kí tự
		if(!Lib.isBlank(this.location_poits)&& this.location_poits.length()>1000){
			String name1 = this.location_poits.substring(0, Math.min(this.location_poits.length(), 1000));
			this.setLocation_poits(name1);
		}
		//stop_address không quá 200 kí tự
		if(!Lib.isBlank(this.stop_address)&& this.stop_address.length()>200){
			String name1 = this.stop_address.substring(0, Math.min(this.stop_address.length(), 200));
			this.setStop_address(name1);
		}
			
		// address không quá 200 kí tự
		if(!Lib.isBlank(this.address)&& this.address.length()>200){
			String name1 = this.address.substring(0, Math.min(this.address.length(), 200));
			this.setAddress(name1);
		}
			
	}

}
