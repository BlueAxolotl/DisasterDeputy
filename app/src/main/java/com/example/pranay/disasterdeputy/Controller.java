package com.example.pranay.disasterdeputy;
//This class is a controller to store the charitylist throughout the app
import android.app.Application;


public class Controller extends Application{



    private CharityList charities=new CharityList();

//This returns the charity list which then can be changed using methods from the charity list class
    public CharityList getData() {

        return charities;

    }

}
