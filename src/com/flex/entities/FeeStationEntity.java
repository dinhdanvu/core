package com.flex.entities;

import java.util.Date;

import com.flex.dbmanager.attributes.RedisClassNameAliasAttribute;
import com.flex.dbmanager.attributes.RedisIndexAttribute;
import com.flex.dbmanager.attributes.RedisKeyAttribute;
import com.flex.utils.Lib;

@RedisKeyAttribute(key="key")
@RedisIndexAttribute(indexs={"car_type"})
@RedisClassNameAliasAttribute(name="FeeStation")
public class FeeStationEntity{
	//Vehicle
	private int id;
	private String name;
	private String polygons;
	private int price;
	private int price_id;
	private int car_type;
	private Date apply_from_date;
	private Date apply_to_date;
	private String key;
	private int active;
	private int is_delete;
	private int direction;
//	private double longtitude;
//	private double radius;
//	private int is_redis_sync;
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
	 * @return the latitude
	 */
//	public double getLatitude() {
//		return latitude;
//	}
	/**
	 * @param latitude the latitude to set
	 */
//	public void setLatitude(double latitude) {
//		this.latitude = latitude;
//	}
	/**
	 * @return the longtitude
	 */
//	public double getLongtitude() {
//		return longtitude;
//	}
	/**
	 * @param longtitude the longtitude to set
	 */
//	public void setLongtitude(double longtitude) {
//		this.longtitude = longtitude;
//	}
	/**
	 * @return the radius
	 */
//	public double getRadius() {
//		return radius;
//	}
	/**
	 * @param radius the radius to set
	 */
//	public void setRadius(double radius) {
//		this.radius = radius;
//	}
//	public int getIs_redis_sync() {
//		return is_redis_sync;
//	}
//	public void setIs_redis_sync(int is_redis_sync) {
//		this.is_redis_sync = is_redis_sync;
//	}
	public int getActive() {
		return active;
	}
	public void setActive(int active) {
		this.active = active;
	}
	public String getPolygons() {
		return polygons;
	}
	public void setPolygons(String polygons) {
		this.polygons = polygons;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getCar_type() {
		return car_type;
	}
	public void setCar_type(int car_type) {
		this.car_type = car_type;
	}
	public Date getApply_from_date() {
		return apply_from_date;
	}
	public void setApply_from_date(Date apply_from_date) {
		this.apply_from_date = apply_from_date;
	}
	public Date getApply_to_date() {
		return apply_to_date;
	}
	public void setApply_to_date(Date apply_to_date) {
		this.apply_to_date = apply_to_date;
	}
	public String getKey() {
		key = this.id+"_"+this.car_type+ "_" +Lib.dateToString(this.apply_from_date,"yyyyMMdd")+"_"+Lib.dateToString(this.apply_to_date,"yyyyMMdd");
		return key;
	}
	public int getIs_delete() {
		return is_delete;
	}
	public void setIs_delete(int is_delete) {
		this.is_delete = is_delete;
	}
	public int getPrice_id() {
		return price_id;
	}
	public void setPrice_id(int price_id) {
		this.price_id = price_id;
	}
	public int getDirection() {
		return direction;
	}
	public void setDirection(int direction) {
		this.direction = direction;
	}
}
