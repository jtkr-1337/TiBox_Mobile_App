package com.example.api_project;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;

public class WeekButton extends AppCompatButton {
    public int id;
    private String date;

    public WeekButton(@NonNull Context context) {
        super(context);
    }

    public WeekButton(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public WeekButton(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setDate(String date){
        this.date = date;
    }
    public String getDate(){
        return this.date;
    }
}
