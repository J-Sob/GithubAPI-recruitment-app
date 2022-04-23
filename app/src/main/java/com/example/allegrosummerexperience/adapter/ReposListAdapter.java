package com.example.allegrosummerexperience.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.allegrosummerexperience.R;
import com.example.allegrosummerexperience.model.ReposModel;

import java.util.ArrayList;
import java.util.List;

public class ReposListAdapter extends ArrayAdapter<String> {

    private Context context;
    private List<ReposModel> reposModels;

    public ReposListAdapter(@NonNull Context context, List<ReposModel> reposModels) {
        super(context, R.layout.repo_row, R.id.textViewRepoName,(ArrayList)reposModels);
        this.context = context;
        this.reposModels = reposModels;
    }

    public List<ReposModel> getReposModels() {
        return reposModels;
    }

    public void setReposModels(List<ReposModel> reposModels) {
        this.reposModels = reposModels;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = layoutInflater.inflate(R.layout.repo_row, parent, false);
        TextView repoName = row.findViewById(R.id.textViewRepoName);
        ImageView icon = row.findViewById(R.id.imageViewIcon);

        repoName.setText(reposModels.get(position).getName());
        return row;
    }
}
