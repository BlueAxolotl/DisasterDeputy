package com.example.pranay.disasterdeputy;

import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

//This class will be for each individual charity to enter the supplies that they have
//the supplies will be added to the corresponding array list in their charity object
//if the user searches something we can make i go to a different activity than if not because it will use a different array list
//Switch the list view on the screen and send it to a different activity that uses the list view that was in the new shortened list
//this will be like an on search method
public class charityInput extends AppCompatActivity {
    int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charity_input);
        Bundle bundle = getIntent().getExtras();
       position=bundle.getInt("position");
        final Controller aController = (Controller) getApplicationContext();
        String charityName=aController.getData().getOneCharity(position).getName();
        String charityLocation=aController.getData().getOneCharity(position).getZipCode();
        ArrayList<String> charitySupplies=aController.getData().getOneCharity(position).getSupplies();


        TextView nametext = findViewById(R.id.individualCharityName);
        nametext.setText("Charity Name: " + charityName);

        TextView  locationText= findViewById(R.id.individualcharityLocation);
        locationText.setText("Charity Location: " + charityLocation );


        ListAdapter charityAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, charitySupplies);
        ListView myListView = (ListView) findViewById(R.id.SupplyList);
        myListView.setAdapter(charityAdapter);



    }

    public void supplyInput(View v){
        TextInputLayout supplyentry = findViewById(R.id.supplyinput);
        String Supply= supplyentry.getEditText().getText().toString();
        final Controller aController = (Controller) getApplicationContext();
        aController.getData().getOneCharity(position).addSupplies(Supply);
        ArrayList<String> charitySupplies=aController.getData().getOneCharity(position).getSupplies();
        ListAdapter charityAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, charitySupplies);
        ListView myListView = (ListView) findViewById(R.id.SupplyList);
        myListView.setAdapter(charityAdapter);
    }

    public void supplyClear(View v){
        final Controller aController = (Controller) getApplicationContext();
        aController.getData().getOneCharity(position).clearSupplies();
        ArrayList<String> charitySupplies=aController.getData().getOneCharity(position).getSupplies();
        ListAdapter charityAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, charitySupplies);
        ListView myListView = (ListView) findViewById(R.id.SupplyList);
        myListView.setAdapter(charityAdapter);
    }
}
