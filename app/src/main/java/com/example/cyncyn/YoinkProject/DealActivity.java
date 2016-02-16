package com.example.cyncyn.YoinkProject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.Menu;
import android.view.View;

public class DealActivity extends FragmentActivity {

	public static final String EXTRA_DEAL_ID = "dealId";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_deal);
		
		FragmentManager fm = getSupportFragmentManager();
		Fragment fragment = fm.findFragmentById(R.id.fragmentContainer);
		
		if (fragment == null) {
			Intent i = getIntent();
			int dealId = (Integer)i.getSerializableExtra(EXTRA_DEAL_ID);

			fragment = DealFragment.newInstance(dealId);

			fm.beginTransaction()
			  .add(R.id.fragmentContainer, fragment)
			  .commit();
		}
	}

	public boolean onCreateOptionsMenu(Menu menu) {
		//Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_main, menu);
		return true;

//        MenuInflater mif = getMenuInflater();
//        mif.inflate(R.menu.menu_main, menu);
//        return super.onCreateOptionsMenu(menu);
	}

	public void btnClickHandler(View view) {

		Intent registerIntent = new Intent(this, LoginActivity.class);
		startActivity(registerIntent);

	}
}
