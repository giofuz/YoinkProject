package com.example.cyncyn.YoinkProject;

import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements GoogleMap.OnInfoWindowClickListener {

    private static final int infoBox = 0;
    private static final int GPS_ERRORDIALOG_REQUEST = 9001;
    @SuppressWarnings("unused")
    private static final int CONNECTION_FAILURE_RESOLUTION_REQUEST = 9002;
    GoogleMap mMap;
    private static final float DEFAULTZOOM = 10;
    private static final double lat = 53.343510;
    private static final double lng = -6.264937;
    Address add;
    List<Marker> list = new ArrayList<>();

    private static final String TAG = "DealWebApp";                             //Name of application on XAMPP
    private static final String API_URL = "http://192.168.1.24:80/YoinkwebApp/api/";
    //private static final String API_URL = "http://www.yoink.netne.net/api/"; //Add in Local URL if in college or at home
    private List<Deal> mDeals;
    String categoryName = "";



    private class HttpRequestTask extends AsyncTask<HttpRequest, Integer, HttpResponse> {

        @Override
        public HttpResponse doInBackground(HttpRequest... requests) {
            HttpClient client;
            HttpRequest request;
            HttpResponse response = null;

            client = new HttpClient();
            request = requests[0];
            try {
                response = client.execute(request);
            } catch (HttpException e) {
                String errorMessage = "Error downloading deal list: " + e.getMessage();
                Log.d(TAG, "HttpClient: " + errorMessage);
            }

            return response;
        }

        @Override
        public void onPostExecute(HttpResponse response) {
            try {
                onHttpResponse(response);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String urlString = null;
        URI uri;
        HttpRequest request;
        HttpRequestTask t;

        try {
            urlString = API_URL + "/Deal";
            uri = new URI(urlString);

            request = new HttpRequest("GET", uri);
            t = new HttpRequestTask();
            t.execute(request);
        } catch (URISyntaxException e) {
            String errorMessage = "Error parsing uri (" + urlString + "): " + e.getMessage();
            Log.d(TAG, "HttpClient: " + errorMessage);
        }

        if (servicesOK()) {
            setContentView(R.layout.activity_maps);

            if (initMap()) {
                if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                mMap.setMyLocationEnabled(true);
                gotoLocation(lat,lng,DEFAULTZOOM);

            } else {
                Toast.makeText(this, "Map not available!", Toast.LENGTH_SHORT).show();
            }
        } else {
            setContentView(R.layout.activity_main);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.maps_menu, menu);
        return true;
    }

    public boolean servicesOK() {
        int isAvailable = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);

        if (isAvailable == ConnectionResult.SUCCESS) {
            return true;
        } else if (GooglePlayServicesUtil.isUserRecoverableError(isAvailable)) {
            Dialog dialog = GooglePlayServicesUtil.getErrorDialog(isAvailable, this, GPS_ERRORDIALOG_REQUEST);
            dialog.show();
        } else {
            Toast.makeText(this, "Can't connect to Google Play services", Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    private boolean initMap() {
        if (mMap == null) {
            SupportMapFragment mapFragment =
                    (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
            mMap = mapFragment.getMap();

            if (mMap != null) {
                //This allows my to click on the infoWindow and implement onInfoWindowClick()
                mMap.setOnInfoWindowClickListener(this);
                mMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
                    @Override
                    public View getInfoWindow(Marker marker) {
                        return null;
                    }

                    @Override
                    public View getInfoContents(Marker marker) {

                        View v = getLayoutInflater().inflate(R.layout.info_window, null);
                        TextView tvTitle = (TextView) v.findViewById(R.id.tv_Title);
                        TextView tvDeal = (TextView) v.findViewById(R.id.tv_deal);
                        //TextView tvLat = (TextView) v.findViewById(R.id.tv_lat);
                        //TextView tvLng = (TextView) v.findViewById(R.id.tv_lng);

                        LatLng ll = marker.getPosition();

                        tvTitle.setText(marker.getTitle());
                        tvDeal.setText(marker.getSnippet());
                        //tvLat.setText("Latitude: " + ll.latitude);
                        //tvLng.setText("Longitude: " + ll.longitude);


                        return v;
                    }
                });

            }
        }
        return (mMap != null);

    }

    public void onInfoWindowClick(Marker marker) {
        Toast.makeText(getBaseContext(),
                "Click the arrow for directions",
                Toast.LENGTH_SHORT).show();
        //+ marker.getId() for the toast

        //Intent intent = new Intent(MapsActivity.this, DealActivity.class);
        //intent.putExtra(DealActivity.EXTRA_DEAL_ID, marker.getTitle());

    }


    private void gotoLocation(double lat, double lng,
                              float DEFAULTZOOM) {
        LatLng ll = new LatLng(lat, lng);
        CameraUpdate update = CameraUpdateFactory.newLatLngZoom(ll, DEFAULTZOOM);
        mMap.moveCamera(update);
    }

    public void geoLocate(View v) throws IOException {

        hideSoftKeyboard(v);

        TextView tv = (TextView) findViewById(R.id.editText1);
        String searchString = tv.getText().toString();

//        if(categoryName == searchString){
//            Toast.makeText(MapsActivity.this,
//                "Category Selected",
//                Toast.LENGTH_SHORT).show();
//        }

        Geocoder gc = new Geocoder(this);
        List<Address> list = gc.getFromLocationName(searchString, 1);

        if (list.size() > 0) {
            Address add = list.get(0);
            double lat = add.getLatitude();
            double lng = add.getLongitude();
            gotoLocation(lat, lng, 15);

        }

    }
//
//    public void geoLocate() throws IOException {
//
//        TextView view = (TextView) findViewById(R.id.editText1);
//        String location = view.getText().toString();
//
//        Geocoder gc = new Geocoder(this);
//        List<Address> list = gc.getFromLocationName(location, 1);
//        Address add = list.get(0);
//        String locality = add.getLocality();
//
//        String i= location;
//        view.setText(i);
//
//    }
//
    private void hideSoftKeyboard(View v) {
        InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
    }

    @Override
    protected void onStop() {
        super.onStop();
        MapStateManager mgr = new MapStateManager(this);
        mgr.saveMapState(mMap);
    }

    @Override
    protected void onResume() {
        super.onResume();
        MapStateManager mgr = new MapStateManager(this);
        CameraPosition position = mgr.getSavedCameraPosition();
        if (position != null) {
            CameraUpdate update = CameraUpdateFactory.newCameraPosition(position);
            mMap.moveCamera(update);

            mMap.setMapType(mgr.getSavedMapType());
        }
    }
//
//    protected void gotoCurrentLocation() {
//
//    }
//
//    public void setMarker(Address add, double lat, double lng) {
//        MarkerOptions options = new MarkerOptions()
//                .title(add.getLocality())
//                .position(new LatLng(lat, lng))
//                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_action_location));
//
//        String country = add.getCountryName();
//        if (country.length() > 0) {
//            options.snippet(country);
//        }
//
//        marker = mMap.addMarker(options);
//    }


    void onHttpResponse(HttpResponse response) throws JSONException {
        // De-serialize the JSON string into an array of city objects

        JSONArray jsonArray;
        JSONObject jsonObject;
        Deal deal;



        if (response != null) {
            if (response.getStatus() == 200) {
                String body = response.getBody();
                jsonArray = new JSONArray(body);
                mDeals = new ArrayList<Deal>();
                for (int i = 0; i != jsonArray.length(); i++) {
                    jsonObject = jsonArray.getJSONObject(i);
                    Marker marker = mMap.addMarker(new MarkerOptions()
                                    .icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_launcher))
                                    .title(jsonObject.getString("business_name"))
                                    .snippet(jsonObject.getString("deal_description"))
                                    .position(new LatLng(
                                            jsonObject.getDouble("business_lat"),
                                            jsonObject.getDouble("business_long")

                                    ))
                    );
                    for (int t = 0; t != jsonArray.length(); t++) {
                        jsonObject = jsonArray.getJSONObject(t);
                        int id = jsonObject.getInt("dealId");
                        String description = jsonObject.getString("deal_description");
                        String category = jsonObject.getString("deal_category");
                        String businessName = jsonObject.getString("business_name");
                        String businessAddress = jsonObject.getString("business_address");
                        double latitude = jsonObject.getDouble("business_lat");
                        double longitude = jsonObject.getDouble("business_long");

                        deal = new Deal(id, description, category, businessName, businessAddress, latitude, longitude);

                        mDeals.add(deal);

                    }
                    for(int t = 0; t < mDeals.size(); t++) {
                        categoryName = mDeals.get(t).getCategory();
                    }

                    list.add(marker);
                    //Toast.makeText(MapsActivity.this,categoryName, Toast.LENGTH_SHORT).show();



                    //categoryName = marker.getSnippet();


                }
            }


        }
    }


}

