package com.example.myapplication;



public class Payment {
    DatabaseHelper dh;
    Ride ride;
    Driver driver;
    User user;
    int id;
    public Payment(Ride ride1){
        this.driver = ride1.getDriver();
        this.user = ride1.getRider();
        this.ride = ride1;
        this.id = (int)(Math.random()*(1e7 +1e4));
    }
    public void makePayment(Driver driver, User user, Ride ride){
        double cost = ride.getPrice();
        double driverBeforeBalance = driver.getBalance();
        double userBeforeBalance = user.getBalance();
        driverBeforeBalance = driverBeforeBalance + cost;
        userBeforeBalance = userBeforeBalance - cost;
        user.setBalance(userBeforeBalance);
        driver.setBalance(driverBeforeBalance);
        dh.updateBalance(user, user.getBalance());
        dh.updateBalance(driver, driver.getBalance());
    }
}