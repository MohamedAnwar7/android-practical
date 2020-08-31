package com.example.mahmoodsakr.explicit_intent;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

//public class SendBackResultActivity extends AppCompatActivity {
public class SendBackResultActivity extends AppCompatActivity implements View.OnClickListener {
    TextView Cnumbertype;
    Button sendBackResult;
    String number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.set_result_back_layout);
        Cnumbertype = (TextView) findViewById(R.id.Cnumvalueid);
        sendBackResult = (Button) findViewById(R.id.Csendbackresultid);
        sendBackResult.setOnClickListener(this);
// Handle the incomming intent data

        Bundle bundle = getIntent().getExtras();
        number = bundle.getString("Anumber");
        Cnumbertype.setText(number);
        Toast.makeText(this, "Done Receiving Data", Toast.LENGTH_SHORT).show();
//        // Prepare the intent to send back the result
//
        Intent resultBackIntent = new Intent();
        if (Integer.parseInt(number) % 2 == 0) {
            resultBackIntent.putExtra("CnumberType", "Even Number");
        } else {
            resultBackIntent.putExtra("CnumberType", "Odd Number");
        }
        Toast.makeText(this, "Result is Send back", Toast.LENGTH_SHORT).show();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        setResult(RESULT_OK, resultBackIntent);

        finish();
    }

    @Override
    public void onClick(View v) {
//        Intent resultBackIntent = new Intent();
//        if (Integer.parseInt(number) % 2 == 0) {
//            resultBackIntent.putExtra("CnumberType", "Even Number");
//        } else {
//            resultBackIntent.putExtra("CnumberType", "Odd Number");
//        }
//        //edit reply
//        setResult(RESULT_OK, resultBackIntent);
//        finish();

    }
}
