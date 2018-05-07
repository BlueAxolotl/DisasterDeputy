package com.example.pranay.disasterdeputy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Bundle bundle = getIntent().getExtras();
        position=bundle.getInt("position");
        supplyName= bundle.getString("supplyName");
        SupplyPlacement =bundle.getInt("supplyPlacement");
        charityName=bundle.getString("CharityName");
        myRef= FirebaseDatabase.getInstance().getReference("Charities");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clear_one_screen);
        TextView nametext = findViewById(R.id.clearOneSupplyMessage);
        nametext.setText("Are you sure you would like to delete " + supplyName+ " from your supplies list?");
    }


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
    public void leaveAll(View v){
        Intent myintent=new Intent(v.getContext(), charityInput.class);
        myintent.putExtra("position",position);
        startActivityForResult(myintent,0);
    }
}
