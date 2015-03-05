package com.hotelOnline.json;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.hotelOnline.domain.Employee;
import com.hotelOnline.domain.Order;

public class EmployeeJson {

	public static Employee handleJson(JSONObject  reader){
		Employee employee=new Employee();
		List<Order> listOrder=new ArrayList<Order>();
		try {
			JSONObject employeeObj=reader.getJSONObject("Employee");
			employee.setId(employeeObj.getString("id"));
			employee.setName(employeeObj.getString("name"));
			employee.setPwd(employeeObj.getString("pwd"));
			JSONArray listOrderObj=employeeObj.getJSONArray("listOrder");
			for(int i=0;i<listOrderObj.length();i++){
				JSONObject orderObj=listOrderObj.getJSONObject(i);
				listOrder.add(OrderJson.handleJson(orderObj));
			}
			employee.setListOrder(listOrder);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return employee;
		
	}
}
