package com.example.api_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SubjectInfoActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject_info);

        Button b = findViewById(R.id.back_btn);
        b.setOnClickListener(this);

        int id = getIntent().getExtras().getInt("id_lesson");
    }

    @Override
    public void onClick(View v) {
        finish();
    }
}