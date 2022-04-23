package com.example.allegrosummerexperience;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.allegrosummerexperience.adapter.ReposListAdapter;
import com.example.allegrosummerexperience.api.GithubAPISingleton;
import com.example.allegrosummerexperience.model.ReposModel;
import com.example.allegrosummerexperience.service.GithubAPIService;
import com.example.allegrosummerexperience.utils.VolleyResponseListener;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText githubUsername;
    private Button getReposBtn;
    private ListView reposList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        githubUsername = findViewById(R.id.editTextUsername);
        getReposBtn = findViewById(R.id.buttonGetRepos);
        reposList = findViewById(R.id.listViewRepos);

        getReposBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GithubAPIService githubAPIService = new GithubAPIService(MainActivity.this);
                List<ReposModel> repos;
                githubAPIService.getUsersRepos(githubUsername.getText().toString(), new VolleyResponseListener() {
                    @Override
                    public void onError(String message) {
                        Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onResponse(Object response) {
                        ReposListAdapter reposListAdapter = new ReposListAdapter(MainActivity.this, (ArrayList)response);
                        reposList.setAdapter(reposListAdapter);
                        reposList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                Toast.makeText(MainActivity.this, reposListAdapter.getReposModels().get(i).getName(), Toast.LENGTH_LONG).show();
                            }
                        });

                    }
                });
            }
        });
    }
}