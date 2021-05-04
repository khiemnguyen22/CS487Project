package com.example.myapplication;



public class Ride {

    private int rideId;
    private double price;
    private double distance;
    private int mins;
    private int secs;
    private Driver driver;
    private User rider;

    public Ride(){
        rideId = 0;
        price = 0.0;
        distance = 0.0;
        mins = 0;
        secs = 0;
        driver = new Driver();
        rider = new User();
    }

    public Ride(int rideId, double price, double distance, int mins, int secs, Driver driver, User rider){
        this.rideId = rideId;
        this.price = price;
        this.distance = distance;
        this.mins = mins;
        this.secs = secs;
        this.driver = driver;
        this.rider = rider;
    }

    public int getRideId() {
        return rideId;
    }

    public void setRideId(int rideId) {
        this.rideId = rideId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public int getMins() {
        return mins;
    }

    public void setMins(int mins) {
        this.mins = mins;
    }

    public int getSecs() {
        return secs;
    }

    public void setSecs(int secs) {
        this.secs = secs;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public User getRider() {
        return rider;
    }

    public void setRider(User rider) {
        this.rider = rider;
    }
}
