package com.pss.project;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Bill extends Activity{
	Dbadapter db=new Dbadapter(this);
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bill);
		db.open();
		String a,b,c,d;
		a=db.sump();
		b=db.sumk();
		c=db.sumall();
		d=db.sumpd();
		TextView t1=(TextView) findViewById(R.id.textView6);
		TextView t2=(TextView) findViewById(R.id.textView7);
		TextView t3=(TextView) findViewById(R.id.textView8);
		TextView t4=(TextView) findViewById(R.id.textView9);
		t1.setText(a);
		t2.setText(b);
		t3.setText(c);
		t4.setText(d);
		db.close();
		
		Button b1=(Button) findViewById(R.id.button1);
		b1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
				startActivity(new Intent(Bill.this,Main.class));
			}
		});
		Button b2=(Button) findViewById(R.id.button2);
		b2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				boolean a;
				Log.d("test", "delete button");
				a=db.deleteall();
				startActivity(new Intent(Bill.this,Main.class));
			}
		});
	}

}
