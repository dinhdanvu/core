package com.flex.services;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HeaderElement;
import org.apache.http.HeaderElementIterator;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.config.SocketConfig;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeaderElementIterator;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import org.apache.log4j.Logger;

import com.flex.dbmanager.ADAMap.DBMap;
import com.flex.entities.BanDoEntity;
import com.flex.utils.FLLogger;
import com.flex.utils.Lib;

public class BanDoService extends DBMap {
	private static final FLLogger logOPM = FLLogger.getLogger("db/openstreetmapLog");
//	private static final Log log = LogFactory.getLog(DeviceService.class); 
	
	/**
	 * get all data in table devices
	 * 
	 * @return
	 */
	public BanDoEntity getAddress(double lat, double lon) {
		List dataList = new ArrayList();
		BanDoEntity result = null;
		try {
			BanDoEntity obj = new BanDoEntity(lat, lon);
			dataList = queryForList("getAddressBanDo", obj);
			if ((dataList != null) && (dataList.size() > 0))
				result= (BanDoEntity)dataList.get(0);
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		return result;
	}
/**
 * Lấy địa chỉ qua open Street Map, Hàm này chỉ truyền lat, lon 
 * @param lat
 * @param lon
 * @return Object BanDoEntity
 * @author: thompt
 */
	public static BanDoEntity getAddressAdvance(double lat, double lon) {
		
		BanDoEntity result = new BanDoEntity(lat, lon);
		try {
			 JSONObject myjson;
						 
			 String url = "http://192.168.10.156/nominatim/reverse.php?format=jsonv2&zoom=16&lat=" + lat + "&lon=" + lon ; 
			 String objOpenStreetMap = sendRequest(url);	
		
				 String address ="";
											 
				 if(!Lib.isBlank(objOpenStreetMap)){
					 myjson = Lib.str2Json(objOpenStreetMap);
					 address = myjson.getString("display_name");
					 if(Lib.isBlank(address)){
						 logOPM.error("ERROR3: KHONG TON TAI DIA CHI TU OPEN STREET MAP CHO LAT, LON:" + lat +", "+ lon);
					 }
				 }		
				 result.setLatitude(lat);
				 result.setLongitude(lon);		  
				 result.setTenduong(address); 
				 result.setType_4("1");
				 
				 				
			} catch (Exception ex) {
				logOPM.error("ERROR2: LOI LAY DIA CHI TU OPEN STREET MAP CHO LAT, LON:" + lat +", "+ lon + ")\n" +  ex.getMessage());
				return null;
			}
			return result;
			
	}
	
	/**
	 * Lấy địa chỉ qua open Street Map, hàm này truyền OSM dạng tham số url
	 * @param lat
	 * @param lon
	 * @param url: địa chỉ đọc open Street Map
	 * @return Object BanDoEntity
	 * @author: thompt
	 */
	public BanDoEntity getAddressFromOpenStreetMap(double lat, double lon, String url) {
		
			BanDoEntity result = new BanDoEntity(lat, lon);
			if(Lib.isBlank(url)){
				logOPM.error("LOI url_open_street_map bi NULL");
				return null;
			}	
			
			try {
				 JSONObject myjson = null;
				 String address ="";
							 
				 String urlMap = url + "&lat=" + lat + "&lon=" + lon ; 
				 String objOpenStreetMap = sendRequest(urlMap);	
				 
				 if(!Lib.isBlank(objOpenStreetMap)){
					 myjson = Lib.str2Json(objOpenStreetMap);
					 address = myjson.getString("display_name");
					 if(Lib.isBlank(address)){
						 logOPM.error("ERROR3: KHONG TON TAI DIA CHI TU OPEN STREET MAP CHO LAT, LON:" + lat +", "+ lon);
					 }
				 }		
				
				 result.setLatitude(lat);
				 result.setLongitude(lon);		  
				 result.setTenduong(address); 
				 result.setType_4("1");
				 			 
				
			} catch (Exception ex) {
				logOPM.error("ERROR2: LOI LAY DIA CHI TU OPEN STREET MAP CHO LAT, LON:" + lat +", "+ lon + ")\n" +  ex.getMessage());
				return null;
			}
			return result;
		}
	
	 public static void main(String[] args) 
	    {
	        String asd = "http://192.168.10.156/nominatim/reverse.php?format=jsonv2&lat=10.7794204&lon=106.6879234";

	        String address = getOSMAddressByPoolConnection(asd);
	        System.out.println(address);
		 
//		 System.out.println(getAddressAdvance(10.90786000,106.66060500).getFullAddress());
//		 System.out.println(getAddressAdvance(10.90727700,106.65836700).getFullAddress());
//		 System.out.println(getAddressAdvance(10.90730500,106.65604200).getFullAddress());
//		 System.out.println(getAddressAdvance(10.90780700,106.65369200).getFullAddress());
//		 System.out.println(getAddressAdvance(10.90812300,106.65215000).getFullAddress());
//		 System.out.println(getAddressAdvance(10.90847500,106.65011800).getFullAddress());
//		 System.out.println(getAddressAdvance(10.90866200,106.64890700).getFullAddress());

	    }
	
/**
 * Hàm gửi url tới server để nhận dữ liệu	
 * @param url
 * @return
 */
	public static String sendRequest(String url) {
        return HttpRequest.get(url).body();
    }
	
	public static String getOSMAddressByPoolConnection(PoolingHttpClientConnectionManager connManager, CloseableHttpClient client, String url) 
    {
		String address ="";		
        try 
        {
        	HttpGet httpget = new HttpGet(url);
        	HttpResponse response = client.execute(httpget);	
        	InputStream ips = response.getEntity().getContent();
            BufferedReader buf = new BufferedReader(new InputStreamReader(ips,"UTF-8"));
            StringBuilder sb = new StringBuilder();
            String s;
            while (true) {
                s = buf.readLine();
                if (s == null || s.length() == 0)
                    break;
                sb.append(s);
            }
           String  result = sb.toString();
             
            System.out.println(result);
            JSONObject myjson = null;			
			 if(!Lib.isBlank(result)){
				 myjson = Lib.str2Json(result);
				 address = myjson.getString("display_name");
				 if(Lib.isBlank(address)){
					 logOPM.error("ERROR3: KHONG TON TAI DIA CHI TU OPEN STREET MAP CHO LAT, LON:" + url);
				 }
			 }		
            
        } 
        catch (Exception e) {
            logOPM.error("ERROR2: LOI LAY DIA CHI TU OPEN STREET MAP CHO LAT, LON:" + url + "\n" +  e.getMessage());
			return null;
        }
        return address;
    }   

	/**
	 * Hàm test thử lấy địa chỉ từ OSM qua Pool connection;
	 * @param url
	 * @return
	 */
	public static String getOSMAddressByPoolConnection(String url) 
    {
		ConnectionKeepAliveStrategy myStrategy = new ConnectionKeepAliveStrategy() {
		    @Override
		    public long getKeepAliveDuration(HttpResponse response, HttpContext context) {
		        HeaderElementIterator it = new BasicHeaderElementIterator
		            (response.headerIterator(HTTP.CONN_KEEP_ALIVE));
		        while (it.hasNext()) {
		            HeaderElement he = it.nextElement();
		            String param = he.getName();
		            String value = he.getValue();
		            if (value != null && param.equalsIgnoreCase
		               ("timeout")) {
		                return Long.parseLong(value) * 1000;
		            }
		        }
		        return 5 * 1000; // keepalive time = 5
		    }
		};
		
		// Tạo ra một Connection Manager
		PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager();
		// Setsố lượng kết nối đồng thời tối đa trên mỗi tuyến, theo mặc định là 2.
		connManager.setDefaultMaxPerRoute(5);
		// Set số lượng kết nối mở tối đa là 5
		connManager.setMaxTotal(5);
				
		
		CloseableHttpClient client = HttpClients.custom().setConnectionManager(connManager).setConnectionManagerShared(true).build();
		String address ="";		
		
        try 
        {
        	HttpGet httpget = new HttpGet(url);
        	HttpResponse response = client.execute(httpget);	
        	InputStream ips = response.getEntity().getContent();
            BufferedReader buf = new BufferedReader(new InputStreamReader(ips,"UTF-8"));
            StringBuilder sb = new StringBuilder();
            String s;
            while (true) {
                s = buf.readLine();
                if (s == null || s.length() == 0)
                    break;
                sb.append(s);
            }
           String  result = sb.toString();
             
            System.out.println(result);
            JSONObject myjson = null;			
			 if(!Lib.isBlank(result)){
				 myjson = Lib.str2Json(result);
				 address = myjson.getString("display_name");
				 if(Lib.isBlank(address)){
					 logOPM.error("ERROR3: KHONG TON TAI DIA CHI TU OPEN STREET MAP CHO LAT, LON:" + url);
				 }
			 }		
            
        } 
        catch (Exception e) {
            logOPM.error("ERROR2: LOI LAY DIA CHI TU OPEN STREET MAP CHO LAT, LON:" + url + "\n" +  e.getMessage());
			return null;
        }
        return address;
    }   
}
