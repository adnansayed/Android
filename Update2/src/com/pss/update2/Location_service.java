package com.pss.update2;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient;
import com.google.android.gms.location.LocationClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

public class Location_service extends Service implements LocationListener,GooglePlayServicesClient.ConnectionCallbacks,
GooglePlayServicesClient.OnConnectionFailedListener{

	LocationManager lm;
	LocationClient lc;
	LocationListener ls;
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Toast.makeText(this, "Service Started", Toast.LENGTH_LONG).show();
		lc=new LocationClient(this,this,this);
		lc.connect();
		return START_STICKY;
		}
	
	@Override
	public void onDestroy() {
	super.onDestroy();
	android.os.Process.killProcess(android.os.Process.myPid());
	Toast.makeText(this, "Service Destroyed", Toast.LENGTH_LONG).show();
	}
	@Override
	public void onLocationChanged(Location location) {
		String lati,longi;
		lati=Double.toString( location.getLatitude());
		longi=Double.toString(location.getLongitude());
		String msg="Location:"+location.getLatitude()+","+location.getLongitude();
		Toast.makeText(this,msg,Toast.LENGTH_LONG).show();
		TelephonyManager mngr = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE); 
        final String id = mngr.getDeviceId();
        Log.d("test","attempting to send");
        String url="http://testapp1pranav.appspot.com/gettaxi?id="+id+"&lat="+lati+"&lon="+longi;
		
	}
	@Override
	public void onConnectionFailed(ConnectionResult arg0) {
		Toast.makeText(this, "turn on gps and internet", Toast.LENGTH_LONG).show();
		
	}
	@Override
	public void onConnected(Bundle arg0) {
		
		LocationRequest request=LocationRequest.create();
		request.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
		request.setInterval(5000);
		request.setFastestInterval(3000);
		lc.requestLocationUpdates(request,this);
		
	}
	@Override
	public void onDisconnected() {
		// TODO Auto-generated method stub
		
	}

}
