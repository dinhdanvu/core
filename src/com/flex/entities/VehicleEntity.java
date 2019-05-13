package com.flex.entities;

import java.util.Date;

import com.flex.dbmanager.attributes.RedisClassNameAliasAttribute;
import com.flex.dbmanager.attributes.RedisIndexAttribute;
import com.flex.dbmanager.attributes.RedisKeyAttribute;

@RedisKeyAttribute(key="device_imei")
//@RedisIndexAttribute(indexs={"is_forward","is_goverment"})
//@RedisIndexAttribute(indexs={"is_goverment"})
@RedisClassNameAliasAttribute(name="Vehicle")
public class VehicleEntity{
	//Vehicle
	private int id;
	private String name;
	private int car_type_id;
	private String user_name;
	private String icon;
	private String plate;
//	private String vin;
	private String driver_name;
	private String driver_code;
	private String driver_phone;
	private Date driver_expired;
	private int num_seats;
//	private int device_group_table_id;
	private String description;
//	private int status;
	private int use_mileage;
	private int use_dev_speed;
	private int v_limit_max;
//	private int vlimitMin;
	private int log_protocol;//0: không; 1: log
//	private int use_flw;
	private int is_goverment;
	private int active;//0: no active; 1: active
	private int data_group;
//	private int use_engine_input;
//	private Date active_date;
	private Date next_pay_time;
	private Date expired;
	private String gplx;
//	private String gplxLimit;
	private String json_norm_string;
	private int is_using_netkeeping;
	private int use_input_config;
	private double distance_param;
	private int fuel_add_limit;
	private int fuel_loss_limit;

			
	private int v_synch;
	private int d_synch;
	
	//device
	private String device_imei;
	private int device_type;//DF521, DF421, ...
	private String device_sim_no;
	private String store;
	private String firmware;
	private int is_forward = 0;
	private String fw_ip;
	private int fw_port;
	
	// có lắp module điện hay không?
	private int is_power_electric = 0;
		
	public int getIs_power_electric() {
		return is_power_electric;
	}
	public void setIs_power_electric(int is_power_electric) {
		this.is_power_electric = is_power_electric;
	}
	public int getCar_type_id() {
		return car_type_id;
	}
	public void setCar_type_id(int car_type_id) {
		this.car_type_id = car_type_id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getPlate() {
		return plate;
	}
	public void setPlate(String plate) {
		this.plate = plate;
	}
//	public String getVin() {
//		return vin;
//	}
//	public void setVin(String vin) {
//		this.vin = vin;
//	}
	public String getDriver_code() {
		return driver_code;
	}
	public void setDriver_code(String driver_id) {
		this.driver_code = driver_id;
	}
	public int getNum_seats() {
		return num_seats;
	}
	public void setNum_seats(int num_seats) {
		this.num_seats = num_seats;
	}
//	public int getDevice_group_table_id() {
//		return device_group_table_id;
//	}
//	public void setDevice_group_table_id(int device_group_table_id) {
//		this.device_group_table_id = device_group_table_id;
//	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
	public void setUse_dev_speed(int use_devspeed) {
		this.use_dev_speed = use_devspeed;
	}
	public int getLog_protocol() {
		return log_protocol;
	}
	public void setLog_protocol(int log_protocol) {
		this.log_protocol = log_protocol;
	}
//	public int getUse_flw() {
//		return use_flw;
//	}
//	public void setUse_flw(int use_flw) {
//		this.use_flw = use_flw;
//	}
	public int getIs_goverment() {
		return is_goverment;
	}
	public void setIs_goverment(int is_goverment) {
		this.is_goverment = is_goverment;
	}
	public int getActive() {
		return active;
	}
	public void setActive(int active) {
		this.active = active;
	}
	public int getData_group() {
		return data_group;
	}
	public void setData_group(int data_group) {
		this.data_group = data_group;
	}
//	public int getUse_engine_input() {
//		return use_engine_input;
//	}
//	public void setUse_engine_input(int use_engine_input) {
//		this.use_engine_input = use_engine_input;
//	}
	public Date getNext_pay_time() {
		return next_pay_time;
	}
	public void setNext_pay_time(Date next_pay_time) {
		this.next_pay_time = next_pay_time;
	}
	public Date getExpired() {
		return expired;
	}
	public void setExpired(Date expired) {
		this.expired = expired;
	}
	public String getGplx() {
		return gplx;
	}
	public void setGplx(String gplx) {
		this.gplx = gplx;
	}
	public int getIs_using_netkeeping() {
		return is_using_netkeeping;
	}
	public void setIs_using_netkeeping(int is_using_netkeeping) {
		this.is_using_netkeeping = is_using_netkeeping;
	}
	public int getUse_input_config() {
		return use_input_config;
	}
	public void setUse_input_config(int use_input_config) {
		this.use_input_config = use_input_config;
	}
	public String getDevice_imei() {
		return device_imei;
	}
	public void setDevice_imei(String devImei) {
		this.device_imei = devImei;
	}
	public int getDevice_type() {
		return device_type;
	}
	public void setDevice_type(int device_type) {
		this.device_type = device_type;
	}
	public String getDevice_sim_no() {
		return device_sim_no;
	}
	public void setDevice_sim_no(String device_sim_no) {
		this.device_sim_no = device_sim_no;
	}
	public String getStore() {
		return store;
	}
	public void setStore(String store) {
		this.store = store;
	}
	public String getFirmware() {
		return firmware;
	}
	public void setFirmware(String firmware) {
		this.firmware = firmware;
	}
	public int getIs_forward() {
		return is_forward;
	}
	public void setIs_forward(int is_forward) {
		this.is_forward = is_forward;
	}
	public String getFw_ip() {
		return fw_ip;
	}
	public void setFw_ip(String fw_ip) {
		this.fw_ip = fw_ip;
	}
	public int getFw_port() {
		return fw_port;
	}
	public void setFw_port(int fw_port) {
		this.fw_port = fw_port;
	}
	public int getId() {
		return id;
	}
	public void setId(int vehicle_id) {
		this.id = vehicle_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String dev_name) {
		this.name = dev_name;
	}
	public String getDriver_name() {
		return driver_name;
	}
	public void setDriver_name(String driver_name) {
		this.driver_name = driver_name;
	}
	public int getV_limit_max() {
		return v_limit_max;
	}
	public void setV_limit_max(int v_limit_max) {
		this.v_limit_max = v_limit_max;
	}
	public String getJson_norm_string() {
		return json_norm_string;
	}
	public void setJson_norm_string(String json_norm_string) {
		this.json_norm_string = json_norm_string;
	}
	public double getDistance_param() {
		return distance_param;
	}
	public void setDistance_param(double distance_param) {
		this.distance_param = distance_param;
	}
	public int getV_synch() {
		return v_synch;
	}
	public void setV_synch(int v_synch) {
		this.v_synch = v_synch;
	}
	public int getD_synch() {
		return d_synch;
	}
	public void setD_synch(int d_synch) {
		this.d_synch = d_synch;
	}
	public int getFuel_loss_limit() {
		return fuel_loss_limit;
	}
	public void setFuel_loss_limit(int fuel_loss_limit) {
		this.fuel_loss_limit = fuel_loss_limit;
	}
	public int getFuel_add_limit() {
		return fuel_add_limit;
	}
	public void setFuel_add_limit(int fuel_add_limit) {
		this.fuel_add_limit = fuel_add_limit;
	}
	public Date getDriver_expired() {
		return driver_expired;
	}
	public void setDriver_expired(Date driver_expired) {
		this.driver_expired = driver_expired;
	}
	public String getDriver_phone() {
		return driver_phone;
	}
	public void setDriver_phone(String driver_phone) {
		this.driver_phone = driver_phone;
	}
	
}
