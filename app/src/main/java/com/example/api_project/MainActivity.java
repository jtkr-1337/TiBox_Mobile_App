package com.example.api_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
    public static Api_connector api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        Token token = new Token(this);
        String userToken = token.getToken();


        System.out.println("Token was taken from storage");
        api = new Api_connector(userToken);
        System.out.println(api.get_user_token());
        Api_connector.wait_state_connection(10000);
        System.out.println(api.get_user_token());
        System.out.println("Connection was been restored");

        NonSwipeableViewPager viewPager = findViewById(R.id.viewpager);
        viewPager.setSwipeEnabled(false);
        TabLayout tabs = findViewById(R.id.sliding_tabs);

        PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);


        tabs.setScrollPosition(2,0f,true);
        viewPager.setCurrentItem(2);

        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                    @Override
                    public void onTabSelected(TabLayout.Tab tab) {
                        tabClear();
                        int i = tab.getPosition();
                        switch (i){
                            case 0:
                                tab.setCustomView(R.layout.tab_settings_on);
                                break;
                            case 1:
                                tab.setCustomView(R.layout.tab_subjects_on);
                                break;
                            case 2:
                                tab.setCustomView(R.layout.tab_calendar_on);
                                break;
                            case 3:
                                tab.setCustomView(R.layout.tab_profile_on);
                                break;
                        }
                        viewPager.setCurrentItem(i);
                    }
                    @Override
                    public void onTabUnselected(TabLayout.Tab tab) {
                        tabClear();
                        int i = tab.getPosition();
                        switch (i){
                            case 0:
                                tab.setCustomView(R.layout.tab_settings_off);
                                break;
                            case 1:
                                tab.setCustomView(R.layout.tab_subjects_off);
                                break;
                            case 2:
                                tab.setCustomView(R.layout.tab_calendar_off);
                                break;
                            case 3:
                                tab.setCustomView(R.layout.tab_profile_off);
                                break;
                        }
                    }
                    @Override
                    public void onTabReselected(TabLayout.Tab tab) {

                    }
                });

        tabSetting();
    }

    private void tabSetting(){
        tabClear();

        TabLayout tabs = findViewById(R.id.sliding_tabs);
        TabLayout.Tab tab = tabs.getTabAt(2);

        if (tab != null) tab.setCustomView(R.layout.tab_calendar_on);
    }
    private void tabClear(){
        TabLayout tabs = findViewById(R.id.sliding_tabs);

        TabLayout.Tab tab = tabs.getTabAt(0);
        if (tab != null) tab.setCustomView(R.layout.tab_settings_off);

        tab = tabs.getTabAt(1);
        if (tab != null) tab.setCustomView(R.layout.tab_subjects_off);

        tab = tabs.getTabAt(2);
        if (tab != null) tab.setCustomView(R.layout.tab_calendar_off);

        tab = tabs.getTabAt(3);
        if (tab != null) tab.setCustomView(R.layout.tab_profile_off);
    }
}