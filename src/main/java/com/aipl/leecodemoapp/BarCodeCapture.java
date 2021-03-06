package com.aipl.leecodemoapp;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class BarCodeCapture extends AppCompatActivity implements View.OnClickListener {
    TextView barcode_field;
    Button submit,add_new;
    FloatingActionButton fab;
    String scannedValue="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_code_capture_parent);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Daily Sellout reporting");
//        getSupportActionBar().setTitle(Html.fromHtml("<font color='#FFFFFF'>Barcode Scanner</font>"));



        barcode_field=(TextView) findViewById(R.id.barcode_field);
        submit=(Button)findViewById(R.id.submit);
        add_new=(Button)findViewById(R.id.add_new);
        fab = (FloatingActionButton) findViewById(R.id.fab_add_new);

        submit.setOnClickListener(this);
        add_new.setOnClickListener(this);
        fab.setOnClickListener(this);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == android.R.id.home) {
            //  onBackPressed();

                startActivity(new Intent(this,MainActivityBarcode.class));
                finish();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();


            startActivity(new Intent(this,MainActivityBarcode.class));
            finish();

    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.add_new:
                new IntentIntegrator(BarCodeCapture.this).initiateScan();
                break;
            case R.id.submit:
                if(scannedValue.equals(""))
                    Toast.makeText(this, "Scan and try Again.!", Toast.LENGTH_LONG).show();
                else {
                    DatabaseHandler dbh = new DatabaseHandler(this);
                    dbh.addDataToDB(scannedValue);
                    scannedValue="";
                    Toast.makeText(this, "Data added successfully", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(this, MainActivityBarcode.class));
                    finish();
                }
                break;
            case R.id.fab:
                new IntentIntegrator(BarCodeCapture.this).initiateScan();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null) {
            if(result.getContents() == null) {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
            } else {
                scannedValue=result.getContents();
                barcode_field.setText(scannedValue);

                //Toast.makeText(this, "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
