package com.application.tms.adapters;


import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.application.tms.R;

import java.util.ArrayList;
import java.util.List;

public class SpinnerItemAdapter extends ArrayAdapter<String> {
    private List<String> stringList = new ArrayList<>();

    public SpinnerItemAdapter(@NonNull Context context, int resource, int spinnerText, @NonNull List<String> stringList) {
        super(context, resource, spinnerText, stringList);
        this.stringList = stringList;
    }

    @Override
    public String  getItem(int position) {
        return stringList.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position);

    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position);
    }
    private View initView(int position) {
        String item = getItem(position);
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.spinner_array, null);
        TextView textView =  v.findViewById(R.id.spinnerarray);
        textView.setText(item);
        return v;

    }
}
