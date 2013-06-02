package com.example.comoviajarbeta;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ListView;

import com.example.comoviajarbeta.dao.EmpresaDataSource;
import com.example.comoviajarbeta.data.Empresa;

public class EmpresasActivity extends Activity {
	// DAO
	private EmpresaDataSource dataSource;
	
	ListView list;
	CustomAdapterEmpresa adapter;
	public  EmpresasActivity CustomListView = null;
	public  ArrayList<Empresa> CustomListViewValuesArr = new ArrayList<Empresa>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.empresas_layout);
		
		CustomListView = this;
		
		//savedInstanceState.get
		
		/******** Take some data in Arraylist ( CustomListViewValuesArr ) ***********/
		setListData();
		
		
		Resources res =getResources(); 
        list=(ListView)findViewById(R.id.list);
        
        /**************** Create Custom Adapter *********/
        adapter=new CustomAdapterEmpresa(CustomListView, CustomListViewValuesArr,res);
        list.setAdapter(adapter);
        
        
	}

	/****** Function to set data in ArrayList *************/
    public void setListData()
    {
    	
		// Create DAO object
		dataSource = new EmpresaDataSource(this);

		for (Empresa e : dataSource.getEmpresas()) {
	   
			/******** Take Model Object in ArrayList **********/
			CustomListViewValuesArr.add(e);
		}
		
    }
    
    
    
    public void onItemClick(int mPosition)
    {
    	Empresa tempValues = (Empresa) CustomListViewValuesArr.get(mPosition);
			// Launching News Feed Screen
			Intent i = new Intent(Intent.ACTION_CALL);
			//i.setData();
			i.setData(Uri.parse("tel:" + tempValues.getTelefono()));
			startActivity(i);
		}
    }