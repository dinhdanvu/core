package com.flex.services;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;

import com.flex.dbmanager.DB;
import com.flex.entities.worker.TrackingEntity;
import com.flex.utils.Constants;
import com.flex.utils.FLLogger;
import com.mysql.jdbc.CommunicationsException;

public class TrackingService extends DB {
	
//	private static final FLLogger log = FLLogger.getLogger("db/db");
	
	/**
	 * get value by field_name in setting
	 * 
	 * @param key
	 * @return
	 * @throws SQLException
	 */
	public String getValue(String key)  {
		try {
			return (String) queryForObject("getValue", key);
		} catch (Exception ex) {
			return "";
		}
	}

	/**
	 * get last tracking from imei
	 * 
	 * @return
	 */
	public TrackingEntity getLastTrackingByImei(String imei) {
		try {
			Object obj = queryForObject("getLastTrackingByImei", imei);
			if (obj == null)
				return null;
			return (TrackingEntity)obj;
		} catch (Exception ex) {
			log.error(ex.getMessage());
			return null;
		}
	}
	
	public boolean SaveLastTracking(TrackingEntity obj) {
		if(obj.isHas_old_data()){
			return this.UpdateLastTracking(obj); 
		}else{
			return this.InsertLastTracking(obj);
		}
	}

	public boolean InsertLastTracking(TrackingEntity obj) {
		try
        {
			long startSub = java.lang.System.currentTimeMillis();
            insert("insertLastTracking", obj);
			long end = java.lang.System.currentTimeMillis();
			long diff = end - startSub;
			if (diff > Constants.SQL_DEBUG_TIME_TIMEOUT)
				log.debug("insertLastTracking: " + diff); 
            
            Date now = Calendar.getInstance().getTime();
            obj.setCreated_date(now);
            obj.setUpdated_date(now);
            return true;
        }
        catch(Exception ex)
        {
            log.error("insertLastTracking", ex);
            return false;
        }
	}
	
	public boolean UpdateLastTracking(TrackingEntity obj) {
		try
        {
			long startSub = java.lang.System.currentTimeMillis();
            update("updateLastTracking", obj);
			long end = java.lang.System.currentTimeMillis();
			long diff = end - startSub;
			if (diff > Constants.SQL_DEBUG_TIME_TIMEOUT)
				log.debug("UpdateLastTracking: " + diff); 
            
            //Date now = Calendar.getInstance().getTime();
            //obj.setUpdated_date(now);
            return true;
        }
        catch(Exception ex)
        {
        	log.error("updateLastTracking", ex);
            return false;
        }
	}
	
	public boolean insertHistoryData(TrackingEntity obj)  {
		try
        {
			long startSub = java.lang.System.currentTimeMillis();
            insert("insertHistoryData", obj);
			long end = java.lang.System.currentTimeMillis();
			long diff = end - startSub;
			if (diff > 5000)
				log.info("insertTracking: " + diff); 
            
            return true;
        }
        catch(Exception ex)
        {
            log.error("Insert tracking:", ex);
            return false;
        }
	}	

	public boolean UpdateTracking(TrackingEntity obj) {
		try
        {
            update("updateTracking", obj);
            return true;
        }
        catch(Exception ex)
        {
        	log.error("updateTracking", ex);
            return false;
        }
	}
	public String entityToQueryInsert(TrackingEntity obj, int group)
	{
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");		
		String rs = "("
					+"'"+obj.getImei()+"',"
					+"'"+obj.getUser_name()+"',"
					+"'"+ simpleDateFormat.format(obj.getTrk_time()) +"',"
					+obj.getLatitude()+","
					+obj.getLongitude()+","
					+obj.getAltitude()+","
					+obj.getSatellites()+","
					+"0,"
					+obj.getDev_status()+","
					+obj.getVelocity()+","
					+obj.getSpeed()+","
					+obj.getDistance()+","
					+obj.getDirection()+","
					+obj.getBattery_level()+","
					+"'"+obj.getAddress()+"',"
					+obj.getMileage()+","
					+obj.getDriver_code()+","
//					+obj.getInput()+","
//					+obj.getAd1()+","
////					+obj.getDelta_ad1()+","
//					+obj.getAd2()+","
//					+obj.getDelta_ad2()+","
//					+obj.getFlowmeter1()+","
//					+obj.getFlowmeter2()+","
//					+obj.getFlowmeter3()+","
//					+obj.getFlowmeter4()+","
//					+obj.getFlowmeter5()+","
//					+obj.getFlowmeter6()+","
					+"now()"
					+")";
		return rs;
	}
	public boolean insertMultiEntity(String s)
	{
		try
        {			
            insert("runSqlTracking", s);
            return true;
        }
        catch(Exception ex)
        {
            log.error("Insert tracking:", ex);
            return false;
        }
	}

	public boolean selectTest()
	{
		try
        {			
            queryForObject("select_test",null);
            return true;
        }
        catch(Exception ex)
        {
            log.error("Insert tracking:", ex);
            return false;
        }
	}
}
