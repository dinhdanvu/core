package com.flex.services;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.flex.dbmanager.redis.IRedisProvider;
import com.flex.entities.worker.TrackingEntity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class DataService {
	
	public void saveToRedisLastTracking(TrackingEntity data,String imei, IRedisProvider redisProvider)
	{
		String json = new Gson().toJson(data);
		SimpleDateFormat formatDateRP = new SimpleDateFormat("yyyyMMddHHmmss");
		HashMap<String, String> hmap = new HashMap<String, String>();
		hmap.put(formatDateRP.format(data.getTrk_time()), json);
		redisProvider.mSetHash("tracking_" + imei,hmap);
	}
	public List<TrackingEntity> getListLastTracking(String imei, IRedisProvider redisProvider)
	{
		Type type = new TypeToken<TrackingEntity>() {
		}.getType();
		Map<String, String> map = redisProvider.hGetAll("tracking_" + imei);
		List<TrackingEntity> rs = new ArrayList<>();
		for (Map.Entry<String, String> entry : map.entrySet())
		{
			rs.add((TrackingEntity) new Gson().fromJson(entry.getValue(), type));
		}
		redisProvider.deleteKeys("tracking_" + imei);	
		return rs;
	}
}
