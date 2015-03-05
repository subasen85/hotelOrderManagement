package com.hotelOnline.activity;

import java.net.HttpURLConnection;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.hotelOnline.domain.Customer;
import com.hotelOnline.domain.Employee;
import com.hotelOnline.domain.Order;

public class HotelServiceUtil {
	public final static String AUTHNETICATE_EMP_URL = "http://localhost;8080/hotelOnline-service/employeeService/authenticateEmployee";

	public final static String AUTHNETICATE_CUST_URL = "http://localhost;8080/hotelOnline-service/cutomerService/authenticateCustomer";
	public final static String CREATE_CUST_URL = "http://localhost;8080/hotelOnline-service/cutomerService/createCustomer";
	
	public final static String VIEW_ORDER_ITEMS_URL = "http://localhost;8080/hotelOnline-service/orderService/viewItems";
	
	public final static String UPDATE_ORDER_STATUS = "http://localhost;8080/hotelOnline-service/orderService/updateOrderStatus";

	
	public final static String GET_ITEMS_LIST = "http://localhost;8080/hotelOnline-service/orderService/getItems";
	
	public static String authenticateEmp(Employee emp) {
		String empJson=null;
		try {
			HttpURLConnection conn = ConnectionUtil
					.getPostConnection(AUTHNETICATE_EMP_URL);

			ObjectWriter ow = new ObjectMapper().writer()
					.withDefaultPrettyPrinter();

			conn.getOutputStream().write(ow.writeValueAsString(emp).getBytes());
			conn.connect();
			 empJson = ConnectionUtil.convertStreamToString(conn
					.getInputStream());
			conn.disconnect();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return empJson;

	}

	public static String authenticateCust(Customer cust) {
		String custJson=null;
		try {
			HttpURLConnection conn = ConnectionUtil
					.getPostConnection(AUTHNETICATE_CUST_URL);

			ObjectWriter ow = new ObjectMapper().writer()
					.withDefaultPrettyPrinter();

			conn.getOutputStream().write(ow.writeValueAsString(cust).getBytes());
			conn.connect();
			 custJson = ConnectionUtil.convertStreamToString(conn
					.getInputStream());
			conn.disconnect();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return custJson;

	}

	public static String addCustomer(Customer cust) {
		String custJson=null;
		try {
			HttpURLConnection conn = ConnectionUtil
					.getPostConnection(CREATE_CUST_URL);

			ObjectWriter ow = new ObjectMapper().writer()
					.withDefaultPrettyPrinter();

			conn.getOutputStream().write(ow.writeValueAsString(cust).getBytes());
			conn.connect();
			 custJson = ConnectionUtil.convertStreamToString(conn
					.getInputStream());
			conn.disconnect();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return custJson;

	}
	
	public static String viewItems(Order order) {
		String orderListJson=null;
		try {
			HttpURLConnection conn = ConnectionUtil
					.getPostConnection(VIEW_ORDER_ITEMS_URL);

			ObjectWriter ow = new ObjectMapper().writer()
					.withDefaultPrettyPrinter();

			conn.getOutputStream().write(ow.writeValueAsString(order).getBytes());
			conn.connect();
			 orderListJson = ConnectionUtil.convertStreamToString(conn
					.getInputStream());
			conn.disconnect();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return orderListJson;

	}
	
	public static String changeOrderStatus(Order order) {
		String orderJson=null;
		try {
			HttpURLConnection conn = ConnectionUtil
					.getPostConnection(UPDATE_ORDER_STATUS);

			ObjectWriter ow = new ObjectMapper().writer()
					.withDefaultPrettyPrinter();

			conn.getOutputStream().write(ow.writeValueAsString(order).getBytes());
			conn.connect();
			 orderJson = ConnectionUtil.convertStreamToString(conn
					.getInputStream());
			conn.disconnect();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return orderJson;

	}
	
	public static String getItemsList() {
		String listItemJson=null;
		try {
			HttpURLConnection conn = ConnectionUtil
					.getPostConnection(GET_ITEMS_LIST);

			ObjectWriter ow = new ObjectMapper().writer()
					.withDefaultPrettyPrinter();

			//conn.getOutputStream().write(ow.writeValueAsString(order).getBytes());
			conn.connect();
			listItemJson = ConnectionUtil.convertStreamToString(conn
					.getInputStream());
			conn.disconnect();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listItemJson;

	}



}
