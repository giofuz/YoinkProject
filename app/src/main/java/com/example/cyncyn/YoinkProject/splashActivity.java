package com.example.cyncyn.YoinkProject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by Giovanni Fusciardi & Luke Doolin for 3rd year project
 IADT multimedia programming on 08/01/16.
 */

public class splashActivity extends Activity {

    private static String TAG = splashActivity.class.getName();
    private static int SLEEP_TIME = 3; //sleep for some time


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.requestWindowFeature(Window.FEATURE_NO_TITLE); //removes title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN); //removes notifications bar

        setContentView(R.layout.splash);

        IntentLauncher launcher = new IntentLauncher();
        launcher.start();

    }

        private class IntentLauncher extends Thread{

            @Override
            //start for some time and start new activity
            public void run(){
                try {
                    Thread.sleep(SLEEP_TIME*1000);
                }catch (Exception e){
                    Log.e(TAG, e.getMessage());
                }

                Intent intent = new Intent(splashActivity.this, LoginActivity.class);
                splashActivity.this.startActivity(intent);
                splashActivity.this.finish();

            }

        }
}
