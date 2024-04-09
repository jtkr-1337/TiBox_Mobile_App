package com.example.api_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class CalendarActivity extends AppCompatActivity implements View.OnClickListener, CalendarView.OnDateChangeListener {

    RelativeLayout mainPanel, widgetPanel, topPanel, bottomPanel;
    CalendarView calendar;
    Button button;
    TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        initialMainVars();
//        getLayoutInflater().inflate(R.layout.tabbar_layout, widgetPanel);
//        TabBar.tabbarSetting(this);
    }

    private void initialMainVars(){
        mainPanel = findViewById(R.id.mainPanel);
        widgetPanel = findViewById(R.id.widgetPanel);

        textView = findViewById(R.id.textView);

        button = findViewById(R.id.button);
        button.setOnClickListener(this);

    }
    private void initialCalendarVars(){
        calendar = findViewById(R.id.calendarView);
        calendar.setOnDateChangeListener(this);

        topPanel = findViewById(R.id.topPanel);
        bottomPanel = findViewById(R.id.bottomPanel);

        bottomPanel.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                try{
                    widgetPanel.removeAllViews();
                } catch (Exception e){
                    System.out.println("bottomPanel");
                    System.out.println(e.toString());
                }
               return true;
            }
        });
    }

    @Override
    public void onClick(View v) {
        try {
            getLayoutInflater().inflate(R.layout.widget_calendar_old, widgetPanel);
            initialCalendarVars();
        } catch (Exception e) {
            System.out.println("button");
            System.out.println(e.toString());
        }
    }

    @Override
    public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
        try {
            textView.setText(year + "/" + month + "/" + dayOfMonth);
        } catch (Exception e){
            System.out.println("calendar");
            System.out.println(e.toString());
        }
    }

}