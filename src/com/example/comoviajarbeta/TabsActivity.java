package com.example.comoviajarbeta;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class TabsActivity extends TabActivity {  
	protected Resources res;
	
    @Override  
    public void onCreate(Bundle savedInstanceState) {  
         super.onCreate(savedInstanceState);  
         setContentView(R.layout.tabs_activity_layout);  
         res = getResources(); 
         
         TabHost tabHost = getTabHost();
        
         // Tab para estaciones cercanas
         TabSpec photospec = tabHost.newTabSpec("Cercanas");
         photospec.setIndicator("Cercanas", getResources().getDrawable(R.drawable.radar));
         Intent photosIntent = new Intent(this, EstacionesCercanasActivity.class);
         photospec.setContent(photosIntent);
         
         // Tab for Songs
         TabSpec songspec = tabHost.newTabSpec("Todas");
         // setting Title and Icon for the Tab
         songspec.setIndicator("Todas", getResources().getDrawable(R.drawable.mapauruguay));
         Intent songsIntent = new Intent(this, DepartamentosActivity.class);
         songspec.setContent(songsIntent);
        
         tabHost.addTab(photospec);
         tabHost.addTab(songspec); // Adding songs tab
    }  
 
}
