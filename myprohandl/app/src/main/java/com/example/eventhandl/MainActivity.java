package com.example.eventhandl;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button mButton = (Button) findViewById(R.id.mbutton);
        mButton.setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){
                        TextView mText = (TextView) findViewById(R.id.mText);
                        mText.setText("hey i can do this !!");
                    }
                }
        );
        mButton.setOnLongClickListener(
                new Button.OnLongClickListener(){
                    public boolean onLongClick(View v){
                        TextView mText = (TextView) findViewById(R.id.mText);
                        mText.setText("oh my god it take long time!");
                        return true;
                    }
                }
        );
    }
}
