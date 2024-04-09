package com.example.api_project;

import android.util.Log;

import okhttp3.*;

import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.util.Objects;

class React{
    public React(){}
    public void reaction(JSONObject data) throws JSONException {
        // all action
    }

    public void FailedRequest(int status){
        // all DIS-action on Ateva. ATEVA ATEVA! ATEVA
    }


}
enum StateConnection {
    success,
    fail,
    processing,
    empty
}
public class Api_connector {
    protected static StateConnection stateConnection = StateConnection.empty;
    final String url = "http://api.arefaste.ru/";
    private final String id_app = "2";
    private final String secret_key = "52053e49-d268-4467-9a7b-6013ba82c966";
    private String user_log, user_pass;
    protected String auth_token, user_token;
    private final OkHttpClient client = new OkHttpClient().newBuilder().build();

    public Api_connector(String login, String pass) {
        this.user_log = login;
        this.user_pass = pass;
        testConnection(new React(){
            @Override
            public void reaction(JSONObject data){
                getAuthToken(new React(){
                    @Override
                    public void reaction(JSONObject data) throws JSONException { // это значит всё успешно завершилось
                        user_token = data.getJSONObject("response").getString("auth_token");
                    }

                    @Override
                    public void FailedRequest(int status){ // это значит НЕ всё успешно завершилось
                        // all DIS-action on Ateva. ATEVA ATEVA! ATEVA

                    }
                });
            }
        });
    }
    public Api_connector(String user_token_test) {
        this.user_token = user_token_test;
        testConnection(new React(){
            @Override
            public void FailedRequest(int status){
                user_token = null; // todo подумай что тут должно быть. Ошибка или мейби так оставить
            }
        });
    }

    /*-------------- SERVER METHODS --------------*/
    private void getAuthToken(React reactor) {
        String url = this.url + "system.auth";
        HttpUrl.Builder urlBuilder = HttpUrl.parse(url).newBuilder();
        urlBuilder.addQueryParameter("login", this.user_log);
        urlBuilder.addQueryParameter("password", this.user_pass);
        urlBuilder.addQueryParameter("id_app", this.id_app);
        urlBuilder.addQueryParameter("secret_key", this.secret_key);

        Request request = new Request.Builder()
                .addHeader("User-Agent", "TiboxMobileApp")
                .url(urlBuilder.build())
                .get()
                .build();

        Callb call = new Callb(reactor);
        this.client.newCall(request).enqueue(call);
    }

    private void getGroupsInstitutes(React reactor) {
        String url = this.url + "system.getApp_data";
        HttpUrl.Builder urlBuilder = HttpUrl.parse(url).newBuilder();
        urlBuilder.addQueryParameter("id_app", this.id_app);
        urlBuilder.addQueryParameter("secret_key", this.secret_key);

        Request request = new Request.Builder()
                .addHeader("User-Agent", "TiboxMobileApp")
                .url(urlBuilder.build())
                .get()
                .build();

        Callb call = new Callb(reactor);
        this.client.newCall(request).enqueue(call);
    }

    private void getAuthToken(String login, String password, React reactor) {
        String url = this.url + "system.auth";
        HttpUrl.Builder urlBuilder = HttpUrl.parse(url).newBuilder();
        urlBuilder.addQueryParameter("login", login);
        urlBuilder.addQueryParameter("password", password);
        urlBuilder.addQueryParameter("id_app", this.id_app);
        urlBuilder.addQueryParameter("secret_key", this.secret_key);

        Request request = new Request.Builder()
                .addHeader("User-Agent", "TiboxMobileApp")
                .url(urlBuilder.build())
                .get()
                .build();

        Callb call = new Callb(reactor);
        this.client.newCall(request).enqueue(call);
    }

    private void getUserToken(String auth_token, String secret_key, React reactor) {
        String url = this.url + "system.getToken";
        HttpUrl.Builder urlBuilder = HttpUrl.parse(url).newBuilder();
        urlBuilder.addQueryParameter("auth_token", auth_token);
        urlBuilder.addQueryParameter("secret_key", secret_key);

        Request request = new Request.Builder()
                .addHeader("User-Agent", "TiboxMobileApp")
                .url(urlBuilder.build())
                .get()
                .build();

        Callb call = new Callb(reactor);
        this.client.newCall(request).enqueue(call);
    }

    private void testConnection(React reactor){
        String url = this.url + "system.ping";

        Callb call = new Callb(reactor, true);
        Request request = new Request.Builder()
                .addHeader("User-Agent", "TiboxMobileApp")
                .url(url).get().build();
        this.client.newCall(request).enqueue(call);
    }

    private void testConnection(boolean ignore_state,React reactor){
        String url = this.url + "system.ping";

        Callb call = new Callb(reactor, ignore_state);
        Request request = new Request.Builder()
                .addHeader("User-Agent", "TiboxMobileApp")
                .url(url).get().build();
        this.client.newCall(request).enqueue(call);
    }

    /*-------------- USER METHODS --------------*/
    public void getUserInfo(React reactor) {
        String url = this.url + "user.getInfo";
        HttpUrl.Builder urlBuilder = HttpUrl.parse(url).newBuilder();
        urlBuilder.addQueryParameter("user_token", this.user_token);

        Request request = new Request.Builder()
                .addHeader("User-Agent", "TiboxMobileApp")
                .url(urlBuilder.build())
                .get()
                .build();

        Callb call = new Callb(reactor);
        this.client.newCall(request).enqueue(call);
    }

    public void getUserInfo(String user_token, React reactor) {
        String url = this.url + "user.getInfo";
        HttpUrl.Builder urlBuilder = HttpUrl.parse(url).newBuilder();
        urlBuilder.addQueryParameter("user_token", user_token);

        Request request = new Request.Builder()
                .addHeader("User-Agent", "TiboxMobileApp")
                .url(urlBuilder.build())
                .get()
                .build();

        Callb call = new Callb(reactor);
        this.client.newCall(request).enqueue(call);
    }

    public void getUserGroup(React reactor) {
        String url = this.url + "user.getGroup";
        HttpUrl.Builder urlBuilder = HttpUrl.parse(url).newBuilder();
        urlBuilder.addQueryParameter("user_token", this.user_token);

        Request request = new Request.Builder()
                .addHeader("User-Agent", "TiboxMobileApp")
                .url(urlBuilder.build())
                .get()
                .build();

        Callb call = new Callb(reactor);
        this.client.newCall(request).enqueue(call);
    }

    public void getUserGroup(String user_token, React reactor) {
        String url = this.url + "user.getGroup";
        HttpUrl.Builder urlBuilder = HttpUrl.parse(url).newBuilder();
        urlBuilder.addQueryParameter("user_token", user_token);

        Request request = new Request.Builder()
                .addHeader("User-Agent", "TiboxMobileApp")
                .url(urlBuilder.build())
                .get()
                .build();

        Callb call = new Callb(reactor);
        this.client.newCall(request).enqueue(call);
    }

    public void changePassword(String old_pass, String new_pass, React reactor) {
        String url = this.url + "user.getInfo";
        HttpUrl.Builder urlBuilder = HttpUrl.parse(url).newBuilder();
        urlBuilder.addQueryParameter("user_token", this.user_token);
        urlBuilder.addQueryParameter("old_password", old_pass);
        urlBuilder.addQueryParameter("password", new_pass);

        Request request = new Request.Builder()
                .addHeader("User-Agent", "TiboxMobileApp")
                .url(urlBuilder.build())
                .get()
                .build();

        Callb call = new Callb(reactor);
        this.client.newCall(request).enqueue(call);
    }

//    public String changeLogin(String new_login) {
//        String resp = "";
//        String url = this.url + "user.getInfo?user_token=" + this.user_token
//                + "&new_login=" + new_login;
//
//        Callb call = new Callb();
//        Request request = new Request.Builder()
//                .addHeader("User-Agent", "TiboxMobileApp")
//                .url(url).get().build();
//        this.client.newCall(request).enqueue(call);
//
//        try{
//            JSONObject data = call.response;
//            resp = data.getString("time_token");
//        } catch (JSONException e) {
////            Log.i("ERRORINFO_JSON", e.toString());
//            System.out.println("ERRORINFO_JSON: " + e.toString());
//        }
//        return resp;
//    } todo - не надо пока что)


    /*-------------- TIMETABLE METHODS --------------*/
    public void getTimetableDay(String date, React reactor) {
        getTimetableDay(date, "", -1, reactor);
    }
    public void getTimetableDay(String date, String teacher, React reactor) {
        getTimetableDay(date, teacher, -1, reactor);
    }
    public void getTimetableDay(String date, int lesson, React reactor) {
        getTimetableDay(date, "", lesson, reactor);
    }

    public void getTimetableDay(String date, String teacher, int lesson, React reactor) {
        String url = this.url + "user.getTimetable";
        HttpUrl.Builder urlBuilder = HttpUrl.parse(url).newBuilder();
        urlBuilder.addQueryParameter("user_token", this.user_token);
        if (Objects.equals(teacher, "") && lesson == -1) {
            urlBuilder.addQueryParameter("date", date);
        } else
        if (Objects.equals(teacher, "")) {
            urlBuilder.addQueryParameter("date", date);
            urlBuilder.addQueryParameter("id_lesson", String.valueOf(lesson));
        } else
        if (lesson == -1) {
            urlBuilder.addQueryParameter("date", date);
            urlBuilder.addQueryParameter("id_teacher", String.valueOf(teacher));
        }
        else {
            urlBuilder.addQueryParameter("date", date);
            urlBuilder.addQueryParameter("id_lesson", String.valueOf(lesson));
            urlBuilder.addQueryParameter("id_teacher", String.valueOf(teacher));
        }

        Request request = new Request.Builder()
                .addHeader("User-Agent", "TiboxMobileApp")
                .url(urlBuilder.build())
                .get()
                .build();

        Callb call = new Callb(reactor);
        this.client.newCall(request).enqueue(call);
    }

    public void getLesson(int lesson, React reactor) {
        String url = this.url + "user.getLesson";
        HttpUrl.Builder urlBuilder = HttpUrl.parse(url).newBuilder();
        urlBuilder.addQueryParameter("user_token", user_token);
        urlBuilder.addQueryParameter("id_lesson", String.valueOf(lesson));

        Request request = new Request.Builder()
                .addHeader("User-Agent", "TiboxMobileApp")
                .url(urlBuilder.build())
                .get()
                .build();

        Callb call = new Callb(reactor);
        this.client.newCall(request).enqueue(call);
    }
    public void getLesson(React reactor) {
        String url = this.url + "user.getLesson";
        HttpUrl.Builder urlBuilder = HttpUrl.parse(url).newBuilder();
        urlBuilder.addQueryParameter("user_token", user_token);

        Request request = new Request.Builder()
                .addHeader("User-Agent", "TiboxMobileApp")
                .url(urlBuilder.build())
                .get()
                .build();

        Callb call = new Callb(reactor);
        this.client.newCall(request).enqueue(call);
    }

    public void getTeacher(int teacher, React reactor) {
        String url = this.url + "user.getTeacher";
        HttpUrl.Builder urlBuilder = HttpUrl.parse(url).newBuilder();
        urlBuilder.addQueryParameter("user_token", user_token);
        urlBuilder.addQueryParameter("id_teacher", String.valueOf(teacher));

        Request request = new Request.Builder()
                .addHeader("User-Agent", "TiboxMobileApp")
                .url(urlBuilder.build())
                .get()
                .build();

        Callb call = new Callb(reactor);
        this.client.newCall(request).enqueue(call);
    }
    public void getTeacher(React reactor) {
        String url = this.url + "user.getTeacher";
        HttpUrl.Builder urlBuilder = HttpUrl.parse(url).newBuilder();
        urlBuilder.addQueryParameter("user_token", user_token);

        Request request = new Request.Builder()
                .addHeader("User-Agent", "TiboxMobileApp")
                .url(urlBuilder.build())
                .get()
                .build();

        Callb call = new Callb(reactor);
        this.client.newCall(request).enqueue(call);
    }

    /*-------------- GET METHODS --------------*/
    public String get_user_token(){
        return this.user_token;
    }
    /*-------------- static methods --------------*/
    public static void wait_state_connection(int milliseconds){
        long timestampInMillis = System.currentTimeMillis();
        while(Api_connector.stateConnection == StateConnection.empty ||
                Api_connector.stateConnection == StateConnection.processing){
            if (System.currentTimeMillis()-timestampInMillis >= milliseconds) break;
        }
    }
}

class Callb implements Callback {
    public boolean status;
    String resp;
    public JSONObject response;
    private React reactor;
    private boolean ignore_state = false;

    public Callb(React reactor){
        this.reactor = reactor;
        Api_connector.stateConnection = StateConnection.processing;
    }

    public Callb(React reactor, boolean ignore_state){
        this.reactor = reactor;
        this.ignore_state = ignore_state;
    }

    @Override
    public void onFailure(Call call, IOException e) {
        this.status = false;
//        Log.i("ERRORINFO_CALLBACK_CONNECTION", e.toString());
        if (!this.ignore_state) Api_connector.stateConnection = StateConnection.fail;
        System.out.println("ERRORINFO_JSON: " + e.toString());
        this.reactor.FailedRequest(502);
    }

    @Override
    public void onResponse(Call call, Response response) throws IOException {
        try {
            if (!response.isSuccessful() && response.code()!=418) {
//                Log.i("ERRORINFO_RESPONSE","Запрос к серверу не был успешен: " + response.code() + " " + response.message());
                System.out.println("Запрос к серверу не был успешен: " + response.code() + " " + response.message());
                this.status = false;
                this.resp = response.body().string();

                if (!this.ignore_state) Api_connector.stateConnection = StateConnection.fail;
                this.reactor.FailedRequest(response.code());
                System.out.println(this.resp);
            } else {
                this.resp = response.body().string();
                System.out.println("response: " + this.resp);
                try{
                    JSONObject data = new JSONObject(this.resp);

                    if (!this.ignore_state) Api_connector.stateConnection = StateConnection.success;
                    this.reactor.reaction(data);
                } catch (JSONException e) {
//                    Log.i("ERRORINFO_JSON", e.toString());
                    System.out.println("ERRORINFO_JSON: " + e.toString());
                }
            }
        } catch (Exception e){

            if (!this.ignore_state) Api_connector.stateConnection = StateConnection.fail;
            this.reactor.FailedRequest(response.code());
            System.out.println("response error: " + e.toString());
        }
    }
}