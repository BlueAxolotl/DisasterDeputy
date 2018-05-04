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
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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

    ArrayList<Charity> charitiesObjects;
    ArrayList<String> CharityNamesOnly;
    DatabaseReference myRef;
    FirebaseDatabase database;
    ArrayList<String> supplies;


   @Override
    protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);                             //make sure the controller is set to the correct thing
    setContentView(R.layout.activity_charity_searcher);
       final Controller aController = (Controller) getApplicationContext();
       FirebaseApp.initializeApp(this);
       database= FirebaseDatabase.getInstance();
       myRef= database.getReference("Charities");
       CharityList cl=new CharityList();
       cl=aController.getData();
       CharityNamesOnly=new ArrayList<>();

       charitiesObjects=cl.getCharityList();


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
               Intent myintent=new Intent(view.getContext(), charityInput.class);
               myintent.putExtra("position",i);
               startActivityForResult(myintent,0);


           }

       });





   }




    }