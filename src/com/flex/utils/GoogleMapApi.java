package com.flex.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.TextUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.flex.entities.GoogleAddressEntity;


public class GoogleMapApi {
	private static final Log log = LogFactory.getLog(GoogleMapApi.class); 
	public static JSONObject getJSONfromURL(String url) {

        // initialize
        InputStream is = null;
        String result = "";
        JSONObject jObject = null;
    
        // http post
        try {
            HttpClient httpclient = new DefaultHttpClient();
            
            HttpPost httppost =  new HttpPost(url);
            
            HttpResponse response = httpclient.execute(httppost);
            HttpEntity entity = response.getEntity();
            is = entity.getContent();

        } catch (Exception e) {
            log.error("Error in http connection" ,e);
        }

        // convert response to string
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "utf8"), 8);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            is.close();
            result = sb.toString();
        } catch (Exception e) {
            log.error( "Error converting result " , e);
        }

        // try parse the string to a JSON object
        
        try {
			jObject = new JSONObject(result);
		} catch (JSONException e) {
			return null;
		}
        return jObject;
    }
	
	public GoogleAddressEntity getAddress(double lat,double lon) {
       
		GoogleAddressEntity addressE=new GoogleAddressEntity();
        try {
        	
            JSONObject jsonObj = getJSONfromURL("http://maps.googleapis.com/maps/api/geocode/json?latlng=" +lat + ","
                    + lon + "&sensor=true");
            String Status = jsonObj.getString("status");
            if (Status.equalsIgnoreCase("OK")) {
                JSONArray Results = jsonObj.getJSONArray("results");
                JSONObject zero = Results.getJSONObject(0);
                String full_address=zero.getString("formatted_address");
                addressE.setFullAddress(full_address);
                JSONArray address_components = zero.getJSONArray("address_components");

                //ThanhLe 24/05/2016
                //http://maps.googleapis.com/maps/api/geocode/json?latlng=10.7757902145,106.7595367432&sensor=true
                //link trên cho ra nhiều địa chỉ sao lại dùng for khi các json lại có kết quả khác nhau
                //thực ra chọn cái nào? 
                //dùng for là chọn cuối cùng vậy for chi?
                for (int i = 0; i < address_components.length(); i++) {
                    JSONObject zero2 = address_components.getJSONObject(i);
                    String long_name = zero2.getString("long_name");
                    JSONArray mtypes = zero2.getJSONArray("types");
                    String Type = mtypes.getString(0);

                    if (TextUtils.isEmpty(long_name) == false || !long_name.equals(null) || long_name.length() > 0 || long_name != "") {
                        if (Type.equalsIgnoreCase("street_number")) {
                        	addressE.setAddress(long_name + " ");
                        } else if (Type.equalsIgnoreCase("route")) {
                           
                            addressE.setAddress(addressE.getAddress() + long_name);
                        } 
                        else if (Type.equalsIgnoreCase("sublocality_level_1")) {
                            
                            addressE.setWard(long_name);
                        } 
                        else if (Type.equalsIgnoreCase("locality")) {
                            // Address2 = Address2 + long_name + ", ";
                           
                            addressE.setCity(long_name);
                        } else if (Type.equalsIgnoreCase("administrative_area_level_2")) {
                            addressE.setDistrict(long_name);
                        } else if (Type.equalsIgnoreCase("administrative_area_level_1")) {
                           
                            addressE.setCity(long_name);
                        } else if (Type.equalsIgnoreCase("country")) {
                            
                            addressE.setCountry(long_name);
                        } 
//                        else if (Type.equalsIgnoreCase("postal_code")) {
//                            
//                            addressE.setPin(long_name);
//                        }
                    }

                    // JSONArray mtypes = zero2.getJSONArray("types");
                    // String Type = mtypes.getString(0);
                    // Log.e(Type,long_name);
                }
            }

        } catch (Exception e) {
           
        }
return addressE;
    }

  


}
