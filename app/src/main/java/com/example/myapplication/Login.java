package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {
    Button createAccountBtn, loginBtn, forgetpasswordbtn;
    EditText username, password;
    FirebaseAuth fireAuth;
    AlertDialog.Builder reset;
    LayoutInflater inflater;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        createAccountBtn = findViewById(R.id.createbtn);
        createAccountBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Register.class));
            }
        });
        //text field
        username = findViewById(R.id.loginEmail);
        password = findViewById(R.id.loginpassword);

        //buttons
        loginBtn = findViewById(R.id.signinbtn);
        forgetpasswordbtn = findViewById(R.id.forgetPassword);
        //reset forgotten password
        reset = new AlertDialog.Builder(this);
        inflater = this.getLayoutInflater();
        //firebase user
        // added comment//
        //dialog for reset password
        forgetpasswordbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final View view = inflater.inflate(R.layout.reset_forgot,null);
                reset.setTitle("Reset because of Forgotten Password")
                        .setMessage("Enter email to get Your Password reset link")
                        .setPositiveButton("Reset", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                EditText email = view.findViewById(R.id.reset_email);
                                if(email.getText().toString().isEmpty()){
                                    email.setError("Email is Required");
                                }
                                fireAuth.sendPasswordResetEmail(email.getText().toString()).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Toast.makeText(Login.this,"Reset Password Email sent",Toast.LENGTH_SHORT).show();
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(Login.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        }).setNegativeButton("Cancel", null).
                        setView(view).
                        create().show();

            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(username.getText().toString().isEmpty()){
                    username.setError("Email Field is empty");
                }
                if(password.getText().toString().isEmpty()){
                    password.setError("Password Field is empty");
                }

                //login user
                fireAuth.signInWithEmailAndPassword(username.getText().toString(), password.getText().toString())
                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                //login successful
                                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                                finish();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Login.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });


    }

    //if user already registered and logged in before no need to keep asking them for info
    @Override
    protected void onStart(){
        super.onStart();
        if(FirebaseAuth.getInstance().getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
        }
    }
}