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


public class Register extends AppCompatActivity {
    EditText registerfirstname, registerlastname, registeremail, registerpassword, registerconfirmpass;
    Button registerUserButton, registerloginUserButton;
    FirebaseAuth fireAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        //EditText in Register xml
        registerfirstname = findViewById(R.id.firstNameReg);
        registerlastname = findViewById(R.id.lastnameReg);
        registeremail = findViewById(R.id.emailRegister);
        registerpassword = findViewById(R.id.passwordRegister);
        registerconfirmpass = findViewById(R.id.confpassRegister);

        //Button in Register xml
        registerUserButton = findViewById(R.id.button3);
        registerloginUserButton = findViewById(R.id.loginReg);

        //firebaseAuth
        fireAuth = FirebaseAuth.getInstance();

        //if already registered press login in button to go to login screen
        registerloginUserButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Login.class));
                finish();
            }
        });

        registerUserButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String firstName = registerfirstname.getText().toString();
                String lastName = registerlastname.getText().toString();
                String email = registeremail.getText().toString();
                String password = registerpassword.getText().toString();
                String confpassword = registerconfirmpass.getText().toString();

                //Check if fields are empty before registering is available

                if (firstName.isEmpty()) {
                    registerfirstname.setError("First Name is required");
                }

                if (lastName.isEmpty()) {
                    registerlastname.setError("Last Name is required");
                }

                if (email.isEmpty()) {
                    registeremail.setError("Email is required");
                }

                if (password.isEmpty()) {
                    registerpassword.setError("Password is required");
                }

                if (confpassword.isEmpty()) {
                    registerconfirmpass.setError("Please confirm password");
                }

                if (!password.equals(confpassword)) {
                    registerconfirmpass.setError("Passwords Do Not Match");
                }

                //if data is valid
                Toast.makeText(Register.this,"Data is Valid",Toast.LENGTH_SHORT).show();

                //firebase create user with email and password
                fireAuth.createUserWithEmailAndPassword(email, password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        finish();

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        //show error if authentication failed
                        Toast.makeText(Register.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });


    }
}
