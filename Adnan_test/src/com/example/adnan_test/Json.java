package com.example.adnan_test;

import java.io.InputStream;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.protocol.HTTP;
import org.json.JSONObject;

import android.os.Looper;
import android.widget.Toast;

public class Json {

	protected void send(final String la,final String lo,final String id)
	{
		Thread t = new Thread()
		{
			public void run()
			{ 
				Looper.prepare(); //For Preparing Message Pool for the child Thread
				final String URL="http://192.168.43.189:8888/devserver";
                HttpClient client = new DefaultHttpClient();
                HttpConnectionParams.setConnectionTimeout(client.getParams(), 10000); //Timeout Limit
                HttpResponse response;
                JSONObject json = new JSONObject();
                try
                {
                	 HttpPost post = new HttpPost(URL);
                	 json.put("id", id);
                     json.put("lat", la);
                     json.put("lon", lo);
                     StringEntity se = new StringEntity( json.toString());  
                     se.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
                     post.setEntity(se);
                     response = client.execute(post);
                     
                     /*Checking response */
                     if(response!=null)
                     {
                         InputStream in = response.getEntity().getContent(); //Get the data in the entity
                         Toast.makeText( null, "done", Toast.LENGTH_LONG).show();
                     }
                     
                }
                catch(Exception e)
                {
                	e.printStackTrace();
                }
                Looper.loop(); //Loop in the message queue
			}
		};
		t.start();
	}

}
