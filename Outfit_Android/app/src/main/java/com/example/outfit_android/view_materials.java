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

public class view_materials extends AppCompatActivity {

    SharedPreferences sh;
    ListView l1;
    String url;
    ArrayList<String> arr1, arr2, arr3, arr4, arr5, arr6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_materials);
        sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        l1 = findViewById(R.id.L12);


        url = "http://" + sh.getString("ip", "") + ":5000/view_materials";
        RequestQueue queue = Volley.newRequestQueue(view_materials.this);

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
                    arr6 = new ArrayList<>();

                    for (int i=0; i<ar.length(); i++) {
                        JSONObject jo = ar.getJSONObject(i);
                        arr1.add(jo.getString("material"));
                        arr2.add(jo.getString("photo"));
                        arr3.add(jo.getString("price"));
                        arr4.add(jo.getString("stock"));
                        arr5.add(jo.getString("details"));
                        arr6.add(jo.getString("material_id"));
                    }

                    // ArrayAdapter<String> ad=new ArrayAdapter<>(Home.this,android.R.layout.simple_list_item_1,name);
                    //lv.setAdapter(ad);

                    l1.setAdapter(new custom_view_materials(view_materials.this, arr1, arr2, arr3, arr4, arr5, arr6));
//                    l1_send_product_request.setOnItemClickListener(send_product_request.this);

                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(),"========="+e,Toast.LENGTH_LONG).show();
                    Log.d("=========", e.toString());
                }


            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(view_materials.this, "err" + error, Toast.LENGTH_SHORT).show();
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
        Intent ik = new Intent(getApplicationContext(), view_designs.class);
        startActivity(ik);
    }

}