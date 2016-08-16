package com.example.adnan_test;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

public class Tracker extends Service implements LocationListener {
private final Context c;
	
	boolean isgps=false;
	boolean isnet=false;
	boolean cangetloc=false;
	Location loc;
	double lat,lon;
	
	private static final long mindist=10;
	private static final long mintimt=1000*60*1;	
	
	protected LocationManager lm;
	
	public Tracker(Context x)
	{
		c=x;
		getloc();
	}


public Location getloc()
{
	try
	{
		lm=(LocationManager)c.getSystemService(LOCATION_SERVICE);
		
		isgps=lm.isProviderEnabled(lm.GPS_PROVIDER);
		
		isnet=lm.isProviderEnabled(lm.NETWORK_PROVIDER);
		
		if((!isgps)&&(!isnet))
		{
			
		}
		else
		{
			this.cangetloc=true;
			
			if(isnet)
			{
				lm.requestLocationUpdates(lm.NETWORK_PROVIDER,mintimt, mindist, this);
				Log.d("Network", "Network");
				if (lm != null) {
                    loc = lm.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                    if (loc != null) {
                        lat = loc.getLatitude();
                        lon = loc.getLongitude();
                    }
                }
            }
            
            if (isgps) {
                if (loc == null) {
                    lm.requestLocationUpdates(lm.GPS_PROVIDER,mintimt,mindist, this);
                    Log.d("GPS Enabled", "GPS Enabled");
                    if (lm != null) {
                        loc = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                        if (loc != null) {
                            lat = loc.getLatitude();
                            lon = loc.getLongitude();
                        }
                    }
                }
            }
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return loc;
}

public void onLocationChanged(Location location) {
}

@Override
public void onProviderDisabled(String provider) {
}

@Override
public void onProviderEnabled(String provider) {
}

@Override
public void onStatusChanged(String provider, int status, Bundle extras) {
}

@Override
public IBinder onBind(Intent arg0) {
    return null;
}

public double getlat(){
    if(loc != null){
        lat = loc.getLatitude();
    }
     
    // return latitude
    return lat;
}
 
/**
 * Function to get longitude
 * */
public double getlon()
{
    if(loc != null)
    {
        lon = loc.getLongitude();
    }
     
    // return longitude
    return lon;
}

public boolean canGetLocation()
{
    return this.cangetloc;
}


}
