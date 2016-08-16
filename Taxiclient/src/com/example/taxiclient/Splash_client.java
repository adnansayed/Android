package com.example.taxiclient;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;
public class Splash_client extends Activity 
{
	// Splash screen timer
    private static int SPLASH_TIME_OUT = 3000;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash_client);
		new Handler().postDelayed(new Runnable() 
		{
			public void run()
			{
                // This method will be executed once the timer is over
                // Start your app main activity
                Intent i = new Intent(Splash_client.this, MainActivity.class);
                startActivity(i);
                // close this splash_client activity
                finish();
            }
		
	}, SPLASH_TIME_OUT);
	}


}
