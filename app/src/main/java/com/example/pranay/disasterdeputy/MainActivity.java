package com.example.pranay.disasterdeputy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    public void CharityPush(View v){
        Intent intent = new Intent (this, CharitySearcher.class);
        startActivity(intent);
    }

    public void DonorPush(View v){
        Intent intent2 = new Intent (this, DonorSearcher.class);
        startActivity(intent2);
    }

}
