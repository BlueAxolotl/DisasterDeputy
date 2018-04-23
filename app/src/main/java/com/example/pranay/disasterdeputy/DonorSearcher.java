package com.example.pranay.disasterdeputy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.FirebaseDatabase;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class DonorSearcher extends AppCompatActivity {




    @Override
    //the new shortened array lists will be used to display information on the following page because those are the elements that will be clicked on
    //originally the list can be clicked on as well which will access the original list
    //for the shortened list the loop can go through the charities and get the list of items then check the list
    //if the user searches something we can make i go to a different activity than if not because it will use a different array list
    //Switch the list view on the screen and send it to a different activity that uses the list view that was in the new shortened list
    //this will be like an on search method
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor_searcher);
        final Controller aController = (Controller) getApplicationContext();
        FirebaseApp.initializeApp(this);

        CharityList cl=new CharityList(FirebaseDatabase.getInstance());
        cl=aController.getData();
        ArrayList<Charity> charitiesObjects= new ArrayList<Charity>();
        charitiesObjects=cl.getCharityList();
        ArrayList<String> CharityNamesOnly= new ArrayList<String>();

        for(int i=0; i<charitiesObjects.size(); i++){
            String CharityName = charitiesObjects.get(i).getName();
            CharityNamesOnly.add(CharityName);
        }
        //This displays the charity list of names only on the donor screen
        //Next we have to figure out how to implement the adapter listener so that when the item is clicked it will go to the next page
        final ListAdapter charityAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, CharityNamesOnly);
        ListView myListView = (ListView) findViewById(R.id.UserCharityList);
        myListView.setAdapter(charityAdapter);


        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l){
                Intent myintent=new Intent(view.getContext(), individualCharity.class);
                myintent.putExtra("position",i);
                startActivityForResult(myintent,0);


            }

        });





    }

}