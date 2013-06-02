package com.example.comoviajarbeta;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.ListView;

import com.example.comoviajarbeta.dao.EstacionDataSource;
import com.example.comoviajarbeta.data.Estacion;

public class EstacionesActivity extends Activity {
	// DAO
	private EstacionDataSource dataSource;
	
	ListView list;
	CustomAdapterEstacion adapter;
	public  EstacionesActivity CustomListView = null;
	public  ArrayList<Estacion> CustomListViewValuesArr = new ArrayList<Estacion>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.estaciones_layout);
		
		CustomListView = this;
		
	Bundle bundle = getIntent().getExtras();
	long depto = bundle.getLong("departamentoSeleccionado");
	
		//savedInstanceState.get
		
		/******** Take some data in Arraylist ( CustomListViewValuesArr ) ***********/
		setListData(depto);
		
		
		Resources res =getResources(); 
        list=(ListView)findViewById(R.id.list);
        
        /**************** Create Custom Adapter *********/
        adapter=new CustomAdapterEstacion(CustomListView, CustomListViewValuesArr,res);
        list.setAdapter(adapter);
        
        
	}

	/****** Function to set data in ArrayList *************/
    public void setListData(long departamentoSeleccionado)
    {
    	
		// Create DAO object
		dataSource = new EstacionDataSource(this);

		for (Estacion e : dataSource.getEstacionesDepartamento(departamentoSeleccionado)) {
	   
			/******** Take Model Object in ArrayList **********/
			CustomListViewValuesArr.add(e);
		}
		
    }
    
    public void onItemClick(int mPosition)
    {
    	  Estacion tempValues = (Estacion) CustomListViewValuesArr.get(mPosition);
    	  Intent i = new Intent(getApplicationContext(), EstacionDetalleActivity.class);
    	  i.putExtra("estacionId", tempValues.getId());
    	  startActivity(i);
    }
   

	}