package com.flex.services;

import java.util.ArrayList;
import java.util.List;

import com.flex.dbmanager.DB;
import com.flex.entities.DriverEntity;
import com.flex.entities.EmployeeEntity;

public class DriverService extends DB {
//	private static final FLLogger log = FLLogger.getLogger("db/db");
	public List getAllDriverSynch(){
		List dataList = new ArrayList();
		DriverEntity obj = new DriverEntity();
		try {
			dataList = queryForList("getAllDriverSynch",obj);
		} catch (Exception ex) {
			log.error(ex.getMessage());
			return new ArrayList();
		}
		return dataList;
	}
	
	public List getDriverListByUser(){
		List dataList = new ArrayList();
		DriverEntity obj = new DriverEntity();
		try {
			dataList = queryForList("getDriverListByUser",obj);
		} catch (Exception ex) {
			log.error(ex.getMessage());
			return new ArrayList();
		}
		return dataList;
	}


	/**
	 * jincheng
	 * cap nhat trang thai synch redis
	 * @param obj
	 * @return
	 */
	public boolean updateStatusSynchLocation(DriverEntity obj){
		try{
			update("updateDriverSynchStatus", obj);
			return true;
		}catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}
	public boolean insertDriver(DriverEntity obj){
		try
        {
            insert("insertDriver", obj);
            return true;
        }
        catch(Exception ex)
        {
            log.error("insertDriver", ex);
            return false;
        }
	}

/**
 * Đếm số tài xế có licence_no của user A	
 * @author thompt
 * @return
 */
	public DriverEntity getDriverByUserAndLicenceNo(DriverEntity obj){
		DriverEntity driver = new DriverEntity();
		 List<DriverEntity> dataList = new ArrayList();
			try {
				dataList = queryForList("getDriverByUserAndLicenceNo",obj);
				driver = dataList.get(0);
			} catch (Exception ex) {
				log.error(ex.getMessage());
				return null;
			}
			return driver;
	}
	
	/**
	 * thompt
	 * cap nhat tên của tai xe theo tin hieu gui ve
	 * @param obj
	 * @return
	 */
	public boolean updateDriver(DriverEntity obj){
		try{
			update("updateDriver", obj);
			return true;
		}catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}
}
