package com.example.myapplication;


public class User {
    // (String username, String password, String email, Payment p, GeoLocation location)
    // TODO: Complete this
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private int id;
    private String creditCardNumber;
    private double balance;


    public User(){
        this.password="";
        this.email="";
        this.firstName="";
        this.lastName="";
        this.id= -1;
        this.creditCardNumber="";
        this.balance = 100;
    }

    public User(int id, String password, String email, String firstName, String lastName){
        this.password=password;
        this.email=email;
        this.firstName=firstName;
        this.lastName=lastName;
        this.id = 0;
        this.creditCardNumber="";
        this.balance = 100;
    }

    public double getBalance() {return this.balance;}

    public void setBalance(double b) {this.balance = b;}

    public int getID() {return this.id;}

    public String getFirstName(){ return this.firstName;}

    public String getCreditCard() {return this.creditCardNumber;}

    public void setCreditCard(String creditCardNumber){ this.creditCardNumber= creditCardNumber;}

    public void setFirstName(String firstName) {this.firstName= firstName;}

    public String getLastName(){ return this.lastName;}

    public void setLastName(String lastName) {this.lastName= lastName;}

    public String getPassword(){
        return this.password;
    }

    public void setPassword(String pw){ this.password = pw; }

    public String getEmail(){
        return this.email;
    }

    public void setEmail(String e){ this.email = e; }



}
