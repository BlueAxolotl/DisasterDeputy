package com.example.pranay.disasterdeputy;

import android.graphics.Typeface;
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

        //this gets the data that was passed from the previous method
        Bundle bundle = getIntent().getExtras();
        position=bundle.getInt("position");

        //this gets the information from the controller that is specific to the charity that was chosen
        final Controller aController = (Controller) getApplicationContext();
        String charityName=aController.getData().getOneCharity(position).getName();
        String charityLocation=aController.getData().getOneCharity(position).getZipcode();
        ArrayList<String> charitySupplies=aController.getData().getOneCharity(position).getSupplies();


        TextView nametext = findViewById(R.id.charityName);
        nametext.setText(charityName);
        Typeface myCustomFont = Typeface.createFromAsset(getAssets(), "fonts/Quicksand_Book.otf");
        nametext.setTypeface(myCustomFont);
        nametext.setTypeface(null, Typeface.BOLD);

        TextView  locationText= findViewById(R.id.charityLocation);
        locationText.setText(charityLocation);
        locationText.setTypeface(myCustomFont);

        //this displays the list of supplies from the controller
        ListAdapter charityAdapter = new ArrayAdapter<String>(this, R.layout.mytextview, charitySupplies);
        ListView myListView = (ListView) findViewById(R.id.suppliesList);
         myListView.setAdapter(charityAdapter);
    }
}
