package com.example.comoviajarbeta.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.comoviajarbeta.data.Estacion;
import com.example.comoviajarbeta.sql.ComoViajarSQLiteHelper;
import com.example.comoviajarbeta.utilidades.EstacionConstantes;

/**
 * TODOs DAO object.
 * 
 * @author itcuties
 *
 */
public class EstacionDataSource {
	private static final String LOGTAG = "LOG_COMOVIAJAR";

	
	private SQLiteDatabase db;
	private ComoViajarSQLiteHelper dbHelper;
	
	public EstacionDataSource(Context context) {
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
	public void createEstacion(String nombre) {
		ContentValues contentValues = new ContentValues();
		contentValues.put(EstacionConstantes.COLUMN_NOMBRE, nombre);
	    // Insert into DB
		db.insert(EstacionConstantes.TABLE_ESTACIONES, null, contentValues);
	}
	
	/**
	 * Create new TODO object
	 * @param todoText
	 */
	public void createEstacionesPrueba() 
	{
		  ContentValues contentValues = new ContentValues();
		  
		  //Empresa 1 Esso,Empresa 2 Ancap, Empresa 3 Petrobras
		  //Lugar 1 Montevideo, Lugar 2 Colonia
		  
		  contentValues.put(EstacionConstantes.COLUMN_DEPARTAMENTO, 1);
		  contentValues.put(EstacionConstantes.COLUMN_NOMBRE, "Amezaga");
		  contentValues.put(EstacionConstantes.COLUMN_DIRECCION, "Amezaga y democracia");
		  contentValues.put(EstacionConstantes.COLUMN_TELEFONO, "0202020220");
		  contentValues.put(EstacionConstantes.COLUMN_IMAGEN, "esso_logo");
		  contentValues.put(EstacionConstantes.COLUMN_LATITUD, "-34.886697");
		  contentValues.put(EstacionConstantes.COLUMN_LONGITUD, "-56.177366");
		  contentValues.put(EstacionConstantes.COLUMN_EMPRESA, "1");
		  
		  // Insert into DB
		  long  id= db.insert(EstacionConstantes.TABLE_ESTACIONES, null, contentValues);
		  Log.i(LOGTAG,"Se inserto una nueva estacion 1 id:"+id);
		  
		  
		  contentValues.put(EstacionConstantes.COLUMN_DEPARTAMENTO, 2);
		  contentValues.put(EstacionConstantes.COLUMN_NOMBRE, "Canelones");
		  contentValues.put(EstacionConstantes.COLUMN_DIRECCION, "Av. de los pinos y ruta 10 ");
		  contentValues.put(EstacionConstantes.COLUMN_TELEFONO, "0202020220");
		  contentValues.put(EstacionConstantes.COLUMN_IMAGEN, "ancap_logo");
		  contentValues.put(EstacionConstantes.COLUMN_LATITUD, "-34.787761");
		  contentValues.put(EstacionConstantes.COLUMN_LONGITUD, "-55.860415");
		  contentValues.put(EstacionConstantes.COLUMN_EMPRESA, "2");
		  
		     // Insert into DB
		  long  id2= db.insert(EstacionConstantes.TABLE_ESTACIONES, null, contentValues);
		  
		  Log.i(LOGTAG,"Se inserto una nueva estacion 2 id:"+id2);
		  
		  contentValues.put(EstacionConstantes.COLUMN_DEPARTAMENTO, 3);
		  contentValues.put(EstacionConstantes.COLUMN_NOMBRE, "Maldonado");
		  contentValues.put(EstacionConstantes.COLUMN_DIRECCION, "El trinquete y Isla de lobos ");
		  contentValues.put(EstacionConstantes.COLUMN_TELEFONO, "0202020220");
		  contentValues.put(EstacionConstantes.COLUMN_IMAGEN, "petrobras_logo");
		  contentValues.put(EstacionConstantes.COLUMN_LATITUD, "-34.970533");
		  contentValues.put(EstacionConstantes.COLUMN_LONGITUD, "-54.953002");
		  contentValues.put(EstacionConstantes.COLUMN_EMPRESA, "2");
		  
		     // Insert into DB
		  long  id3= db.insert(EstacionConstantes.TABLE_ESTACIONES, null, contentValues);
		  
		  Log.i(LOGTAG,"Se inserto una nueva estacion 3 id:"+id3);
		 }
	
	/**
	 * Delete TODO object
	 * @param todoId
	 */
	public void deleteEstacion(long estacionId) {
		// Delete from DB where id match
		db.delete(EstacionConstantes.TABLE_ESTACIONES, EstacionConstantes.COLUMN_ID+" = " + estacionId, null);
	}
	
	public Estacion getEstacion(long estacionId) {
		  
		  Estacion estacion = null;
		  // Name of the columns we want to select
		  String[] tableColumns = new String[] {EstacionConstantes.COLUMN_ID,EstacionConstantes.COLUMN_NOMBRE,
		    EstacionConstantes.COLUMN_EMPRESA,EstacionConstantes.COLUMN_DIRECCION,EstacionConstantes.COLUMN_IMAGEN,
		    EstacionConstantes.COLUMN_LATITUD,EstacionConstantes.COLUMN_LONGITUD,
		    EstacionConstantes.COLUMN_DEPARTAMENTO,EstacionConstantes.COLUMN_TELEFONO};
		  
		  // Query the database
		  Cursor cursor = db.query(EstacionConstantes.TABLE_ESTACIONES, tableColumns, EstacionConstantes.COLUMN_ID + "=" + estacionId, null, null, null, null);
		  cursor.moveToFirst();
		  
		  // Iterate the results
		     while (!cursor.isAfterLast()) {
		      estacion = new Estacion();
		      // Take values from the DB
		      estacion.setId(cursor.getLong(cursor.getColumnIndex(EstacionConstantes.COLUMN_ID)));
		      estacion.setDepartamentoId(cursor.getLong(cursor.getColumnIndex(EstacionConstantes.COLUMN_DEPARTAMENTO)));
		      estacion.setNombre(cursor.getString(cursor.getColumnIndex(EstacionConstantes.COLUMN_NOMBRE)));
		      estacion.setDireccion(cursor.getString(cursor.getColumnIndex(EstacionConstantes.COLUMN_DIRECCION)));
		      estacion.setTelefono(cursor.getString(cursor.getColumnIndex(EstacionConstantes.COLUMN_TELEFONO)));
		      estacion.setImagen(cursor.getString(cursor.getColumnIndex(EstacionConstantes.COLUMN_IMAGEN)));
		      estacion.setLatitud(cursor.getString(cursor.getColumnIndex(EstacionConstantes.COLUMN_LATITUD)));
		      estacion.setLongitud(cursor.getString(cursor.getColumnIndex(EstacionConstantes.COLUMN_LONGITUD)));
		      estacion.setEmpresa(cursor.getString(cursor.getColumnIndex(EstacionConstantes.COLUMN_EMPRESA)));
		      
		      
		      // Move to the next result
		      cursor.moveToNext();
		     }
		  
		  return estacion;
		 }
	
	
	/**
	 * Get all TODOs.
	 * @return
	 */
	public List<Estacion> getEstaciones() {
		List<Estacion> todoList = new ArrayList<Estacion>();
		
		// Name of the columns we want to select
		String[] tableColumns = new String[] {EstacionConstantes.COLUMN_ID,EstacionConstantes.COLUMN_NOMBRE,
				EstacionConstantes.COLUMN_EMPRESA,EstacionConstantes.COLUMN_DIRECCION,EstacionConstantes.COLUMN_IMAGEN,
				EstacionConstantes.COLUMN_LATITUD,EstacionConstantes.COLUMN_LONGITUD,
				EstacionConstantes.COLUMN_DEPARTAMENTO,EstacionConstantes.COLUMN_TELEFONO};
		
		// Query the database
		Cursor cursor = db.query(EstacionConstantes.TABLE_ESTACIONES, tableColumns, null, null, null, null, null);
		cursor.moveToFirst();
		
		// Iterate the results
	    while (!cursor.isAfterLast()) {
	    	Estacion estacion = new Estacion();
	    	// Take values from the DB
	    	estacion.setId(cursor.getLong(cursor.getColumnIndex(EstacionConstantes.COLUMN_ID)));
	    	estacion.setDepartamentoId(cursor.getLong(cursor.getColumnIndex(EstacionConstantes.COLUMN_DEPARTAMENTO)));
	    	estacion.setNombre(cursor.getString(cursor.getColumnIndex(EstacionConstantes.COLUMN_NOMBRE)));
	    	estacion.setDireccion(cursor.getString(cursor.getColumnIndex(EstacionConstantes.COLUMN_DIRECCION)));
	    	estacion.setTelefono(cursor.getString(cursor.getColumnIndex(EstacionConstantes.COLUMN_TELEFONO)));
	    	estacion.setImagen(cursor.getString(cursor.getColumnIndex(EstacionConstantes.COLUMN_IMAGEN)));
	    	estacion.setLatitud(cursor.getString(cursor.getColumnIndex(EstacionConstantes.COLUMN_LATITUD)));
	    	estacion.setLongitud(cursor.getString(cursor.getColumnIndex(EstacionConstantes.COLUMN_LONGITUD)));
	    	estacion.setEmpresa(cursor.getString(cursor.getColumnIndex(EstacionConstantes.COLUMN_EMPRESA)));
	    	

	    	
	    	
	    	
	    	// Add to the DB
	    	todoList.add(estacion);
	    	
	    	// Move to the next result
	    	cursor.moveToNext();
	    }
		
		return todoList;
	}
	
	public List<Estacion> getEstacionesDepartamento(long departamentoId) {
		List<Estacion> todoList = new ArrayList<Estacion>();
		
		// Name of the columns we want to select
		String[] tableColumns = new String[] {EstacionConstantes.COLUMN_ID,EstacionConstantes.COLUMN_NOMBRE,
				EstacionConstantes.COLUMN_EMPRESA,EstacionConstantes.COLUMN_DIRECCION,EstacionConstantes.COLUMN_IMAGEN,
				EstacionConstantes.COLUMN_LATITUD,EstacionConstantes.COLUMN_LONGITUD,
				EstacionConstantes.COLUMN_DEPARTAMENTO,EstacionConstantes.COLUMN_TELEFONO};
		
		// Query the database
		Cursor cursor = db.query(EstacionConstantes.TABLE_ESTACIONES, tableColumns, EstacionConstantes.COLUMN_DEPARTAMENTO + "=" + departamentoId, null, null, null, null);
		cursor.moveToFirst();
		
		// Iterate the results
	    while (!cursor.isAfterLast()) {
	    	Estacion estacion = new Estacion();
	    	// Take values from the DB
	    	estacion.setId(cursor.getLong(cursor.getColumnIndex(EstacionConstantes.COLUMN_ID)));
	    	estacion.setDepartamentoId(cursor.getLong(cursor.getColumnIndex(EstacionConstantes.COLUMN_DEPARTAMENTO)));
	    	estacion.setNombre(cursor.getString(cursor.getColumnIndex(EstacionConstantes.COLUMN_NOMBRE)));
	    	estacion.setDireccion(cursor.getString(cursor.getColumnIndex(EstacionConstantes.COLUMN_DIRECCION)));
	    	estacion.setTelefono(cursor.getString(cursor.getColumnIndex(EstacionConstantes.COLUMN_TELEFONO)));
	    	estacion.setImagen(cursor.getString(cursor.getColumnIndex(EstacionConstantes.COLUMN_IMAGEN)));
	    	estacion.setLatitud(cursor.getString(cursor.getColumnIndex(EstacionConstantes.COLUMN_LATITUD)));
	    	estacion.setLongitud(cursor.getString(cursor.getColumnIndex(EstacionConstantes.COLUMN_LONGITUD)));
	    	estacion.setEmpresa(cursor.getString(cursor.getColumnIndex(EstacionConstantes.COLUMN_EMPRESA)));
	    	
	    	// Add to the DB
	    	todoList.add(estacion);
	    	
	    	// Move to the next result
	    	cursor.moveToNext();
	    }
		
		return todoList;
	}
	
}
