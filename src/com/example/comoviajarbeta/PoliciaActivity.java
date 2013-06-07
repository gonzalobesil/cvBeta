package com.example.comoviajarbeta;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.comoviajarbeta.dao.RutaDataSource;
import com.example.comoviajarbeta.utilidades.ReportarFunciones;

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

	private ProgressDialog mProgressDialog ;
	private ProgressBarAsync mProgressbarAsync;
	private int mProgressStatus = 0;
	// GPSTracker class
	GPSTracker gps;
	ListView list;
	CustomAdapterRuta adapter;
	public  PoliciaActivity CustomListView = null;
	Button btnEnviar;
	EditText textComentario;
	TextView reportarErrorMsg;
	CheckBox chkPoliciaVisible;
	CheckBox chkPoliciaOculta;
	ImageButton btnFoto;
	String latitud ;
	String longitud ;

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
		btnFoto = (ImageButton) findViewById(R.id.btnFoto);
		textComentario = (EditText) findViewById(R.id.textComentario);
		reportarErrorMsg = (TextView) findViewById(R.id.reportar_error);
		chkPoliciaOculta=(CheckBox) findViewById(R.id.chkPoliciaEscondida);
		chkPoliciaVisible=(CheckBox) findViewById(R.id.chkPoliciaVisible);
				
		/** Creating a progress dialog window */
		mProgressDialog = new ProgressDialog(this);

		/** Close the dialog window on pressing back button */
		mProgressDialog.setCancelable(true);
		mProgressDialog.setMessage("Enviando alerta..");

		/** Setting a horizontal style progress bar */
		//  mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);

		// Login button Click Event	   			
		btnEnviar.setOnClickListener(new OnClickListener() {

			public void onClick(View view) {
				
				mProgressDialog.show();
				gps = new GPSTracker(PoliciaActivity.this);

				// check if GPS enabled		
				if(gps.canGetLocation()){
					String comentario="";
					latitud = String.valueOf(gps.getLatitude());
					longitud = String.valueOf(gps.getLongitude());
					if(textComentario.getText().toString()!="Comentario")
						comentario=textComentario.getText().toString();
				}
				/** Creating an instance of ProgressBarAsync */
				mProgressbarAsync = new ProgressBarAsync();

				/** ProgressBar starts its execution */
				mProgressbarAsync.execute();



			}
		});	
		

		
		// Login button Click Event	   			
		btnFoto.setOnClickListener(new OnClickListener() {

			public void onClick(View view) 
			{
					Intent i = new Intent(getApplicationContext(),
							upload.class);
			    	//i.putExtra("tipoReporte", "Policia");
					startActivity(i);

					finish();
			}
		});	
		
		// Login button Click Event	   			
		chkPoliciaOculta.setOnClickListener(new OnClickListener() {

			public void onClick(View view) {

				chkPoliciaVisible.setChecked(false);



			}
		});	
		
		// Login button Click Event	   			
		chkPoliciaVisible.setOnClickListener(new OnClickListener() {

			public void onClick(View view) {

				chkPoliciaOculta.setChecked(false);



			}
		});	




	}

	public boolean enviarAlerta()
	{
		gps = new GPSTracker(PoliciaActivity.this);

		// check if GPS enabled		
		if(gps.canGetLocation()){
			String comentario="";
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
					//reportarErrorMsg.setText("");
					String res = json.getString(KEY_SUCCESS); 
					if(Integer.parseInt(res) == 1){
						//		reportarErrorMsg.setText("Se reportó con exito");
						return true;
					}else{
						// Error in registration
						//		reportarErrorMsg.setText("Error al reportar");
						return false;
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
		return false;

	}

	private class ProgressBarAsync extends AsyncTask<Void, Integer,Boolean>{

		/** This callback method is invoked, before starting the background process */
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			mProgressStatus = 0;
		}

		/** This callback method is invoked on calling execute() method
		 * on an instance of this class */
		@Override
		protected Boolean doInBackground(Void...params) {
			//	            while(mProgressStatus<100){
			//	                try{
			//	 
			//	                    mProgressStatus++;
			//	 
			//	                    /** Invokes the callback method onProgressUpdate */
			//	                    publishProgress(mProgressStatus);
			//	 
			//	                    /** Sleeps this thread for 100ms */
			//	                    Thread.sleep(100);
			//	 
			//	                }catch(Exception e){
			//	                    Log.d("Exception", e.toString());
			//	                }
			//	            }
			return enviarAlerta();
			//   return null;
		}

		/** This callback method is invoked when publishProgress()
		 * method is called */
		@Override
		protected void onProgressUpdate(Integer... values) {
			super.onProgressUpdate(values);
			mProgressDialog.setProgress(mProgressStatus);
		}

		/** This callback method is invoked when the background function
		 * doInBackground() is executed completely */
		@Override
		protected void onPostExecute(Boolean result) {
			super.onPostExecute(result);
			mProgressDialog.dismiss();
			if(result == true)
			{
				reportarErrorMsg.setText("Se reportó con exito");
			}
			else
			{
				reportarErrorMsg.setText("Error al reportar");
			}
		}
	}
}