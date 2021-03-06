package com.example.comoviajarbeta;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.comoviajarbeta.data.Ruta;

/********* Adapter class extends with BaseAdapter and implements with OnClickListener ************/
public class CustomAdapterRuta extends BaseAdapter   implements OnClickListener {
    
	/*********** Declare Used Variables *********/
    private Activity activity;
    private ArrayList data;
    private static LayoutInflater inflater=null;
    public Resources res;
    Ruta tempValues=null;
    int i=0;
    
    /*************  CustomAdapter Constructor *****************/
    public CustomAdapterRuta(Activity a, ArrayList d,Resources resLocal) {
    	
    	/********** Take passed values **********/
        activity = a;
        data=d;
        res = resLocal;
        
        /***********  Layout inflator to call external xml layout () **********************/
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        
    }

    /******** What is the size of Passed Arraylist Size ************/
    public int getCount() {
    	
    	if(data.size()<=0)
    		return 1;
        return data.size();
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }
    
    /********* Create a holder to contain inflated xml file elements ***********/
    public static class ViewHolder{
    	
        public TextView text;
        public TextView text1;
        public TextView textWide;
        public ImageView image;

    }

    /*********** Depends upon data size called for each row , Create each ListView row ***********/
    public View getView(int position, View convertView, ViewGroup parent) {
    	
        View vi=convertView;
        ViewHolder holder;
        
        if(convertView==null){ 
        	
        	/********** Inflate tabitem.xml file for each row ( Defined below ) ************/
            vi = inflater.inflate(R.layout.tabitem, null); 
            
            /******** View Holder Object to contain tabitem.xml file elements ************/
            holder=new ViewHolder();
            holder.text=(TextView)vi.findViewById(R.id.text);
            holder.text1=(TextView)vi.findViewById(R.id.text1);
            holder.image=(ImageView)vi.findViewById(R.id.image);
            
           /************  Set holder with LayoutInflater ************/
            vi.setTag(holder);
        }
        else  
            holder=(ViewHolder)vi.getTag();
        
        if(data.size()<=0)
        {
        	holder.text.setText("No Data");
            
        }
        else
        {
        	/***** Get each Model object from Arraylist ********/
	        tempValues=null;
	        tempValues = (Ruta) data.get(position);
	        
	        /************  Set Model values in Holder elements ***********/
	         holder.text.setText(tempValues.getNombre());
	         holder.image.setImageResource(res.getIdentifier("com.example.comoviajarbeta:drawable/"+tempValues.getImagen(),null,null));
	         
	         /******** Set Item Click Listner for LayoutInflater for each row ***********/
	         vi.setOnClickListener(new OnItemClickListener(position));
        }
        return vi;
    }
    
    @Override
    public void onClick(View v) {
            Log.v("CustomAdapter", "=====Row button clicked");
    }
    
    /********* Called when Item click in ListView ************/
    private class OnItemClickListener  implements OnClickListener{           
        private int mPosition;
        
        OnItemClickListener(int position){
        	 mPosition = position;
        }
        
        @Override
        public void onClick(View arg0) {
            RutasActivity sct = (RutasActivity)activity;
        	sct.onItemClick(mPosition);
        }               
    }   
}