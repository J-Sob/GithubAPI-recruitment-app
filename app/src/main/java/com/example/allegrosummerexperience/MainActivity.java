package com.example.allegrosummerexperience;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.allegrosummerexperience.activity.RepositoryActivity;
import com.example.allegrosummerexperience.adapter.ReposListAdapter;
import com.example.allegrosummerexperience.service.GithubAPIService;
import com.example.allegrosummerexperience.utils.VolleyResponseListener;

import java.util.ArrayList;

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
                githubAPIService.getUsersRepos(githubUsername.getText().toString(), new VolleyResponseListener() {
                    @Override
                    public void onError(String message) {
                        Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG).show();
                        reposList.setAdapter(null);
                    }

                    @Override
                    public void onResponse(Object response) {
                        ReposListAdapter reposListAdapter = new ReposListAdapter(MainActivity.this, (ArrayList)response);
                        reposList.setAdapter(reposListAdapter);
                        reposList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                Intent intent = new Intent(MainActivity.this, RepositoryActivity.class);;
                                intent.putExtra("repoName", reposListAdapter.getReposNames().get(i));
                                intent.putExtra("repoOwner", githubUsername.getText().toString());
                                startActivity(intent);
                            }
                        });

                    }
                });
            }
        });
    }
}