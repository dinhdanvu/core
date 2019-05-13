package com.flex.services;

import java.util.ArrayList;
import java.util.List;

import com.flex.dbmanager.DB;
import com.flex.entities.worker.ExceedspeedEntity;

@SuppressWarnings("rawtypes")
public class ExceedspeedService extends DB {
//	private static final FLLogger log = FLLogger.getLogger("db/db");
	// private static final Log log =
	// LogFactory.getLog(ExceedspeedService.class);

	/**
	 * get all data in table Routes
	 * 
	 * @return
	 */
	public List getAllExceedspeeds(ExceedspeedEntity obj) {
		List dataList = new ArrayList();
		try {
			dataList = queryForList("getExceedspeeds", obj);
			if (dataList == null)
				return new ArrayList();
		} catch (Exception ex) {
			log.error(ex.getMessage());
			return new ArrayList();
		}
		return dataList;
	}

	public ExceedspeedEntity getExceedspeed(ExceedspeedEntity obj) {
		List dataList = new ArrayList();
		ExceedspeedEntity result = null;
		try {
			dataList = queryForList("getExceedspeeds", obj);
			if (dataList != null && dataList.size() > 0)
				result = (ExceedspeedEntity) dataList.get(0);
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		return result;
	}

	public boolean insertExceedspeed(ExceedspeedEntity obj) {
		try {
			insert("insertExceedspeed", obj);
			return true;
		} catch (Exception ex) {
			log.error("insert", ex);
			return false;
		}
	}

	public boolean updateExceedspeed(ExceedspeedEntity obj) {
		try {
			update("updateExceedspeed", obj);
			return true;
		} catch (Exception ex) {
			log.error("update", ex);
			return false;
		}
	}

	// public boolean updateRouteContinueDriving(ExceedspeedEntity obj){
	// try
	// {
	// update("updateRouteContinueDriving", obj);
	// return true;
	// }
	// catch(Exception ex)
	// {
	// log.error("update", ex);
	// return false;
	// }
	// }
	

}
