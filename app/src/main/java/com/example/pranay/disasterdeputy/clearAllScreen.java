package com.example.pranay.disasterdeputy;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class clearAllScreen extends AppCompatActivity {
    int position;
    String charityName;
    DatabaseReference myRef;
    Controller aController;
    TextView t;
    Button b;
    Button butt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clear_all_screen);

        //This gets the data from the previous screen
        Bundle bundle = getIntent().getExtras();
        position=bundle.getInt("position");   //this is the position of the charity in the controller
        charityName=bundle.getString("CharityName");


        myRef= FirebaseDatabase.getInstance().getReference("Charities");

        //font for question text
        t = (TextView) findViewById(R.id.CheckingDelete);
        Typeface myCustomFont = Typeface.createFromAsset(getAssets(), "fonts/cocogoose.ttf");
        t.setTypeface(myCustomFont);

        //change yes button font
        b = (Button) findViewById(R.id.confirmButton);
        Typeface buttonFont = Typeface.createFromAsset(getAssets(), "fonts/Quicksand_Book.otf");
        b.setTypeface(buttonFont);

        //change no button font
        butt = (Button) findViewById(R.id.denyButton);
        butt.setTypeface(buttonFont);
    }

    //if the user clicks the yes buttons then all the data is cleared from the specified charity
    //the method also returns to the previous screen
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


    //if the user clicks no then the supplies is left the way it is and the app returns to the previous screen
    public void leaveSupplies(View v){
        Intent back =new Intent(v.getContext(), charityInput.class);
        back.putExtra("position",position);
        startActivity(back);
    }
}
