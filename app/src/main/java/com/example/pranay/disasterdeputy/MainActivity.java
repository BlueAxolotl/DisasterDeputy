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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

   // ArrayList<Charity> charitiesObject;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //the code below is commented out because originally the list of charity objects was generated in this activity but it was moved
        //to the donor and charity activities because it was only being used for testing
        //in the future the arraylist will be all together and will be able to be used throughout the classes v
//       charitiesObject = new ArrayList<Charity>();
//        InputStream is = getResources().openRawResource(R.raw.originalcharitylist);
//
//        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
//
//        String line = "";
//
//        try {
//
//
//            while ((line = reader.readLine()) != null) {
//
//                String[] fields = line.split(",");
//                ArrayList<String> supplies = new ArrayList<String>();
//                Charity c = new Charity(fields[0], fields[1], supplies);
//                charitiesObject.add(c);
//
//            }
//        } catch (IOException e) {
//            Log.e("MainActivity", "Error reading data on line" + line);
//
//        }

    }

    //This method brings the user to the charity searcher class when the charity button is pressed
    public void CharityPush(View v){
        Intent intent = new Intent (this, CharitySearcher.class);

        //intent.putExtra("CharityList", charitiesObject);

        startActivity(intent);


    }

    //This method switches to the Donor searcher class when the donor button is pressed
    public void DonorPush(View v){
        Log.d("MainActivity","In donor push function");
        Intent intent = new Intent (this, DonorSearcher.class);

       // intent.putExtra("CharityList", charitiesObject);

        startActivity(intent);



    }

}
