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
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class DonorSearcher extends AppCompatActivity {

    ArrayList<Charity> charitiesObject;

    @Override
    //Currently the onCreate method takes the entire charity list and makes a general list for only the names of the charities eventually there will be a method
    //that is used when th search is typed in so that a new charity list will be created based on the search parameter that the user inputs that
    //method will create the shortened charity list of objects and the shortened charity list of names
    //when the screen initially opens the list will display every charity then with the click of the button along with the entry the list will display
    //the charities that have the search parameter that the user entered
    //the new shortened array lists will be used to display information on the following page because those are the elements that will be clicked on
    //originally the list can be clicked on as well which will access the original list
    //for the shortened list the loop can go through the charities and get the list of items then check the list
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor_searcher);

        charitiesObject = new ArrayList<Charity>();
        InputStream is = getResources().openRawResource(R.raw.originalcharitylist);

        BufferedReader reader = new BufferedReader(new InputStreamReader(is));

        String line = "";

        try {


            while ((line = reader.readLine()) != null) {

                String[] fields = line.split(",");
                ArrayList<String> supplies = new ArrayList<String>();
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

        for(int i=0; i<charitiesObject.size(); i++){
            String CharityName = charitiesObject.get(i).getName();
            CharityNamesOnly.add(CharityName);
        }

        ListAdapter charityAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, CharityNamesOnly);
        ListView myListView = (ListView) findViewById(R.id.UserCharityList);
        myListView.setAdapter(charityAdapter);

        /*@Override
      public void onClick(View v) {
        charityAdapter.notifyDataSetChanged();
      }

        //when a charity is clicked from the listView, it will redirect to the charity's page
        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent myintent = new Intent(view.getContext(), individualCharity.class);
                startActivityForResult(myintent,0);
            }
        });*/
    }
}