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


public class Purchase extends Activity{
	Dbadapter db=new Dbadapter(this);
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.purchase);
        initiate();
	
	}
	
	private void initiate() {
		final EditText datetxt=(EditText) findViewById(R.id.editText1);
		final EditText piecetxt=(EditText) findViewById(R.id.editText2);
		final EditText kgtxt=(EditText) findViewById(R.id.editText3);
		final EditText per_kgtxt=(EditText) findViewById(R.id.editText4);
		
	    Button b1= (Button) findViewById(R.id.button1);
        b1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Log.d("test", "button entered");
				if((datetxt.getText().toString().equals(""))||(piecetxt.getText().toString().equals(""))||(kgtxt.getText().toString().equals(""))||(per_kgtxt.getText().toString().equals("")))
				{
					Toast toast= Toast.makeText(Purchase.this,"please enter all the details", 5000);
					toast.setGravity(Gravity.CENTER, 0, 0);
					toast.show();
				}
				else
				{
				String a, b;
			    Float sum;
				a=kgtxt.getText().toString();
				b=per_kgtxt.getText().toString();
				sum=Float.parseFloat(a)*Float.parseFloat(b);
				
				
				//workin with db
				db.open();
				Log.d("test", "adding purchase");
				long id=db.insertPurchase(datetxt.getText().toString(), piecetxt.getText().toString(), kgtxt.getText().toString(), per_kgtxt.getText().toString(), sum.toString());
				Log.d("test", "purchase returned");
				db.close();
				if(id==1)
				{
					Log.d("test","data entered");
				}
				else
				{
					Log.d("test","data lost");
				}
				
				datetxt.setText("");
				piecetxt.setText("");
				kgtxt.setText("");
				per_kgtxt.setText("");
				
				}	
			}
		});
        Button b2=(Button) findViewById(R.id.button2);
        b2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
			
				startActivity(new Intent(Purchase.this,Main.class));
				
			}
		});
        
        Button b3=(Button) findViewById(R.id.button3);
        b3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Log.d("test", "button entered");
				
				startActivity(new Intent(Purchase.this,Detail1.class));
			}
		});

	}


}
