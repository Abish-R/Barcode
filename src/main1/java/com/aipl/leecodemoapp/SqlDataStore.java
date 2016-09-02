package com.aipl.leecodemoapp;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

public class SqlDataStore 
{
	 private static final int DATABASE_VERSION = 3;
	 
	    // Database Name
	    static final String DATABASE_NAME = "mylandatabase";
	    private DBHelper myhelper;
	    private final Context myContext;
	    public static SQLiteDatabase myDatabasenew;
	    private SQLiteDatabase myDatabase;
	
 
   private class DBHelper extends SQLiteOpenHelper
	{

		public DBHelper(Context context) 
		    {
			  super(context,DATABASE_NAME, null, DATABASE_VERSION);
			  // TODO Auto-generated constructor stub
			}
			
		

		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			
			
		}

		@Override 
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub

			
			onCreate(db);
			
		}
		
	}
	
	public SqlDataStore(Context c)
	{
		this.myContext =c;
	}
	public  SqlDataStore open() throws SQLException
	{
		myhelper= new DBHelper(myContext);
	    myDatabase = myhelper.getWritableDatabase();
	    myDatabasenew = myhelper.getWritableDatabase();
		return this;
	}
	public void close()
	{
		myhelper.close();
	}
	
	
	//public long insertDetailing( String detail_id,String detail_name) {
	public long insert(String TABLE, ContentValues cv) 
	{
		// TODO Auto-generated method stub
	    return myDatabase.insert(TABLE, null,cv);
    }
	
	public long update(String TABLE, ContentValues cv, String where) 
	{
		// TODO Auto-generated method stub
	    return myDatabase.update(TABLE, cv, where, null);
    }

	
	// Deleting single contact
    public long delete(String TABLE, String where)
    {
    	return myDatabase.delete(TABLE, where, null);
    }
    
 
    
 //---------------------read data ------------------------------------------------
	
	public List<Databasereader> getAllContacts(String TABLE_NAME) {
		List<Databasereader> contactList = new ArrayList<Databasereader>();
		
		String selectQuery ="select * from "+ TABLE_NAME +";"  ;
		//String selectQuery = "select "+ Queryclass.DETAIL_ROWID +" , "+ Queryclass.DETAIL_ID+ " , "+ Queryclass.DETAIL_NAME+ " from " + TABLE_NAME ;
	    Cursor cursor = myDatabase.rawQuery(selectQuery, null);
	    
		if (cursor.moveToFirst()) {
		do {
		       Databasereader dr = new Databasereader();
	           dr.setID(Integer.parseInt(cursor.getString(0)));
		       dr.setuniqueid(cursor.getString(1));
		       dr.setuniquename(cursor.getString(2));
		       contactList.add(dr);
		} while (cursor.moveToNext());
		}
		 return contactList;
		}
  //--------------------------------------------------------------------------------
	
	public Cursor getData(String selectQuery ) {
		
	    Cursor cursor = myDatabase.rawQuery(selectQuery, null);
		 return cursor;
		
	}
	
	
	
 //-------------------------------------------------------------------------------	
	
	public void droptable( String TABLE_NAME , String Query)
	{
		try{
		Cursor cur;
		cur=myDatabase.rawQuery("select * from  "+TABLE_NAME ,null);
        if(cur!= null)
          {
        	myDatabase.execSQL("DROP TABLE " +TABLE_NAME);
          }
            myDatabase.execSQL(Query);
		 }
		catch (SQLiteException e) {
		
			//our table doesn't exist, so we'll create one or take an action.
			
			myDatabase.execSQL(Query);
			
			}

		}

//-------------Create TRANSACTION table---------------------------------------
	public void createtable( String TABLE_NAME , String Query)
	{
		try{
		Cursor cur;
		cur=myDatabase.rawQuery("select * from  "+TABLE_NAME ,null);
        if(cur == null)
          {
        	 myDatabase.execSQL(Query);
        	 System.out.println(TABLE_NAME+"Table created"+cur.toString());
          }
          
		 }
		catch (SQLiteException e) {
		
			//our table doesn't exist, so we'll create one or take an action.
			
			myDatabase.execSQL(Query);
			
			}

		}
}
