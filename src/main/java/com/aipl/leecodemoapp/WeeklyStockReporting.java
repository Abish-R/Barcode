package com.aipl.leecodemoapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

public class WeeklyStockReporting extends AppCompatActivity {

    LinearLayout scroll_data_container, hiddenPanel;
    Spinner sp_model;
    EditText volume;
    Button add;

    String currentDateTimeString,model = "",model_id = "";
    ArrayList<String> arr_model,arr_model_id;
    ArrayAdapter<String> model_adap;
    ArrayList<weekly_stock_reporting_model> wsr_data;
    FloatingActionButton fab;
    int mode_saving=0;
    int row_id_accessory_data=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weekly_stock_reporting);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        arr_model = new ArrayList<>();
        arr_model_id = new ArrayList<>();
        wsr_data=new ArrayList<>();
        arr_model_id.add("1");
        arr_model_id.add("2");
        arr_model_id.add("3");

        arr_model.add("Model 1");
        arr_model.add("Model 2");
        arr_model.add("Model 3");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Weekly Stock Reporting");
        currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
         fab = (FloatingActionButton) findViewById(R.id.fab);

        add = (Button) findViewById(R.id.add_wsr);
        hiddenPanel = (LinearLayout) findViewById(R.id.hiddenlayout_wsr);
        sp_model = (Spinner) findViewById(R.id.model_no);
        scroll_data_container = (LinearLayout) findViewById(R.id.wsr_data);
        volume = (EditText) findViewById(R.id.volume_wsr);



        model_adap = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, arr_model) {
            public View getView(int position, View convertView, android.view.ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView text = (TextView) view.findViewById(android.R.id.text1);
                text.setTextColor(Color.BLACK);

                return view;

            }
        };

        sp_model.setAdapter(model_adap);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                add.setClickable(true);
                slideUpDown(hiddenPanel, 0, 0);
                sp_model.setAdapter(model_adap);
            }
        });

        //// adding rows
        addRow();
        //

        //Items of hidden layout

        sp_model.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                model = arr_model.get(position);
                model_id = arr_model_id.get(position);


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (model.equals("")) {
                    model = arr_model.get(0);

                    model_id = arr_model_id.get(0);

                }
                if (volume.getText().toString().equals("")) {
                    Snackbar.make(v, "Please enter volume", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                } else {

                    if (mode_saving == 0) {
                        wsr_data.add(new weekly_stock_reporting_model(currentDateTimeString, model, model_id, volume.getText().toString()));


                    } else {

                        weekly_stock_reporting_model wsr = wsr_data.get(row_id_accessory_data);

                        wsr.setModel_no(model);
                        wsr.setModel_no_id(model_id);
                        wsr.setVolume(volume.getText().toString());

                        wsr_data.set(row_id_accessory_data, wsr);

                    }
                    addRow();
                    volume.setText("");
                    add.setClickable(false);
                    slideUpDown(hiddenPanel, 0, 0);
                }
             }
        });
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
            if(isPanelShown())
            {
                slideUpDown(hiddenPanel,0,0);
            }
            else
            {
                startActivity(new Intent(WeeklyStockReporting.this,DAR_Activity.class));
                finish();
            }
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();

        if(isPanelShown())
        {
            slideUpDown(hiddenPanel,0,0);
        }
        else
        {
            startActivity(new Intent(WeeklyStockReporting.this,DAR_Activity.class));
            finish();
        }
    }
    public void slideUpDown(final View view,int mode,int row_id) {
        if (!isPanelShown()) {
            // Show the panel

            if(mode==1)
            {
                loadSavedData(row_id);

                row_id_accessory_data=row_id;
                mode_saving=1;
            }
            else
            {
                row_id_accessory_data=0;
                mode_saving=0;
            }
            Animation bottomUp = AnimationUtils.loadAnimation(this,
                    R.anim.bottom_up);
            add.setClickable(true);
            hiddenPanel.startAnimation(bottomUp);
            hiddenPanel.setVisibility(View.VISIBLE);
            fab.setVisibility(view.INVISIBLE);

        } else {
            // Hide the Panel
            fab.setClickable(true);
            Animation bottomDown = AnimationUtils.loadAnimation(this,
                    R.anim.bottom_down);

            hiddenPanel.startAnimation(bottomDown);
            hiddenPanel.setVisibility(View.GONE);
            fab.setVisibility(view.VISIBLE);
         }
    }
    private boolean isPanelShown() {
        return hiddenPanel.getVisibility() == View.VISIBLE;
    }
    private void loadSavedData(int row_id)
    {
        int model_pos=0;

        for(int i=0;i<arr_model_id.size();i++)
        {
            if(arr_model_id.get(i).equals(wsr_data.get(row_id).getModel_no_id()))
            {
                model_pos=i;
            }
        }
        sp_model.setSelection(model_pos);
        volume.setText(wsr_data.get(row_id).getVolume());

    }

    private void addRow() {
        scroll_data_container.removeAllViews();


        System.out.println("%%%%%%%%% size addrow :" + wsr_data.size());

        for (int i = 0; i < wsr_data.size(); i++) {


            final int element_position = i;
            final weekly_stock_reporting_model toremove = wsr_data.get(i);
            View child = getLayoutInflater().inflate(R.layout.weekly_stock_row_data, null);
            TextView tv_model_no = (TextView) child.findViewById(R.id.model_no);
            TextView tv_volume = (TextView) child.findViewById(R.id.volume);
            ImageView btn_edit = (ImageView) child.findViewById(R.id.edit);
            ImageView btn_del = (ImageView) child.findViewById(R.id.delete);

            tv_model_no.setText(wsr_data.get(element_position).getModel_no());
            tv_volume.setText(wsr_data.get(element_position).getVolume());


            btn_edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Snackbar.make(v, "Edit mode", Snackbar.LENGTH_LONG)
//                            .setAction("Action", null).show();
                    add.setClickable(true);
                    slideUpDown(hiddenPanel,1,element_position);
                }
            });

     btn_del.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    wsr_data.remove(toremove);
                    addRow();
                }
            });


            scroll_data_container.addView(child);
        }


    }

}
