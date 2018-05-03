package com.example.pranay.disasterdeputy;


import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

//import public com.example.pranay.disasterdeputy.Controller.charities;

public class MainActivity extends AppCompatActivity {

    CharityList charities;
    DatabaseReference myRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Object name;
        FirebaseOptions options;
        charities =new CharityList();

        FirebaseApp.initializeApp(this);
        final Controller aController = (Controller) getApplicationContext();
        charities=new CharityList();
        FirebaseDatabase database= FirebaseDatabase.getInstance();
        myRef= database.getReference("Charities");


       myRef.addListenerForSingleValueEvent(new ValueEventListener() {
           @Override
           public void onDataChange(DataSnapshot dataSnapshot) {
                aController.getData().clearCharities();
               charities.clearCharities();
               Log.d("MainActivity", "In the on data change.");
               for(DataSnapshot charitysnapshot: dataSnapshot.getChildren()){
                   Log.d("MainActivity", "In loop");
                   Charity c = charitysnapshot.getValue(Charity.class);
                   charities.addCharity(c);
                   aController.getData().addCharity(c);



               }

              ArrayList<Charity> charitiesObjects= new ArrayList<Charity>();
               charitiesObjects=charities.getCharityList();
              for(int i=0; i<charitiesObjects.size(); i++) {
                   Log.d("MainActivity", charitiesObjects.get(i).getName());
               }

           }



           @Override
           public void onCancelled(DatabaseError databaseError) {

           }
       });


   }





    //This method brings the user to the charity searcher class when the charity button is pressed
    public void CharityPush(View v){
        Intent intent = new Intent (this, CharitySearcher.class);

        startActivity(intent);


    }

    //This method switches to the Donor searcher class when the donor button is pressed
    public void DonorPush(View v){
        Log.d("MainActivity","In donor push function");
        Intent intent = new Intent (this, DonorSearcher.class);
        startActivity(intent);



    }

}
