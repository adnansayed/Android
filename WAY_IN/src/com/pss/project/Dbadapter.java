package com.pss.project;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class Dbadapter {

	
	private static final String BALANCE = "balance";
	private static final String AMT_PAID = "amt_paid";
	private static final String RDATE = "rdate";
	private static final String _PID = "_pid";
	private static final String PAYMENT_TABLE = "payment";
	private static final String PURCHASE_TABLE = "purchase";
	private static final String TOTAL = "total";
	private static final String PR_KG = "pr_kg";
	private static final String KG = "kg";
	private static final String PIECE = "piece";
	private static final String _ID = "_id";
	private static final String PDATE = "pdate";
	private static final String TAG = "Dbadapter";
	private static final String DATABASE_NAME ="WAYIN";
	private static final int DATABASE_VERSION = 2;
	
	private static final String DATABASE_CREATE =
	"CREATE TABLE IF NOT EXISTS "+PURCHASE_TABLE+" ("+_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
	+ ""+PDATE+" DATE NOT NULL,"+PIECE+" INTEGER NOT NULL,"+KG+" VARCHAR NOT NULL,"+PR_KG+" VARCHAR NOT NULL,"+TOTAL+" VARCHAR NOT NULL);";
	
	//creation of payment table
	
	private static final String CREATE_PAYMENT="CREATE TABLE IF NOT EXISTS "+PAYMENT_TABLE+"("+_PID+" INTEGER PRIMARY KEY AUTOINCREMENT," +
			""+RDATE+" DATE NOT NULL,"+AMT_PAID+" VARCHAR NOT NULL,"+BALANCE+" VARCHAR NOT NULL);";
	
	
	
	private final Context context;
	private DatabaseHelper DBHelper;
	private SQLiteDatabase db;
	
	public Dbadapter(Context ctx)
	{
	this.context = ctx;
	DBHelper = new DatabaseHelper(context);
	}
	
	private static class DatabaseHelper extends SQLiteOpenHelper
	{
	DatabaseHelper(Context context)
	{
	super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db)
	{
	try {
		 Log.d("test", "attempting to create db");
	     db.execSQL(DATABASE_CREATE);
	     db.execSQL(CREATE_PAYMENT);
	    
	} catch (SQLException e) {
	e.printStackTrace();
	}
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
	{
	Log.w(TAG, "Upgrading database from version " + oldVersion + "to "
	+ newVersion + ",which will destroy all old data");
	db.execSQL("DROP TABLE IF EXISTS PURCHASE_TABLE");
	db.execSQL("DROP TABLE IF EXISTS PAYMENT_TABLE");
	
	onCreate(db);
	}
	}
	
	//---opens the database---
	public Dbadapter open() throws SQLException
	{
	db = DBHelper.getWritableDatabase();
	return this;
	}
	
	
	//---closes the database---
	public void close()
	{
	DBHelper.close();
	}
	
	//---insert a PURCHASE into the database---
	public long insertPurchase(String pdate, String piece,String kg,String pr_kg,String total)
	{
	Log.d("test", "insert entered");
	db=DBHelper.getWritableDatabase();
	ContentValues iv=new ContentValues();
	iv.put(PDATE,pdate);
	iv.put(PIECE, piece);
	iv.put(KG,kg);
	iv.put(PR_KG,pr_kg);
	iv.put(TOTAL,total);
	Log.d("test", "values taken");
	return db.insert(PURCHASE_TABLE, null, iv);
	}
	
	//---insert a payment into the database---
		public long insertPayment(String rdate, String amt_paid,String balance)
		{
		Log.d("test", "PAYMENT entered");
		db=DBHelper.getWritableDatabase();
		ContentValues iv=new ContentValues();
		iv.put(RDATE,rdate);
		iv.put(AMT_PAID,amt_paid);
		iv.put(BALANCE,balance);
		Log.d("test", "values taken");
		return db.insert(PAYMENT_TABLE, null, iv);
		}
		
	//--summing the values
	public String sumall()
	{
		Log.d("test", "sumall entered");
		db=DBHelper.getReadableDatabase();
		Float columntotal =(float) 0.0;
			Log.d("test", "cursor entered");
		 Cursor cursor1 = db.rawQuery(
		     "SELECT SUM(TOTAL) FROM purchase", null);
			Log.d("test", "query runned successfully");
		       if(cursor1.moveToFirst()) {
		         columntotal = cursor1.getFloat(0);
		     	Log.d("test", "cursor calculation");
		     }
		   cursor1.close();
		 
		 String  sumtotal = Float.toString(columntotal);          
			Log.d("test", "string converted");
		  return sumtotal;
		
		
	 
	}
	
	//sum the pieces
	public String sump()
	{
		Log.d("test", "sump entered");
		db=DBHelper.getReadableDatabase();
		 Float columntotal =(float) 0.0;
			Log.d("test", "cursor entered");
		 Cursor cursor1 = db.rawQuery(
		     "SELECT SUM(PIECE) FROM purchase", null);
			Log.d("test", "query runned successfully");
		       if(cursor1.moveToFirst()) {
		         columntotal = cursor1.getFloat(0);
		     	Log.d("test", "cursor calculation");
		     }
		   cursor1.close();
		 
		 String  sumtotal = Float.toString(columntotal);          
			Log.d("test", "string converted");
		  return sumtotal;
		
		
	 
	}
	
	//SUM THE KG
	public String sumk()
	{
		Log.d("test", "sumk entered");
		db=DBHelper.getReadableDatabase();
		Float columntotal =(float) 0.0;
			Log.d("test", "cursor entered");
		 Cursor cursor1 = db.rawQuery(
		     "SELECT SUM(KG) FROM purchase", null);
			Log.d("test", "query runned successfully");
		       if(cursor1.moveToFirst()) {
		         columntotal = cursor1.getFloat(0);
		     	Log.d("test", "cursor calculation");
		     }
		   cursor1.close();
		 
		 String  sumtotal = Float.toString(columntotal);          
			Log.d("test", "string converted");
		  return sumtotal;
		 
	}
	//sum the paid
	public String sumpd()
	{
		Log.d("test", "sumpd entered");
		db=DBHelper.getReadableDatabase();
		Float columntotal =(float) 0.0;
			Log.d("test", "cursor entered");
		 Cursor cursor1 = db.rawQuery(
		     "SELECT SUM(AMT_PAID) FROM payment", null);
			Log.d("test", "query runned successfully");
		       if(cursor1.moveToFirst()) {
		         columntotal = cursor1.getFloat(0);
		     	Log.d("test", "cursor calculation");
		     }
		   cursor1.close();
		 
		 String  sumtotal = Float.toString(columntotal);          
			Log.d("test", "string converted");
		  return sumtotal;
		
		
	 
	}

	
	//---deletes all---
	public boolean deleteall()
	{
		Log.d("test", "delete entered");
		
		db=DBHelper.getWritableDatabase();
		Log.d("test", "running query");
		db.delete(PURCHASE_TABLE,null, null);
		db.delete(PAYMENT_TABLE,null, null);
		//ALTER TABLE tablename AUTO_INCREMENT = 1
		return false;
	 
	}
	
	//---retrieves all the contacts---
	public String getAllPurchase()
	{
		Log.d("test", "display all entered");
		db=DBHelper.getReadableDatabase();
		String[] s=new String[]{_ID, PDATE,PIECE,KG,PR_KG,TOTAL};
		Cursor c=db.query(PURCHASE_TABLE,s, null, null, null, null, null);
		String result="";
		int irow=c.getColumnIndex(_ID);
		int d=c.getColumnIndex(PDATE);
		int pi=c.getColumnIndex(PIECE);
		int k=c.getColumnIndex(KG);
		int pk=c.getColumnIndex(PR_KG);
		int tt=c.getColumnIndex(TOTAL);
		
		for(c.moveToFirst();!c.isAfterLast();c.moveToNext())
		{
			result=result+c.getString(irow)+"\t\t\t"+c.getString(d)+"\t\t\t"+c.getString(pi)+"\t\t\t"+c.getString(k)+"\t\t\t\t"+c.getString(pk)+"\t\t\t"+c.getString(tt)+"\n";
		}
		return result;
	
	}
	
	//retrives all payments
	public String getAllPayment()
	{
		Log.d("test", "display2 all entered");
		db=DBHelper.getReadableDatabase();
		String[] s=new String[]{_PID, RDATE,AMT_PAID,BALANCE};
		Cursor c1=db.query(PAYMENT_TABLE,s, null, null, null, null, null);
		String result="";
		int irow=c1.getColumnIndex(_PID);
		int d=c1.getColumnIndex(RDATE);
		int pi=c1.getColumnIndex(AMT_PAID);
		int k=c1.getColumnIndex(BALANCE);
		
		Log.d("test", "loop entered");
		for(c1.moveToFirst();!c1.isAfterLast();c1.moveToNext())
		{
			Log.d("test", "insde loop");
			result=result+c1.getString(irow)+"\t\t\t\t\t"+c1.getString(d)+"\t\t\t\t\t"+c1.getString(pi)+"\t\t\t\t\t"+c1.getString(k)+"\n";
		}
		
		Log.d("test", "loop completed");
		return result;
	}
	
	//---retrieves a particular contact---
	public Cursor getPurchase(long rowId) throws SQLException
	{
	Cursor mCursor =
	db.query(true,PURCHASE_TABLE, new String[] {_ID,
	PDATE, PIECE,KG,PR_KG,TOTAL}, _ID + "=" + rowId, null,
	null, null, null, null);
	if (mCursor != null) {
	mCursor.moveToFirst();
	}
	return mCursor;
	}
	
	//--getting count
	public Cursor readentry()
	{
		db=DBHelper.getReadableDatabase();
		String[] s=new String[]{_ID, PDATE,PIECE,KG,PR_KG,TOTAL};
		Cursor c=db.query(PURCHASE_TABLE,s, null, null, null, null, null);
		
		if (c != null)
		      {
			   c.moveToFirst();
			  }
			  return c;
	}
	
	//--getting count
		public Cursor readentry2()
		{
			db=DBHelper.getReadableDatabase();
			String[] s=new String[]{_PID, RDATE,AMT_PAID,BALANCE};
			Cursor c1=db.query(PAYMENT_TABLE,s, null, null, null, null, null);
			
			if (c1 != null)
			      {
				   c1.moveToFirst();
				  }
				  return c1;
		}
		
	
	//---updates a contact---
	public boolean updatePurchase(long rowId, String pdate, String piece,String kg,String pr_kg,String total)
	{
		ContentValues args = new ContentValues();
		args.put(PDATE,pdate);
		args.put(PIECE, piece);
		args.put(KG,kg);
		args.put(PR_KG,pr_kg);
		args.put(TOTAL,total);
		return db.update(PURCHASE_TABLE, args, _ID + "=" + rowId, null) > 0;
		}
}
