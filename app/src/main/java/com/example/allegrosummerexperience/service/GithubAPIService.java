package com.example.allegrosummerexperience.service;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.allegrosummerexperience.api.GithubAPISingleton;

import org.json.JSONArray;

import java.util.List;

public class GithubAPIService {

    private Context context;

    public GithubAPIService() {
    }

    public GithubAPIService(Context context) {
        this.context = context;
    }

    public List<String> getUsersReposNames(String username) {
        if (!username.equals("")) {
            String url = "https://api.github.com/users/" + username + "/repos";
            JsonArrayRequest request = new JsonArrayRequest(
                    Request.Method.GET,
                    url,
                    null,
                    new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray response) {
                            System.out.println(response);
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(context, "User not found", Toast.LENGTH_LONG).show();
                        }
                    }
            );
            GithubAPISingleton.getInstance(context).addToRequestQueue(request);
        } else {
            Toast.makeText(context, "Enter GitHub username first", Toast.LENGTH_LONG).show();
        }
        return null;
    }
}
