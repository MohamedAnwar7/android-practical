package com.example.practicaltest;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnSave,btnClear,btnNext;
    EditText name , pass , address;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnSave = (Button)findViewById(R.id.btnSave);
        btnClear = (Button) findViewById(R.id.btnClear);
        btnNext = (Button) findViewById(R.id.btnNext);

        //Input
        name = (EditText) findViewById(R.id.nameInput);
        pass = (EditText)findViewById(R.id.passInput);
        address = (EditText) findViewById(R.id.addressInput);

    }
    public void next(View view){

        Intent next = new Intent(this ,viewActivity.class);
        startActivity(next);

    }

    public  void clear(View view) {
        name.setText("");
        pass.setText("");
        address.setText("");
    }

    public void save(View v){
        String Name = name.getText().toString() ;
        String Pass = pass.getText().toString();
        String Address = address.getText().toString();
        if (Name.isEmpty() || Pass.isEmpty() || Address.isEmpty()) {
            Toast.makeText(this, "EditText Fields are Empty !", Toast.LENGTH_SHORT).show();
        } else {

            SharedPreferences sharedPreferences = getSharedPreferences("mydata",Context.MODE_PRIVATE);



            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("name", Name);
            editor.putString("pass", Pass);
            editor.putString("address",Address);
            editor.commit();
            Toast.makeText(this, "Data is stored Successfully .", Toast.LENGTH_SHORT).show();

        }
    }
}
//relativ
/*name pass,address
        save,clear ,intent 2*/

//3 lable //3 empty
/*load
exit */