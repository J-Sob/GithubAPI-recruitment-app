package com.example.allegrosummerexperience.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.allegrosummerexperience.MainActivity;
import com.example.allegrosummerexperience.R;
import com.example.allegrosummerexperience.adapter.LanguagesListAdapter;
import com.example.allegrosummerexperience.adapter.ReposListAdapter;
import com.example.allegrosummerexperience.model.SingleRepoModel;
import com.example.allegrosummerexperience.service.GithubAPIService;
import com.example.allegrosummerexperience.utils.VolleyResponseListener;

import java.util.ArrayList;

public class RepositoryActivity extends AppCompatActivity {

    private TextView repoName;
    private TextView ownerName;
    private ListView languagesList;
    private Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repository);

        repoName = findViewById(R.id.textViewRepoName);
        ownerName = findViewById(R.id.textViewOwner);
        languagesList = findViewById(R.id.listViewLanguages);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        GithubAPIService githubAPIService = new GithubAPIService(RepositoryActivity.this);
        Intent intent = getIntent();
        String owner = intent.getStringExtra("repoOwner");
        String name = intent.getStringExtra("repoName");
        githubAPIService.getSingleRepoInfo(name, owner, new VolleyResponseListener() {
            @Override
            public void onError(String message) {
                Toast.makeText(RepositoryActivity.this, message, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onResponse(Object response) {
                SingleRepoModel repoModel = (SingleRepoModel) response;
                repoName.setText(repoModel.getName());
                ownerName.setText(repoModel.getOwner());
                LanguagesListAdapter languagesListAdapter = new LanguagesListAdapter(RepositoryActivity.this, repoModel.getLanguages());
                languagesList.setAdapter(languagesListAdapter);
            }
        });

    }
}