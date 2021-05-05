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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class DriverMainActivity extends AppCompatActivity {

    Button logout;
    Button mapsView;
    FirebaseUser user;
    private TextView fullName;
    private String name;
    private String email;
    private TextView emailView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_main);

        logout = findViewById(R.id.logoutbutton);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext(),Login.class));
                finish();
            }
        });
        mapsView = findViewById(R.id.findRide);
        mapsView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),MapsActivity.class));
                finish();
            }
        });

        fullName = findViewById(R.id.DriverName);
        emailView = findViewById(R.id.DriverEmail);

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
        if(item.getItemId() == R.id.resetuserpassword){
            startActivity(new Intent(getApplicationContext(),ResetPassword.class));
        }
        if(item.getItemId() == R.id.updateCreditCard){
            startActivity(new Intent(getApplicationContext(),UpdateCreditCard.class));
        }
        if(item.getItemId() == R.id.updateEmailmenu){
            startActivity(new Intent(getApplicationContext(),UpdateEmail.class));
        }
        if(item.getItemId() == R.id.registerDriver){
            startActivity(new Intent(getApplicationContext(), RegisterDriver.class));
        }
        return super.onOptionsItemSelected(item);
    }

}