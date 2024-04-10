package com.example.api_project;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.textfield.TextInputEditText;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

public class profileFragment extends Fragment implements View.OnClickListener {
    boolean flag_connection;
    JSONObject data_user;
    View v;
    AppCompatButton logout, ch_login, ch_pass;
    TextInputEditText tf_name, tf_pass;
    ShapeableImageView pfp;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_profile, container, false);
        initialVars();
        try {
            setValues();
        } catch (JSONException e) {
            Toast.makeText(getContext(), "Проблема сервера. Попробуйте немного позже!", Toast.LENGTH_LONG).show();

            getActivity().finish();
        }
        return v;

    }
    private void setValues() throws JSONException {
        MainActivity.api.getUserInfo(new React(){
            @Override
            public void reaction(JSONObject data) throws JSONException {
                data_user = data.getJSONObject("response");
                flag_connection = true;
            }

            @Override
            public void FailedRequest(int status) {
                flag_connection = false;
            }
        });
        Api_connector.wait_state_connection(1000);
        System.out.println("");
        if(flag_connection){
            Picasso.get().load(data_user.getString("pfp")).into(pfp);

            tf_name.setText(data_user.getString("name_full"));
            tf_name.setEnabled(false);
        } else {
            Toast.makeText(getActivity(),
                    "Проблемы с подключением интернета", Toast.LENGTH_LONG).show();

        }
    }

    private void initialVars(){
        logout = v.findViewById(R.id.bLogout);
        logout.setOnClickListener(this);
        ch_login = v.findViewById(R.id.bChangeLogin);
        ch_login.setOnClickListener(this);
        ch_pass = v.findViewById(R.id.bChangePass);
        ch_pass.setOnClickListener(this);

        tf_name = v.findViewById(R.id.fstNameField);
        pfp = v.findViewById(R.id.profileImage);
//        tf_pass = v.findViewById(R.id.sndNameField);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        if (id==R.id.bLogout){
            Token token = new Token((AppCompatActivity) getActivity());
            token.removeToken();

            startActivity(new Intent(getActivity(), LoginActivity.class));
            getActivity().finish();
        }else if (id==R.id.bChangeLogin){
            Toast.makeText(getContext(), "Функция в разработке", Toast.LENGTH_LONG).show();
        }else if (id==R.id.bChangePass){
            Toast.makeText(getContext(), "Функция в разработке", Toast.LENGTH_LONG).show();
        }
    }
}