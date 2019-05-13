package com.flex.services;

import com.flex.dbmanager.DB;
import com.flex.utils.Constants;

public class TrailerRouteService extends DB implements IReportDataServices {
	@Override
	public <T> boolean insert(T obj) {
		try
        {
			long startSub = java.lang.System.currentTimeMillis();
            insert("insertTrailerRouteData", obj);
			long end = java.lang.System.currentTimeMillis();
			long diff = end - startSub;
			if (diff > Constants.SQL_DEBUG_TIME_TIMEOUT)
				log.debug("insertTrailerRouteData: " + diff); 
            return true;
        }
        catch(Exception ex)
        {
            log.error("insertTrailerRouteData", ex);
            return false;
        }
	}

	
	
}
