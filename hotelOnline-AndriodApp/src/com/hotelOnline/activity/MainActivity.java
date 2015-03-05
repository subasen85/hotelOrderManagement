package com.hotelOnline.activity;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.hotelOnline.domain.Customer;
import com.hotelOnline.domain.Employee;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class MainActivity extends ActionBarActivity {

	private EditText username = null;
	private EditText password = null;
	private TextView attempts;
	private Button signin;
	private Button signup;
	int counter = 3;
	public static final String USER_INFO = "userinfo1";
	// user session management
	private SharedPreferences sharedpreferences = getSharedPreferences(
			"MyPref", Context.MODE_PRIVATE);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		username = (EditText) findViewById(R.id.editText1);
		password = (EditText) findViewById(R.id.editText2);
		attempts = (TextView) findViewById(R.id.textView5);
		attempts.setText(Integer.toString(counter));
		signin = (Button) findViewById(R.id.button1);
		signup = (Button) findViewById(R.id.button2);
		signup.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(MainActivity.this,
						Registration.class);
				startActivityForResult(intent, 2);// Activity is started with
													// requestCode 2
			}
		});

	}

	// Call Back method to get the Message form other Activity
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		// check if the request code is same as what is passed here it is 2
		if (requestCode == 2) {
			String message = data.getStringExtra("MESSAGE");
			if ("success".equalsIgnoreCase(message)) {
				signup.setEnabled(false);

			} else {
				signup.setEnabled(true);
			}

		}

	}

	public void login(View view) {

		String userNameStr = username.getText().toString();
		String passwordStr = password.getText().toString();
		if (userNameStr.startsWith("EMP")) {
			// if a user name is prefixed with EMP then he is an employee so
			// present him a "cookView"
			Employee emp = new Employee();
			emp.setId(userNameStr);
			emp.setPwd(passwordStr);
			String empJson = HotelServiceUtil.authenticateEmp(emp);

			if (empJson != null) {
				Editor editor = sharedpreferences.edit();
				editor.putString("UserInSession", empJson);

				editor.commit();
				Intent intent = new Intent(MainActivity.this,
						WorkQueueActivity.class);
				startActivity(intent);
			} else {
				this.decrementCounter();
			}

		} else if (userNameStr.startsWith("CUS")) {
			// if a user name is prefixed with CUS then he is an employee so
			// present him a "ListView"
			Customer cus = new Customer();
			cus.setId(userNameStr);
			cus.setPwd(passwordStr);
			String cusJson = HotelServiceUtil.authenticateCust(cus);
			if (cusJson != null) {
				// present him the list view
				Editor editor = sharedpreferences.edit();
				editor.putString("UserInSession", cusJson);
				editor.commit();

				Intent intent = new Intent(MainActivity.this,
						ListViewActivity.class);
				
				startActivity(intent);
			} else {
				this.decrementCounter();
			}

		} else {
			// invalid userName
			this.decrementCounter();

		}
	}

	private void decrementCounter() {
		Toast.makeText(getApplicationContext(), "Invalid userName/password",
				Toast.LENGTH_SHORT).show();
		attempts.setBackgroundColor(Color.RED);
		counter--;
		attempts.setText(Integer.toString(counter));
		if (counter == 0) {
			signin.setEnabled(false);
		}
	}

}
