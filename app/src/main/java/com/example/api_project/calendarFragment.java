package com.example.api_project;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sothree.slidinguppanel.SlidingUpPanelLayout;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class calendarFragment extends Fragment implements View.OnClickListener, CalendarView.OnDateChangeListener {

    View v;
    SlidingUpPanelLayout slidingPanel;
    RelativeLayout topPanel, bottomPanel;
    LinearLayout subjectsList;
    CalendarView calendar;
    Button button;
    TextView current_date;
    int subj_id=0;

//    Api_connector api;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        api = new Api_connector("karas", "EgorKaras");

        v = inflater.inflate(R.layout.fragment_calendar, container, false);
        initialMainVars();
        return v;
    }


    private void initialMainVars(){
        slidingPanel = v.findViewById(R.id.slidingPanel);

        subjectsList = v.findViewById(R.id.subjectsList);

        bottomPanel = v.findViewById(R.id.bottomPanel);
        bottomPanel.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                try{
                    slidingPanel.setPanelState(SlidingUpPanelLayout.PanelState.HIDDEN);
                } catch (Exception e){
                    System.out.println("bottomPanel");
                    System.out.println(e.toString());
                }
                return true;
            }
        });

        current_date = v.findViewById(R.id.date);
        current_date.setOnClickListener(this);

        button = v.findViewById(R.id.button);



        topPanel = v.findViewById(R.id.topPanel);

        calendar = v.findViewById(R.id.calendarView);
        calendar.setOnDateChangeListener(this);
    }


    @Override
    public void onClick(View v) {
        try {
            slidingPanel.setPanelState(SlidingUpPanelLayout.PanelState.EXPANDED);
//            System.out.println(api.get_user_token());
//            SubjectGenerator sb = new SubjectGenerator(getLayoutInflater(), this.subjectsList, "Testing", subj_id);
//            subj_id += 1;
        } catch (Exception e) {
            System.out.println("button");
            System.out.println(e.toString());
        }
    }

    @Override
    public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
        try {
            String m = String.valueOf(month);
            String d = String.valueOf(dayOfMonth);

            if (month/10 == 0){
                m = "0" + month;
            }
            if (dayOfMonth/10 == 0){
                d = "0" + dayOfMonth;
            }

            String date = year + "-" + m + "-" + d;
            textView.setText(date);
//            textView.setText(String.valueOf(api.getTimetableDay(date)));
        } catch (Exception e){
            System.out.println("calendar");
            System.out.println(e.toString());
        }
    }
}