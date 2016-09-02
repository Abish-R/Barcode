package com.aipl.leecodemoapp;

import android.net.Uri;
import android.provider.BaseColumns;

public class Queryclass {

	
	//-----------Create Table------------------------------ 

	public static final String TABLE_MISCDESCRIPTION="miscdescription";
	




	public static final String DESC_ROWID ="desc_rowid";
	public static final String DESC_ID ="desc_id";
	public static final String DOCMAND ="doc_mand";
	public static final String FIELDLABEL ="field_label";
	public static final String DESCNAME ="descname";
	
	

		
		
		public static String CREATE_MISCDESCRIPTION = "CREATE TABLE "+ TABLE_MISCDESCRIPTION + " (" + 
				DESC_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + 
				DESC_ID + " TEXT NOT NULL, "+
				DOCMAND + " TEXT NOT NULL, "+
				FIELDLABEL + " TEXT NOT NULL, "+
				DESCNAME + " TEXT NOT NULL);"; 
		
		


}

