package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class UpdateEmail extends AppCompatActivity {

    EditText usernewEmail, userconfirmEmail;
    Button updatebtn;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_email);

        usernewEmail = findViewById(R.id.email_conf1);
        userconfirmEmail = findViewById(R.id.email_conf2);
        updatebtn = findViewById(R.id.updatebtn);
        user = FirebaseAuth.getInstance().getCurrentUser();


        updatebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(usernewEmail.getText().toString().isEmpty()){
                    usernewEmail.setError("Required to update Email");
                }
                if(userconfirmEmail.getText().toString().isEmpty()){
                    userconfirmEmail.setError("Required to update Email");
                }
                if(!usernewEmail.getText().toString().equals(userconfirmEmail.getText().toString())){
                    userconfirmEmail.setError("Email Does Not Match");
                }

                DatabaseHelper db = new DatabaseHelper(UpdateEmail.this, 4);
                boolean success = db.updateEmail(SharedDBProperties.sharedUser,usernewEmail.getText().toString());
                if(success) {
                    Toast.makeText(UpdateEmail.this, "User Email Updated", Toast.LENGTH_SHORT).show();
                    finish();
                }
                else{
                    Toast.makeText(UpdateEmail.this, "Update Email Failed", Toast.LENGTH_SHORT).show();
                }

                user.updateEmail(usernewEmail.getText().toString()).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(UpdateEmail.this, "Email Updated",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(),Login.class));
                        finish();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(UpdateEmail.this,e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
    }
}