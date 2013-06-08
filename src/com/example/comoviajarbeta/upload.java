package com.example.comoviajarbeta;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.MediaStore.Images.Media;
import android.util.Base64;
import android.util.Log;

public class upload extends Activity {

	InputStream is;
	private static final int CAMERA_REQUEST = 1888;

	@Override

	public void onCreate(Bundle icicle) {

		super.onCreate(icicle);
		setContentView(R.layout.policia_layout);

		String s="";
		  /*ContentValues values = new ContentValues();
	      values.put(Media.TITLE, "My demo image");
	      values.put(Media.DESCRIPTION, "Image Captured by Camera via an Intent");
	      Uri myPicture = getContentResolver().insert(Media.EXTERNAL_CONTENT_URI, values);
	      Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
	      i.putExtra(MediaStore.EXTRA_OUTPUT, myPicture);
	      startActivity(i);
	      startActivityForResult(i, 0);
*/
		
 		String filePath = Environment.getExternalStorageDirectory()+ "/your_image_name.jpeg";
	    File file = new File(filePath);
	    Uri output = Uri.fromFile(file);
	    Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
	    startActivityForResult(cameraIntent, CAMERA_REQUEST);

	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data)
	{
	    if (resultCode == RESULT_OK) {
	        if (requestCode == CAMERA_REQUEST) 
	        { 
	        	Bitmap bitmapOrg = (Bitmap)data.getExtras().get("data");//(getResources(), R.drawable.ic_launcher);
	        	
	    		ByteArrayOutputStream bao = new ByteArrayOutputStream();

	    		bitmapOrg.compress(Bitmap.CompressFormat.JPEG, 90, bao);

	    		byte [] ba = bao.toByteArray();

	    		String ba1 = Base64.encodeToString(ba, 0);//encodeBytes(ba);




	    			 Intent returnIntent = new Intent();
	    			 returnIntent.putExtra("imagen",ba1);
	    			 setResult(RESULT_OK,returnIntent);  
	    			 finish();


	        }
	    }else
	    {
	    	this.finish();
	    }
	}
}