package com.example.outfit_android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

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

public class send_custom_design extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    SharedPreferences sh;
    Spinner s1;
    EditText e1;
    Button b1;
    String details, url, designer_id;
    ArrayList<String> designer_arr, designer_id_arr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_custom_design);
        sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        s1 = findViewById(R.id.spinner);
        e1 = findViewById(R.id.editTextTextPersonName4);
        b1 = findViewById(R.id.button16);


        url = "http://" + sh.getString("ip", "") + ":5000/view_designers_drop";
        RequestQueue queue = Volley.newRequestQueue(send_custom_design.this);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
//                Toast.makeText(send_complaint.this, "eeeerr" + response, Toast.LENGTH_SHORT).show();

                // Display the response string.
                Log.d("+++++++++++++++++", response);
                try {
//                    Toast.makeText(send_complaint.this, "" + response, Toast.LENGTH_SHORT).show();
                    JSONArray ar = new JSONArray(response);

                    designer_arr = new ArrayList<>();
                    designer_id_arr = new ArrayList<>();

                    for (int i=0; i<ar.length(); i++) {
                        JSONObject jo = ar.getJSONObject(i);
                        designer_arr.add(jo.getString("Name"));
                        designer_id_arr.add(jo.getString("designer_id"));

                    }
//                    Toast.makeText(send_complaint.this, "err" + response, Toast.LENGTH_SHORT).show();


                     ArrayAdapter<String> ad=new ArrayAdapter<>(send_custom_design.this,android.R.layout.simple_list_item_1,designer_arr);
                    s1.setAdapter(ad);

//                    l1.setAdapter(new custom_send_complaint(send_custom_design.this,complaint_arr, reply_arr, date_arr));
                    s1.setOnItemSelectedListener(send_custom_design.this);

                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(),"========="+e,Toast.LENGTH_LONG).show();
                    Log.d("=========", e.toString());
                }

            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(send_custom_design.this, "err" + error, Toast.LENGTH_SHORT).show();
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
            public void onClick(View v) {

                details = e1.getText().toString();

                if (details.equalsIgnoreCase("")) {
                    e1.setError("Enter Your design details");
                } else {
                    RequestQueue queue = Volley.newRequestQueue(send_custom_design.this);
                    url = "http://" + sh.getString("ip", "") + ":5000/send_custom_design";

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
                                    Toast.makeText(send_custom_design.this, "design sent successfully ", Toast.LENGTH_SHORT).show();
                                    Intent ik = new Intent(getApplicationContext(), view_user_custom_design.class);
                                    startActivity(ik);
                                } else {
                                    Toast.makeText(send_custom_design.this, "please try again", Toast.LENGTH_SHORT).show();
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
                            params.put("details", details);
                            params.put("lid", sh.getString("user_lid", ""));
                            params.put("designer_id", designer_id);
                            return params;
                        }
                    };
                    queue.add(stringRequest);

                }
            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        designer_id = designer_id_arr.get(position);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}