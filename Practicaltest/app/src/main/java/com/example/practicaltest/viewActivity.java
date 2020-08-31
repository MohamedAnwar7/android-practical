package com.example.practicaltest;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class viewActivity extends AppCompatActivity {
    final String Default = "Default Value";
    TextView viewname , viewpass,viewaddress;
    Button load , exit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        //view text
        viewname = (TextView)findViewById(R.id.viewname);
        viewpass =(TextView) findViewById(R.id.viewpass);
        viewaddress = (TextView)findViewById(R.id.viewaddress);
        //button

        load = (Button)findViewById(R.id.btnLoad);
        exit = (Button)findViewById(R.id.btnExit);

    }
    public void load(View v){

        SharedPreferences sharedPreferences = getSharedPreferences("mydata", Context.MODE_PRIVATE);

        String Name = sharedPreferences.getString("name", Default);
        String Password = sharedPreferences.getString("pass", Default);
        String Address = sharedPreferences.getString("address",Default);
        if (Name.equals(Default) || Password.equals(Default) ||Address.equals(Default)) {
            Toast.makeText(this, "Default values are loaded .", Toast.LENGTH_SHORT).show();
        } else {
            viewname.setText(Name);
            viewpass.setText(Password);
            viewaddress.setText(Address);
            Toast.makeText(this, "Data are loaded Successfully .", Toast.LENGTH_SHORT).show();
        }
    }
    public void Exit(View v){
        finish();
    }
}
