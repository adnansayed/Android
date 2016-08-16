package com.pss.update;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

public class Myservice extends Service{
	private static final int FIVE_SEC = 5000;
	public LocationManager locationManager;
	public MyLocationListener listener;
	public Location previousBestLocation = null;
	
	Intent intent;
	int counter = 0;
	
	@Override
	public void onCreate() {
	    super.onCreate();
	        
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onStart(Intent intent, int startId) {
	Toast.makeText(this, "Service Started", Toast.LENGTH_LONG).show();
	    locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
	    listener = new MyLocationListener();  
	    Log.d("test", "calling for manager entered");
	    locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 4000, 0, listener);
	    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 4000, 0, listener);
	}
	
	
	protected boolean isBetterLocation(Location location, Location currentBestLocation) {
		
		Log.d("test", "isBetterLocation entered");
	    if (currentBestLocation == null) {
	        // A new location is always better than no location
	        return true;
	    }

	    // Check whether the new location fix is newer or older
	    long timeDelta = location.getTime() - currentBestLocation.getTime();
	    boolean isSignificantlyNewer = timeDelta > FIVE_SEC;
	    boolean isSignificantlyOlder = timeDelta < -FIVE_SEC;
	    boolean isNewer = timeDelta > 0;

	    // If it's been more than two minutes since the current location, use the new location
	    // because the user has likely moved
	    if (isSignificantlyNewer) {
	        return true;
	    // If the new location is more than two minutes older, it must be worse
	    } else if (isSignificantlyOlder) {
	        return false;
	    }

	    // Check whether the new location fix is more or less accurate
	    int accuracyDelta = (int) (location.getAccuracy() - currentBestLocation.getAccuracy());
	    boolean isLessAccurate = accuracyDelta > 0;
	    boolean isMoreAccurate = accuracyDelta < 0;
	    boolean isSignificantlyLessAccurate = accuracyDelta > 200;

	    // Check if the old and new location are from the same provider
	    boolean isFromSameProvider = isSameProvider(location.getProvider(),
	            currentBestLocation.getProvider());

	    // Determine location quality using a combination of timeliness and accuracy
	    if (isMoreAccurate) {
	        return true;
	    } else if (isNewer && !isLessAccurate) {
	        return true;
	    } else if (isNewer && !isSignificantlyLessAccurate && isFromSameProvider) {
	        return true;
	    }
	    return false;
	}

	/** Checks whether two providers are the same */
	private boolean isSameProvider(String provider1, String provider2) {
	    if (provider1 == null) {
	      return provider2 == null;
	    }
	    return provider1.equals(provider2);
	}

  

	public static Thread performOnBackgroundThread(final Runnable runnable) {
	    final Thread t = new Thread() {
	        @Override
	        public void run() {
	            try {
	                runnable.run();
	            } finally {

	            }
	        }
	    };
	    t.start();
	    return t;
	}
	
	public class MyLocationListener implements LocationListener
	{

	    public void onLocationChanged(final Location loc)
	    {
	    	Log.d("test", "Location changed entered");
	        Log.i("**************************************", "Location changed");
	        if(isBetterLocation(loc, previousBestLocation)) {
	           String lati= Double.toString(loc.getLatitude());
	           String longi= Double.toString(loc.getLongitude());
	           TelephonyManager mngr = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE); 
	           final String id = mngr.getDeviceId();
	           Log.d("test","attempting to send");
	           String url="http://testapp1pranav.appspot.com/gettaxi?id="+id+"&lat="+lati+"&lon="+longi;
	                     

	        }                               
	    }

	    public void onProviderDisabled(String provider)
	    {
	        Toast.makeText( getApplicationContext(), "Gps Disabled", Toast.LENGTH_SHORT ).show();
	    }


	    public void onProviderEnabled(String provider)
	    {
	        Toast.makeText( getApplicationContext(), "Gps Enabled", Toast.LENGTH_SHORT).show();
	    }


	    public void onStatusChanged(String provider, int status, Bundle extras)
	    {

	    }

	}
	@Override
	public void onDestroy() {
	
	  super.onDestroy();
	    Log.v("STOP_SERVICE", "DONE");
	    locationManager.removeUpdates(listener); 
	Toast.makeText(this, "Service Destroyed", Toast.LENGTH_LONG).show();
	}
}
