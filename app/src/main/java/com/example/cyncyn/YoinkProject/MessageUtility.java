package com.example.cyncyn.YoinkProject;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Giovanni Fusciardi & Luke Doolin for 3rd year project
 * IADT multimedia programming on 19/02/16.
 */
public class MessageUtility {

    public static void displayToast(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }
}
