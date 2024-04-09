package com.example.api_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    Button login_btn, login_with_tg_btn;
    TextView forget_pass, signup_btn, login_error, pass_error;
    TextInputEditText tf_login, tf_pass;
    public static boolean logged_in;
    Api_connector api;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initalVars();
        if (!logged_in){
            setMethodsToButtons();
        }else {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }

    }

    private boolean checkLogin() {
        String regex_login = "^[a-zA-Z0-9-*]{16}\\b";
        String regex_pass = "^\\b[A-Za-z0-9._!]{8,}\\b";

        Pattern pattern_login = Pattern.compile(regex_login);
        Pattern pattern_pass = Pattern.compile(regex_pass);

        String l_txt = tf_login.getText().toString();
        String p_txt = tf_pass.getText().toString();
        if (!pattern_login.matcher(l_txt).matches()){
            login_error.setText("Логин должен содержать только буквы латинского алфавита, цифры и не должен быть длинее 16 символов");
            login_error.setVisibility(View.VISIBLE);
            return false;
        }else if (!pattern_pass.matcher(l_txt).matches()){
            pass_error.setText("Пароль должен содержать только буквы латинского алфавита, цифры и не должен быть короче 8 символов");
            pass_error.setVisibility(View.VISIBLE);
            return false;
        }else{
            return true;
        }
    }

    private void initalVars(){
        login_btn = findViewById(R.id.login_btn);
        login_with_tg_btn = findViewById(R.id.login_tg_btn);

        forget_pass = findViewById(R.id.forget_pass);
        signup_btn = findViewById(R.id.signup_btn);

        login_error = findViewById(R.id.login_error);
        pass_error = findViewById(R.id.pass_error);

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
        if (checkLogin()) {
            api = new Api_connector(tf_login.getText().toString(), tf_pass.getText().toString());
            Api_connector.wait_state_connection(5000);
            String token = api.get_user_token();
            if (!token.isEmpty() && token != null) {
                ApiMover api_mover = new ApiMover(api);
                Intent i = new Intent(this, MainActivity.class);
                i.putExtra("api", api_mover);
                startActivity(i);
                logged_in = true;
                finish();
            }
        }
    }
}