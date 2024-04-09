package com.example.api_project;

import java.io.Serializable;

public class ApiMover implements Serializable {
    Api_connector api;
    public ApiMover(Api_connector a){
        this.api = a;
    }
    public Api_connector getApi(){
        return this.api;
    }
}
