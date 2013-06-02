package com.example.comoviajarbeta;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.comoviajarbeta.dao.RutaDataSource;
import com.example.comoviajarbeta.data.Ruta;

public class RutaDetalleActivity extends Activity
{
	private RutaDataSource dataSource;
	public ImageView imageRutaLogo;
	public TextView textRutaNombre;
	public TextView textRutaEstado;
	public WebView webViewMapa;


	public Resources res;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ruta_detalle_layout);

		imageRutaLogo = (ImageView)findViewById(R.id.imageRutaLogo);
		textRutaNombre = (TextView)findViewById(R.id.textRutaNombre);
		textRutaEstado = (TextView)findViewById(R.id.textRutaEstado);
		webViewMapa = (WebView)findViewById(R.id.webViewMapa);

		DisplayMetrics displaymetrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
		int height = displaymetrics.heightPixels;
		int width = displaymetrics.widthPixels;
		webViewMapa.getLayoutParams().width=width;
		webViewMapa.getLayoutParams().height=height - 100;


		Bundle bundle = getIntent().getExtras();
		long rutaId = bundle.getLong("rutaId");

		dataSource = new RutaDataSource(this);
		final Ruta ruta = dataSource.getRuta(rutaId);

		res = getResources(); 

		//imageRutaLogo.setImageResource(res.getIdentifier("com.example.comoviajarbeta:drawable/"+ruta.getImagen(),null,null));
		textRutaNombre.setText(ruta.getNombre());
		textRutaEstado.setText(ruta.getEstado());

		imageRutaLogo.setBackgroundResource(res.getIdentifier("com.example.comoviajarbeta:drawable/"+ruta.getImagen(),null,null));

		//webViewMapa = new WebView(this);
		WebSettings webSettings = webViewMapa.getSettings();
		webSettings.setJavaScriptEnabled(true);
		webSettings.setPluginsEnabled(true);
		//setContentView(webViewMapa);

		final String summary = "<html lang=\"en\">" +
				"<head>" +
				"<meta charset=\"utf-8\">" +
				"<title>Routing Using Cloudmade Routing and Leaflet</title>" +
				"<meta name='viewport' content='width=device-width, initial-scale=1.0'>" +
				"<link rel='stylesheet' href='http://leafletjs.com/dist/leaflet.css' />" +
				"<script type='text/javascript' language='javascript' src='http://leafletjs.com/dist/leaflet.js'></script>" +
				"</head>" +
				"<body>" +
				"<div id='map' style='width: 300px; height: 400px;'></div>" +
				"<script type='text/javascript'>" +
				"    var map, fromMarker, toMarker;" +

                "    function addScript(url) {" +
                "        var script = document.createElement('script');" +
                "        script.type='text/javascript';" +
                "        script.src=url;" +
                "        document.getElementsByTagName('head') [0].appendChild(script);" +
                "    }" +

        		"	function getRoute(response) {" +
        		"        var point, route, points = [];" +
        		"        for (var i=0; i<response.route_geometry.length; i++)" +
        		"        {" +
        		"            point = new L.LatLng(response.route_geometry[i][0] , response.route_geometry[i][1]);" +
        		"            points.push(point);" +
        		"        }" +
        		"        route= new L.Polyline(points, {" +
        		"            weight: 6," +
        		"            opacity: 0.5," +
        		"            smoothFactor: 1" +
        		"        }).addTo(map);" +
        		"        route.bringToFront();" +
        		"    }" +

                "    map=L.map('map').setView([-33.83392,-55.968018], 6);" +
                "    var basemap = L.tileLayer('http://{s}.tile.cloudmade.com/BC9A493B41014CAABB98F0471D759707/1/256/{z}/{x}/{y}.png'," +
                "    {" +
                "        maxZoom:15" +
                "    }).addTo(map);" +
                "    fromMarker = new L.Marker(new L.latLng([-31.381193,-57.96833])).addTo(map);" +
                "    toMarker=new L.Marker(new L.latLng([-31.708308,-55.998688])).addTo(map);" +
                "    addScript('http://routes.cloudmade.com/BC9A493B41014CAABB98F0471D759707/api/0.3/' + fromMarker.getLatLng().lat + ',' + fromMarker.getLatLng().lng + ',' + toMarker.getLatLng().lat + ',' + toMarker.getLatLng().lng + '/car.js?callback=getRoute');" +
                "</script>" +
                "</body>" +
                "</html>";




		//webViewMapa.loadData(summary, "text/html", null);


		final ProgressDialog progressDialog = new ProgressDialog(this);
		progressDialog.setMessage("Cargando mapa ...");

		progressDialog.setCancelable(false);
		progressDialog.show();

		webViewMapa.setWebViewClient(new WebViewClient() {
			@Override
			public void onPageFinished(WebView view, String url) {
				super.onPageFinished(view, url);
				progressDialog.dismiss();
			}
		});

		webViewMapa.loadData(summary, "text/html", null);


	}
}
