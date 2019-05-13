package com.flex.entities.worker;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.flex.dbmanager.attributes.RedisClassNameAliasAttribute;
import com.flex.dbmanager.attributes.RedisIgnoreKeyAttributes;
import com.flex.dbmanager.attributes.RedisIndexAttribute;
import com.flex.dbmanager.attributes.RedisKeyAttribute;
import com.flex.entities.BaseEntity;
import com.flex.entities.SensorDataEntity;
import com.flex.utils.Constants;
import com.flex.utils.Lib;

@RedisIndexAttribute(indexs={"user_name","dev_status"})
@RedisKeyAttribute(key="vehicle_id")
@RedisIgnoreKeyAttributes(ignoreKey={"arr_basic_sensor_value","sensorDataMap"})
@RedisClassNameAliasAttribute(name="Trk")

public class TrackingEntity implements Cloneable
//implements Cloneable
{
	private String db;
//	public HashMap<String, SensorDataEntity> sensorDataMap = new HashMap<String, SensorDataEntity>();
	private List value_list = new ArrayList();
	private int id;
	private int device_type;
	/**
	 * id phuong tien xu dung thiet bi
	 */
	private int vehicle_id;
	private String vehicle_name;
	private String plate;
	private Date expired;
	/**
	 * imei thiet bi
	 */
	private String imei;
	private String user_name;
	private int data_group;
	private Date trk_time;
	private double latitude;
	private double longitude;
	private int msg_post_kind;
	/**
	 * do cao
	 */
	private int altitude;
	/**
	 * so luong song ve tin nhan duoc 
	 */
	private int satellites;
	//động cơ: 0: tắt; 1: nổ; 2: Không xác định;
	private int engine;

	private int door_is_open;
	private int conditional;
	private HashMap<String, SensorDataEntity> map_sensor_data;
//	private HashMap<String, testE> map_list_fuel_level;
//	private HashMap<String, SensorDataEntity> map_fuel_dfm;
	
	//thanhle gán vì không muốn trạng thái bắt đầu lại là dừng xe
	//vì nó sẽ tạo tuyến dừng
	private int status = 0;
	private int dev_status = -1;
	//trang thai truoc trachking
	private int pre_dev_status=-1;
	
	/**
	 * tin hieu be tong
	 */
	private int concrete_status;
	/**
	 * toc do do thiet bi tu tinh toan
	 */
	private double velocity;
	/**
	 * toc do cuoi cung, co the la noi suy hoac lay toc do thiet bi
	 */
	private double speed;
	private double speed_calc;//van toc noi suy
	private double speed_gps;
	private double speed_pulse;
	private boolean event;

	/**
	 * khoang cach cua tung diem
	 */
	private double distance;


	
	private double exc_distance;//khoang cach vuot toc do
	private double exc_max_speed;//van toc lon nhat vuot toc do
	private int direction;
	private int battery_level;
	private int use_battery=1;
	private String address;
	private double mileage;
	private double mileage_calc;
	private double mileage_gps;
	private double mileage_sensor;
	private String driver_code = "";
	private String driver_name = "";
	private String driver_phone = "";
	private Date driver_expired;
	private int is_device_driver;
//	private int input;
	private int use_mileage;
	private int use_dev_speed;
	private double distance_param;
	private String icon;
//	private int runing=-1;
	private int duration = 0;
	/**
	 * theo cau hinh canh bao
	 */
	private int exc_alarm_duration;//thoi gian vuot toc do
	private int drive_alarm_duration;
	private int loc_in_out_alarm_duration;
	private double continue_route_distance = 0;
	private int continue_route_count;
	/**
	 * thoi gian chay trong chu ky 24h
	 */
	private int one_day_running_time = 0;
	//Tổng thời gian dừng trong ngày
	/**
	 * thoi gian dung do trong chu ky 24h
	 */
	private int one_day_stop_time = 0;
	//Thời gian dừng cho sự kiện dừng hiện tại nếu có
//	private int stop_time = 0;
	/**
	 * khoang cach cong don cua trang thai
	 * duoc tinh khi trang thai bat dau.
	 */
	private double route_distance;
	/**
	 * thoi gian cong don cua trang thai hien gio
	 */
	private int route_time;
	/**
	 * thoi gian ra/vung vung
	 */
	private int location_route_time;
	
	private double v_limit_max = 0;
	private double vlimit_min;
	/**
	 * thoi gian bat dau cua chu ki lai xe 24h
	 * duoc ghi nhan khi tai bat dau 1 ca lam viec moi, dieu kien la truoc do chua lai xe, hoac da duoc nghi voi thoi gian >=14h
	 * reset lai thoi gian moi khi chu ky du 24h hoac thoi gian nghi >=14h
	 */
	private Date shift_start;
	private int shift_stop_time;
	/**
	 * thoi gian lai xe lien tuc
	 * duoc tinh khi bat dau mot chuyen lai xe lien tuc
	 */
	private int continue_driving_time;
	private int continue_driving_route_flg;
	private int driver_driving_time_onShift;
	
	/*Start gia tong hop */
	/**
	 * khoang cach chay xe trong ngay
	 */
	private double distance_on_day; //ok
	/**
	 * So lan vuot toc do
	 */
	private int over_speed_count_on_day; //ok
	/**
	 * so lan mo cua trong ngay
	 */
	private int open_door_count_on_day; //ok
	private int open_door_time_on_day;
	/**
	 * so lan no may trong ngay
	 */
	private int engine_on_count_on_day; //ok
	private int engine_on_time_on_day; 
	/**
	 * so lan mo may lanh trong ngay
	 */
	private int air_conditional_on_count_on_day;//ok
	private int air_conditional_on_time_on_day;
	/**
	 * so lan dung trong ngay
	 */
	private int stop_count_on_day; //ok
	//Tổng thời gian dừng trong ngày
	private int stop_times_on_day;//ok
	/**
	 * So lan di chuyen trong ngay
	 */
	private int move_count_on_day; //ok
	//Thời diem di chuyen cuối cùng trong ngày
	private Date move_end_time_on_day;  //ok
	//Tổng thời gian chạy trong ngày
	private int move_times_on_day; //ok
	//Thời diem chạy đầu tiên trong ngày
	private Date move_first_time_on_day = null;
	
	//thompt add 
	/**
	 * so lan đóng mở thùng hàng/ben/cẩu trong ngày
	 */
	private int container; // thùng hàng mở hay đóng
	private int container_on_count_on_day;//ok
	private int container_on_time_on_day;
	
	//thompt add cảnh báo ben, bồn, cẩu
	private int container_crane_dump_alarm_duration;//thoi gian ben, cẩu, thùng hàng
		
	//Thời gian của chuyến dừng cuối cùng
//	private int stop_times_end_on_day;
	/*end gia tri tong hop*/
	
	public int getContainer_crane_dump_alarm_duration() {
		return container_crane_dump_alarm_duration;
	}
	public void setContainer_crane_dump_alarm_duration(int container_crane_dump_alarm_duration) {
		this.container_crane_dump_alarm_duration = container_crane_dump_alarm_duration;
	}

	//============nhien lieu ==============
//	/**
//	 * su dung DFM nhieu lieu tieu hao
//	 */
//	private int use_fuel_dfm;
//	private int use_fuel_sensor;
	/**
	 * xx-xx-xx-xx-xx-.......-xx{50} 1,"Digital 1";2,"Digital 2";3,"Digital
	 * 3";4,"Digital 4";5,"Digital 5";6,"Digital 6";7,"Digital 7";8,"Digital
	 * 8";9,"Analog 1";10,"Analog 2";11,"Pulse 1";12,"Pulse 2";13,"Pulse
	 * 3";14,"Pulse 4";15,"Pulse 5";16,"Pulse 6";17,"Counter 1";18,"Counter
	 * 2";19,"Counter 3";20,"Counter 4";21,"Counter 5";22,"Counter
	 * 6";23,"Counter 7"
	 */
	private String basic_sensor_value;
	private double[] arr_basic_sensor_value;
//	//Nhien lieu binh
	//============end nhien lieu=============
	
	private String dev_name;	
	private String dev_plate;
	private int car_type;
	private long mapid;
	private String location_poits;
	private long old_mapid;
	private LocationEntity location;
	/**
	 * flg danh dau co vuot toc hay khong
	 * 1: vuot, 0: khong vuot
	 */
	private String tcp_port;	
	
	private int cellid_id;			
	private boolean has_old_data;
	private int noGPS;
	
	private String event_code;
	private Date event_time;
	private int fee_station_id;

// Thông tin nạp/xả
	private double fuel_remaining;
	private int fuel_add_limit;
	private int fuel_loss_limit;
	private int drive_continue_max_time;
	
// Thông tin về tiêu thụ điện năng - thompt add 2018-05-14
	//Quãng đường
	private double route_power_distance;
	//thời gian
	private int route_power_time;
	private int toll_in_count;
	private String account_msg;
	private double fuel_consumed;
	private Date created_date;
	private String created_user;
	private Date updated_date;
	private String updated_user;
	private int record_count_on_day;
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}

	/**
	 * ho tro truy suat du lieu khoan thoi gian
	 */
	private Date range_from;
	/**
	 * ho tro truy suat du lieu khoan thoi gian
	 */
	private Date range_to;
	private String formular;
	public String getFormular() {
		return formular;
	}
	public void setFormular(String formular) {
		this.formular = formular;
	}
	
	public int getVehicle_id() {
		return vehicle_id;
	}
	public void setVehicle_id(int vehicle_id) {
		this.vehicle_id = vehicle_id;
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
	public Date getTrk_time() {
		return trk_time;
	}
	public void setTrk_time(Date trk_time) {
		this.trk_time = trk_time;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public int getAltitude() {
		return altitude;
	}
	public void setAltitude(int altitude) {
		this.altitude = altitude;
	}
	public int getSatellites() {
		return satellites;
	}
	public void setSatellites(int satellites) {
		this.satellites = satellites;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getDev_status() {
		return dev_status;
	}
	public void setDev_status(int dev_status) {
		this.dev_status = dev_status;
	}
	
	public int getPre_dev_status() {
		return pre_dev_status;
	}
	public void setPre_dev_status(int pre_dev_status) {
		this.pre_dev_status = pre_dev_status;
	}
	/**
	 * @return the engine
	 */
	public int getEngine() {
		return engine;
	}
	/**
	 * @param engine the engine to set
	 */
	public void setEngine(int engine) {
		this.engine = engine;
	}
	public double getVelocity() {
		return velocity;
	}
	public void setVelocity(double velocity) {
		this.velocity = velocity;
	}
	public double getSpeed() {
		return speed;
	}
	public void setSpeed(double speed) {
		this.speed = speed;
	}
	public double getDistance() {
		return distance;
	}
	public void setDistance(double distance) {
		this.distance = distance;
	}
	public int getDirection() {
		return direction;
	}
	public void setDirection(int direction) {
		this.direction = direction;
	}
	public int getBattery_level() {
		return battery_level;
	}
	public void setBattery_level(int battery_level) {
		this.battery_level = battery_level;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public double getMileage() {
		return mileage;
	}
	public void setMileage(double mileage) {
		this.mileage = mileage;
	}
	public int getIs_device_driver() {
		return is_device_driver;
	}
	public void setIs_device_driver(int using_default_driver) {
		this.is_device_driver = using_default_driver;
	}
	public void setDriver_code(String driver_code) {
		this.driver_code = driver_code;
	}
	public String getDriver_code() {
		return driver_code;
	}
	public void setDriver_name(String driver_name) {
		this.driver_name = driver_name;
	}
	public String getDriver_name() {
		return driver_name;
	}
	
	public long getMapid() {
		return mapid;
	}
	public void setMapid(long mapid) {
		this.mapid = mapid;
	}
	public String getTcp_port() {
		return tcp_port;
	}
	public void setTcp_port(String tcp_port) {
		this.tcp_port = tcp_port;
	}
	public int getCellid_id() {
		return cellid_id;
	}
	public void setCellid_id(int cellid_id) {
		this.cellid_id = cellid_id;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}

	public int getNoGPS() {
		return noGPS;
	}
	public void setNoGPS(int noGPS) {
		this.noGPS = noGPS;
	}
	public double getRoute_distance() {
		return route_distance;
	}
	public void setRoute_distance(double route_distance) {
		this.route_distance = route_distance;
	}
	public int getContinue_driving_time() {
		return continue_driving_time;
	}
	public void setContinue_driving_time(int continue_driving_time) {
		this.continue_driving_time = continue_driving_time;
	}
	public int getContinue_driving_route_flg() {
		return continue_driving_route_flg;
	}
	public void setContinue_driving_route_flg(int continue_driving_route_flg) {
		this.continue_driving_route_flg = continue_driving_route_flg;
	}
	public double getContinue_route_distance() {
		return continue_route_distance;
	}
	public void setContinue_route_distance(double continue_route_distance) {
		this.continue_route_distance = continue_route_distance;
	}
	public int getOne_day_stop_time() {
		return one_day_stop_time;
	}
	public void setOne_day_stop_time(int one_day_stop_time) {
		this.one_day_stop_time = one_day_stop_time;
	}

	public int getDriver_driving_time_onShift() {
		return driver_driving_time_onShift;
	}
	public void setDriver_driving_time_onShift(int driver_driving_time_onShift) {
		this.driver_driving_time_onShift = driver_driving_time_onShift;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
//	public int getOverspeed() {
//		return overspeed;
//	}
//	public void setOverspeed(int overspeed) {
//		this.overspeed = overspeed;
//	}
	
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
	 * @return the stopTimeEndOnDay
	 */
	public Date getMove_end_time_on_day() {
		return move_end_time_on_day;
	}
	/**
	 * @param stopTimeEndOnDay the stopTimeEndOnDay to set
	 */
	public void setMove_end_time_on_day(Date stopTimeEndOnDay) {
		this.move_end_time_on_day = stopTimeEndOnDay;
	}
	public double getV_limit_max() {
		return v_limit_max;
	}
	public void setV_limit_max(double v_limit_max) {
		this.v_limit_max = v_limit_max;
	}
	public int getOne_day_running_time() {
		return one_day_running_time;
	}
	public void setOne_day_running_time(int one_day_running_time) {
		this.one_day_running_time = one_day_running_time;
	}

	public double getSpeed_calc() {
		return speed_calc;
	}
	public void setSpeed_calc(double speed_calc) {
		this.speed_calc = speed_calc;
	}
	public double getSpeed_gps() {
		return speed_gps;
	}
	public void setSpeed_gps(double speed_gps) {
		this.speed_gps = speed_gps;
	}
	public double getSpeed_pulse() {
		return speed_pulse;
	}
	public void setSpeed_pulse(double speed_pulse) {
		this.speed_pulse = speed_pulse;
	}
	
	private String speed_history;
	public String getSpeed_history() {
		return speed_history;
	}
	public void setSpeed_history(String speed_history) {
		this.speed_history = speed_history;
	}


	public double getMileage_calc() {
		return mileage_calc;
	}
	public void setMileage_calc(double mileage_calc) {
		this.mileage_calc = mileage_calc;
	}
	public double getMileage_gps() {
		return mileage_gps;
	}
	public void setMileage_gps(double mileage_gps) {
		this.mileage_gps = mileage_gps;
	}
	public double getMileage_sensor() {
		return mileage_sensor;
	}
	public void setMileage_sensor(double mileage_sensor) {
		this.mileage_sensor = mileage_sensor;
	}
	public Date getEvent_time() {
		return event_time;
	}
	public void setEvent_time(Date event_time) {
		this.event_time = event_time;
	}
	public String getEvent_code() {
		return event_code;
	}
	public void setEvent_code(String event_code) {
		this.event_code = event_code;
	}
	public boolean isEvent() {
		return event;
	}
	public void setEvent(boolean event) {
		this.event = event;
	}

	public double getDistance_on_day() {
		return distance_on_day;
	}
	public void setDistance_on_day(double distance_on_day) {
		this.distance_on_day = distance_on_day;
	}
	public int getStop_times_on_day() {
		return stop_times_on_day;
	}
	public void setStop_times_on_day(int stop_times_on_day) {
		this.stop_times_on_day = stop_times_on_day;
	}
	public Date getMove_first_time_on_day() {
		return move_first_time_on_day;
	}
	public void setMove_first_time_on_day(Date move_first_time_on_day) {
		this.move_first_time_on_day = move_first_time_on_day;
	}
	public int getMove_times_on_day() {
		return move_times_on_day;
	}
	public void setMove_times_on_day(int move_times_on_day) {
		this.move_times_on_day = move_times_on_day;
	}
	public double getExc_distance() {
		return exc_distance;
	}
	public void setExc_distance(double exc_distance) {
		this.exc_distance = exc_distance;
	}
	public double getExc_max_speed() {
		return exc_max_speed;
	}
	public void setExc_max_speed(double exc_max_speed) {
		this.exc_max_speed = exc_max_speed;
	}
	public int getUse_mileage() {
		return use_mileage;
	}
	public void setUse_mileage(int use_mileage) {
		this.use_mileage = use_mileage;
	}
	public int getUse_dev_speed() {
		return use_dev_speed;
	}
	public void setUse_dev_speed(int use_dev_speed) {
		this.use_dev_speed = use_dev_speed;
	}

	public double getDistance_param() {
		return distance_param;
	}
	public void setDistance_param(double distance_param) {
		this.distance_param = distance_param;
	}
	public int getExc_alarm_duration() {
		return exc_alarm_duration;
	}
	public void setExc_alarm_duration(int exc_duration) {
		this.exc_alarm_duration = exc_duration;
	}
	public int getRoute_time() {
		return route_time;
	}
	public void setRoute_time(int route_time) {
		this.route_time = route_time;
	}
	public double getVlimit_min() {
		return vlimit_min;
	}
	public void setVlimit_min(double vlimit_min) {
		this.vlimit_min = vlimit_min;
	}
	public Date getShift_start() {
		return shift_start;
	}
	public void setShift_start(Date shift_start) {
		this.shift_start = shift_start;
	}
	public int getShift_stop_time() {
		return shift_stop_time;
	}
	public void setShift_stop_time(int shift_stop_time) {
		this.shift_stop_time = shift_stop_time;
	}
	public int getOver_speed_count_on_day() {
		return over_speed_count_on_day;
	}
	public void setOver_speed_count_on_day(int over_speed_count) {
		this.over_speed_count_on_day = over_speed_count;
	}
	public int getOpen_door_count_con_day() {
		return open_door_count_on_day;
	}
	public void setOpen_door_count_on_day(int open_door_count) {
		this.open_door_count_on_day = open_door_count;
	}
	public int getEngine_on_count_on_day() {
		return engine_on_count_on_day;
	}
	public void setEngine_on_count_on_day(int engine_on_count) {
		this.engine_on_count_on_day = engine_on_count;
	}
	public int getAir_conditional_on_count_on_day() {
		return air_conditional_on_count_on_day;
	}
	public void setAir_conditional_on_count_on_day(int air_conditional_on_count) {
		this.air_conditional_on_count_on_day = air_conditional_on_count;
	}
	public int getStop_count_on_day() {
		return stop_count_on_day;
	}
	public void setStop_count_on_day(int stop_count_on_day) {
		this.stop_count_on_day = stop_count_on_day;
	}
	public int getMove_count_on_day() {
		return move_count_on_day;
	}
	public void setMove_count_on_day(int move_count_on_day) {
		this.move_count_on_day = move_count_on_day;
	}
	public String getDev_name() {
		return dev_name;
	}
	public void setDev_name(String dev_name) {
		this.dev_name = dev_name;
	}
	public String getDev_plate() {
		return dev_plate;
	}
	public void setDev_plate(String dev_plate) {
		this.dev_plate = dev_plate;
	}
	public boolean isHas_old_data() {
		return has_old_data;
	}
	public void setHas_old_data(boolean has_old_data) {
		this.has_old_data = has_old_data;
	}

	public int getFee_station_id() {
		return fee_station_id;
	}
	public void setFee_station_id(int fee_station_id) {
		this.fee_station_id = fee_station_id;
	}
	public int getLocation_route_time() {
		return location_route_time;
	}
	public void setLocation_route_time(int location_route_time) {
		this.location_route_time = location_route_time;
	}
	public String getBasic_sensor_value() {
		return basic_sensor_value;
	}
	
	public void setBasic_sensor_value(String basic_sensor_value) {
		this.basic_sensor_value = basic_sensor_value;
	}

	public double[] getArr_basic_sensor_value() {
		return arr_basic_sensor_value;
	}
	public double getElementArr_basic_sensor_valueByIndex(int index) {
		if(null==arr_basic_sensor_value||arr_basic_sensor_value.length<=0){
			String[] arr= basic_sensor_value.split("-");
			if(null==arr||arr.length<=0){
				return 0;
			}
			arr_basic_sensor_value = new double[arr.length];
			for(int i =0; i<arr.length;i++){
				arr_basic_sensor_value[i]=Lib.strToDouble(arr[i]);
			}
		}
		if(null==arr_basic_sensor_value||arr_basic_sensor_value.length<=0){
			return 0;
		}
		if(index>=arr_basic_sensor_value.length){
			return 0;
		}
		return arr_basic_sensor_value[index];
	}

	/**
	 * 
	 * @param index
	 * @param value
	 */
	public void setElementArr_basic_sensor_valueByIndex(int index,double value) {
		if(null==arr_basic_sensor_value||arr_basic_sensor_value.length<=0){
			String[] arr= basic_sensor_value.split("-");
			if(null==arr||arr.length<=0){
				return;
			}
			arr_basic_sensor_value = new double[arr.length];
			for(int i =0; i<arr.length;i++){
				arr_basic_sensor_value[i]=Lib.strToDouble(arr[i]);
			}
		}
		if(null==arr_basic_sensor_value){
			return;
		}
		if(index>=arr_basic_sensor_value.length){
			return;
		}
		arr_basic_sensor_value[index] = value;
		String basic_value = Lib.doubleArray2JoinString(arr_basic_sensor_value);
		this.setBasic_sensor_value(basic_value);
	}
	public void setArr_basic_sensor_value(double[] arr_basic_sensor_value) {
		this.arr_basic_sensor_value = arr_basic_sensor_value;
	}
//	public List getListSensorData() {
//		return listSensorData;
//	}
//	public void setElemetListSensorData(SensorDataEntity sensorData) {
//		if(null==this.listSensorData){
//			this.listSensorData = new ArrayList<SensorDataEntity>();
//		}
//		listSensorData.add(sensorData);
//	}
	public int getDevice_type() {
		return device_type;
	}
	public void setDevice_type(int device_type) {
		this.device_type = device_type;
	}
	public int getDoor_is_open() {
		return door_is_open;
	}
	public void setDoor_is_open(int door_is_open) {
		this.door_is_open = door_is_open;
	}
	public int getConditional() {
		return conditional;
	}
	public void setConditional(int conditional) {
		this.conditional = conditional;
	}
	public HashMap<String, SensorDataEntity> getMap_sensor_data() {
		return map_sensor_data;
	}
	public void setMap_sensor_data(HashMap<String, SensorDataEntity> fuel_level) {
		this.map_sensor_data = fuel_level;
	}
//	public HashMap<String, SensorDataEntity> getMap_fuel_dfm() {
//		return map_fuel_dfm;
//	}
//	public void setMap_fuel_dfm(HashMap<String, SensorDataEntity> fuel_dfm) {
//		this.map_fuel_dfm = fuel_dfm;
//	}
	public int getFuel_add_limit() {
		return fuel_add_limit;
	}
	public void setFuel_add_limit(int fuel_add_limit) {
		this.fuel_add_limit = fuel_add_limit;
	}
	public int getFuel_loss_limit() {
		return fuel_loss_limit;
	}
	public void setFuel_loss_limit(int fuel_loss_limit) {
		this.fuel_loss_limit = fuel_loss_limit;
	}
	public String getDriver_phone() {
		return driver_phone;
	}
	public void setDriver_phone(String driver_phone) {
		this.driver_phone = driver_phone;
	}
	public int getContinue_route_count() {
		return continue_route_count;
	}
	public void setContinue_route_count(int continue_route_count) {
		this.continue_route_count = continue_route_count;
	}
	public int getToll_in_count() {
		return toll_in_count;
	}
	public void setToll_in_count(int toll_in_count) {
		this.toll_in_count = toll_in_count;
	}
	public String getAccount_msg() {
		return account_msg;
	}
	public void setAccount_msg(String account_msg) {
		this.account_msg = account_msg;
	}
	public int getEngine_on_time_on_day() {
		return engine_on_time_on_day;
	}
	public void setEngine_on_time_on_day(int engine_on_time_on_day) {
		this.engine_on_time_on_day = engine_on_time_on_day;
	}
	public int getOpen_door_time_on_day() {
		return open_door_time_on_day;
	}
	public void setOpen_door_time_on_day(int open_door_time_on_day) {
		this.open_door_time_on_day = open_door_time_on_day;
	}
	public int getAir_conditional_on_time_on_day() {
		return air_conditional_on_time_on_day;
	}
	public void setAir_conditional_on_time_on_day(int air_conditional_on_time_on_day) {
		this.air_conditional_on_time_on_day = air_conditional_on_time_on_day;
	}
	public double getFuel_consumed() {
		return fuel_consumed;
	}
	public void setFuel_consumed(double fuel_consumed) {
		this.fuel_consumed = fuel_consumed;
	}
	public int getDrive_continue_max_time() {
		return drive_continue_max_time;
	}
	public void setDrive_continue_max_time(int drive_continue_max_time) {
		this.drive_continue_max_time = drive_continue_max_time;
	}
	public double getFuel_remaining() {
		return fuel_remaining;
	}
	public void setFuel_remaining(double fuel_remaining) {
		this.fuel_remaining = fuel_remaining;
	}
	public int getDrive_alarm_duration() {
		return drive_alarm_duration;
	}
	public void setDrive_alarm_duration(int drive_alarm_duration) {
		this.drive_alarm_duration = drive_alarm_duration;
	}
	public int getLoc_in_out_alarm_duration() {
		return loc_in_out_alarm_duration;
	}
	public void setLoc_in_out_alarm_duration(int loc_in_alarm_duration) {
		this.loc_in_out_alarm_duration = loc_in_alarm_duration;
	}
	public long getOld_mapid() {
		return old_mapid;
	}
	public void setOld_mapid(long old_mapid) {
		this.old_mapid = old_mapid;
	}
	public int getCar_type() {
		return car_type;
	}
	public void setCar_type(int car_type) {
		this.car_type = car_type;
	}
	public List getValue_list() {
		return value_list;
	}
	public void setValue_list(List value_list) {
		this.value_list = value_list;
	}
	public Date getCreated_date() {
		return created_date;
	}
	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}
	public String getCreated_user() {
		return created_user;
	}
	public void setCreated_user(String created_user) {
		this.created_user = created_user;
	}
	public Date getUpdated_date() {
		return updated_date;
	}
	public void setUpdated_date(Date updated_date) {
		this.updated_date = updated_date;
	}
	public String getUpdated_user() {
		return updated_user;
	}
	public void setUpdated_user(String updated_user) {
		this.updated_user = updated_user;
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
	public String getLocation_poits() {
		return location_poits;
	}
	public void setLocation_poits(String location_poits) {
		this.location_poits = location_poits;
	}
	public Date getExpired() {
		return expired;
	}
	public void setExpired(Date expired) {
		this.expired = expired;
	}
	public Date getRange_from() {
		return range_from;
	}
	public void setRange_from(Date range_from) {
		this.range_from = range_from;
	}
	public Date getRange_to() {
		return range_to;
	}
	public void setRange_to(Date range_to) {
		this.range_to = range_to;
	}
	public Date getDriver_expired() {
		return driver_expired;
	}
	public void setDriver_expired(Date driver_expired) {
		this.driver_expired = driver_expired;
	}
	public int getUse_battery() {
		return use_battery;
	}
	public void setUse_battery(int use_battery) {
		this.use_battery = use_battery;
	}
	public int getMsg_post_kind() {
		return msg_post_kind;
	}
	public void setMsg_post_kind(int msg_post_kind) {
		this.msg_post_kind = msg_post_kind;
	}
	public int getRecord_count_on_day() {
		return record_count_on_day;
	}
	public void setRecord_count_on_day(int record_count_on_day) {
		this.record_count_on_day = record_count_on_day;
	}
	public int getConcrete_status() {
		return concrete_status;
	}
	public void setConcrete_status(int concrete_status) {
		this.concrete_status = concrete_status;
	}
	public LocationEntity getLocation() {
		return location;
	}
	public void setLocation(LocationEntity location) {
		this.location = location;
	}
	
	public double getRoute_power_distance() {
		return route_power_distance;
	}
	public void setRoute_power_distance(double route_power_distance) {
		this.route_power_distance = route_power_distance;
	}
	public int getRoute_power_time() {
		return route_power_time;
	}
	public void setRoute_power_time(int route_power_time) {
		this.route_power_time = route_power_time;
	}
		
	public int getContainer() {
		return container;
	}
	public void setContainer(int container) {
		this.container = container;
	}
	public int getContainer_on_count_on_day() {
		return container_on_count_on_day;
	}
	public void setContainer_on_count_on_day(int container_on_count_on_day) {
		this.container_on_count_on_day = container_on_count_on_day;
	}
	public int getContainer_on_time_on_day() {
		return container_on_time_on_day;
	}
	public void setContainer_on_time_on_day(int container_on_time_on_day) {
		this.container_on_time_on_day = container_on_time_on_day;
	}
	/**
	 * Hàm hiển thị thông tin về phương tiện trên màn hình console.
	 * thompt add
	 */
	public String toString(){
		String sTracking = new String();
		sTracking +="\n THONG TIN PHUONG TIEN";
		sTracking +="\n vehicle_id : " + this.vehicle_id ;
		sTracking +="\n vehicle_name : " + this.vehicle_name ;
		sTracking +="\n IMEI : " + this.imei ;
		
		sTracking +="\n stop_times_on_day: " + this.stop_times_on_day ;
		sTracking +="\n one_day_running_time: " + this.one_day_running_time ;
		sTracking +="\n duration: " + this.duration ;
		sTracking +="\n trk_time: " + this.trk_time.toString() ;
		sTracking +="\n Event_code: " + this.event_code ;
		sTracking +="\n Event_time: " + this.event_time.toString() ;
		
		sTracking +="\n dev_status: " + this.status ;
		sTracking +="\n Pre_dev_status: " + this.pre_dev_status ;
		sTracking +="\n Route_time: " + this.route_time ;
		
	return  sTracking;	
	}
	
	/***
	 * Hàm kiểm tra dữ liệu với mỗi đối tượng tracking
	 * thompt add 03/03/2018
	 */
	public void validateTracking(){
		// Tên không quá 150 kí tự
		if(!Lib.isBlank(this.vehicle_name)&& this.vehicle_name.length()>150){
			String name1 = this.vehicle_name.substring(0, Math.min(this.vehicle_name.length(), 150));
			this.setVehicle_name(name1);
		}
		
		// Biển số không quá 64 kí tự
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
		
		//Kinh độ và vĩ độ decimal(11,8)
		if(this.latitude<-999.99999999){
			this.setLatitude(-999.99999999);
		}
		if(this.latitude>999.99999999){
			this.setLatitude(999.99999999);
		}
		if(this.longitude<-999.99999999){
			this.setLongitude(-999.99999999);
		}
		if(this.longitude>999.99999999){
			this.setLongitude(999.99999999);
		}
		
		// Cao độ không quá 32500 và ko âm		
		if(this.altitude<0){
			this.setAltitude(0);
		}
		if(this.altitude>32500){
			this.setAltitude(32500);
		}
		
		// Số vệ tinh <127 và ko âm		
		if(this.satellites<0){
			this.setSatellites(0);
		}
		if(this.satellites>127){
			this.setSatellites(127);
		}
		
		// Thời gian lái xe liên tục trong ngày ko âm và ko quá 86400 giây (24h)
		if(this.one_day_running_time>86400){
			this.setOne_day_running_time(86400);
		}
		
		//Trạng thái pin không âm và nhỏ hơn 26.
		if(this.battery_level<0){
			this.setBattery_level(0);
		}
		if(this.battery_level>26){
			this.setBattery_level(26);
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
		
		//speed history không quá 1024 kí tự
		if(!Lib.isBlank(this.speed_history)&& this.speed_history.length()>=1024){
			String name1 = this.speed_history.substring(0, Math.min(this.speed_history.length(), 1023));
			this.setSpeed_history(name1);
		}
		//basic_sensor_value không quá 300 kí tự
		if(!Lib.isBlank(this.basic_sensor_value)&& this.basic_sensor_value.length()>300){
			String name1 = this.basic_sensor_value.substring(0, Math.min(this.basic_sensor_value.length(), 300));
			this.setBasic_sensor_value(name1);
		}
			
		// address không quá 255 kí tự
		if(!Lib.isBlank(this.address)&& this.address.length()>255){
			String name1 = this.address.substring(0, Math.min(this.address.length(), 255));
			this.setAddress(name1);
		}
			
		// Quãng đường di chuyển không lớn hơn 9999,999km
		double maxValue = (Math.pow(10,7)-1)/Math.pow(10,3);
		
		if(this.distance < 0){
			this.setDistance(0);
		}
		if(this.distance > maxValue){
			this.setDistance(maxValue);
		}
		
		//Các vận tốc không quá 9999,99km
		double maxSpeed = (Math.pow(10,6)-1)/Math.pow(10,2);
		if(this.velocity < 0){
			this.setVelocity(0);
		}
		if(this.velocity > maxSpeed){
			this.setVelocity(maxSpeed);
		}
		if(this.speed > maxSpeed){
			this.setSpeed(maxSpeed);
		}
		if(this.speed_calc > maxSpeed){
			this.setSpeed_calc(maxSpeed);
		}
		if(this.speed_gps > maxSpeed){
			this.setSpeed_gps(maxSpeed);
		}
		// hướng không smallint(6).		
		if(this.direction<-32768){
			this.setDirection(-32768);
		}
		if(this.direction>32767){
			this.setDirection(32768);
		}
	}
	
}
