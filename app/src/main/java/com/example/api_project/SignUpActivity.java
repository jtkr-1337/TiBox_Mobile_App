package com.example.api_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    protected Api_connector api_con;
    TextInputEditText login, pass, re_pass;
    TextView login_error;
    Spinner inst, groups;
    Button signup;

    protected JSONArray inst_values;
    protected JSONObject groups_values;
    boolean api_status;
    private int id_group = -1, id_institute = -1;
    boolean flag_connection;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_registration);
        api_con = new Api_connector("","");

        initVars();
        loadsValues();
    }
    private void blockGroup(){
        ArrayList<String> initialList = new ArrayList<>();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, initialList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        groups.setAdapter(adapter);
        groups.setEnabled(false);
    }

    private int getId_institute(int id) throws JSONException {
        JSONObject institute_obj = inst_values.getJSONObject(id);
        return institute_obj.getInt("id");
    }

    private int getId_institute(String name) throws JSONException {
        for(int index = 0; index < inst_values.length(); index++) {
            if (inst_values.getJSONObject(index).getString("text").equals(name)) {
                JSONObject institute_obj = inst_values.getJSONObject(index);
                return institute_obj.getInt("id");
            }
        }
        return 0;
    }

    private int getId_group(int id) throws JSONException {
        JSONObject institute_obj = groups_values.getJSONArray(
                String.valueOf(id_institute)
        ).getJSONObject(id);
        return institute_obj.getInt("id");
    }

    private void setGroups(JSONArray temp_groups){
        ArrayList<String> initialList = new ArrayList<>();
        for (int index = 0; index < temp_groups.length(); index++) {
            try {
                initialList.add(temp_groups.getJSONObject(index).getString("text"));
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, initialList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        groups.setAdapter(adapter);
    }


    private void initVars() {
        login = findViewById(R.id.login);
        pass = findViewById(R.id.password);
        re_pass = findViewById(R.id.password2);

        login_error = findViewById(R.id.login_error);

        inst = findViewById(R.id.institutes);
        groups = findViewById(R.id.groups);

        signup = findViewById(R.id.signup_btn);
        signup.setOnClickListener(this);
        this.blockGroup();

        inst.setOnItemSelectedListener(this);
        groups.setOnItemSelectedListener(this);
    }


    private void loadsValues() {
        this.api_con.getGroupsInstitutes(new React(){
            @Override
            public void reaction(JSONObject data) throws JSONException {
                data = data.getJSONObject("response");
                inst_values = data.getJSONArray("institutes");
                groups_values = data.getJSONObject("groups");
                api_status = true;
                System.out.println("SignUpActivity end: " + System.currentTimeMillis());
            }

            @Override
            public void FailedRequest(int status){
                api_status = false;
            }
        });

        Api_connector.wait_state_connection(10000);
        System.out.println("- - - - >"+api_status);
        if (api_status){
            List<String> names = new ArrayList<>();
            ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            for (int index = 0; index < inst_values.length(); index++) {
                try {
                    names.add(inst_values.getJSONObject(index).getString("text"));
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
            adapter.addAll(names);
            inst.setAdapter(adapter);
        } else {
            Toast.makeText(getApplicationContext(),
                    "Регистрация Проблемы с подключением интернета", Toast.LENGTH_LONG).show();
            startActivity(new Intent(this, LoginActivity.class));
        }
        System.out.println("SignUpActivity end: " + System.currentTimeMillis());



    }

    @Override
    public void onClick(View v) {
        String l_txt = login.getText().toString();
        String p_txt = pass.getText().toString();


        if (checkFields()) {
            api_con.createNewUser(
                    l_txt, p_txt, String.valueOf(id_group), String.valueOf(id_institute),
                    new React(){
                        @Override
                        public void reaction(JSONObject data) throws JSONException {
                            flag_connection = true;
                        }

                        @Override
                        public void FailedRequest(int status) {
                            flag_connection = false;
                        }
                    });
            Api_connector.wait_state_connection(5000);
            System.out.println("a");

            if (flag_connection){
                Toast.makeText(getApplicationContext(),
                        "Регистрация прошла успешно!",
                        Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, LoginActivity.class));
            }else {
                Toast.makeText(getApplicationContext(),
                        "Проблемы с интернет соединением!\nПопробуйте снова.",
                        Toast.LENGTH_SHORT).show();
            }
            finish();
        }
    }

    private boolean checkFields() {

        String regex_login = "^[a-zA-Z0-9-*]{4,}";
        String regex_pass = "^\\b[A-Za-z0-9._!]{8,}\\b";

        Pattern pattern_login = Pattern.compile(regex_login);
        Pattern pattern_pass = Pattern.compile(regex_pass);

        String l_txt = login.getText().toString();
        String p_txt = pass.getText().toString();
        String re_p_txt = re_pass.getText().toString();
        boolean flag_to_trigger = (!pattern_login.matcher(l_txt).matches() || l_txt.isEmpty()) ||
                (!pattern_pass.matcher(p_txt).matches() || p_txt.isEmpty()) ||
                (!p_txt.equals(re_p_txt));
        String textMsg = "";
        if (!pattern_login.matcher(l_txt).matches() || l_txt.isEmpty()){
            textMsg += " - Логин должен содержать только буквы латинского алфавита, цифры, тире и звёздочку и не должен быть короче 8 символов\n";
        }else if (!pattern_pass.matcher(p_txt).matches() || p_txt.isEmpty()){
            textMsg += " - Пароль должен содержать только буквы латинского алфавита, цифры, точку, нижнее подчеркивание, восклицательный знак и не должен быть короче 8 символов";
        }else if (!p_txt.equals(re_p_txt)){
            textMsg += " - \"Пароль\" должен совпадать с \"Повторный пароль\"\n";
        }else if (id_group == -1){
            textMsg += " - Выберите институт и группу";
        }
        if (flag_to_trigger)
            Toast.makeText(getApplicationContext(), textMsg, Toast.LENGTH_SHORT).show();
        login_error.setText(textMsg);
        login_error.setVisibility(View.VISIBLE);
        return !flag_to_trigger;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (parent.getId() == R.id.institutes) {
            try {
                id_group = -1;
                setGroups(groups_values.getJSONArray(
                        String.valueOf(getId_institute(position))));
                id_institute = getId_institute(position);
                groups.setEnabled(true);
                System.out.println("Institute id ->"+ id_institute);
            } catch (JSONException e) {
                System.out.println("- - - - >"+e.toString());
            }
        } else if (parent.getId() == R.id.groups) {
            try {
                id_group = getId_group(position);
                System.out.println("Group id ->"+ id_group);
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}