package com.example.cyncyn.YoinkProject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DealActivity extends FragmentActivity {

	DealFragment mDeal;
	public static final String EXTRA_DEAL_ID = "dealId";
	private static final float DEFAULTZOOM = 10;
	public Double ZOOM = 5.5;
	boolean mShowmap;
	GoogleMap mMap;

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

		mShowmap = GooglePlayServiceUtility.isPlayServiceAvailable(this)&& init();

		displayDealMarker();

	}

	public LatLng getLatLng() {
		LatLng latLng = new LatLng(53.347207, -6.259103 );
		return latLng;
	}

	private boolean init() {
		if(mMap == null){
			MapFragment mapFrag = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
			mMap = mapFrag.getMap();
		}
		return (mMap != null);
	}

	public boolean onCreateOptionsMenu(Menu menu) {
		//Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_deal, menu);
		return true;

//        MenuInflater mif = getMenuInflater();
//        mif.inflate(R.menu.menu_main, menu);
//        return super.onCreateOptionsMenu(menu);
	}

	private void displayDealMarker(){

		if (mShowmap){
			CameraUpdate update = CameraUpdateFactory.newLatLngZoom(getLatLng(), DEFAULTZOOM);
			mMap.moveCamera(update);

//			String markerTitle = mDeal.getBusinessName().equals("") ?
//					mDeal.getBusinessName() :
//					mDeal.getBusinessAddress();

			mMap.addMarker(new MarkerOptions()
					.position(getLatLng())
					.anchor(.5f, .5f)
					.icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_launcher))
						);
		}
	}



	public void read(View v) {
		Intent read_intent = new Intent(DealActivity.this, MainActivity.class);
		startActivity(read_intent);
	}

	public void btnClickHandler(View view) {

		Intent registerIntent = new Intent(this, RegisterActivity.class);
		startActivity(registerIntent);

	}
}
