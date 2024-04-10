package com.example.api_project;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


public class settingsFragment extends Fragment implements View.OnClickListener {
    View v;
    AppCompatButton theme, lang, help, about_app, about_us;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_settings, container, false);

        theme = v.findViewById(R.id.theme);
        lang = v.findViewById(R.id.lang);
        help = v.findViewById(R.id.help);
        about_app = v.findViewById(R.id.about_app);
        about_us = v.findViewById(R.id.about_us);

        theme.setOnClickListener(this);
        help.setOnClickListener(this);
        lang.setOnClickListener(this);
        about_app.setOnClickListener(this);
        about_us.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.theme){
            Toast.makeText(getContext(), "Функция в разработке", Toast.LENGTH_LONG).show();
        }else if (id == R.id.lang){
            Toast.makeText(getContext(), "Функция в разработке", Toast.LENGTH_LONG).show();
        }else if (id == R.id.help){
            Intent i = new Intent(getActivity(), InfoActivity.class);
            i.putExtra("title", "Помощь");
            i.putExtra("desc", getResources().getString(R.string.help));
            startActivity(i);
        }else if (id == R.id.about_app){
            Intent i = new Intent(getActivity(), InfoActivity.class);
            i.putExtra("title", "О приложении");
            i.putExtra("desc", getResources().getString(R.string.about_app));
            startActivity(i);
        }else if (id == R.id.about_us){
            Intent i = new Intent(getActivity(), InfoActivity.class);
            i.putExtra("title", "О команде");
            i.putExtra("desc", getResources().getString(R.string.about_us));
            startActivity(i);
        }
    }
}