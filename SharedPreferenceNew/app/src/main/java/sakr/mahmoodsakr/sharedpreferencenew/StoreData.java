package sakr.mahmoodsakr.sharedpreferencenew;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;

import static android.os.Environment.*;

public class StoreData extends AppCompatActivity {

    EditText nameEditText;
    EditText passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_data);
        nameEditText = (EditText) findViewById(R.id.nameEditText);
        passwordEditText = (EditText) findViewById(R.id.passwordEditText);
    }

    public void open_ActivityB(View view) {
        Intent intent = new Intent(StoreData.this, LoadData.class);
        startActivity(intent);
        finish();

    }


    public void storeData(View view) {
        String Name = nameEditText.getText().toString();
        String Password = passwordEditText.getText().toString();
        if (Name.isEmpty() || Password.isEmpty()) {
            Toast.makeText(this, "EditText Fields are Empty !", Toast.LENGTH_SHORT).show();
        } else {
            // if there is only one sharedPreferece file , use this line code
            SharedPreferences sharedPreferences = getPreferences(Context.MODE_PRIVATE);

//if there is several sharedPreferece files , use this line code
//        SharedPreferences sharedPreferences = getSharedPreferences("shareFilename", Context.MODE_PRIVATE);

            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("name", Name);
            editor.putString("pass", Password);
            editor.commit();
            Toast.makeText(this, "Data is stored Successfully .", Toast.LENGTH_SHORT).show();

        }
    }
}
