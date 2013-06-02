package com.example.comoviajarbeta.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.comoviajarbeta.data.Lugar;
import com.example.comoviajarbeta.sql.ComoViajarSQLiteHelper;
import com.example.comoviajarbeta.utilidades.LugarConstantes;
import com.example.comoviajarbeta.utilidades.TrayectoConstantes;

/**
 * TODOs DAO object.
 * 
 * @author itcuties
 *
 */
public class LugarDataSource {
	private static final String LOGTAG = "LOG_COMOVIAJAR";

	
	private SQLiteDatabase db;
	private ComoViajarSQLiteHelper dbHelper;
	
	public LugarDataSource(Context context) {
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
	public void createLugar(String nombre) {
		ContentValues contentValues = new ContentValues();
		contentValues.put(LugarConstantes.COLUMN_NOMBRE, nombre);
	    // Insert into DB
		db.insert(LugarConstantes.TABLE_LUGARES, null, contentValues);
	}
	
	/**
	 * Create new TODO object
	 * @param todoText
	 */
	public void createEmpresasPrueba() {
		ContentValues contentValues = new ContentValues();
		
		contentValues.put(LugarConstantes.COLUMN_DEPARTAMENTO, 2);
		contentValues.put(LugarConstantes.COLUMN_NOMBRE, "Rocha");
		contentValues.put(LugarConstantes.COLUMN_LATITUD, "-34.436823");
		contentValues.put(LugarConstantes.COLUMN_LONGITUD, "-57.862952");

		
	    // Insert into DB
		long  id1= db.insert(LugarConstantes.TABLE_LUGARES, null, contentValues);
		Log.i(LOGTAG,"Se inserto un nuevo lugar 1 id:"+id1);
		
		contentValues.put(LugarConstantes.COLUMN_DEPARTAMENTO, 2);
		contentValues.put(LugarConstantes.COLUMN_NOMBRE, "La Pedrera");
		contentValues.put(LugarConstantes.COLUMN_LATITUD, "-34.436823");
		contentValues.put(LugarConstantes.COLUMN_LONGITUD, "-57.862952");

		
	    // Insert into DB
		long  id2= db.insert(LugarConstantes.TABLE_LUGARES, null, contentValues);
		Log.i(LOGTAG,"Se inserto un nuevo lugar 2 id:"+id2);
		
		contentValues.put(LugarConstantes.COLUMN_DEPARTAMENTO, 1);
		contentValues.put(LugarConstantes.COLUMN_NOMBRE, "Montevideo");
		contentValues.put(LugarConstantes.COLUMN_LATITUD, "-34.436823");
		contentValues.put(LugarConstantes.COLUMN_LONGITUD, "-57.862952");

		
	    // Insert into DB
		long  id3= db.insert(LugarConstantes.TABLE_LUGARES, null, contentValues);
		Log.i(LOGTAG,"Se inserto un nuevo lugar 3 id:"+id3);
	}
	
	/**
	 * Delete TODO object
	 * @param todoId
	 */
	public void deleteLugar(long lugarId) {
		// Delete from DB where id match
		db.delete(LugarConstantes.TABLE_LUGARES, LugarConstantes.COLUMN_ID+" = " + lugarId, null);
	}
	
	/**
	 * Get all TODOs.
	 * @return
	 */
	public List<Lugar> getLugares() {
		List<Lugar> lugarList = new ArrayList<Lugar>();
		
		// Name of the columns we want to select
		String[] tableColumns = new String[] {LugarConstantes.COLUMN_ID,LugarConstantes.COLUMN_DEPARTAMENTO,LugarConstantes.COLUMN_NOMBRE,
				LugarConstantes.COLUMN_LATITUD,LugarConstantes.COLUMN_LONGITUD};
		
		// Query the database
		Cursor cursor = db.query(LugarConstantes.TABLE_LUGARES, tableColumns, null, null, null, null, null);
		cursor.moveToFirst();
		
		// Iterate the results
	    while (!cursor.isAfterLast()) {
	    	Lugar lugar = new Lugar();
	    	// Take values from the DB
	    	lugar.setId(cursor.getLong(cursor.getColumnIndex(LugarConstantes.COLUMN_ID)));
			lugar.setDepartamentoId(cursor.getLong(cursor.getColumnIndex(LugarConstantes.COLUMN_DEPARTAMENTO)));
	    	lugar.setNombre(cursor.getString(cursor.getColumnIndex(LugarConstantes.COLUMN_NOMBRE)));
	    	lugar.setLatitud(cursor.getString(cursor.getColumnIndex(LugarConstantes.COLUMN_LATITUD)));
	    	lugar.setLongitud(cursor.getString(cursor.getColumnIndex(LugarConstantes.COLUMN_LONGITUD)));
	    	
	    	
	    	
	    	// Add to the DB
	    	lugarList.add(lugar);
	    	
	    	// Move to the next result
	    	cursor.moveToNext();
	    }
		
		return lugarList;
	}
	
	public List<Lugar> getDestinos(String origenId) {
		List<Lugar> lugarList = new ArrayList<Lugar>();
		
		// Name of the columns we want to select
		String[] tableColumns = new String[] {LugarConstantes.COLUMN_ID,LugarConstantes.COLUMN_DEPARTAMENTO,LugarConstantes.COLUMN_NOMBRE,
				LugarConstantes.COLUMN_LATITUD,LugarConstantes.COLUMN_LONGITUD};
		
		// Query the database
//		Cursor cursor = db.query(LugarConstantes.TABLE_LUGARES, tableColumns, null, null, null, null, null);
		
		//Select * from lugar
		//where lugar.id in ( select trayecto.detino where trayecto.origen = parametro)
		
		String myQuery = "SELECT * FROM " + LugarConstantes.TABLE_LUGARES + " WHERE " + LugarConstantes.COLUMN_ID + 
				" IN (SELECT "+ TrayectoConstantes.COLUMN_DESTINO_ID + " FROM "+ TrayectoConstantes.TABLE_TRAYECTOS+ " WHERE " + TrayectoConstantes.COLUMN_ORIGEN_ID + " = " + origenId + ");";
		
		
		Cursor cursor = db.rawQuery(myQuery, null);
		cursor.moveToFirst();
		
		
		
		// Iterate the results
	    while (!cursor.isAfterLast()) {
	    	Lugar lugar = new Lugar();
	    	// Take values from the DB
	    	lugar.setId(cursor.getLong(cursor.getColumnIndex(LugarConstantes.COLUMN_ID)));
			lugar.setDepartamentoId(cursor.getLong(cursor.getColumnIndex(LugarConstantes.COLUMN_DEPARTAMENTO)));
	    	lugar.setNombre(cursor.getString(cursor.getColumnIndex(LugarConstantes.COLUMN_NOMBRE)));
	    	lugar.setLatitud(cursor.getString(cursor.getColumnIndex(LugarConstantes.COLUMN_LATITUD)));
	    	lugar.setLongitud(cursor.getString(cursor.getColumnIndex(LugarConstantes.COLUMN_LONGITUD)));
	    	
	    	
	    	
	    	// Add to the DB
	    	lugarList.add(lugar);
	    	
	    	// Move to the next result
	    	cursor.moveToNext();
	    }
		
		return lugarList;
	}
}
