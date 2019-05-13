package com.flex.services;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.flex.dbmanager.DB;
import com.flex.entities.VehicleEntity;
import com.flex.entities.VehicleGovermentEntity;
import com.flex.utils.FLLogger;

public class VehicleService extends DB {

//	private static final FLLogger log = FLLogger.getLogger("db/db");
//	private static final Log log = LogFactory.getLog(RouteService.class); 
	
	/**
	 * get all data in table Routes
	 * 
	 * @return
	 */
	public List getAllDevicesCache(VehicleEntity obj) {
		List dataList = new ArrayList();
		try {
			dataList = queryForList("getAllDevicesCache", obj);
			if (dataList == null)
				return new ArrayList();
		} catch (Exception ex) {
			log.error(ex.getMessage());
			return new ArrayList();
		}
		return dataList;
	}
	public List getAllDevices(VehicleEntity obj) {
		List dataList = new ArrayList();
		try {
			dataList = queryForList("getAllDevices", obj);
			if (dataList == null)
				return new ArrayList();
		} catch (Exception ex) {
			log.error(ex.getMessage());
			return new ArrayList();
		}
		return dataList;
	}

	public boolean updateStatusSynchDevice(VehicleEntity obj){
		try{
			update("updateDeviceSynchStatus", obj);
			return true;
		}catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}
	
	public boolean updateStatusSynchVehicle(VehicleEntity obj){
		try{
			update("updateVehicleSynchStatus", obj);
			return true;
		}catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}

	
}
