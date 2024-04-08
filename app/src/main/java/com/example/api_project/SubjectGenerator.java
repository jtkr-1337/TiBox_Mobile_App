package com.example.api_project;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.appcompat.widget.AppCompatButton;

public class SubjectGenerator {
    final int id_const = 133755668;
    int id_b, id_l;
    RelativeLayout rl;
    AppCompatButton b;
    public SubjectGenerator(LayoutInflater l, View v, String t, int id){
        l.inflate(R.layout.template_subject, (ViewGroup) v);
        rl = v.findViewById(R.id.subject);
        this.id_l = id_const*10 + id;
        rl.setId(id_l);
        this.id_b = id_const + id;
        rl.setId(this.id_b);
        b = (AppCompatButton) rl.getChildAt(0);
        b.setHint(t);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b.setHint("Help me");
            }
        });
    }
}
