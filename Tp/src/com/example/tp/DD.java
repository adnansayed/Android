package com.example.tp;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.ViewGroup.LayoutParams;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class DD extends Activity{
	String companies[] = {"Google","Windows","iPhone","Nokia","Samsung",
            "Google","Windows","iPhone","Nokia","Samsung",
            "Google","Windows","iPhone","Nokia","Samsung"};
      String os[] ={"Android","Mango","iOS","Symbian","Bada",
            "Android","Mango","iOS","Symbian","Bada",
            "Android","Mango","iOS","Symbian","Bada"};
      
	 TableLayout t;
     TableRow tr;
     TextView companyTV,valueTV;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.display);		         
            t = (TableLayout) findViewById(R.id.maintable);
          addHeaders();
           addData();
           
	}
	public void addHeaders(){
		 
        /** Create a TableRow dynamically **/
       tr = new TableRow(this);
       tr.setLayoutParams(new LayoutParams(
               LayoutParams.FILL_PARENT,
               LayoutParams.WRAP_CONTENT));

       /** Creating a TextView to add to the row **/
       TextView companyTV = new TextView(this);
       companyTV.setText("Companies");
       companyTV.setTextColor(Color.GRAY);
       companyTV.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
       companyTV.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.WRAP_CONTENT));
       companyTV.setPadding(5, 5, 5, 0);
       tr.addView(companyTV);  // Adding textView to tablerow.

       /** Creating another textview **/
       TextView valueTV = new TextView(this);
       valueTV.setText("Operating Systems");
       valueTV.setTextColor(Color.GRAY);
       valueTV.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.WRAP_CONTENT));
       valueTV.setPadding(5, 5, 5, 0);
       valueTV.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
       tr.addView(valueTV); // Adding textView to tablerow.

       // Add the TableRow to the TableLayout
       t.addView(tr, new TableLayout.LayoutParams(
               LayoutParams.FILL_PARENT,
               LayoutParams.WRAP_CONTENT));

       // we are adding two textviews for the divider because we have two columns
       tr = new TableRow(this);
       tr.setLayoutParams(new LayoutParams(
               LayoutParams.FILL_PARENT,
               LayoutParams.WRAP_CONTENT));

       /** Creating another textview **/
       TextView divider = new TextView(this);
       divider.setText("-----------------");
       divider.setTextColor(Color.GREEN);
       divider.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.WRAP_CONTENT));
       divider.setPadding(5, 0, 0, 0);
       divider.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
       tr.addView(divider); // Adding textView to tablerow.

       TextView divider2 = new TextView(this);
       divider2.setText("-------------------------");
       divider2.setTextColor(Color.GREEN);
       divider2.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.WRAP_CONTENT));
       divider2.setPadding(5, 0, 0, 0);
       divider2.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
       tr.addView(divider2); // Adding textView to tablerow.

       // Add the TableRow to the TableLayout
       t.addView(tr, new TableLayout.LayoutParams(
               LayoutParams.FILL_PARENT,
               LayoutParams.WRAP_CONTENT));
   }
	public void addData(){
		 
        for (int i = 0; i < companies.length; i++)
        {
            /** Create a TableRow dynamically **/
            tr = new TableRow(this);
            tr.setLayoutParams(new LayoutParams(
                    LayoutParams.FILL_PARENT,
                    LayoutParams.WRAP_CONTENT));
 
            /** Creating a TextView to add to the row **/
            companyTV = new TextView(this);
            companyTV.setText(companies[i]);
            companyTV.setTextColor(Color.RED);
            companyTV.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
            companyTV.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.WRAP_CONTENT));
            companyTV.setPadding(5, 5, 5, 5);
            tr.addView(companyTV);  // Adding textView to tablerow.
 
            /** Creating another textview **/
            valueTV = new TextView(this);
            valueTV.setText(os[i]);
            valueTV.setTextColor(Color.GREEN);
            valueTV.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.WRAP_CONTENT));
            valueTV.setPadding(5, 5, 5, 5);
            valueTV.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
            tr.addView(valueTV); // Adding textView to tablerow.
 
            // Add the TableRow to the TableLayout
            t.addView(tr, new TableLayout.LayoutParams(
                    LayoutParams.FILL_PARENT,
                    LayoutParams.WRAP_CONTENT));
        }
    }
}


