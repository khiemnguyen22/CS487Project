package com.example.myapplication;

public class Driver extends User {
    private String make;
    private String model;
    private String year;
    private String liscensePlate;

    public Driver(){
        super();
        make = "";
        model = "";
        year = "";
        liscensePlate = "";
    }

    public Driver(String username,String password,String email,String firstName,String lastName,String make,String model,String year,String liscensePlate){
        super(username, password, email, firstName, lastName);
        this.make = make;
        this.model = model;
        this.year = year;
        this.liscensePlate = liscensePlate;
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
}
