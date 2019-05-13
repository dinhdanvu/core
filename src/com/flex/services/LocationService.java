/**
 * @author Trần Gia Minh
 */
package com.flex.services;

import java.util.ArrayList;
import java.util.List;

import com.flex.dbmanager.DB;
import com.flex.entities.worker.LocationEntity;
import com.flex.utils.Lib;

/**
 * @author Trần Gia Minh
 *
 */
public class LocationService extends DB {
//	private static final FLLogger log = FLLogger.getLogger("db/db");

	public List<LocationEntity> getAllLocationsByUser(String userName) {
		List<LocationEntity> locationList = new ArrayList<LocationEntity>();
		LocationEntity obj = new LocationEntity();
		obj.setUser_name(userName);
		try {
			locationList = queryForList("getAllLocations", obj);
			if (locationList == null)
				return new ArrayList<LocationEntity>();
		} catch (Exception ex) {
			log.error(ex.getMessage());
			return new ArrayList<LocationEntity>();
		}
		return locationList;
	}
	/**
	 * jincheng
	 * lay danh sach location can synch redis
	 * @param obj
	 * @return
	 */
	public List<LocationEntity> getLocations(LocationEntity obj) {
		List<LocationEntity> locationList = new ArrayList<LocationEntity>();
		if(null ==obj){
			obj = new LocationEntity();
		}
		try {
			locationList = queryForList("getSynchLocations", obj);
			if (locationList == null){
				return new ArrayList<LocationEntity>();
			}
		} catch (Exception ex) {
			log.error(ex.getMessage());
			return new ArrayList<LocationEntity>();
		}
		return locationList;
	}

	/**
	 * jincheng
	 * lay danh sach location can synch redis
	 * @param obj
	 * @return
	 */
	public String getLocationPoits(long loc_id) {
		try {
			Object poits  = queryForObject("getLocationPolyPoits", loc_id);
			if (poits != null){
				return Lib.safeTrim(poits);
			}
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		return "";
	}
	/**
	 * jincheng
	 * cap nhat trang thai synch redis
	 * @param obj
	 * @return
	 */
	public boolean updateStatusSynchLocation(LocationEntity obj){
		try{
			update("updateLocationSynchStatus", obj);
			return true;
		}catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}
}
