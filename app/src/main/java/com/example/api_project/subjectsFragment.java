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

    View v;
    LinearLayout list;
    TextView error;
    JSONArray lessons;
    SubjectGenerator[] subjects = new SubjectGenerator[0];
    boolean api_status;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_subjects, container, false);

        list = v.findViewById(R.id.subjects);
        error = v.findViewById(R.id.error_msg);

        MainActivity.api.getLesson(new React(){
            @Override
            public void reaction(JSONObject data) throws JSONException {
                lessons = data.getJSONObject("response").getJSONArray("rows");
                subjects = new SubjectGenerator[lessons.length()];
                api_status = true;
            }

            @Override
            public void FailedRequest(int status){
                api_status = false;
            }
        });

        Api_connector.wait_state_connection(100000);
        if (api_status || subjects.length!=0){
            try {
                createSubjects();
            } catch (JSONException e) {
                System.out.println("SubjectsJSONError: " + e.toString());
            }
        } else{
            error.setVisibility(View.VISIBLE);
        }

        return v;
    }

    private void createSubjects() throws JSONException {
        for (int i = 0; i< lessons.length(); i++){
            JSONObject lesson = lessons.getJSONObject(i);
            subjects[i] = new SubjectGenerator(getLayoutInflater(), list, lesson.getString("name"), lesson.getInt("id_lesson"), getActivity());
        }
    }
}