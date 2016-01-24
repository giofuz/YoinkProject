package com.example.cyncyn.YoinkProject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;


/**
 * Android login screen Activity
 */
public class LoginActivity extends Activity  {

    private TextView signUpTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        signUpTextView = (TextView) findViewById(R.id.signUpTextView);
        signUpTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("LoginActivity", "Sign Up Activity activated.");
                // this is where you should start the signup Activity
                LoginActivity.this.startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });

    }

    public void btnClickHandler(View view) {

        Intent registerIntent = new Intent(this, SavedListActivity.class);
        startActivity(registerIntent);

    }


}