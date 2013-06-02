package com.example.comoviajarbeta.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.comoviajarbeta.data.Trayecto;
import com.example.comoviajarbeta.sql.ComoViajarSQLiteHelper;
import com.example.comoviajarbeta.utilidades.TrayectoConstantes;

/**
 * TODOs DAO object.
 * 
 * @author itcuties
 *
 */
public class TrayectoDataSource {
	private static final String LOGTAG = "LOG_COMOVIAJAR";

	
	private SQLiteDatabase db;
	private ComoViajarSQLiteHelper dbHelper;
	
	public TrayectoDataSource(Context context) {
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
	 * Create new TODO object
	 * @param todoText
	 */
	public void createTrayectosPrueba() {
		ContentValues contentValues = new ContentValues();
		
		contentValues.put(TrayectoConstantes.COLUMN_ORIGEN_ID, "1");
		contentValues.put(TrayectoConstantes.COLUMN_DESTINO_ID, "2");
		contentValues.put(TrayectoConstantes.COLUMN_HORA, "10:30");
		//contentValues.put(TrayectoConstantes.COLUMN_EMPRESA_ID, "Empresa1");
		
	    // Insert into DB
		long  id= db.insert(TrayectoConstantes.TABLE_TRAYECTOS, null, contentValues);
		Log.i(LOGTAG,"Se inserto un nuevo trayecto id:"+id);
		
		contentValues.put(TrayectoConstantes.COLUMN_ORIGEN_ID, "1");
		contentValues.put(TrayectoConstantes.COLUMN_DESTINO_ID, "3");
		contentValues.put(TrayectoConstantes.COLUMN_HORA, "11:30");
		//contentValues.put(TrayectoConstantes.COLUMN_EMPRESA_ID, "Empresa2");
		
	    // Insert into DB
		long  id2= db.insert(TrayectoConstantes.TABLE_TRAYECTOS, null, contentValues);
		Log.i(LOGTAG,"Se inserto un nuevo trayecto id:"+id2);
		

	}
	

	
	/**
	 * Get all TODOs.
	 * @return
	 */
	public List<Trayecto> getTrayectos() {
		List<Trayecto> trayectoList = new ArrayList<Trayecto>();
		
		// Name of the columns we want to select
		String[] tableColumns = new String[] {TrayectoConstantes.COLUMN_ID,TrayectoConstantes.COLUMN_ORIGEN_ID,TrayectoConstantes.COLUMN_DESTINO_ID,
				TrayectoConstantes.COLUMN_HORA/*,TrayectoConstantes.COLUMN_EMPRESA_ID*/};
		
		// Query the database
		Cursor cursor = db.query(TrayectoConstantes.TABLE_TRAYECTOS, tableColumns, null, null, null, null, null);
		cursor.moveToFirst();
		
		// Iterate the results
	    while (!cursor.isAfterLast()) {
	    	Trayecto trayecto = new Trayecto();
	    	// Take values from the DB
	    	trayecto.setId(cursor.getLong(cursor.getColumnIndex(TrayectoConstantes.COLUMN_ID)));
	    	trayecto.setOrigen_lugar_id(cursor.getLong(cursor.getColumnIndex(TrayectoConstantes.COLUMN_ORIGEN_ID)));
	    	trayecto.setDestino_lugar_id(cursor.getLong(cursor.getColumnIndex(TrayectoConstantes.COLUMN_DESTINO_ID)));
	    	trayecto.setHora(cursor.getString(cursor.getColumnIndex(TrayectoConstantes.COLUMN_HORA)));
	    	
	    	
	    	
	    	// Add to the DB
	    	trayectoList.add(trayecto);
	    	
	    	// Move to the next result
	    	cursor.moveToNext();
	    }
		
		return trayectoList;
	}
	
	
	
}
