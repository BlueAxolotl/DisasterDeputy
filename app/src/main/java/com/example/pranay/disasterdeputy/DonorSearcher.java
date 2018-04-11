package com.example.pranay.disasterdeputy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class DonorSearcher extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_donor_searcher);
        int charitytextfilelength = 10;

        ArrayList<String> charities = new ArrayList<String>();
        for(int i = 0; i < charitytextfilelength; i++){
            charities.add(charities.get(i));
        }
        ListAdapter myAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, charities);

        ListView myListView = (ListView) findViewById(R.id.myListView);
        myListView.setAdapter(myAdapter);
    }
}
