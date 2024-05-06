package com.example.outfit_android;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

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

public class chat_with_designers extends AppCompatActivity implements AdapterView.OnItemClickListener {


    ListView l1_chat;
    SharedPreferences sh;
    ArrayList<String> name, designer_id;
    String url;
    String designer_id_str;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_with_designers);
        l1_chat=findViewById(R.id.L1_Chat);
        l1_chat.setOnItemClickListener(chat_with_designers.this);


        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        url ="http://"+sh.getString("ip", "") + ":5000/view_designers";
        RequestQueue queue = Volley.newRequestQueue(chat_with_designers.this);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // Display the response string.
                Log.d("+++++++++++++++++",response);
                try {

                    JSONArray ar=new JSONArray(response);
                    name= new ArrayList<>();
                    designer_id= new ArrayList<>();

                    for(int i=0;i<ar.length();i++)
                    {
                        JSONObject jo=ar.getJSONObject(i);
                        name.add(jo.getString("Name"));
                        designer_id.add(jo.getString("designer_id"));



                    }

                    ArrayAdapter<String> ad=new ArrayAdapter<>(chat_with_designers.this,android.R.layout.simple_list_item_1,name);
                    l1_chat.setAdapter(ad);

//                    L1.setAdapter(new custom2(chatwithfriends.this,name));


                } catch (Exception e) {
                    Log.d("=========", e.toString());
                }


            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(chat_with_designers.this, "err"+error, Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("lid",sh.getString("user_lid",""));

                return params;
            }
        };
        queue.add(stringRequest);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        designer_id_str = designer_id.get(position);
        SharedPreferences.Editor edp = sh.edit();
        edp.putString("designer_id", designer_id_str);
        edp.commit();

        Intent i=new Intent(getApplicationContext(), Test1.class);
        i.putExtra("toid",designer_id.get(position));
        i.putExtra("name",name.get(position));

        startActivity(i);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent ik = new Intent(getApplicationContext(), user_home.class);
        startActivity(ik);
    }

}