package sakr.mahmoodsakr.sharedpreferencenew;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class LoadData extends AppCompatActivity {

    final String Default = "Default Value";
    TextView TextViewname, TextViewpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_data);
        TextViewname = (TextView) findViewById(R.id.nameTextView);
        TextViewpassword = (TextView) findViewById(R.id.passwordTextView);
    }

    public void open_ActivityA(View view) {
        Intent intent = new Intent(LoadData.this, StoreData.class);
        startActivity(intent);
        finish();

    }

    public void loadData(View view) {
        // if there is only one sharedPreferece file , use this line code
// SharedPreferences sharedPreferences =getPreferences(Context.MODE_PRIVATE); // if your are in the same storage activity
        // or use the name of the dataStoring activity as the file name
        SharedPreferences sharedPreferences = getSharedPreferences("StoreData", Context.MODE_PRIVATE);

//if there is several sharedPreferece files, use this line code to detect which file ?
//        SharedPreferences sharedPreferences = getSharedPreferences("shareFile", Context.MODE_PRIVATE);
        String Name = sharedPreferences.getString("name", Default);
        String Password = sharedPreferences.getString("pass", Default);
        if (Name.equals(Default) || Password.equals(Default)) {
            Toast.makeText(this, "Default values are loaded .", Toast.LENGTH_SHORT).show();
        } else {
            TextViewname.setText(Name);
            TextViewpassword.setText(Password);
            Toast.makeText(this, "Data are loaded Successfully .", Toast.LENGTH_SHORT).show();
        }
//data/data/packageid/sharedprefs/sharedPRefrencefile.xml
    }
}
