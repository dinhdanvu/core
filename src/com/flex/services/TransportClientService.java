package com.flex.services;

import java.util.ArrayList;
import java.util.List;
import com.flex.dbmanager.DB;
import com.flex.entities.TransportationClientEntity;
import com.flex.entities.VehicleGovermentEntity;


public class TransportClientService extends DB {

	
	/**
	 * Thompt add Lấy dữ liệu từ DB data gửi TCĐB.	
	 * @param obj
	 * @return
	 */
		public List<TransportationClientEntity> getGovermentHistoryData(VehicleGovermentEntity obj) {
			List <TransportationClientEntity> dataList = new ArrayList<TransportationClientEntity>();
			try {
				dataList = queryForList("getGovermentHistoryData", obj);
				if (dataList == null)
					return new ArrayList();
			} catch (Exception ex) {
				log.error(ex.getMessage());
				return new ArrayList();
			}
			return dataList;
		}
	
}
