package com.example.comoviajarbeta;

import java.util.ArrayList;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.example.comoviajarbeta.dao.LugarDataSource;
import com.example.comoviajarbeta.data.Lugar;

public class LugaresActivity extends Activity
{
	
	// DAO
	private LugarDataSource dataSource;
	
	ListView list;
	CustomAdapterLugar adapter;
	public  LugaresActivity CustomListView = null;
	public  ArrayList<Lugar> CustomListViewValuesArr = new ArrayList<Lugar>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lugares_layout);
		
		CustomListView = this;
		
		/******** Take some data in Arraylist ( CustomListViewValuesArr ) ***********/
		setListData();
		
		Resources res =getResources(); 
        list=(ListView)findViewById(R.id.list);
        
        /**************** Create Custom Adapter *********/
        adapter = new CustomAdapterLugar(CustomListView, CustomListViewValuesArr,res);
        list.setAdapter(adapter);
		
	}

	/****** Function to set data in ArrayList *************/
    public void setListData()
    {
    	
		// Create DAO object
		dataSource = new LugarDataSource(this);

		for (Lugar e : dataSource.getLugares()) {
	   
			/******** Take Model Object in ArrayList **********/
			CustomListViewValuesArr.add(e);
		}
		
    }
    
    public void onItemClick(int mPosition)
    {
    	Lugar tempValues = (Lugar) CustomListViewValuesArr.get(mPosition);
    	
		Toast.makeText(CustomListView, 
    			""+tempValues.getNombre(), 
    			Toast.LENGTH_LONG)
    	.show();
    }
   

}
