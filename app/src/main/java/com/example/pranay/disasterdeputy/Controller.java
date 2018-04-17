package com.example.pranay.disasterdeputy;
//This class is a controller to store the array list throughout the app
import android.app.Application;

import java.util.ArrayList;
//this is a controller that stores a CharityList object the object is an arraylist of charities
//using a method from the charity list class the elements of the list can be found
//will need to figure out how to use the shorter list when going to the next page
//how to get this this to be shorter when searching
public class Controller extends Application{

    private CharityList charities= new CharityList();

    public CharityList getData() {

        return charities;

    }

}
