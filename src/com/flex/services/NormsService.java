package com.flex.services;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.flex.dbmanager.DB;
//import com.google.gson.Gson;
import com.flex.utils.FLLogger;

public class NormsService extends DB {
//	private static final FLLogger log = FLLogger.getLogger("db/db");
//	private static final Log log = LogFactory.getLog(NormsService.class); 
	
	/**
	 * get all data in table devices
	 * 
	 * @return
	 */
	public List getAllNorms() {
		List dataList = new ArrayList();
		try {
			dataList = queryForList("getAllNorms", null);
			if (dataList == null)
				return new ArrayList();
		} catch (Exception ex) {
			log.error(ex.getMessage());
			return new ArrayList();
		}
		return dataList;
	}
	
	public List getNormByImei(String imei){
		List dataList = new ArrayList();
		try{
			dataList = queryForList("getNormsByImei",imei );
		}catch(Exception ex){
			log.error(ex.getMessage());
			return new ArrayList();
		}
		return dataList;
	}
}
