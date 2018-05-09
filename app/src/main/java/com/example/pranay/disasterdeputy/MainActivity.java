package com.example.pranay.disasterdeputy;


import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

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
//need to add a disclaimer that the app is not password protected
public class MainActivity extends AppCompatActivity {
    Controller aController;
    CharityList charities;
    DatabaseReference myRef;

    //creating a new font for the welcome message
    TextView t;
    Button b;
    Button butt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Object name;
        FirebaseOptions options;
        charities = new CharityList();

        //UI
        //font stuff
        t = (TextView) findViewById(R.id.WelcomeMessage);
        Typeface myCustomFont = Typeface.createFromAsset(getAssets(), "fonts/cocogoose.ttf");
        t.setTypeface(myCustomFont);

        //image icon
        ImageView myImg = (ImageView) findViewById(R.id.myImageView);
        myImg.setImageResource(R.drawable.tornado);

        //image position
        ImageView s = (ImageView) findViewById(R.id.myImageView);
        s.setY(120);
        s.setX(755);

        //change donor button font
        b = (Button) findViewById(R.id.DonorButton);
        Typeface buttonFont = Typeface.createFromAsset(getAssets(), "fonts/redona.ttf");
        b.setTypeface(buttonFont);

        //change charity button font
        butt = (Button) findViewById(R.id.CharityButton);
        butt.setTypeface(buttonFont);

        FirebaseApp.initializeApp(this);
        aController = (Controller) getApplicationContext();
        charities=new CharityList();
        FirebaseDatabase database= FirebaseDatabase.getInstance();
        myRef= database.getReference("Charities");

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                aController.getData().clearCharities();


                for(DataSnapshot charitysnapshot: dataSnapshot.getChildren()){

                    Charity c = charitysnapshot.getValue(Charity.class);

                    aController.getData().addCharity(c);

                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });







    }





    //This method brings the user to the charity searcher class when the charity button is pressed
    public void CharityPush(View v){
        Intent intent0 = new Intent (this, MainActivity.class);

        startActivity(intent0);


        Intent intent = new Intent (this, CharitySearcher.class);

        startActivity(intent);


    }

    //This method switches to the Donor searcher class when the donor button is pressed
    //The method first restarts the main activity so that the controller will be repopulated by the database
    public void DonorPush(View v){

        Intent intent0 = new Intent (this, MainActivity.class);

        startActivity(intent0);
        Intent intent = new Intent (this, DonorSearcher.class);
        startActivity(intent);



    }

}
