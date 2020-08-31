package com.example.mahmoodsakr.implicitintent;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.net.URI;

import static android.R.attr.button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Intent intent;
    Button button1, button2, button3, button4, button5, button6, button7, button8, button9, button10;
    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);
        button5 = (Button) findViewById(R.id.button5);
        button6 = (Button) findViewById(R.id.button6);
        button7 = (Button) findViewById(R.id.button7);
        button8 = (Button) findViewById(R.id.button8);
        button9 = (Button) findViewById(R.id.button9);
        button10 = (Button) findViewById(R.id.button10);

        img = (ImageView) findViewById(R.id.imgview);

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);
        button7.setOnClickListener(this);
        button8.setOnClickListener(this);
        button9.setOnClickListener(this);
        button10.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button1:
                intent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://www.google.com"));
                startActivity(intent);
                break;
            case R.id.button2:
                intent = new Intent(Intent.ACTION_DIAL,
                        Uri.parse("tel:(+2)012345678910"));
                startActivity(intent);
                break;
            case R.id.button3:
                intent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("geo:60.123,60.1434?z=19"));
                startActivity(intent);
                break;
            case R.id.button4:
                intent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("geo:0,0?q=Cairo"));
                startActivity(intent);
                break;
            case R.id.button5:
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse("content://contacts/people/"));
                startActivity(intent);
                break;
            case R.id.button6:
                intent = new Intent(Intent.ACTION_EDIT, Uri.parse("content://contacts/people/1"));
                startActivity(intent);
                break;
            case R.id.button7:
                try {
                    String[] TO = {"info.mahmoodsakr@gmail.com"};
                    String[] CC = {"mcmohd@gmail.com"};
                    intent = new Intent(Intent.ACTION_SEND);
                    intent.setData(Uri.parse("mailto:")); // open your email app in your phone
                    intent.setType("text/plain");
                    intent.putExtra(Intent.EXTRA_EMAIL, TO);
                    intent.putExtra(Intent.EXTRA_CC, CC);
                    intent.putExtra(Intent.EXTRA_SUBJECT, "Mess Subject");//subject
                    intent.putExtra(Intent.EXTRA_TEXT, "Mess Body");//Body
                    startActivity(intent);
                } catch (Exception e) {
                    Toast.makeText(this, "Error-no email app existed on your phone", Toast.LENGTH_LONG).show();
                }
                break;

            case R.id.button10:
                Uri uri = Uri.parse("smsto:012345678910");
                intent = new Intent(Intent.ACTION_SENDTO, uri);
                intent.putExtra("sms_body", "SMS TEXT");
                startActivity(intent);
                break;

            case R.id.button8:
                intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 100);
                Toast.makeText(this, "Camera Capturing", Toast.LENGTH_SHORT).show();
                break;
            case R.id.button9:
                intent = new Intent(Intent.ACTION_PICK,
                        MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                Toast.makeText(this, "Opening Gallary", Toast.LENGTH_SHORT).show();
                startActivityForResult(intent, 200);
                break;


        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == RESULT_OK) {
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            img.setImageBitmap(bitmap);

        }
        if (requestCode == 200 && resultCode == RESULT_OK) {
            Uri uri = data.getData();
            img.setImageURI(uri);
        }

    }
}

