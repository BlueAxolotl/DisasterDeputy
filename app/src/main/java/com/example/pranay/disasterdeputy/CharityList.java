package com.example.pranay.disasterdeputy;
import java.util.ArrayList;

/**
 * Created by Pranay on 4/2/18.
 */

//This class is a list of charities to be used in the controller so that the charities can be accessed from the controller

public class CharityList {

    private ArrayList<Charity> charities;
    private Charity c;

    //This is the constructor for the charityList
    //the arrayList of charities is initialized
    public CharityList() {
        charities = new ArrayList<Charity>();

    }

    //This is a method to add a single charity to the charity list
    public void addCharity(Charity c) {

        charities.add(c);
    }

    //this method returns the list of charities
    public ArrayList<Charity> getCharityList() {

        return charities;
    }

    //this method takes the input of a position
    //returns the charity at the position
    public Charity getOneCharity(int position) {

        return charities.get(position);
    }

    //This method clears the list of charities
    public void clearCharities(){
        charities.clear();
    }


}






