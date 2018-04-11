package com.example.pranay.disasterdeputy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class DonorSearcher extends AppCompatActivity {


    ListView simpleList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor_searcher);
        Bundle extras = getIntent().getExtras();
        ArrayList<Charity> Charities  =  (ArrayList<Charity>)getIntent().getSerializableExtra("CharityList");



    }


}
