package com.example.pranay.disasterdeputy;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class clearOneScreen extends AppCompatActivity {
    int position;
    String supplyName;
    int SupplyPlacement;
    Controller aController;
    DatabaseReference myRef;
    String charityName;
    TextView t;
    Button b;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clear_one_screen);

        //This gets the data that was passed from the previous screen
        Bundle bundle = getIntent().getExtras();
        position=bundle.getInt("position");
        supplyName= bundle.getString("supplyName");
        SupplyPlacement =bundle.getInt("supplyPlacement");
        charityName=bundle.getString("CharityName");

        myRef= FirebaseDatabase.getInstance().getReference("Charities");

      //This is a statement to make sure the user would like to delete the supply
        TextView nametext = findViewById(R.id.clearOneSupplyMessage);
        nametext.setText("Are you sure you would like to delete " + supplyName+ " from your supplies list?");

        //font for question text
        t = (TextView) findViewById(R.id.clearOneSupplyMessage);
        Typeface myCustomFont = Typeface.createFromAsset(getAssets(), "fonts/cocogoose.ttf");
        t.setTypeface(myCustomFont);

        //change yes button font
        b = (Button) findViewById(R.id.confirmOneButton);
        Typeface buttonFont = Typeface.createFromAsset(getAssets(), "fonts/Quicksand_Book.otf");
        b.setTypeface(buttonFont);

        //change no button font
        button = (Button) findViewById(R.id.DenyOneButton);
        button.setTypeface(buttonFont);
    }

    //If the user clicks the yes button it deletes the supply they selected on the previous screen from the list of supplies in their charity
    //after the supply is deleted the app returns back to the previous screen
    public void deleteOne(View v){
        aController = (Controller) getApplicationContext();

        ArrayList<String> charitySupplies=aController.getData().getOneCharity(position).getSupplies();
        if(charitySupplies != null) {
            charitySupplies.remove(SupplyPlacement);
            myRef.child(charityName).child("supplies").setValue(charitySupplies);
        }
        Intent myintent=new Intent(v.getContext(), charityInput.class);
        myintent.putExtra("position",position);
        startActivityForResult(myintent,0);
    }

    //this leaves all the supplies in the list and returns to the previous screen if the user selects no
    public void leaveAll(View v){
        Intent myintent=new Intent(v.getContext(), charityInput.class);
        myintent.putExtra("position",position);
        startActivityForResult(myintent,0);
    }
}
