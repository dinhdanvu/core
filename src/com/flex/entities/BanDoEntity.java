package com.flex.entities;

import com.flex.utils.Lib;

public class BanDoEntity{
	
	private String type_4;
	private String name_4;
	private String name_3;
	private String name_2;
	private String tenduong;
	private double latitude;
	private double longitude;

	public BanDoEntity(){
	}
	
	public BanDoEntity(double lat, double lon){
		latitude = lat;
		longitude = lon;
	}
	
	public String getType_4() {
		return type_4;
	}
	public void setType_4(String type_4) {
		this.type_4 = type_4;
	}
	public String getName_4() {
		return name_4;
	}
	public void setName_4(String name_4) {
		this.name_4 = name_4;
	}
	public String getName_3() {
		return name_3;
	}
	public void setName_3(String name_3) {
		this.name_3 = name_3;
	}
	public String getName_2() {
		return name_2;
	}
	public void setName_2(String name_2) {
		this.name_2 = name_2;
	}
	public String getTenduong() {
		return tenduong;
	}
	public void setTenduong(String tenduong) {
		this.tenduong = tenduong;
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
	
	public String getFullAddress(){
		String ret = "";
		
		if(!Lib.isBlank(tenduong)){ 
			if(!tenduong.trim().toLowerCase().startsWith("đường") || !tenduong.trim().toLowerCase().startsWith("hẻm"))
				ret = "Đường " + tenduong;
		}
		else
			ret = "Đường không tên, ";
		
//		if(!Lib.isBlank(type_4))
//			ret += type_4;
//		else
//			ret = "";
		if(Lib.isBlank(type_4))
			ret = "";
			
		if(!Lib.isBlank(name_4))
			ret += ", " + name_4;
		if(!Lib.isBlank(name_3))
			ret += ", " + name_3;
		if(!Lib.isBlank(name_2))
			ret += ", " + name_2;
		
		return ret;
	}
	
}
