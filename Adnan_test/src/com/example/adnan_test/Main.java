package com.example.adnan_test;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class Main extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		Button btnStart = (Button) findViewById(R.id.btnStartService);
		btnStart.setOnClickListener(new View.OnClickListener() {
		public void onClick(View v) {
		startService(new Intent(getBaseContext(), Myservice.class) );
		}
		});
		Button btnStop = (Button) findViewById(R.id.btnStopService);
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
