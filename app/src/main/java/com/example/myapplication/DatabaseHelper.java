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


    private String createTable= "CREATE TABLE " + USER_TABLE + "( " + COLUMN_ID + " INTEGER, "
            + COLUMN_USER_FIRSTNAME +" TEXT, "+ COLUMN_USER_LASTNAME+" TEXT, "+COLUMN_USER_EMAIL+ " TEXT PRIMARY KEY, "
            + COLUMN_USER_PASSWORD +" TEXT, "+ COLUMN_CREDITCARD+" TEXT, "+ COLUMN_BALANCE+" NUMERIC)";

    public DatabaseHelper(Context context){
        super(context, "User.db",null, 1);

    }
    public DatabaseHelper(Context context, int version){
        super(context, "User.db", null, version);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + USER_TABLE);
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

    public boolean resetPassword(User u,String newPass){
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues cv = new ContentValues();

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
