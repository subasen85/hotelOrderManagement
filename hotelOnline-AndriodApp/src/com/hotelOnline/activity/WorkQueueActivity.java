package com.hotelOnline.activity;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.JsonReader;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.hotelOnline.domain.Employee;
import com.hotelOnline.domain.Order;
import com.hotelOnline.json.EmployeeJson;
import com.hotelOnline.json.ItemJson;
import com.hotelOnline.json.OrderJson;

public class WorkQueueActivity extends ActionBarActivity {

	private TableLayout workQueueLayout;
	private TextView welcomeText;
	private SharedPreferences sharedpreferences = getSharedPreferences(
			"MyPref", Context.MODE_PRIVATE);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_work_queue);
		welcomeText=(TextView) findViewById(R.id.welcomeText);
		workQueueLayout = (TableLayout) findViewById(R.id.workQueue_table);
		 TableRow tr_head = new TableRow(this);
		 tr_head.setId(10);
		 tr_head.setBackgroundColor(Color.GRAY);
		
		 tr_head.setLayoutParams(new LayoutParams(
				 LayoutParams.MATCH_PARENT,
				 LayoutParams.WRAP_CONTENT));
		 //Add headers to the table
		 TextView label_Items = new TextView(this);
		 label_Items.setId(20);
		 label_Items.setText("Order");
		 label_Items.setTextColor(Color.WHITE);
		 label_Items.setPadding(5, 5, 5, 5);
        tr_head.addView(label_Items);// add the column to the table row here

        TextView label_Details = new TextView(this);
        label_Details.setId(21);// define id that must be unique
        label_Details.setText("Select"); // set the text for the header 
        label_Details.setTextColor(Color.WHITE); // set the color
        label_Details.setPadding(5, 5, 5, 5); // set the padding (if required)
        tr_head.addView(label_Details);// add the column to the table row here
        
        
        TextView label_Ready = new TextView(this);
        label_Ready.setId(21);// define id that must be unique
        label_Ready.setText("Select"); // set the text for the header 
        label_Ready.setTextColor(Color.WHITE); // set the color
        label_Ready.setPadding(5, 5, 5, 5); // set the padding (if required)
        tr_head.addView(label_Ready);// add the column to the table row here
        
       
        //Add the header to tableLayout
        workQueueLayout.addView(tr_head, new TableLayout.LayoutParams(
                LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT));
       
        
        //Add the entries based on the number of items in the list
        
        String empJson=sharedpreferences.getString("UserInSession",null);
       Employee emp = null;
	try {
		emp = EmployeeJson.handleJson(new JSONObject(empJson));
		welcomeText.setText(emp.getName());
	} catch (JSONException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
       for(final Order order:emp.getListOrder()){
    	   TableRow tr_row = new TableRow(this);
           tr_row.setId(order.getId());
           tr_row.setBackgroundColor(Color.BLUE);
   		
           tr_row.setLayoutParams(new LayoutParams(
   				 LayoutParams.MATCH_PARENT,
   				 LayoutParams.WRAP_CONTENT));
           
           Button viewItems=new Button(this);
           viewItems.setId(order.getId());
           viewItems.setText("View Items");
           viewItems.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String listItems=HotelServiceUtil.viewItems(order);
				try {
					ItemJson.handleJson(new JSONObject(listItems));
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
								
			}
		});
           
           tr_row.addView(viewItems);
           
           
           Button orderReady=new Button(this);
           viewItems.setId(order.getId());
           viewItems.setText("Order Ready");
           viewItems.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String orderJson=HotelServiceUtil.changeOrderStatus(order);
				try {
					OrderJson.handleJson(new JSONObject(orderJson));
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
								
			}
		});
           
           tr_row.addView(orderReady);
         //Add the header to tableLayout
           workQueueLayout.addView(tr_row, new TableLayout.LayoutParams(
                   LayoutParams.MATCH_PARENT,
                   LayoutParams.WRAP_CONTENT));
          
    	   
       }
        
		 
        
		
        
	}

	
}
