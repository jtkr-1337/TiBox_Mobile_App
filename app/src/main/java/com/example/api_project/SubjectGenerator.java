package com.example.api_project;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.appcompat.widget.AppCompatButton;

import com.sothree.slidinguppanel.SlidingUpPanelLayout;

public class SubjectGenerator {
    final int id_const = 133755668;
    int id;
    RelativeLayout rl;
    AppCompatButton b;
    public SubjectGenerator(LayoutInflater l, View v, String t, int id, Activity a){
        l.inflate(R.layout.template_subject, (ViewGroup) v);
        rl = v.findViewById(R.id.subject);
        this.id = id_const + id;
        rl.setId(this.id);
        b = (AppCompatButton) rl.getChildAt(0);
        b.setHint(t);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a.startActivity(new Intent(a, SubjectInfoActivity.class));
            }
        });
    }
}

