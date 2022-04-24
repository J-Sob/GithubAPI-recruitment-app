package com.example.allegrosummerexperience.utils;

/* Callback interface for async requests */
public interface VolleyResponseListener {
    void onError(String message);

    void onResponse(Object response);
}
