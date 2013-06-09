/**
 * Author: Ravi Tamada
 * URL: www.androidhive.info
 * twitter: http://twitter.com/ravitamada
 * */
package com.example.comoviajarbeta;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

public class Funciones {
	
	private JSONParser jsonParser;
	
	private static String busquedaTrayectoUrl = "http://www.comoviajar.com.uy/androide/simple-rest/examples/trayecto";
	private static String busquedaAlertasUrl = "http://www.comoviajar.com.uy/androide/obtenerAlertas.php";
	private static String registerURL = "http://www.comoviajar.com.uy/androide/";
	
	private static String Busqueda_tag = "BusquedaTrayecto";
	private static String register_tag = "register";
	
	// constructor
	public Funciones(){
		jsonParser = new JSONParser();
	}
	
	/**
	 * function make Login Request
	 * @param email
	 * @param password
	 * */
	public JSONObject busquedaTrayecto(String origen){
		// Building Parameters
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		//params.add(new BasicNameValuePair("tag", Busqueda_tag));
		//params.add(new BasicNameValuePair("origen", origen));
		JSONObject json = jsonParser.getJSONFromUrl(busquedaTrayectoUrl, params);
		return json;
	}
	
	public JSONObject busquedaAlertas(String latitud,String longitud){
		// Building Parameters
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("latitud", latitud));
		params.add(new BasicNameValuePair("longitud", longitud));
		JSONObject json = jsonParser.getJSONFromUrl(busquedaAlertasUrl, params);
		return json;
	}
	/**
	 * function make Login Request
	 * @param name
	 * @param email
	 * @param password
	 * */
	public JSONObject registerUser(String name, String email, String password){
		// Building Parameters
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("tag", register_tag));
		params.add(new BasicNameValuePair("name", name));
		params.add(new BasicNameValuePair("email", email));
		params.add(new BasicNameValuePair("password", password));
		
		// getting JSON Object
		JSONObject json = jsonParser.getJSONFromUrl(registerURL, params);
		// return json
		return json;
	}
	
//	/**
//	 * Function get Login status
//	 * */
//	public boolean isUserLoggedIn(Context context){
//		DatabaseHandler db = new DatabaseHandler(context);
//		int count = db.getRowCount();
//		if(count > 0){
//			// user logged in
//			return true;
//		}
//		return false;
//	}
	
	/**
	 * Function to logout user
	 * Reset Database
	 * */
//	public boolean logoutUser(Context context){
//		DatabaseHandler db = new DatabaseHandler(context);
//		db.resetTables();
//		return true;
//	}
	
}
