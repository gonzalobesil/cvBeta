package com.example.comoviajarbeta;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class MyService extends Service {
	int counter = 0;
	static final int UPDATE_INTERVAL = 3000;
	private Timer timer = new Timer();
	private Handler handler;

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		// Queremos que este servicio se ejecute continuamente
		// hasta que sea detenido manualmente, por lo que retornaremos
		// START_STICKY

		
		handler = new Handler();
		handler.postDelayed(runnable, 100);
		doSomethingRepeatedly();

//		try {
//			new DoBackgroundTask().execute(new URL(
//					"http://www.google.com/imagen1.png"), new URL(
//					"http://www.google.com/imagen2.png"), new URL(
//					"http://www.google.com/imagen3.png"), new URL(
//					"http://www.google.com/imagen4.png"));		
//		} catch (MalformedURLException ex) {
//			ex.printStackTrace();
//		}

		return START_STICKY;
	}
	
	private Runnable runnable = new Runnable() {
		   @Override
		   public void run() {
		      /* do what you need to do */
			   try {
					new DoBackgroundTask().execute(new URL(
							"http://www.google.com/imagen1.png"), new URL(
							"http://www.google.com/imagen2.png"), new URL(
							"http://www.google.com/imagen3.png"), new URL(
							"http://www.google.com/imagen4.png"));		
				} catch (MalformedURLException ex) {
					ex.printStackTrace();
				}
		      /* and here comes the "trick" */
		      handler.postDelayed(this, 20000);
		   }
		};
	
	private void doSomethingRepeatedly(){
		
		timer.scheduleAtFixedRate(new TimerTask(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				Log.d("MyService", String.valueOf(++counter));
			}
			
		},0, UPDATE_INTERVAL);
	}

	private int DownloadFile(URL url) {
		try {
			// Simulamos la descarga de un fichero
			Thread.sleep(5000);
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}

		return 100;
	}

	private class DoBackgroundTask extends AsyncTask<URL, Integer, Long> {

		@Override
		protected Long doInBackground(URL... urls) {
			// TODO Auto-generated method stub
			int count = urls.length;
			long totalKBytesDownloaded = 0;
			for (int i = 0; i < count; i++) {
				totalKBytesDownloaded += DownloadFile(urls[0]);
				// --Calculamos el porcentaje descargado y
				// --reportamos el progreso
				publishProgress((int) (((i + 1) / (float) count) * 100));
			}
			return totalKBytesDownloaded;
		}

		@Override
		protected void onProgressUpdate(Integer... values) {
			// TODO Auto-generated method stub
			super.onProgressUpdate(values);
			Log.d("Descargando ficheros", String.valueOf(values[0])
					+ "% descargado");
			Toast.makeText(getBaseContext(), values[0] + "% descargado",
					Toast.LENGTH_SHORT).show();
		}

		@Override
		protected void onPostExecute(Long result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			Toast.makeText(getBaseContext(),
					"Descargado " + result + " KBytes", Toast.LENGTH_SHORT)
					.show();
			//stopSelf();
		}
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		if (timer != null){
			timer.cancel();
		}
		Toast.makeText(getBaseContext(), "Servicio Detenido",
				Toast.LENGTH_SHORT).show();
	}

}
