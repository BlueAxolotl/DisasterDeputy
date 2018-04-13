package com.example.pranay.disasterdeputy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
// This class is for the charity searcher page... the charities who use the app will be able to search for their charity and add items
public class CharitySearcher extends AppCompatActivity {
    ArrayList<Charity> charitiesObject;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charity_searcher);

        charitiesObject = new ArrayList<Charity>();
        InputStream is = getResources().openRawResource(R.raw.originalcharitylist);

        BufferedReader reader = new BufferedReader(new InputStreamReader(is));

        String line = "";
        //This is creating the list of charities
        try {


            while ((line = reader.readLine()) != null) {

                String[] fields = line.split(",");
                ArrayList<String> supplies = new ArrayList<String>(); //currently this creates an empty list of supplies for each charity... once the charities enter the app they will be able to add
                Charity c = new Charity(fields[0], fields[1], supplies);
                charitiesObject.add(c);

            }
        }
        catch (IOException e) {
            Log.e("MainActivity", "Error reading data on line" + line);
        }

        //  Bundle extras = getIntent().getExtras();
//        ArrayList<Charity> Charities  =  (ArrayList<Charity>)getIntent().getSerializableExtra("CharityList");
        ArrayList<String> CharityNamesOnly= new ArrayList<String>();

        for(int i=0; i<charitiesObject.size(); i++){                //This goes through the list of charity objects and creates a list of only
            String CharityName = charitiesObject.get(i).getName();  //the names so that they can be displayed on the screen
            CharityNamesOnly.add(CharityName);
        }
        //This list adapter displays the list of charity names on the list view in the xml
        ListAdapter charityAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, CharityNamesOnly);
        ListView myListView = (ListView) findViewById(R.id.UserCharityList);
        myListView.setAdapter(charityAdapter);

    }
    }

