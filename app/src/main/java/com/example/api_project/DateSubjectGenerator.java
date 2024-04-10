package com.example.api_project;

import static android.app.PendingIntent.getActivity;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sothree.slidinguppanel.SlidingUpPanelLayout;

public class DateSubjectGenerator implements View.OnClickListener {
    final int id_const = 13371111;
    int id;
    Activity a;
    RelativeLayout rl;
    LinearLayout leftPanel, centerPanel;
    TextView cab, time, prof, subj;
    SlidingUpPanelLayout panel;
    public DateSubjectGenerator(LayoutInflater l, View v, SlidingUpPanelLayout p, Activity a, int id, String str_cab, String str_time, String str_prof, String str_name){
        this.a = a;
        panel = p;
        l.inflate(R.layout.template_subject_calendar, (ViewGroup) v);
        rl = v.findViewById(R.id.subject);
        this.id = id_const + id;
        rl.setId(this.id);

        leftPanel = (LinearLayout) rl.getChildAt(0);
        leftPanel.setOnClickListener(this);

        cab = (TextView) leftPanel.getChildAt(0);
        if (str_cab.length() > 8){
            cab.setText(str_cab.substring(0,9));
        }else {
            cab.setText(str_cab);
        }
        cab.setOnClickListener(this);
        time = (TextView) leftPanel.getChildAt(1);
        time.setText(str_time);
        time.setOnClickListener(this);


        centerPanel = (LinearLayout) rl.getChildAt(1);
        centerPanel.setOnClickListener(this);

        subj = (TextView) centerPanel.getChildAt(0);
        subj.setText(str_name);
        subj.setOnClickListener(this);
        prof = (TextView) centerPanel.getChildAt(1);
        prof.setText(str_prof);
        leftPanel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (panel.getPanelState() == SlidingUpPanelLayout.PanelState.HIDDEN) {
            Intent i = new Intent(this.a, InfoActivity.class);
            i.putExtra("title", subj.getText().toString());
            String desc = "Преподаватель: " + prof.getText().toString() + "\n"
                    + "Аудитория: " + cab.getText().toString();
            i.putExtra("desc", desc);
            this.a.startActivity(i);
        } else{
            panel.setPanelState(SlidingUpPanelLayout.PanelState.HIDDEN);
        }
    }
}

