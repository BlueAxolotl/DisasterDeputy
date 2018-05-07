package com.example.pranay.disasterdeputy;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;




// fix the getting of the controller to make all of the charities display


//This class will be for each individual charity to enter the supplies that they have
//the supplies will be added to the corresponding array list in their charity object
//if the user searches something we can make i go to a different activity than if not because it will use a different array list
//Switch the list view on the screen and send it to a different activity that uses the list view that was in the new shortened list
//this will be like an on search method
public class charityInput extends AppCompatActivity {
    int position;
    DatabaseReference myRef;
    String charityName;
    Controller aController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charity_input);
        Bundle bundle = getIntent().getExtras();
         position=bundle.getInt("position");
        aController = (Controller) getApplicationContext();
        FirebaseDatabase database= FirebaseDatabase.getInstance();
        myRef= database.getReference("Charities");
        charityName=aController.getData().getOneCharity(position).getName();
        String charityLocation=aController.getData().getOneCharity(position).getZipcode();
        ArrayList<String> charitySupplies=aController.getData().getOneCharity(position).getSupplies();


        TextView nametext = findViewById(R.id.individualCharityName);
        nametext.setText("Charity Name: " + charityName);

        TextView  locationText= findViewById(R.id.individualcharityLocation);
        locationText.setText("Charity Location: " + charityLocation );


        ListAdapter charityAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, charitySupplies); //only works if something is in the list
        ListView myListView = (ListView) findViewById(R.id.SupplyList);                                                            //had to put none in the list for now
        myListView.setAdapter(charityAdapter);                                                                                      //in the method that can be deleted when something is added to



        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l){           //need to add a message that says click one to delete
                Intent myintent=new Intent(view.getContext(), clearOneScreen.class);
                String supplyName=aController.getData().getOneCharity(position).getSupplies().get(i);
                myintent.putExtra("supplyPlacement",i);
                myintent.putExtra("position",position);
                myintent.putExtra("supplyName", supplyName);
                myintent.putExtra("CharityName", charityName);
                startActivityForResult(myintent,0);


            }

        });


    }

    public void supplyInput(View v){
        TextInputLayout supplyentry = findViewById(R.id.supplyinput);
        String Supply= supplyentry.getEditText().getText().toString();
        aController = (Controller) getApplicationContext();
        ArrayList<String> charitySupplies=(aController.getData().getOneCharity(position)).getSupplies();
        charitySupplies.add(Supply);



        myRef.child(charityName).child("supplies").setValue(charitySupplies);

        Intent myintent=new Intent(v.getContext(), charityInput.class);
        myintent.putExtra("position",position);
        startActivityForResult(myintent,0);
    }

    public void supplyClear(View v){                                           //add a delete single supply method
        Intent myintent=new Intent(v.getContext(), clearAllScreen.class);
        myintent.putExtra("position",position);
        myintent.putExtra("CharityName", charityName);
        startActivityForResult(myintent,0);

    }
}
