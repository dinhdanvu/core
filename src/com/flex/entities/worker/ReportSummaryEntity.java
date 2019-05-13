package com.flex.entities.worker;

import java.util.Date;

import com.flex.dbmanager.attributes.RedisClassNameAliasAttribute;
import com.flex.dbmanager.attributes.RedisIndexAttribute;
import com.flex.dbmanager.attributes.RedisKeyAttribute;
import com.flex.entities.BaseEntity;
import com.flex.utils.Constants;
import com.flex.utils.Lib;

@RedisClassNameAliasAttribute(name="SM")
@RedisKeyAttribute(key="key")
@RedisIndexAttribute(indexs={"user_name","vehicle_id","driver_code"})
public class ReportSummaryEntity extends BaseEntity {
	private String db;
	private String key;
	private int id;
	private int vehicle_id;
	private String vehicle_name;
	private String plate;
	private Date date;
	private String user_name="System";
	private String driver_code;
	private String driver_name;
	private int is_device_driver;
	private int driver_change_count = 0;
	private int driver_card_on_time = 0;
	private String imei;
	private int move_time = 0;
	private int stop_time = 0;
	private double distance = 0;
	private int move_count = 0;
	private int stop_count = 0;
	private int over_speed_count = 0;
	private int stop_out_location_count;
	private int stop_out_location_time;
	
	private int no_gps_count = 0;
	/**
	 * thoi gian mat GPS
	 */
	private int no_gps_time;

	/**
	 * Đếm số lần mở cửa
	 */
	private int open_door_num;//
	/**
	 * Số lần dừng tắt máy
	 */
	private int engine_off_num;//
	
	/**
	 * van toc lon nhat trong ngay
	 */
	private double max_speed = 0;
	/**
	 * thoi gian bat dau di chuyen dau ngay
	 */
	private Date move_first;
	/**
	 * thoi gian dung lai cuoi ngay
	 */
	private Date move_end;

	private double stopped_tank_time;
	private double discharged_tank_time;
	private double mixed_tank_time;
	private double stop_engine_on_time = 0;
	private int stop_engine_on_count = 0;
	private int stop_in_location_count = 0;
	private int stop_in_location_time = 0;
	private int engine_on_count = 0;
	private int engine_on_time = 0;
	
	private int toll_in_count = 0;
	private int toll_in_money = 0;
	private int location_out_count = 0;
	private int location_in_count = 0;
	private int location_in_time = 0;
	
	private int power_lost_count = 0;
	private int power_lost_time = 0;
	/**
	 * thoi gian mat GPRS
	 */
	private int no_gprs_time = 0;
	private int no_gprs_count = 0;
	private int routes_count = 0;
	
	private int parking_count = 0;
	private int parking_time = 0;
	/**
	 * van toc trung binh cua ngay
	 */
	private double avg_speed;
	private String account_msg;
	/**
	 * nhien lieu ho tro trong ngay
	 */
	private double fuel_support_time;
	private String rp_time = "";
	private int record_count;
	
	//thompt bổ sung tín thời gian và số lần mở điều hòa, thùng hàng
	private int conditional_on_count = 0;
	private int conditional_on_time = 0;
	private int container_on_count = 0;
	private int container_on_time = 0;
	
	private int data_group = 0;

	public String getRp_time() {
		return rp_time;
	}

	public void setRp_time(String rptime) {
		rp_time = rptime;
	}

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public int getData_group() {
		return data_group;
	}

	public void setData_group(int data_group) {
		this.data_group = data_group;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getDriver_code() {
		return driver_code;
	}

	public void setDriver_code(String driver_code) {
		this.driver_code = driver_code;
	}
	
	public int getMove_time() {
		return move_time;
	}

	public void setMove_time(int movetime) {
		this.move_time = movetime;
	}

	public int getStop_time() {
		return stop_time;
	}

	public void setStop_time(int stoptime) {
		this.stop_time = stoptime;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public int getStop_count() {
		return stop_count;
	}

	public void setStop_count(int stopcount) {
		this.stop_count = stopcount;
	}

	public int getOver_speed_count() {
		return over_speed_count;
	}

	public void setOver_speed_count(int overspeedcount) {
		this.over_speed_count = overspeedcount;
	}

	public int getNo_gps_time() {
		return no_gps_time;
	}

	public void setNo_gps_time(int nogpstime) {
		this.no_gps_time = nogpstime;
	}

	public int getNo_gps_count() {
		return no_gps_count;
	}

	public void setNo_gps_count(int no_gps_count) {
		this.no_gps_count = no_gps_count;
	}
	

	public int getOpen_door_num() {
		return open_door_num;
	}

	public void setOpen_door_num(int opendoornum) {
		this.open_door_num = opendoornum;
	}

	public int getEngine_off_num() {
		return engine_off_num;
	}

	public void setEngine_off_num(int engineoffnum) {
		this.engine_off_num = engineoffnum;
	}


	public double getMax_speed() {
		return max_speed;
	}

	public void setMax_speed(double maxspeed) {
		this.max_speed = maxspeed;
	}

	public Date getMove_first() {
		return move_first;
	}

	public void setMove_first(Date movefirst) {
		this.move_first = movefirst;
	}

	public Date getMove_end() {
		return move_end;
	}

	public void setMove_end(Date stopend) {
		this.move_end = stopend;
	}

	public double getStopped_tank_time() {
		return stopped_tank_time;
	}

	public void setStopped_tank_time(double stoppedtank_time) {
		this.stopped_tank_time = stoppedtank_time;
	}

	public double getDischarged_tank_time() {
		return discharged_tank_time;
	}

	public void setDischarged_tank_time(double dischargedtank_time) {
		this.discharged_tank_time = dischargedtank_time;
	}

	public double getMixed_tank_time() {
		return mixed_tank_time;
	}

	public void setMixed_tank_time(double mixedtank_time) {
		this.mixed_tank_time = mixedtank_time;
	}

	public double getStop_engine_on_time() {
		return stop_engine_on_time;
	}

	public void setStop_engine_on_time(double stop_engineon_time) {
		this.stop_engine_on_time = stop_engineon_time;
	}

	public int getStop_engine_on_count() {
		return stop_engine_on_count;
	}

	public void setStop_engine_on_count(int stop_engineon_count) {
		this.stop_engine_on_count = stop_engineon_count;
	}

	public double getAvg_speed() {
		return avg_speed;
	}

	public void setAvg_speed(double avgspeed) {
		this.avg_speed = avgspeed;
	}

	public String getAccount_msg() {
		return account_msg;
	}

	public void setAccount_msg(String account_msg) {
		this.account_msg = account_msg;
	}

	public double getFuel_support_time() {
		return fuel_support_time;
	}

	public void setFuel_support_time(double fuel_support_time) {
		this.fuel_support_time = fuel_support_time;
	}



	public int getParking_count() {
		return parking_count;
	}

	public void setParking_count(int parking_count) {
		this.parking_count = parking_count;
	}

	public int getParking_time() {
		return parking_time;
	}

	public void setParking_time(int parking_time) {
		this.parking_time = parking_time;
	}

	public int getEngine_on_count() {
		return engine_on_count;
	}

	public void setEngine_on_count(int engine_on_count) {
		this.engine_on_count = engine_on_count;
	}

	public int getEngine_on_time() {
		return engine_on_time;
	}

	public void setEngine_on_time(int engine_on_time) {
		this.engine_on_time = engine_on_time;
	}

	public int getDriver_change_count() {
		return driver_change_count;
	}

	public void setDriver_change_count(int driver_change_count) {
		this.driver_change_count = driver_change_count;
	}
	
	public int getDriver_card_on_time() {
		return driver_card_on_time;
	}

	public void setDriver_card_on_time(int driver_card_on_time) {
		this.driver_card_on_time = driver_card_on_time;
	}

	public int getPower_lost_count() {
		return power_lost_count;
	}

	public void setPower_lost_count(int power_lost_count) {
		this.power_lost_count = power_lost_count;
	}

	public int getPower_lost_time() {
		return power_lost_time;
	}

	public void setPower_lost_time(int power_lost_time) {
		this.power_lost_time = power_lost_time;
	}

	public int getNo_gprs_count() {
		return no_gprs_count;
	}

	public void setNo_gprs_count(int no_gprs_count) {
		this.no_gprs_count = no_gprs_count;
	}

	public int getNo_gprs_time() {
		return no_gprs_time;
	}

	public void setNo_gprs_time(int no_gprs_time) {
		this.no_gprs_time = no_gprs_time;
	}


	public int getRoutes_count() {
		return routes_count;
	}

	public void setRoutes_count(int routes_count) {
		this.routes_count = routes_count;
	}

	public int getStop_in_location_count() {
		return stop_in_location_count;
	}

	public void setStop_in_location_count(int stop_in_location_count) {
		this.stop_in_location_count = stop_in_location_count;
	}

	public int getStop_in_location_time() {
		return stop_in_location_time;
	}

	public void setStop_in_location_time(int stop_in_location_time) {
		this.stop_in_location_time = stop_in_location_time;
	}

	public int getToll_in_count() {
		return toll_in_count;
	}

	public void setToll_in_count(int toll_in_count) {
		this.toll_in_count = toll_in_count;
	}

	public int getToll_in_money() {
		return toll_in_money;
	}

	public void setToll_in_money(int toll_in_money) {
		this.toll_in_money = toll_in_money;
	}

	public int getLocation_out_count() {
		return location_out_count;
	}

	public void setLocation_out_count(int location_out_count) {
		this.location_out_count = location_out_count;
	}

	public int getLocation_in_count() {
		return location_in_count;
	}

	public void setLocation_in_count(int location_in_count) {
		this.location_in_count = location_in_count;
	}

	public int getLocation_in_time() {
		return location_in_time;
	}

	public void setLocation_in_time(int location_in_time) {
		this.location_in_time = location_in_time;
	}


	public int getVehicle_id() {
		return vehicle_id;
	}

	public void setVehicle_id(int vehicleId) {
		this.vehicle_id = vehicleId;
	}



	public Date getStopend() {
		return move_end;
	}

	public void setStopend(Date stopend) {
		this.move_end = stopend;
	}

	public int getMove_count() {
		return move_count;
	}


	public void setMove_count(int move_count) {
		this.move_count = move_count;
	}

	public String getKey() {
		try{
			String code = Lib.isBlank(this.driver_code)?"":this.driver_code;
			key = this.user_name+"_"+this.vehicle_id+"_"+code+"_"+Lib.dateToString(this.date,"yyyyMMdd");
			return key;
		}
		catch(Exception ex){
			return "";
		}
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

	public int getStop_out_location_count() {
		return stop_out_location_count;
	}

	public void setStop_out_location_count(int stop_out_location_count) {
		this.stop_out_location_count = stop_out_location_count;
	}

	public int getStop_out_location_time() {
		return stop_out_location_time;
	}

	public void setStop_out_location_time(int stop_out_location_time) {
		this.stop_out_location_time = stop_out_location_time;
	}

	public int getRecord_count() {
		return record_count;
	}

	public void setRecord_count(int record_count) {
		this.record_count = record_count;
	}
	

	public int getConditional_on_count() {
		return conditional_on_count;
	}

	public void setConditional_on_count(int air_conditional_on_count_on_day) {
		this.conditional_on_count = air_conditional_on_count_on_day;
	}

	public int getConditional_on_time() {
		return conditional_on_time;
	}

	public void setConditional_on_time(int air_conditional_on_time_on_day) {
		this.conditional_on_time = air_conditional_on_time_on_day;
	}

	public int getContainer_on_count() {
		return container_on_count;
	}

	public void setContainer_on_count(int container_on_count_on_day) {
		this.container_on_count = container_on_count_on_day;
	}

	public int getContainer_on_time() {
		return container_on_time;
	}

	public void setContainer_on_time(int container_on_time_on_day) {
		this.container_on_time = container_on_time_on_day;
	}
	
	/**
	 * Hàm hiển thị thông tin tổng hợp về phương tiện trên màn hình console.
	 * thompt add
	 */
	public String toString(){
		String sTracking = new String();
		sTracking +="\n THONG TIN TONG HOP PHUONG TIEN";
		sTracking +="\n vehicle_id : " + this.vehicle_id ;
		sTracking +="\n vehicle_name : " + this.vehicle_name ;
		sTracking +="\n IMEI : " + this.imei ;
		
		sTracking +="\n So lan thay doi tai xe: " + this.driver_change_count ;
		sTracking +="\n So ban tin gui ve: " + this.record_count ;
		sTracking +="\n Quang duong di chuyen trong ngay: " + this.distance ;
		sTracking +="\n Van toc lơn nhat: " + this.max_speed;
		sTracking +="\n Ngay TONG HOP: " + this.date.toString() ;

		return  sTracking;	
	}
	
	
	/***
	 * Hàm kiểm tra dữ liệu với mỗi đối tượng Summary_info
	 * thompt add 03/03/2018
	 */
	public void validateSummaryInfo(){
	
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
				
			// Quãng đường di chuyển không lớn hơn 9999,999km
			double maxValue = (Math.pow(10,7)-1)/Math.pow(10,3);
			
			if(this.distance < 0){
				this.setDistance(0);
			}
			if(this.distance > maxValue){
				this.setDistance(maxValue);
			}
						
	// Thời gian di chuyển trong ngày ko âm và ko quá 86000 giây (24h)
			if(this.move_time > 86000){
				this.setMove_time(86000);
			}
	
	// Thời gian di dừng trong ngày ko âm và ko quá 86000 giây (24h)
			if(this.stop_time > 86000){
				this.setStop_time(86000);
			}
			
			//move_count
			if(this.move_count<=0){
				this.setMove_count(0);
			}
			//stop_count
			if(this.stop_count<=0){
				this.setStop_count(0);
			}
			//parking_count
			if(this.parking_count<=0){
				this.setParking_count(0);
			}
			//stop_in_location_count
			if(this.stop_in_location_count<=0){
				this.stop_in_location_count=0;
			}
			//stop_out_location_count
			if(this.stop_out_location_count<=0){
				this.setStop_out_location_count(0);
			}
		  //parking_time
			if(this.parking_time<=0){
				this.setParking_time(0);
			}
			//driver_change_count
			if(this.driver_change_count<=0){
				this.setDriver_change_count(0);
			}		
			
			//stop_in_location_time
			if(this.stop_in_location_time > 86000){
				this.setStop_in_location_time(86000);
			}

			//stop_out_location_time
			if(this.stop_out_location_time > 86000){
				this.setStop_out_location_time(86000);
			}
				
			//driver_card_on_time
			if(this.driver_card_on_time > 86000){
				this.setDriver_card_on_time(86000);
			}
			
			//no_gps_time
			if(this.no_gps_time > 86000){
				this.setNo_gps_time(86000);
			}
			
			//no_gprs_time
			if(this.no_gprs_time > 86000){
				this.setNo_gprs_time(86000);
			}

	}
}
