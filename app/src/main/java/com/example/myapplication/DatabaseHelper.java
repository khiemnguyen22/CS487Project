package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String USER_TABLE = "USER_TABLE" ;
    private static final String COLUMN_USER_FIRSTNAME = "USER_FIRSTNAME";
    private static final String COLUMN_USER_LASTNAME = "USER_LASTNAME";
    private static final String COLUMN_USER_EMAIL = "USER_EMAIL";
    private static final String COLUMN_USER_PASSWORD = "USER_PASSWORD";
    private static final String COLUMN_ID = "USER_ID";
    private static final String COLUMN_CREDITCARD = "USER_CREDITCARD";
    private static final String COLUMN_BALANCE = "USER_BALANCE";

    private static final String DRIVER_TABLE = "DRIVER_TABLE";
    private static final String COLUMN_DRIVER_MAKE = "DRIVER_MAKE";
    private static final String COLUMN_DRIVER_MODEL = "DRIVER_MODEL";
    private static final String COLUMN_DRIVER_YEAR = "DRIVER_YEAR";
    private static final String COLUMN_DRIVER_LICENSEPLATE = "DRIVER_LICENSEPLATE";
    private static final String COLUMN_DRIVER_RIDES = "DRIVER_RIDES";




    private String createTable= "CREATE TABLE " + USER_TABLE + "( " + COLUMN_ID + " INTEGER, "
            + COLUMN_USER_FIRSTNAME +" TEXT, "+ COLUMN_USER_LASTNAME+" TEXT, "+COLUMN_USER_EMAIL+ " TEXT PRIMARY KEY, "
            + COLUMN_USER_PASSWORD +" TEXT, "+ COLUMN_CREDITCARD+" TEXT, "+ COLUMN_BALANCE+" NUMERIC)";

    private String createDriverTable = "CREATE TABLE " + DRIVER_TABLE + "( " + COLUMN_ID + " INTEGER, "
            + COLUMN_USER_FIRSTNAME +" TEXT, "+ COLUMN_USER_LASTNAME+" TEXT, "+COLUMN_USER_EMAIL+ " TEXT PRIMARY KEY, "
            + COLUMN_USER_PASSWORD +" TEXT, "+ COLUMN_CREDITCARD+" TEXT, "+ COLUMN_BALANCE +" NUMERIC, "
            + COLUMN_DRIVER_MAKE+" TEXT, "+ COLUMN_DRIVER_MODEL+" TEXT, "+ COLUMN_DRIVER_YEAR+" TEXT, "
            + COLUMN_DRIVER_LICENSEPLATE+" TEXT, "+  COLUMN_DRIVER_RIDES+" INTEGER )";


    public DatabaseHelper(Context context){
        super(context, "User.db",null, 1);

    }
    public DatabaseHelper(Context context, int version){
        super(context, "User.db", null, version);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createDriverTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }

    public boolean addUser(User u){
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_ID, u.getID());
        cv.put(COLUMN_USER_FIRSTNAME, u.getFirstName());
        cv.put(COLUMN_USER_LASTNAME, u.getLastName());
        cv.put(COLUMN_USER_EMAIL, u.getEmail());
        cv.put(COLUMN_USER_PASSWORD, u.getPassword());
        cv.put(COLUMN_CREDITCARD, u.getCreditCard());
        cv.put(COLUMN_BALANCE, u.getBalance());

        long insert = db.insert(USER_TABLE, null, cv);

        if (insert == -1){
            return false;
        }
        else{
            return true;
        }
    }

    public boolean addDriver(Driver d){
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_ID, d.getID());
        cv.put(COLUMN_USER_FIRSTNAME, d.getFirstName());
        cv.put(COLUMN_USER_LASTNAME, d.getLastName());
        cv.put(COLUMN_USER_EMAIL, d.getEmail());
        cv.put(COLUMN_USER_PASSWORD, d.getPassword());
        cv.put(COLUMN_CREDITCARD, d.getCreditCard());
        cv.put(COLUMN_BALANCE, d.getBalance());

        cv.put(COLUMN_DRIVER_MAKE,d.getMake());
        cv.put(COLUMN_DRIVER_MODEL, d.getYear());
        cv.put(COLUMN_DRIVER_YEAR, d.getYear());
        cv.put(COLUMN_DRIVER_LICENSEPLATE, d.getLiscensePlate());
        cv.put(COLUMN_DRIVER_RIDES, d.getRides());

        long insert = db.insert(DRIVER_TABLE, null, cv);

        if (insert == -1){
            return false;
        }
        else{
            return true;
        }
    }

    public User returnUser(String email, String password){
        User returnU = new User();

        String queryString = "SELECT * FROM " + USER_TABLE + " WHERE "+ COLUMN_USER_EMAIL+" = '"+email+"' AND "
                + COLUMN_USER_PASSWORD + " = '"+ password +"';";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString,null);

        if(cursor.moveToFirst()) {
            do {
                returnU.setFirstName(cursor.getString(1));
                returnU.setLastName(cursor.getString(2));
                returnU.setEmail(email);
                returnU.setPassword(password);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return returnU;
    }

    public User returnDriver(String email, String password){
        Driver returnD = new Driver();

        String queryString = "SELECT * FROM " + DRIVER_TABLE + " WHERE "+ COLUMN_USER_EMAIL+" = '"+email+"' AND "
                + COLUMN_USER_PASSWORD + " = '"+ password +"';";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString,null);

        if(cursor.moveToFirst()) {
            do {
                returnD.setFirstName(cursor.getString(1));
                returnD.setLastName(cursor.getString(2));
                returnD.setEmail(email);
                returnD.setPassword(password);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return returnD;
    }

    public boolean resetPassword(User u,String newPass){
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues cv = new ContentValues();
        ContentValues cvDriver = new ContentValues();

        //if user is a driver
        Driver d = (Driver) SharedDBProperties.sharedDriver;
        if( (d.getEmail()).equals(u.getEmail()) ){
            System.out.println("User is a driver");
            cvDriver.put(COLUMN_ID, d.getID());
            cvDriver.put(COLUMN_USER_FIRSTNAME, d.getFirstName());
            cvDriver.put(COLUMN_USER_LASTNAME, d.getLastName());
            cvDriver.put(COLUMN_USER_EMAIL, d.getEmail());
            cvDriver.put(COLUMN_USER_PASSWORD, newPass);
            cvDriver.put(COLUMN_CREDITCARD, d.getCreditCard());
            cvDriver.put(COLUMN_BALANCE, d.getBalance());

            cvDriver.put(COLUMN_DRIVER_MAKE,d.getMake());
            cvDriver.put(COLUMN_DRIVER_MODEL, d.getYear());
            cvDriver.put(COLUMN_DRIVER_YEAR, d.getYear());
            cvDriver.put(COLUMN_DRIVER_LICENSEPLATE, d.getLiscensePlate());
            cvDriver.put(COLUMN_DRIVER_RIDES, d.getRides());

            long insertDriver = db.update(DRIVER_TABLE,cvDriver,COLUMN_USER_EMAIL+" = '"+ u.getEmail()+"' ;",null);
        }

        cv.put(COLUMN_ID, u.getID());
        cv.put(COLUMN_USER_FIRSTNAME, u.getFirstName());
        cv.put(COLUMN_USER_LASTNAME, u.getLastName());
        cv.put(COLUMN_USER_EMAIL, u.getEmail());
        cv.put(COLUMN_USER_PASSWORD, newPass);
        cv.put(COLUMN_CREDITCARD, u.getCreditCard());
        cv.put(COLUMN_BALANCE, u.getBalance());

        long insert = db.update(USER_TABLE,cv, COLUMN_USER_EMAIL+" = '"+ u.getEmail()+"' ;",null);

        if (insert == -1){
            return false;
        }
        else{
            return true;
        }
    }

    public boolean updateCreditCard(User u,String creditCard){
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_ID, u.getID());
        cv.put(COLUMN_USER_FIRSTNAME, u.getFirstName());
        cv.put(COLUMN_USER_LASTNAME, u.getLastName());
        cv.put(COLUMN_USER_EMAIL, u.getEmail());
        cv.put(COLUMN_USER_PASSWORD, u.getPassword());
        cv.put(COLUMN_CREDITCARD, creditCard);
        cv.put(COLUMN_BALANCE, u.getBalance());

        long insert = db.update(USER_TABLE,cv, COLUMN_USER_EMAIL+" = '"+ u.getEmail()+"' ;",null);

        if (insert == -1){
            return false;
        }
        else{
            return true;
        }
    }

    public boolean updateEmail(User u,String email){
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_ID, u.getID());
        cv.put(COLUMN_USER_FIRSTNAME, u.getFirstName());
        cv.put(COLUMN_USER_LASTNAME, u.getLastName());
        cv.put(COLUMN_USER_EMAIL, email);
        cv.put(COLUMN_USER_PASSWORD, u.getPassword());
        cv.put(COLUMN_CREDITCARD, u.getCreditCard());
        cv.put(COLUMN_BALANCE, u.getBalance());

        long insert = db.update(USER_TABLE,cv, COLUMN_USER_EMAIL+" = '"+ u.getEmail()+"' ;",null);

        if (insert == -1){
            return false;
        }
        else{
            return true;
        }
    }

}