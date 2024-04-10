package com.example.api_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

import com.google.android.material.textfield.TextInputLayout;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {

    TextInputLayout login, pass, re_pass;
    Spinner inst, groups;
    Button signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        initVars();

    }

    private void initVars() {
        login = findViewById(R.id.login);
        pass = findViewById(R.id.password);
        re_pass = findViewById(R.id.password2);

        inst = findViewById(R.id.institutes);
        groups = findViewById(R.id.groups);

        signup = findViewById(R.id.signup_btn);
        signup.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (checkFields()) {
            finish();
        }
    }

    private boolean checkFields() {
        return true;
    }
}