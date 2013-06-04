package com.example.comoviajarbeta.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.comoviajarbeta.data.Transporte;
import com.example.comoviajarbeta.sql.ComoViajarSQLiteHelper;
import com.example.comoviajarbeta.utilidades.TransporteConstantes;

/**
 * TODOs DAO object.
 * 
 * @author itcuties
 *
 */
public class TransporteDataSource {
	private static final String LOGTAG = "LOG_COMOVIAJAR";

	
	private SQLiteDatabase db;
	private ComoViajarSQLiteHelper dbHelper;
	
	public TransporteDataSource(Context context) {
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
	public void createTransporte(String descripcion) {
		ContentValues contentValues = new ContentValues();
		contentValues.put(TransporteConstantes.COLUMN_DESCRIPCION, descripcion);
	    // Insert into DB
		db.insert(TransporteConstantes.COLUMN_DESCRIPCION, null, contentValues);
	}
	
	/**
	 * Create new TODO object
	 * @param todoText
	 */
	public void createTransportesPrueba() {
		ContentValues contentValues = new ContentValues();
		
		contentValues.put(TransporteConstantes.COLUMN_DESCRIPCION, "Omnibus");
	    // Insert into DB
		long  id1= db.insert(TransporteConstantes.TABLE_TRANSPORTES, null, contentValues);
		Log.i(LOGTAG,"Se inserto un nuevo transporte 1 id: " + id1);
		
		contentValues.put(TransporteConstantes.COLUMN_DESCRIPCION, "Barco");
	    // Insert into DB
		long  id2= db.insert(TransporteConstantes.TABLE_TRANSPORTES, null, contentValues);
		Log.i(LOGTAG,"Se inserto un nuevo transporte 2 id: " + id2);
		
		contentValues.put(TransporteConstantes.COLUMN_DESCRIPCION, "Avion");
		// Insert into DB
		long  id3= db.insert(TransporteConstantes.TABLE_TRANSPORTES, null, contentValues);
		Log.i(LOGTAG,"Se inserto un nuevo transporte 3 id: " + id3);
	}
	
	/**
	 * Delete TODO object
	 * @param todoId
	 */
	public void deleteTransporte(long transporteId) {
		// Delete from DB where id match
		db.delete(TransporteConstantes.TABLE_TRANSPORTES, TransporteConstantes.COLUMN_ID + " = " + transporteId, null);
	}
	
	/**
	 * Get all TODOs.
	 * @return
	 */
	public List<Transporte> getTransporte() {
		List<Transporte> transporteList = new ArrayList<Transporte>();
		
		// Name of the columns we want to select
		String[] tableColumns = new String[] {TransporteConstantes.COLUMN_ID ,TransporteConstantes.COLUMN_DESCRIPCION};
		
		// Query the database
		Cursor cursor = db.query(TransporteConstantes.TABLE_TRANSPORTES , tableColumns, null, null, null, null, null);
		cursor.moveToFirst();
		
		// Iterate the results
	    while (!cursor.isAfterLast()) {
	    	Transporte transporte = new Transporte();
	    	// Take values from the DB
	    	transporte.setId(cursor.getLong(cursor.getColumnIndex(TransporteConstantes.COLUMN_ID)));
	    	transporte.setDescripcion(cursor.getString(cursor.getColumnIndex(TransporteConstantes.COLUMN_DESCRIPCION)));
	    	
	    	// Add to the DB
	    	transporteList.add(transporte);
	    	
	    	// Move to the next result
	    	cursor.moveToNext();
	    }
		
		return transporteList;
	}
}
