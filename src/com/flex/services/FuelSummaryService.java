package com.flex.services;

import java.sql.SQLException;
import java.util.List;

import com.flex.dbmanager.DB;
import com.flex.entities.FuelAbnormalyChangeEntity;
import com.flex.entities.SensorDataEntity;
import com.flex.entities.worker.FuelConsumedSummaryEntity;
import com.flex.entities.worker.FuelSummaryEntity;
import com.flex.utils.Constants;

public class FuelSummaryService extends DB {

	
	public boolean insertFuelSummary(FuelSummaryEntity obj) {
		try
        {
			long startSub = java.lang.System.currentTimeMillis();
            insert("insertFuelSummary", obj);
			long end = java.lang.System.currentTimeMillis();
			long diff = end - startSub;
			if (diff > Constants.SQL_DEBUG_TIME_TIMEOUT)
				log.debug("insertFuelSummary: " + diff); 
            
            return true;
        }
        catch(Exception ex)
        {
            log.error("insertFuelSummary:", ex);
            return false;
        }
	}	
	public boolean insertFuelConsumedSummary(FuelConsumedSummaryEntity obj) {
		try
        {
			long startSub = java.lang.System.currentTimeMillis();
            insert("insertFuelConsumedSummary", obj);
			long end = java.lang.System.currentTimeMillis();
			long diff = end - startSub;
			if (diff > Constants.SQL_DEBUG_TIME_TIMEOUT)
				log.debug("insertFuelConsumedSummary: " + diff); 
            
            return true;
        }
        catch(Exception ex)
        {
            log.error("insertFuelConsumedSummary:", ex);
            return false;
        }
	}	
	

}
