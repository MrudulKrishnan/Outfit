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
import android.widget.RatingBar;
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

public class send_rating extends AppCompatActivity {

    RatingBar r1;
    EditText e1;
    Button b1;
    String rating, review, url;
    SharedPreferences sh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_rating);
        sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        r1 = findViewById(R.id.ratingBar);
        e1 = findViewById(R.id.editTextTextPersonName2);
        b1 = findViewById(R.id.button11);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rating = String.valueOf(r1.getRating());
                review = e1.getText().toString();


                if (review.equalsIgnoreCase("")) {
                    e1.setError("Enter Your review");
                } else {
                    RequestQueue queue = Volley.newRequestQueue(send_rating.this);
                    url = "http://" + sh.getString("ip", "") + ":5000/send_rating";

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
                                    Toast.makeText(send_rating.this, "rating sent successfully ", Toast.LENGTH_SHORT).show();
                                    Intent ik = new Intent(getApplicationContext(), user_home.class);
                                    startActivity(ik);
                                } else {
                                    Toast.makeText(send_rating.this, "please try again", Toast.LENGTH_SHORT).show();
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
                            params.put("rating", rating);
                            params.put("review", review);
                            params.put("lid", sh.getString("user_lid", ""));
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
        Intent ik = new Intent(getApplicationContext(), user_home.class);
        startActivity(ik);
    }

}