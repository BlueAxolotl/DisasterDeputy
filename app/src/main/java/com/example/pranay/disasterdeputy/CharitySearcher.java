package com.example.pranay.disasterdeputy;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class CharitySearcher extends AppCompatActivity {

    ArrayList<Charity> charitiesObjects;
    ArrayList<String> CharityNamesOnly;
    DatabaseReference myRef;
    FirebaseDatabase database;
    Controller aController;
    ListAdapter charityAdapter;
    int count;
    Button b;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_charity_searcher);

        aController = (Controller) getApplicationContext();
       FirebaseApp.initializeApp(this);
       database= FirebaseDatabase.getInstance();
       myRef= database.getReference("Charities");
       CharityList cl=aController.getData();
       CharityNamesOnly=new ArrayList<>();

       charitiesObjects=cl.getCharityList();

    //This creates a list of the names of the charities so that they can be displayed on the list view
       for(int i=0; i<charitiesObjects.size(); i++){
           String CharityName = charitiesObjects.get(i).getName();
           CharityNamesOnly.add(CharityName);
       }

        //change enter button
        b = (Button) findViewById(R.id.CharityEnterButton);
        Typeface buttonFont = Typeface.createFromAsset(getAssets(), "fonts/Quicksand_Book.otf");
        b.setTypeface(buttonFont);

        //change charity refresh font
        button = (Button) findViewById(R.id.CharityRefreshButton);
        button.setTypeface(buttonFont);

       //This displays the charity list of names only on the donor screen
        charityAdapter = new ArrayAdapter<String>(this, R.layout.mytextview, CharityNamesOnly);
       ListView myListView = (ListView) findViewById(R.id.UserCharityList);
       myListView.setAdapter(charityAdapter);






        //this goes to the next page when an item on the list is selected
        //the position of the charity in the list view so that the charity can be selected from the controller in the next class
       myListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
           @Override
           public void onItemClick(AdapterView<?> adapterView, View view, int i, long l){
               Intent myintent=new Intent(view.getContext(), charityInput.class);
               myintent.putExtra("position",i);
               startActivityForResult(myintent,0);


           }

       });





   }


//This method is activated when the user clicks the enter button on the screen
    //This method searches the database and populates the controller with the charities that match the name that the user searched for
    public void charitySearch2(View v){

        charitiesObjects.clear();
        CharityNamesOnly.clear();
        aController.getData().clearCharities();
        final Intent refresh = new Intent(this, CharitySearcher.class);
        TextInputLayout supplySearch = findViewById(R.id.CharityNameSearch);
        final String Name= supplySearch.getEditText().getText().toString();


        //This gets the data from the database and populates the controller with only the data that contains the name that the user entered
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot charitysnapshot: dataSnapshot.getChildren()){
                    count=0;
                    Charity c=charitysnapshot.getValue(Charity.class);


                        if((c.getName().toLowerCase()).contains(Name.toLowerCase())){        //Checks to determine if the charity contains the name that the user entered

                        aController.getData().addCharity(c);
                    }


                }


                startActivity(refresh);                                         //This restarts the activity to repopulate the list with the Controller


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }


    //This method refreshes the charity list that is displayed on the app to include all the data in the database
    public void CharityRefresh2(View v){
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

        Intent myintent=new Intent(this, CharitySearcher.class);
        startActivity(myintent);
    }

    }