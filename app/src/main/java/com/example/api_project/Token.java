package com.example.api_project;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.appcompat.app.AppCompatActivity;

public class Token {
    private final SharedPreferences cache_storage;
    public Token(AppCompatActivity activity){
        this.cache_storage = activity.getSharedPreferences(
                activity.getResources().getString(R.string.cache_token_storage),
                Context.MODE_PRIVATE
        );
    }
    public String getToken(){
        return this.cache_storage.getString("user_token", null);
    }
    public void setToken(String token_temp){
        SharedPreferences.Editor editor = cache_storage.edit();
        editor.putString("user_token", token_temp);
        editor.apply();
    }
    public void removeToken(){
        SharedPreferences.Editor editor = cache_storage.edit();
        editor.remove("user_token");
        editor.apply();
    }
    public boolean isEmptyToken(){
        return this.getToken().isEmpty();
    }
}
