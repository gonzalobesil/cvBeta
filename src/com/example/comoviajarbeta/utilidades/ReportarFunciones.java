
package com.example.comoviajarbeta.utilidades;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import android.content.Context;

public class ReportarFunciones {
	
	private JSONParser jsonParser;
	
	private static String loginURL = "http://10.0.2.2/ah_login_api/";
	private static String registrarAlertaURL = "http://www.comoviajar.com.uy/androide/guardarAlerta.php";
	
	private static String login_tag = "login";
	private static String register_tag = "register";
	
	// constructor
	public ReportarFunciones(){
		jsonParser = new JSONParser();
	}
	
	/**
	 * function make Login Request
	 * @param email
	 * @param password
	 * */
	public JSONObject reportar(String latitud, String longitud,String comentario, String tipoAlerta){
		// Building Parameters
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("latitud", latitud));
		params.add(new BasicNameValuePair("longitud", longitud));
		params.add(new BasicNameValuePair("tipoAlerta", tipoAlerta));
		params.add(new BasicNameValuePair("comentario", comentario));
		JSONObject json = jsonParser.getJSONFromUrl(registrarAlertaURL, params);
		// return json
		// Log.e("JSON", json.toString());
		return json;
	}
	

	
}
