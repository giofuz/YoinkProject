package com.example.cyncyn.YoinkProject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;

/**
 * Created by Giovanni Fusciardi & Luke Doolin for 3rd year project
 IADT multimedia programming on 08/01/16.
 */
public class SavedListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_list);

//        ActionBar actionBar = getSupportActionBar();
//        actionBar.setDisplayHomeAsUpEnabled(true);
//        actionBar.setIcon(R.drawable.mark1);

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

    public void btnClickHandler(View view) {

        Intent registerIntent = new Intent(this, MapsActivity.class);
        startActivity(registerIntent);

    }

}
