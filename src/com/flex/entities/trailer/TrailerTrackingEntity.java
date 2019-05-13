package com.flex.entities.trailer;

import com.flex.dbmanager.attributes.RedisClassNameAliasAttribute;
import com.flex.dbmanager.attributes.RedisIndexAttribute;
import com.flex.dbmanager.attributes.RedisKeyAttribute;
import com.flex.utils.Constants;
import com.flex.utils.Lib;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
@RedisIndexAttribute(indexs = {"user_name"})
@RedisKeyAttribute(key = "trailer_id")
@RedisClassNameAliasAttribute(name = "Trailer_trk")
public class TrailerTrackingEntity {
	private String db;
    private int trailer_id;
    private String trailer_imei;
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
    private long loc_id;
    private int data_group;

    // =========== tracking ==============
    private int vehicle_id;
    private String vehicle_name;
    private String driver_code;
    private String driver_name;
    private String plate;
    private Date trk_time;
    private double latitude;
    private double longitude;
    private String address;
    private int dev_status;
    private double speed;
    private double distance;
    private int noGPS;
    private int direction;
    private int satellite;
    private int engine;
    private double mileage;
    /**
     * thoi gian cong don cua trang thai hien gio
     */
    private int sum_time_same_dev_status;

    // =========== route ============
    private int route_run_time;
    private int route_stop_time;
    private double route_distance;

    // =========== summary===============
    private int move_time;
    private int stop_time;
    private double summary_distance;
    private int associate_count;
    private int unassociate_count;

    
    // =========== mysql ================
    private List value_list = new LinkedList<TrailerTrackingEntity>();
    /**
     * @return trailer_imei
     */
    public String getTrailer_imei() {
        return trailer_imei;
    }

    /**
     * @param trailer_imei
     */
    public void setTrailer_imei(String trailer_imei) {
        this.trailer_imei = trailer_imei;
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
     * @return the height
     */
    public double getHeight() {
        return height;
    }

    /**
     * @param height the height to set
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

    /**
     * @return the trailer_id
     */
    public int getTrailer_id() {
        return trailer_id;
    }

    /**
     * @param trailer_id the trailer_id to set
     */
    public void setTrailer_id(int trailer_id) {
        this.trailer_id = trailer_id;
    }

    /**
     * @return the loc_id
     */
    public long getLoc_id() {
        return loc_id;
    }

    /**
     * @param loc_id the loc_id to set
     */
    public void setLoc_id(long loc_id) {
        this.loc_id = loc_id;
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
     * @return the vehicle_name
     */
    public String getVehicle_name() {
        return vehicle_name;
    }

    /**
     * @param vehicle_name the vehicle_name to set
     */
    public void setVehicle_name(String vehicle_name) {
        this.vehicle_name = vehicle_name;
    }

    /**
     * @return the driver_code
     */
    public String getDriver_code() {
        return driver_code;
    }

    /**
     * @param driver_code the driver_code to set
     */
    public void setDriver_code(String driver_code) {
        this.driver_code = driver_code;
    }

    /**
     * @return the driver_name
     */
    public String getDriver_name() {
        return driver_name;
    }

    /**
     * @param driver_name the driver_name to set
     */
    public void setDriver_name(String driver_name) {
        this.driver_name = driver_name;
    }

    /**
     * @return the plate
     */
    public String getPlate() {
        return plate;
    }

    /**
     * @param plate the plate to set
     */
    public void setPlate(String plate) {
        this.plate = plate;
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
     * @return the latitude
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * @param latitude the latitude to set
     */
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    /**
     * @return the longitude
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * @param longitude the longitude to set
     */
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the dev_status
     */
    public int getDev_status() {
        return dev_status;
    }

    /**
     * @param dev_status the dev_status to set
     */
    public void setDev_status(int dev_status) {
        this.dev_status = dev_status;
    }

    /**
     * @return the speed
     */
    public double getSpeed() {
        return speed;
    }

    /**
     * @param speed the speed to set
     */
    public void setSpeed(double speed) {
        this.speed = speed;
    }

    /**
     * @return the route_run_time
     */
    public int getRoute_run_time() {
        return route_run_time;
    }

    /**
     * @param route_run_time the route_run_time to set
     */
    public void setRoute_run_time(int route_run_time) {
        this.route_run_time = route_run_time;
    }

    /**
     * @return the route_stop_time
     */
    public int getRoute_stop_time() {
        return route_stop_time;
    }

    /**
     * @param route_stop_time the route_stop_time to set
     */
    public void setRoute_stop_time(int route_stop_time) {
        this.route_stop_time = route_stop_time;
    }

    /**
     * @return the route_distance
     */
    public double getRoute_distance() {
        return route_distance;
    }

    /**
     * @param route_distance the route_distance to set
     */
    public void setRoute_distance(double route_distance) {
        this.route_distance = route_distance;
    }

    /**
     * @return the move_time
     */
    public int getMove_time() {
        return move_time;
    }

    /**
     * @param move_time the move_time to set
     */
    public void setMove_time(int move_time) {
        this.move_time = move_time;
    }

    /**
     * @return the stop_time
     */
    public int getStop_time() {
        return stop_time;
    }

    /**
     * @param stop_time the stop_time to set
     */
    public void setStop_time(int stop_time) {
        this.stop_time = stop_time;
    }

    /**
     * @return the distance
     */
    public double getDistance() {
        return distance;
    }

    /**
     * @param distance the distance to set
     */
    public void setDistance(double distance) {
        this.distance = distance;
    }

    /**
     * @return the associate_count
     */
    public int getAssociate_count() {
        return associate_count;
    }

    /**
     * @param associate_count the associate_count to set
     */
    public void setAssociate_count(int associate_count) {
        this.associate_count = associate_count;
    }
    public int getData_group() {
        return data_group;
    }
    public void setData_group(int data_group) {
        this.data_group = data_group;
    }

    public double getSummary_distance() {
        return summary_distance;
    }

    public void setSummary_distance(double summary_distance) {
        this.summary_distance = summary_distance;
    }

    public int getNoGPS() {
        return noGPS;
    }

    public void setNoGPS(int noGPS) {
        this.noGPS = noGPS;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public int getSatellite() {
        return satellite;
    }

    public void setSatellite(int satellite) {
        this.satellite = satellite;
    }

    public int getEngine() {
        return engine;
    }

    public void setEngine(int engine) {
        this.engine = engine;
    }

    public double getMileage() {
        return mileage;
    }

    public void setMileage(double mileage) {
        this.mileage = mileage;
    }

    public int getSum_time_same_dev_status() {
        return sum_time_same_dev_status;
    }

    public void setSum_time_same_dev_status(int sum_time_same_dev_status) {
        this.sum_time_same_dev_status = sum_time_same_dev_status;
    }
    public String getDb() {
		int year = Lib.getYearByDate(this.trk_time);
		int month = Lib.getMonthByDate(this.trk_time);
		this.db =Constants.DB_PREFIX+year+Lib.padLeft(month+"",2); 
		return db;
	}

	public List getValue_list() {
		return value_list;
	}

	public void setValue_list(List value_list) {
		this.value_list = value_list;
	}

	public int getUnassociate_count() {
		return unassociate_count;
	}

	public void setUnassociate_count(int unassociate_count) {
		this.unassociate_count = unassociate_count;
	}


}