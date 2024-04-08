package com.example.api_project;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.CalendarView;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.Navigation;


//<include layout="@layout/tabbar_layout"/>

public class TabBar {
    static Drawable d;
    static ImageButton settings, subjects, calen, profile;

    public static void tabbarSetting(AppCompatActivity a){
        settings = a.findViewById(R.id.settingsButton);
        subjects = a.findViewById(R.id.subjectsButton);
        calen = a.findViewById(R.id.calendarButton);
        profile = a.findViewById(R.id.profileButton);

        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                d = a.getResources().getDrawable(R.drawable.settings_button_on);
                settings.setBackground(d);

                d = a.getResources().getDrawable(R.drawable.subjects_button_off);
                subjects.setBackground(d);

                d = a.getResources().getDrawable(R.drawable.calend_button_off);
                calen.setBackground(d);

                d = a.getResources().getDrawable(R.drawable.prof_button_off);
                profile.setBackground(d);

                if (a.equals(CalendarActivity.class)) {
                    Navigation.findNavController(v).navigate(R.id.action_calendarActivity_to_mainActivity);
//                    Intent intent = new Intent(a, MainActivity.class);
//                    a.startActivity(intent);
                } else if(a.equals(ProfileActivity.class)){
                    Navigation.findNavController(v).navigate(R.id.action_profileActivity_to_mainActivity);
                }
            }
        });

        subjects.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                d = a.getResources().getDrawable(R.drawable.settings_button_off);
                settings.setBackground(d);

                d = a.getResources().getDrawable(R.drawable.subjects_button_on);
                subjects.setBackground(d);

                d = a.getResources().getDrawable(R.drawable.calend_button_off);
                calen.setBackground(d);

                d = a.getResources().getDrawable(R.drawable.prof_button_off);
                profile.setBackground(d);

//                Intent intent = new Intent(a, SubjectsActivity.class);
//                a.startActivity(intent);
            }
        });

        calen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                d = a.getResources().getDrawable(R.drawable.settings_button_off);
                settings.setBackground(d);

                d = a.getResources().getDrawable(R.drawable.subjects_button_off);
                subjects.setBackground(d);

                d = a.getResources().getDrawable(R.drawable.calend_button_on);
                calen.setBackground(d);

                d = a.getResources().getDrawable(R.drawable.prof_button_off);
                profile.setBackground(d);

                if (!a.equals(CalendarActivity.class)) {
                    Intent intent = new Intent(a, CalendarActivity.class);
                    a.startActivity(intent);
                }
            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                d = a.getResources().getDrawable(R.drawable.settings_button_off);
                settings.setBackground(d);

                d = a.getResources().getDrawable(R.drawable.subjects_button_off);
                subjects.setBackground(d);

                d = a.getResources().getDrawable(R.drawable.calend_button_off);
                calen.setBackground(d);

                d = a.getResources().getDrawable(R.drawable.prof_button_on);
                profile.setBackground(d);

                if (!a.equals(ProfileActivity.class)) {
                    Intent intent = new Intent(a, ProfileActivity.class);
                    a.startActivity(intent);
                }
            }
        });}
}
