package com.example.outfit_android;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class send_complaint extends AppCompatActivity {

    SharedPreferences sh;
    EditText e1;
    Button b1;
    ListView l1;
    String complaint, url;
    ArrayList<String> complaint_arr, reply_arr, date_arr;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_complaint);

        sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        e1 = findViewById(R.id.editTextTextPersonName13);
        b1 = findViewById(R.id.button8);
        l1 = findViewById(R.id.list1);


        url = "http://" + sh.getString("ip", "") + ":5000/complaints_reply";
        RequestQueue queue = Volley.newRequestQueue(send_complaint.this);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
//                Toast.makeText(send_complaint.this, "eeeerr" + response, Toast.LENGTH_SHORT).show();

                // Display the response string.
                Log.d("+++++++++++++++++", response);
                try {
//                    Toast.makeText(send_complaint.this, "" + response, Toast.LENGTH_SHORT).show();
                    JSONArray ar = new JSONArray(response);

                    complaint_arr = new ArrayList<>();
                    reply_arr = new ArrayList<>();
                    date_arr = new ArrayList<>();

                    for (int i=0; i<ar.length(); i++) {
                        JSONObject jo = ar.getJSONObject(i);
                        complaint_arr.add(jo.getString("complaint"));
                        reply_arr.add(jo.getString("reply"));
                        date_arr.add(jo.getString("date"));

                    }
//                    Toast.makeText(send_complaint.this, "err" + response, Toast.LENGTH_SHORT).show();


                    // ArrayAdapter<String> ad=new ArrayAdapter<>(Home.this,android.R.layout.simple_list_item_1,name);
                    //lv.setAdapter(ad);

                    l1.setAdapter(new custom_send_complaint(send_complaint.this,complaint_arr, reply_arr, date_arr));
//                    l1.setOnItemClickListener(view_assigned_work_boy.this);

                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(),"========="+e,Toast.LENGTH_LONG).show();
                    Log.d("=========", e.toString());
                }

            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(send_complaint.this, "err" + error, Toast.LENGTH_SHORT).show();
            }
        }) {
            @NonNull
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("lid", sh.getString("user_lid", ""));
                return params;
            }
        };
        queue.add(stringRequest);


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                complaint = e1.getText().toString();

                if (complaint.equalsIgnoreCase("")) {
                    e1.setError("Enter Your complaint");
                }
                else {
                    RequestQueue queue = Volley.newRequestQueue(send_complaint.this);
                    url = "http://" + sh.getString("ip", "") + ":5000/send_complaint";

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
                                    Toast.makeText(send_complaint.this, "Complaint sent successfully ", Toast.LENGTH_SHORT).show();
                                    Intent ik = new Intent(getApplicationContext(), send_complaint.class);
                                    startActivity(ik);
                                } else {
                                    Toast.makeText(send_complaint.this, "please try again", Toast.LENGTH_SHORT).show();
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
                            params.put("complaint", complaint);
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