package com.flex.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.flex.dbmanager.attributes.RedisClassNameAliasAttribute;
import com.flex.dbmanager.attributes.RedisIndexAttribute;
import com.flex.dbmanager.attributes.RedisKeyAttribute;
import com.flex.utils.Constants;
import com.flex.utils.Lib;
@RedisKeyAttribute(key="key")
public class SensorDataEntity extends BaseEntity{
	private String db;
	private int  id;
	private int vehicle_id;
	private String imei;
	private String user_name;
	private Date trk_time;
	private int sensor_index;
	private String sensor_param_code;
	private String display_name;
	private String status_alias_map;
	private double basic_value;
	private double cal_value;
	private double interpolate_value;
	private String formular;
	private String func_name;
	private int data_group;
	private double limit_min;
	private double limit_max;
	private Date from_date;
	private Date to_date;
	private int is_interpolate;
	private List<SensorDataEntity> value_list = new ArrayList<SensorDataEntity>();
	private List<Double> lst_cal_value=new ArrayList<Double>();
	private List<Double> lst_basic_value=new ArrayList<Double>();
	private String key;
	public boolean addCalValue(double val){
		try{
			if(lst_cal_value.size()>=10){
				lst_cal_value.remove(0);
			}
			lst_cal_value.add(val);
			if(lst_cal_value.size()<10){
				for(int i = lst_cal_value.size();i<10;i++){
					lst_cal_value.add(val);
				}
			}
			return true;
		}catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}
	public boolean setElementCalValue(double val,int index){
		try{
			lst_cal_value.set(index, val);
			return true;
		}catch (Exception e) {
			return false;
		}
	}
	public boolean addBasicValue(double val){
		try{
			if(lst_basic_value.size()>=10){
				lst_basic_value.remove(0);
			}
			lst_basic_value.add(val);
			if(lst_basic_value.size()<10){
				for(int i = lst_basic_value.size();i<10;i++){
					lst_basic_value.add(val);
				}
			}
			return true;
		}catch (Exception e) {
			return false;
		}
	}
	public boolean setElementBasicValue(double val,int index){
		try{
			lst_basic_value.set(index, val);
			return true;
		}catch (Exception e) {
			return false;
		}
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
	 * @return the sensor_index
	 */
	public int getSensor_index() {
		return sensor_index;
	}
	/**
	 * @param sensor_index the sensor_index to set
	 */
	public void setSensor_index(int sensor_index) {
		this.sensor_index = sensor_index;
	}
	/**
	 * @return the sensor_param_id
	 */
	public String getSensor_param_code() {
		return sensor_param_code;
	}
	/**
	 * @param sensor_param_id the sensor_param_id to set
	 */
	public void setSensor_param_code(String sensor_param_id) {
		this.sensor_param_code = sensor_param_id;
	}
	/**
	 * @return the basic_value
	 */
	public double getBasic_value() {
		return basic_value;
	}
	/**
	 * @param basic_value the basic_value to set
	 */
	public void setBasic_value(double basic_value) {
		this.basic_value = basic_value;
	}
	/**
	 * @return the cal_value
	 */
	public double getCal_value() {
		return cal_value;
	}
	/**
	 * @param cal_value the cal_value to set
	 */
	public void setCal_value(double cal_value) {
		this.cal_value = cal_value;
	}
	public int getData_group() {
		return data_group;
	}
	public void setData_group(int data_group) {
		this.data_group = data_group;
	}
	public double getInterpolate_value() {
		return interpolate_value;
	}
	public void setInterpolate_value(double interpolate) {
		this.interpolate_value = interpolate;
	}

	public double getAverageCalValue() {
		try{
			return lst_cal_value.stream().mapToDouble(val -> val).average().getAsDouble();
		}catch (Exception e) {
			return 0;
		}
	}
	public double getElementLstCalValue(int idx) {
		try{
			if(null==lst_cal_value){
				return Double.NaN;
			}
			if(idx<0){
				idx=0;
			}
			if(idx>=lst_cal_value.size()){
				idx = lst_cal_value.size()-1;
			}
			return lst_cal_value.get(idx);
		}catch (Exception e) {
			return 0;
		}
	}

	public double getAverageBasicValue() {
		try{
			return lst_basic_value.stream().mapToDouble(val -> val).average().getAsDouble();
		}catch (Exception e) {
			return 0;
		}
	}
	public double getMaxLstBasicValue() {
		try{
			return lst_basic_value.stream().mapToDouble(val -> val).max().getAsDouble();
		}catch (Exception e) {
			return 0;
		}
	}
	public double getMinLstBasicValue() {
		try{
			return lst_basic_value.stream().mapToDouble(val -> val).min().getAsDouble();
		}catch (Exception e) {
			return 0;
		}
	}
	public double getElementLstBasicValue(int idx) {
		try{
			if(null==lst_basic_value){
				return Double.NaN;
			}
			if(idx<0){
				idx=0;
			}
			if(idx>=lst_basic_value.size()){
				idx = lst_basic_value.size()-1;
			}
			return lst_basic_value.get(idx);
		}catch (Exception e) {
			return 0;
		}
	}
	
	public List<Double> getLst_cal_value() {
		return lst_cal_value;
	}
	public void setLst_cal_value(List<Double> lstValue) {
		this.lst_cal_value = lstValue;
	}
	public List<Double> getLst_basic_value() {
		return lst_basic_value;
	}
	public void setLst_basic_value(List<Double> lst_basic_value) {
		this.lst_basic_value = lst_basic_value;
	}
	public String getFormular() {
		return formular;
	}
	public void setFormular(String formular) {
		this.formular = formular;
	}
	public String getFunc_name() {
		return func_name;
	}
	public void setFunc_name(String func_name) {
		this.func_name = func_name;
	}
	public List getValue_list() {
		return value_list;
	}
	public void setValue_list(List value_list) {
		this.value_list = value_list;
	}
	public double getLimit_min() {
		return limit_min;
	}
	public void setLimit_min(double limit_min) {
		this.limit_min = limit_min;
	}
	public double getLimit_max() {
		return limit_max;
	}
	public void setLimit_max(double limit_max) {
		this.limit_max = limit_max;
	}
	public String getDb() {
		int year = Lib.getYearByDate(this.trk_time);
		int month = Lib.getMonthByDate(this.trk_time);
		db=Constants.DB_PREFIX+year+Lib.padLeft(month+"",2);
		return db;
	}
	public String getDisplay_name() {
		return display_name;
	}
	public void setDisplay_name(String display_name) {
		this.display_name = display_name;
	}
	public Date getFrom_date() {
		return from_date;
	}
	public void setFrom_date(Date from_date) {
		this.from_date = from_date;
	}
	public Date getTo_date() {
		return to_date;
	}
	public void setTo_date(Date to_date) {
		this.to_date = to_date;
	}
	public int getIs_interpolate() {
		return is_interpolate;
	}
	public void setIs_interpolate(int is_interpolate) {
		this.is_interpolate = is_interpolate;
	}
	public String getKey(){
		return this.vehicle_id+"_"+this.sensor_index;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getStatus_alias_map() {
		return status_alias_map;
	}
	public void setStatus_alias_map(String status_alias_map) {
		this.status_alias_map = status_alias_map;
	}
	

	/***
	 * Hàm kiểm tra dữ liệu với mỗi đối tượng Sensor
	 * thompt add 07/01/2019
	 */
	public void validateSensor(){
		// Tên không quá 150 kí tự
		if(!Lib.isBlank(this.display_name)&& this.display_name.length()>150){
			String name1 = this.display_name.substring(0, Math.min(this.display_name.length(), 150));
			this.setDisplay_name(name1);
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
		
		//status_alias_map không quá 250 kí tự
		if(!Lib.isBlank(this.status_alias_map)&& this.status_alias_map.length()>250){
			String name1 = this.status_alias_map.substring(0, Math.min(this.status_alias_map.length(), 250));
			this.setStatus_alias_map(name1);
		}
		
		//sensor_param_id không quá 30 kí tự
		if(!Lib.isBlank(this.sensor_param_code)&& this.sensor_param_code.length()>30){
			String name1 = this.sensor_param_code.substring(0, Math.min(this.sensor_param_code.length(), 30));
			this.setSensor_param_code(name1);
		}	
				
	}
}
