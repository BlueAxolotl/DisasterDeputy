package com.example.pranay.disasterdeputy;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
/**
 * Created by Pranay on 4/2/18.
 */

public class CharityList {
    public static void main(String[]args){
        ArrayList<Charity> list = new ArrayList<Charity>();
        //FirebaseDatabase database = FirebaseDatabase.getInstance();
        //DatabaseReference myRef = database.getReference("message ");
        //myRef.setValue(list);


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
