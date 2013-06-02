package com.example.comoviajarbeta;

import java.util.ArrayList;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.example.comoviajarbeta.data.Trayecto;

public class MyExpandableListAdapter extends BaseExpandableListAdapter {
	
	private Activity activity;
	private  ArrayList preguntas;
	private String[][] respuestas;
	
	public MyExpandableListAdapter(Activity activity,  ArrayList d,
			String[][] respuestas) {
		super();
		this.activity = activity;
		this.preguntas = d;
		this.respuestas = respuestas;
	}

	public Object getChild(int groupPosition, int childPosition) {
		Trayecto t = (Trayecto) preguntas.get(groupPosition);
		return "Destino :" + t.getDestino_lugar_id();
//		return respuestas[groupPosition][childPosition];
	}

	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}

	public int getChildrenCount(int groupPosition) {
		return 1;
//      return respuestas[groupPosition].length;
	}

	public TextView getGenericView() {
		// Layout parameters for the ExpandableListView
		AbsListView.LayoutParams lp = new AbsListView.LayoutParams(
				ViewGroup.LayoutParams.FILL_PARENT,
				ViewGroup.LayoutParams.WRAP_CONTENT);
		
		TextView textView = new TextView(activity.getApplication());
		
		textView.setLayoutParams(lp);

		textView.setPadding(60, 5, 0, 5);
//		textView.setTextAppearance(activity.getBaseContext(), R.style.TitleText);
		return textView;
	}

	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		
		//Including a custom view for the child image and text
		View inflatedView = View.inflate(activity.getApplicationContext(),
				R.layout.child, null);
		inflatedView.setPadding(50, 0, 0, 0);
		TextView textView =(TextView)inflatedView.findViewById(R.id.textView1);
		
		//lets set up a custom font
//		Typeface font = Typeface.createFromAsset(activity.getAssets(),"BLOODY.ttf");
//		textView.setTypeface(font);
//		textView.setTextSize(17);
		//distancia y duración---------------------------------------------------------------------------------
		textView.setText(getChild(groupPosition, childPosition).toString());
		return inflatedView;
	}

	public Object getGroup(int groupPosition) {
		//hora empresa frecuencia------------------------------------------------------------------------------
		Trayecto t = (Trayecto) preguntas.get(groupPosition);
		return t.getHora() + t.getDestino_lugar_id();
	}

	public int getGroupCount() {
		return preguntas.size();
	}

	public long getGroupId(int groupPosition) {
		return groupPosition;
	}

	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		TextView textView = getGenericView();
		textView.setText(getGroup(groupPosition).toString());
		return textView;
	}

	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return true;
	}

	public boolean hasStableIds() {
		return true;
	}

}