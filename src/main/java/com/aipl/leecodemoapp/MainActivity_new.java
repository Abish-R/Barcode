package com.aipl.leecodemoapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity_new extends AppCompatActivity implements View.OnClickListener{

    private TextView textViewName, textViewDate;
    private Button buttonDAR, buttonReport, buttonFeedback, buttonMessage, buttonSupportDesk;
    private FloatingActionButton fab;
    int buttonHide=-1,reportThere=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Calendar c = Calendar.getInstance();
        System.out.println("Current time => " + c.getTime());

        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        String formattedDate = df.format(c.getTime());

        textViewName = (TextView) findViewById(R.id.textViewName);
        textViewName.setText("name here");

        textViewDate = (TextView) findViewById(R.id.textViewDate);
        textViewDate.setText(formattedDate);

        buttonReport = (Button) findViewById(R.id.buttonReport);
        buttonFeedback = (Button) findViewById(R.id.buttonFeedback);
        buttonMessage = (Button) findViewById(R.id.buttonMessage);
        buttonSupportDesk = (Button) findViewById(R.id.buttonSupportDesk);

        buttonReport.setOnClickListener(this);
        buttonFeedback.setOnClickListener(this);
        buttonMessage.setOnClickListener(this);
        buttonSupportDesk.setOnClickListener(this);

        buttonHide = getIntent().getExtras().getInt("mainpage_hide");
        if(buttonHide==1){
            buttonDAR = (Button) findViewById(R.id.buttonDAR);
            buttonDAR.setVisibility(View.GONE);
        }else{
            buttonDAR = (Button) findViewById(R.id.buttonDAR);
            buttonDAR.setOnClickListener(this);
        }

        Drawable dr = getResources().getDrawable(R.drawable.file);
        dr.setBounds(10, 0, 80, 60);
        buttonDAR.setCompoundDrawables(dr, null, null, null );

        dr = getResources().getDrawable(R.drawable.reports);
        dr.setBounds(10, 0, 80, 60);
        buttonReport.setCompoundDrawables(dr, null, null, null );

        dr = getResources().getDrawable(R.drawable.feedback);
        dr.setBounds(10, 0, 80, 60);
        buttonFeedback.setCompoundDrawables(dr, null, null, null );

        dr = getResources().getDrawable(R.drawable.message);
        dr.setBounds(10, 0, 80, 60);
        buttonMessage.setCompoundDrawables(dr, null, null, null );

        dr = getResources().getDrawable(R.drawable.support_desk);
        dr.setBounds(10, 0, 80, 60);
        buttonSupportDesk.setCompoundDrawables(dr, null, null, null );


        if(true) {
        } else {
            buttonDAR.setEnabled(false);
            buttonDAR.setBackgroundColor(Color.GRAY);
            buttonReport.setEnabled(false);
            buttonReport.setBackgroundColor(Color.GRAY);
        }

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Logged out Successfully", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity_new.this,LoginScreen.class));
                finish();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        if(buttonHide==1)
            menu.getItem(0).setVisible(false);//setEnabled(false); // here pass the index of save menu item
        else {
            SaveInSharedPref obj = new SaveInSharedPref(this);
            reportThere = obj.getReport();
        }
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings && reportThere == 1) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.AppCompatAlertDialogStyle);
            builder.setTitle("Alert");
            builder.setMessage("Are you sure, you want to checkout? This cannot be reversed.");
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Snackbar.make(fab, "Checkout done...", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
            });
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Snackbar.make(fab, "Cancelled...", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                }
            });
            builder.show();
            return true;
        }else {
            Snackbar.make(fab, "No report to file today.!", Snackbar.LENGTH_LONG).setAction("Action", null).show();
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onBackPressed() {
//        super.onBackPressed();


    }

    // Get the results:
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null) {
            if(result.getContents() == null) {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonDAR:
                startActivity(new Intent(MainActivity_new.this, DAR_Activity.class));
                break;
            case R.id.buttonReport:
                startActivity(new Intent(MainActivity_new.this, ReportsActivity.class));
                break;
            case R.id.buttonFeedback:
                startActivity(new Intent(MainActivity_new.this, FeedbackActivity.class));
                break;
            case R.id.buttonMessage:
                startActivity(new Intent(MainActivity_new.this, MessageActivity.class));
                break;
            case R.id.buttonSupportDesk:
                startActivity(new Intent(MainActivity_new.this, SupportDeskActivity.class));
                break;
        }
    }
}
