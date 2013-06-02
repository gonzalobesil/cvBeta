package com.example.comoviajarbeta;
import com.example.comoviajarbeta.dao.*;

import com.example.comoviajarbeta.data.*;

import java.util.ArrayList;


import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class ReportarActivity extends Activity {
	// DAO
	private RutaDataSource dataSource;
	
	ListView list;
	CustomAdapterRuta adapter;
	public  ReportarActivity CustomListView = null;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.reportar_layout);
		
		CustomListView = this;
		
		//savedInstanceState.get
		
		/******** Take some data in Arraylist ( CustomListViewValuesArr ) ***********/

		
		Resources res =getResources(); 
        list=(ListView)findViewById(R.id.list);
        
        
        /**
		 * Creating all buttons instances
		 * */
		// Dashboard News feed button
		Button btn_transito = (Button) findViewById(R.id.btn_transito);

		// Dashboard Friends button
		Button btn_accidente = (Button) findViewById(R.id.btn_accidente);


		// Dashboard Events button
		Button btn_peligro = (Button) findViewById(R.id.btn_peligro);

		// Dashboard Photos button
		Button btn_policia = (Button) findViewById(R.id.btn_policia);

		/**
		 * Handling all button click events
		 * */

		// Listening to News Feed button click
		btn_policia.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View view) {
				// Launching News Feed Screen
				Intent i = new Intent(getApplicationContext(), PoliciaActivity.class);
				startActivity(i);
			}
		});

		// Listening to News Feed button click
		btn_accidente.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View view) {
				// Launching News Feed Screen
				Intent i = new Intent(getApplicationContext(), AccidenteActivity.class);
				startActivity(i);
			}
		});

		// Listening Friends button click
		btn_transito.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View view) {
				// Launching News Feed Screen
				Intent i = new Intent(getApplicationContext(),TransitoActivity.class);
				startActivity(i);
			}
		});
		
		// Listening Friends button click
		btn_peligro.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View view) {
				// Launching News Feed Screen
				Intent i = new Intent(getApplicationContext(), PeligroActivity.class);
				startActivity(i);
			}
		});
		        
        
	}


    
    
   
   

	}