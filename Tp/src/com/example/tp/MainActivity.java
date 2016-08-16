package com.example.tp;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {
	
	/*EditText t1;
     EditText t2;
	 EditText t3;
	 String a, b;
	Integer sum;*/
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//initiate();
		Button b1= (Button) findViewById(R.id.button1);
		b1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startActivity(new Intent(MainActivity.this,DD.class));
			}
		});
		
		
	}

	/*private void initiate() {
		
		t1= (EditText) findViewById(R.id.editText1);
		t2=(EditText) findViewById(R.id.editText2);
		t3=(EditText) findViewById(R.id.editText3);
		Button b1= (Button) findViewById(R.id.button1);
		b1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				a=t1.getText().toString();
				b=t2.getText().toString();
				sum=Integer.parseInt(a)+Integer.parseInt(b);
				t3.setText(String.valueOf(sum));
			}
		});
		
	}*/

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
