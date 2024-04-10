package com.example.api_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    Button login_btn, login_with_tg_btn;
    TextView forget_pass, signup_btn, login_error, pass_error;
    TextInputEditText tf_login, tf_pass;
    boolean api_status;
    Api_connector api;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        String token = sharedPref.getString("user_token", null);

        initalVars();

        if (token == null){
            setMethodsToButtons();
        }else {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }

    }

    private boolean checkLogin() {
        String regex_login = "^[a-zA-Z0-9-*]+";
//        String regex_pass = "^\\b[A-Za-z0-9._!]{8,}\\b";
        String regex_pass = "^[a-zA-Z0-9-*]+";

        Pattern pattern_login = Pattern.compile(regex_login);
        Pattern pattern_pass = Pattern.compile(regex_pass);

        String l_txt = tf_login.getText().toString();
        String p_txt = tf_pass.getText().toString();
        if (!pattern_login.matcher(l_txt).matches() || l_txt.isEmpty()){
            login_error.setText("Логин должен содержать только буквы латинского алфавита, цифры и не должен быть длинее 16 символов");
            login_error.setVisibility(View.VISIBLE);
            return false;
        }else if (!pattern_pass.matcher(l_txt).matches() || p_txt.isEmpty()){
            login_error.setVisibility(View.INVISIBLE);
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
            api.getAuthToken(new React(){
                @Override
                public void reaction(JSONObject data) throws JSONException { // это значит всё успешно завершилось
                    api.user_token = data.getJSONObject("response").getString("auth_token");
                    System.out.println("Пользователь найден");
                    api_status = true;
                }

                @Override
                public void FailedRequest(int status){
                    System.out.println("Не удалось найти пользователя");
                    api_status = false;
                }
            });
            Api_connector.wait_state_connection(10000);
            if (this.api_status){
                startMainActivity();
            } else{
                showError();
            }
        }
    }
    private void startMainActivity() {
        SharedPreferences settings = this.getSharedPreferences("TiBox_Storage", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("user_token", api.get_user_token());
        editor.apply();

        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
    private void showError(){
        pass_error.setText("Не удалось найти пользователя");
        pass_error.setVisibility(View.VISIBLE);
    }
}