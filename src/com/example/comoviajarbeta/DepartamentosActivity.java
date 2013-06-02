package com.example.comoviajarbeta;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.ListView;

import com.example.comoviajarbeta.dao.DepartamentoDataSource;
import com.example.comoviajarbeta.data.Departamento;

public class DepartamentosActivity extends Activity
{
	
	// DAO
	private DepartamentoDataSource dataSource;
	
	ListView list;
	CustomAdapterDepartamento adapter;
	public  DepartamentosActivity CustomListView = null;
	public  ArrayList<Departamento> CustomListViewValuesArr = new ArrayList<Departamento>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.departamentos_layout);
		
		CustomListView = this;
		
		/******** Take some data in Arraylist ( CustomListViewValuesArr ) ***********/
		setListData();
		
		Resources res =getResources(); 
        list=(ListView)findViewById(R.id.list);
        
        /**************** Create Custom Adapter *********/
        adapter = new CustomAdapterDepartamento(CustomListView, CustomListViewValuesArr,res);
        list.setAdapter(adapter);
		
	}

	/****** Function to set data in ArrayList *************/
    public void setListData()
    {
    	
		// Create DAO object
		dataSource = new DepartamentoDataSource(this);

		for (Departamento e : dataSource.getDepartamentos()) {
	   
			/******** Take Model Object in ArrayList **********/
			CustomListViewValuesArr.add(e);
		}
		
    }
    
    public void onItemClick(int mPosition)
    {
    	Departamento tempValues = (Departamento) CustomListViewValuesArr.get(mPosition);
    	
//    	Toast.makeText(CustomListView, 
//    			""+tempValues.getNombre(), 
//    			Toast.LENGTH_LONG)
//    	.show();
    	Intent i = new Intent(getApplicationContext(), EstacionesActivity.class);
    	i.putExtra("departamentoSeleccionado", tempValues.getId());
		startActivity(i);
    }
   

}
