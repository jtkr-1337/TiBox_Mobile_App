package com.example.api_project;


import android.util.Log;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class Requests {
    String txt = "default";

    public Requests(String url) {
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.i("MYINFOJTKR_E", e.toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try (ResponseBody responseBody = response.body()) {
                    if (!response.isSuccessful()) {
                        Log.i("MYINFOJTKR0","Запрос к серверу не был успешен: " +
                                response.code() + " " + response.message());
                    }
                    txt = responseBody.string();
                    Log.i("MYINFOJTKR1", txt);
                }
            }
        });
    }

    public String getTxt(){
        return this.txt;
    }
}
