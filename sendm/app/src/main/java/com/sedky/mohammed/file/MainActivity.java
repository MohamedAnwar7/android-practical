package com.sedky.mohammed.file;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {
private  static  final String file_name ="example.txt";

EditText medittext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

     medittext = (EditText) findViewById(R.id.edit_text);
    }


    public void save(View view) {
        String text =medittext.getText().toString();

        FileOutputStream fos =null;
        try {
            fos =openFileOutput(file_name,MODE_APPEND);
            fos.write(text.getBytes());

            medittext.getText().clear();
            Toast.makeText(this,"Saved to " + getFilesDir() + "/" +file_name,Toast.LENGTH_LONG).show();
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if(fos !=null){
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void lode(View view) {
        FileInputStream fis =null;
        try {
            fis = openFileInput(file_name);
            InputStreamReader isr =new InputStreamReader(fis);
            BufferedReader br =new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String text;

            while ((text=br.readLine()) != null){
                sb.append(text).append("\n");
            }
            medittext.setText(sb.toString());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if(fis != null){
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }


}
