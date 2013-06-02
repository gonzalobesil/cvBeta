package com.example.comoviajarbeta;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.comoviajarbeta.data.Empresa;

/********* Adapter class extends with BaseAdapter and implements with OnClickListener ************/
public class CustomAdapterEmpresa extends BaseAdapter /*implements OnClickListener*/ {
    
	/*********** Declare Used Variables *********/
    private Activity activity;
    private ArrayList data;
    private static LayoutInflater inflater=null;
    public Resources res;
    Empresa tempValues=null;
    int i=0;
    
    /*************  CustomAdapter Constructor *****************/
    public CustomAdapterEmpresa(Activity a, ArrayList d,Resources resLocal) {
    	
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
        public Button btnLlamar;

    }

    /*********** Depends upon data size called for each row , Create each ListView row ***********/
    public View getView(int position, View convertView, ViewGroup parent) {
    	
        View vi=convertView;
        ViewHolder holder;
        
        if(convertView==null){ 
        	
        	/********** Inflate tabitem.xml file for each row ( Defined below ) ************/
            vi = inflater.inflate(R.layout.tabitem_empresa, null); 
            
            /******** View Holder Object to contain tabitem.xml file elements ************/
            holder=new ViewHolder();
            holder.text=(TextView)vi.findViewById(R.id.text);
            holder.text1=(TextView)vi.findViewById(R.id.text1);
            holder.image=(ImageView)vi.findViewById(R.id.image);
            holder.btnLlamar=(Button)vi.findViewById(R.id.btnLlamarEmpresa);
            
            holder.btnLlamar.setOnClickListener(new View.OnClickListener() {

        	@Override
     		public void onClick(View view) {
     			// Launching News Feed Screen
     			Intent i = new Intent(Intent.ACTION_CALL);
     			//i.setData();
     			i.setData(Uri.parse("tel:" + tempValues.getTelefono()));
     			EmpresasActivity sct = (EmpresasActivity)activity;
     			sct.startActivity(i);
     		}
     	});
            
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
	        tempValues = (Empresa) data.get(position);
	        
	        /************  Set Model values in Holder elements ***********/
	         holder.text.setText(tempValues.getNombre());
	         holder.text1.setText(tempValues.getTelefono());
	         holder.image.setImageResource(res.getIdentifier("com.example.comoviajarbeta:drawable/"+tempValues.getImagen(),null,null));
	         
	         /******** Set Item Click Listner for LayoutInflater for each row ***********/
//	         vi.setOnClickListener(new OnItemClickListener(position));
	         //vi.setOnClickListener(new );
        }
        return vi;
    }
    
    
//    @Override
//    public void onClick(View v) {
//            Log.v("CustomAdapter", "=====Row button clicked");
//    }
//    
//    /********* Called when Item click in ListView ************/
//    private class OnItemClickListener  implements OnClickListener{           
//        private int mPosition;
//        
//        OnItemClickListener(int position){
//        	 mPosition = position;
//        }
//        
//        @Override
//        public void onClick(View arg0) {
//            EmpresasActivity sct = (EmpresasActivity)activity;
//        	sct.onItemClick(mPosition);
//        }               
//    }   
}