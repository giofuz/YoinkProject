package com.example.cyncyn.YoinkProject;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;



public class CustomList extends ArrayAdapter<String> {
    private String[] deal_id;
    private String[] deal_descrp;
    private String[] deal_cat;
    private Activity context;

    public CustomList(Activity context, String[] id, String[] descprt, String[] category) {
        super(context, R.layout.list_view_layout, id);
        this.context = context;
        this.deal_id = id;
        this.deal_descrp = descprt;
        this.deal_cat = category;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.list_view_layout, null, true);
        TextView textViewId = (TextView) listViewItem.findViewById(R.id.textViewId);
        TextView textViewName = (TextView) listViewItem.findViewById(R.id.textViewName);
        TextView textViewEmail = (TextView) listViewItem.findViewById(R.id.textViewEmail);

        textViewId.setText(deal_id[position]);
        textViewName.setText(deal_descrp[position]);
        textViewEmail.setText(deal_cat[position]);

        return listViewItem;
    }
}