package com.fog.suitcase;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class CharacteristicActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_characteristic);
        String[] characteristics = getIntent().getStringArrayExtra("characteristic");
        ((ListView) findViewById(R.id.lstv_showChar)).setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, characteristics));
    }
}
