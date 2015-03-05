package com.hotelOnline.activity;

import java.util.HashSet;
import java.util.Set;

import com.hotelOnline.domain.Customer;
import com.hotelOnline.json.CustomerJson;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class Registration extends ActionBarActivity {
	private EditText username = null;
	private EditText password = null;
	private EditText email = null;
	private Button register;
	public static final String USER_INFO = "userinfo1";
	private SharedPreferences sharedpreferences;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_registration);
		username = (EditText) findViewById(R.id.editText1);
		password = (EditText) findViewById(R.id.editText3);
		email = (EditText) findViewById(R.id.editText4);
		register = (Button) findViewById(R.id.button1);
		register.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				sharedpreferences = getSharedPreferences(USER_INFO,
						Context.MODE_PRIVATE);
				
				Customer cust=new Customer();
				cust.setEmail(email.getText().toString());
				cust.setName(username.getText().toString());
				cust.setPwd(password.getText().toString());
				String custJson=HotelServiceUtil.addCustomer(cust);
				
				if(custJson!=null){
					Intent intent = new Intent();
					intent.putExtra("MESSAGE", "success");
					Toast.makeText(getApplicationContext(),
							"Registartion Successful", Toast.LENGTH_SHORT).show();
					setResult(RESULT_OK, intent);	
				}else{
					Intent intent = new Intent();
					intent.putExtra("MESSAGE", "Failure");
					Toast.makeText(getApplicationContext(),
							"Registartion Failure", Toast.LENGTH_SHORT).show();
					setResult(RESULT_OK, intent);	
				}
				
				
				finish();
			}
		});
	}

}
