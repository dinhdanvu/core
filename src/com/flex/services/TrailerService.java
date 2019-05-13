package com.flex.services;

import java.util.ArrayList;
import java.util.List;

import com.flex.dbmanager.DB;
import com.flex.entities.trailer.TrailerEntity;

public class TrailerService extends DB {

//	private static final FLLogger log = FLLogger.getLogger("db/db");
//	private static final Log log = LogFactory.getLog(RouteService.class); 
	
	/**
	 * get all data in table Routes
	 * 
	 * @return
	 */
	public List getAllTrailerCache(TrailerEntity obj) {
		List dataList = new ArrayList();
		try {
			dataList = queryForList("getAllTrailerCache", obj);
			if (dataList == null)
				return new ArrayList();
		} catch (Exception ex) {
			log.error(ex.getMessage());
			return new ArrayList();
		}
		return dataList;
	}

	public boolean updateStatusSynchTrailer(TrailerEntity obj){
		try{
			update("updateTrailerSynchStatus", obj);
			return true;
		}catch (Exception ex) {
			log.error(ex.getMessage());
			return false;
		}
	}
}
