package com.example.pranay.disasterdeputy;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
/**
 * Created by Pranay on 4/2/18.
 */

//This class is still in progress but it will eventually  be used to establish the database of charities

public class CharityList {
    static DatabaseReference database = FirebaseDatabase.getInstance().getReference();
    Charity result;
    private ArrayList<Charity> charities;
    private Charity c;
    public static void main(String[]args){
        charities = new ArrayList<Charity>();
        for(int i = 0;i<charities.size();i++) {

            DatabaseReference pushedCharity = database.child(charities.get(i).getName());
            pushedCharity.setValue(charities.get(i));
        }

        //myRef.setValue(list);
    }
    public Charity getCharityByName(String name) {

        database.child("charities").child(name).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                result = dataSnapshot.getValue(Charity.class);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return result;
    }
    public CharityList(){
        charities=new ArrayList<Charity>();
    }

    public void addCharity(String n, String L, ArrayList<String> s){
        c=new Charity(n,L,s);
        charities.add(c);
    }

    public ArrayList<Charity> getCharityList(){

        return charities;
    }

    public Charity getOneCharity(int position){

        return charities.get(position);
    }




    private ArrayList<Charity> charities = new ArrayList<Charity>();
    public static ArrayList<Charity> search(String s, ArrayList<Charity> charities){
        ArrayList<Charity> matches = new ArrayList<Charity>();
        for(int i = 0;i< charities.size();i++){
            for(int j = 0; j<charities.get(i).getSupplies().size();j++){
                if(charities.get(i).getSupplies().get(j).equals(s)){
                    matches.add(charities.get(i));
                }
            }
        }
        return matches;
    }
    public static ArrayList<Charity> sort(ArrayList<Charity> matches){
        ArrayList<Charity> sortedList = new ArrayList<Charity>();
        if(matches.size()>0){
            sortedList.add(matches.get(0));
        }
        for(int i = 1; i<matches.size();i++){
            boolean added = false;
            for(int j = 0;j<sortedList.size();j++){
                if(matches.get(i).getDistance()<=sortedList.get(j).getDistance()){
                    sortedList.add(j, matches.get(i));
                    added = true;
                    break;
                }

            }
            if(!added){
                sortedList.add(matches.get(i));
            }
        }
        return sortedList;
    }
}






