package com.hotelOnline.json;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.hotelOnline.domain.Customer;
import com.hotelOnline.domain.Item;
import com.hotelOnline.domain.Order;

public class OrderJson {
	public static Order handleJson(JSONObject  reader){
		Order order=new Order();
		List<Item> listItem=new ArrayList<Item>();
		try {
			JSONObject orderObj=reader.getJSONObject("Order");
			
			order.setCost(orderObj.getDouble("cost"));
			order.setId(orderObj.getInt("id"));
			order.setEmployee(EmployeeJson.handleJson(orderObj.getJSONObject("employee")));
			order.setStatus(orderObj.getString("status"));
			order.setUser(CustomerJson.handleJson(orderObj.getJSONObject("user")));
			
			
			JSONArray listItemObj=orderObj.getJSONArray("listItem");
			for(int i=0;i<listItemObj.length();i++){
				JSONObject itemObj=listItemObj.getJSONObject(i);
				listItem.add(ItemJson.handleJson(itemObj));
			}
			order.setListItem(listItem);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			Log.e("Error", "Error while parsing Customer Object",e);
		}
		return order;
	}
	
}
