package com.example.cyncyn.YoinkProject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

public class DealActivity extends FragmentActivity {

	public static final String EXTRA_BOOK_ID = "book_id";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_book);
		
		FragmentManager fm = getSupportFragmentManager();
		Fragment fragment = fm.findFragmentById(R.id.fragmentContainer);
		
		if (fragment == null) {
			Intent i = getIntent();
			int bookId = (Integer)i.getSerializableExtra(EXTRA_BOOK_ID);

			fragment = DealFragment.newInstance(bookId);

			fm.beginTransaction()
			  .add(R.id.fragmentContainer, fragment)
			  .commit();
		}
	}
}
