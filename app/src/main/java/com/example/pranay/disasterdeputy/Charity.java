package com.example.pranay.disasterdeputy;
import java.util.ArrayList;
public class Charity {
    private String zipCode;
    private String name;
    private ArrayList<String> supplies;

//This class models a charity with a certain location and a list of supplies that the charity needs

    //This constructor creates a charity object with the name, address and supplies of the charity
    public Charity(String n, String z, ArrayList<String> s){
        name = n;
        zipCode = z;
        supplies= new ArrayList<String>();
        supplies=s;
//        for(int i = 0;i<s.size();i++){
//            supplies.add(s.get(i));
//        }
    }
    //This method takes the input of a string and sets that to the name of a charity object
    public void setName(String n){

        name = n;
    }

    //This method takes the input of a distance and sets it to the location of the charity
    public void setDistance(String z){

        zipCode = z;
    }

    //This method does not have any input and gets the name of the charity
    public String getName(){

        return name;
    }

    //This method does not take input and returns location of the charity
    public String getZipCode(){

        return zipCode;
    }

    //This method does not take any input and returns the array list of supplies for the charity
    public ArrayList<String> getSupplies(){

        return supplies;
    }

    //This method will eventually take user input to determine the distance from the charity to the user
    public double getDistance(){

        return 0;
    }

    public void addSupplies(String supply){
        supplies.add(supply);

    }

    public void clearSupplies(){
        supplies.clear();
    }

   //public Charity(String charityName, int distance){

    //}
}
