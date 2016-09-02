package com.aipl.leecodemoapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ReportsActivity extends AppCompatActivity implements View.OnClickListener{

    private Button buttonSellOutValueReport, buttonSellOutVolumeReport, buttonCompetitorSalesReport, buttonAccessorySalesReport,
            buttonStockReport, buttonAttendanceReport, buttonDemoHandsetReport;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reports);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        buttonSellOutValueReport = (Button) findViewById(R.id.buttonSellOutValueReport);
        buttonSellOutVolumeReport = (Button) findViewById(R.id.buttonSellOutVolumeReport);
        buttonCompetitorSalesReport = (Button) findViewById(R.id.buttonCompetitorSalesReport);
        buttonAccessorySalesReport = (Button) findViewById(R.id.buttonAccessorySalesReport);
        buttonStockReport = (Button) findViewById(R.id.buttonStockReport);
        buttonAttendanceReport = (Button) findViewById(R.id.buttonAttendanceReport);
        buttonDemoHandsetReport = (Button) findViewById(R.id.buttonDemoHandsetReport);

        buttonSellOutValueReport.setOnClickListener(this);
        buttonSellOutVolumeReport.setOnClickListener(this);
        buttonCompetitorSalesReport.setOnClickListener(this);
        buttonAccessorySalesReport.setOnClickListener(this);
        buttonStockReport.setOnClickListener(this);
        buttonAttendanceReport.setOnClickListener(this);
        buttonDemoHandsetReport.setOnClickListener(this);

        Drawable dr = getResources().getDrawable(R.drawable.reports);
        dr.setBounds(10, 0, 80, 60);
        buttonSellOutValueReport.setCompoundDrawables(dr, null, null, null );

        dr = getResources().getDrawable(R.drawable.reports);
        dr.setBounds(10, 0, 80, 60);
        buttonSellOutVolumeReport.setCompoundDrawables(dr, null, null, null );

        dr = getResources().getDrawable(R.drawable.reports);
        dr.setBounds(10, 0, 80, 60);
        buttonCompetitorSalesReport.setCompoundDrawables(dr, null, null, null );

        dr = getResources().getDrawable(R.drawable.reports);
        dr.setBounds(10, 0, 80, 60);
        buttonAccessorySalesReport.setCompoundDrawables(dr, null, null, null );

        dr = getResources().getDrawable(R.drawable.reports);
        dr.setBounds(10, 0, 80, 60);
        buttonStockReport.setCompoundDrawables(dr, null, null, null );

        dr = getResources().getDrawable(R.drawable.reports);
        dr.setBounds(10, 0, 80, 60);
        buttonAttendanceReport.setCompoundDrawables(dr, null, null, null );

        dr = getResources().getDrawable(R.drawable.reports);
        dr.setBounds(10, 0, 80, 60);
        buttonDemoHandsetReport.setCompoundDrawables(dr, null, null, null );
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {

        Toast.makeText(ReportsActivity.this, "module not implemented", Toast.LENGTH_LONG).show();

        switch (v.getId()) {
            case R.id.buttonSellOutValueReport:
//                startActivity(new Intent(ReportsActivity.this, DAR_Activity.class));
                break;
            case R.id.buttonSellOutVolumeReport:
//                startActivity(new Intent(ReportsActivity.this, ReportsActivity.class));
                break;
            case R.id.buttonCompetitorSalesReport:
//                startActivity(new Intent(ReportsActivity.this, FeedbackActivity.class));
                break;
            case R.id.buttonAccessorySalesReport:
//                startActivity(new Intent(ReportsActivity.this, MessageActivity.class));
                break;
            case R.id.buttonStockReport:
//                startActivity(new Intent(ReportsActivity.this, SupportDeskActivity.class));
                break;
            case R.id.buttonAttendanceReport:
//                startActivity(new Intent(ReportsActivity.this, SupportDeskActivity.class));
                break;
            case R.id.buttonDemoHandsetReport:
//                startActivity(new Intent(ReportsActivity.this, SupportDeskActivity.class));
                break;
        }
    }
}
