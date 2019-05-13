package com.flex.services;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.flex.dbmanager.DB;
import com.flex.entities.worker.RouteEntity;
import com.flex.entities.worker.TrackingEntity;

public class RouteService extends DB {
//	private static final FLLogger log = FLLogger.getLogger("db/db");
//	public static final Logger _infoLog = Logger.getLogger("infoLog");
//	private static final Log log = LogFactory.getLog(RouteService.class); 
	
	/**
	 * get all data in table Routes
	 * 
	 * @return
	 */
	public List getAllRoutes(RouteEntity obj) {
		List dataList = new ArrayList();
		try {
			dataList = queryForList("getRoutes", obj);
			if (dataList == null)
				return new ArrayList();
		} catch (Exception ex) {
			log.error(ex.getMessage());
			return new ArrayList();
		}
		return dataList;
	}
	public RouteEntity getRoute(RouteEntity obj) {
		List dataList = new ArrayList();
		RouteEntity result = null;
		try {
			dataList = queryForList("getRoutes", obj);
			if (dataList != null&&dataList.size()>0)
				result= (RouteEntity)dataList.get(0);
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		return result;
	}
	public boolean insertRoute(RouteEntity obj) {
		try
        {
            insert("insertRoute", obj);
            return true;
        }
        catch(Exception ex)
        {
            log.error("insert", ex);
            return false;
        }
	}
	public boolean updateRouteStopToRun(TrackingEntity dataTracking,String device_id,int data_group) {
		try
        {
			RouteEntity obj = new RouteEntity();
			obj.setTo(dataTracking.getTrk_time());
			obj.setRoute_type(1);
			obj.setImei(device_id); 
			obj.setData_group(data_group);
			
            update("updateRouteStopToRun", obj);
            return true;
        }
        catch(Exception ex)
        {
        	log.error("update", ex);
            return false;
        }
	}
	public boolean updateRoute(RouteEntity obj) {
		try
        {
            update("updateRoute", obj);
            return true;
        }
        catch(Exception ex)
        {
        	log.error("update", ex);
            return false;
        }
	}
	public boolean updateRouteContinueDriving(RouteEntity obj){
		try
        {
            update("updateRouteContinueDriving", obj);
            return true;
        }
        catch(Exception ex)
        {
        	log.error("update", ex);
            return false;
        }
	}
	public boolean reOpenRouteContinueDriving(RouteEntity obj){
		try
        {
            int updateId = update("reOpenRouteContinueDriving", obj);
//            System.out.println(updateId);
            if(updateId==0)return false;
            return true;
        }
        catch(Exception ex)
        {
        	log.error("update", ex);
            return false;
        }
	}
	
//	public  RouteEntity createNewRouteFromTracking(TrackingEntity dataTracking, int routeType){
//		return createNewRouteFromTracking(dataTracking, routeType, null, null);
//	}
	
	
	public String entityToQueryInsert (RouteEntity obj,int group)
	{
		//chưa có cách phân biệt là câu query update insert bug thanhlm
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
		String rs ="("
					+"'"+obj.getImei()+"',"
					+"'"+obj.getUser_name()+"',"
					+obj.getRoute_type()+","
					+"'"+ simpleDateFormat.format(obj.getFrom()) +"',"
					+"'"+ simpleDateFormat.format(obj.getTo()) +"',"
					+obj.getDistance()+","
					+"'"+obj.getStart_address()+"',"
					+obj.getStart_latitude()+","
					+obj.getStart_longitude()+","
					+obj.getStart_map_id()+","
					+"'"+obj.getStop_address()+"',"
					+obj.getStop_latitude()+","
					+obj.getStop_longitude()+","
					+obj.getStop_map_id()+","
					+obj.getFuel_signal()+","
					+obj.getAlerted()+","
					+"'"+obj.getDriver_code()+"'"
					+")";
		
		return rs;
	}
	
	
	
//	public void saveRouteStopRunToRedis(String key,RouteEntity o ,RedisProvider redisProvider)
//	{
////		redisProvider.pushRouteStopRunToRedis(key,o);
//		redisProvider.set(key, o, false);
//	}
	public boolean insertMultiEntity(String s)
	{
		try
        {			
            insert("runSqlRouteStopToRun", s);
            return true;
        }
        catch(Exception ex)
        {
            log.error("Insert tracking:", ex);
            return false;
        }
	}
}
