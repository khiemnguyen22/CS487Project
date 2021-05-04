package com.example.myapplication;

public class Driver extends User {
    private String make;
    private String model;
    private String year;
    private String liscensePlate;
    private int rides;

    public Driver(){
        super();
        make = "";
        model = "";
        year = "";
        liscensePlate = "";
        rides = 0;

    }

    public Driver(int id, String password,String email,String firstName,String lastName,String make,String model,String year,String liscensePlate,int rides){
        super(id, password, email, firstName, lastName);
        this.make = make;
        this.model = model;
        this.year = year;
        this.liscensePlate = liscensePlate;
        this.rides = rides;
    }

    public Driver(int id, String password,String email,String firstName,String lastName){
        super(id, password, email, firstName, lastName);
        make = "";
        model = "";
        year = "";
        liscensePlate = "";
        rides = 0;

    }

    public String getMake(){ return this.make;}

    public void setMake(String make) {this.make= make;}

    public String getModel(){ return this.model;}

    public void setModel(String model) {this.model= model;}

    public String getYear(){
        return this.year;
    }

    public void setYear(String un){
        this.year = year;
    }

    public String getLiscensePlate(){
        return this.liscensePlate;
    }

    public void setLiscensePlate(String liscensePlate){ this.liscensePlate = liscensePlate; }

    public int getRides(){ return this.rides;}

    public void setRides(int rides) {this.rides = rides;}
}
