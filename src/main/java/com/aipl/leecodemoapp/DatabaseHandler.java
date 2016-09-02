package com.aipl.leecodemoapp;

/**
 * Created by HelixTech-Admin on 3/12/2016.
 */
import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper {
    /** All Static variables*/
    private static final int DATABASE_VERSION = 1; 				  // Database Version
    private static final String DATABASE_NAME = "mydb";  	   	  // Database Name
    private static final String TABLE_NAME_ATTENDANCE = "com/aipl/leecodemoapp/attendance";  // Contacts table name
    private static final String TABLE_NAME_BARCODE = "barcode";  // Contacts table name
    private static final String TABLE_NAME_BARCODE_DEMO_REPORTING = "demo_reporting_barcode";



    /** Contacts Table Columns names*/
    private static final String KEY_ID = "id";
    private static final String KEY_USERID = "user_id";
    private static final String KEY_BARCODE = "barcode";
    //private static final String KEY_MOBILE = "mobile";
    //private static final String KEY_DEVICEID = "device_id";
    private static final String KEY_IN_TIME = "in_time";
    private static final String KEY_OUT_TIME = "out_time";
    private static final String KEY_IN_PHOTO_LOC = "in_pic_name";
    private static final String KEY_OUT_PHOTO_LOC = "out_pic_name";
    //private static final String KEY_Auto_UPDATE_TIME = "auto_update_time";
    private static final String KEY_IN_GPS_LAT = "in_gps_lat";//changed
    private static final String KEY_IN_GPS_LON = "in_gps_lon";//changed
    private static final String KEY_OUT_GPS_LAT = "out_gps_lat";//changed
    private static final String KEY_OUT_GPS_LON = "out_gps_lon";//changed
    private static final String KEY_IN_GPS_ADDRESS = "in_gps_address";
    private static final String KEY_OUT_GPS_ADDRESS = "out_gps_address";//changed
    private static final String KEY_UP_STATE = "upload_state";
    private static final String KEY_ATTENDANCE_DATE = "attendance_date";
    private static final String KEY_IN_IMAGE_UPLOAD_STATE = "in_image_upload_state";
    private static final String KEY_OUT_IMAGE_UPLOAD_STATE = "out_image_upload_state";

    private static final String DEMO_KEY_ID = "id";
    private static final String DEMO_KEY_BARCODE = "demo_rep_barcode";
    private static final String DEMO_KEY_ATTENDANCE_DATE = "demo_rep_attendance_date";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /** Creating Tables*/
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_NAME_ATTENDANCE + "("+ KEY_ID + " INTEGER PRIMARY KEY autoincrement not null,"
                + KEY_USERID + " TEXT,"+ KEY_IN_TIME
                +" DATETIME,"+ KEY_OUT_TIME + " DATETIME," + KEY_IN_PHOTO_LOC + " TEXT,"+ KEY_OUT_PHOTO_LOC + " TEXT,"+KEY_IN_GPS_LAT+" TEXT,"
                + KEY_IN_GPS_LON+ " TEXT,"+KEY_OUT_GPS_LAT+ " TEXT,"+KEY_OUT_GPS_LON+ " TEXT,"+KEY_IN_GPS_ADDRESS+ " TEXT,"
                + KEY_OUT_GPS_ADDRESS+ " TEXT," + KEY_UP_STATE+" TEXT,"+KEY_ATTENDANCE_DATE+" DATETIME DEFAULT CURRENT_DATE,"
                + KEY_IN_IMAGE_UPLOAD_STATE+" TEXT,"+ KEY_OUT_IMAGE_UPLOAD_STATE +" TEXT)";//DATETIME DEFAULT CURRENT_DATE"+")";
        db.execSQL(CREATE_CONTACTS_TABLE);

        String CREATE_CONTACTS_BARCODE = "CREATE TABLE " + TABLE_NAME_BARCODE + "("+ KEY_ID + " INTEGER PRIMARY KEY autoincrement not null,"
                + KEY_BARCODE + " TEXT,"+ KEY_ATTENDANCE_DATE+" DATETIME DEFAULT CURRENT_DATE)";//DATETIME DEFAULT CURRENT_DATE"+")";
        db.execSQL(CREATE_CONTACTS_BARCODE);

        String CREATE_DEMO_REPORTING_BARCODE = "CREATE TABLE " + TABLE_NAME_BARCODE_DEMO_REPORTING + "("+ DEMO_KEY_ID + " INTEGER PRIMARY KEY autoincrement not null,"
                + DEMO_KEY_BARCODE + " TEXT,"+ DEMO_KEY_ATTENDANCE_DATE+" DATETIME DEFAULT CURRENT_DATE)";
        db.execSQL(CREATE_DEMO_REPORTING_BARCODE);

    }

    /** Upgrading database*/
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_ATTENDANCE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_BARCODE);
        // Create tables again
        onCreate(db);
    }

    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */

    /** Adding new rate*/
    long addDataToDB_DemoReporting(String val) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DEMO_KEY_BARCODE, val);

        long temp = db.insert(TABLE_NAME_BARCODE_DEMO_REPORTING, null, values);
        db.close(); // Closing database connection
        return temp;
    }

    /** Adding new rate*/
    long addDataToDB(String val) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_BARCODE, val);

        long temp = db.insert(TABLE_NAME_BARCODE, null, values);
        db.close(); // Closing database connection
        return temp;
    }

    public List<GetSetAttandance> getAllBarCodes(){
        List<GetSetAttandance> list = new ArrayList<GetSetAttandance>();
        String Query = "SELECT * FROM " + TABLE_NAME_BARCODE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(Query, null);
        int i = 0;
        String[] val = new String[2];
        if (cursor.moveToFirst()) {
            do {
                GetSetAttandance values = new GetSetAttandance();
                values.setBarcode(cursor.getString(1));

                val[1]=cursor.getString(1);
                if(i>0 && (val[1].equals(val[0]))) {
                    values.setAttandenceDate("");
                }
                else
                    values.setAttandenceDate(cursor.getString(2));

                val[0]=val[1];
                list.add(values);
                i++;
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return list;
    }


    public List<GetSetAttandance> getAllBarCodes_DemoRep(){
        List<GetSetAttandance> list = new ArrayList<GetSetAttandance>();
        String Query = "SELECT * FROM " + TABLE_NAME_BARCODE_DEMO_REPORTING;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(Query, null);
        int i = 0;
        String[] val = new String[2];
        if (cursor.moveToFirst()) {
            do {
                GetSetAttandance values = new GetSetAttandance();
                values.setBarcode(cursor.getString(1));

                val[1]=cursor.getString(1);
                if(i>0 && (val[1].equals(val[0]))) {
                    values.setAttandenceDate("");
                }
                else
                    values.setAttandenceDate(cursor.getString(2));

                val[0]=val[1];
                list.add(values);
                i++;
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return list;
    }

//    int checkInTimePunchedOrNot(String date){
//        SQLiteDatabase db = this.getReadableDatabase();
//        String Query = "SELECT "+KEY_ATTENDANCE_DATE+" FROM " +TABLE_NAME +" WHERE "+KEY_ATTENDANCE_DATE +"='"+date+"'";
//        Cursor cursor = db.rawQuery(Query, null);
//        return cursor.getCount();
//    }

//    int checkOutTimePunchedOrNot(String date){
//        SQLiteDatabase db = this.getReadableDatabase();
////        if(checkInTimePunchedOrNot(date)<1)
////            return 100;
////        else {
//            String Query = "SELECT " + KEY_ATTENDANCE_DATE + " FROM " + TABLE_NAME + " WHERE " + KEY_OUT_TIME + " IS NULL AND " +
//                    KEY_ATTENDANCE_DATE + "='" + date + "'";
//            Cursor cursor = db.rawQuery(Query, null);
//            return cursor.getCount();
//        //}
//    }

    /** Update rate of a date */
//    public int updateAttendance(GetSetAttandance atten) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues values = new ContentValues();
//        values.put(KEY_OUT_TIME, atten.getOutTime());                  // Get Rate
//        values.put(KEY_OUT_PHOTO_LOC, atten.getOutPicLocation());// Get Rate
//        values.put(KEY_OUT_GPS_LAT,atten.getOutGpsLat());				// Get Created date
//        values.put(KEY_OUT_GPS_LON,atten.getOutGpsLon());                  // Get Up State
//        values.put(KEY_OUT_GPS_ADDRESS,atten.getOutAddress());
//        values.put(KEY_UP_STATE,atten.getUpState());				// Get Created date
//        values.put(KEY_ATTENDANCE_DATE,atten.getAttandenceDate());
//        values.put(KEY_OUT_IMAGE_UPLOAD_STATE,atten.getOutPhotoUpState());
//        /** updating row*/
//        int temp= db.update(TABLE_NAME, values, KEY_ATTENDANCE_DATE + " = ?",
//                new String[]{atten.getAttandenceDate()});
//        db.close();
//        return temp;
//    }


//    public List<GetSetAttandance> getAttendanceDataNotUploaded(){
//        List<GetSetAttandance> data_list = new ArrayList<GetSetAttandance>();
//        String Query = "SELECT * FROM " + TABLE_NAME +" WHERE "+KEY_UP_STATE +"='N'";
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor cursor = db.rawQuery(Query, null);
//        if (cursor.moveToFirst()) {
//            do {
//                GetSetAttandance values = new GetSetAttandance();
//                values.setUserId(cursor.getString(1));
//                values.setEmail(cursor.getString(2));
//                values.setMobile(cursor.getString(3));
//                values.setDeviceId(cursor.getString(4));
//                values.setInTime(cursor.getString(5));
//                values.setOutTime(cursor.getString(6));
//                values.setInPicLocation(cursor.getString(7));
//                values.setOutPicLocation(cursor.getString(8));
//                values.setInGpsLat(cursor.getString(9));
//                values.setInGpsLon(cursor.getString(10));
//                values.setOutGpsLat(cursor.getString(11));
//                values.setOutGpsLon(cursor.getString(12));
//                values.setInAddress(cursor.getString(13));
//                values.setOutAddress(cursor.getString(14));
//                values.setUpState(cursor.getString(15));
//                values.setAttandenceDate(cursor.getString(16));
//                data_list.add(values);
//            } while (cursor.moveToNext());
//        }
//        cursor.close();
//        db.close();
//
//        return data_list;
//    }

//    public void changeImageUploadState(String date, String input){
//        SQLiteDatabase db = this.getReadableDatabase();
//        ContentValues values = new ContentValues();
//        if(input.equals("In"))
//            values.put(KEY_IN_IMAGE_UPLOAD_STATE, "U");
//        else if(input.equals("Out"))
//            values.put(KEY_OUT_IMAGE_UPLOAD_STATE, "U");
//        int temp = db.update(TABLE_NAME, values, KEY_ATTENDANCE_DATE + " = ?",
//                new String[]{date});
//        db.close();
//    }

//    int getCountOfRow(){
//        String Query = "SELECT * FROM " + TABLE_NAME;
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor cursor = db.rawQuery(Query, null);
//        int temp=cursor.getCount();
//        return temp;
//    }

//     String getLastRecord(){
//        String Query = "SELECT "+KEY_ATTENDANCE_DATE+" FROM " + TABLE_NAME +" ORDER BY "+KEY_ATTENDANCE_DATE +" DESC LIMIT 1";
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor cursor = db.rawQuery(Query, null);
//        String temp=null;
//         if (cursor.moveToFirst())
//            temp= cursor.getString(0);
//        return temp;
//    }

//    String getLastRecordOutTime(){
//        return null;
//    }

//    void checkImgUpstateBeforeDelete(String date){
//        SQLiteDatabase db = this.getWritableDatabase();
//
//        long dd=db.delete(TABLE_NAME, KEY_ATTENDANCE_DATE + " = ? AND " + KEY_IN_IMAGE_UPLOAD_STATE + " =? AND " +
//                KEY_OUT_IMAGE_UPLOAD_STATE + " =? AND " + KEY_UP_STATE+" =?", new String[]{date,"U","U","U"});
//        ContentValues values = new ContentValues();
//        values.put(KEY_UP_STATE, "U");
//        int temp = db.update(TABLE_NAME, values, KEY_ATTENDANCE_DATE + " = ?", new String[]{date});
////        String Query = "SELECT * FROM " + TABLE_NAME;
////        Cursor cursor = db.rawQuery(Query, null);
////        int temp1 = cursor.getCount();
//        db.close();
//    }
}
