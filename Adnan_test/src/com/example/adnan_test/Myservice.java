package com.example.adnan_test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Timer;
import java.util.TimerTask;

import com.example.adnan_test.Json;

import android.app.IntentService;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;



public class Myservice extends Service {

	
	int counter = 0;
	static final int UPDATE_INTERVAL = 10000;
	private Timer timer = new Timer();
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public int onStartCommand(Intent intent, int flags, int startId ) {
		// We want this service to continue running until it is explicitly
		// stopped, so return sticky.
		timer.scheduleAtFixedRate(new TimerTask(){
			
		@SuppressWarnings("static-access")
		public void run(){
		 String lati = null,longi=null;
		//Toast.makeText(this,"Service Started", Toast.LENGTH_LONG).show();
		// Toast q = new Toast(getApplicationContext());
	//	 Toast.makeText(getApplicationContext(), "service started", q.LENGTH_LONG).show();
		 Log.d(Constants.LOG, "Service Runs");

		Tracker t = new Tracker(Myservice.this);
        if(t.canGetLocation())
		{
			lati=Double.toString(t.getlat());
			longi=Double.toString(t.getlon());
			 Log.d(Constants.LOG, "GPS Enabled");
	    // Toast.makeText(getApplicationContext(), "GPS Enabled", Toast.LENGTH_LONG).show();
		          
		}
		else
		{
			
			//Toast.makeText(getApplicationContext(), "GPS Disabled", Toast.LENGTH_LONG).show();
			 Log.d(Constants.LOG, "GPS Disabled");

		}
        
        TelephonyManager mngr = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE); 
        final String id = mngr.getDeviceId();
       
        
        /*Json j= new Json();		
		j.send(lati,longi,id); 
		 Log.d(Constants.LOG, "Data send attempted");*/
        
        try{
    	String url="http://testapp1pranav.appspot.com/gettaxi?id="+id+"&lat="+lati+"&lon="+longi;
    	
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
 
		// optional default is GET
		con.setRequestMethod("POST");

		//add request header
		con.setRequestProperty("User-Agent", "Mozilla/5.0");
 
		int responseCode = con.getResponseCode();
		
		Log.d(Constants.LOG, "Response Code : " + responseCode);

 
		BufferedReader in = new BufferedReader( new InputStreamReader(con.getInputStream()));
		String input;
		StringBuffer buff = new StringBuffer();
 
		while ((input = in.readLine()) != null)
		{
			buff.append(input);
		}
		in.close();
		
		String rply =buff.toString();
		          
           if(rply == "SUCCESS")
           {
    			 Log.d(Constants.LOG, "POST Success");

           }
           else
           {
        	   Log.d(Constants.LOG, "Post Failed");
           }
    }
   
    catch (UnknownHostException h)
    {
    	 Log.e(Constants.LOG, "Internet connection not available"); 

    }
 catch(Exception e)
	{
	 Log.e(Constants.LOG, "OTHER ERROR"); 
	}

		}
		
		}, 0, UPDATE_INTERVAL);
		return START_STICKY;
		}
		@Override
		public void onDestroy() {
			
		super.onDestroy();
		if (timer != null){
			timer.cancel();
			 Log.d(Constants.LOG, "Service Stopped");

			}
		}
	

}
