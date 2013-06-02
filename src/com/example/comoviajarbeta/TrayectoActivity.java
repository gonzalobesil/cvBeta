package com.example.comoviajarbeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.ExpandableListActivity;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.comoviajarbeta.dao.LugarDataSource;
import com.example.comoviajarbeta.dao.TrayectoDataSource;
import com.example.comoviajarbeta.data.Lugar;
import com.example.comoviajarbeta.data.Trayecto;



public class TrayectoActivity extends /*Activity,*/ExpandableListActivity {
	
	// JSON Response node names
	private static String KEY_SUCCESS = "success";
	
	private TrayectoDataSource trayectoDataSource;
	private LugarDataSource lugarDataSource;
	
//	------------------------------------------------------------
	private MyExpandableListAdapter mAdapter;
	private String[] preguntas;
	private String[][] respuestas;
	public  ArrayList<Trayecto> CustomListViewValuesArr = new ArrayList<Trayecto>();

//	------------------------------------------------------------
	
	//nombre y tipo de los elementos del layout
	TextView lblOrigen;
	TextView lblDestino;
	Button btnBuscar;
	AutoCompleteTextView ingresoOrigen;
	AutoCompleteTextView destino;
	
	HashMap<String, Long> mapaLugaresOrigen = new HashMap<String,Long>();
	HashMap<String, Long> mapaLugaresDestino = new HashMap<String,Long>();
	  
	
//	String[] origenes = null;
//		{
//			"montevideo",
//			"Canelones",
//			"monaco"
//		};
	
	List<String> listaOrigenes = new ArrayList<String>();
	List<String> listaDestinos = new ArrayList<String>();
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.trayecto_layout);
		
		lugarDataSource = new LugarDataSource(this);
		
		for (Lugar lugar : lugarDataSource.getLugares()) {
            mapaLugaresOrigen.put(lugar.getNombre(),lugar.getId());
            listaOrigenes.add(lugar.getNombre());
        }
		
		// Importar los elementos contenidos en el layout
		btnBuscar = (Button) findViewById(R.id.btnBuscar);
		ingresoOrigen = (AutoCompleteTextView) findViewById(R.id.origen);
		destino = (AutoCompleteTextView) findViewById(R.id.destino);
		lblOrigen = (TextView) findViewById(R.id.lblOrigen);
		lblDestino = (TextView) findViewById(R.id.lblDestino);
		   
	   ingresoOrigen.setThreshold(1);
	   String []origenes = new String[listaOrigenes.size()];
	   listaOrigenes.toArray(origenes);
	   ingresoOrigen.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,origenes));
//	   String []destinosArray = null;
//	   destino.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,destinosArray));
	   
	  

		// Login button Click Event	   			
				destino.setOnClickListener(new OnClickListener() {

					public void onClick(View view) {
						
						
					     // progressDialog.show();
						
						String origen = ingresoOrigen.getText().toString();
						if(validarOrigen(origen, listaOrigenes))
						{
							Long idOrigen = mapaLugaresOrigen.get(origen);
							
							List<Lugar> destinos = lugarDataSource.getDestinos(String.valueOf(idOrigen));
							
							for (Lugar lugar : destinos) {
					            mapaLugaresDestino.put(lugar.getNombre(),lugar.getId());
					            listaDestinos.add(lugar.getNombre());
					        }
							
							
							//cargar combo destino
							destino.setThreshold(1);
							String[] destinosArray = new String[listaDestinos.size()];
							listaDestinos.toArray(destinosArray);
							ArrayAdapter<String> adapter = new ArrayAdapter<String>(TrayectoActivity.this,android.R.layout.simple_spinner_item,destinosArray);
							destino.setAdapter(adapter);
							destino.showDropDown();
							//destino.requestFocus();
							
							Context context = getApplicationContext();
							CharSequence text = "Ingrese el destino";
							int duration = Toast.LENGTH_SHORT;
							Toast toast = Toast.makeText(context, text, duration);
							toast.setGravity(Gravity.TOP, 0, 20);
							toast.show();
							
//							//expandable list view------------------------------------------------------------------
//							preguntas = getResources().getStringArray(R.array.countries);
//
//							// children array
//							String[] respuestas1 = getResources().getStringArray(R.array.capitals);
//							respuestas = new String[respuestas1.length][1];
//							for (int i = 0; i < respuestas1.length; i++) {
//								respuestas[i][0] = respuestas1[i];
//							}
//
//							// Set up our adapter
//							mAdapter = new MyExpandableListAdapter(TrayectoActivity.this,preguntas,respuestas);
//							setListAdapter(mAdapter);
//							//end expandable list view------------------------------------------------------------------
							
							//String origen = "montevideo";
							
							Funciones userFunction = new Funciones();
							//Log.d("Button", "Login");
//							JSONObject json = userFunction.busquedaTrayecto(origen);
//							
//						
//
//							// check for login response
//							try {
//								if (json.getString(KEY_SUCCESS) != null) {
//									//loginErrorMsg.setText("");
//									Gson gson = new Gson();
//									Trayecto t = gson.fromJson(json.toString(), Trayecto.class);
//									t.toString();
//									destino.setText(t.getName());
////									String res = json.getString(KEY_SUCCESS); 
////									if(Integer.parseInt(res) == 1){
////										// user successfully logged in
////										
////										// Close Login Screen
////										//finish();
////									}else{
////										// Error in login
////										//loginErrorMsg.setText("Incorrect username/password");
////									}
//								}
//							} catch (JSONException e) {
//								e.printStackTrace();
//							}
						}
						else
						{
							Context context = getApplicationContext();
							CharSequence text = "Ingrese un orígen válido";
							int duration = Toast.LENGTH_SHORT;
							Toast toast = Toast.makeText(context, text, duration);
							toast.setGravity(Gravity.TOP, 0, 20);
							toast.show();
						}
						
					}
				});
				
				// Login button Click Event	   			
				btnBuscar.setOnClickListener(new OnClickListener() {

					public void onClick(View view) {
						
						String destinoSeleccionado = destino.getText().toString();
						if(validarOrigen(destinoSeleccionado, listaDestinos))
						{
							
							setListData();
							
							Long idDestino = mapaLugaresDestino.get(destinoSeleccionado);
							

							
							//expandable list view------------------------------------------------------------------
							//preguntas = getResources().getStringArray(R.array.countries);
							//preguntas = CustomListViewValuesArr.toArray();

							// children array
							String[] respuestas1 = getResources().getStringArray(R.array.capitals);
							respuestas = new String[respuestas1.length][1];
							for (int i = 0; i < respuestas1.length; i++) {
								respuestas[i][0] = respuestas1[i];
							}

							// Set up our adapter
							mAdapter = new MyExpandableListAdapter(TrayectoActivity.this,CustomListViewValuesArr,respuestas);
							setListAdapter(mAdapter);
							//end expandable list view------------------------------------------------------------------
							
						}
						else
						{
							Context context = getApplicationContext();
							CharSequence text = "Ingrese un orígen válido";
							int duration = Toast.LENGTH_SHORT;
							Toast toast = Toast.makeText(context, text, duration);
							toast.setGravity(Gravity.TOP, 0, 20);
							toast.show();
						}
						
					}
				});	
	}
	

	
	
	//end expandable list view--------------------------------------------------------------

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.trayecto, menu);
		return true;
	}
	
	private boolean validarOrigen(String origen, List<String> listaOrigenes){
		 if(!listaOrigenes.contains(origen)){
			 return false;
		   }
		 return true;
	}
	
	  public void setListData()
	    {
	    	
			// Create DAO object
			trayectoDataSource = new TrayectoDataSource(this);

			for (Trayecto e : trayectoDataSource.getTrayectos()) {
		   
				/******** Take Model Object in ArrayList **********/
				CustomListViewValuesArr.add(e);
			}
			
	    }

}
