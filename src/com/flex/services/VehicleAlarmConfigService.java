package com.flex.services;

import java.util.ArrayList;
import java.util.List;

import com.flex.dbmanager.DB;
import com.flex.entities.VehicleAlarmConfigEntity;

public class VehicleAlarmConfigService extends DB {

//	private static final FLLogger log = FLLogger.getLogger("db/db");
//	private static final Log log = LogFactory.getLog(RouteService.class); 
	
	/**
	 * get all data in table Routes
	 * 
	 * @return
	 */
	public List<VehicleAlarmConfigEntity> getAllVehicleAlarmCfg(VehicleAlarmConfigEntity obj) {
		List<VehicleAlarmConfigEntity> dataList = new ArrayList<VehicleAlarmConfigEntity>();
		try {
			dataList = queryForList("getAllVehicleAlarmCfg", obj);
			if (dataList == null)
			{
			//	log.error("getAllVehicleAlarmCfg tra ve NULL");
				return new ArrayList<VehicleAlarmConfigEntity>();
			}
				
		} catch (Exception ex) {
			log.error("getAllVehicleAlarmCfg",ex);
			return new ArrayList<VehicleAlarmConfigEntity>();
		}
		return dataList;
	}

	public boolean updateStatusSynchAlarmCfg(VehicleAlarmConfigEntity obj){
		try{
			update("updateVehicleAlarmCfgSyncStatus", obj);
		//	log.error("updateVehicleAlarmCfgSyncStatus da thanh cong");
			return true;
		}catch (Exception e) {
			// TODO: handle exception
			log.error("updateVehicleAlarmCfgSyncStatus",e);
			return false;
		}
	}

}
