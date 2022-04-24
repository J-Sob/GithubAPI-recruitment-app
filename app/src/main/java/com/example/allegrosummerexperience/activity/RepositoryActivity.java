package com.example.allegrosummerexperience.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
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
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Map;

public class RepositoryActivity extends AppCompatActivity {

    private TextView repoName;
    private TextView ownerName;
    private ListView languagesList;
    private Button backButton;
    private PieChart pieChartLanguages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repository);

        repoName = findViewById(R.id.textViewRepoName);
        ownerName = findViewById(R.id.textViewOwner);
        languagesList = findViewById(R.id.listViewLanguages);
        pieChartLanguages = findViewById(R.id.pieChartLanguages);

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
                setupPieChart();
                loadPieChart(repoModel.getLanguages());
            }
        });

    }

    private void setupPieChart() {
        pieChartLanguages.setDrawHoleEnabled(true);
        pieChartLanguages.setUsePercentValues(true);
        pieChartLanguages.setEntryLabelTextSize(12);
        pieChartLanguages.setEntryLabelColor(Color.BLACK);
        pieChartLanguages.getDescription().setEnabled(false);
        pieChartLanguages.getLegend().setEnabled(false);
    }

    private void loadPieChart(Map<String, Integer> languages){
        ArrayList<PieEntry> entries = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : languages.entrySet()) {
            entries.add(new PieEntry(entry.getValue(), entry.getKey()));
        }

        ArrayList<Integer> colors = new ArrayList<>();
        for(int color: ColorTemplate.MATERIAL_COLORS){
            colors.add(color);
        }

        for(int color: ColorTemplate.VORDIPLOM_COLORS){
            colors.add(color);
        }

        PieDataSet dataSet = new PieDataSet(entries, "Usage of languages");
        dataSet.setColors(colors);

        PieData data = new PieData(dataSet);
        data.setDrawValues(true);
        data.setValueFormatter(new PercentFormatter(pieChartLanguages));
        data.setValueTextSize(12f);
        data.setValueTextColor(Color.BLACK);

        pieChartLanguages.setData(data);
        pieChartLanguages.invalidate();
    }
}