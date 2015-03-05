package com.hotelOnline.json;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.hotelOnline.domain.Item;

public class ItemJson {
	public static Item handleJson(JSONObject  reader){
		Item item=new Item();
		try {
			JSONObject itemObj=reader.getJSONObject("Item");
			item.setId(itemObj.getInt("id"));
			item.setName(itemObj.getString("name"));
			item.setPrice(itemObj.getDouble("price"));
			item.setQty(itemObj.getInt("qty"));
		} catch (JSONException e) {
		Log.e("Error", "Error while parsing Item Object",e);
		}
		return item;
		
	}
	
	public static List<Item> handleJsonList(JSONObject  reader){
		
		List<Item> listItem=new ArrayList<Item>();
		try {
			JSONArray jsonArray=reader.getJSONArray("listItem");
			JSONObject itemObj=reader.getJSONObject("Item");
			for(int i=0;i<jsonArray.length();i++){
				Item item=new Item();
				item.setId(itemObj.getInt("id"));
				item.setName(itemObj.getString("name"));
				item.setPrice(itemObj.getDouble("price"));
				item.setQty(itemObj.getInt("qty"));
				listItem.add(item);
			}
			
		} catch (JSONException e) {
		Log.e("Error", "Error while parsing Item Object",e);
		}
		return listItem;
		
	}
}
