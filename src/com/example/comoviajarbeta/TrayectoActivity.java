package com.example.comoviajarbeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.ExpandableListActivity;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.example.comoviajarbeta.dao.LugarDataSource;
import com.example.comoviajarbeta.dao.TrayectoDataSource;
import com.example.comoviajarbeta.data.Lugar;
import com.example.comoviajarbeta.data.Trayecto;



public class TrayectoActivity extends ExpandableListActivity {

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
	Button btnInvertir;
	AutoCompleteTextView ingresoOrigen;
	AutoCompleteTextView destino;
	CheckBox chkOmnibus;
	CheckBox chkBarco;

	HashMap<String, Long> mapaLugaresOrigen = new HashMap<String,Long>();
	HashMap<String, Long> mapaLugaresDestino = new HashMap<String,Long>();

	List<String> listaOrigenes = new ArrayList<String>();
	List<String> listaDestinos = new ArrayList<String>();


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.trayecto_layout);

		lugarDataSource = new LugarDataSource(this);


		// Cargar los lugares existentes en la base de datos.
		for (Lugar lugar : lugarDataSource.getLugares()) {
			mapaLugaresOrigen.put(lugar.getNombre(),lugar.getId());
			listaOrigenes.add(lugar.getNombre());
		}

		// Importar los elementos contenidos en el layout
		btnBuscar = (Button) findViewById(R.id.btnBuscar);
		btnInvertir = (Button) findViewById(R.id.btnInvertir);
		ingresoOrigen = (AutoCompleteTextView) findViewById(R.id.origen);
		destino = (AutoCompleteTextView) findViewById(R.id.destino);
		lblOrigen = (TextView) findViewById(R.id.lblOrigen);
		lblDestino = (TextView) findViewById(R.id.lblDestino);
		chkOmnibus = (CheckBox) findViewById(R.id.chkOmnibus);
		chkBarco = (CheckBox) findViewById(R.id.chkBarco);

		ingresoOrigen.setThreshold(1);
		String []origenes = new String[listaOrigenes.size()];
		listaOrigenes.toArray(origenes);
		ingresoOrigen.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,origenes));



		//	   String []destinosArray = null;
		//	   destino.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,destinosArray));



		// Evento de tocar el TextView destino	   			
		destino.setOnFocusChangeListener(new View.OnFocusChangeListener() {


			public void onFocusChange(View view, boolean arg1) {


				//public void onClick(View view) {

				String origen = ingresoOrigen.getText().toString();

				if(validarOrigen(origen, listaOrigenes) && listaDestinos.isEmpty())
				{

					//cargar los destinos posibles desde el origen seleccionado
					Long idOrigen = mapaLugaresOrigen.get(origen);

					List<Lugar> destinos = lugarDataSource.getDestinos(String.valueOf(idOrigen));

					for (Lugar lugar : destinos) {
						mapaLugaresDestino.put(lugar.getNombre(),lugar.getId());
						listaDestinos.add(lugar.getNombre());
					}

					//cargar TextView destino
					destino.setThreshold(1);
					String[] destinosArray = new String[listaDestinos.size()];
					listaDestinos.toArray(destinosArray);
					ArrayAdapter<String> adapter = new ArrayAdapter<String>(TrayectoActivity.this,android.R.layout.simple_spinner_item,destinosArray);
					destino.setAdapter(adapter);
					destino.showDropDown();


					//Mostrar Toast para "Ingrese destino"

					//							Context context = getApplicationContext();
					//							CharSequence text = "Ingrese el destino";
					//							int duration = Toast.LENGTH_SHORT;
					//							Toast toast = Toast.makeText(context, text, duration);
					//							toast.setGravity(Gravity.TOP, 0, 20);
					//							toast.show();


				}
			}
		});

		//Evento del boton buscar		
		btnBuscar.setOnClickListener(new OnClickListener() {

			public void onClick(View view) {

				String destinoSeleccionado = destino.getText().toString();
				if(validarOrigen(destinoSeleccionado, listaDestinos))
				{

					//Esconder teclado
					InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
					imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

					List<String> transporte = new ArrayList<String>();

					//TODO: "Revisar al seleccionar mas de un tipo de trasnporte"

					if(chkOmnibus.isChecked())
					{
						transporte.add("1");
					}

					if(chkBarco.isChecked())
					{
						transporte.add("2");
					}

					if(transporte.equals(""))
					{
						Context context = getApplicationContext();
						CharSequence text = "Seleccione transporte";
						int duration = Toast.LENGTH_SHORT;
						Toast toast = Toast.makeText(context, text, duration);
						toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
						toast.show();

						return;
					}


					Long idOrigen = mapaLugaresOrigen.get(ingresoOrigen.getText().toString());
					Long idDestino = mapaLugaresDestino.get(destinoSeleccionado);

					setListData(idOrigen.toString(),idDestino.toString(),transporte);

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
					mAdapter = new MyExpandableListAdapter(TrayectoActivity.this,CustomListViewValuesArr,respuestas,getResources());
					setListAdapter(mAdapter);
					//end expandable list view------------------------------------------------------------------

					if( CustomListViewValuesArr.size() == 0)
					{
						Context context = getApplicationContext();
						CharSequence text = "No hay trayectos";
						int duration = Toast.LENGTH_SHORT;
						Toast toast = Toast.makeText(context, text, duration);
						toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
						toast.show();
					}

				}
				else
				{
					Context context = getApplicationContext();
					CharSequence text = "No hay trayectos para el orígen";
					int duration = Toast.LENGTH_SHORT;
					Toast toast = Toast.makeText(context, text, duration);
					toast.setGravity(Gravity.TOP, 0, 20);
					toast.show();
				}

			}
		});

		//Evento boton invertir
		btnInvertir.setOnClickListener(new OnClickListener() {

			public void onClick(View view) {

				//Invertir origen y destino
				String transporte = "";

				//TODO: "Revisar al seleccionar mas de un tipo de trasnporte"

				if(chkOmnibus.isChecked())
				{
					transporte = transporte + "1";
				}

				if(chkBarco.isChecked())
				{
					transporte = transporte + "2";
				}

				if(transporte.equals(""))
				{
					Context context = getApplicationContext();
					CharSequence text = "Seleccione transporte";
					int duration = Toast.LENGTH_SHORT;
					Toast toast = Toast.makeText(context, text, duration);
					toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
					toast.show();

					return;
				}


				Long idOrigen = mapaLugaresOrigen.get(ingresoOrigen.getText().toString());

				//Invertir los textos a mostrar
				Editable aux = ingresoOrigen.getText();
				ingresoOrigen.setText(destino.getText());
				destino.setText(aux);

				idOrigen = mapaLugaresOrigen.get(ingresoOrigen.getText().toString());
				List<Lugar> destinos = lugarDataSource.getDestinos(String.valueOf(idOrigen));

				listaDestinos.clear();

				for (Lugar lugar : destinos) {
					mapaLugaresDestino.put(lugar.getNombre(),lugar.getId());
					listaDestinos.add(lugar.getNombre());
				}

				String[] destinosArray = new String[listaDestinos.size()];
				listaDestinos.toArray(destinosArray);
				ArrayAdapter<String> adapter = new ArrayAdapter<String>(TrayectoActivity.this,android.R.layout.simple_spinner_item,destinosArray);
				destino.setAdapter(adapter);

				//Long idOrigen = mapaLugaresOrigen.get(ingresoOrigen.getText().toString());
				Long idDestino = mapaLugaresDestino.get(destino.getText().toString());


			//	setListData(idOrigen.toString(),idDestino.toString(),transporte);

				// children array
				String[] respuestas1 = getResources().getStringArray(R.array.capitals);
				respuestas = new String[respuestas1.length][1];
				for (int i = 0; i < respuestas1.length; i++) {
					respuestas[i][0] = respuestas1[i];
				}

				// Set up our adapter
				mAdapter = new MyExpandableListAdapter(TrayectoActivity.this,CustomListViewValuesArr,respuestas,getResources());
				setListAdapter(mAdapter);

				if(CustomListViewValuesArr.size() == 0)
				{
					Context context = getApplicationContext();
					CharSequence text = "No existen trayectos";
					int duration = Toast.LENGTH_SHORT;
					Toast toast = Toast.makeText(context, text, duration);
					toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
					toast.show();
				}
			}
		});	
	}


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

	public void setListData(String idOrigen, String idDestino, List<String> transporte)
	{
		//Clear list content
		CustomListViewValuesArr.clear();

		// Create DAO object
		trayectoDataSource = new TrayectoDataSource(this);
		
		String[] transportes = new String[transporte.size()];
		transporte.toArray(transportes);

		for (Trayecto e : trayectoDataSource.getTrayectosTransportes(idOrigen, idDestino, transportes)) {

			/******** Take Model Object in ArrayList **********/
			CustomListViewValuesArr.add(e);
		}

	}
}
