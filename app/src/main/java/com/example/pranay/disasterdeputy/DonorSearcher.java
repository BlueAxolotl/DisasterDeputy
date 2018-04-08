package com.example.pranay.disasterdeputy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class DonorSearcher extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String charityList[]={"UNICEF", "American Red Cross"};
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor_searcher);
        ListView simpleList = (ListView) findViewById(R.id.CharityList_User);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.activity_donor_searcher, charityList);
        simpleList.setAdapter(arrayAdapter);
    }
}
