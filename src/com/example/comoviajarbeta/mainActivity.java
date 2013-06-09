package com.example.comoviajarbeta;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.comoviajarbeta.dao.DepartamentoDataSource;
import com.example.comoviajarbeta.dao.EmpresaDataSource;
import com.example.comoviajarbeta.dao.EstacionDataSource;
import com.example.comoviajarbeta.dao.LugarDataSource;
import com.example.comoviajarbeta.dao.RutaDataSource;
import com.example.comoviajarbeta.dao.TransporteDataSource;
import com.example.comoviajarbeta.dao.TrayectoDataSource;

public class mainActivity extends Activity {

	// DAO//
	//test
	//asdas
	private EstacionDataSource estacionDataSource;
	private EmpresaDataSource empresaDataSource;
	private RutaDataSource rutaDataSource;
	private DepartamentoDataSource departamentoDataSource;
	private LugarDataSource lugarDataSource;
	private TrayectoDataSource trayectoDataSource;
	private TransporteDataSource transporteDataSource;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		/**
		 * Creating all buttons instances
		 * */
		// Dashboard News feed button
		Button btn_horarios = (Button) findViewById(R.id.btn_horarios);

		// Dashboard Friends button
		Button btn_estaciones = (Button) findViewById(R.id.btn_estaciones);

		// Dashboard Messages button
		//Button btn_messages = (Button) findViewById(R.id.btn_messages);

		// Dashboard Places button
		Button btn_reportar = (Button) findViewById(R.id.btn_reportar);

		// Dashboard Events button
		Button btn_rutas = (Button) findViewById(R.id.btn_rutas);

		// Dashboard Photos button
		Button btn_empresas = (Button) findViewById(R.id.btn_empresas);
		
		Button btn_alertas = (Button) findViewById(R.id.btn_alertas);

		/**
		 * Handling all button click events
		 * */

		// Listening to News Feed button click
		btn_horarios.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View view) {
				// Launching News Feed Screen
				Intent i = new Intent(getApplicationContext(), TrayectoActivity.class);
				startActivity(i);
			}
		});

		// Listening to News Feed button click
		btn_reportar.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View view) {
				// Launching News Feed Screen
				Intent i = new Intent(getApplicationContext(), ReportarActivity.class);
				startActivity(i);
			}
		});

		// Listening Friends button click
		btn_estaciones.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View view) {
				// Launching News Feed Screen
				Intent i = new Intent(getApplicationContext(), TabsActivity.class);
				startActivity(i);
			}
		});
		
		// Listening Friends button click
		btn_rutas.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View view) {
				// Launching News Feed Screen
				Intent i = new Intent(getApplicationContext(), RutasActivity.class);
				startActivity(i);
			}
		});
		
		// Listening Friends button click
		btn_empresas.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View view) {
				// Launching News Feed Screen
				Intent i = new Intent(getApplicationContext(), EmpresasActivity.class);
				startActivity(i);
			}
		});
		
		
		btn_alertas.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View view) {
				// Launching News Feed Screen
				Intent i = new Intent(getApplicationContext(), AlertasNotificacionesActivity.class);
				startActivity(i);
			}
		});
		
				
		// Create DAO object
		estacionDataSource = new EstacionDataSource(this);
		if(estacionDataSource.getEstaciones().isEmpty())
		{
		   estacionDataSource.createEstacionesPrueba();
		}
		  
		departamentoDataSource = new DepartamentoDataSource(this);
		if(departamentoDataSource.getDepartamentos().isEmpty())
		{
		   departamentoDataSource.createDepartamentosPrueba();
		}
		empresaDataSource = new EmpresaDataSource(this);
		if(empresaDataSource.getEmpresas().isEmpty())
		{
			empresaDataSource.createEmpresasPrueba();
		}
		rutaDataSource = new RutaDataSource(this);
		if(rutaDataSource.getRutas().isEmpty())
		{
			rutaDataSource.createRutasPrueba();
		}
		lugarDataSource = new LugarDataSource(this);
		if(lugarDataSource.getLugares().isEmpty())
		{
			lugarDataSource.createEmpresasPrueba();
		}
		trayectoDataSource = new TrayectoDataSource(this);
		if(trayectoDataSource.getTrayectos().isEmpty())
		{
			trayectoDataSource.createTrayectosPrueba();
		}
		transporteDataSource = new TransporteDataSource(this);
		if(transporteDataSource.getTransporte().isEmpty())
		{
			transporteDataSource.createTransportesPrueba();
		}
		// Set the list adapter and get TODOs list via DAO
		//setListAdapter(new ListAdapter(this, dataSource.getEstaciones()));
	}
}

/*public class mainActivity extends Activity{

	Button btnTrayecto;
	Button btnEstaciones;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		btnTrayecto = (Button) findViewById(R.id.btnTrayectos);

		btnTrayecto.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent iinent= new Intent(mainActivity.this,TrayectoActivity.class);
				startActivity(iinent);
			}
		});

		btnEstaciones = (Button) findViewById(R.id.btnEstaciones);

		btnEstaciones.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent iinent= new Intent(mainActivity.this,EstacionesActivity.class);
				startActivity(iinent);
			}
		});
	}



}*/
