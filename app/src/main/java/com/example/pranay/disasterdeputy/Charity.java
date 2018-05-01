package com.example.pranay.disasterdeputy;
import java.util.ArrayList;
public class Charity {
    private String zipcode;
    private String name;
    private  long distance;
    private ArrayList<String> supplies;

//This class models a charity with a certain location and a list of supplies that the charity needs


    public Charity(){

    }

    //This constructor creates a charity object with the name, address and supplies of the charity
    public Charity(String n, String z, ArrayList<String> s){
        name = n;
        zipcode = z;
        supplies= new ArrayList<String>();
        supplies=s;
        distance=0;
    }




    //This method takes the input of a string and sets that to the name of a charity object
    public void setName(String n){

        this.name = n;
    }

    //This method takes the input of a distance and sets it to the location of the charity
    public void setZipcode(String z){

        this.zipcode = z;
    }

    //This method does not have any input and gets the name of the charity
    public String getName(){

        return name;
    }

    //This method does not take input and returns location of the charity
    public String getZipcode(){

        return zipcode;
    }

    //This method does not take any input and returns the array list of supplies for the charity
    public ArrayList<String> getSupplies(){

        return supplies;
    }


    public void setDistance(long d){
        this.distance=d;

    }
    //This method will eventually take user input to determine the distance from the charity to the user
    public long getDistance(){

        return 0;
    }

    public void setSupplies(ArrayList<String> s){
        this.supplies=s;
    }

    public void addSupplies(String supply){
        supplies.add(supply);

    }

    public void clearSupplies(){
        supplies.clear();
    }


}
