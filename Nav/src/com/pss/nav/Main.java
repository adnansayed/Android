package com.pss.nav;

import java.util.Date;


import android.os.Bundle;
import android.preference.PreferenceManager;
import android.app.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class Main extends Activity {
	
	public static final String flag1="flag1";
	private SharedPreferences pref;
	private String prefName = "MyPref";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		Button b1=(Button) findViewById(R.id.button1);
		b1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
			
				/*String uri = "http://maps.google.com/";
	            Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(uri));
	            intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
	            startActivity(intent);*/
				Date d=new Date();
				d.getTime();
				String time=d.toString();
				pref = getSharedPreferences(prefName,MODE_PRIVATE);
				SharedPreferences.Editor editor = pref.edit();
				editor.putBoolean("flag1", true);
				editor.putString("time", time);
				editor.commit();
				Toast.makeText(getBaseContext(),
						"FILE saved successfully!",
						Toast.LENGTH_SHORT).show();
				
							
				SharedPreferences pref = getSharedPreferences(prefName, MODE_PRIVATE);
				String t2=pref.getString("time","");
				Boolean b=pref.getBoolean(flag1,true);
				Toast.makeText(getApplicationContext(), t2, Toast.LENGTH_SHORT).show();
			    Intent i= new Intent(Main.this,Second.class);
			    startActivity(i);
				
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
