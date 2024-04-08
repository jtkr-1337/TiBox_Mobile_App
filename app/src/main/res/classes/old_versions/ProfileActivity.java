package com.example.api_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.RelativeLayout;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        RelativeLayout mainLayout = findViewById(R.id.mainLayout);

        getLayoutInflater().inflate(R.layout.tabbar_layout, mainLayout);
        TabBar.tabbarSetting(this);
    }
}