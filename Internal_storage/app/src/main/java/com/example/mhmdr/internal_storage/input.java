package com.example.mhmdr.internal_storage;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class input extends Activity {

    EditText input;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.input);
        input = (EditText) findViewById(R.id.input_field);
    }

    public void save_btn_act(View view) {
    String input_string = input.getText().toString();
        try {
            FileOutputStream fileOutputStream= openFileOutput("myfile.txt", MODE_PRIVATE);
            fileOutputStream.write(input_string.getBytes());
            fileOutputStream.close();
            Toast.makeText(this, "SAVED!", Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void next_btn_act(View view) {
        Intent intent=new Intent(this,output.class);
        startActivity(intent);
        finish();
    }

}
