package com.example.outfit_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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

public class update_status extends AppCompatActivity {
    SharedPreferences sh;
    EditText e1;
    Button b1;
    String status, url;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_status);

        sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        e1 = findViewById(R.id.editTextTextPersonName3);
        b1 = findViewById(R.id.button14);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                status = e1.getText().toString();


                if (status.equalsIgnoreCase("")) {
                    e1.setError("Enter Your status");
                } else {
                    RequestQueue queue = Volley.newRequestQueue(update_status.this);
                    url = "http://" + sh.getString("ip", "") + ":5000/update_status";

                    // Request a string response from the provided URL.
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            // Display the response string.
                            Log.d("+++++++++++++++++", response);
                            try {
                                JSONObject json = new JSONObject(response);
                                String res = json.getString("task");

                                if (res.equalsIgnoreCase("success")) {
                                    Toast.makeText(update_status.this, "status updated successfully ", Toast.LENGTH_SHORT).show();
                                    Intent ik = new Intent(getApplicationContext(), tailor_home.class);
                                    startActivity(ik);
                                } else {
                                    Toast.makeText(update_status.this, "please try again", Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(getApplicationContext(), "Error" + error, Toast.LENGTH_LONG).show();
                        }
                    }) {
                        @Override
                        protected Map<String, String> getParams() {


                            Map<String, String> params = new HashMap<String, String>();
                            params.put("status", status);
                            params.put("assign_id", getIntent().getStringExtra("assign_id"));
                            return params;
                        }
                    };
                    queue.add(stringRequest);

                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent ik = new Intent(getApplicationContext(), view_assigned_work.class);
        startActivity(ik);
    }

}