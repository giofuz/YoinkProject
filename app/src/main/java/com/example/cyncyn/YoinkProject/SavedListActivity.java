package com.example.cyncyn.YoinkProject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Giovanni Fusciardi & Luke Doolin for 3rd year project
 IADT multimedia programming on 08/01/16.
 */
public class SavedListActivity extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_list);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragmentContainer);

        if (fragment == null) {
            fragment = new DealListFragment();
            fm.beginTransaction()
                    .add(R.id.fragmentContainer, fragment)
                    .commit();
        }

        //textView = (TextView) findViewById(R.id.textView3);
        //Intent intent = getIntent();
        //textView.setText("Welcome " + LoginActivity.KEY_USERNAME);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        //Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_saved_list, menu);
        return true;

//        MenuInflater mif = getMenuInflater();
//        mif.inflate(R.menu.menu_main, menu);
//        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.saved_list_settings) {
            Intent registerIntent = new Intent(this, MainActivity.class);
            startActivity(registerIntent);
            LoginActivity.loggedIn = false;
        }

        return super.onOptionsItemSelected(item);
    }


}
