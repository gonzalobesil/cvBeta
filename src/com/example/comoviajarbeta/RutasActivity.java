package com.example.comoviajarbeta;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.ListView;

import com.example.comoviajarbeta.dao.RutaDataSource;
import com.example.comoviajarbeta.data.Ruta;

public class RutasActivity extends Activity {
	// DAO
	private RutaDataSource dataSource;
	
	ListView list;
	CustomAdapterRuta adapter;
	public  RutasActivity CustomListView = null;
	public  ArrayList<Ruta> CustomListViewValuesArr = new ArrayList<Ruta>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.rutas_layout);
		
		CustomListView = this;
		
		//savedInstanceState.get
		
		/******** Take some data in Arraylist ( CustomListViewValuesArr ) ***********/
		setListData();
		
		
		Resources res =getResources(); 
        list=(ListView)findViewById(R.id.list);
        
        /**************** Create Custom Adapter *********/
        adapter=new CustomAdapterRuta(CustomListView, CustomListViewValuesArr,res);
        list.setAdapter(adapter);
        
        
	}

	/****** Function to set data in ArrayList *************/
    public void setListData()
    {
    	
		// Create DAO object
		dataSource = new RutaDataSource(this);

		for (Ruta e : dataSource.getRutas()) {
	   
			/******** Take Model Object in ArrayList **********/
			CustomListViewValuesArr.add(e);
		}
		
    }
    
    
    public void onItemClick(int mPosition)
    {
    	  Ruta tempValues = (Ruta) CustomListViewValuesArr.get(mPosition);
    	  Intent i = new Intent(getApplicationContext(), RutaDetalleActivity.class);
    	  i.putExtra("rutaId", tempValues.getId());
    	  startActivity(i);
    }
   
   

	}