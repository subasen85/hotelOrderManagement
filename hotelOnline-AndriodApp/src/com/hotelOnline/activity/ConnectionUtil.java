package com.hotelOnline.activity;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONObject;

import android.util.Log;

public class ConnectionUtil {
	
	
	public static HttpURLConnection getPostConnection(String urlString) throws Exception{
		URL url = new URL(urlString);
		HttpURLConnection conn = (HttpURLConnection) url
				.openConnection();
		conn.setReadTimeout(10000 /* milliseconds */);
		conn.setConnectTimeout(15000 /* milliseconds */);
		conn.setRequestMethod("POST");
		conn.setDoInput(true);
		
		return conn;
	}
	
	
	public static String convertStreamToString(java.io.InputStream is) {
		java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
		StringBuilder builder = new StringBuilder();
		while (s.hasNext()) {
			builder.append(s.next());
		}
		return builder.toString();
	}
}
