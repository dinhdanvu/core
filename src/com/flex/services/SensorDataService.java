package com.flex.services;

import java.sql.SQLException;
import java.util.List;

import com.flex.dbmanager.DB;
import com.flex.entities.SensorDataEntity;
import com.flex.utils.Constants;

public class SensorDataService extends DB {
	public SensorDataEntity getLastSensorData(SensorDataEntity obj)  {
		try {
			List dataList = queryForList("getLastSensor", obj);
			if (dataList != null&&dataList.size()>0)
				return (SensorDataEntity)dataList.get(0);
			return null;
		} catch (Exception ex) {
			return null;
		}
	}

	public SensorDataEntity getFirstSensorData(SensorDataEntity obj)  {
		try {
			List dataList = queryForList("getFirstSensor", obj);
			if (dataList != null&&dataList.size()>0)
				return (SensorDataEntity)dataList.get(0);
			return null;
		} catch (Exception ex) {
			return null;
		}
	}
	
	public boolean insertSensorData(SensorDataEntity obj) {
		try
        {
			long startSub = java.lang.System.currentTimeMillis();
            insert("insertSensorData", obj);
			long end = java.lang.System.currentTimeMillis();
			long diff = end - startSub;
			if (diff > Constants.SQL_DEBUG_TIME_TIMEOUT)
				log.debug("insertSensorData: " + diff); 
            
            return true;
        }
        catch(Exception ex)
        {
            log.error("insertSensorData:", ex);
            return false;
        }
	}	
	public boolean insertSensorDataSingle(SensorDataEntity obj) {
		try
        {
			long startSub = java.lang.System.currentTimeMillis();
            insert("insertSensorDataSingle", obj);
			long end = java.lang.System.currentTimeMillis();
			long diff = end - startSub;
			if (diff > Constants.SQL_DEBUG_TIME_TIMEOUT)
				log.debug("insertSensorData: " + diff); 
            
            return true;
        }
        catch(Exception ex)
        {
            log.error("insertSensorData:", ex);
            return false;
        }
	}	

}
