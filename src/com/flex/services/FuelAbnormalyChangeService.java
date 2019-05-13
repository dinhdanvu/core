package com.flex.services;

import java.sql.SQLException;
import java.util.List;

import com.flex.dbmanager.DB;
import com.flex.entities.FuelAbnormalyChangeEntity;
import com.flex.entities.SensorDataEntity;
import com.flex.utils.Constants;

public class FuelAbnormalyChangeService extends DB {

	
	public boolean insertFuelAbnormalyChangeData(FuelAbnormalyChangeEntity obj) {
		try
        {
			long startSub = java.lang.System.currentTimeMillis();
            insert("insertFuelAbnormalyChange", obj);
			long end = java.lang.System.currentTimeMillis();
			long diff = end - startSub;
			if (diff > Constants.SQL_DEBUG_TIME_TIMEOUT)
				log.debug("insertFuelAbnormalyChangeData: " + diff); 
            
            return true;
        }
        catch(Exception ex)
        {
            log.error("insertFuelAbnormalyChangeData:", ex);
            return false;
        }
	}	

}
