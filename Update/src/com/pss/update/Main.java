package com.pss.update;

import android.os.Bundle;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;


import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class Main extends Activity {
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		
		
		Button btnStart = (Button) findViewById(R.id.button1);
		btnStart.setOnClickListener(new View.OnClickListener() {
		public void onClick(View v) {
		Log.d("test", "button entered");
		startService(new Intent(getBaseContext(), Myservice.class));
		}
		});
		Button btnStop = (Button) findViewById(R.id.button2);
		btnStop.setOnClickListener(new View.OnClickListener() {
		public void onClick(View v) {
		stopService(new Intent(getBaseContext(), Myservice.class));
		}
		});
	
		
	}
		
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
