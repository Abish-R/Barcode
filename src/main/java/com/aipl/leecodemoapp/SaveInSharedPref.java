package com.aipl.leecodemoapp;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by kapil on 24/08/16.
 */
public class SaveInSharedPref {
    Activity fromwhich;
    public static final String MyPREFERENCES = "ReportPref";
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
    SharedPreferences mySharedPreferences;
    Calendar c = Calendar.getInstance();
    String date;

    SaveInSharedPref(Activity act){
        fromwhich=act;
        mySharedPreferences = fromwhich.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        long date1 = System.currentTimeMillis();
        date= sdf.format(date1).toString();
    }

    public void saveReport() {
        SharedPreferences.Editor editor = mySharedPreferences.edit();
        editor.putInt(date, 1);
        editor.commit();
    }

    public int getReport(){
        if(mySharedPreferences!=null)
            return mySharedPreferences.getInt(date, 0);
        else
            return 0;
    }
}
