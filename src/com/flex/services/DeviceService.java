package com.flex.services;

import java.util.ArrayList;
import java.util.List;

import com.flex.dbmanager.DB;
import com.flex.entities.DeviceActiveEntity;
import com.flex.entities.DeviceEntity;

public class DeviceService extends DB {
//	private static final FLLogger log = FLLogger.getLogger("db/db");
//	private static final Log log = LogFactory.getLog(DeviceService.class); 
// getAllActiveDevices	

	/**
	 * get all imei in table devices
	 * 
	 * @return
	 */
	public List getAllActiveDevices() {
		List dataList = new ArrayList();
		try {
			DeviceActiveEntity obj = new DeviceActiveEntity();

			dataList = queryForList("getAllActiveDevices", obj);
			if (dataList == null)
				return new ArrayList();
		} catch (Exception ex) {
			log.error("LOI KHONG LAY DUOC DANH SACH IMEIS TRONG DB");
			log.error(ex.getMessage());
			return new ArrayList();
		}
		return dataList;
	}
	
	
	
	/**
	 * get all data in table devices
	 * 
	 * @return
	 */
	public List getAllDevices() {
		List dataList = new ArrayList();
		try {
			DeviceEntity obj = new DeviceEntity();
//			obj.setUser_name("v3");
//			log.error("vo");
			dataList = queryForList("getAllDevices", obj);
			if (dataList == null)
				return new ArrayList();
		} catch (Exception ex) {
			log.error(ex.getMessage());
			return new ArrayList();
		}
		return dataList;
	}
	public List getAllDevicesReport() {
		List dataList = new ArrayList();
		try {
			DeviceEntity obj = new DeviceEntity();
			dataList = queryForList("getAllDevicesReport", obj);
			if (dataList == null)
				return new ArrayList();
		} catch (Exception ex) {
			log.error(ex.getMessage());
			return new ArrayList();
		}
		return dataList;
	}
	public boolean insertData(DeviceEntity obj) {
		try
        {
            insert("insertDevice", obj).toString();
            return true;
        }
        catch(Exception ex)
        {
            log.error("insert", ex);
            return false;
        }
	}
	public boolean updateData(DeviceEntity obj) {
		try
        {
            update("updateDevice", obj);
            return true;
        }
        catch(Exception ex)
        {
        	log.error("update", ex);
            return false;
        }
	}

	/**
	 * update setting
	 * 
	 * @param settingE
	 * @return
	 */
	public boolean updateDevice(DeviceEntity objE) {
		try {
			int row = update("updateDevice", objE);
			if (row <= 0)
				return false;
		} catch (Exception ex) {
			log.error(ex.getMessage());
			return false;
		}
		return true;
	}

	

	

}
