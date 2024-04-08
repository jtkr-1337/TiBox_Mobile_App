package com.example.api_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    Button login_btn, login_with_tg_btn;
    TextView forget_pass, signup_btn;
    TextInputEditText tf_login, tf_pass;
    public static boolean login;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Log.i("ERROR", String.valueOf(login));
        initalVars();
        if (!login){
            setMethodsToButtons();
        }else {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }

    }

    private void checkLogin() {
        String login_txt = tf_login.getText().toString();
        String pass_txt = tf_pass.getText().toString();
        Log.i("ERROR", login_txt);
        Log.i("ERROR", pass_txt);
        if (!login_txt.isEmpty() && !pass_txt.isEmpty()) {
            login = true;
        } else{
            login = false;
        }
    }

    private void initalVars(){
        login_btn = findViewById(R.id.login_btn);
        login_with_tg_btn = findViewById(R.id.login_tg_btn);

        forget_pass = findViewById(R.id.forget_pass);
        signup_btn = findViewById(R.id.signup_btn);

        tf_login = findViewById(R.id.login);
        tf_pass = findViewById(R.id.pass);
    }
    private void setMethodsToButtons(){
        login_btn.setOnClickListener(this);
        login_with_tg_btn.setOnClickListener(this);
        forget_pass.setOnClickListener(this);
        signup_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        if (id == R.id.login_btn){
            login();
        }else if (id == R.id.login_tg_btn){

        }else if (id == R.id.forget_pass){

        }else if (id == R.id.signup_btn){
            startActivity(new Intent(this, SignUpActivity.class));
        }
    }

    private void login() {
        checkLogin();
        if (login) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
    }
}