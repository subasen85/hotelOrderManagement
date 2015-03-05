package com.hotelOnline.activity;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.hotelOnline.domain.Customer;
import com.hotelOnline.domain.Item;
import com.hotelOnline.domain.Order;
import com.hotelOnline.json.CustomerJson;
import com.hotelOnline.json.ItemJson;
import com.hotelOnline.json.OrderJson;

public class ListViewActivity extends ActionBarActivity {
	
	private TableLayout selectItemsLayout;
	private TextView welcomeText;
	private Button proceedButton;
	private SharedPreferences sharedpreferences = getSharedPreferences(
			"MyPref", Context.MODE_PRIVATE);
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_view);
		welcomeText=(TextView) findViewById(R.id.welcomeText);
		
		 selectItemsLayout = (TableLayout) findViewById(R.id.selectItems_table);
		 
		 proceedButton=(Button) findViewById(R.id.proceed);
		 TableRow tr_head = new TableRow(this);
		 tr_head.setId(10);
		 tr_head.setBackgroundColor(Color.GRAY);
		
		 tr_head.setLayoutParams(new LayoutParams(
				 LayoutParams.MATCH_PARENT,
				 LayoutParams.WRAP_CONTENT));
		 //Add headers to the table
		 TextView label_Items = new TextView(this);
		 label_Items.setId(20);
		 label_Items.setText("Item Name");
		 label_Items.setTextColor(Color.WHITE);
		 label_Items.setPadding(5, 5, 5, 5);
         tr_head.addView(label_Items);// add the column to the table row here

         TextView label_Price = new TextView(this);
         label_Price.setId(21);// define id that must be unique
         label_Price.setText("Price"); // set the text for the header 
         label_Price.setTextColor(Color.WHITE); // set the color
         label_Price.setPadding(5, 5, 5, 5); // set the padding (if required)
         tr_head.addView(label_Price);// add the column to the table row here
         
         TextView label_qty = new TextView(this);
         label_qty.setId(22);// define id that must be unique
         label_qty.setText("Qty"); // set the text for the header 
         label_qty.setTextColor(Color.WHITE); // set the color
         label_qty.setPadding(5, 5, 5, 5); // set the padding (if required)
         tr_head.addView(label_qty);// add the column to the table row here
         
         TextView label_select = new TextView(this);
         label_qty.setId(23);// define id that must be unique
         label_qty.setText("Select"); // set the text for the header 
         label_qty.setTextColor(Color.WHITE); // set the color
         label_qty.setPadding(5, 5, 5, 5); // set the padding (if required)
         tr_head.addView(label_select);// add the column to the table row here
         
         //Add the header to tableLayout
         selectItemsLayout.addView(tr_head, new TableLayout.LayoutParams(
                 LayoutParams.MATCH_PARENT,
                 LayoutParams.WRAP_CONTENT));
         
         String custJson=sharedpreferences.getString("UserInSession",null);
         Customer cust = null;
  	try {
  		cust = CustomerJson.handleJson(new JSONObject(custJson));
  		//setting the welcome text
  		welcomeText.setText(cust.getName());
  	} catch (JSONException e1) {
  		// TODO Auto-generated catch block
  		e1.printStackTrace();
  	}
  	
  	String listItemsJson=HotelServiceUtil.getItemsList();
  	List<Item> listItem = null;
	try {
		listItem = ItemJson.handleJsonList(new JSONObject(listItemsJson));
	} catch (JSONException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  	
  	 for(final Item item:listItem){
  	   TableRow tr_row = new TableRow(this);
         tr_row.setId(item.getId());
         tr_row.setBackgroundColor(Color.BLUE);
 		
         tr_row.setLayoutParams(new LayoutParams(
 				 LayoutParams.MATCH_PARENT,
 				 LayoutParams.WRAP_CONTENT));
         
         //set the itemName
         TextView itemName=new TextView(this);
         itemName.setId(item.getId());
         itemName.setText(item.getName());
         tr_row.addView(itemName);
         
         //set the price
         TextView itemPrice=new TextView(this);
         itemPrice.setId(item.getId());
         itemPrice.setText(item.getPrice()+"");
         tr_row.addView(itemPrice);
         
         //set the QTY edit boc
         EditText editText=new EditText(this);
         editText.setId(item.getId());
         tr_row.addView(editText);
         
         
         //set Check box value
         CheckBox chkBox=new CheckBox(this);
         chkBox.setId(item.getId());
         tr_row.addView(chkBox);
         
         
         //Add the header to tableLayout
         selectItemsLayout.addView(tr_row, new TableLayout.LayoutParams(
                   LayoutParams.MATCH_PARENT,
                   LayoutParams.WRAP_CONTENT));
		
	}
  	 
  	proceedButton.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			List<Item> listSelectedItem=new ArrayList<Item>();
			selectItemsLayout = (TableLayout) findViewById(R.id.selectItems_table);
			for(int i=0;i<selectItemsLayout.getChildCount();i++){
				if(selectItemsLayout.getChildAt(i) instanceof TableRow){
					TableRow tableRow=(TableRow) selectItemsLayout.getChildAt(i);
					
						//index 3 is checkbox
				CheckBox chkbox=(CheckBox) tableRow.getChildAt(3);
				if(chkbox.isChecked()){
					Item item=new Item();
					item.setId(chkbox.getId());
					TextView itemName=(TextView) tableRow.getChildAt(0);
					item.setName(itemName.getText().toString());
					TextView itemPrice=(TextView) tableRow.getChildAt(1);
					item.setPrice(Double.parseDouble(itemPrice.getText().toString()));
					TextView itemQty=(TextView) tableRow.getChildAt(1);
					item.setQty(Integer.parseInt(itemQty.getText().toString()));
					listSelectedItem.add(item);
				}
					
				}
				
			}
			//move to the confirm order page
			try {
			Intent intent = new Intent(ListViewActivity.this,
					OrderSumActivity.class);
			ObjectWriter ow = new ObjectMapper().writer()
					.withDefaultPrettyPrinter();
			
				intent.putExtra("listSelectedItem", ow.writeValueAsString(listSelectedItem));
				intent.putExtra("userName", ow.writeValueAsString(listSelectedItem));
				startActivity(intent);
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
		}
	});
  	 
  	 

	}
	
}
