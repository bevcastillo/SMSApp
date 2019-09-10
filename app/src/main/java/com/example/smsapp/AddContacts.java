package com.example.smsapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class AddContacts extends AppCompatActivity {

    ListView listView;
    private final static int REQUEST_CODE = 1;
    String selectednumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        final String[] numbers = getResources().getStringArray(R.array.numbers);
        listView = (ListView) findViewById(R.id.contactlist);

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, numbers);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectednumber = numbers[position];
                Intent intent = new Intent(AddContacts.this, MainActivity.class);
                intent.putExtra("number", selectednumber);
                startActivity(intent);
            }
        });
    }
}
