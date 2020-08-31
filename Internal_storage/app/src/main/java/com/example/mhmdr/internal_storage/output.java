package com.example.mhmdr.internal_storage;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class output extends Activity {
    EditText output ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.output);
    output = (EditText) findViewById(R.id.output_field);
    }

    public void show_btn_act(View view) {
        try {
            FileInputStream fileInputStream = openFileInput("myfile.txt");
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuffer stringBuffer = new StringBuffer();
            String lines ;
            while ((lines = bufferedReader.readLine()) != null){
                stringBuffer.append(lines+ "\n");
            }
            output.setText(stringBuffer.toString());
            Toast.makeText(this, "LOADED!", Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void back_btn_act(View view) {
        Intent intent = new Intent(this,input.class);
        startActivity(intent);
        finish();
    }


}
