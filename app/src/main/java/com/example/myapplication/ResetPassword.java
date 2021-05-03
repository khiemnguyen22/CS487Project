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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ResetPassword extends AppCompatActivity {
    EditText usernewPassword, userconfirmpassword;
    Button resetPassword;
    FirebaseUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        usernewPassword = findViewById(R.id.passconf1);
        userconfirmpassword = findViewById(R.id.passconf2);
        resetPassword = findViewById(R.id.resetpasswordbtn);
        user = FirebaseAuth.getInstance().getCurrentUser();

        resetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(usernewPassword.getText().toString().isEmpty()){
                    usernewPassword.setError("Required for reset password");
                }
                if(userconfirmpassword.getText().toString().isEmpty()){
                    userconfirmpassword.setError("Required to reset password");
                }
                if(!usernewPassword.getText().toString().equals(userconfirmpassword.getText().toString())){
                    userconfirmpassword.setError("Password Does Not Match");
                }

                DatabaseHelper db = new DatabaseHelper(ResetPassword.this, 3);
                boolean success = db.resetPassword(SharedDBProperties.sharedUser,usernewPassword.getText().toString());
                if(success) {
                    Toast.makeText(ResetPassword.this, "User password reset", Toast.LENGTH_SHORT).show();
                }
                user.updatePassword(usernewPassword.getText().toString()).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(ResetPassword.this, "Password Updated",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        finish();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(ResetPassword.this,e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
    }
}