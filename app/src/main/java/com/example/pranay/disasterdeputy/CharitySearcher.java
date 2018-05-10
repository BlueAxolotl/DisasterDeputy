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
import android.widget.TextView;
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
    Controller aController;
    ListAdapter charityAdapter;
    int count;
    Button b;
    Button butt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);                             //make sure the controller is set to the correct thing
    setContentView(R.layout.activity_charity_searcher);
        aController = (Controller) getApplicationContext();
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

        //change enter button
        b = (Button) findViewById(R.id.CharityEnterButton);
        Typeface buttonFont = Typeface.createFromAsset(getAssets(), "fonts/Quicksand_Book.otf");
        b.setTypeface(buttonFont);

        //change charity refresh font
        butt = (Button) findViewById(R.id.CharityRefreshButton);
        butt.setTypeface(buttonFont);

       //This displays the charity list of names only on the donor screen
       //Next we have to figure out how to implement the adapter listener so that when the item is clicked it will go to the next page
        charityAdapter = new ArrayAdapter<String>(this, R.layout.mytextview, CharityNamesOnly);
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





    public void charitySearch2(View v){                           //don't know if this method will stop the list from being complete if the screen were to revert back

        charitiesObjects.clear();
        CharityNamesOnly.clear();
        aController.getData().clearCharities();
        final Intent refresh = new Intent(this, CharitySearcher.class);
        TextInputLayout supplySearch = findViewById(R.id.CharityNameSearch);
        final String Name= supplySearch.getEditText().getText().toString();

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {       //will this add the the controller and change that
            //in the on click make the method set the controller to whatever the charity objects list holds
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {      //have to make sure that this will work if it is empty and have a special case for that but want to be able to go back to all charities too

                for(DataSnapshot charitysnapshot: dataSnapshot.getChildren()){
                    count=0;
                    Charity c=charitysnapshot.getValue(Charity.class);


                        if((c.getName().toLowerCase()).contains(Name.toLowerCase())){

                        aController.getData().addCharity(c);
                    }


                }


                startActivity(refresh);                                         //This restarts the activity to repopulate the list with the Controller
                                                                                //this could also be done by starting a new list view here

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }




    public void CharityRefresh2(View v){                                     //figure out why this works for a single value event her but is only the
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {     //value event listener in the donor push and charity push button
            @Override                                                       //make sure that this is checked
            public void onDataChange(DataSnapshot dataSnapshot) {           //check if this listener is ok with the value FIX THIS
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


    }