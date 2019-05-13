package com.flex.services;

import com.flex.dbmanager.DB;
import com.flex.utils.Constants;

public class TrailerSummaryService extends DB implements IReportDataServices {
	@Override
	public <T> boolean insert(T obj) {
		try
        {
			long startSub = java.lang.System.currentTimeMillis();
            insert("insertTrailerSummaryData", obj);
			long end = java.lang.System.currentTimeMillis();
			long diff = end - startSub;
			if (diff > Constants.SQL_DEBUG_TIME_TIMEOUT)
				log.debug("insertTrailerSummaryData: " + diff); 
            return true;
        }
        catch(Exception ex)
        {
            log.error("insertTrailerSummaryData", ex);
            return false;
        }
	}

	
	
}
