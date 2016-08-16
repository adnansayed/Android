package com.pss.project;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Payment extends Activity{
	Dbadapter db=new Dbadapter(this);
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.payment);
		Button b1=(Button) findViewById(R.id.button1);
		Button b2=(Button) findViewById(R.id.button2);
		Button b3=(Button) findViewById(R.id.button3);
		b1.setOnClickListener(new OnClickListener() {
		
		
			@Override
			public void onClick(View v) {
				Log.d("test", "button entered");
				EditText t1=(EditText) findViewById(R.id.editText1);
				EditText t2=(EditText) findViewById(R.id.editText2);
				if((t1.getText().toString().equals(""))||(t2.getText().toString().equals("")))
				{
					Toast toast= Toast.makeText(Payment.this,"please enter all the details", 5000);
					toast.setGravity(Gravity.CENTER, 0, 0);
					toast.show();
				}
				else
				{
				String a,d;
			    Float sub;
				a=t2.getText().toString();
				db.open();
				Log.d("test", "entering sum all");
				d=db.sumall();
			    sub=Float.parseFloat(d)-Float.parseFloat(a);
			    Log.d("test", "adding payment");
			    
			    long id=db.insertPayment(t1.getText().toString(), t2.getText().toString(), sub.toString());
				Log.d("test", "purchase returned");
				db.close();
				if(id==1)
				{
					Log.d("test","U PAYED");
				}
				else
				{
					Log.d("test","PAYMENT NOT ENTERED");
				}
				t1.setText("");
				t2.setText("");
				}
				
			}
		});
		b3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				startActivity(new Intent(Payment.this,Detail2.class));
				
			}
		});
		b2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
				startActivity(new Intent(Payment.this,Main.class));
			}
		});
	}
}
