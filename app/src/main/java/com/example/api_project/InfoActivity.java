package com.example.api_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class InfoActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject_info);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        TextView title = findViewById(R.id.title);
        TextView desc = findViewById(R.id.description);

        Button b = findViewById(R.id.back_btn);
        b.setOnClickListener(this);

        Intent args = getIntent();
        if (args!=null){
            String str_title = args.getStringExtra("title");
            String str_desc = args.getStringExtra("desc");

            title.setText(str_title);
            desc.setText(str_desc);
        } else{
            System.out.println("InfoActivityErrorArgs");
        }
    }

    @Override
    public void onClick(View v) {
        finish();
    }
}