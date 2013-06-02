package com.example.comoviajarbeta.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.comoviajarbeta.data.Ruta;
import com.example.comoviajarbeta.sql.ComoViajarSQLiteHelper;
import com.example.comoviajarbeta.utilidades.RutaConstantes;

/**
 * TODOs DAO object.
 * 
 * @author itcuties
 *
 */
public class RutaDataSource {
	private static final String LOGTAG = "LOG_COMOVIAJAR";

	
	private SQLiteDatabase db;
	private ComoViajarSQLiteHelper dbHelper;
	
	public RutaDataSource(Context context) {
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
	public void createRuta(String ruta) {
		ContentValues contentValues = new ContentValues();
		contentValues.put(RutaConstantes.COLUMN_NOMBRE, ruta);
	    // Insert into DB
		db.insert(RutaConstantes.TABLE_RUTAS, null, contentValues);
	}
	
	public Ruta getRuta(long rutaId) {
		  
		  Ruta ruta = null;
		  // Name of the columns we want to select
		  String[] tableColumns = new String[] {RutaConstantes.COLUMN_ID,
		  RutaConstantes.COLUMN_NOMBRE, RutaConstantes.COLUMN_ESTADO,RutaConstantes.COLUMN_IMAGEN};
		  
		  // Query the database
		  Cursor cursor = db.query(RutaConstantes.TABLE_RUTAS, tableColumns, RutaConstantes.COLUMN_ID + "=" + rutaId, null, null, null, null);
		  cursor.moveToFirst();
		  
		  // Iterate the results
		     while (!cursor.isAfterLast()) {
		      ruta = new Ruta();
		      // Take values from the DB
		      ruta.setId(cursor.getLong(cursor.getColumnIndex(RutaConstantes.COLUMN_ID)));
		      ruta.setNombre(cursor.getString(cursor.getColumnIndex(RutaConstantes.COLUMN_NOMBRE)));
		      ruta.setNombre(cursor.getString(cursor.getColumnIndex(RutaConstantes.COLUMN_ESTADO)));
		      ruta.setImagen(cursor.getString(cursor.getColumnIndex(RutaConstantes.COLUMN_IMAGEN)));
		      
		      
		      // Move to the next result
		      cursor.moveToNext();
		     }
		  
		  return ruta;
		}
	/**
	 * Create new TODO object
	 * @param todoText
	 */
	public void createRutasPrueba() {
		ContentValues contentValues = new ContentValues();
		
		contentValues.put(RutaConstantes.COLUMN_NOMBRE, "Ruta 1");
		contentValues.put(RutaConstantes.COLUMN_ESTADO, "Está en buen estado con algunos pozos");
		contentValues.put(RutaConstantes.COLUMN_IMAGEN, "petrobras_logo");
		
	    // Insert into DB
		long  id1= db.insert(RutaConstantes.TABLE_RUTAS, null, contentValues);
		Log.i(LOGTAG,"Se inserto una nueva ruta 2 id:"+id1);
		
		contentValues.put(RutaConstantes.COLUMN_NOMBRE, "Ruta 2");
		contentValues.put(RutaConstantes.COLUMN_ESTADO, "Está desecha en general");
		contentValues.put(RutaConstantes.COLUMN_IMAGEN, "petrobras_logo");
		
	    // Insert into DB
		long  id2= db.insert(RutaConstantes.TABLE_RUTAS, null, contentValues);
		Log.i(LOGTAG,"Se inserto una nueva ruta 2 id:"+id2);
	}
	
	/**
	 * Delete TODO object
	 * @param todoId
	 */
	public void deleteRuta(long rutaId) 
	{
		// Delete from DB where id match
		db.delete(RutaConstantes.TABLE_RUTAS, RutaConstantes.COLUMN_ID+" = " + rutaId, null);
	}
	
	/**
	 * Get all TODOs.
	 * @return
	 */
	public List<Ruta> getRutas() {
		List<Ruta> rutaList = new ArrayList<Ruta>();
		
		// Name of the columns we want to select
		String[] tableColumns = new String[] {RutaConstantes.COLUMN_ID,RutaConstantes.COLUMN_NOMBRE,
				RutaConstantes.COLUMN_IMAGEN,RutaConstantes.COLUMN_ESTADO};
		
		// Query the database
		Cursor cursor = db.query(RutaConstantes.TABLE_RUTAS, tableColumns, null, null, null, null, null);
		cursor.moveToFirst();
		
		// Iterate the results
	    while (!cursor.isAfterLast()) {
	    	Ruta ruta = new Ruta();
	    	// Take values from the DB
	    	ruta.setId(cursor.getLong(cursor.getColumnIndex(RutaConstantes.COLUMN_ID)));
	    	ruta.setNombre(cursor.getString(cursor.getColumnIndex(RutaConstantes.COLUMN_NOMBRE)));
	    	ruta.setEstado(cursor.getString(cursor.getColumnIndex(RutaConstantes.COLUMN_ESTADO)));
	    	ruta.setImagen(cursor.getString(cursor.getColumnIndex(RutaConstantes.COLUMN_IMAGEN)));
	    	
	    	
	    	
	    	// Add to the DB
	    	rutaList.add(ruta);
	    	
	    	// Move to the next result
	    	cursor.moveToNext();
	    }
		
		return rutaList;
	}
	
	
	
}
