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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Login extends AppCompatActivity {
    Button createAccountBtn, loginBtn, forgetpasswordbtn, driverLoginBtn;
    EditText username, password;
    FirebaseAuth fireAuth;
    AlertDialog.Builder reset;
    LayoutInflater inflater;
    Driver d;
    User u;
    boolean flag = true;


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
        username = (EditText) findViewById(R.id.loginEmail);
        password = (EditText) findViewById(R.id.loginpassword);

        //buttons
        loginBtn = (Button) findViewById(R.id.signinbtn);
        driverLoginBtn = (Button) findViewById(R.id.driverSigninbtn);
        forgetpasswordbtn = findViewById(R.id.forgetPassword);
        //reset forgotten password
        reset = new AlertDialog.Builder(this);
        inflater = this.getLayoutInflater();
        // added comment//
        //dialog for reset password
        fireAuth = FirebaseAuth.getInstance();
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

                DatabaseHelper db = new DatabaseHelper(Login.this, 4);
                User loginUser = db.returnUser(username.getText().toString(), password.getText().toString());
                SharedDBProperties.sharedUser.setEmail(loginUser.getEmail());
                SharedDBProperties.sharedUser.setPassword(loginUser.getPassword());
                SharedDBProperties.sharedUser.setFirstName(loginUser.getFirstName());
                SharedDBProperties.sharedUser.setLastName(loginUser.getLastName());
                SharedDBProperties.sharedUser.setPassword(loginUser.getPassword());

                fireAuth = FirebaseAuth.getInstance();
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
                        flag= false;
                    }
                });
            }
        });

        driverLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (username.getText().toString().isEmpty()) {
                    username.setError("Email Field is empty");
                }
                if (password.getText().toString().isEmpty()) {
                    password.setError("Password Field is empty");
                }
                final String email = username.getText().toString();
                final String password1 = password.getText().toString();


                DatabaseHelper databaseHelper = new DatabaseHelper(Login.this, 4);

                Toast.makeText(Login.this, "Data is Valid", Toast.LENGTH_SHORT).show();
                try {
                    u = databaseHelper.returnUser(email, password1);
                    d = new Driver(u.getID(), u.getPassword(), u.getEmail(), u.getFirstName(), u.getLastName());
                } catch (Exception e) {
                    Toast.makeText(Login.this, "Error", Toast.LENGTH_SHORT).show();
                }
                //update to current user
                SharedDBProperties.sharedDriver.setEmail(u.getEmail());
                SharedDBProperties.sharedDriver.setPassword(u.getPassword());
                SharedDBProperties.sharedDriver.setFirstName(u.getFirstName());
                SharedDBProperties.sharedDriver.setLastName(u.getLastName());
                SharedDBProperties.sharedDriver.setPassword(u.getPassword());

                boolean success = databaseHelper.addDriver(d);
                    if (success) {
                        Toast.makeText(Login.this, "Success", Toast.LENGTH_SHORT).show();
                    }
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