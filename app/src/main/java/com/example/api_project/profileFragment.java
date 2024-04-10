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

import com.google.android.material.textfield.TextInputEditText;

public class profileFragment extends Fragment implements View.OnClickListener {

    View v;
    AppCompatButton logout, ch_login, ch_pass;
    TextInputEditText tf_name, tf_pass;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_profile, container, false);
        initialVars();
        return v;
    }

    private void initialVars(){
        logout = v.findViewById(R.id.bLogout);
        logout.setOnClickListener(this);
        ch_login = v.findViewById(R.id.bChangeLogin);
        ch_login.setOnClickListener(this);
        ch_pass = v.findViewById(R.id.bChangePass);
        ch_pass.setOnClickListener(this);

        tf_name = v.findViewById(R.id.fstNameField);
        tf_pass = v.findViewById(R.id.sndNameField);
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

        }else if (id==R.id.bChangePass){

        }
    }
}