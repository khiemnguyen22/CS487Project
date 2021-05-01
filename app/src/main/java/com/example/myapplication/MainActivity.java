package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    @Override
    // TODO: add Onclick to buttons

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView forgotTextView = findViewById(R.id.forgotUsername);
        TextView createAccountView = findViewById(R.id.firstTime);

        String forgotText = "Forgot Username? Forgot Password? ";
        String createAccount = "First time? Create an Account";

        SpannableString ss1 = new SpannableString(forgotText);
        SpannableString ss2 = new SpannableString(createAccount);

        ClickableSpan cs1 = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                Toast.makeText(MainActivity.this, "Forgot username", Toast.LENGTH_SHORT).show();
            }
            public void updateDrawState(@NonNull TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(Color.BLUE);
            }
        };
        ClickableSpan cs2 = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                Toast.makeText(MainActivity.this, "Forgot password", Toast.LENGTH_SHORT).show();
            }
            public void updateDrawState(@NonNull TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(Color.BLUE);
            }
        };
        ClickableSpan cs3 = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                Toast.makeText(MainActivity.this, "Create an account", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void updateDrawState(@NonNull TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(Color.BLUE);
            }
        };
        ss1.setSpan(cs1,0,16, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss1.setSpan(cs2,17,33, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss2.setSpan(cs3,12,29, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        forgotTextView.setText(ss1);
        forgotTextView.setMovementMethod(LinkMovementMethod.getInstance());

        createAccountView.setText(ss2);
        createAccountView.setMovementMethod(LinkMovementMethod.getInstance());
    }

}
