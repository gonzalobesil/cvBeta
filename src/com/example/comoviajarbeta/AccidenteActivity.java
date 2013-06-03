package com.example.comoviajarbeta;
import com.example.comoviajarbeta.dao.*;

import com.example.comoviajarbeta.data.*;

import java.util.ArrayList;


import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
//Prueba   
public class AccidenteActivity extends Activity {
	// DAO
	private RutaDataSource dataSource;
	
	ListView list;
	CustomAdapterRuta adapter;
	public  AccidenteActivity CustomListView = null;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.accidente_layout);
		
		CustomListView = this;
		
		//savedInstanceState.get
		
		/******** Take some data in Arraylist ( CustomListViewValuesArr ) ***********/

		
		Resources res =getResources(); 
        list=(ListView)findViewById(R.id.list);
	}  
    
   
   

	}