package com.flex.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.flex.dbmanager.DB;
import com.flex.entities.worker.TrackingEntity;
import com.flex.utils.FLLogger;

public class LastDataService extends DB {
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
	public boolean saveLastData(TrackingEntity obj) {
//		if(obj.isHasOldData()){
//			return this.updateLastData(obj); 
//		}else{
			return this.insertLastData(obj);
//		}
	}
	public boolean insertLastData(TrackingEntity obj) {
		try
        {
            insert("insertTracking", obj);
//            obj.setDevice_id(id);
            return true;
        }
        catch(Exception ex)
        {
            log.error("insert", ex);
            return false;
        }
	}
	public boolean updateLastData(TrackingEntity obj) {
		try
        {
            update("updateTracking", obj);
            return true;
        }
        catch(Exception ex)
        {
        	log.error("update", ex);
            return false;
        }
	}
	/**
	 * get all data in table setting
	 * 
	 * @return
	 */
	public List getAllLastData() {
		List lastDataList = new ArrayList();
		try {
			lastDataList = queryForList("getAlllastData", null);
			if (lastDataList == null)
				return new ArrayList();
		} catch (Exception ex) {
			log.error(ex.getMessage());
			return new ArrayList();
		}
		return lastDataList;
	}

	/**
	 * update setting
	 * 
	 * @param settingE
	 * @return
	 */
	public boolean updateSetting(TrackingEntity trackingE) {
		try {
			int row = update("updateLastData", trackingE);
			if (row <= 0)
				return false;
		} catch (Exception ex) {
			log.error(ex.getMessage());
			return false;
		}
		return true;
	}

	

	

}
