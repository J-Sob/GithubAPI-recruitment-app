package com.example.allegrosummerexperience.api;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/* Singleton class for API requests */
public class GithubAPISingleton {
    private static GithubAPISingleton instance;
    private RequestQueue requestQueue;
    private static Context ctx;

    private GithubAPISingleton(Context context) {
        ctx = context;
        requestQueue = getRequestQueue();
    }

    public static synchronized GithubAPISingleton getInstance(Context context) {
        if (instance == null) {
            instance = new GithubAPISingleton(context);
        }
        return instance;
    }

    public RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(ctx.getApplicationContext());
        }
        return requestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }

}
