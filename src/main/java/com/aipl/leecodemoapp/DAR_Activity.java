package com.aipl.leecodemoapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DAR_Activity extends AppCompatActivity implements View.OnClickListener {

    private TextView textViewDate;
    private Button buttonDailySellOut, buttonDailyAccessory, buttonDailyCompetitor, buttonWeeklyStock, buttonHandsetDemo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dar_);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Calendar c = Calendar.getInstance();
        System.out.println("Current time => " + c.getTime());

        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        String formattedDate = df.format(c.getTime());

        textViewDate = (TextView) findViewById(R.id.textViewDate);
        textViewDate.setText(formattedDate);

        buttonDailySellOut = (Button) findViewById(R.id.buttonDailySellOut);
        buttonDailyAccessory = (Button) findViewById(R.id.buttonDailyAccessory);
        buttonDailyCompetitor = (Button) findViewById(R.id.buttonDailyCompetitor);
        buttonWeeklyStock = (Button) findViewById(R.id.buttonWeeklyStock);
        buttonHandsetDemo = (Button) findViewById(R.id.buttonHandsetDemo);

        buttonDailySellOut.setOnClickListener(this);
        buttonDailyAccessory.setOnClickListener(this);
        buttonDailyCompetitor.setOnClickListener(this);
        buttonWeeklyStock.setOnClickListener(this);
        buttonHandsetDemo.setOnClickListener(this);

        Drawable dr = getResources().getDrawable(R.drawable.bar_code);
        dr.setBounds(10, 0, 80, 60);
        buttonDailySellOut.setCompoundDrawables(dr, null, null, null );


        dr = getResources().getDrawable(R.drawable.accessory);
        dr.setBounds(10, 0, 80, 60);
        buttonDailyAccessory.setCompoundDrawables(dr, null, null, null );


        dr = getResources().getDrawable(R.drawable.competitor);
        dr.setBounds(10, 0, 80, 60);
        buttonDailyCompetitor.setCompoundDrawables(dr, null, null, null );


        dr = getResources().getDrawable(R.drawable.weekly_stock);
        dr.setBounds(10, 0, 80, 60);
        buttonWeeklyStock.setCompoundDrawables(dr, null, null, null );


        dr = getResources().getDrawable(R.drawable.handset);
        dr.setBounds(10, 0, 80, 60);
        buttonHandsetDemo.setCompoundDrawables(dr, null, null, null );

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == android.R.id.home) {
            startActivity(new Intent(this,MainActivity_new.class));
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        startActivity(new Intent(this,MainActivity_new.class));
        finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonDailySellOut:
                startActivity(new Intent(DAR_Activity.this, MainActivityBarcode.class));
                break;
            case R.id.buttonDailyAccessory:
                startActivity(new Intent(DAR_Activity.this, Accessory_Reporting.class));
                break;
            case R.id.buttonDailyCompetitor:
                startActivity(new Intent(DAR_Activity.this, Competition_reporting.class));
                break;
            case R.id.buttonWeeklyStock:
                startActivity(new Intent(DAR_Activity.this, WeeklyStockReporting.class));
                break;
            case R.id.buttonHandsetDemo:
                startActivity(new Intent(DAR_Activity.this, DemoReporting.class));
                break;
        }
    }
}
