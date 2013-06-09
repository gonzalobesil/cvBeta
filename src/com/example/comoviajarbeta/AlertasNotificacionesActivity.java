package com.example.comoviajarbeta;

import android.os.Bundle;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class AlertasNotificacionesActivity extends Activity {

	private static final int NOTIFY_ME_ID=1987;
	private int count=0;
	private NotificationManager mgr=null;

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_alertas_notificaciones);

		mgr=(NotificationManager)getSystemService(NOTIFICATION_SERVICE);
	}

	public void notifyMe(View v) 
	{
		Notification note=new Notification(R.drawable.ic_launcher,"Status message!",System.currentTimeMillis());
		PendingIntent i=PendingIntent.getActivity(this, 0,new Intent(this, NotificationMessage.class),0);
		note.setLatestEventInfo(this, "New Email","Unread Conversation", i);
		note.number=++count;
		note.vibrate=new long[] {500L, 200L, 200L, 500L};
		note.flags|=Notification.FLAG_AUTO_CANCEL;	    

		mgr.notify(NOTIFY_ME_ID, note);
	}

	public void clearNotification(View v) {
		mgr.cancel(NOTIFY_ME_ID);
	}
}
