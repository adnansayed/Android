package com.example.taxiclient;	
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.view.Menu;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.lang.StringBuffer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import android.os.Looper;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends Activity
{
	private Spinner spinner1;
	public static final String flag1="flag1";
	private SharedPreferences pref;
	 String taxis,id,lon,lat,street;
	 int flag;
	 final Context context = this;
	 Geocoder geo;
		List<Address> add;
    @Override
    protected void onCreate(Bundle savedInstanceState) 
    {
    	super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		spinner1 = (Spinner) findViewById(R.id.spinner1);
		
       final List<String> inf = new ArrayList<String>();
       inf.add("Select a Taxi");
       Thread t1 = new Thread()
       {
       	public void run()
       	{
       		Looper.prepare();
       		try
       		{
       			String address="http://testapp1pranav.appspot.com/gettaxi";
       			URL object = new URL(address);
 			    HttpURLConnection connect = (HttpURLConnection) object.openConnection();
 			    connect.setRequestMethod("GET");
 			    connect.connect();
 			    BufferedReader reader = new BufferedReader( new InputStreamReader(connect.getInputStream()));
 			    String line;    			
 			    while ((line = reader.readLine()) != null)
 			    {
 			    	inf.add(line);
 			    }
 			    reader.close();
       		}
     		catch(Exception e)
     		{
     			Log.d("taxiclient",e.toString());
     		}
     		Looper.loop();
     	}

     };//end of thread
     t1.start();
      ArrayAdapter<String> dataAdapter = new ArrayAdapter<String> (this, android.R.layout.simple_spinner_item,inf); 
      dataAdapter.setDropDownViewResource (android.R.layout.simple_spinner_dropdown_item);
      spinner1.setAdapter(dataAdapter);
     spinner1.setOnItemSelectedListener(new OnItemSelectedListener() 
     {

		@Override
		public void onItemSelected(AdapterView<?> parent, View arg1, int pos,long id)
		{
			// TODO Auto-generated method stub
			if(parent.getItemAtPosition(pos).toString()=="Select a Taxi")
			{
				flag=1;
			}
			else
			{
			taxis= (String)spinner1.getAdapter().getItem(pos);
			Toast.makeText(parent.getContext(),"you selected:\n"+taxis ,Toast.LENGTH_SHORT).show();
			  flag=0;
			}
		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) 
		{
			// TODO Auto-generated method stub
		}
	 
     });
     
      //destination
        EditText d1 = (EditText)findViewById(R.id.editText1);
        String dest = d1.getText().toString();
        
   		 LocationService l = new  LocationService(MainActivity.this);
   		 if(l.canGetLocation())
   		 {
   			   lat=Double.toString(l.getlat());
   			  lon=Double.toString(l.getlon());
   			 
   			 Toast.makeText(getApplicationContext(), "Latitude:"+lat+"\n"+"Longitude:"+lon, Toast.LENGTH_LONG).show();
   			 
   			 TelephonyManager mngr = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE); 
   			id = mngr.getDeviceId();
   		 }
   		 else
   		 {
   			 Toast.makeText(null, "location not found", Toast.LENGTH_LONG).show();
   		 }
   		 
   		
   			Button b1 = (Button)findViewById(R.id.button1);
   			b1.setOnClickListener(new View.OnClickListener()
            {
   				

			@Override
			public void onClick(View arg0)
			{
				Log.d("test","button entered");
				if (flag != 1)
				{
					Log.d("test","if entered");
				Thread t = new Thread()
				{
					// public Handler mHandler;       
					public void run()
					{
				
						Log.d("test","looper entered");
						
							Looper.prepare(); // For Preparing Message Pool for
												// the child Thread
							String info;

							try {
								Log.d("test","try block entered");

								String url = "http://testapp1pranav.appspot.com/devserver?id="+ id + "&lat=" + lat + "&lon=" + lon;
								Date d=new Date();
								d.getTime();
								String time=d.toString();
								SharedPreferences pref = getSharedPreferences(flag1,MODE_PRIVATE);
								SharedPreferences.Editor editor = pref.edit();
								editor.putBoolean("flag1", true).putString("time", time);
								editor.commit();
								
								String t2=pref.getString("time",time);
								Toast.makeText(getParent(),"the time:\n"+t2 ,Toast.LENGTH_SHORT).show();
								Log.w("taxiapp", "Url is:" + url);
								URL obj = new URL(url);
								HttpURLConnection con = (HttpURLConnection) obj.openConnection();

								// optional default is GET
								con.setRequestMethod("GET");

								// add request header
								con.setRequestProperty("User-Agent","Mozilla/5.0");

								int responseCode = con.getResponseCode();

								System.out.println("Response Code : "+ responseCode);

								BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
								String input;
								StringBuffer buff = new StringBuffer();

								while ((input = in.readLine()) != null)
								
								{
									buff.append(input);
								}
								in.close();

								info = buff.toString();
								Log.w("taxiapp", "response is:" + info);
								Gson gson = new Gson();
								GsonBuilder build = new GsonBuilder();
								gson = build.create();
								ServerReply sr = gson.fromJson(info,ServerReply.class);
								if (sr == null)
								{
									Toast.makeText(getApplicationContext(),"null", Toast.LENGTH_LONG).show();

								}
								else
								{
									Log.d("test","else entered");
									//driver details
									String id1 = sr.id;
									String lat1 = sr.lat;
									String lon1 = sr.lon;
									String dist1 =sr.distance;
									//customer
									String clat=lat;
									String clon=lon;
												
									Intent i = new Intent(MainActivity.this,Second_client.class);
									i.putExtra("id1", id1);
									i.putExtra("lat1", lat1);
									i.putExtra("lon1", lon1);
									i.putExtra("dist1", dist1);
									i.putExtra("clat", clat);
									i.putExtra("clon", clon);
									Log.d("test","all values taken");
									startActivity(i);
								}
							}

							catch (UnknownHostException h) {
								Toast.makeText(getApplicationContext(),"Internet connection not available",Toast.LENGTH_LONG).show();

							} catch (Exception e) 
							{
								e.printStackTrace();
							}
							Looper.loop(); // process messages till loop stopped	
					}// end run

				};// end of thread
				t.start();
				}// end of if
				else
				{
					AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
					// set title
					alertDialogBuilder.setTitle("");
					// set dialog message
					alertDialogBuilder
						.setMessage("Please select Taxi type")
						.setCancelable(false)
						.setPositiveButton("OK",new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,int id) 
							{
								//do nothing
							}
						  });
					AlertDialog alertDialog = alertDialogBuilder.create();
					// show it
					alertDialog.show();
				}
				
			}//end of on click
		});// end of button

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
   
}

//ServerReply class for matching strings from severs reply
class ServerReply
{
	
	public String id,lat,lon,distance;
	//show content

}


