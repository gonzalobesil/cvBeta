package com.example.comoviajarbeta;
import com.example.comoviajarbeta.dao.*;

import com.example.comoviajarbeta.data.*;

import java.util.ArrayList;
import java.util.Collections;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.location.Location;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class PoliciaActivity extends Activity {
	// DAO
	private RutaDataSource dataSource;
	// GPSTracker class
	GPSTracker gps;
	ListView list;
	CustomAdapterRuta adapter;
	public  PoliciaActivity CustomListView = null;
	Button btnEnviar;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.policia_layout);
		
		CustomListView = this;
		
		//savedInstanceState.get
		
		/******** Take some data in Arraylist ( CustomListViewValuesArr ) ***********/

		
		Resources res =getResources(); 
        list=(ListView)findViewById(R.id.list);
        
    	// Importar los elementos contenidos en el layout
        btnEnviar = (Button) findViewById(R.id.btnEnviar);
		
     // Login button Click Event	   			
		btnEnviar.setOnClickListener(new OnClickListener() {

			public void onClick(View view) {
				
				
				
			}
		});	
		
		
		
        
	}

	public void enviarAlerta()
	{


		gps = new GPSTracker(PoliciaActivity.this);

		// check if GPS enabled		
		if(gps.canGetLocation()){

			double latitude = gps.getLatitude();
			double longitude = gps.getLongitude();

			Location location = new Location("");
			location.setLatitude(latitude);
			location.setLongitude(longitude);

				
					

			//					        	// \n is for new line
			//					        	Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude + "\n"+ "distancia: " + (location.distanceTo(dest)/1000), Toast.LENGTH_LONG).show();	
		}else{
			// can't get location
			// GPS or Network is not enabled
			// Ask user to enable GPS/network in settings
			gps.showSettingsAlert();
		}
	}

    
    
   
   

	}