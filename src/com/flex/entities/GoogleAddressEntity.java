package com.flex.entities;

public class GoogleAddressEntity {
	 private String address = "";
	
	 private String city = "";
	
	 private String country = "";
	 private String district = "";
	 private String ward="";

	private String full_address;
	/**
	 * @return the address1
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * @param address1 the address1 to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * @return the address2
	 */
	
	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}
	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}
	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}
	/**
	 * @return the county
	 */
	public String getDistrict() {
		return district;
	}
	/**
	 * @param county the county to set
	 */
	public void setDistrict(String county) {
		this.district = county;
	}

	public String getWard() {
		return ward;
	}
	public void setWard(String ward) {
		this.ward = ward;
	}
	
	public void setFullAddress(String full_address) {
		// TODO Auto-generated method stub
		this.setFull_address(full_address);
	}
	public String getFull_address() {
		return full_address;
	}
	public void setFull_address(String full_address) {
		this.full_address = full_address;
	}
	public String toString() {
		return this.full_address;
	}
}
