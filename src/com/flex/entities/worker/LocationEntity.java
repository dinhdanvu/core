/**
 * 
 */
package com.flex.entities.worker;

import com.flex.dbmanager.attributes.RedisClassNameAliasAttribute;
import com.flex.dbmanager.attributes.RedisIndexAttribute;
import com.flex.dbmanager.attributes.RedisKeyAttribute;
import com.flex.entities.BaseEntity;

//@RedisClassNameAliasAttribute(name="Locations:")

/**
 * @author Trần Gia Minh
 *
 */

@RedisClassNameAliasAttribute(name="Location")
@RedisKeyAttribute(key="id")
@RedisIndexAttribute(indexs={"user_name"})
public class LocationEntity extends BaseEntity {
	private long id;
	private String user_name;
	private double longitude1;
	private double latitude1;
	private double longitude2;
	private double latitude2;
	private double radius;
	private String name;
	private int location_type_id;
	private int location_shape;
	private int active;
	private int is_delete;
	private int is_redis_sync;
	private String str_poits;
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public double getLongitude1() {
		return longitude1;
	}

	public void setLongitude1(double longitude1) {
		this.longitude1 = longitude1;
	}

	public double getLatitude1() {
		return latitude1;
	}

	public void setLatitude1(double latitude1) {
		this.latitude1 = latitude1;
	}

	public double getLongitude2() {
		return longitude2;
	}

	public void setLongitude2(double longitude2) {
		this.longitude2 = longitude2;
	}

	public double getLatitude2() {
		return latitude2;
	}

	public void setLatitude2(double latitude2) {
		this.latitude2 = latitude2;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int status) {
		this.active = status;
	}

	public int getLocation_type_id() {
		return location_type_id;
	}

	public void setLocation_type_id(int location_type_id) {
		this.location_type_id = location_type_id;
	}

	public int getIs_redis_sync() {
		return is_redis_sync;
	}

	public void setIs_redis_sync(int is_redis_sync) {
		this.is_redis_sync = is_redis_sync;
	}

	public String getStr_poits() {
		return str_poits;
	}

	public void setStr_poits(String str_poits) {
		this.str_poits = str_poits;
	}

	public int getLocation_shape() {
		return location_shape;
	}

	public void setLocation_shape(int location_shape) {
		this.location_shape = location_shape;
	}

	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}

	public int getIs_delete() {
		return is_delete;
	}

	public void setIs_delete(int is_delete) {
		this.is_delete = is_delete;
	}
}
