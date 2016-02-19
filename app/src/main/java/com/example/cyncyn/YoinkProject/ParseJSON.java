package com.example.cyncyn.YoinkProject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/**
 * Created by Giovanni Fusciardi & Luke Doolin for 3rd year project
 * IADT multimedia programming on 18/02/16.
 */
public class ParseJSON {
    public static String[] deal_id;
    public static String[] deal_descrp;
    public static String[] deal_cat;
    public static String[] business_name;
    public static String[] business_lat;
    public static String[] business_long;

    public static final String JSON_ARRAY = "result";
    public static final String KEY_DEALID = "dealID";
    public static final String KEY_DESCRIPT = "deal_description";
    public static final String KEY_CATEGORY = "deal_category";
    public static final String KEY_BIZNAME = "business_name";
    public static final String KEY_LAT = "business_lat";
    public static final String KEY_LONG = "business_long";

    private JSONArray deal = null;

    private String json;

    public ParseJSON(String json){
        this.json = json;
    }

    protected void parseJSON(){
        JSONObject jsonObject=null;
        try {
            jsonObject = new JSONObject(json);
            deal = jsonObject.getJSONArray(JSON_ARRAY);

            deal_id = new String[deal.length()];
            deal_descrp = new String[deal.length()];
            deal_cat = new String[deal.length()];
            business_name = new String[deal.length()];
            business_lat = new String[deal.length()];
            business_long = new String[deal.length()];

            for(int i=0;i< deal.length();i++){
                JSONObject jo = deal.getJSONObject(i);
                deal_id[i] = jo.getString(KEY_DEALID);
                deal_descrp[i] = jo.getString(KEY_DESCRIPT);
                deal_cat[i] = jo.getString(KEY_CATEGORY);
                business_name[i] = jo.getString(KEY_BIZNAME);
                business_lat[i] = jo.getString(KEY_LAT);
                business_long[i] = jo.getString(KEY_LONG);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
