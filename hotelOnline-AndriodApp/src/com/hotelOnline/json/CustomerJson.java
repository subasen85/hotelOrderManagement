package com.hotelOnline.json;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.hotelOnline.domain.Customer;
import com.hotelOnline.domain.Order;

public class CustomerJson {

	
	public static Customer handleJson(JSONObject  reader){
		Customer customer=new Customer();
		try {
			List<Order> listOrder=new ArrayList<Order>();
			JSONObject customerObj=reader.getJSONObject("Customer");
			
			customer.setEmail(customerObj.getString("email"));
			customer.setId(customerObj.getString("id"));
			customer.setName(customerObj.getString("name"));
			customer.setPwd(customerObj.getString("pwd"));
			JSONArray listOrderObj=customerObj.getJSONArray("listOrder");
			for(int i=0;i<listOrderObj.length();i++){
				JSONObject orderObj=listOrderObj.getJSONObject(i);
				listOrder.add(OrderJson.handleJson(orderObj));
			}
			customer.setListOrder(listOrder);
			
		} catch (JSONException e) {
			Log.e("Error", "Error while parsing Customer Object",e);
		}
		return customer;
		
	}
}
