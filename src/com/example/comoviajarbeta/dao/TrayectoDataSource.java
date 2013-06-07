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
		
		open();
		
		ContentValues contentValues = new ContentValues();
		
		contentValues.put(TrayectoConstantes.COLUMN_ORIGEN_ID, "1");
		contentValues.put(TrayectoConstantes.COLUMN_DESTINO_ID, "2");
		contentValues.put(TrayectoConstantes.COLUMN_HORA, "10:30");
		contentValues.put(TrayectoConstantes.COLUMN_FRECUENCIA, "Omnibus");
		contentValues.put(TrayectoConstantes.COLUMN_EMPRESA_ID, "1");
		contentValues.put(TrayectoConstantes.COLUMN_TIPOTRANSPORTE_ID, "1");
		
	    // Insert into DB
		long  id= db.insert(TrayectoConstantes.TABLE_TRAYECTOS, null, contentValues);
		Log.i(LOGTAG,"Se inserto un nuevo trayecto id:"+id);
		
		contentValues.put(TrayectoConstantes.COLUMN_ORIGEN_ID, "1");
		contentValues.put(TrayectoConstantes.COLUMN_DESTINO_ID, "3");
		contentValues.put(TrayectoConstantes.COLUMN_HORA, "11:30");
		contentValues.put(TrayectoConstantes.COLUMN_FRECUENCIA, "Barco");
		contentValues.put(TrayectoConstantes.COLUMN_EMPRESA_ID, "1");
		contentValues.put(TrayectoConstantes.COLUMN_TIPOTRANSPORTE_ID, "2");
		
	    // Insert into DB
		long  id2= db.insert(TrayectoConstantes.TABLE_TRAYECTOS, null, contentValues);
		Log.i(LOGTAG,"Se inserto un nuevo trayecto id:"+id2);
		
		contentValues.put(TrayectoConstantes.COLUMN_ORIGEN_ID, "2");
		contentValues.put(TrayectoConstantes.COLUMN_DESTINO_ID, "1");
		contentValues.put(TrayectoConstantes.COLUMN_HORA, "23:30");
		contentValues.put(TrayectoConstantes.COLUMN_FRECUENCIA, "Omnibus");
		contentValues.put(TrayectoConstantes.COLUMN_EMPRESA_ID, "1");
		contentValues.put(TrayectoConstantes.COLUMN_TIPOTRANSPORTE_ID, "1");
		
	    // Insert into DB
		long  id3= db.insert(TrayectoConstantes.TABLE_TRAYECTOS, null, contentValues);
		Log.i(LOGTAG,"Se inserto un nuevo trayecto id:"+id3);
		
		//mismo trayecto distinto medio de transporte
		contentValues.put(TrayectoConstantes.COLUMN_ORIGEN_ID, "1");
		contentValues.put(TrayectoConstantes.COLUMN_DESTINO_ID, "2");
		contentValues.put(TrayectoConstantes.COLUMN_HORA, "10:30");
		contentValues.put(TrayectoConstantes.COLUMN_FRECUENCIA, "Barco");
		contentValues.put(TrayectoConstantes.COLUMN_EMPRESA_ID, "1");
		contentValues.put(TrayectoConstantes.COLUMN_TIPOTRANSPORTE_ID, "2");
		
	    // Insert into DB
		long  id4= db.insert(TrayectoConstantes.TABLE_TRAYECTOS, null, contentValues);
		Log.i(LOGTAG,"Se inserto un nuevo trayecto id:"+id4);
		
		contentValues.put(TrayectoConstantes.COLUMN_ORIGEN_ID, "2");
		contentValues.put(TrayectoConstantes.COLUMN_DESTINO_ID, "1");
		contentValues.put(TrayectoConstantes.COLUMN_HORA, "11:30");
		contentValues.put(TrayectoConstantes.COLUMN_FRECUENCIA, "Barco");
		contentValues.put(TrayectoConstantes.COLUMN_EMPRESA_ID, "1");
		contentValues.put(TrayectoConstantes.COLUMN_TIPOTRANSPORTE_ID, "2");
		
	    // Insert into DB
		long  id5= db.insert(TrayectoConstantes.TABLE_TRAYECTOS, null, contentValues);
		Log.i(LOGTAG,"Se inserto un nuevo trayecto id:"+id5);
		
		close();

	}
	

	
	/**
	 * Get all TODOs.
	 * @return
	 */
	public List<Trayecto> getTrayectos() {
		List<Trayecto> trayectoList = new ArrayList<Trayecto>();
		
		// Name of the columns we want to select
		String[] tableColumns = new String[] {TrayectoConstantes.COLUMN_ID,TrayectoConstantes.COLUMN_ORIGEN_ID,TrayectoConstantes.COLUMN_DESTINO_ID,
				TrayectoConstantes.COLUMN_HORA,TrayectoConstantes.COLUMN_FRECUENCIA,TrayectoConstantes.COLUMN_EMPRESA_ID,TrayectoConstantes.COLUMN_TIPOTRANSPORTE_ID};
		
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
	     	trayecto.setFrecuencia(cursor.getString(cursor.getColumnIndex(TrayectoConstantes.COLUMN_FRECUENCIA)));
	     	trayecto.setEmpresa_id(cursor.getLong(cursor.getColumnIndex(TrayectoConstantes.COLUMN_EMPRESA_ID)));
	     	trayecto.setTipotransporte_id(cursor.getLong(cursor.getColumnIndex(TrayectoConstantes.COLUMN_TIPOTRANSPORTE_ID)));
	    	
	    	// Add to the DB
	    	trayectoList.add(trayecto);
	    	
	    	// Move to the next result
	    	cursor.moveToNext();
	    }
	    
	    close();
		
		return trayectoList;
	}
	
	public List<Trayecto> getTrayectosTransportes(String idOrigen, String idDestino, String[] transportes) {
		List<Trayecto> trayectoList = new ArrayList<Trayecto>();
		
		// Name of the columns we want to select
		String[] tableColumns = new String[] {TrayectoConstantes.COLUMN_ID,TrayectoConstantes.COLUMN_ORIGEN_ID,TrayectoConstantes.COLUMN_DESTINO_ID,
				TrayectoConstantes.COLUMN_HORA,TrayectoConstantes.COLUMN_FRECUENCIA,TrayectoConstantes.COLUMN_EMPRESA_ID,TrayectoConstantes.COLUMN_TIPOTRANSPORTE_ID};
		        
		   
		String tq = "( ";
		Cursor cursor = null;
		// Query the database
		if( transportes.length > 1)
		{
			
			for (int i = 0; i<transportes.length; i++)
			{
				tq = tq + TrayectoConstantes.COLUMN_TIPOTRANSPORTE_ID + "=" + transportes[i];
				if(i < transportes.length - 1)
				{
					tq = tq + " OR ";
				}
			}
			
			tq = tq + " )";
			
			cursor = db.query(TrayectoConstantes.TABLE_TRAYECTOS, tableColumns, TrayectoConstantes.COLUMN_ORIGEN_ID+"="+idOrigen + " AND " + TrayectoConstantes.COLUMN_DESTINO_ID + "=" + idDestino + " AND " + tq, null, null, null, null);
			
		}
		else
		{
			cursor = db.query(TrayectoConstantes.TABLE_TRAYECTOS, tableColumns, TrayectoConstantes.COLUMN_ORIGEN_ID+"="+idOrigen + " AND " + TrayectoConstantes.COLUMN_DESTINO_ID + "=" + idDestino + " AND " + TrayectoConstantes.COLUMN_TIPOTRANSPORTE_ID+"=?", transportes, null, null, null);	
		}
		 
		
		cursor.moveToFirst();
		
		// Iterate the results
	    while (!cursor.isAfterLast()) {
	    	Trayecto trayecto = new Trayecto();
	    	// Take values from the DB
	    	trayecto.setId(cursor.getLong(cursor.getColumnIndex(TrayectoConstantes.COLUMN_ID)));
	    	trayecto.setOrigen_lugar_id(cursor.getLong(cursor.getColumnIndex(TrayectoConstantes.COLUMN_ORIGEN_ID)));
	    	trayecto.setDestino_lugar_id(cursor.getLong(cursor.getColumnIndex(TrayectoConstantes.COLUMN_DESTINO_ID)));
	    	trayecto.setHora(cursor.getString(cursor.getColumnIndex(TrayectoConstantes.COLUMN_HORA)));
	     	trayecto.setFrecuencia(cursor.getString(cursor.getColumnIndex(TrayectoConstantes.COLUMN_FRECUENCIA)));
	     	trayecto.setEmpresa_id(cursor.getLong(cursor.getColumnIndex(TrayectoConstantes.COLUMN_EMPRESA_ID)));
	     	trayecto.setTipotransporte_id(cursor.getLong(cursor.getColumnIndex(TrayectoConstantes.COLUMN_TIPOTRANSPORTE_ID)));
	    	
	    	// Add to the DB
	    	trayectoList.add(trayecto);
	    	
	    	// Move to the next result
	    	cursor.moveToNext();
	    }
	    
	    close();
		
		return trayectoList;
	}
	
	
	
}
