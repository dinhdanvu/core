package com.flex.entities.redis;

import com.flex.dbmanager.attributes.RedisClassNameAliasAttribute;
import com.flex.dbmanager.attributes.RedisIndexAttribute;
import com.flex.dbmanager.attributes.RedisKeyAttribute;
import com.flex.utils.Lib;

@RedisIndexAttribute(indexs={"unitID"})
@RedisKeyAttribute(key="unitID")
@RedisClassNameAliasAttribute(name="DD")

public class DeviceDataEntity{
	
	private String cmd;
	private String unitID;
	private int posKind;
	private String trkTime;
	private double longitude;
	private double latitude;
	private double altitude;
	private double speed;
	private String speedHistory;
	private int heading;
	private int satellite;
	private int reportID;
	private String deviceStatus;
	private int batteryLevel;
//	private String cellID;
	private double mileage;
	private double accTimeOn;
	private double vPulse;
	private double ad1;
	private double ad2;
	private double flw1;
	private double flw1Real;
	private double flw2;
	private double flw2Real;
	private double flw3;
	private double flw3Real;
	private double flw4;
	private double flw4Real;
	private double flw5;
	private double flw5Real;
	private double flw6;
	private double flw6Real;
	private String driver_code;
	private String driver_name;
	private String strTracking;
	
	public static DeviceDataEntity convertToDeviceData(String dataInput)
	{
		DeviceDataEntity objDD = new DeviceDataEntity();
		
		//lưu lại chuổi ban đầu trước khi phân giải để làm DD false
		objDD.setStrTracking(dataInput);
		 
		String[] data = dataInput.split(":");
		
		String[] data1 = data[0].split(",");
		//Command
		objDD.setCmd(data1[0]);
		if (objDD.getCmd().equals("RP") || objDD.getCmd().equals("AP"))
		{	
			//UnitID, Imei
			objDD.setUnitID(data1[1]);
			
			String[] data2 = data[1].split(",");
			//PosKind
			objDD.setPosKind(Lib.strToInteger(data2[0]));
			//DateTime
			objDD.setTrkTime(data2[1]);
			//Lontitude
			objDD.setLongitude(Lib.strToDouble(data2[2].substring(1)));
			//Latitude
			objDD.setLatitude(Lib.strToDouble(data2[3].substring(1)));
			//Altitude
			objDD.setAltitude(Lib.strToDouble("0" + data2[4]));
			//Speed
			objDD.setSpeed(Lib.strToInteger(data2[5]));
			//Heading
			objDD.setHeading(Lib.strToInteger(data2[6]));
			//Satellite
			objDD.setSatellite(Lib.strToInteger(data2[7]));
			//Report ID
			objDD.setReportID(Lib.strToInteger(data2[8]));
			//Device Status
			objDD.setDeviceStatus(data2[9]);
			//Battery Level
			objDD.setBatteryLevel(Lib.strToInteger("0" + data2[10]));
			//Cell ID
//			objDD.setCellID(data2[11]);

//			#12#3>0.000-325,#13#4>2.632-2369-0-0,#14#7>-500,#15#9>10-1-4095,#16#16>45371-0.00-53523-0.00-8249-0.00-8249-0.00-11104-0.00,#17#9>2-1-0-209
//			%RP,2181304665:1,150611095019,E000.000000,N00.000000,,0,0,0,0,00-00-80-08-00-00-02-00,,0000-0000-45201,3>28383.015-3672769,4>28384.394-25545955-0-0,9>10-2-4095,9>2-1-0-64
//			3>28383.015-3672769,4>28384.394-25545955-0-0,9>10-2-4095,9>2-1-0-64
			//%RP,1208202633:9,160215165930,E106.778782,N10.866700,,0,0,12,0,00-00-00-00-00-00-00-00,,4efc-c06d-45201,3>14222.576-2741722,4>0.000-0-0-0,9>10-2-4095,9>2-1-0-192,17>0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0
			for (int i = 12; i < data2.length; i++)
			{
				int idx = data2[i].indexOf(">")+1;
				String sub_str = data2[i].substring(0,idx);
				int keyIdx = Lib.strToInteger(sub_str);
				switch (keyIdx) {
					case 6:	// 6>1-DID0002
						String[] last_element =  data2[i].split("-");
						if(last_element.length <= 1) break;
						if (last_element.length > 2)
                        {
							String driver_code = last_element[1];
							String driver_name = last_element[2];
							objDD.setDriver_code(driver_code);
							objDD.setDriver_name(driver_name);
                        }else
                        {
                        	String driver_code =last_element[1];
							objDD.setDriver_code(driver_code);
                        }
					break;
					case 3: //3>28383.015-3672769, mileage_gps
						double mileage = objDD.getValuePosOption(data2[i], 0);
						double accTimeOn = objDD.getValuePosOption(data2[i], 1);
						objDD.setMileage(mileage);
						objDD.setAccTimeOn(accTimeOn);
						break;
					case 4: //4>28384.394-25545955-0-0
						double flw1 = objDD.getValuePosOption(data2[i], 1);
						double flw1Real = objDD.getValuePosOption(data2[i], 2);
						double vPulse = objDD.getValuePosOption(data2[i], 3);//van toc xung
						//20160616 vận tốc lấy theo gps
						objDD.setVPulse(vPulse);
						//20160616 dùng cho mileage sensor
						objDD.setFlw1(flw1);
						//20160616 vận tốc lấy theo sensor
						objDD.setFlw1Real(flw1Real);
						break;
					case 9: 
						if(objDD.getValuePosOption(data2[i], 0)==10){
							//9>10-2-4095
							double ad1 = objDD.getValuePosOption(data2[i], 1);
							double ad2 = objDD.getValuePosOption(data2[i], 2);
							objDD.setAd1(ad1);
							objDD.setAd2(ad2);
						}else{
							//9>2-1-0-64
							//20160708 @khanh: Chưa sử dung
							/*double flw2 = objDD.getValuePosOption(data2[i], 0);
							double flw2Real = objDD.getValuePosOption(data2[i], 1);
							double flw3 = objDD.getValuePosOption(data2[i], 2);
							double flw3Real = objDD.getValuePosOption(data2[i], 3);
							double flw4 = objDD.getValuePosOption(data2[i], 4);
							double flw4Real = objDD.getValuePosOption(data2[i], 5);
							double flw5 = objDD.getValuePosOption(data2[i], 6);
							double flw5Real = objDD.getValuePosOption(data2[i], 7);
							double flw6 = objDD.getValuePosOption(data2[i], 8);
							double flw6Real = objDD.getValuePosOption(data2[i], 9);
							objDD.setFlw2(flw2);
							objDD.setFlw2Real(flw2Real);
							objDD.setFlw3(flw3);
							objDD.setFlw3Real(flw3Real);
							objDD.setFlw4(flw4);
							objDD.setFlw4Real(flw4Real);
							objDD.setFlw5(flw5);
							objDD.setFlw5Real(flw5Real);
							objDD.setFlw6(flw6);
							objDD.setFlw6Real(flw6Real);*/
						}
						break;
					case 16://17>0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0 speed history
						objDD.setSpeedHistory(data2[i]);
						break;	
					case 17://17>0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0-0 speed history
						String[] p = data2[i].split(">");
						//20160708 @khanh: bỏ chuỗi "0-" đầu tiên
						if((p.length > 1) && (p[1].length() > 2))
							objDD.setSpeedHistory(p[1].substring(2, p[1].length()));
						break;

				}
				
//				if (!strPosOption.equals(""))
//					strPosOption += ",";
//				strPosOption += data2[i];
			}
//			objDD.set_pos_option(strPosOption);
			/*
			  	[6/15/15, 13:09:48] jincheng: 4>2.632{x1}-2369{x1 van toc}-0-0
				[6/15/15, 13:11:26] jincheng: 9>10-1{ad1}-4095{ad2}
				[6/15/15, 13:13:33] jincheng: 16>45371{x2}-0.00{x2 tuc thoi}-53523{x3}-0.00{x3 tuc thoi}-8249{x4}-0.00{x4 tuc thoi}-8249{x5}-0.00{x5 tuc thoi}-11104{x6}-0.00{x6 tuc thoi}
				[6/15/15, 13:14:43] jincheng: 3>0.000{mileage}-325{van toc}
			 */
			
		}
		else
			return null;
		
		return objDD;
	}
	
	public String getCmd() {
		return cmd;
	}

	public void setCmd(String cmd) {
		this.cmd = cmd;
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

	public String getStrTracking() {
		return strTracking;
	}

	public void setStrTracking(String strTracking) {
		this.strTracking = strTracking;
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

	public String getSpeedHistory() {
		return speedHistory;
	}

	public void setSpeedHistory(String speedHistory) {
		this.speedHistory = speedHistory;
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

	public int getBatteryLevel() {
		return batteryLevel;
	}

	public void setBatteryLevel(int batteryLevel) {
		this.batteryLevel = batteryLevel;
	}

//	public String getCellID() {
//		return cellID;
//	}
//
//	public void setCellID(String cellID) {
//		this.cellID = cellID;
//	}

	private double getValuePosOption(String posOption, int key){
		String[] p = posOption.split(">");
		if(p.length <= 1)
			return 0;
		String[] subP = p[1].split("-");
		if(key >= subP.length)
			return 0;
		return Lib.strToDouble(subP[key]);
	}

	public double getAccTimeOn() {
		return accTimeOn;
	}

	public void setAccTimeOn(double accTimeOn) {
		this.accTimeOn = accTimeOn;
	}

	public double getMileage() {
		return mileage;
	}

	public void setMileage(double mileage) {
		this.mileage = mileage;
	}
	public double getVPulse() {
		return vPulse;
	}

	public void setVPulse(double vPulse) {
		this.vPulse = vPulse;
	}

	public double getFlw1() {
		return flw1;
	}

	public void setFlw1(double flw1) {
		this.flw1 = flw1;
	}

	public double getFlw1Real() {
		return flw1Real;
	}

	public void setFlw1Real(double flw1Real) {
		this.flw1Real = flw1Real;
	}

	public double getFlw2() {
		return flw2;
	}

	public void setFlw2(double flw2) {
		this.flw2 = flw2;
	}

	public double getFlw2Real() {
		return flw2Real;
	}

	public void setFlw2Real(double flw2Real) {
		this.flw2Real = flw2Real;
	}

	public double getFlw3() {
		return flw3;
	}

	public void setFlw3(double flw3) {
		this.flw3 = flw3;
	}

	public double getFlw3Real() {
		return flw3Real;
	}

	public void setFlw3Real(double flw3Real) {
		this.flw3Real = flw3Real;
	}

	public double getFlw4() {
		return flw4;
	}

	public void setFlw4(double flw4) {
		this.flw4 = flw4;
	}

	public double getFlw4_real() {
		return flw4Real;
	}

	public void setFlw4Real(double flw4Real) {
		this.flw4Real = flw4Real;
	}

	public double getFlw5() {
		return flw5;
	}

	public void setFlw5(double flw5) {
		this.flw5 = flw5;
	}

	public double getFlw5Real() {
		return flw5Real;
	}

	public void setFlw5Real(double flw5Real) {
		this.flw5Real = flw5Real;
	}

	public double getFlw6() {
		return flw6;
	}

	public void setFlw6(double flw6) {
		this.flw6 = flw6;
	}

	public double getFlw6Real() {
		return flw6Real;
	}

	public void setFlw6Real(double flw6Real) {
		this.flw6Real = flw6Real;
	}

	public double getAd1() {
		return ad1;
	}

	public void setAd1(double ad1) {
		this.ad1 = ad1;
	}

	public double getAd2() {
		return ad2;
	}

	public void setAd2(double ad2) {
		this.ad2 = ad2;
	}

	public String getDriver_code() {
		return driver_code;
	}

	public void setDriver_code(String driver_code) {
		this.driver_code = driver_code;
	}

	public String getDriver_name() {
		return driver_name;
	}

	public void setDriver_name(String driver_name) {
		this.driver_name = driver_name;
	}
}
