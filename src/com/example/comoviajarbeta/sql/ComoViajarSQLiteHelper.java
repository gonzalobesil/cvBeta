package com.example.comoviajarbeta.sql;

import com.example.comoviajarbeta.utilidades.*;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Creates application database.
 * 
 * @author itcuties
 *
 */

public class ComoViajarSQLiteHelper extends SQLiteOpenHelper {
	private static final String LOGTAG = "LOG_COMOVIAJAR";

	private static final String DATABASE_NAME = "comoviajar.db";
	private static final int DATABASE_VERSION = 13;



	private static final String TABLE_CREATE_ESTACIONES = 
			"CREATE TABLE " + EstacionConstantes.TABLE_ESTACIONES + " (" +
					EstacionConstantes.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
					EstacionConstantes.COLUMN_DEPARTAMENTO + " TEXT, " +
					EstacionConstantes.COLUMN_NOMBRE + " TEXT, " +
					EstacionConstantes.COLUMN_DIRECCION + " TEXT, " +
					EstacionConstantes.COLUMN_TELEFONO + " TEXT, " +
					EstacionConstantes.COLUMN_IMAGEN + " TEXT, " +
					EstacionConstantes.COLUMN_LATITUD + " TEXT, " +
					EstacionConstantes.COLUMN_LONGITUD + " TEXT, " +
					EstacionConstantes.COLUMN_EMPRESA + " TEXT " +
			")";

	private static final String TABLE_CREATE_DEPARTAMENTOS = 
	"CREATE TABLE " + DepartamentoConstantes.TABLE_DEPARTAMENTOS + " (" +
			DepartamentoConstantes.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
			DepartamentoConstantes.COLUMN_NOMBRE + " TEXT " +
	")";

	private static final String TABLE_CREATE_LUGARES = 
	"CREATE TABLE " + LugarConstantes.TABLE_LUGARES + " (" +
			LugarConstantes.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
			LugarConstantes.COLUMN_DEPARTAMENTO + " TEXT, " +
			LugarConstantes.COLUMN_NOMBRE + " TEXT, " +
			LugarConstantes.COLUMN_LATITUD + " TEXT, " +
			LugarConstantes.COLUMN_LONGITUD + " TEXT " +
	")";

	private static final String TABLE_CREATE_RUTAS = 
	"CREATE TABLE " + RutaConstantes.TABLE_RUTAS + " (" +
			RutaConstantes.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
			RutaConstantes.COLUMN_NOMBRE + " TEXT, " +
			RutaConstantes.COLUMN_ESTADO + " TEXT, " +
			RutaConstantes.COLUMN_IMAGEN + " TEXT " +
	")";

	private static final String TABLE_CREATE_EMPRESAS = 
			"CREATE TABLE " + EmpresaConstantes.TABLE_EMPRESAS + " (" +
					EmpresaConstantes.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
					EmpresaConstantes.COLUMN_NOMBRE + " TEXT, " +
					EmpresaConstantes.COLUMN_TELEFONO + " TEXT, " +
					EmpresaConstantes.COLUMN_IMAGEN + " TEXT " +

			")";
	
	private static final String TABLE_CREATE_TRAYECTOS =
			"CREATE TABLE " + TrayectoConstantes.TABLE_TRAYECTOS + " (" +
					TrayectoConstantes.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
					TrayectoConstantes.COLUMN_ORIGEN_ID + " TEXT, " +
					TrayectoConstantes.COLUMN_DESTINO_ID + " TEXT, " +
					TrayectoConstantes.COLUMN_HORA + " TEXT, " +
					TrayectoConstantes.COLUMN_FRECUENCIA + " TEXT, " +
					TrayectoConstantes.COLUMN_EMPRESA_ID + " TEXT, " +
					TrayectoConstantes.COLUMN_TIPOTRANSPORTE_ID + " TEXT, " +
					TrayectoConstantes.COLUMN_DISTANCIA + " TEXT " +

			")";
	
	private static final String TABLE_CREATE_TRANSPORTES =
			"CREATE TABLE " + TransporteConstantes.TABLE_TRANSPORTES + " (" +
					TransporteConstantes.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
					TransporteConstantes.COLUMN_DESCRIPCION + " TEXT " +
			")";
			
	public ComoViajarSQLiteHelper(Context context) {
		// Databse: todos_db, Version: 1
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	/**
	 * Create simple table
	 * todos
	 * 		_id 	- key
	 * 		todo	- todo text
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {
		// Execute create table SQL
		db.execSQL(TABLE_CREATE_ESTACIONES);
		Log.i(LOGTAG,"La tabla"+EstacionConstantes.TABLE_ESTACIONES+"  ha sido creada exitosamente");
		db.execSQL(TABLE_CREATE_DEPARTAMENTOS);
		Log.i(LOGTAG,"La tabla"+DepartamentoConstantes.TABLE_DEPARTAMENTOS+"  ha sido creada exitosamente");
		db.execSQL(TABLE_CREATE_LUGARES);
		Log.i(LOGTAG,"La tabla"+LugarConstantes.TABLE_LUGARES+"  ha sido creada exitosamente");
		db.execSQL(TABLE_CREATE_RUTAS);
		Log.i(LOGTAG,"La tabla"+RutaConstantes.TABLE_RUTAS+"  ha sido creada exitosamente");
		db.execSQL(TABLE_CREATE_EMPRESAS);
		Log.i(LOGTAG,"La tabla"+EmpresaConstantes.TABLE_EMPRESAS+"  ha sido creada exitosamente");
		db.execSQL(TABLE_CREATE_TRAYECTOS);
		Log.i(LOGTAG,"La tabla"+TrayectoConstantes.TABLE_TRAYECTOS+"  ha sido creada exitosamente");
		db.execSQL(TABLE_CREATE_TRANSPORTES);
		Log.i(LOGTAG,"La tabla"+TransporteConstantes.TABLE_TRANSPORTES+"  ha sido creada exitosamente");
	}

	/**
	 * Recreates table
	 */
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVer, int newVer) {
		// DROP table
		db.execSQL("DROP TABLE IF EXISTS "+EstacionConstantes.TABLE_ESTACIONES);
		Log.i(LOGTAG,"La tabla"+EstacionConstantes.TABLE_ESTACIONES+"  ha sido borrada exitosamente");
		db.execSQL("DROP TABLE IF EXISTS "+DepartamentoConstantes.TABLE_DEPARTAMENTOS);
		Log.i(LOGTAG,"La tabla"+DepartamentoConstantes.TABLE_DEPARTAMENTOS+"  ha sido borrada exitosamente");
		db.execSQL("DROP TABLE IF EXISTS "+LugarConstantes.TABLE_LUGARES);
		Log.i(LOGTAG,"La tabla"+LugarConstantes.TABLE_LUGARES+"  ha sido borrada exitosamente");
		db.execSQL("DROP TABLE IF EXISTS "+RutaConstantes.TABLE_RUTAS);
		Log.i(LOGTAG,"La tabla"+RutaConstantes.TABLE_RUTAS+"  ha sido borrada exitosamente");
		db.execSQL("DROP TABLE IF EXISTS "+EmpresaConstantes.TABLE_EMPRESAS);
		Log.i(LOGTAG,"La tabla"+EmpresaConstantes.TABLE_EMPRESAS+"  ha sido borrada exitosamente");
		db.execSQL("DROP TABLE IF EXISTS "+TrayectoConstantes.TABLE_TRAYECTOS);
		Log.i(LOGTAG,"La tabla"+TrayectoConstantes.TABLE_TRAYECTOS+"  ha sido borrada exitosamente");
		db.execSQL("DROP TABLE IF EXISTS "+TransporteConstantes.TABLE_TRANSPORTES);
		Log.i(LOGTAG,"La tabla"+TransporteConstantes.TABLE_TRANSPORTES+"  ha sido borrada exitosamente");
		// Recreate table
		onCreate(db);
	}
	
}
