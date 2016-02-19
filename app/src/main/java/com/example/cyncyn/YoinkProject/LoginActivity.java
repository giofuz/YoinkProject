package com.example.cyncyn.YoinkProject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;


/**
 * Android login screen Activity
 */
public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    public static final String LOGIN_URL = "http://192.168.1.24:80/YoinkWebApp/volley_login.php";

    public static final String KEY_USERNAME="username";
    public static final String KEY_PASSWORD="password";
    //public static boolean loggedIn = false;

    private EditText editTextUsername;
    private EditText editTextPassword;
    private Button buttonLogin;
    private Button buttonRegister;

    private String username;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextUsername = (EditText) findViewById(R.id.editTextUsername);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);

        buttonLogin = (Button) findViewById(R.id.buttonLogin);
        buttonLogin.setOnClickListener(this);

        buttonRegister = (Button) findViewById(R.id.Register_btn);
        buttonRegister.setOnClickListener(this);
    }


    private void userLogin() {
        username = editTextUsername.getText().toString().trim();
        password = editTextPassword.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, LOGIN_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.trim().equals("success")){
                            openProfile();
                            //loggedIn = true;
                            Toast.makeText(LoginActivity.this,"Welcome "+username,Toast.LENGTH_LONG).show();
                        }else{
                            Toast.makeText(LoginActivity.this,"Login details incorrect",Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(LoginActivity.this,error.toString(),Toast.LENGTH_LONG ).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map = new HashMap<String,String>();
                map.put(KEY_USERNAME,username);
                map.put(KEY_PASSWORD,password);
                return map;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void openProfile(){
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(KEY_USERNAME, username);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        if(v== buttonLogin){
            userLogin();
        }
        if(v == buttonRegister){
            Intent intent = new Intent(this, RegisterActivity.class);
            startActivity(intent);
        }
    }

}

//int id = jsonObject.getInt("id");
//intent.putExtra(KEY_USERNAME, username.getText().toString());
//private static final String URL = "http://192.168.1.24:80/DealWebApp/user_control.php";