package com.example.api_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity extends AppCompatActivity {
    public static Api_connector api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        api = ((ApiMover) getIntent().getSerializableExtra("api")).getApi();

        NonSwipeableViewPager viewPager = findViewById(R.id.viewpager);
        viewPager.setSwipeEnabled(false);
        TabLayout tabs = findViewById(R.id.sliding_tabs);

        PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                    @Override
                    public void onTabSelected(TabLayout.Tab tab) {
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
                        int i = tab.getPosition();
                        switch (i){
                            case 0:
                                getSupportFragmentManager().beginTransaction().replace(R.id.container, new settingsFragment()).commit();
                                break;
                            case 1:
                                getSupportFragmentManager().beginTransaction().replace(R.id.container, new subjectsFragment()).commit();
                                break;
                            case 2:
                                getSupportFragmentManager().beginTransaction().replace(R.id.container, new calendarFragment()).commit();
                                break;
                            case 3:
                                getSupportFragmentManager().beginTransaction().replace(R.id.container, new profileFragment()).commit();
                                break;
                        }
                    }
                });

        tabSetting();
    }

    private void tabSetting(){
        tabClear();

        TabLayout tabs = findViewById(R.id.sliding_tabs);
        TabLayout.Tab tab = tabs.getTabAt(0);

        if (tab != null) tab.setCustomView(R.layout.tab_settings_on);
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