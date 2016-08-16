package com.pss.location;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import android.os.Bundle;
import android.app.Activity;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Main extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		final EditText et1=(EditText) findViewById(R.id.editText1);
		final EditText et2=(EditText) findViewById(R.id.editText2);
		final EditText et3=(EditText) findViewById(R.id.editText3);
		final EditText et4=(EditText) findViewById(R.id.editText4);
		final EditText et5=(EditText) findViewById(R.id.editText5);
		final EditText et6=(EditText) findViewById(R.id.editText6);
		final EditText et7=(EditText) findViewById(R.id.editText7);
		final EditText et8=(EditText) findViewById(R.id.editText8);
		final Button b1=(Button) findViewById(R.id.button1);
		Button b2=(Button) findViewById(R.id.button2);
		b2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				if((et1.getText().toString().equals(""))||(et2.getText().toString().equals(""))||(et3.getText().toString().equals(""))||(et4.getText().toString().equals(""))||(et5.getText().toString().equals(""))||(et6.getText().toString().equals(""))||(et7.getText().toString().equals("")))
				{
					Toast toast= Toast.makeText(Main.this,"please enter all the details", 5000);
					toast.setGravity(Gravity.CENTER, 0, 0);
					toast.show();
				}
				else
				{
					if(et3.getText().toString()!=et4.getText().toString())
					{
						Toast toast= Toast.makeText(Main.this,"PASSWORDS DO NOT MATCH", 5000);
						toast.setGravity(Gravity.CENTER, 0, 0);
						toast.show();
					}
					else{
						String password=et3.getText().toString();
						MessageDigest md;
						try {
							md = MessageDigest.getInstance("MD5");
							md.update(password.getBytes());
							byte byteData[] = md.digest();
							
							//convert the byte to hex format method 1
					        StringBuffer sb = new StringBuffer();
					        for (int i = 0; i < byteData.length; i++) {
					         sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
					        }
					        
					        //getting details in local variable to send
					        String usnm=et1.getText().toString();
					        String nm=et2.getText().toString();
					        String pass=sb.toString();
					        String tt=et5.getText().toString();
					        String tn=et6.getText().toString();
					        String num=et7.getText().toString();
					        String mail=et8.getText().toString();
					        String tp="driver";
					        //sending to server
					        try{
					        String data = URLEncoder.encode("username", "UTF-8") 
			                         + "=" + URLEncoder.encode(usnm, "UTF-8"); 
			 
			            data += "&" + URLEncoder.encode("name", "UTF-8") + "="
			                        + URLEncoder.encode(nm, "UTF-8"); 
			 
			            data += "&" + URLEncoder.encode("password", "UTF-8") 
			                        + "=" + URLEncoder.encode(pass, "UTF-8");
			            
			            data += "&" + URLEncoder.encode("email", "UTF-8") 
		                        + "=" + URLEncoder.encode(mail, "UTF-8");
			 
			            data += "&" + URLEncoder.encode("t_type", "UTF-8") 
			                        + "=" + URLEncoder.encode(tt, "UTF-8");
			            
			            data += "&" + URLEncoder.encode("t_number", "UTF-8") 
		                        + "=" + URLEncoder.encode(tn, "UTF-8");
			            
			            data += "&" + URLEncoder.encode("mob", "UTF-8") 
		                        + "=" + URLEncoder.encode(num, "UTF-8");
			            data += "&" + URLEncoder.encode("type", "UTF-8") 
		                        + "=" + URLEncoder.encode(tp, "UTF-8");
			            
			            URL url = new URL("http://testapp1pranav.appspot.com/register?"+data);
			               
			            // Send POST data request
			  
			             HttpURLConnection conn = (HttpURLConnection)url.openConnection(); 
			             conn.setDoOutput(true); 
			             OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream()); 
			             wr.write( data ); 
			             wr.flush(); 
					        }
					        catch(Exception ex)
					        {}
			            
						} catch (NoSuchAlgorithmException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				        
					}
				}
				
				
			}
		});
		b2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				android.os.Process.killProcess(android.os.Process.myPid());
				System.exit(0);
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
