package com.example.api_project;

import static android.app.PendingIntent.getActivity;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
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
    public DateSubjectGenerator(LayoutInflater l, View v, int id, SlidingUpPanelLayout p, Activity a){
        this.a = a;
        panel = p;
        l.inflate(R.layout.template_subject_calendar, (ViewGroup) v);
        rl = v.findViewById(R.id.subject);
        this.id = id_const + id;
        rl.setId(this.id);

        setText();

        leftPanel = (LinearLayout) rl.getChildAt(0);
        leftPanel.setOnClickListener(this);
        cab = (TextView) leftPanel.getChildAt(0);
        cab.setOnClickListener(this);
        time = (TextView) leftPanel.getChildAt(1);
        time.setOnClickListener(this);

        centerPanel = (LinearLayout) rl.getChildAt(1);
        centerPanel.setOnClickListener(this);
        subj = (TextView) centerPanel.getChildAt(0);
        subj.setOnClickListener(this);
        prof = (TextView) centerPanel.getChildAt(1);
        leftPanel.setOnClickListener(this);
    }

    private void setText(){

    }

    @Override
    public void onClick(View v) {
        if (panel.getPanelState() == SlidingUpPanelLayout.PanelState.HIDDEN) {
            this.a.startActivity(new Intent(this.a, SubjectInfoActivity.class));
        } else{
            panel.setPanelState(SlidingUpPanelLayout.PanelState.HIDDEN);
        }
    }
}

