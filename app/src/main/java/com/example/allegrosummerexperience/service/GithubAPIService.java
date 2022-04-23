package com.example.allegrosummerexperience.service;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.allegrosummerexperience.api.GithubAPISingleton;
import com.example.allegrosummerexperience.model.ReposModel;
import com.example.allegrosummerexperience.utils.VolleyResponseListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class GithubAPIService {

    private Context context;

    public GithubAPIService() {
    }

    public GithubAPIService(Context context) {
        this.context = context;
    }

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
                            List<ReposModel> repos = new ArrayList<>();
                            for(int i = 0; i < response.length(); i++){
                                try {
                                    JSONObject repo = response.getJSONObject(i);
                                    String repoName = repo.getString("name");
                                    boolean repoPrivate = repo.getBoolean("private");
                                    ReposModel reposModel = new ReposModel(repoName);
                                    repos.add(reposModel);
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
}
