package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class DriverMainActivity extends AppCompatActivity {

    Button logout;
    Button getCustomer;
    FirebaseUser user;
    private TextView fullName, emailView, modelView, yearView, licenseView, makeView;
    private String name, email;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_main);

        logout = findViewById(R.id.logoutbutton);
        getCustomer = findViewById(R.id.findRide);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext(),Login.class));
                finish();
            }
        });
        getCustomer = findViewById(R.id.findRide);
        getCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHelper db = new DatabaseHelper(DriverMainActivity.this,4);
                Driver d = db.returnDriver(SharedDBProperties.sharedUser.getEmail(),SharedDBProperties.sharedUser.getPassword());

                if(d.getLiscensePlate().equals("") | d.getMake().equals("") | d.getModel().equals("") | d.getYear().equals("")){
                    Toast.makeText(DriverMainActivity.this, "Driver not registered", Toast.LENGTH_SHORT).show();
                }
                else {
                    startActivity(new Intent(getApplicationContext(), DriverMapsActivity.class));
                    finish();
                }
            }
        });

        fullName = findViewById(R.id.DriverName);
        emailView = findViewById(R.id.DriverEmail);
        modelView = findViewById(R.id.modelInfo);
        makeView = findViewById(R.id.makeInfo);
        yearView = findViewById(R.id.yearInfo);
        licenseView = findViewById(R.id.licenseInfo);


        DatabaseHelper db = new DatabaseHelper(DriverMainActivity.this,4);
        Driver d = db.returnDriver(SharedDBProperties.sharedUser.getEmail(),SharedDBProperties.sharedUser.getPassword());

        SharedDBProperties.sharedDriver.setModel(d.getModel());
        SharedDBProperties.sharedDriver.setMake(d.getMake());
        SharedDBProperties.sharedDriver.setYear(d.getYear());
        SharedDBProperties.sharedDriver.setLiscensePlate(d.getLiscensePlate());


        modelView.setText("Car's model: "+ d.getModel());
        makeView.setText("Car's make: " + d.getMake());
        yearView.setText("Car's year: " + d.getYear());
        licenseView.setText("Car's License Number: " + d.getLiscensePlate());
        fullName.setText(SharedDBProperties.sharedDriver.getFirstName() + " "+SharedDBProperties.sharedDriver.getLastName());
        emailView.setText(email);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_driver_main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.registerDriver){
            startActivity(new Intent(getApplicationContext(),RegisterDriver.class));
        }
        if(item.getItemId() == R.id.resetuserpassword){
            startActivity(new Intent(getApplicationContext(),ResetPassword.class));
            finish();
        }
        if(item.getItemId() == R.id.updateCreditCard){
            startActivity(new Intent(getApplicationContext(),UpdateCreditCard.class));
        }
        if(item.getItemId() == R.id.updateEmailmenu){
            startActivity(new Intent(getApplicationContext(),UpdateEmail.class));
        }
        return super.onOptionsItemSelected(item);
    }

}