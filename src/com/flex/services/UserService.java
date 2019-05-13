package com.flex.services;

import java.util.ArrayList;
import java.util.List;

import com.flex.dbmanager.DB;
import com.flex.entities.UserEntity;
import com.flex.entities.VehicleEntity;

public class UserService extends DB {
//	private static final FLLogger log = FLLogger.getLogger("db/db");
	public List getAllUserSynch(){
		List dataList = new ArrayList();
		UserEntity obj = new UserEntity();
		try {
			dataList = queryForList("getAllUserSynch",obj);
		} catch (Exception ex) {
			log.error(ex.getMessage());
			return new ArrayList();
		}
		return dataList;
	}
	
	/**
	 * thompt
	 * cap nhat trang thai synch redis
	 * @param obj
	 * @return
	 */
	public boolean updateUserSynchStatus(UserEntity obj){
		try{
			update("updateUserSynchStatus", obj);
			return true;
		}catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}
	
	/**
	 * thompt bổ sung hàm update số tiền phí dịch vụ tin nhắn sau khi gửi sms
	 * @param obj
	 * @return
	 */
	public boolean updateSMSMoney(UserEntity obj){
		try{
			update("updateSMSMoney", obj);
			return true;
		}catch (Exception ex) {
			// TODO: handle exception
			log.error("Loi trong ham updateSMSMoney: \n" + ex.getMessage());
			return false;
		}
	}

	
}
