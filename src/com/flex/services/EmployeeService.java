package com.flex.services;

import com.flex.dbmanager.DB;
import com.flex.entities.EmployeeEntity;
import com.flex.entities.EmployeeEntity;

import java.util.ArrayList;
import java.util.List;

public class EmployeeService extends DB {
//	private static final FLLogger log = FLLogger.getLogger("db/db");
	public List getAllEmployeeSynch(){
		List dataList = new ArrayList();
		EmployeeEntity obj = new EmployeeEntity();
		try {
			dataList = queryForList("getAllEmployeeSynch",obj);
		} catch (Exception ex) {
			log.error(ex.getMessage());
			return new ArrayList();
		}
		return dataList;
	}
	
	public List getEmployeeListByUser(){
		List dataList = new ArrayList();
		EmployeeEntity obj = new EmployeeEntity();
		try {
			dataList = queryForList("getEmployeeListByUser",obj);
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
	public boolean updateStatusSynchLocation(EmployeeEntity obj){
		try{
			update("updateEmployeeSynchStatus", obj);
			return true;
		}catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}
	public boolean insertEmployee(EmployeeEntity obj){
		try
		{
			insert("insertEmployee", obj);
			return true;
		}
		catch(Exception ex)
		{
			log.error("insertEmployee", ex);
			return false;
		}
	}

/**
 * Đếm số employee
 * @author thompt
 * @return
 */
	public EmployeeEntity getEmployeeByEmp_no(int emp_no){
		EmployeeEntity Employee = new EmployeeEntity();
		 List<EmployeeEntity> dataList = new ArrayList();
			try {
				dataList = queryForList("getEmployeeByEmp_no",emp_no);
				Employee = dataList.get(0);
			} catch (Exception ex) {
				log.error(ex.getMessage());
				return null;
			}
			return Employee;
	}
	
	/**
	 * thompt
	 * cap nhat tên của tai xe theo tin hieu gui ve
	 * @param obj
	 * @return
	 */
	public boolean updateEmployee(EmployeeEntity obj){
		try{
			update("updateEmployee", obj);
			return true;
		}catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}
}
