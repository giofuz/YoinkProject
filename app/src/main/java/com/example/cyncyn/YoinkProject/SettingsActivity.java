package com.example.cyncyn.YoinkProject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;

/**
 * Created by Giovanni Fusciardi & Luke Doolin for 3rd year project
 * IADT multimedia programming on 08/01/16.
 */
public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

//        ActionBar actionBar = getSupportActionBar();
//        actionBar.setDisplayHomeAsUpEnabled(true);
//        actionBar.setIcon(R.drawable.mark1);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;

    }
}
