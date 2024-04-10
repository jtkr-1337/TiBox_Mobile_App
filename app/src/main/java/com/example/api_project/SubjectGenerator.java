package com.example.api_project;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.appcompat.widget.AppCompatButton;

public class SubjectGenerator {
    final int id_const = 133755668;
    int id_rl, id_lesson;
    RelativeLayout rl;
    AppCompatButton b;
    public SubjectGenerator(LayoutInflater l, View v, String t, int id, Activity a, String desc){
        l.inflate(R.layout.template_subject, (ViewGroup) v);
        rl = v.findViewById(R.id.subject);
        this.id_rl = id_const + id;
        this.id_lesson = id;
        rl.setId(this.id_rl);
        b = (AppCompatButton) rl.getChildAt(0);
        b.setHint(t);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(a, InfoActivity.class);
                i.putExtra("title", t);
                i.putExtra("desc", desc);
                a.startActivity(i);
            }
        });
    }
}

