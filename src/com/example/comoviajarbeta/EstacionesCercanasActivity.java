package com.example.comoviajarbeta;

import java.util.ArrayList;
import java.util.Collections;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.location.Location;
import android.os.Bundle;
import android.widget.ListView;

import com.example.comoviajarbeta.dao.EstacionDataSource;
import com.example.comoviajarbeta.data.Estacion;

public class EstacionesCercanasActivity extends Activity
{
	// GPSTracker class
	GPSTracker gps;

	// DAO
	private EstacionDataSource dataSource;

	ListView list;
	CustomAdapterEstacionCercana adapter;
	public  EstacionesCercanasActivity CustomListView = null;
	public  ArrayList<Estacion> CustomListViewValuesArr = new ArrayList<Estacion>();


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.estaciones_cercanas_layout);

		CustomListView = this;

		//		Bundle bundle = getIntent().getExtras();
		//		long depto = bundle.getLong("departamentoSeleccionado");

		//savedInstanceState.get

		/******** Take some data in Arraylist ( CustomListViewValuesArr ) ***********/
		seleccionarEstaciones();


		Resources res =getResources(); 
		list=(ListView)findViewById(R.id.list);

		/**************** Create Custom Adapter *********/
		adapter=new CustomAdapterEstacionCercana(CustomListView, CustomListViewValuesArr,res);
		list.setAdapter(adapter);


	}

	public void seleccionarEstaciones()
	{
		// Create DAO object
		dataSource = new EstacionDataSource(this);

		gps = new GPSTracker(EstacionesCercanasActivity.this);

		// check if GPS enabled		
		if(gps.canGetLocation()){

			double latitude = gps.getLatitude();
			double longitude = gps.getLongitude();

			Location location = new Location("");
			location.setLatitude(latitude);
			location.setLongitude(longitude);

			//location.distanceTo(dest);

			for (Estacion e : dataSource.getEstaciones()) {

				Location dest = new Location("");
				dest.setLatitude(Float.parseFloat(e.getLatitud()));
				dest.setLongitude(Float.parseFloat(e.getLongitud()));

				e.setDistancia(location.distanceTo(dest));
				/******** Take Model Object in ArrayList **********/
				CustomListViewValuesArr.add(e);
			}
				
			Collections.sort(CustomListViewValuesArr);
					

			//					        	// \n is for new line
			//					        	Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude + "\n"+ "distancia: " + (location.distanceTo(dest)/1000), Toast.LENGTH_LONG).show();	
		}else{
			// can't get location
			// GPS or Network is not enabled
			// Ask user to enable GPS/network in settings
			gps.showSettingsAlert();
		}
	}




	/****** Function to set data in ArrayList *************/
	public void setListData()
	{

	}

	public void onItemClick(int mPosition)
	{
		Estacion tempValues = (Estacion) CustomListViewValuesArr.get(mPosition);
		Intent i = new Intent(getApplicationContext(), EstacionDetalleActivity.class);
		i.putExtra("estacionId", tempValues.getId());
		startActivity(i);
	}
}
