package com.example.pranay.disasterdeputy;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
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

public class DonorSearcher extends AppCompatActivity {
    ArrayList<Charity> charitiesObjects;
    ArrayList<String> CharityNamesOnly;
    DatabaseReference myRef;
    FirebaseDatabase database;
    ArrayList<String> supplies;
    int count;
    ListAdapter charityAdapter;
    Controller aController;



    @Override
    //the new shortened array lists will be used to display information on the following page because those are the elements that will be clicked on
    //originally the list can be clicked on as well which will access the original list
    //for the shortened list the loop can go through the charities and get the list of items then check the list
    //if the user searches something we can make i go to a different activity than if not because it will use a different array list
    //Switch the list view on the screen and send it to a different activity that uses the list view that was in the new shortened list
    //this will be like an on search method
    protected void onCreate(Bundle savedInstanceState) {

        database= FirebaseDatabase.getInstance();
        myRef= database.getReference("Charities");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor_searcher);
        aController = (Controller) getApplicationContext();
        FirebaseApp.initializeApp(this);

        CharityList cl=new CharityList();
        cl=aController.getData();
       charitiesObjects= new ArrayList<Charity>();
        charitiesObjects=cl.getCharityList();
        for(int i=0; i<charitiesObjects.size(); i++){
            String CharityName = charitiesObjects.get(i).getName();
            Log.d("DonorSearcher",CharityName);
        }
       CharityNamesOnly= new ArrayList<String>();


        for(int i=0; i<charitiesObjects.size(); i++){
            String CharityName = charitiesObjects.get(i).getName();
            CharityNamesOnly.add(CharityName);
            Log.d("DonorSearcher",CharityName);
        }
        //This displays the charity list of names only on the donor screen
        //Next we have to figure out how to implement the adapter listener so that when the item is clicked it will go to the next page
         charityAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, CharityNamesOnly);
        ListView myListView = (ListView) findViewById(R.id.UserCharityList);
        myListView.setAdapter(charityAdapter);




        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l){                //I think the controller will have to be updated in this method to match the most recent list of charities
                Intent myintent=new Intent(view.getContext(), individualCharity.class);  //make sure the controller is set to the correct thing.
                myintent.putExtra("position",i);
                startActivityForResult(myintent,0);                            //gets new controller from the database so that should be ok

            }

        });





    }
    public void charitySearch(View v){                           //don't know if this method will stop the list from being complete if the screen were to revert back
       supplies=new ArrayList<>();
        charitiesObjects.clear();
        CharityNamesOnly.clear();
        aController.getData().clearCharities();
        final Intent refresh = new Intent(this, DonorSearcher.class);
        TextInputLayout supplySearch = findViewById(R.id.SupplyUserSearch);
        final String Supply= supplySearch.getEditText().getText().toString();

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {       //will this add the the controller and change that
                                                                     //in the on click make the method set the controller to whatever the charity objects list holds
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {      //have to make sure that this will work if it is empty and have a special case for that but want to be able to go back to all charities too
                 
                for(DataSnapshot charitysnapshot: dataSnapshot.getChildren()){
                    count=0;
                    Charity c=charitysnapshot.getValue(Charity.class);
                    supplies=c.getSupplies();
                    for(int i=0; i<supplies.size();i++){
                        if(supplies.get(i).contains(Supply)){
                            count++;
                        }
                        else{
                            count=count; //just to catch exceptions
                        }
                    }
                    if(count>0){
                        aController.getData().addCharity(c);
                    }


                }
                for(int i=0; i<CharityNamesOnly.size();i++){
                    Log.d("DonorSearcher", CharityNamesOnly.get(i));
                }

                startActivity(refresh);


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }



    public void CharityRefresh(View v){
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                aController.getData().clearCharities();

                Log.d("MainActivity", "In the on data change.");
                for(DataSnapshot charitysnapshot: dataSnapshot.getChildren()){
                    Log.d("MainActivity", "In loop");
                    Charity c = charitysnapshot.getValue(Charity.class);

                    aController.getData().addCharity(c);

                }
                ArrayList<Charity> charitiesObjects= new ArrayList<Charity>();
                for(int i=0; i<charitiesObjects.size(); i++) {
                    Log.d("MainActivity", charitiesObjects.get(i).getName());
                }

            }



            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }


}