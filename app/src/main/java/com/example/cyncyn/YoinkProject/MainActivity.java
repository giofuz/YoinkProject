package com.example.cyncyn.YoinkProject;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toolbar;

/**
 * Created by Giovanni Fusciardi & Luke Doolin for 3rd year project
 IADT multimedia programming.
 */

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Toolbar myToolbar = (Toolbar)findViewById(R.id.my_toolbar);
//        setSupportActionBar(myToolbar);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;

//        MenuInflater mif = getMenuInflater();
//        mif.inflate(R.menu.menu_main, menu);
//        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent registerIntent = new Intent(this, SettingsActivity.class);
            startActivity(registerIntent);
        }

        if (id == R.id.action_map) {

            Intent registerIntent = new Intent(this, MapsActivity.class);
            startActivity(registerIntent);

        }

        if (id == R.id.action_saved_list) {

            Intent registerIntent = new Intent(this,LoginActivity.class);
            startActivity(registerIntent);

        }

        return super.onOptionsItemSelected(item);
    }



    public void btnClickHandler(View view) {

        Intent registerIntent = new Intent(this, MapsActivity.class);
        startActivity(registerIntent);

    }

    public void btnClickHandler2(View view) {

        Intent registerIntent = new Intent(this, DealActivity.class);
        startActivity(registerIntent);

    }





    //    @Override
//    public void onConnected(Bundle connectionHint) {
//        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
//                mGoogleApiClient);
//        if (mLastLocation != null) {
//            mLatitudeText.setText(String.valueOf(mLastLocation.getLatitude()));
//            mLongitudeText.setText(String.valueOf(mLastLocation.getLongitude()));
//        }
//    }
}
