package com.example.comoviajarbeta;

import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import com.example.comoviajarbeta.dao.TipoAlertaDataSource;
import com.example.comoviajarbeta.data.Alerta;
import com.example.comoviajarbeta.data.TipoAlerta;
import com.google.gson.Gson;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.view.Menu;
import android.widget.ListView;

public class TipoAlertasActivity extends Activity {
	// DAO
	private TipoAlertaDataSource dataSource;
	ListView list;
	CustomAdapterTipoAlerta adapter;
	public  TipoAlertasActivity CustomListView = null;
	private static String KEY_SUCCESS = "success";
	public  ArrayList<TipoAlerta> CustomListViewValuesArr = new ArrayList<TipoAlerta>();
	GPSTracker gps;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tipoalertas);
		
		CustomListView = this;
		
		
			//savedInstanceState.get
			
		/******** Take some data in Arraylist ( CustomListViewValuesArr ) ***********/
		setListData();
		
		Resources res =getResources(); 
        list=(ListView)findViewById(R.id.list);
        
        /**************** Create Custom Adapter *********/
        adapter = new CustomAdapterTipoAlerta(CustomListView, CustomListViewValuesArr,res);
        list.setAdapter(adapter);
        
        
		gps = new GPSTracker(TipoAlertasActivity.this);
		String latitud="0";
		String longitud="0";
		// check if GPS enabled		
		if(gps.canGetLocation())
		{
			latitud = String.valueOf(gps.getLatitude());
			longitud = String.valueOf(gps.getLongitude());

		}
        Funciones userFunction = new Funciones();
		JSONObject json = userFunction.busquedaAlertas(latitud,longitud);
		
	

		// check for login response
		try {
			if (json.getString(KEY_SUCCESS) != null) {
				//loginErrorMsg.setText("");
				Gson gson = new Gson();
				Alerta[] t = gson.fromJson(json.getString("alertas"), Alerta[].class);
				t.toString();

//				String res = json.getString(KEY_SUCCESS); 
//				if(Integer.parseInt(res) == 1){
//					// user successfully logged in
//					
//					// Close Login Screen
//					//finish();
//				}else{
//					// Error in login
//					//loginErrorMsg.setText("Incorrect username/password");
//				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
        
	}


	
	
	/****** Function to set data in ArrayList *************/
    public void setListData()
    {
    	
		// Create DAO object
		dataSource = new TipoAlertaDataSource(this);

		for (TipoAlerta a : dataSource.getTipoAlerta()) {
	   
			/******** Take Model Object in ArrayList **********/
			CustomListViewValuesArr.add(a);
		}
		
    }
    
    public void onItemClick(int mPosition)
    {
    	  /*TipoAlerta tempValues = (TipoAlerta) CustomListViewValuesArr.get(mPosition);
    	  Intent i = new Intent(getApplicationContext(), EstacionDetalleActivity.class);
    	  i.putExtra("estacionId", tempValues.getId());
    	  startActivity(i);*/
    }

}
