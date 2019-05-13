package com.flex.services;

import java.util.ArrayList;
import java.util.List;
import com.flex.dbmanager.DB;
import com.flex.entities.VehicleGovermentEntity;


public class VehicleGovermentService extends DB {

	
	/**
	 * Thompt add Lấy danh sách phương tiện có cấu hình gửi TCĐB.	
	 * @param obj
	 * @return
	 */
		public List<VehicleGovermentEntity> getAllGovermentDevices(VehicleGovermentEntity obj) {
			List <VehicleGovermentEntity> dataList = new ArrayList<VehicleGovermentEntity>();
			try {
				dataList = queryForList("getGovermentDevices", obj);
				if (dataList == null)
					return new ArrayList();
			} catch (Exception ex) {
				log.error(ex.getMessage());
				return new ArrayList();
			}
			return dataList;
		}
	
}
