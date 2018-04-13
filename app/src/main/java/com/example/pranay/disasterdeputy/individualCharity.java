package com.example.pranay.disasterdeputy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

//This class will be for the individual charity once the user clicks on it
//The class will display the location and supplies that the charity needs
public class individualCharity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_individual_charity);

        ArrayList<String> supplies = new ArrayList<String>();

        ListAdapter suppliesAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, supplies);
        ListView myListView = (ListView) findViewById(R.id.suppliesList);
        myListView.setAdapter(suppliesAdapter);

    }
}
