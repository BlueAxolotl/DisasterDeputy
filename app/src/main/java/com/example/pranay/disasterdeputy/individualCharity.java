package com.example.pranay.disasterdeputy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class individualCharity extends AppCompatActivity {
    int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_individual_charity);

        Bundle bundle = getIntent().getExtras();
        position=bundle.getInt("position");
        final Controller aController = (Controller) getApplicationContext();
        String charityName=aController.getData().getOneCharity(position).getName();
        String charityLocation=aController.getData().getOneCharity(position).getZipCode();
        ArrayList<String> charitySupplies=aController.getData().getOneCharity(position).getSupplies();


        TextView nametext = findViewById(R.id.charityName);
        nametext.setText("Charity Name: " + charityName);

        TextView  locationText= findViewById(R.id.charityLocation);
        locationText.setText("Charity Location: " + charityLocation );


        ListAdapter charityAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, charitySupplies);
        ListView myListView = (ListView) findViewById(R.id.suppliesList);
        myListView.setAdapter(charityAdapter);
    }
}
