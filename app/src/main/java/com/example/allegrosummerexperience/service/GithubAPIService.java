package com.example.allegrosummerexperience.service;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.allegrosummerexperience.api.GithubAPISingleton;
import com.example.allegrosummerexperience.model.SingleRepoModel;
import com.example.allegrosummerexperience.utils.VolleyResponseListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/*  Service class for API responses handling */
public class GithubAPIService {

    private Context context;

    public GithubAPIService() {
    }

    public GithubAPIService(Context context) {
        this.context = context;
    }
    /* Fetching certain users repositories */
    public void getUsersRepos(String username, final VolleyResponseListener volleyResponseListener) {
        if (!username.equals("")) {
            String url = "https://api.github.com/users/" + username + "/repos";
            JsonArrayRequest request = new JsonArrayRequest(
                    Request.Method.GET,
                    url,
                    null,
                    new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray response) {
                            List<String> repos = new ArrayList<>();
                            for(int i = 0; i < response.length(); i++){
                                try {
                                    JSONObject repo = response.getJSONObject(i);
                                    String repoName = repo.getString("name");
                                    repos.add(repoName);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                            volleyResponseListener.onResponse(repos);
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error){
                            volleyResponseListener.onError("User not found");
                        }
                    }
            );
            GithubAPISingleton.getInstance(context).addToRequestQueue(request);
        } else {
            Toast.makeText(context, "Enter GitHub username first", Toast.LENGTH_LONG).show();
        }
    }
    /* Fetching single repo languages info */
    public void getSingleRepoInfo(String repoName, String repoOwner, final VolleyResponseListener volleyResponseListener){
        String url = "https://api.github.com/repos/" + repoOwner + "/" + repoName + "/languages";
        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        /* Keeping languages in LinkedHashMap to keep original order of elements */
                        Map<String, Integer> languages = new LinkedHashMap<>();
                        Iterator<String> keys = response.keys();
                        while(keys.hasNext()){
                            try {
                                String key = keys.next();
                                Integer bytes = response.getInt(key);
                                languages.put(key, bytes);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        SingleRepoModel singleRepoModel = new SingleRepoModel(repoName, repoOwner, languages);
                        volleyResponseListener.onResponse(singleRepoModel);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        volleyResponseListener.onError("Something went wrong");
                    }
                }
        );
        GithubAPISingleton.getInstance(context).addToRequestQueue(request);
    }
}
