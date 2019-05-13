package com.flex.entities.trailer;

import java.util.Date;

import com.flex.dbmanager.attributes.RedisClassNameAliasAttribute;
import com.flex.dbmanager.attributes.RedisIgnoreKeyAttributes;
import com.flex.dbmanager.attributes.RedisIndexAttribute;
import com.flex.dbmanager.attributes.RedisKeyAttribute;

@RedisKeyAttribute(key="device_imei")
@RedisClassNameAliasAttribute(name="Trailer")
public class TrailerEntity {
	private int id;
	private String device_imei;
	private String name;
	private String user_name;
	private String icon;
	private String brand;
	private String manufacturer;
	private Date first_registration;
	private int production_year;
	private String color;
	private double fuel_tank_capacity;
	private double tonnage;
	private double number_of_cakes;
	private double weight;
	private double trailer_long;
	private double wide;
	private double height;
	private double volume_of_tank;
	private String vin;
	private Date expired;
	private Date next_pay_time;
	private String sale_id;
	private int active;
	private int is_delete;
	private int data_group;
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
	 * @return the device_imei
	 */
	public String getDevice_imei() {
		return device_imei;
	}
	/**
	 * @param device_imei the device_imei to set
	 */
	public void setDevice_imei(String device_imei) {
		this.device_imei = device_imei;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
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
	 * @return the icon
	 */
	public String getIcon() {
		return icon;
	}
	/**
	 * @param icon the icon to set
	 */
	public void setIcon(String icon) {
		this.icon = icon;
	}
	/**
	 * @return the brand
	 */
	public String getBrand() {
		return brand;
	}
	/**
	 * @param brand the brand to set
	 */
	public void setBrand(String brand) {
		this.brand = brand;
	}
	/**
	 * @return the manufacturer
	 */
	public String getManufacturer() {
		return manufacturer;
	}
	/**
	 * @param manufacturer the manufacturer to set
	 */
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	/**
	 * @return the first_registration
	 */
	public Date getFirst_registration() {
		return first_registration;
	}
	/**
	 * @param first_registration the first_registration to set
	 */
	public void setFirst_registration(Date first_registration) {
		this.first_registration = first_registration;
	}
	/**
	 * @return the production_year
	 */
	public int getProduction_year() {
		return production_year;
	}
	/**
	 * @param production_year the production_year to set
	 */
	public void setProduction_year(int production_year) {
		this.production_year = production_year;
	}
	/**
	 * @return the color
	 */
	public String getColor() {
		return color;
	}
	/**
	 * @param color the color to set
	 */
	public void setColor(String color) {
		this.color = color;
	}
	/**
	 * @return the fuel_tank_capacity
	 */
	public double getFuel_tank_capacity() {
		return fuel_tank_capacity;
	}
	/**
	 * @param fuel_tank_capacity the fuel_tank_capacity to set
	 */
	public void setFuel_tank_capacity(double fuel_tank_capacity) {
		this.fuel_tank_capacity = fuel_tank_capacity;
	}
	/**
	 * @return the tonnage
	 */
	public double getTonnage() {
		return tonnage;
	}
	/**
	 * @param tonnage the tonnage to set
	 */
	public void setTonnage(double tonnage) {
		this.tonnage = tonnage;
	}
	/**
	 * @return the number_of_cakes
	 */
	public double getNumber_of_cakes() {
		return number_of_cakes;
	}
	/**
	 * @param number_of_cakes the number_of_cakes to set
	 */
	public void setNumber_of_cakes(double number_of_cakes) {
		this.number_of_cakes = number_of_cakes;
	}
	/**
	 * @return the weight
	 */
	public double getWeight() {
		return weight;
	}
	/**
	 * @param weight the weight to set
	 */
	public void setWeight(double weight) {
		this.weight = weight;
	}
	/**
	 * @return the trailer_long
	 */
	public double getTrailer_long() {
		return trailer_long;
	}
	/**
	 * @param trailer_long the trailer_long to set
	 */
	public void setTrailer_long(double trailer_long) {
		this.trailer_long = trailer_long;
	}
	/**
	 * @return the wide
	 */
	public double getWide() {
		return wide;
	}
	/**
	 * @param wide the wide to set
	 */
	public void setWide(double wide) {
		this.wide = wide;
	}
	/**
	 * @return the hight
	 */
	public double getHeight() {
		return height;
	}
	/**
	 * @param hight the hight to set
	 */
	public void setHeight(double height) {
		this.height = height;
	}
	/**
	 * @return the volume_of_tank
	 */
	public double getVolume_of_tank() {
		return volume_of_tank;
	}
	/**
	 * @param volume_of_tank the volume_of_tank to set
	 */
	public void setVolume_of_tank(double volume_of_tank) {
		this.volume_of_tank = volume_of_tank;
	}
	/**
	 * @return the vin
	 */
	public String getVin() {
		return vin;
	}
	/**
	 * @param vin the vin to set
	 */
	public void setVin(String vin) {
		this.vin = vin;
	}
	/**
	 * @return the expired
	 */
	public Date getExpired() {
		return expired;
	}
	/**
	 * @param expired the expired to set
	 */
	public void setExpired(Date expired) {
		this.expired = expired;
	}
	/**
	 * @return the next_pay_time
	 */
	public Date getNext_pay_time() {
		return next_pay_time;
	}
	/**
	 * @param next_pay_time the next_pay_time to set
	 */
	public void setNext_pay_time(Date next_pay_time) {
		this.next_pay_time = next_pay_time;
	}
	/**
	 * @return the sale_id
	 */
	public String getSale_id() {
		return sale_id;
	}
	/**
	 * @param sale_id the sale_id to set
	 */
	public void setSale_id(String sale_id) {
		this.sale_id = sale_id;
	}
	public int getActive() {
		return active;
	}
	public void setActive(int active) {
		this.active = active;
	}
	public int getIs_delete() {
		return is_delete;
	}
	public void setIs_delete(int is_delete) {
		this.is_delete = is_delete;
	}
	public int getData_group() {
		return data_group;
	}
	public void setData_group(int data_group) {
		this.data_group = data_group;
	}
}
