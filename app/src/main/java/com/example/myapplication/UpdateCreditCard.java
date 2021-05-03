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

public class UpdateCreditCard extends AppCompatActivity {

    EditText usernewCC, userconfirmCC;
    Button updatebtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_credit_card);

        usernewCC = findViewById(R.id.cc_conf1);
        userconfirmCC = findViewById(R.id.cc_conf2);
        updatebtn = findViewById(R.id.updatebtn);


        updatebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(usernewCC.getText().toString().isEmpty()){
                    usernewCC.setError("Required to update Credit Card");
                }
                if(userconfirmCC.getText().toString().isEmpty()){
                    userconfirmCC.setError("Required to update Credit Card");
                }
                if(!usernewCC.getText().toString().equals(userconfirmCC.getText().toString())){
                    userconfirmCC.setError("Credit Card Number Does Not Match");
                }

                DatabaseHelper db = new DatabaseHelper(UpdateCreditCard.this, 3);
                boolean success = db.updateCreditCard(SharedDBProperties.sharedUser,usernewCC.getText().toString());
                if(success) {
                    Toast.makeText(UpdateCreditCard.this, "User Credit Card Updated", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(),MainActivity.class));
                    finish();
                }
                else{
                    Toast.makeText(UpdateCreditCard.this, "Update Credit Card Failed", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}