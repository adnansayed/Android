package com.pss.nav;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Toast;

public class Second extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.second);
		SharedPreferences pref=PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
		String t1=pref.getString("time", "does not exist");
		Toast.makeText(getApplicationContext(), t1, Toast.LENGTH_SHORT).show();
		
	}

}
