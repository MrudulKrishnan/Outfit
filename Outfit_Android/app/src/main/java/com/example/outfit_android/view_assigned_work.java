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

public class view_assigned_work extends AppCompatActivity implements AdapterView.OnItemClickListener {

    SharedPreferences sh;
    ListView l1;
    String url, assign_id;
    ArrayList<String> arr1, arr2, arr3, arr4, arr5, arr6, arr7, arr8, arr9, arr10, arr11, arr12, arr13;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_assigned_work);
        sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        l1 = findViewById(R.id.L12);


        url = "http://" + sh.getString("ip", "") + ":5000/view_assigned_work";
        RequestQueue queue = Volley.newRequestQueue(view_assigned_work.this);

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
                    arr7 = new ArrayList<>();
                    arr8 = new ArrayList<>();
                    arr9 = new ArrayList<>();
                    arr10 = new ArrayList<>();
                    arr11 = new ArrayList<>();
                    arr12 = new ArrayList<>();
                    arr13 = new ArrayList<>();

                    for (int i=0; i<ar.length(); i++) {
                        JSONObject jo = ar.getJSONObject(i);
                        arr1.add(jo.getString("username"));
                        arr2.add(jo.getString("Gender"));
                        arr3.add(jo.getString("address"));
                        arr4.add(jo.getString("Phone"));
                        arr5.add(jo.getString("design"));
                        arr6.add(jo.getString("type"));
                        arr7.add(jo.getString("photo"));
                        arr8.add(jo.getString("material"));
                        arr9.add(jo.getString("date"));
                        arr10.add(jo.getString("status"));
                        arr11.add(jo.getString("details"));
                        arr12.add(jo.getString("user_photo"));
                        arr13.add(jo.getString("assign_id"));
                    }

                    // ArrayAdapter<String> ad=new ArrayAdapter<>(Home.this,android.R.layout.simple_list_item_1,name);
                    //lv.setAdapter(ad);

                    l1.setAdapter(new custom_view_assigned_work(view_assigned_work.this,
                            arr1, arr2, arr3, arr4, arr5, arr6, arr7, arr8, arr9, arr10, arr11, arr12));
                    l1.setOnItemClickListener(view_assigned_work.this);

                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(),"========="+e,Toast.LENGTH_LONG).show();
                    Log.d("=========", e.toString());
                }


            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(view_assigned_work.this, "err" + error, Toast.LENGTH_SHORT).show();
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
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        assign_id = arr13.get(position);

        Intent i1 = new Intent(getApplicationContext(), update_status.class);
        i1.putExtra("assign_id", arr13.get(position));
        startActivity(i1);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent ik = new Intent(getApplicationContext(), tailor_home.class);
        startActivity(ik);
    }

}