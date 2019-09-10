package com.example.smsapp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText txtphone, txtmessage;
    private Button btnsend;
    ImageView imgcontacts;
    final int SEND_SMS_PERMISSION_REQUEST_CODE = 1;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

        //
        txtphone = (EditText) findViewById(R.id.txtsearch);
        txtmessage = (EditText) findViewById(R.id.txtmessage);
        btnsend = (Button) findViewById(R.id.btnsend);
        imgcontacts = (ImageView) findViewById(R.id.imgcontacts);

        btnsend.setOnClickListener(this);
        imgcontacts.setOnClickListener(this);

        Intent intent = getIntent();
        String itemText = intent.getStringExtra("number");
        txtphone.setText(itemText);

        //
        btnsend.setEnabled(false);
        if(checkPermission(Manifest.permission.SEND_SMS)){
            btnsend.setEnabled(true);
        }else{
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.SEND_SMS}, SEND_SMS_PERMISSION_REQUEST_CODE);
        }

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.imgcontacts:
                Intent contacts = new Intent(MainActivity.this, AddContacts.class);
                startActivity(contacts);
                break;
            case R.id.btnsend:
                sendSMS();
                break;
        }
    }

    public void sendSMS(){
        String phone = txtphone.getText().toString();
        String message = txtmessage.getText().toString();

        if(phone==null || phone.length()==0 || message==null || message.length()==0){
            Toast.makeText(getApplicationContext(), "Please fill in all fields!", Toast.LENGTH_SHORT).show();
            return;
        }else{
        }

        if(checkPermission(Manifest.permission.SEND_SMS)){
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phone,null,message,null,null);
            Toast.makeText(getApplicationContext(), "Message sent.", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this, "Permission denied!", Toast.LENGTH_SHORT).show();
        }
    }

    public boolean checkPermission(String permission){
        int check = ContextCompat.checkSelfPermission(this,permission);
        return (check == PackageManager.PERMISSION_GRANTED);
    }

}
