package com.aipl.leecodemoapp;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class LoginScreen extends AppCompatActivity implements View.OnClickListener {

    EditText et_userame,et_password;
    Button btn_login,btn_exit;
    CheckBox remeber_pass;
    String username="user",password="user",u_name="",pass="";
    public static final String MyPREFERENCES = "MyPrefs";
    SharedPreferences mySharedPreferences ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_login_screen);

        btn_login= (Button) findViewById(R.id.login);
        btn_exit= (Button) findViewById(R.id.exit);

        et_userame= (EditText) findViewById(R.id.input_username);
        et_password=(EditText) findViewById(R.id.input_password);

        remeber_pass= (CheckBox) findViewById(R.id.remember_pass);

        btn_exit.setOnClickListener(this);
        btn_login.setOnClickListener(this);
        mySharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);


        u_name = mySharedPreferences.getString("username", "").toString();
        pass = mySharedPreferences.getString("password", "").toString();

        if(u_name.equals("user")&&pass.equals("user")) {
            startActivity(new Intent(LoginScreen.this, MainActivity_new.class));
            //finish();

        }





//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);



    }


    public void createtables()
    {
        try{
            SqlDataStore sd = new SqlDataStore(this);
            sd.open();

            //sd.createtable(Queryclass.TABLE_RETAILERS_DATA, Queryclass.CREATE_RETAILERSDATA);

            sd.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            System.out.println("--------TABLES CREATED-----------");
        }
    }

    @Override
    public void onClick(View v) {

       if(v==btn_login) {


           if(et_userame.getText().toString().equals(username)&&et_password.getText().toString().equals(password))
           {
               Toast.makeText(getApplicationContext(),"Login Successfull",Toast.LENGTH_SHORT).show();

               if(remeber_pass.isChecked())
               {
                   SharedPreferences.Editor editor = mySharedPreferences.edit();

                   editor.putString("username", et_userame.getText().toString());
                   editor.putString("password",et_password.getText().toString());

                   editor.commit();

               }



               startActivity(new Intent(LoginScreen.this, MainActivity_new.class));



           }
           else
           {
               Snackbar.make(v, "Login Failed", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

           }

       }
        else if(v==btn_exit)
       {
           this.finish();
           System.exit(0);
       }


    }
}
