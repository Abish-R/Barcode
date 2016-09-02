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
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Accessory_Reporting extends AppCompatActivity {

    LinearLayout scroll_data_container, hiddenPanel;
    Spinner sp_category, sp_accessory;
    EditText volume;
    Button submit;
    String cat = "", acc = "", cat_id = "", acc_id = "";
    ArrayList<accessory_reporting_model> access_data;
    FloatingActionButton fab;
    ArrayList<String> arr_category, arr_category_id, arr_acce, arr_acce_id;
    ArrayAdapter<String> category_adap, accessory_adap;
    String currentDateTimeString;
    int mode_saving=0;
    int row_id_accessory_data=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accessory__reporting);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Accessory Reporting");

        arr_category = new ArrayList<>();
        arr_acce = new ArrayList<>();
        access_data = new ArrayList<>();
        arr_category_id = new ArrayList<>();
        arr_acce_id = new ArrayList<>();


        arr_category_id.add("1");
        arr_category_id.add("2");
        arr_category_id.add("3");
        arr_category.add("Category 1");
        arr_category.add("Category 2");
        arr_category.add("Category 3");

        arr_acce_id.add("1");
        arr_acce_id.add("2");
        arr_acce_id.add("3");
        arr_acce.add("accessory 1");
        arr_acce.add("accessory 2");
        arr_acce.add("accessory 3");

        submit = (Button) findViewById(R.id.submit);
        hiddenPanel = (LinearLayout) findViewById(R.id.hiddenlayout);
        scroll_data_container = (LinearLayout) findViewById(R.id.accessory_data);
         fab = (FloatingActionButton) findViewById(R.id.fab);
        sp_category = (Spinner) findViewById(R.id.category);
        sp_accessory = (Spinner) findViewById(R.id.accessory);
        volume = (EditText) findViewById(R.id.volume_unit);

        currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());

        category_adap = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, arr_category) {
            public View getView(int position, View convertView, android.view.ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView text = (TextView) view.findViewById(android.R.id.text1);
                text.setTextColor(Color.BLACK);

                return view;

            }
        };

        accessory_adap = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, arr_acce) {
            public View getView(int position, View convertView, android.view.ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView text = (TextView) view.findViewById(android.R.id.text1);
                text.setTextColor(Color.BLACK);

                return view;

            }
        };

        sp_category.setAdapter(category_adap);
        sp_accessory.setAdapter(accessory_adap);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                submit.setClickable(true);
                slideUpDown(hiddenPanel, 0, 0);
                sp_category.setAdapter(category_adap);
                sp_accessory.setAdapter(accessory_adap);


            }
        });
        //// adding rows
        addRow();
        //

        //Items of hidden layout
        sp_category.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                cat = arr_category.get(position);
                cat_id = arr_category_id.get(position);


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        sp_accessory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                acc = arr_acce.get(position);
                acc_id = arr_acce_id.get(position);
           }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (cat.equals("") && acc.equals("")) {
                    cat = arr_category.get(0);
                    acc = arr_acce.get(0);
                    cat_id = arr_category_id.get(0);
                    acc_id = arr_acce_id.get(0);
                }
                if (volume.getText().toString().equals("")) {
                    Snackbar.make(v, "Please enter volume", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                } else {

                    if(mode_saving==0)
                    {
                        access_data.add(new accessory_reporting_model(currentDateTimeString, acc_id, cat_id, acc, volume.getText().toString(), cat));
                    }
                    else
                    {

                        accessory_reporting_model as=access_data.get(row_id_accessory_data);
                        as.setAccessory_id(acc_id);
                        as.setAccessory(acc);
                        as.setCategory(cat);
                        as.setCategory_id(cat_id);
                        as.setVolume(volume.getText().toString());

                        access_data.set(row_id_accessory_data,as);
                    }
                    addRow();
                    volume.setText("");
                    submit.setClickable(false);
                    slideUpDown(hiddenPanel,0,0);
                }
           }
        });
///
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
                startActivity(new Intent(Accessory_Reporting.this,DAR_Activity.class));
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
            startActivity(new Intent(Accessory_Reporting.this,DAR_Activity.class));
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
            submit.setClickable(true);
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
        int cat_pos=0;
        int access_pos=0;
         for(int i=0;i<arr_category_id.size();i++)
         {
             if(arr_category_id.get(i).equals(access_data.get(row_id).getCategory_id()))
             {
                 cat_pos=i;
             }

         }
        for(int i=0;i<arr_category_id.size();i++)
        {
            if (arr_acce_id.get(i).equals(access_data.get(row_id).getAccessory_id()))
            {
                access_pos = i;
            }
        }
        sp_accessory.setSelection(access_pos);
        sp_category.setSelection(cat_pos);

        volume.setText(access_data.get(row_id).getVolume());
    }

    private void addRow() {
        scroll_data_container.removeAllViews();
        System.out.println("%%%%%%%%% size addrow :" + access_data.size());

        for (int i = 0; i < access_data.size(); i++) {
            final int element_position = i;
            final accessory_reporting_model toremove = access_data.get(i);
              View child = getLayoutInflater().inflate(R.layout.accessory_row_data, null);
             TextView tv_accessory = (TextView) child.findViewById(R.id.accessory);
             TextView tv_volume = (TextView) child.findViewById(R.id.volume);
            ImageView btn_edit = (ImageView) child.findViewById(R.id.edit);
            ImageView btn_del = (ImageView) child.findViewById(R.id.delete);
            tv_accessory.setText(access_data.get(element_position).getAccessory());
            tv_volume.setText(access_data.get(element_position).getVolume());


            btn_edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Snackbar.make(v, "Edit mode", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();


                    submit.setClickable(true);
                    slideUpDown(hiddenPanel,1,element_position);
                }
            });

            btn_del.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                   access_data.remove(toremove);
                   addRow();
                }
            });


            scroll_data_container.addView(child);
        }


    }

}