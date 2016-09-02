package com.aipl.leecodemoapp;

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
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class Accessory_Reporting extends AppCompatActivity {

    LinearLayout scroll_data_container, hiddenPanel;
    Spinner sp_category, sp_accessory;
    EditText volume;
    Button submit;
    String cat="",acc="";
    ArrayList<accessory_reporting_model> access_data;
    FloatingActionButton fab;
    ArrayList<String> arr_category, arr_acce;
    ArrayAdapter<String> category_adap,accessory_adap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accessory__reporting);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        arr_category = new ArrayList<>();
        arr_acce = new ArrayList<>();
        access_data=new ArrayList<>();

        arr_category.add("Category 1");
        arr_category.add("Category 2");
        arr_category.add("Category 3");

        arr_acce.add("accessory 1");
        arr_acce.add("accessory 2");
        arr_acce.add("accessory 3");

        submit = (Button) findViewById(R.id.submit);
        hiddenPanel = (LinearLayout) findViewById(R.id.hiddenlayout);

        sp_category= (Spinner) findViewById(R.id.category);
        sp_accessory= (Spinner) findViewById(R.id.accessory);
        volume= (EditText) findViewById(R.id.volume_unit);

        category_adap=new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_dropdown_item,arr_category){
            public View getView(int position, View convertView, android.view.ViewGroup parent)
            {
                View view = super.getView(position, convertView, parent);
                TextView text = (TextView) view.findViewById(android.R.id.text1);
                text.setTextColor(Color.BLACK);

                return view;

            }
        } ;;
        accessory_adap=new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_dropdown_item,arr_acce)
        {
            public View getView(int position, View convertView, android.view.ViewGroup parent)
            {
                View view = super.getView(position, convertView, parent);
                TextView text = (TextView) view.findViewById(android.R.id.text1);
                text.setTextColor(Color.BLACK);

                return view;

            }
        } ;;


        sp_category.setAdapter(category_adap);
        sp_accessory.setAdapter(accessory_adap);



        scroll_data_container = (LinearLayout) findViewById(R.id.accessory_data);

         fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    submit.setClickable(true);
                slideUpDown(hiddenPanel);
            }
        });


        sp_category.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                cat = arr_category.get(position);


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        sp_accessory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                acc   = arr_acce.get(position);


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(cat.equals("")&&acc.equals(""))
                {
                    cat=arr_category.get(0);
                    acc=arr_acce.get(0);
                }
                 if(volume.getText().toString().equals(""))
                {
                    Snackbar.make(v, "Please enter volume", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
                else
                {
                    volume.setText("");
                    submit.setClickable(false);
                    slideUpDown(hiddenPanel);
                }


            }
        });

    }


    public void slideUpDown(final View view) {
        if (!isPanelShown()) {
            // Show the panel
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

//    private void addRow() {
//        scroll_data_container.removeAllViews();
//
//
//        System.out.println("%%%%%%%%% size addrow :"+doctorcalltypewithBrands.size());
//
//        for (int i = 0; i < doctorcalltypewithBrands.size(); i++) {
//            final int element_position = i;
//            final OtherWithBrandModel toremove = Datafields.doctorcalltypewithBrands.get(i);
//
////			if(toremove.getTrans_name().equals("POST CALL SUMMARY"))
////			{
////				View child = act.getLayoutInflater().inflate(R.layout.postcall, null);
////
////			}
////			else
////			{
//            View child = getLayoutInflater().inflate(R.layout.accessory_row_data, null);
//
//            TextView textView_call_type = (TextView) child.findViewById(R.id.textView_call_type);
//
//            TextView textView_call_type = (TextView) child.findViewById(R.id.textView_call_type);
//
//
//            Spinner spinner_brand = (Spinner) child.findViewById(R.id.spinner_brand);
//            Spinner spinner_comp_brand = (Spinner) child.findViewById(R.id.spinner_comp_brand);
//
//            EditText et_rx_unit=(EditText) child.findViewById(R.id.rx_unit);
//            ImageButton button_comment = (ImageButton) child.findViewById(R.id.button_comment);
//            ImageButton button_delete = (ImageButton) child.findViewById(R.id.button_delete);
//
//            InputFilter[] filters = new InputFilter[1];
//            filters[0] = new InputFilter.LengthFilter(4); //Filter to 4 characters
//            et_rx_unit .setFilters(filters);
//
//            textView_call_type.setText(toremove.getTrans_name());
//
//            if(toremove.getTrans_name().equals("REMARKS"))
//            {
//                spinner_brand.setVisibility(View.INVISIBLE);
//                spinner_comp_brand.setVisibility(View.INVISIBLE);
//                et_rx_unit.setVisibility(View.INVISIBLE);
//                toremove.setAllbrands_name("NODATA");
//                toremove.setBrand_id("NODATA");
//                toremove.setRx_unit("NODATA");
//                toremove.setCompetitor_rxunit("NODATA");
//                toremove.setCompetitorbrand("NODATA");
//                toremove.setCompetitorbrand_id("NODATA");
//                Datafields.doctorcalltypewithBrands.set(element_position, toremove);
//
//                //et_rx_unit.setVisibility(View.INVISIBLE);
//            }
//            if(!toremove.getTrans_name().equals("Rx-AUDIT")&&!toremove.getTrans_name().equals("COMPETITOR INFO"))
//            {
//                System.out.println("inside rx comp");
//                et_rx_unit.setVisibility(View.INVISIBLE);
//                spinner_comp_brand.setVisibility(View.INVISIBLE);
//                toremove.setRx_unit("NODATA");
//
//                toremove.setCompetitor_rxunit("NODATA");
//                toremove.setCompetitorbrand("NODATA");
//                toremove.setCompetitorbrand_id("NODATA");
//
//                Datafields.doctorcalltypewithBrands.set(element_position, toremove);
//
//
//            }
//            if(toremove.getTrans_name().equals("COMPETITOR INFO"))
//            {
//                spinner_comp_brand.setVisibility(View.VISIBLE);
//                toremove.setRx_unit("NODATA");
//                Datafields.doctorcalltypewithBrands.set(element_position, toremove);
//            }
//
//
//
//            ///// cangsessdsjnfddhgf
//
//
//
//
//
//            }
//
//            if(toremove.getTrans_name().equals("COMPETITOR INFO"))
//            {
//
//                String saved_comp_rxunit=toremove.getCompetitor_rxunit();
//                System.out.println("saved_comp_rxunit"+saved_comp_rxunit);
//                if(saved_comp_rxunit.length()>0) {
//                    System.out.println("saved_rx_unit : "+saved_comp_rxunit);
//
//                    if(saved_comp_rxunit.equals("NODATA"))
//                        et_rx_unit.setText("");
//                    else
//                        et_rx_unit.setText(saved_comp_rxunit);
//
//                }
//                else
//                    et_rx_unit.setText("");
//
//                et_rx_unit.addTextChangedListener(new TextWatcher() {
//
//                    public void afterTextChanged(Editable s) {
//                        String comp_rx_unit=s.toString();
//                        System.out.println("comp_rx_unit: "+comp_rx_unit);
//
//                        if(comp_rx_unit.contains("'")) {
//                            comp_rx_unit = comp_rx_unit.replace("'", "`");
//                            System.out.println("comp_rx_unit: "+comp_rx_unit);
//                        }
//
//                        if(comp_rx_unit.equals("")){
//                            comp_rx_unit = "NODATA";
//                        }
//
//                        toremove.setCompetitor_rxunit(comp_rx_unit);
//
//
//                        Datafields.doctorcalltypewithBrands.set(element_position, toremove);
//                        System.out.println("	Datafields.doctorcalltypewithBrands--->>"+	Datafields.doctorcalltypewithBrands.get(element_position).getCompetitor_rxunit());
//                    }
//
//                    public void beforeTextChanged(CharSequence s, int start,
//                                                  int count, int after) {
//                    }
//
//                    public void onTextChanged(CharSequence s, int start,
//                                              int before, int count) {
//
//                    }
//                });
//
//            }
//
//            ////
//
//
//
//            ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, allbrandsName);
//            spinner_brand.setAdapter(spinnerArrayAdapter);
//
//            ArrayAdapter<String> spinnercompArrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, comp_brand);
//            spinner_comp_brand.setAdapter(spinnercompArrayAdapter);
//
//            int spinner_pos = 0;
//            int comp_spinner_pos=0;
//            for (int j = 0; j < allbrandsName.size(); j++) {
//
//                if(!toremove.getTrans_name().equals("REMARKS"))
//                {
//                    if(toremove.getAllbrands_name().equals(allbrandsName.get(j))){
//                        spinner_pos = j;
//                        break;
//                    }
//                }
//            }
//
//            for (int k = 0; k < comp_brand.size(); k++) {
//
//                if(toremove.getTrans_name().equals("COMPETITOR INFO"))
//                {
//
//                    if(toremove.getCompetitorbrand().equals(comp_brand.get(k))){
//                        comp_spinner_pos = k;
//                        break;
//                    }
//
//                }
//
//            }
//
//            spinner_comp_brand.setSelection(comp_spinner_pos);
//
//            spinner_brand.setSelection(spinner_pos);
//
//            final Toast toast = Toast.makeText(getActivity(), "Brand name already selected with the call type", Toast.LENGTH_SHORT);
//            toast.show();
//            toast.cancel();
//
//            spinner_brand.setOnItemSelectedListener(new OnItemSelectedListener() {
//
//                @Override
//                public void onItemSelected(AdapterView<?> parent, View view,
//                                           int position_, long id) {
//
//					/*boolean previously_selected = false;
//
//					for (int j = 0; j < Datafields.doctorcalltypewithBrands.size(); j++) {
//
//						System.out.println(Datafields.doctorcalltypewithBrands.get(j).getAllbrands_name()+" brands_name "+toremove.getAllbrands_name());
//						System.out.println(Datafields.doctorcalltypewithBrands.get(j).getTrans_name()+" Trans_name "+toremove.getTrans_name());
//						System.out.println(j+" position "+element_position);
//
//
//						if(Datafields.doctorcalltypewithBrands.get(j).getAllbrands_name().equals(toremove.getAllbrands_name())
//								&& Datafields.doctorcalltypewithBrands.get(j).getTrans_name().equals(toremove.getTrans_name())){
//							if(j != element_position){
//								previously_selected = true;
////								if(toast.getView().isShown()){
////									toast.cancel();
////								}
////								toast.show();
//								break;
//							}
//						}
//					}*/
//
////					if(previously_selected == false) {
//
//                    if(toremove.getTrans_name().equals("REMARKS"))
//                    {
//                        toremove.setBrand_id("NODATA");
//                        toremove.setAllbrands_name("NODATA");
//                        Datafields.doctorcalltypewithBrands.set(element_position, toremove);
//                    }
//
//                    else
//                    {
//                        toremove.setBrand_id(allbrandsId.get(position_));
//                        toremove.setAllbrands_name(allbrandsName.get(position_));
//                        Datafields.doctorcalltypewithBrands.set(element_position, toremove);
//                    }
////					}
//                }
//
//                @Override
//                public void onNothingSelected(AdapterView<?> parent) {
//                    // TODO Auto-generated method stub
//                }
//            });
//
//
//            spinner_comp_brand.setOnItemSelectedListener(new OnItemSelectedListener() {
//
//                @Override
//                public void onItemSelected(AdapterView<?> arg0, View arg1,
//                                           int position_, long arg3) {
//                    // TODO Auto-generated method stub
//
//                    if(toremove.getTrans_name().equals("COMPETITOR INFO"))
//                    {
//                        toremove.setCompetitorbrand_id(comp_brand_id.get(position_));
//                        toremove.setCompetitorbrand(comp_brand.get(position_));
//                        Datafields.doctorcalltypewithBrands.set(element_position, toremove);
//                    }
//                    else
//                    {
//                        toremove.setCompetitorbrand_id("NODATA");
//                        toremove.setCompetitorbrand("NODATA");
//                        Datafields.doctorcalltypewithBrands.set(element_position, toremove);
//                    }
//
//                }
//
//                @Override
//                public void onNothingSelected(AdapterView<?> arg0) {
//                    // TODO Auto-generated method stub
//
//                }
//            });
//
//
//
//
//            button_comment.setOnClickListener(new OnClickListener() {
//
//                @Override
//                public void onClick(View arg0) {
//                    // TODO Auto-generated method stub
//                    System.out.println("data in Datafields.doccall_comment:"+toremove.getComment());
//                    Button ok,cancel;
//                    final Dialog dialog=new Dialog(getActivity());
//
//                    final TextView textv;
//                    dialog.setTitle("Enter comments");
//                    dialog.setCancelable(false);
//                    dialog.setContentView(R.layout.dialoge);
//                    dialog.setCancelable(false);
//
//                    textv=(TextView)dialog.findViewById(R.id.textview);
//                    //textv.setInputType(InputType.TYPE_CLASS_NUMBER);
//
//                    String cmmnt = toremove.getComment();
//                    if(cmmnt.length() > 0) {
//                        System.out.println("cmmnt : "+cmmnt);
//
//                        if(cmmnt.contains("`")) {
//                            cmmnt = cmmnt.replace("`", "'");
//                            System.out.println("cmmnt rep: "+cmmnt);
//                        }
//
//                        if(cmmnt.equals("NODATA"))
//                            textv.setText("");
//                        else
//                            textv.setText(cmmnt);
//
//                    }
//                    else
//                        textv.setText("");
//
//                    ok=(Button)dialog.findViewById(R.id.ok);
//                    cancel=(Button)dialog.findViewById(R.id.cancel);
//
//                    ok.setOnClickListener(new OnClickListener() {
//
//                        @Override
//                        public void onClick(View v) {
//                            // TODO Auto-generated method stub
//                            String commen=textv.getText().toString();
//                            System.out.println("commen: "+commen);
//
//                            if(commen.contains("'")) {
//                                commen = commen.replace("'", "`");
//                                System.out.println("comm: "+commen);
//                            }
//
//                            if(commen.equals("")){
//                                commen = "NODATA";
//                            }
//
//                            toremove.setComment(commen);
//                            Datafields.doctorcalltypewithBrands.set(element_position, toremove);
//
//                            dialog.dismiss();
//                        }
//
//                    });
//
//                    cancel.setOnClickListener(new OnClickListener() {
//
//                        @Override
//                        public void onClick(View v) {
//                            // TODO Auto-generated method stub
//                            dialog.dismiss();
//                        }
//                    });
//
//                    dialog.show();
//                }
//
//            });
//
//            button_delete.setOnClickListener(new OnClickListener() {
//
//                @Override
//                public void onClick(View v) {
//                    // TODO Auto-generated method stub
//                    Datafields.doctorcalltypewithBrands.remove(toremove);
//                    addRow();
//                }
//            });
//
//
//            scroll_data_container.addView(child);
//        }


}
