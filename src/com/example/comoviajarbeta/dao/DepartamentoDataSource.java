package com.example.comoviajarbeta.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.comoviajarbeta.data.Departamento;
import com.example.comoviajarbeta.sql.ComoViajarSQLiteHelper;
import com.example.comoviajarbeta.utilidades.DepartamentoConstantes;

/**
 * TODOs DAO object.
 * 
 * @author itcuties
 *
 */
public class DepartamentoDataSource {
	private static final String LOGTAG = "LOG_COMOVIAJAR";

	
	private SQLiteDatabase db;
	private ComoViajarSQLiteHelper dbHelper;
	
	public DepartamentoDataSource(Context context) {
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
	public void createDepartamento(String nombre) {
		ContentValues contentValues = new ContentValues();
		contentValues.put(DepartamentoConstantes.COLUMN_NOMBRE, nombre);
	    // Insert into DB
		db.insert(DepartamentoConstantes.TABLE_DEPARTAMENTOS, null, contentValues);
	}
	
	/**
	 * Create new TODO object
	 * @param todoText
	 */
	public void createDepartamentosPrueba() {
		ContentValues contentValues = new ContentValues();
		
		contentValues.put(DepartamentoConstantes.COLUMN_NOMBRE, "Montevideo");

	    // Insert into DB
		long  id= db.insert(DepartamentoConstantes.TABLE_DEPARTAMENTOS, null, contentValues);
		Log.i(LOGTAG,"Se inserto una nueva estacion 1 id:"+id);
		
		contentValues.put(DepartamentoConstantes.COLUMN_NOMBRE, "Canelones");

	    // Insert into DB
		long  id2= db.insert(DepartamentoConstantes.TABLE_DEPARTAMENTOS, null, contentValues);
		Log.i(LOGTAG,"Se inserto una nueva estacion 1 id:"+id2);
		
		contentValues.put(DepartamentoConstantes.COLUMN_NOMBRE, "Rivera");

	    // Insert into DB
		long  id3= db.insert(DepartamentoConstantes.TABLE_DEPARTAMENTOS, null, contentValues);
		Log.i(LOGTAG,"Se inserto una nueva estacion 1 id:"+id3);
		
	}
	
	/**
	 * Delete TODO object
	 * @param todoId
	 */
	public void deleteDepartamento(long departamentoId) {
		// Delete from DB where id match
		db.delete(DepartamentoConstantes.TABLE_DEPARTAMENTOS, DepartamentoConstantes.COLUMN_ID+" = " + departamentoId, null);
	}
	
	/**
	 * Get all TODOs.
	 * @return
	 */
	public List<Departamento> getDepartamentos() {
		List<Departamento> todoList = new ArrayList<Departamento>();
		
		// Name of the columns we want to select
		String[] tableColumns = new String[] {DepartamentoConstantes.COLUMN_ID,DepartamentoConstantes.COLUMN_NOMBRE};

		
		// Query the database
		Cursor cursor = db.query(DepartamentoConstantes.TABLE_DEPARTAMENTOS, tableColumns, null, null, null, null, null);
		cursor.moveToFirst();
		
		// Iterate the results
	    while (!cursor.isAfterLast()) {
	    	Departamento departamento = new Departamento();
	    	// Take values from the DB
	    	departamento.setId(cursor.getLong(cursor.getColumnIndex(DepartamentoConstantes.COLUMN_ID)));
	    	departamento.setNombre(cursor.getString(cursor.getColumnIndex(DepartamentoConstantes.COLUMN_NOMBRE)));
	    	
	    	// Add to the DB
	    	todoList.add(departamento);
	    	
	    	// Move to the next result
	    	cursor.moveToNext();
	    }
		
		return todoList;
	}
	
}
