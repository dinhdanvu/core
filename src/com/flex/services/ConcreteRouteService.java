package com.flex.services;

import com.flex.dbmanager.DB;
import com.flex.entities.worker.ConcreteRouteEntity;

public class ConcreteRouteService extends DB {
	public boolean insertConcreteRoute(ConcreteRouteEntity obj) {
		try
        {
            insert("insertConcreteRoute", obj);
            return true;
        }
        catch(Exception ex)
        {
            log.error("insertConcreteRoute", ex);
            return false;
        }
	}
}
