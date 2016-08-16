package com.pss.update2;

import java.util.Calendar;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class Main extends Activity {

	final Context context=this;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		//alarm manager
		    Calendar cal = Calendar.getInstance();
		    cal.add(Calendar.SECOND, 10);
		    Intent intent = new Intent(Main.this, Location_service.class);
		    PendingIntent pintent = PendingIntent.getService(Main.this, 0, intent, 0);
		    AlarmManager alarm = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
		    alarm.setRepeating(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(),3600 * 1000, pintent);
		
		
		Button b1=(Button) findViewById(R.id.button1);
		Button b2=(Button) findViewById(R.id.button2);
		
		
		b1.setOnClickListener(new View.OnClickListener() {
		public void onClick(View v) {
		startService(new Intent(getBaseContext(), Location_service.class));
		}
		});
		
		b2.setOnClickListener(new View.OnClickListener() {
		public void onClick(View v) {
			AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
					context);
			alertDialogBuilder.setTitle("ALERT");
			alertDialogBuilder
			.setMessage("Click yes to exit!")
			.setCancelable(false)
			.setPositiveButton("Yes",new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog,int id) {
					// if this button is clicked, close
					// current activity
					stopService(new Intent(getBaseContext(), Location_service.class));
					Main.this.finish();
					
				}
			  })
			.setNegativeButton("No",new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog,int id) {
					// if this button is clicked, just close
					// the dialog box and do nothing
					dialog.cancel();
				}
			});
			AlertDialog alertDialog = alertDialogBuilder.create();
			 
		
			alertDialog.show();
			
		
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
