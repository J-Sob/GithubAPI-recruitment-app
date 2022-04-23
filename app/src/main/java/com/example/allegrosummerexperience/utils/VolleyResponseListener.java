package com.example.allegrosummerexperience.utils;

public interface VolleyResponseListener {
    void onError(String message);

    void onResponse(Object response);
}
