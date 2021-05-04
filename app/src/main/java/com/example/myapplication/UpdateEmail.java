package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class UpdateEmail extends AppCompatActivity {

    EditText usernewEmail, userconfirmEmail;
    Button updatebtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_email);

        usernewEmail = findViewById(R.id.email_conf1);
        userconfirmEmail = findViewById(R.id.email_conf2);
        updatebtn = findViewById(R.id.updatebtn);


        updatebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(usernewEmail.getText().toString().isEmpty()){
                    usernewEmail.setError("Required to update Credit Card");
                }
                if(userconfirmEmail.getText().toString().isEmpty()){
                    userconfirmEmail.setError("Required to update Credit Card");
                }
                if(!usernewEmail.getText().toString().equals(userconfirmEmail.getText().toString())){
                    userconfirmEmail.setError("Credit Card Number Does Not Match");
                }

                DatabaseHelper db = new DatabaseHelper(UpdateEmail.this, 3);
                boolean success = db.updateEmail(SharedDBProperties.sharedUser,usernewEmail.getText().toString());
                if(success) {
                    Toast.makeText(UpdateEmail.this, "User Email Updated", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(),MainActivity.class));
                    finish();
                }
                else{
                    Toast.makeText(UpdateEmail.this, "Update Email Failed", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}