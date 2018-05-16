package com.example.pranay.disasterdeputy;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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
    Button b;
    Button button;

    @Override

    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor_searcher);

        database= FirebaseDatabase.getInstance();
        myRef= database.getReference("Charities");
        aController = (Controller) getApplicationContext();
        FirebaseApp.initializeApp(this);


        CharityList cl=aController.getData();
       charitiesObjects= new ArrayList<Charity>();
        charitiesObjects=cl.getCharityList();

       CharityNamesOnly= new ArrayList<String>();

        //change enter button font
        b = (Button) findViewById(R.id.SupplySearch);
        Typeface buttonFont = Typeface.createFromAsset(getAssets(), "fonts/Quicksand_Book.otf");
        b.setTypeface(buttonFont);

        //change refresh button font
        button = (Button) findViewById(R.id.SupplyReset);
        button.setTypeface(buttonFont);

        //This makes a list of the names to display in the list view
        for(int i=0; i<charitiesObjects.size(); i++){
            String CharityName = charitiesObjects.get(i).getName();
            CharityNamesOnly.add(CharityName);

        }
        //This displays the charity list of names only on the donor screen

         charityAdapter = new ArrayAdapter<String>(this, R.layout.mytextview, CharityNamesOnly);
        ListView myListView = (ListView) findViewById(R.id.UserCharityList);
        myListView.setAdapter(charityAdapter);



        //this brings the user to information about the charity they click on the next activity
        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l){
                Intent myintent=new Intent(view.getContext(), individualCharity.class);
                myintent.putExtra("position",i);
                startActivityForResult(myintent,0);

            }

        });

    }


    //This is the search for the donor by the supplies that they have


    public void charitySearch(View v){
       supplies=new ArrayList<>();
        charitiesObjects.clear();
        CharityNamesOnly.clear();
        aController.getData().clearCharities();
        final Intent refresh = new Intent(this, DonorSearcher.class);
        TextInputLayout supplySearch = findViewById(R.id.SupplyUserSearch);
        final String Supply= supplySearch.getEditText().getText().toString();

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                 
                for(DataSnapshot charitysnapshot: dataSnapshot.getChildren()){
                    count=0;
                    Charity c=charitysnapshot.getValue(Charity.class);
                    supplies=(ArrayList<String>) c.getSupplies();
                    for(int i=0; i<supplies.size();i++){
                        if((supplies.get(i).toLowerCase()).contains(Supply.toLowerCase())){        //checks to see if the string that the user enters is contained in any of the supplies in the charity
                            count++;
                        }
                        else{
                            count=count; //just to catch exceptions
                        }
                    }
                    if(count>0){                                 //if the charity has at least one match then it is added to the controller to get the new list
                        aController.getData().addCharity(c);
                    }


                }

                startActivity(refresh);                          // this restarts the activity to repopulate the list view


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }


//this refreshes the charities that are displayed in the list view
    public void CharityRefresh(View v){
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

        Intent myintent=new Intent(this, DonorSearcher.class);
        startActivity(myintent);
    }


}