package com.flex.services;

import java.util.ArrayList;
import java.util.List;

import com.flex.dbmanager.DB;
import com.flex.entities.FeeStationEntity;
import com.flex.entities.RouteFeeStationEntity;
import com.flex.entities.TollStationEntity;

public class FeeStationService extends DB {
	/**
	 * get all data in table Routes
	 * 
	 * @return
	 */
	public List getAllFeeStation(FeeStationEntity obj) {
		List dataList = new ArrayList();
		try {
			dataList = queryForList("getAllFeeStation", obj);
			if (dataList == null)
				return new ArrayList();
		} catch (Exception ex) {
			log.error("getAllFeeStation",ex);
			return new ArrayList();
		}
		return dataList;
	}
	public List getAllTollStation(TollStationEntity obj) {
		List dataList = new ArrayList();
		try {
			dataList = queryForList("getAllTollStation", obj);
			if (dataList == null)
				return new ArrayList();
		} catch (Exception ex) {
			log.error("getAllTollStation",ex);
			return new ArrayList();
		}
		return dataList;
	}

	public boolean updateStatusSynchStatus(FeeStationEntity obj){
		try{
			update("updateFeeStationSyncStatus", obj);
			return true;
		}catch (Exception e) {
			log.error("updateStatusSynchStatus",e);
			return false;
		}
	}
	public boolean updateFeeStationPriceSyncStatus(FeeStationEntity obj){
		try{
			update("updateFeeStationPriceSyncStatus", obj);
			return true;
		}catch (Exception e) {
			log.error("updateFeeStationPriceSyncStatus",e);
			return false;
		}
	}

	public boolean insertRouteFeeStation(RouteFeeStationEntity obj){
		try{
			insert("insertRouteFeestation", obj);
			return true;
		}catch (Exception e) {
			// TODO: handle exception
			log.error("insertRouteFeeStation",e);
			return false;
		}
	}
}
