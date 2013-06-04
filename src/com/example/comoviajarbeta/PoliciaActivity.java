package com.example.comoviajarbeta;
import com.example.comoviajarbeta.dao.*;

import com.example.comoviajarbeta.data.*;
import com.example.comoviajarbeta.utilidades.ReportarFunciones;

import java.util.ArrayList;
import java.util.Collections;

import org.json.JSONException;
import org.json.JSONObject;


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
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class PoliciaActivity extends Activity {
	// DAO
	private RutaDataSource dataSource;
	// JSON Response node names
	private static String KEY_SUCCESS = "success";
	private static String KEY_ERROR = "error";
	private static String KEY_ERROR_MSG = "error_msg";
	private static String KEY_UID = "uid";
	private static String KEY_NAME = "name";
	private static String KEY_EMAIL = "email";
	private static String KEY_CREATED_AT = "created_at";
	// GPSTracker class
	GPSTracker gps;
	ListView list;
	CustomAdapterRuta adapter;
	public  PoliciaActivity CustomListView = null;
	Button btnEnviar;
	EditText textComentario;
	TextView reportarErrorMsg;
	
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
        textComentario = (EditText) findViewById(R.id.textComentario);
        reportarErrorMsg = (TextView) findViewById(R.id.reportar_error);
		
     // Login button Click Event	   			
		btnEnviar.setOnClickListener(new OnClickListener() {

			public void onClick(View view) {
				
				enviarAlerta();
				
			}
		});	
		
		
		
        
	}

	public void enviarAlerta()
	{
		gps = new GPSTracker(PoliciaActivity.this);

		// check if GPS enabled		
		if(gps.canGetLocation()){
			String comentario="";
			String latitud = String.valueOf(gps.getLatitude());
			String longitud = String.valueOf(gps.getLongitude());
			if(textComentario.getText().toString()!="Comentario")
				comentario=textComentario.getText().toString();
			
			//Location location = new Location("");
			//location.setLatitude(latitude);
			//location.setLongitude(longitude);

			ReportarFunciones reportarFunciones = new ReportarFunciones();
			JSONObject json = reportarFunciones.reportar(latitud, longitud,comentario, "1");
			
			// check for login response
			try {
				if (json.getString(KEY_SUCCESS) != null) {
					reportarErrorMsg.setText("");
					String res = json.getString(KEY_SUCCESS); 
					if(Integer.parseInt(res) == 1){
						reportarErrorMsg.setText("Se reportó con exito");
					}else{
						// Error in registration
						reportarErrorMsg.setText("Error al reportar");
					}
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
		
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