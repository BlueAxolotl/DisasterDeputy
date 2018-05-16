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
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;




//This class is where the charity user can input, clear, and delete specific supplies from their charity.
//The action completed will be reflected in Firebase as well as the controller
public class charityInput extends AppCompatActivity {
    int position;   //This is the position of the charity in the list on the previous screen so that the information about the specific charity can be attained
    DatabaseReference myRef;
    String charityName;
    Controller aController;
    TextView t;           //This is the text for the charity location
    Button b;           //This is the variable for the text on the
    Button button;
    TextView d;        //This is for the delete message button

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charity_input);

        //The gets the position from the previous screen
        Bundle bundle = getIntent().getExtras();
        position=bundle.getInt("position");

        //This initializes the database from this screen
        FirebaseDatabase database= FirebaseDatabase.getInstance();
        myRef= database.getReference("Charities");

        //This gets the specific charity name, location, and supplies by using the position and the controller
        aController = (Controller) getApplicationContext();
        charityName=aController.getData().getOneCharity(position).getName();
        String charityLocation=aController.getData().getOneCharity(position).getZipcode();
        ArrayList<String> charitySupplies=aController.getData().getOneCharity(position).getSupplies();


        TextView nametext = findViewById(R.id.individualCharityName);
        nametext.setText(charityName);

        TextView  locationText= findViewById(R.id.individualcharityLocation);
        locationText.setText(charityLocation);

        //font for charity name
        Typeface myCustomFont = Typeface.createFromAsset(getAssets(), "fonts/Quicksand_Book.otf");
        nametext.setTypeface(myCustomFont);
        nametext.setTypeface(null, Typeface.BOLD);

        //font for delete Message
        d = (TextView) findViewById(R.id.deleteMessage);
        d.setTypeface(myCustomFont);


        //font for charity location
        t = (TextView) findViewById(R.id.individualcharityLocation);
        t.setTypeface(myCustomFont);

        //change donor button font
        b = (Button) findViewById(R.id.clearSupply);
        b.setTypeface(myCustomFont);

        //change charity button font
        button = (Button) findViewById(R.id.AddSupply);
        button.setTypeface(myCustomFont);

        //This list displays the list of supplies for the user to view
        ListAdapter charityAdapter = new ArrayAdapter<String>(this, R.layout.mytextview, charitySupplies);
        ListView myListView = (ListView) findViewById(R.id.SupplyList);
        myListView.setAdapter(charityAdapter);

        //When an item in the list of supplies is clicked a new screen is created

        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l){
                Intent myintent=new Intent(view.getContext(), clearOneScreen.class);
                String supplyName=aController.getData().getOneCharity(position).getSupplies().get(i);
                myintent.putExtra("supplyPlacement",i);                             //the placement of the the supply so that the supply can be deleted in the next activity
                myintent.putExtra("position",position);                             //The position of the original list of charities is brought to the next screen so that the user can return to this screen if they want to
                myintent.putExtra("supplyName", supplyName);                        //The supply name for verification on the next screen
                myintent.putExtra("CharityName", charityName);                      //to access the database on the next screen
                startActivityForResult(myintent,0);

            }

        });


    }

    //this method is activated when the user enters a supply and clicks the add button
    //this method adds the supply to both the controller and the database
    public void supplyInput(View v){
        TextInputLayout supplyentry = findViewById(R.id.supplyinput);
        String Supply= supplyentry.getEditText().getText().toString().trim();
        aController = (Controller) getApplicationContext();

        ArrayList<String> charitySupplies=(aController.getData().getOneCharity(position)).getSupplies();  //this gets the existing list from the controller

        if(!Supply.isEmpty()) {
            charitySupplies.add(Supply);                                                                   //This adds the supply to the existing list
        }


        myRef.child(charityName).child("supplies").setValue(charitySupplies);                            //this sets the value of the supplies to the new list

        Intent myintent=new Intent(v.getContext(), charityInput.class);
        myintent.putExtra("position",position);
        startActivityForResult(myintent,0);
    }

    //this method is activated when the user clicks the clear all button
    //The click of the button starts the new activity that verifies that the user would like to clear all the supplies
    public void supplyClear(View v){
        Intent myintent=new Intent(v.getContext(), clearAllScreen.class);
        myintent.putExtra("position",position);
        myintent.putExtra("CharityName", charityName);
        startActivityForResult(myintent,0);

    }

}
