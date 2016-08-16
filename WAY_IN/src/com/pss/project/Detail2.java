package com.pss.project;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.TableRow.LayoutParams;

public class Detail2 extends Activity{
	TableLayout table_layout;
	Dbadapter db=new Dbadapter(this);
	Cursor c;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.detail2);
		Log.d("test", "detail activity entered");
		table_layout = (TableLayout) findViewById(R.id.tableLayout1);
		db.open();
		c = db.readentry2();
		int rows = c.getCount();
		int cols = c.getColumnCount();
	    Log.d("test", "Value: " + c.getCount());
	    Log.d("test", "Value: " + c.getColumnCount());
		//table_layout.removeAllViews();
		BuildTable(rows, cols);
	}
	private void BuildTable(int rows, int cols) {
		c.moveToFirst();

		// outer for loop
		for (int i = 1; i <= rows; i++) {

			TableRow row = new TableRow(this);
			row.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
					LayoutParams.WRAP_CONTENT));

			// inner for loop
			for (int j = 1; j < cols; j++) {

				TextView tv = new TextView(this);
				tv.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
						LayoutParams.WRAP_CONTENT));
				tv.setBackgroundResource(R.drawable.cell_shape);
				tv.setPadding(5, 5, 5, 5);
				tv.setText(c.getString(j));
				Log.d("test", "Value: " + c.getString(j));
				row.addView(tv);

			}
			c.moveToNext();
			table_layout.addView(row);

		}
	}


}
