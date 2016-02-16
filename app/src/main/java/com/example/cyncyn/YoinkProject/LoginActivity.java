package com.example.cyncyn.YoinkProject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


/**
 * Android login screen Activity
 */
public class LoginActivity extends AppCompatActivity {

    public static String KEY_USERNAME;
    private EditText username,password;
    private Button login;
    private RequestQueue requestQueue;
    private static final String URL = "http://www.yoink.netne.net/user_control.php";
    private StringRequest request;
    private Button sign_up;
    public static boolean loggedIn = false;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.sign_in);

        requestQueue = Volley.newRequestQueue(this);



        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            if(jsonObject.names().get(0).equals("success")){
                                Toast.makeText(getApplicationContext(), "SUCCESS " + jsonObject.getString("success"), Toast.LENGTH_SHORT).show();
                                openProfile();
                                loggedIn = true;
                                //int id = jsonObject.getInt("id");
                            }else {
                                Toast.makeText(getApplicationContext(), "Error" +jsonObject.getString("error"), Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        HashMap<String,String> hashMap = new HashMap<String, String>();
                        hashMap.put("username", username.getText().toString());
                        hashMap.put("password",password.getText().toString());

                        return hashMap;
                    }
                };

                requestQueue.add(request);
            }
        });

        sign_up = (Button) findViewById(R.id.signUp_btn);
        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
            }
        });


    }

    private void openProfile (){
        Intent intent = new Intent(this, SavedListActivity.class);
        intent.putExtra(KEY_USERNAME, username.getText().toString());
        startActivity(intent);
    }


}