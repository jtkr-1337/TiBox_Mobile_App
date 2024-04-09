package com.example.api_project;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class subjectsFragment extends Fragment {

    SubjectGenerator[] subjects;
    View v;
    LinearLayout list;
    TextView error;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_subjects, container, false);

        list = v.findViewById(R.id.subjects);
        error = v.findViewById(R.id.error_msg);

        MainActivity.api.getLesson(new React(){
            @Override
            public void reaction(JSONObject data) throws JSONException {
                JSONArray lesons = data.getJSONObject("response").getJSONArray("rows");
                subjects = new SubjectGenerator[lesons.length()];
                for (int i = 0; i< lesons.length(); i++){
                    JSONObject lesson = lesons.getJSONObject(i);
                    subjects[i] = new SubjectGenerator(getLayoutInflater(), list, lesson.getString("name"), lesson.getInt("id_lesson"), getActivity());
                }
            }

            @Override
            public void FailedRequest(int status){
                error.setVisibility(View.VISIBLE);
            }
        });


        return v;
    }
}