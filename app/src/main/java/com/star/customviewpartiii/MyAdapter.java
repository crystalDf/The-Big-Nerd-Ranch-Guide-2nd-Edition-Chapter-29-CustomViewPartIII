package com.star.customviewpartiii;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class MyAdapter extends ArrayAdapter<String> {

    private int mResource;

    public MyAdapter(Context context, int resource, List<String> objects) {
        super(context, resource, objects);

        mResource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext())
                    .inflate(mResource, null);
        }

        TextView textView = (TextView) convertView.findViewById(R.id.text_view);
        textView.setText(getItem(position));

        return convertView;
    }

}
