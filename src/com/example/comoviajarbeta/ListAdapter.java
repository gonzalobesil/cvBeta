package com.example.comoviajarbeta;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.comoviajarbeta.data.Estacion;

/**
 * List adapter for storing TODOs data
 * 
 * @author itcuties
 *
 */
public class ListAdapter extends ArrayAdapter<Estacion> {

	// List context
    private final Context context;
    // List values
    private final List<Estacion> todoList;
	
	public ListAdapter(Context context, List<Estacion> todoList) {
		super(context, R.layout.activity_main, todoList);
		this.context = context;
		this.todoList = todoList;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		 
        View rowView = inflater.inflate(R.layout.activity_main, parent, false);
         
        TextView todoText = (TextView) rowView.findViewById(R.id.todoText);
        todoText.setText(todoList.get(position).getNombre());
         
        return rowView;
	}
	
}
