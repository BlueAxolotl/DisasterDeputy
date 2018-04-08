package com.example.pranay.disasterdeputy;
import java.util.ArrayList;
public class Charity {
    private int zipCode;
    private String name;
    private ArrayList<String> supplies = new ArrayList<String>();
    public Charity(String n, int z, ArrayList<String> s){
        name = n;
        zipCode = z;
        for(int i = 0;i<s.size();i++){
            supplies.add(s.get(i));
        }
    }
    public void setName(String n){
        name = n;
    }
    public void setDistance(int z){
        zipCode = z;
    }
    public String getName(){
        return name;
    }
    public double getZipCode(){
        return zipCode;
    }
    public ArrayList<String> getSupplies(){
        return supplies;
    }
    public double getDistance(){
        return 0;
    }

   public Charity(String charityName, int distance){

    }
}
