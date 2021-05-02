package com.example.myapplication;

public class User {
    // (String username, String password, String email, Payment p, GeoLocation location)
    // TODO: Complete this
    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;

    public User(){
        this.username="";
        this.password="";
        this.email="";
        this.firstName="";
        this.lastName="";
    }

    public User(String username, String password, String email, String firstName, String lastName){
        this.username=username;
        this.password=password;
        this.email=email;
        this.firstName=firstName;
        this.lastName=lastName;
    }

    public String getFirstName(){ return this.firstName;}

    public void setFirstName(String firstName) {this.firstName= firstName;}

    public String getLastName(){ return this.lastName;}

    public void setLastName(String lastName) {this.lastName= lastName;}

    public String getUsername(){
        return this.username;
    }

    public void setUsername(String un){
        this.username = un;
    }
    public String getPassword(){
        return this.password;
    }

    public void setPassword(String pw){ this.password = pw; }


}
