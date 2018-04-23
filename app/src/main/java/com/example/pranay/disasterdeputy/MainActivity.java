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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

//import public com.example.pranay.disasterdeputy.Controller.charities;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Object name;
        FirebaseOptions options;
        

        //the code below is commented out because originally the list of charity objects was generated in this activity but it was moved
        //to the donor and charity activities because it was only being used for testing
        //in the future the arraylist will be all together and will be able to be used throughout the classes v
        final Controller aController = (Controller) getApplicationContext();
        InputStream is = getResources().openRawResource(R.raw.originalcharitylist);

        BufferedReader reader = new BufferedReader(new InputStreamReader(is));

        String line = "";

        try {


            while ((line = reader.readLine()) != null) {           //this code adds to the controller which is the CharityList object
                                                                    //The controller can then be used throughout the app
                String[] fields = line.split(",");
                String charityName= fields[0];
                String charityAddress =fields[1];
                ArrayList<String> supplies=new ArrayList<>();
                aController.getData().addCharity(charityName,charityAddress,supplies);



            }
        } catch (IOException e) {
            Log.e("MainActivity", "Error reading data on line" + line);

        }

       CharityList cl=aController.getData();
        ArrayList<Charity> charitiesObjects= new ArrayList<Charity>();
        charitiesObjects=cl.getCharityList();
        for(int i=0; i<charitiesObjects.size(); i++) {
            Log.d("MainActivity", charitiesObjects.get(i).getName() + " " + charitiesObjects.get(i).getZipCode() + " ");
        }
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
