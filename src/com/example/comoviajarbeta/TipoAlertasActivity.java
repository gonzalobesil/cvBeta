package com.example.comoviajarbeta;

import java.util.ArrayList;

import com.example.comoviajarbeta.dao.TipoAlertaDataSource;
import com.example.comoviajarbeta.data.TipoAlerta;

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
	public  ArrayList<TipoAlerta> CustomListViewValuesArr = new ArrayList<TipoAlerta>();
	
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
