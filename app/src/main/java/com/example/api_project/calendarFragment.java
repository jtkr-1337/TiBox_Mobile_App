package com.example.api_project;

import android.icu.util.Calendar;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sothree.slidinguppanel.SlidingUpPanelLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;


public class calendarFragment extends Fragment implements View.OnClickListener, CalendarView.OnDateChangeListener {

    View v;
    SlidingUpPanelLayout slidingPanel;
    RelativeLayout topPanel, bottomPanel;
    LinearLayout subjectsListLayout;
    CalendarView calendar;
    TextView current_date, error, week;
    WeekButton[] days_of_week_buttons = new WeekButton[7];
    DateSubjectGenerator[] subjects_list = new DateSubjectGenerator[0];

    String date;
    JSONArray lessons;
    boolean api_status;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_calendar, container, false);
        try {
            initialMainVars();
        } catch (JSONException | ParseException e) {
            System.out.println("CalendarJSONError: " + e.toString());
        }
        return v;
    }

    private void initialMainVars() throws JSONException, ParseException {
        slidingPanel = v.findViewById(R.id.slidingPanel);
        slidingPanel.setPanelState(SlidingUpPanelLayout.PanelState.HIDDEN);

        subjectsListLayout = v.findViewById(R.id.subjectsList);

        bottomPanel = v.findViewById(R.id.bottomPanel);
        bottomPanel.setOnClickListener(this);

        fillWeekButtonsList();
        for (int i=0; i<7; i++){
            days_of_week_buttons[i].setOnClickListener(this);
        }

        error = v.findViewById(R.id.error_msg);
        week = v.findViewById(R.id.week);

        current_date = v.findViewById(R.id.date);
        current_date.setOnClickListener(this);
        setCurrentDay();

        topPanel = v.findViewById(R.id.topPanel);

        calendar = v.findViewById(R.id.calendarView);
        calendar.setOnDateChangeListener(this);

        getLessonInfo(this.date);
    }

    private void getLessonInfo(String date) throws JSONException, ParseException {
        setWeekNum(date);

        MainActivity.api.getTimetableDay(date, new React(){
            @Override
            public void reaction(JSONObject data) throws JSONException {
                lessons = data.getJSONObject("response").getJSONArray("lessons");
                subjects_list = new DateSubjectGenerator[lessons.length()];
                api_status = true;
                System.out.println("CalendarFragment start: "+System.currentTimeMillis());
            }

            @Override
            public void FailedRequest(int status){
                api_status = false;
            }
        });

        Api_connector.wait_state_connection(100000);
        System.out.println("CalendarFragment end: "+System.currentTimeMillis());
        subjectsListLayout.removeAllViews();
        if (api_status || subjects_list.length!=0){
            createLessons();
        } else{
            error.setVisibility(View.VISIBLE);
        }
        v.invalidate();
    }

    private void setWeekNum(String date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date d = sdf.parse(date);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d);
        int week = calendar.get(Calendar.WEEK_OF_YEAR)%2;
        if (week==0){
            this.week.setText("Нижняя неделя");
        }else {
            this.week.setText("Верхняя неделя");
        }
    }

    private void createLessons() throws JSONException {
        for (int i=0; i<lessons.length(); i++){
            JSONObject lesson = lessons.getJSONObject(i);

            int id = lesson.getInt("id");
            String cab = lesson.getString("info");
            JSONArray up = lesson.getJSONObject("time").getJSONArray("up");
            JSONArray down = lesson.getJSONObject("time").getJSONArray("down");


            String time = rightTimeFormat(up.getInt(0)) + ":" + rightTimeFormat(up.getInt(1))
                    + "-" + rightTimeFormat(down.getInt(0)) + ":" + rightTimeFormat(down.getInt(1));
            String prof = lesson.getJSONArray("teacher").getJSONObject(0).getString("name");
            String name = lesson.getString("name");

            subjects_list[i] = new DateSubjectGenerator(getLayoutInflater(), subjectsListLayout, slidingPanel, getActivity(), id, cab, time, prof, name);
        }
    }
    private String rightTimeFormat(int t){
        String tru_t;
        if (t/10==0){
            tru_t = "0" + t;
        } else{
            tru_t = String.valueOf(t);
        }
        return tru_t;
    }

    private void fillWeekButtonsList() {
        days_of_week_buttons[0] = v.findViewById(R.id.button1);
        days_of_week_buttons[1] = v.findViewById(R.id.button2);
        days_of_week_buttons[2] = v.findViewById(R.id.button3);
        days_of_week_buttons[3] = v.findViewById(R.id.button4);
        days_of_week_buttons[4] = v.findViewById(R.id.button5);
        days_of_week_buttons[5] = v.findViewById(R.id.button6);
        days_of_week_buttons[6] = v.findViewById(R.id.button7);

        days_of_week_buttons[0].id = R.id.button1;
        days_of_week_buttons[1].id = R.id.button2;
        days_of_week_buttons[2].id = R.id.button3;
        days_of_week_buttons[3].id = R.id.button4;
        days_of_week_buttons[4].id = R.id.button5;
        days_of_week_buttons[5].id = R.id.button6;
        days_of_week_buttons[6].id = R.id.button7;

        for (int i=0; i<7; i++){
            days_of_week_buttons[i].parent = this;
        }


        Calendar calendar = Calendar.getInstance();
        Date date = new Date();
        calendar.setTime(date);

        String[] week = new String[7];

        String str_date = date.toString();
        if (str_date.substring(0,3).equals("Mon")){
            week[0] = Integer.parseInt(calendar.getTime().toString().substring(8,10)) + "\n";
            days_of_week_buttons[0].setDate(calendar.get(Calendar.YEAR) + "-" + String.valueOf(calendar.get(Calendar.MONTH) + 1) + "-" + calendar.get(Calendar.DAY_OF_MONTH));
        }else if (str_date.substring(0,3).equals("Tue")){
            calendar.add(Calendar.DATE, -1);
            week[0] = Integer.parseInt(calendar.getTime().toString().substring(8,10)) + "\n";
            days_of_week_buttons[0].setDate(calendar.get(Calendar.YEAR) + "-" + String.valueOf(calendar.get(Calendar.MONTH) + 1) + "-" + calendar.get(Calendar.DAY_OF_MONTH));
        }else if (str_date.substring(0,3).equals("Wed")){
            calendar.add(Calendar.DATE, -2);
            week[0] = Integer.parseInt(calendar.getTime().toString().substring(8,10)) + "\n";
            days_of_week_buttons[0].setDate(calendar.get(Calendar.YEAR) + "-" + String.valueOf(calendar.get(Calendar.MONTH) + 1) + "-" + calendar.get(Calendar.DAY_OF_MONTH));
        }else if (str_date.substring(0,3).equals("Thu")){
            calendar.add(Calendar.DATE, -3);
            week[0] = Integer.parseInt(calendar.getTime().toString().substring(8,10)) + "\n";
            days_of_week_buttons[0].setDate(calendar.get(Calendar.YEAR) + "-" + String.valueOf(calendar.get(Calendar.MONTH) + 1) + "-" + calendar.get(Calendar.DAY_OF_MONTH));
        }else if (str_date.substring(0,3).equals("Fri")){
            calendar.add(Calendar.DATE, -4);
            week[0] = Integer.parseInt(calendar.getTime().toString().substring(8,10)) + "\n";
            days_of_week_buttons[0].setDate(calendar.get(Calendar.YEAR) + "-" + String.valueOf(calendar.get(Calendar.MONTH) + 1) + "-" + calendar.get(Calendar.DAY_OF_MONTH));
        }else if (str_date.substring(0,3).equals("Sat")){
            calendar.add(Calendar.DATE, -5);
            week[0] = Integer.parseInt(calendar.getTime().toString().substring(8,10)) + "\n";
            days_of_week_buttons[0].setDate(calendar.get(Calendar.YEAR) + "-" + String.valueOf(calendar.get(Calendar.MONTH) + 1) + "-" + calendar.get(Calendar.DAY_OF_MONTH));
        }else if (str_date.substring(0,3).equals("Sun")){
            calendar.add(Calendar.DATE, -6);
            week[0] = Integer.parseInt(calendar.getTime().toString().substring(8,10)) + "\n";
            days_of_week_buttons[0].setDate(calendar.get(Calendar.YEAR) + "-" + String.valueOf(calendar.get(Calendar.MONTH) + 1) + "-" + calendar.get(Calendar.DAY_OF_MONTH));
        }

        for (int i=1; i<7; i++){
            calendar.add(Calendar.DATE, 1);
            week[i] = Integer.parseInt(calendar.getTime().toString().substring(8,10)) + "\n";
            days_of_week_buttons[i].setDate(calendar.get(Calendar.YEAR) + "-" + String.valueOf(calendar.get(Calendar.MONTH) + 1) + "-" + calendar.get(Calendar.DAY_OF_MONTH));
        }

        days_of_week_buttons[0].setText(week[0]+"ПН");
        days_of_week_buttons[1].setText(week[1]+"ВТ");
        days_of_week_buttons[2].setText(week[2]+"СР");
        days_of_week_buttons[3].setText(week[3]+"ЧТ");
        days_of_week_buttons[4].setText(week[4]+"ПТ");
        days_of_week_buttons[5].setText(week[5]+"СБ");
        days_of_week_buttons[6].setText(week[6]+"ВС");
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (slidingPanel.getPanelState() == SlidingUpPanelLayout.PanelState.HIDDEN){
            if (id == R.id.date) {
                try {
                    slidingPanel.setPanelState(SlidingUpPanelLayout.PanelState.EXPANDED);
                } catch (Exception e) {
                    System.out.println("current_day_click_error");
                    System.out.println(e.toString());
                }
            } else {
                for (int i=0; i<7; i++){
                    if (id == days_of_week_buttons[i].id){
                        days_of_week_buttons[i].setStatus(true);
                        try {
                            getLessonInfo(days_of_week_buttons[i].getDate());
                            current_date.setText(days_of_week_buttons[i].getAltDate());
                        } catch (JSONException | ParseException e) {
                            System.out.println("weekButtonJSONError: " + e.toString());
                        }
                    }else{
                        days_of_week_buttons[i].setStatus(false);
                    }
                }

            }
        }else {
            slidingPanel.setPanelState(SlidingUpPanelLayout.PanelState.HIDDEN);
        }
    }

    @Override
    public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
        try {
            current_date.setText(getDateText(year, month, dayOfMonth));

            month += 1;
            String d, m;
            if (dayOfMonth/10==0){
                d = "0" + dayOfMonth;
            } else{
                d = String.valueOf(dayOfMonth);
            }
            if (month/10==0){
                m = "0" + month;
            } else{
                m = String.valueOf(month);
            }
            String date = year + "-" + m + "-" + d;

            getLessonInfo(date);
        } catch (Exception e){
            System.out.println("calendar");
            System.out.println(e.toString());
        }
    }


    private void setCurrentDay() {
        String[] weeks = createWeekList();
        String[] months = createMonthsList();

        String day_week = new SimpleDateFormat("u", Locale.getDefault()).format(new Date());
        String day = new SimpleDateFormat("dd", Locale.getDefault()).format(new Date());
        String month = new SimpleDateFormat("MM", Locale.getDefault()).format(new Date());
        String year = new SimpleDateFormat("yyyy", Locale.getDefault()).format(new Date());

        String date = weeks[Integer.parseInt(day_week)] + ", " + day + " " + months[Integer.parseInt(month)-1] + " " + year;
        current_date.setText(date);

        this.date = year + "-" + month + "-" + day;
    }
    private String getDateText(int year, int month, int dayOfMonth){
        String[] weeks = createWeekList();
        String[] months = createMonthsList();

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, dayOfMonth);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

        return weeks[dayOfWeek-1] + ", " + dayOfMonth + " " + months[month] + " " + year;
    }
    String[] createMonthsList() {
        String[] months = new String[12];

        months[0] = "Января";
        months[1] = "Февраля";
        months[2] = "Марта";
        months[3] = "Апреля";
        months[4] = "Мая";
        months[5] = "Июня";
        months[6] = "Июля";
        months[7] = "Августа";
        months[8] = "Сентября";
        months[9] = "Октября";
        months[10] = "Ноября";
        months[11] = "Декабря";

        return months;
    }
    String[] createWeekList() {
        String[] week = new String[8];

        week[0] = "Воскресенье";
        week[1] = "Понедельник";
        week[2] = "Вторник";
        week[3] = "Среда";
        week[4] = "Четверг";
        week[5] = "Пятница";
        week[6] = "Суббота";
        week[7] = "Воскресенье";

        return week;
    }
}