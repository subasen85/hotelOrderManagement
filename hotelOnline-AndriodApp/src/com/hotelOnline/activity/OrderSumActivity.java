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
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.hotelOnline.domain.Customer;
import com.hotelOnline.domain.Item;
import com.hotelOnline.domain.Order;
import com.hotelOnline.json.CustomerJson;
import com.hotelOnline.json.ItemJson;
import com.hotelOnline.json.OrderJson;

public class OrderSumActivity extends ActionBarActivity {
	private TableLayout orderSumLayout;
	private TextView welcomeText;
	private TextView totalCost;
	private Button confirmButton;
	private Button cancelButton;
	private Double totCost=(double) 0;
	private SharedPreferences sharedpreferences = getSharedPreferences(
			"MyPref", Context.MODE_PRIVATE);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_order_sum);
		welcomeText = (TextView) findViewById(R.id.welcomeText);
		totalCost = (TextView) findViewById(R.id.totalCost);
		confirmButton = (Button) findViewById(R.id.confirm);
		cancelButton = (Button) findViewById(R.id.cancel);
		// set welcome text here
		welcomeText.setText("Welcome Sen");
		orderSumLayout = (TableLayout) findViewById(R.id.selectItems_table);
		TableRow tr_head = new TableRow(this);
		tr_head.setId(10);
		tr_head.setBackgroundColor(Color.GRAY);

		tr_head.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT));
		// Add headers to the table
		TextView label_Items = new TextView(this);
		label_Items.setId(20);
		label_Items.setText("Items");
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
		label_qty.setText("Cost"); // set the text for the header
		label_qty.setTextColor(Color.WHITE); // set the color
		label_qty.setPadding(5, 5, 5, 5); // set the padding (if required)
		tr_head.addView(label_select);// add the column to the table row here

		// Add the header to tableLayout
		orderSumLayout.addView(tr_head, new TableLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));

		final String custJson = sharedpreferences.getString("UserInSession", null);
		Customer cust = null;
		try {
			cust = CustomerJson.handleJson(new JSONObject(custJson));
			// setting the welcome text
			welcomeText.setText(cust.getName());
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		final String listSelectedItemJson = this.getIntent().getStringExtra(
				"listSelectedItem");
		List<Item> listSelectedItem = new ArrayList<Item>();
		try {
			listSelectedItem = ItemJson.handleJsonList(new JSONObject(
					listSelectedItemJson));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (final Item item : listSelectedItem) {
			TableRow tr_row = new TableRow(this);
			tr_row.setId(item.getId());
			tr_row.setBackgroundColor(Color.BLUE);

			tr_row.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
					LayoutParams.WRAP_CONTENT));

			// set the itemName
			TextView itemName = new TextView(this);
			itemName.setId(item.getId());
			itemName.setText(item.getName());
			tr_row.addView(itemName);

			// set the price
			TextView itemPrice = new TextView(this);
			itemPrice.setId(item.getId());
			itemPrice.setText(item.getPrice() + "");
			tr_row.addView(itemPrice);

			// set the QTY edit boc
			TextView itemQty = new EditText(this);
			itemQty.setId(item.getId());
			itemQty.setText(item.getQty() + "");
			tr_row.addView(itemQty);

			// set the Cost here
			TextView itemCost = new EditText(this);
			itemCost.setId(item.getId());
			totCost=totCost+item.getQty() * item.getPrice();
			itemCost.setText(item.getQty() * item.getPrice() + "");
			tr_row.addView(itemQty);

			// Add the header to tableLayout
			orderSumLayout.addView(tr_row, new TableLayout.LayoutParams(
					LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
			totalCost.setText(totCost+"");

		}

		confirmButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Order order = new Order();
				List<Item> listFinalSelectedItem;
				Order finalOrder = null;
				
				
				try {
					Customer finalCust = CustomerJson.handleJson(new JSONObject(custJson));
					finalCust = CustomerJson.handleJson(new JSONObject(custJson));
					order.setUser(finalCust);
					listFinalSelectedItem = ItemJson
							.handleJsonList(new JSONObject(listSelectedItemJson));

					order.setListItem(listFinalSelectedItem);
					String orderJson = HotelServiceUtil
							.changeOrderStatus(order);
					

					finalOrder = OrderJson
							.handleJson(new JSONObject(orderJson));
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Toast.makeText(getApplicationContext(),
						"Your confirmation number is" + finalOrder.getId(),
						Toast.LENGTH_SHORT).show();

			}

		});
		cancelButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(OrderSumActivity.this,
						ListViewActivity.class);
				startActivity(intent);

			}

		});

	}

}
