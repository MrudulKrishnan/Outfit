package com.example.outfit_android;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

class custom_view_assigned_work extends BaseAdapter {
    private Context context;
    ArrayList<String> arr1, arr2, arr3, arr4, arr5, arr6, arr7, arr9, arr8, arr10, arr11,arr12;
    Button b1;
    SharedPreferences sh;
    String url;
    public custom_view_assigned_work(Context applicationContext, ArrayList<String> arg1,
                               ArrayList<String> arg2, ArrayList<String> arg3,
                               ArrayList<String> arg4, ArrayList<String> arg5,
                               ArrayList<String> arg6, ArrayList<String> arg7,
                               ArrayList<String> arg8, ArrayList<String> arg9,
                               ArrayList<String> arg10, ArrayList<String> arg11,
                               ArrayList<String> arg12)
    {
        // TODO Auto-generated constructor stub
        this.context = applicationContext;
        this.arr1 = arg1;
        this.arr2 = arg2;
        this.arr3 = arg3;
        this.arr4 = arg4;
        this.arr5 = arg5;
        this.arr6 = arg6;
        this.arr7 = arg7;
        this.arr8 = arg8;
        this.arr9 = arg9;
        this.arr10 = arg10;
        this.arr11 = arg11;
        this.arr12 = arg12;


        sh= PreferenceManager.getDefaultSharedPreferences(applicationContext);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return arr1.size();
    }

    @Override
    public Object getItem(int arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long getItemId(int arg0) {
        // TODO Auto-generated method stub
        return 0;
    }
    @Override
    public int getItemViewType(int arg0) {
        // TODO Auto-generated method stub
        return 0;
    }


    @Override
    public View getView(int position, View convertview, ViewGroup parent) {
        // TODO Auto-generated method stub
        LayoutInflater inflator=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;
        if(convertview==null)
        {
            gridView=new View(context);
            gridView=inflator.inflate(R.layout.activity_custom_view_assigned_work,null);

        }
        else
        {
            gridView=(View)convertview;

        }
        ///////////////////////
        if(android.os.Build.VERSION.SDK_INT>9)
        {
            StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        /////////////////////////////////

        TextView tv1 = (TextView) gridView.findViewById(R.id.T1);
        TextView tv2 = (TextView) gridView.findViewById(R.id.T2);
        TextView tv3 = (TextView) gridView.findViewById(R.id.T3);
        TextView tv4 = (TextView) gridView.findViewById(R.id.T4);
        TextView tv5 = (TextView) gridView.findViewById(R.id.T5);
        TextView tv6 = (TextView) gridView.findViewById(R.id.T6);
        TextView tv7 = (TextView) gridView.findViewById(R.id.T7);
        TextView tv8 = (TextView) gridView.findViewById(R.id.T8);
        ImageView im1 = (ImageView) gridView.findViewById(R.id.imageView1);
        ImageView im2 = (ImageView) gridView.findViewById(R.id.imageView2);
        ImageView im3 = (ImageView) gridView.findViewById(R.id.imageView);
//        b1 = gridView.findViewById(R.id.button22);

//        b1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Intent ik = new Intent(context, view_materials.class);
//                ik.putExtra("design_id", arr5.get(position));
//                SharedPreferences.Editor edp = sh.edit();
//                edp.putString("d_id", arr5.get(position));
//                edp.commit();
//                context.startActivity(ik);
//
//            }
//        });

//        b2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                RequestQueue queue = Volley.newRequestQueue(context);
//                url = "http://" + sh.getString("ip", "") + ":5000/delete_product";
//
//                // Request a string response from the provided URL.
//                StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        // Display the response string.
//                        Log.d("+++++++++++++++++", response);
//                        try {
//                            JSONObject json = new JSONObject(response);
//                            String res = json.getString("task");
//
//                            if (res.equalsIgnoreCase("success")) {
//                                Toast.makeText(context, "successfully deleted", Toast.LENGTH_SHORT).show();
//                                Intent ik = new Intent(context.getApplicationContext(), send_doubt.class);
//                                context.startActivity(ik);
//                            } else {
//                                Toast.makeText(context, "please try again", Toast.LENGTH_SHORT).show();
//                            }
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//
//                }, new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Toast.makeText(context.getApplicationContext(), "Error" + error, Toast.LENGTH_LONG).show();
//                    }
//                }) {
//                    @Override
//                    protected Map<String, String> getParams() {
//
//                        Map<String, String> params = new HashMap<String, String>();
//                        params.put("product_id", arr5.get(position));
//                        return params;
//                    }
//                };
//                queue.add(stringRequest);
//
//
//            }
//        });
//
//        b1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                RequestQueue queue = Volley.newRequestQueue(context);
//                url = "http://"+sh.getString("ip","")+":5000/add_to_cart";
//                Toast.makeText(context,url , Toast.LENGTH_SHORT).show();
//                StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>()
//                {
//                    @Override
//                    public void onResponse(String response)
//                    {
//                        // Display the response string.
//                        Log.d("+++++++++++++++++", response);
//                        try
//                        {
//                            JSONObject json = new JSONObject(response);
//                            String res = json.getString("task");
//
//                            if (res.equalsIgnoreCase("success"))
//                            {
////                                String lid = json.getString("id");     // getting login id
////                                SharedPreferences.Editor edp = sh.edit();
////                                edp.putString("lid", lid);
////                                edp.commit();
//                                Intent ik = new Intent(context, view_product.class);
//                                context.startActivity(ik);
//                            }
//                            else
//                            {
//                                Toast.makeText(context, "Invalid request", Toast.LENGTH_SHORT).show();
//                            }
//                        }
//                        catch (JSONException e)
//                        {
//                            e.printStackTrace();
//                        }
//                    }
//                },new Response.ErrorListener()
//                {
//                    @Override
//                    public void onErrorResponse(VolleyError error)
//                    {
//                        Toast.makeText(context, "Error" + error, Toast.LENGTH_LONG).show();
//                    }
//                }) {
//                    @Override
//                    protected Map<String, String> getParams()
//                    {
//                        Map<String, String> params = new HashMap<String, String>();
//                        params.put("u_lid", sh.getString("user_lid", ""));
//                        params.put("product_id",arr6.get(position));
//                        params.put("quantity", str1);
//                        return params;
//                    }
//                };
//                queue.add(stringRequest);
//
//            }
//        });

        java.net.URL thumb_u;
        try {

            //thumb_u = new java.net.URL("http://192.168.43.57:5000/static/photo/flyer.jpg");

            thumb_u = new java.net.URL("http://"+sh.getString("ip","")+":5000"+arr12.get(position));
//            Toast.makeText(context, "yyyyyyyyyyyyyyyy"+thumb_u, Toast.LENGTH_SHORT).show();
            Drawable thumb_d = Drawable.createFromStream(thumb_u.openStream(), "src");
            im1.setImageDrawable(thumb_d);
        }
        catch (Exception e)
        {
            Log.d("errsssssssssssss",""+e);
        }

        try {

            //thumb_u = new java.net.URL("http://192.168.43.57:5000/static/photo/flyer.jpg");

            thumb_u = new java.net.URL("http://"+sh.getString("ip","")+":5000"+arr5.get(position));
//            Toast.makeText(context, "yyyyyyyyyyyyyyyy"+thumb_u, Toast.LENGTH_SHORT).show();
            Drawable thumb_d = Drawable.createFromStream(thumb_u.openStream(), "src");
            im2.setImageDrawable(thumb_d);
        }
        catch (Exception e)
        {
            Log.d("errsssssssssssss",""+e);
        }

        try {

            //thumb_u = new java.net.URL("http://192.168.43.57:5000/static/photo/flyer.jpg");

            thumb_u = new java.net.URL("http://"+sh.getString("ip","")+":5000"+arr7.get(position));
//            Toast.makeText(context, "yyyyyyyyyyyyyyyy"+thumb_u, Toast.LENGTH_SHORT).show();
            Drawable thumb_d = Drawable.createFromStream(thumb_u.openStream(), "src");
            im3.setImageDrawable(thumb_d);
        }
        catch (Exception e)
        {
            Log.d("errsssssssssssss",""+e);
        }

        tv1.setText(arr9.get(position));
        tv2.setText(arr11.get(position));
        tv3.setText(arr10.get(position));
        tv4.setText(arr1.get(position));
        tv5.setText(arr3.get(position));
        tv6.setText(arr4.get(position));
        tv7.setText(arr6.get(position));
        tv8.setText(arr8.get(position));

        tv1.setTextColor(Color.BLACK);
        tv2.setTextColor(Color.BLACK);
        tv3.setTextColor(Color.BLACK);
        tv4.setTextColor(Color.BLACK);
        tv5.setTextColor(Color.BLACK);
        tv6.setTextColor(Color.BLACK);
        tv7.setTextColor(Color.BLACK);
        tv8.setTextColor(Color.BLACK);
        return gridView;

    }

}
