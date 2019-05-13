package com.flex.services;

import com.flex.dbmanager.DB;
import com.flex.entities.worker.EventEntity;

public class EventService extends DB {
//	private static final FLLogger log = FLLogger.getLogger("db/db");
	public boolean InserEvent(EventEntity obj) {
		try
        {
            insert("insertEvent", obj);
            return true;
        }
        catch(Exception ex)
        {
            log.error("InserEvent", ex);
            return false;
        }
	}
}
