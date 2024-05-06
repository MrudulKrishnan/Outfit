package com.example.outfit_android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class view_designs extends AppCompatActivity {

    SharedPreferences sh;
    ListView l1;
    String url;
    ArrayList<String> arr1, arr2, arr3, arr4, arr5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_designs);
        sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        l1 = findViewById(R.id.L12);


        url = "http://" + sh.getString("ip", "") + ":5000/view_design";
        RequestQueue queue = Volley.newRequestQueue(view_designs.this);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
//                Toast.makeText(user_view_product.this, ""+response, Toast.LENGTH_SHORT).show();
                // Display the response string.
                Log.d("+++++++++++++++++", response);
                try {
//                    Toast.makeText(view_product.this, "error1" + response, Toast.LENGTH_SHORT).show();
                    JSONArray ar = new JSONArray(response);

                    arr1 = new ArrayList<>();
                    arr2 = new ArrayList<>();
                    arr3 = new ArrayList<>();
                    arr4 = new ArrayList<>();
                    arr5 = new ArrayList<>();

                    for (int i=0; i<ar.length(); i++) {
                        JSONObject jo = ar.getJSONObject(i);
                        arr1.add(jo.getString("designer_name"));
                        arr2.add(jo.getString("design"));
                        arr3.add(jo.getString("type"));
                        arr4.add(jo.getString("description"));
                        arr5.add(jo.getString("design_id"));
                    }

                    // ArrayAdapter<String> ad=new ArrayAdapter<>(Home.this,android.R.layout.simple_list_item_1,name);
                    //lv.setAdapter(ad);

                    l1.setAdapter(new custom_view_designs(view_designs.this, arr1, arr2, arr3, arr4, arr5));
//                    l1_send_product_request.setOnItemClickListener(send_product_request.this);

                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(),"========="+e,Toast.LENGTH_LONG).show();
                    Log.d("=========", e.toString());
                }


            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(view_designs.this, "err" + error, Toast.LENGTH_SHORT).show();
            }
        }) {
            @NonNull
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("lid", sh.getString("lid",""));
                return params;
            }
        };
        queue.add(stringRequest);

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent ik = new Intent(getApplicationContext(), user_home.class);
        startActivity(ik);
    }

}