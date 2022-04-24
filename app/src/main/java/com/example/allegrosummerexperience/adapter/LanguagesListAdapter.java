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

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* Adapter for listView with languages */
public class LanguagesListAdapter extends ArrayAdapter<String> {

    private Context context;
    private List<String> names;
    private List<Integer> bytes;
    private int totalBytes;
    private final DecimalFormat df = new DecimalFormat("0.0");

    public LanguagesListAdapter(@NonNull Context context, Map<String, Integer> languages) {
        super(context, R.layout.language_row, R.id.textViewLanguage, new ArrayList<>(languages.keySet()));
        this.context = context;
        this.names = new ArrayList(languages.keySet());
        this.bytes = new ArrayList(languages.values());
        this.totalBytes = 0;
        for(Integer i: bytes){
            this.totalBytes += i;
        }
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = layoutInflater.inflate(R.layout.language_row, parent, false);
        TextView languageNameLabel = row.findViewById(R.id.textViewLanguage);
        TextView bytesLabel = row.findViewById(R.id.textViewBytes);
        TextView percentageLabel = row.findViewById(R.id.textViewPercentage);
        languageNameLabel.setText(names.get(position));
        bytesLabel.setText(bytes.get(position).toString() + " bytes");
        Double percentage = (1. * bytes.get(position) / totalBytes) * 100.;
        percentageLabel.setText(df.format(percentage) + "%");
        return row;
    }
}
