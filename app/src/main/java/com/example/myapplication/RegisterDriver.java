package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterDriver extends AppCompatActivity {

    EditText registerMake, registerModel, registerLicense, registerYear;
    Button registerDriverButton;
    FirebaseAuth fireAuth;
    User u;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_driver);

        //EditText in Register Driver xml
        registerMake = findViewById(R.id.makeRegister);
        registerModel = findViewById(R.id.modelReg);
        registerLicense = findViewById(R.id.licenseReg);
        registerYear = findViewById(R.id.yearRegister);

        //Button in Register Driver xml
        registerDriverButton = findViewById(R.id.button3);
        registerDriverButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String make = registerMake.getText().toString();
                String model = registerModel.getText().toString();
                String year = registerYear.getText().toString();
                String license = registerLicense.getText().toString();


                //Check if fields are empty before registering is available

                if (make.isEmpty()) {
                    registerMake.setError("Car's Make is required");
                }

                if (model.isEmpty()) {
                    registerModel.setError("Car's Model is required");
                }

                if (year.isEmpty()) {
                    registerYear.setError("Car's Year is required");
                }

                if (license.isEmpty()) {
                    registerLicense.setError("Car's License Number is required");
                }

                //if data is valid
                Toast.makeText(RegisterDriver.this,"Data is Valid",Toast.LENGTH_SHORT).show();

                //update Driver
                SharedDBProperties.sharedDriver.setMake(make);
                SharedDBProperties.sharedDriver.setModel(model);
                SharedDBProperties.sharedDriver.setYear(year);
                SharedDBProperties.sharedDriver.setLiscensePlate(license);

                DatabaseHelper databaseHelper= new DatabaseHelper(RegisterDriver.this, 4);

                boolean success = databaseHelper.registerDriver(SharedDBProperties.sharedDriver,make, license, model, year);
                if(success) {
                    Toast.makeText(RegisterDriver.this, "Driver registered", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(),DriverMainActivity.class));
                }
                else{
                    Toast.makeText(RegisterDriver.this, "Registration failed", Toast.LENGTH_SHORT).show();
                }
                // update current user

            }
        });


    }
}
