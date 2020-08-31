package com.example.mahmoodsakr.explicit_intent;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class RecieveOnlyActivity extends AppCompatActivity implements View.OnClickListener {
    TextView Bname, Bpassword;
    Button Breceive, Bback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.receive_layout);
        Bname = (TextView) findViewById(R.id.Bnamevalueid);
        Bpassword = (TextView) findViewById(R.id.Bpassvalueid);
        Breceive = (Button) findViewById(R.id.BreciveOnlyid);
        Breceive.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(this, "Start Receiving Data ", Toast.LENGTH_SHORT).show();
        Intent receiveintent = getIntent();
        Bundle bundle = receiveintent.getExtras();
        String name = bundle.getString("Aname");
        String password = bundle.getString("Apassword");
        Bname.setText(name);
        Bpassword.setText(password);
        Toast.makeText(this, "Done Receiving Data ", Toast.LENGTH_SHORT).show();
    }

    public void backMethod(View v) {
        Toast.makeText(this, "Back to the Home Activity ", Toast.LENGTH_SHORT).show();
        Intent backIntent = new Intent(this, SendTypesActivity.class);
        startActivity(backIntent);
        finish();
    }
}
