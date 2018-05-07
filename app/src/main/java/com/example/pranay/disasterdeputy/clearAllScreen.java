package com.example.pranay.disasterdeputy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class clearAllScreen extends AppCompatActivity {
    int position;
    String charityName;
    DatabaseReference myRef;
    Controller aController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clear_all_screen);
        Bundle bundle = getIntent().getExtras();
        position=bundle.getInt("position");
        charityName=bundle.getString("CharityName");
        myRef= FirebaseDatabase.getInstance().getReference("Charities");



    }

    public void clearSupplies(View v){
         aController = (Controller) getApplicationContext();

        ArrayList<String> charitySupplies=aController.getData().getOneCharity(position).getSupplies();
        if(charitySupplies != null) {
            charitySupplies.clear();
            myRef.child(charityName).child("supplies").setValue(charitySupplies);
        }




        Intent back =new Intent(v.getContext(), charityInput.class);
        back.putExtra("position",position);
        startActivity(back);

    }

    public void leaveSupplies(View v){
        Intent back =new Intent(v.getContext(), charityInput.class);
        back.putExtra("position",position);
        startActivity(back);
    }
}
