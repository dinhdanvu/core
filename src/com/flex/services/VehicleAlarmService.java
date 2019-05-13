package com.flex.services;

import com.flex.dbmanager.DB;
import com.flex.entities.VehicleAlarmEntity;
import com.flex.entities.worker.TrackingEntity;
import com.flex.utils.Constants;

public class VehicleAlarmService extends DB {

//	private static final FLLogger log = FLLogger.getLogger("db/db");
//	private static final Log log = LogFactory.getLog(RouteService.class); 
	
	/**
	 * get all data in table Routes
	 * 
	 * @return
	 */

	public boolean insertVehicleAlarm(VehicleAlarmEntity obj) {
		try
        {
			long startSub = java.lang.System.currentTimeMillis();
            insert("insertVehicleAlarm", obj);
			long end = java.lang.System.currentTimeMillis();
			long diff = end - startSub;
			if (diff > Constants.SQL_DEBUG_TIME_TIMEOUT)
				log.debug("insertVehicleAlarm: " + diff); 
            return true;
        }
        catch(Exception ex)
        {
            log.error("insertVehicleAlarm", ex);
            return false;
        }
	}
	/** thompt bổ sung hàm update các alarm 
	 * Sử dụng trong các service sms và email
	 * @param obj
	 * @return
	 */
	public boolean UpdateVehicleAlarmSMS(VehicleAlarmEntity obj) {
		try
        {
            update("updateVehicleAlarmSMS", obj);
            return true;
        }
        catch(Exception ex)
        {
        	log.error("updateVehicleAlarmSMS", ex);
            return false;
        }
	}
	public boolean UpdateVehicleAlarmEmail(VehicleAlarmEntity obj) {
		try
        {
            update("updateVehicleAlarmEmail", obj);
            return true;
        }
        catch(Exception ex)
        {
        	log.error("updateVehicleAlarmEmail", ex);
            return false;
        }
	}

}
