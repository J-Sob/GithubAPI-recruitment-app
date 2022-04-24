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

import java.util.List;

public class ReposListAdapter extends ArrayAdapter<String> {

    private Context context;
    private List<String> reposNames;

    public ReposListAdapter(@NonNull Context context, List<String> reposNames) {
        super(context, R.layout.repo_row, R.id.textViewLanguage,reposNames);
        this.context = context;
        this.reposNames = reposNames;
    }

    public List<String> getReposNames() {
        return reposNames;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = layoutInflater.inflate(R.layout.repo_row, parent, false);
        TextView repoName = row.findViewById(R.id.textViewLanguage);
        ImageView icon = row.findViewById(R.id.imageViewIcon);

        repoName.setText(reposNames.get(position));
        return row;
    }
}
