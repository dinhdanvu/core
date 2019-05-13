package com.flex.entities;

import java.util.Date;

public class TransportationClientEntity{
	private Date dataTime;
	private boolean door;
	private int heading;
	private boolean ignition;
	private float speed;
	private String vehicle;
	private double x;
	private double y;
	private float z;
	private String driver;
	public Date getDataTime() {
		return dataTime;
	}
	public void setDataTime(Date dataTime) {
		this.dataTime = dataTime;
	}
	public boolean isDoor() {
		return door;
	}
	public void setDoor(boolean door) {
		this.door = door;
	}
	public int getHeading() {
		return heading;
	}
	public void setHeading(int heading) {
		this.heading = heading;
	}
	public boolean isIgnition() {
		return ignition;
	}
	public void setIgnition(boolean ignition) {
		this.ignition = ignition;
	}
	public float getSpeed() {
		return speed;
	}
	public void setSpeed(float speed) {
		this.speed = speed;
	}
	public String getVehicle() {
		return vehicle;
	}
	public void setVehicle(String vehicle) {
		this.vehicle = vehicle;
	}
	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}
	public float getZ() {
		return z;
	}
	public void setZ(float z) {
		this.z = z;
	}
	public String getDriver() {
		return driver;
	}
	public void setDriver(String driver) {
		this.driver = driver;
	}
}
