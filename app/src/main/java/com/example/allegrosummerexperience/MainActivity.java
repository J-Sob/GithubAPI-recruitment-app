package com.example.allegrosummerexperience;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.allegrosummerexperience.api.GithubAPISingleton;
import com.example.allegrosummerexperience.service.GithubAPIService;

import org.json.JSONArray;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText githubUsername;
    private Button getReposBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        githubUsername = findViewById(R.id.editTextUsername);
        getReposBtn = findViewById(R.id.buttonGetRepos);

        getReposBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GithubAPIService githubAPIService = new GithubAPIService();
                List<String> repoNames = githubAPIService.getUsersReposNames(githubUsername.getText().toString());
            }
        });
    }
}