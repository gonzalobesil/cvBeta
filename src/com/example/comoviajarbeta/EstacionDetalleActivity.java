package com.example.comoviajarbeta;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.comoviajarbeta.dao.EstacionDataSource;
import com.example.comoviajarbeta.data.Estacion;

public class EstacionDetalleActivity extends Activity
{
	private EstacionDataSource dataSource;
	public ImageView imageEstacionLogo;
	public TextView textEstacionNombre;
	public TextView textEstacionDireccion;
	public TextView contactoEstacionTelefono;
	public WebView webViewMapa;
	public Button btnLlamar;
	
	public Resources res;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.estacion_detalle_layout);
		
		imageEstacionLogo = (ImageView)findViewById(R.id.imageEstacionLogo);
		textEstacionNombre = (TextView)findViewById(R.id.textEstacionNombre);
		textEstacionDireccion = (TextView)findViewById(R.id.textEstacionDireccion);
		contactoEstacionTelefono = (TextView)findViewById(R.id.textEstacionTelefono);
		webViewMapa = (WebView)findViewById(R.id.webViewMapa);
		btnLlamar = (Button)findViewById(R.id.btnLlamar);
		
		
		
		
		Bundle bundle = getIntent().getExtras();
		long estacionId = bundle.getLong("estacionId");
		
		dataSource = new EstacionDataSource(this);
		final Estacion estacion = dataSource.getEstacion(estacionId);
		
		res = getResources(); 
		
		imageEstacionLogo.setImageResource(res.getIdentifier("com.example.comoviajarbeta:drawable/"+estacion.getImagen(),null,null));
		textEstacionNombre.setText(estacion.getNombre());
		textEstacionDireccion.setText(estacion.getDireccion());
		contactoEstacionTelefono.setText(estacion.getTelefono());
		
		//imageEstacionLogo.setBackgroundResource(res.getIdentifier("com.example.comoviajarbeta:drawable/"+estacion.getImagen(),null,null));
		
		//webViewMapa = new WebView(this);
		WebSettings webSettings = webViewMapa.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setPluginsEnabled(true);
        //setContentView(webViewMapa);
        
        final String summary = "<!DOCTYPE html>" +
        	       " <html>" +
        	        "<head>" +
        	       " <title>Leaflet mobile example</title>" +
        	       " <meta name='viewport' content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no'>" +
        	       " <link rel='stylesheet' href='http://leafletjs.com/dist/leaflet.css' />" +
        	       " <!--[if lte IE 8]><link rel='stylesheet' href='http://leafletjs.com/dist/leaflet.ie.css' /><![endif]-->" +
        	       " <script type='text/javascript' language='javascript' src='http://leafletjs.com/dist/leaflet.js'></script>" +
        	      
        	      "  </head>" +
        	      "  <body>" +
        	     "   <div id='map' style='width: 300px; height: 300px'></div>" +
        	      "  <script type='text/javascript' language='javascript'>" +
        	     "   var map = L.map('map');" +
        	     "   L.tileLayer('http://{s}.tile.cloudmade.com/BC9A493B41014CAABB98F0471D759707/997/256/{z}/{x}/{y}.png', {" +
        	     //cuantas veces se puede hacer zoom
        	     "   maxZoom: 18," +
        	      "  }).addTo(map); " +
        	      "map.setView(new L.LatLng("+ estacion.getLatitud()+","+ estacion.getLongitud()+"), 15);" +
        	      //"map.panTo(new L.LatLng("+ estacion.getLatitud()+","+ estacion.getLongitud()+"));" +
        	     "L.marker(["+ estacion.getLatitud()+","+ estacion.getLongitud()+"]).addTo(map);" +
        	      "  function onLocationFound(e) { " +
        	     "   var radius = e.accuracy / 2; " +
        	     "   L.marker(e.latlng).addTo(map) " +
        	     "   .bindPopup('You are within ' + radius + ' meters from this point').openPopup(); " +
        	     "   L.circle(e.latlng, radius).addTo(map); " +
        	     "   } " +
        	     "   function onLocationError(e) { " +
        	     "   alert(e.message); " +
        	     "   } " +
        	     "   </script> " +
        	      "  </body> " +
        	     "   </html>";
        
        
      
        
        //webViewMapa.loadData(summary, "text/html", null);
        
        
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Cargando mapa ...");

        progressDialog.setCancelable(false);
        progressDialog.show();

       // webViewMapa = new WebView(this);

        webViewMapa.setWebViewClient(new WebViewClient() {
          @Override
          public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            progressDialog.dismiss();
          }
        });

        webViewMapa.loadData(summary, "text/html", null);
       
 
        	        
	        btnLlamar.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View view) {
					// Launching News Feed Screen
					Intent i = new Intent(Intent.ACTION_CALL);
					//i.setData();
					i.setData(Uri.parse("tel:" + estacion.getTelefono()));
					startActivity(i);
				}
			});
		
	}
			
	
	
}
