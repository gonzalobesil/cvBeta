package com.example.comoviajarbeta.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.comoviajarbeta.data.Empresa;
import com.example.comoviajarbeta.sql.ComoViajarSQLiteHelper;
import com.example.comoviajarbeta.utilidades.EmpresaConstantes;

/**
 * TODOs DAO object.
 * 
 * @author itcuties
 *
 */
public class EmpresaDataSource {
	private static final String LOGTAG = "LOG_COMOVIAJAR";

	
	private SQLiteDatabase db;
	private ComoViajarSQLiteHelper dbHelper;
	
	public EmpresaDataSource(Context context) {
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
	public void createEmpresa(String empresa) {
		ContentValues contentValues = new ContentValues();
		contentValues.put(EmpresaConstantes.COLUMN_NOMBRE, empresa);
	    // Insert into DB
		db.insert(EmpresaConstantes.TABLE_EMPRESAS, null, contentValues);
	}
	
	/**
	 * Create new TODO object
	 * @param todoText
	 */
	public void createEmpresasPrueba() {
		ContentValues contentValues = new ContentValues();
		
		contentValues.put(EmpresaConstantes.COLUMN_NOMBRE, "Ega");
		contentValues.put(EmpresaConstantes.COLUMN_TELEFONO, "0202020220");
		contentValues.put(EmpresaConstantes.COLUMN_IMAGEN, "petrobras_logo");
		
	    // Insert into DB
		long  id= db.insert(EmpresaConstantes.TABLE_EMPRESAS, null, contentValues);
		Log.i(LOGTAG,"Se inserto una nueva empresa 1 id:"+id);
		
		
		contentValues.put(EmpresaConstantes.COLUMN_NOMBRE, "Nuñez");
		contentValues.put(EmpresaConstantes.COLUMN_TELEFONO, "0202020221");
		contentValues.put(EmpresaConstantes.COLUMN_IMAGEN, "petrobras_logo");
		
	    // Insert into DB
		long  id2= db.insert(EmpresaConstantes.TABLE_EMPRESAS, null, contentValues);
		Log.i(LOGTAG,"Se inserto una nueva empresa 2 id:"+id2);
		
		contentValues.put(EmpresaConstantes.COLUMN_NOMBRE, "Cacciola");
		contentValues.put(EmpresaConstantes.COLUMN_TELEFONO, "0202020221");
		contentValues.put(EmpresaConstantes.COLUMN_IMAGEN, "petrobras_logo");
		
	    // Insert into DB
		long  id3= db.insert(EmpresaConstantes.TABLE_EMPRESAS, null, contentValues);
		Log.i(LOGTAG,"Se inserto una nueva empresa 2 id:"+id3);
	}
	
	/**
	 * Delete TODO object
	 * @param todoId
	 */
	public void deleteEmpresa(long empresaId) {
		// Delete from DB where id match
		db.delete(EmpresaConstantes.TABLE_EMPRESAS, EmpresaConstantes.COLUMN_ID+" = " + empresaId, null);
	}
	
	/**
	 * Get all TODOs.
	 * @return
	 */
	public List<Empresa> getEmpresas() {
		List<Empresa> empresaList = new ArrayList<Empresa>();
		
		// Name of the columns we want to select
		String[] tableColumns = new String[] {EmpresaConstantes.COLUMN_ID,EmpresaConstantes.COLUMN_NOMBRE,
				EmpresaConstantes.COLUMN_IMAGEN,EmpresaConstantes.COLUMN_TELEFONO};
		
		// Query the database
		Cursor cursor = db.query(EmpresaConstantes.TABLE_EMPRESAS, tableColumns, null, null, null, null, null);
		cursor.moveToFirst();
		
		// Iterate the results
	    while (!cursor.isAfterLast()) {
	    	Empresa empresa = new Empresa();
	    	// Take values from the DB
	    	empresa.setId(cursor.getLong(cursor.getColumnIndex(EmpresaConstantes.COLUMN_ID)));
	    	empresa.setNombre(cursor.getString(cursor.getColumnIndex(EmpresaConstantes.COLUMN_NOMBRE)));
	    	empresa.setTelefono(cursor.getString(cursor.getColumnIndex(EmpresaConstantes.COLUMN_TELEFONO)));
	    	empresa.setImagen(cursor.getString(cursor.getColumnIndex(EmpresaConstantes.COLUMN_IMAGEN)));
	    	
	    	
	    	
	    	// Add to the DB
	    	empresaList.add(empresa);
	    	
	    	// Move to the next result
	    	cursor.moveToNext();
	    }
		
		return empresaList;
	}
	
	
	
}
