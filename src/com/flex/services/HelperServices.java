package com.flex.services;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.flex.dbmanager.DB;
import com.flex.entities.VehicleEntity;

import com.flex.utils.Constants;
import com.flex.utils.FLLogger;

public class HelperServices extends DB {
	public List<Map> getRedisConfigs(){
		List dataList = new ArrayList();
		try {
			List lst = queryForList("getRedisConfigs", null);
			if (lst == null)
				return new ArrayList();
			for(Object obj : lst){
				try{
					LinkedHashMap map = (LinkedHashMap)obj;
					String pass = (String)map.get("password");
//					pass = AES128Bit.decrypt(pass, Constants.AES_KEY);
					map.put("password", pass);
					dataList.add(map);
				}catch (Exception e) {
					// TODO: handle exception
				}
			}
		} catch (Exception ex) {
			log.error("getRedisConfigs: "+ex.getMessage());
			return new ArrayList();
		}
		return dataList;
	}
}
