package com.example.cyncyn.YoinkProject;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.List;

public class DealListAdapter extends ArrayAdapter<Deal>
                             implements ListAdapter {

	private Activity mContext;
	private List<Deal> mDeals;
	
	public DealListAdapter(Context context, List<Deal> deals) {
		super(context, R.layout.list_item_book, deals);
		mContext = (Activity)context;
		mDeals = deals;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// If we weren't given a view, inflate one
        if (convertView == null) {
        	LayoutInflater inflater = mContext.getLayoutInflater();
            convertView = inflater.inflate(R.layout.list_item_book, null);
        }

        // Configure the view for this Crime
        Deal bk = mDeals.get(position);

        TextView titleTextView = (TextView)convertView.findViewById(
        		R.id.list_item_book_title_textView);
        titleTextView.setText(bk.getDescription());
        
        TextView yearTextView = (TextView)convertView.findViewById(
        		R.id.list_item_book_year_textView);
        yearTextView.setText("" + bk.getCategory());
        
        TextView authorTextView = (TextView)convertView.findViewById(
        		R.id.list_item_book_author_textView);
        authorTextView.setText(bk.getBusinessName());

        return convertView;
	}
}