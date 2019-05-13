package com.flex.services;

import java.util.ArrayList;
import java.util.List;

import com.flex.dbmanager.DB;
import com.flex.entities.VehicleSensorSettingEntity;

public class VehicleSensorSettingService extends DB {

//	private static final FLLogger log = FLLogger.getLogger("db/db");
//	private static final Log log = LogFactory.getLog(RouteService.class); 
	
	/**
	 * get all data in table Routes
	 * 
	 * @return
	 */
	public List getAllVehicleSensorSetting(VehicleSensorSettingEntity obj) {
		List dataList = new ArrayList();
		try {
			dataList = queryForList("getAllVehicleSensorSetting", obj);
			if (dataList == null)
				return new ArrayList();
		} catch (Exception ex) {
			log.error(ex.getMessage());
			return new ArrayList();
		}
		return dataList;
	}
	public List getVehicleSensorSettingByVehicle(VehicleSensorSettingEntity obj) {
		List dataList = new ArrayList();
		try {
			dataList = queryForList("getAllVehicleSensorSettingByVehicleId", obj);
			if (dataList == null)
				return new ArrayList();
		} catch (Exception ex) {
			log.error(ex.getMessage());
			return new ArrayList();
		}
		return dataList;
	}
	public List getAllKeyListSynchronized() {
		List dataList = new ArrayList();
		try {
			dataList = queryForList("getAllKeyListSynchronized", null);
			if (dataList == null)
				return new ArrayList();
		} catch (Exception ex) {
			log.error(ex.getMessage());
			return new ArrayList();
		}
		return dataList;
	}
	public boolean updateStatusSyncVehicleSensorSetting(VehicleSensorSettingEntity obj){
		try{
			update("updateVehicleSensorSettingSynchStatus", obj);
			return true;
		}catch (Exception e) {
			log.error("updateStatusSyncVehicleSensorSetting",e);
			return false;
		}
	}

	public boolean updateStatusSyncDeviceSensorDefaultSetting(VehicleSensorSettingEntity obj){
		try{
			update("updateDeviceSensorDefaultSettingSynchStatus", obj);
			return true;
		}catch (Exception e) {
			// TODO: handle exception
			log.error("updateStatusSyncDeviceSensorDefaultSetting",e);
			return false;
		}
	}

	public boolean deleteVehicleSensorSetting(VehicleSensorSettingEntity obj){
		try{
			delete("deleteVehicleSensorSetting", obj);
			return true;
		}catch (Exception e) {
			// TODO: handle exception
			log.error("deleteVehicleSensorSetting",e);
			return false;
		}
	}
}
