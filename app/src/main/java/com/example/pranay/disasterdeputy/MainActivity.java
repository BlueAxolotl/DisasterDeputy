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




//https://firebase.google.com/docs/ was very helpful with the implementation of firebase
//This class corresponds to the first activity that is activated when the app is run.
public class MainActivity extends AppCompatActivity {

    //The controller is populated in this method to store a list of charities throughout the app.
    Controller aController;
    CharityList charities;
    DatabaseReference myRef;

    //creating a new font for the welcome message
    TextView t;
    TextView c;
    Button b;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        charities = new CharityList();

        //UI
        //The code below is for the text fonts on the main activity screen.
        //font for title text
        t = (TextView) findViewById(R.id.WelcomeMessage);
        Typeface myCustomFont = Typeface.createFromAsset(getAssets(), "fonts/cocogoose.ttf");
        t.setTypeface(myCustomFont);

        //font for password disclaimer
        c = (TextView) findViewById(R.id.noPasswordDisclaimer);
        c.setTypeface(myCustomFont);

        //image icon
        ImageView myImg = (ImageView) findViewById(R.id.myImageView);
        myImg.setImageResource(R.drawable.tornado);

        //image position
        ImageView s = (ImageView) findViewById(R.id.myImageView);
        s.setX(200);
        s.setY(370);

        //change donor button font
        b = (Button) findViewById(R.id.DonorButton);
        Typeface buttonFont = Typeface.createFromAsset(getAssets(), "fonts/Quicksand_Book.otf");
        b.setTypeface(buttonFont);

        //change charity button font
        button = (Button) findViewById(R.id.CharityButton);
        button.setTypeface(buttonFont);

        FirebaseApp.initializeApp(this);
        aController = (Controller) getApplicationContext();
        charities=new CharityList();
        FirebaseDatabase database= FirebaseDatabase.getInstance();
        myRef= database.getReference("Charities");

        //This is a listener that will run a single time when the app is created so that the controller can be populated by the data that is in the database.
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                aController.getData().clearCharities();


                for(DataSnapshot charitysnapshot: dataSnapshot.getChildren()){

                    Charity c = charitysnapshot.getValue(Charity.class);            //This gets the single value in the database.

                    aController.getData().addCharity(c);             //The charity from the database is added to the controller.

                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


    //This method brings the user to the charity searcher class when the charity button is pressed.
    //The main activity restarts to repopulate the controller with the data from the database before the app goes to the next screen.
    public void CharityPush(View v){
        Intent intent0 = new Intent (this, MainActivity.class);
        startActivity(intent0);
        Intent intent = new Intent (this, CharitySearcher.class);
        startActivity(intent);
    }

    //This method switches to the Donor searcher class when the donor button is pressed.
    //The method first restarts the main activity so that the controller will be repopulated by the database.
    public void DonorPush(View v){

        Intent intent0 = new Intent (this, MainActivity.class);
        startActivity(intent0);
        Intent intent = new Intent (this, DonorSearcher.class);
        startActivity(intent);

    }

}