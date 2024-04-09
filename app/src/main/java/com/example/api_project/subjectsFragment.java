package com.example.api_project;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class subjectsFragment extends Fragment {

    SubjectGenerator[] subjects = new SubjectGenerator[10];
    View v;
    LinearLayout list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_subjects, container, false);

        list = v.findViewById(R.id.subjects);

        for (int i=0; i<10; i++){
            subjects[i] = new SubjectGenerator(getLayoutInflater(), list, "Testing", i, getActivity());
        }

        return v;
    }
}