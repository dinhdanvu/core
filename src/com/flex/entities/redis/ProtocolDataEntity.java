package com.flex.entities.redis;

import com.flex.utils.Lib;

import java.util.Date;

/**
 * Note: 9>10-1-4095,9>2-1-0-209=> 9> thu 2 tinh trang o SD card, hien gio chua su dung
 * 4>0.001-1-0-0 neu he so cau hinh anh huong den gia tri thu 1 va thu 3,
 *
 * @author jincheng
 */
public class ProtocolDataEntity {
    private String deviceType;
    //%RP,1200103050:1,151205211146,E105.708565,N18.828447,,0,0,10,0,
    //00-00-00-00-00-00-00-00,,2deb-f443-45201,3>384.474-0,4>219.949-197954-0-0,9>10-0-4095,
    //16>3471351-0.00-0-0.00-0-0.00-0-0.00-0-0.00,9>2-1-0-209
    //%RP,1192409905:9,160815170800,E106.793773,N10.764958,,0,0,12,0,
    //00-00-80-00-00-00-00-00,,4e36-3ce8-45201,3>6915.825-1772860,4>0.001-1-0-0,6>1-740101001198-Tr?n V?n Duy?t,9>10-1-4095,9>2-1-0-209,
    //17>0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0
    /**
     * KP: Real-time track notify, need to response from center=> %AT+KP=<ReportID>
     * AP: Alarm notify, need to response from center => %AT+KP=<ReportID>
     * EP: Event notify, need to response from center => %AT+EP=<ReportID>
     * MP: Message notify, no need to response from center
     * PP: Appendix location message
     * SN: Synchronous notify, need to response from center => %AT+SN=<SyncKind>,<DeviceKind> [,<DataTime> [,<ProtocolVersion>,<CenterFuncSets>]]
     * PM: Picture information, need to response from center => %AT+PICDATA   or  %AT+PHOTO
     * CA: Authentication code notify
     */
    private String cmd;//RP
    private String rfid;
    private String unitID;//1200103050 imei
    private int posKind;
    private String trkTime;
    private double longitude;//E105.708565
    private double latitude;//N18.828447
    private double altitude;//,,
    private double speed;
    private int heading;
    private int satellite;
    private int gsm_signal;
    private int reportID;
    private String deviceStatus;
    private String cellID;
    private double mileage;//don vi km: 3>384.474-0
    private double accTimeOn;//don vi giay
//	private double totalPulseXFactor;// xung cong don va da nhan he so: 4>219.949-x-x-x
//	private double totalPulse;//xung cong don  chua nhan he so(da chia 4): 4>x-197954-x-x
    /**
     * xung tuc thoi, co the su dung lam van toc xung hoac luu luong
     * cac gia tri xung tuc thoi hien dang su dung lam tin hieu sensor nhien lieu hoac tin hieu nhiet do, do am
     */
//	private double realPulse; 
    private double gpsSpeed;
    private String driver_lisence;//6>1-740101001198-Tr?n V?n Duy?t
    private String driver_name;
    private Date driver_ranged_date;
    private Date driver_expered_date;

    private int protocol_version;

    private String speedHistory;//17>x-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0, trktime ung voi van toc dau tien cua chuoi van toc tung giay, so luong van toc tung gia tuong ung chua ky truyen/giay
    private int batteryLevel;// protocol bo chong
    //	private String strTracking;//chuoi goc
//	private String[] eventCmds = new String[]{"KP","AP","EP","MP","CA"};
    private double[] degitals = new double[50];
    private String basic_sensor_value;
    private String event_content;

    private boolean associate_with_rfid = false;

    ////////////////////// J610 //////////////////////
    private String device_phone;
    private String owner_phone;
    private String device_password;
    private int mileage_distance;
    ////////////////////// J610 //////////////////////
    ///////////////////// Begin DF42x ////////////
    private String data_identifier = "";
    private double data_length;
    private double event_code;
    /**
     * HDOP 
     * The value ranges from 0.5 to 99.9. The smaller the value is, the more the accuracy is.
     * When the accuracy value is 0, the signal is invalid. \
     * 0.5–1: Perfect
     * 2–3: Wonderful
     * 4–6: Good
     * 7–8: Medium
     * 9–20: Below average 21–99.9: Poor 
     */
    private double hdop;
    private double run_time;
    /**
     * Geo-fence number 
     * 32-bit unsigned
     * Only available by GPRS event code 20 or 21. 
     */
    private String base_station_info;
//    private String io_port_status;
    private String assisted_event_info;
    /**
     * Indicates the GPS signal status. 
     * A = Valid
     * V = Invalid
     */
    private String positioning_status;
    private String picture_name;
    private int geo_fence_number;
    private String temperature_sensor_no;
    private String customized_data;
    private String fuel_percentage;
    private String temperature_sensor_no_and_value;
    ///////////////////// End DF42x ////////////
    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    /**
     * KP: Real-time track notify, need to response from center=> %AT+KP=<ReportID>
     * AP: Alarm notify, need to response from center => %AT+KP=<ReportID>
     * EP: Event notify, need to response from center => %AT+EP=<ReportID>
     * MP: Message notify, no need to response from center
     * PP: Appendix location message
     * SN: Synchronous notify, need to response from center => %AT+SN=<SyncKind>,<DeviceKind> [,<DataTime> [,<ProtocolVersion>,<CenterFuncSets>]]
     * PM: Picture information, need to response from center => %AT+PICDATA   or  %AT+PHOTO
     * CA: Authentication code notify
     */
    public String getCmd() {
        return cmd;
    }

    public void setCmd(String cmd) {
        this.cmd = cmd;
    }

    /**
     * KP: Real-time track notify, need to response from center=> %AT+KP=<ReportID>
     * AP: Alarm notify, need to response from center => %AT+KP=<ReportID>
     * EP: Event notify, need to response from center => %AT+EP=<ReportID>
     * MP: Message notify, no need to response from center
     * CA: Authentication code notify
     */
    public boolean isEvent() {
        switch (cmd) {
            case "KP":
            case "AP":
            case "EP":
            case "MP":
            case "CA":
            case "LP":
            case "MG":
                return true;
            default:
                return false;
        }
    }

    /**
     * KP: Real-time track notify, need to response from center=> %AT+KP=<ReportID>
     * AP: Alarm notify, need to response from center => %AT+KP=<ReportID>
     * EP: Event notify, need to response from center => %AT+EP=<ReportID>
     * MP: Message notify, no need to response from center
     * CA: Authentication code notify
     */
    public String getEvent_key() {
        String event_key;
        switch (cmd) {
            case "KP":
                event_key = "real_time_track";
                break;
            case "AP":
                event_key = "alarm_notify";
                break;
            case "EP":
                event_key = "event_notify";
                break;
            case "MP":
                event_key = "message_notify";
                break;
            case "CA":
                event_key = "Authcode_code_notify";
                break;
            case "MG":
                event_key = "route_start_msg";
                break;
            default:
                event_key = "";
                break;
        }
        return event_key;
    }

    public String getUnitID() {
        return unitID;
    }

    public void setUnitID(String unitID) {
        this.unitID = unitID;
    }

    public int getPosKind() {
        return posKind;
    }

    public void setPosKind(int posKind) {
        this.posKind = posKind;
    }

    public String getTrkTime() {
        return trkTime;
    }

    public void setTrkTime(String trkTime) {
        this.trkTime = trkTime;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getAltitude() {
        return altitude;
    }

    public void setAltitude(double altitude) {
        this.altitude = altitude;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public int getHeading() {
        return heading;
    }

    public void setHeading(int heading) {
        this.heading = heading;
    }

    public int getSatellite() {
        return satellite;
    }

    public void setSatellite(int satellite) {
        this.satellite = satellite;
    }

    public int getReportID() {
        return reportID;
    }

    public void setReportID(int reportID) {
        this.reportID = reportID;
    }

    public String getDeviceStatus() {
        return deviceStatus;
    }

    public void setDeviceStatus(String deviceStatus) {
        this.deviceStatus = deviceStatus;
    }

    public String getCellID() {
        return cellID;
    }

    public void setCellID(String cellID) {
        this.cellID = cellID;
    }

    public double getMileage() {
        return mileage;
    }

    public void setMileage(double mileage) {
        this.mileage = mileage;
    }

    public double getAccTimeOn() {
        return accTimeOn;
    }

    public void setAccTimeOn(double accTimeOn) {
        this.accTimeOn = accTimeOn;
    }

    public double getGpsSpeed() {
        return gpsSpeed;
    }

    public void setGpsSpeed(double gpsSpeed) {
        this.gpsSpeed = gpsSpeed;
    }

    public String getDriver_lisence() {
        return driver_lisence;
    }

    public void setDriver_lisence(String driver_lisence) {
        if (!Lib.isBlank(driver_lisence)) {
            driver_lisence = driver_lisence.toUpperCase();
        }
        this.driver_lisence = driver_lisence;
    }

    public String getDriver_name() {
        return driver_name;
    }

    public void setDriver_name(String driver_name) {
        this.driver_name = driver_name;
    }

    public String getSpeedHistory() {
        return speedHistory;
    }

    public void setSpeedHistory(String speedHistory) {
        this.speedHistory = speedHistory;
    }

    public int getBatteryLevel() {
        return batteryLevel;
    }

    public void setBatteryLevel(int batteryLevel) {
        this.batteryLevel = batteryLevel;
    }

    public String getBasic_sensor_value() {
        return basic_sensor_value;
    }

    public void setBasic_sensor_value(String basic_sensor_value) {
        this.basic_sensor_value = basic_sensor_value;
    }

    public double[] getDegitals() {
        return degitals;
    }

    public void setValElementDegitals(double val, int index) {
        try {
            this.degitals[index] = val;
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public String getEvent_content() {
        return event_content;
    }

    public void setEvent_content(String event_content) {
        this.event_content = event_content;
    }

    public int getProtocol_version() {
        return protocol_version;
    }

    public void setProtocol_version(int protocol_version) {
        this.protocol_version = protocol_version;
    }

    public int getGsm_signal() {
        return gsm_signal;
    }

    public void setGsm_signal(int gsm_signal) {
        this.gsm_signal = gsm_signal;
    }

    public Date getDriver_ranged_date() {
        return driver_ranged_date;
    }

    public void setDriver_ranged_date(Date driver_ranged_date) {
        this.driver_ranged_date = driver_ranged_date;
    }

    public Date getDriver_expered_date() {
        return driver_expered_date;
    }

    public void setDriver_expered_date(Date driver_expered_date) {
        this.driver_expered_date = driver_expered_date;
    }

    public String getRfid() {
        return rfid;
    }

    public void setRfid(String rfid) {
        this.rfid = rfid;
    }

    public String getDevice_phone() {
        return device_phone;
    }

    public void setDevice_phone(String device_phone) {
        this.device_phone = device_phone;
    }

    public String getOwner_phone() {
        return owner_phone;
    }

    public void setOwner_phone(String owner_phone) {
        this.owner_phone = owner_phone;
    }

    public String getDevice_password() {
        return device_password;
    }

    public void setDevice_password(String device_password) {
        this.device_password = device_password;
    }

    public boolean isAssociate_with_rfid() {
        return associate_with_rfid;
    }

    public void setAssociate_with_rfid(boolean associate_with_rfid) {
        this.associate_with_rfid = associate_with_rfid;
    }

	public int getMileage_distance() {
		return mileage_distance;
	}

	public void setMileage_distance(int mileage_distance) {
		this.mileage_distance = mileage_distance;
	}

	public String getData_identifier() {
		return data_identifier;
	}

	public void setData_identifier(String data_identifier) {
		this.data_identifier = data_identifier;
	}

	public double getData_length() {
		return data_length;
	}

	public void setData_length(double data_length) {
		this.data_length = data_length;
	}

	public double getEvent_code() {
		return event_code;
	}

	public void setEvent_code(double event_code) {
		this.event_code = event_code;
	}

	public double getHdop() {
		return hdop;
	}

	public void setHdop(double hdop) {
		this.hdop = hdop;
	}

	public double getRun_time() {
		return run_time;
	}

	public void setRun_time(double run_time) {
		this.run_time = run_time;
	}

	public String getAssisted_event_info() {
		return assisted_event_info;
	}

	public void setAssisted_event_info(String assisted_event_info) {
		this.assisted_event_info = assisted_event_info;
	}

	public String getBase_station_info() {
		return base_station_info;
	}

	public void setBase_station_info(String base_station_info) {
		this.base_station_info = base_station_info;
	}

//	public String getIo_port_status() {
//		return io_port_status;
//	}
//
//	public void setIo_port_status(String io_port_status) {
//		this.io_port_status = io_port_status;
//	}

	public String getPositioning_status() {
		return positioning_status;
	}

	public void setPositioning_status(String positioning_status) {
		this.positioning_status = positioning_status;
	}

	public String getPicture_name() {
		return picture_name;
	}

	public void setPicture_name(String picture_name) {
		this.picture_name = picture_name;
	}

	public String getTemperature_sensor_no() {
		return temperature_sensor_no;
	}

	public void setTemperature_sensor_no(String temperature_sensor_no) {
		this.temperature_sensor_no = temperature_sensor_no;
	}

	public String getCustomized_data() {
		return customized_data;
	}

	public void setCustomized_data(String customized_data) {
		this.customized_data = customized_data;
	}

	public String getFuel_percentage() {
		return fuel_percentage;
	}

	public void setFuel_percentage(String fuel_percentage) {
		this.fuel_percentage = fuel_percentage;
	}

	public String getTemperature_sensor_no_and_value() {
		return temperature_sensor_no_and_value;
	}

	public void setTemperature_sensor_no_and_value(String temperature_sensor_no_and_value) {
		this.temperature_sensor_no_and_value = temperature_sensor_no_and_value;
	}

	public int getGeo_fence_number() {
		return geo_fence_number;
	}

	public void setGeo_fence_number(int geo_fence_number) {
		this.geo_fence_number = geo_fence_number;
	}
}