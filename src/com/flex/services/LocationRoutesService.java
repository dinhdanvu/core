/**
 * @author Trần Gia Minh
 */
package com.flex.services;

import java.util.List;

import com.flex.dbmanager.DB;
import com.flex.entities.worker.LocationRoutesEntity;
import com.flex.entities.worker.TrackingEntity;

/**
 * @author Trần Gia Minh
 *
 */
public class LocationRoutesService extends DB {
//	private static final FLLogger log = FLLogger.getLogger("db/db");

	public boolean insertLocationRoutes(LocationRoutesEntity obj) {
		try {
			obj.setCreated_user(obj.getUser_name());
			Object id = insert("insertLocationRoutes", obj);
			if (id != null && id instanceof Integer)
				return ((Integer) id).intValue()>-1;
			return false;
		} catch (Exception ex) {
			log.error("insert", ex);
			return false;
		}
	}

	public boolean updateLocationRoutes(LocationRoutesEntity obj) {
		try {
			obj.setUpdated_user(obj.getUser_name());
			update("updateLocationRoutes", obj);
			return true;
		} catch (Exception ex) {
			log.error("update", ex);
			return false;
		}
	}

	public List getAllLstLocationRoutes() {
		try {
			List lst = queryForList("getAllLstLocationRoutes", null);
			return lst;
		} catch (Exception ex) {
			log.error(ex.getMessage());
			return null;
		}
	}
}
