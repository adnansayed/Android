package com.example.taxiclient;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.content.Intent;
public class Second_client extends Activity
{
String a,b,c,d,cust_lat,cust_lon;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second_client);
		
		Intent i=getIntent();
		Bundle bu=i.getExtras();
		a=bu.getString("id1");
		b=bu.getString("lat1");
		c=bu.getString("lon1");
		d=bu.getString("dist1");
		cust_lat=bu.getString("clat");
		cust_lon=bu.getString("clon");
		TextView t6=(TextView)findViewById(R.id.textView6);
		TextView t7=(TextView)findViewById(R.id.textView7);
		TextView t8=(TextView)findViewById(R.id.textView8);
		TextView t9=(TextView)findViewById(R.id.textView9);
		t6.setText(a);
		t7.setText(b);
		t8.setText(c);
	    t9.setText(d);
	    
	    
	    Button b1 = (Button)findViewById(R.id.button1);  
    try{	    
	    b1.setOnClickListener(new View.OnClickListener()
	    {
			
			@Override
			public void onClick(View v) 
			{
				// TODO Auto-generated method stub
				Intent i1 = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse("http://maps.google.com/maps?saddr=lat1,lon1&daddr=cust_lat,cust_lon"));
				startActivity(i1);
			}
		});
	    
}


catch(Exception e)
{
Log.w("pita",e.toString());	
}

	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.second_client, menu);
		return true;
	}

}
/* mHandler = new Handler()
   {              
   public void handleMessage(Message msg) 
  {                   // process incoming messages here              
   }        
     }; */   