package com.example.api_project;

import android.content.Context;
import android.icu.util.Calendar;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.content.ContextCompat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class WeekButton extends AppCompatButton {
    public int id;
    private String date;
    public calendarFragment parent;
    private boolean status, current = false;

    public WeekButton(@NonNull Context context) {
        super(context);
    }

    public WeekButton(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public WeekButton(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setStatus(boolean s){
        status = s;
        if (status){
            this.setBackground(AppCompatResources.getDrawable(getContext(), R.drawable.round_button2));
            this.setTextColor(ContextCompat.getColor(getContext(), R.color.white));
        }else if (current){
            this.setBackground(AppCompatResources.getDrawable(getContext(), R.drawable.round_button3));
            this.setTextColor(ContextCompat.getColor(getContext(), R.color.button_current));
        }else {
            this.setBackground(AppCompatResources.getDrawable(getContext(), R.drawable.round_button1));
            this.setTextColor(ContextCompat.getColor(getContext(), R.color.black));
        }
    }
    public void setCurrent(boolean s){
        current = s;
    }
    public void setDate(String date){
        this.date = date;
    }
    public String getDate(){
        return this.date;
    }
    public String getAltDate(){
        String[] weeks = parent.createWeekList();
        String[] months = parent.createMonthsList();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date d = new Date();
        try {
            d = sdf.parse(this.date);
        } catch (ParseException e) {
            System.out.println("WeekButttonDateParseError: " + e.toString());
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);

        return weeks[dayOfWeek-1] + ", " + dayOfMonth + " " + months[month] + " " + year;
    }
}
