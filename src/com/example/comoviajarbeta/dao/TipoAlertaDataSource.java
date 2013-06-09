package com.example.comoviajarbeta.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.comoviajarbeta.data.TipoAlerta;
import com.example.comoviajarbeta.sql.ComoViajarSQLiteHelper;

/**
 * TODOs DAO object.
 * 
 * @author itcuties
 *
 */
public class TipoAlertaDataSource {
	private static final String LOGTAG = "LOG_COMOVIAJAR";

	
	private SQLiteDatabase db;
	private ComoViajarSQLiteHelper dbHelper;
	
	public TipoAlertaDataSource(Context context) {
		dbHelper = new ComoViajarSQLiteHelper(context);
		open();
	}
	
	// Close the db
	public void open() {
		db = dbHelper.getWritableDatabase();
		Log.i(LOGTAG,"La base ha sido abierta ");
	}
	
	// Close the db
	public void close() {
		dbHelper.close();
		Log.i(LOGTAG,"La base ha sido cerrada ");
	}
	


	
	/**
	 * Get all TODOs.
	 * @return
	 */
	public List<TipoAlerta> getTipoAlerta() {
		List<TipoAlerta> tipoAlertaList = new ArrayList<TipoAlerta>();
		
    	TipoAlerta tipoAlerta1 = new TipoAlerta();
    	// Take values from the DB
    	tipoAlerta1.setId(1);
    	tipoAlerta1.setNombre("Acccidentes");
    	tipoAlerta1.setImagen("accidente_logo");
    	

    	// Add to the DB
    	tipoAlertaList.add(tipoAlerta1);
    	
    	TipoAlerta tipoAlerta2 = new TipoAlerta();
    	// Take values from the DB
    	tipoAlerta2.setId(2);
    	tipoAlerta2.setNombre("Transito");
    	tipoAlerta2.setImagen("transito_logo");
    	

    	// Add to the DB
    	tipoAlertaList.add(tipoAlerta2);
    	
    	TipoAlerta tipoAlerta3 = new TipoAlerta();
    	// Take values from the DB
    	tipoAlerta3.setId(3);
    	tipoAlerta3.setNombre("Peligro");
    	tipoAlerta3.setImagen("peligro_logo");
    	

    	// Add to the DB
    	tipoAlertaList.add(tipoAlerta3);
    	
    	TipoAlerta tipoAlerta4 = new TipoAlerta();
    	// Take values from the DB
    	tipoAlerta4.setId(4);
    	tipoAlerta4.setNombre("Policia");
    	tipoAlerta4.setImagen("policia_logo");
    	

    	// Add to the DB
    	tipoAlertaList.add(tipoAlerta4);
    	
    	
    	return tipoAlertaList;
	}
	
	
	
}
