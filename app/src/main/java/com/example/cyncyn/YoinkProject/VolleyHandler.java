package com.example.cyncyn.YoinkProject;

import android.app.Application;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by Giovanni Fusciardi & Luke Doolin for 3rd year project
 * IADT multimedia programming on 17/02/16.
 */
    public class VolleyHandler extends Application {

        private RequestQueue mRequestQueue;
        private static VolleyHandler mInstance;

        @Override
        public void onCreate() {
            super.onCreate();
            mInstance = this;
        }

        public static synchronized VolleyHandler getInstance() {
            return mInstance;
        }

        public RequestQueue getReqQueue() {
            if (mRequestQueue == null) {
                mRequestQueue = Volley.newRequestQueue(getApplicationContext());
            }

            return mRequestQueue;
        }

        public <T> void addToReqQueue(Request<T> req, String tag) {

            getReqQueue().add(req);
        }

        public <T> void addToReqQueue(Request<T> req) {

            getReqQueue().add(req);
        }

        public void cancelPendingReq(Object tag) {
            if (mRequestQueue != null) {
                mRequestQueue.cancelAll(tag);
            }
        }
    }
