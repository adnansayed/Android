package com.pss.tp2;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Main extends Activity {
	
	 String a,b;
	 float result,r;

		EditText t1=(EditText) findViewById(R.id.editText1);
		EditText t2=(EditText) findViewById(R.id.editText2);
		TextView t3=(TextView) findViewById(R.id.textView1);
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	    
		Button b1=(Button) findViewById(R.id.button1);
	     b1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
			    a=t1.getText().toString();
			    b=t2.getText().toString();
			    result=Float.parseFloat(a)+Float.parseFloat(b);
			    t3.setText(String.valueOf(result));
			    
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
