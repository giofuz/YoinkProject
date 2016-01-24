package com.example.cyncyn.YoinkProject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;


/**
 * Android login screen Activity
 */
public class RegisterActivity extends Activity  {

    private TextView signUpTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        signUpTextView = (TextView) findViewById(R.id.signUpTextView);
        signUpTextView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("LoginActivity", "Sign Up Activity activated.");
                // this is where you should start the signup Activity
                RegisterActivity.this.startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });
    }


}