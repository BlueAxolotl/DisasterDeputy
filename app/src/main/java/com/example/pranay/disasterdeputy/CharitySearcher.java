package com.example.pranay.disasterdeputy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.FirebaseDatabase;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
// This class is for the charity searcher page... the charities who use the app will be able to search for their charity and add items
//if the user searches something we can make i go to a different activity than if not because it will use a different array list
//Switch the list view on the screen and send it to a different activity that uses the list view that was in the new shortened list
//this will be like an on search method
public class CharitySearcher extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charity_searcher);
        final Controller aController = (Controller) getApplicationContext();

        FirebaseApp.initializeApp(this);

        CharityList cl=new CharityList(FirebaseDatabase.getInstance());
        cl=aController.getData();
        ArrayList<Charity> charitiesObjects= new ArrayList<Charity>();
        charitiesObjects=cl.getCharityList();

        ArrayList<String> CharityNamesOnly= new ArrayList<String>();


        for(int i=0; i<charitiesObjects.size(); i++){                //This goes through the list of charity objects and creates a list of only
            String CharityName = charitiesObjects.get(i).getName();  //the names so that they can be displayed on the screen
            CharityNamesOnly.add(CharityName);
        }

        for(int k=0; k<CharityNamesOnly.size(); k++){
            Log.d("CharitySearcher", CharityNamesOnly.get(k));

        }
        //This list adapter displays the list of charity names on the list view in the xml
        ListAdapter charityAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, CharityNamesOnly);
        ListView myListView = (ListView) findViewById(R.id.UserCharityList);
        myListView.setAdapter(charityAdapter);
        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l){
                Intent myintent=new Intent(view.getContext(), charityInput.class);
                myintent.putExtra("position",i);
                startActivityForResult(myintent,0);


            }

        });

    }
    }

