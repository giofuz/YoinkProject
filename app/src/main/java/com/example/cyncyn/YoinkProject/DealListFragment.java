package com.example.cyncyn.YoinkProject;

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

public class DealListFragment extends ListFragment {
    private static final String API_URL = "http://192.168.1.24:80/YoinkWebApp/api/";
    //private final String API_URL = "http://www.yoink.netne.net/api/";
    private final String TAG = "DealWebApp";

    private List<Deal> mDeals;

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
            urlString = API_URL + "/Deal";
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
        Deal deal;

        if (response != null) {
            if (response.getStatus() == 200) {
                String body = response.getBody();
                try {
                    jsonArray = new JSONArray(body);
                    mDeals = new ArrayList<Deal>();
                    for (int i = 0; i != jsonArray.length(); i++) {
                        jsonObject = jsonArray.getJSONObject(i);

                        int id = jsonObject.getInt("dealId");
                        String description = jsonObject.getString("deal_description");
                        String category = jsonObject.getString("deal_category");
                        String businessName = jsonObject.getString("business_name");
                        String businessAddress = jsonObject.getString("business_address");
                        double latitude = jsonObject.getDouble("business_lat");
                        double longitude = jsonObject.getDouble("business_long");

                        deal = new Deal(id, description, category, businessName, businessAddress, latitude, longitude);

                        mDeals.add(deal);
                    }
                }
                catch (JSONException e) {
                    String message = "Error retrieving deals: " + e.getMessage();
                    Toast.makeText(this.getActivity(), message, Toast.LENGTH_LONG).show();
                }

                DealStore store = DealStore.getInstance();
                store.setDeals(mDeals);

                ListAdapter adapter = new DealListAdapter(getActivity(), mDeals);

                setListAdapter(adapter);
            }
            else {
                Log.d(TAG, "Http Response: " + response.getStatus() + " " + response.getDescription());
            }
        }
    }
}
