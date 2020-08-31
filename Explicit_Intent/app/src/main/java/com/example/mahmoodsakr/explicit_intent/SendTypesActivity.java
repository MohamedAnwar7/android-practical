package com.example.mahmoodsakr.explicit_intent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SendTypesActivity extends AppCompatActivity implements View.OnClickListener {
    Button Asendonly, Asendandreceive;
    EditText Aname, Apassword, ANumber;
    TextView Atypeofnumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.send_layout);
        Atypeofnumber = (TextView) findViewById(R.id.Atypevalueid);
        Aname = (EditText) findViewById(R.id.Anamevalueid);
        Apassword = (EditText) findViewById(R.id.Apassvalueid);
        ANumber = (EditText) findViewById(R.id.ANumbervalueid);
        Asendonly = (Button) findViewById(R.id.ASendOnlyid);
        Asendandreceive = (Button) findViewById(R.id.Asendandreceiveid);
        Asendonly.setOnClickListener(this);
        Asendandreceive.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ASendOnlyid:
                Intent sendIntent = new Intent(this, RecieveOnlyActivity.class);
                sendIntent.putExtra("Aname", Aname.getText().toString());
                sendIntent.putExtra("Apassword", Apassword.getText().toString());
                startActivity(sendIntent);
                break;
            case R.id.Asendandreceiveid:
                Intent sendandreceiveIntent = new Intent(this, SendBackResultActivity.class);
                sendandreceiveIntent.putExtra("Anumber", ANumber.getText().toString());
                startActivityForResult(sendandreceiveIntent, 15);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 15) {
            if (resultCode == RESULT_OK) {
                Toast.makeText(this, "Automaic Received the Result from the destination Activity", Toast.LENGTH_LONG).show();
                Bundle bundle = data.getExtras();
                String numberType = bundle.getString("CnumberType");
                Atypeofnumber.setText(numberType);
            }
        }
    }
}