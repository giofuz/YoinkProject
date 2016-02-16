package com.example.cyncyn.YoinkProject;

/**
 * Created by Giovanni Fusciardi & Luke Doolin for 3rd year project
 * IADT multimedia programming on 06/02/16.
 */
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class UserListFragment extends ListFragment {
    private final String API_URL = "http://192.168.1.24/DealWebApp/api/";
    private final String TAG = "DealWebApp";

    private List<User> mUsers;

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
            }
            catch (HttpException e) {
                String errorMessage = "Error downloading deal list: " + e.getMessage();
                Log.d(TAG, "HttpClient: " + errorMessage);
            }

            return response;
        }

        @Override
        public void onPostExecute(HttpResponse response) {
            onHttpResponse(response);
        }
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String urlString = null;
        URI uri;
        HttpRequest request;
        HttpRequestTask t;

        try {
            urlString = API_URL + "/users";
            uri = new URI(urlString);

            request = new HttpRequest("GET", uri);
            t = new HttpRequestTask();
            t.execute(request);
        }
        catch (URISyntaxException e) {
            String errorMessage = "Error parsing uri (" + urlString + "): " + e.getMessage();
            Log.d(TAG, "HttpClient: " + errorMessage);
        }
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Deal b = (Deal)(getListAdapter()).getItem(position);
        Intent i = new Intent(getActivity(), DealActivity.class);
        i.putExtra(DealActivity.EXTRA_DEAL_ID, b.getId());
        startActivity(i);
    }

    public void onHttpResponse(HttpResponse response) {
        JSONArray jsonArray;
        JSONObject jsonObject;
        User user;

        if (response != null) {
            if (response.getStatus() == 200) {
                String body = response.getBody();
                try {
                    jsonArray = new JSONArray(body);
                    mUsers = new ArrayList<User>();
                    for (int i = 0; i != jsonArray.length(); i++) {
                        jsonObject = jsonArray.getJSONObject(i);

                        int id = jsonObject.getInt("dealId");
                        String username = jsonObject.getString("username");
                        String password = jsonObject.getString("password");

                        user = new User(id, username, password);

                        mUsers.add(user);
                    }
                }
                catch (JSONException e) {
                    String message = "Error retrieving deals: " + e.getMessage();
                    Toast.makeText(this.getActivity(), message, Toast.LENGTH_LONG).show();
                }

            }
            else {
                Log.d(TAG, "Http Response: " + response.getStatus() + " " + response.getDescription());
            }
        }
    }
}
