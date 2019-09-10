package com.example.smsapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class Sample extends AppCompatActivity {
    String selectednumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);


        Intent intent = getIntent();
        String itemText = intent.getStringExtra("number");
        Toast.makeText(getApplicationContext(), itemText, Toast.LENGTH_SHORT).show();
//        textView.settext(itemText);

    }
}
