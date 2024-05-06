package com.example.outfit_android;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class Registration extends AppCompatActivity {
    EditText firstname, place, post, pin, phone, email,photo,username, password;
//    TextInputEditText firstname, place, post, pin, phone, email,photo,username, password;
    RadioButton male, female;
    Button butt_signup, butt_photo;
    String Name_str, gender_str, photo_str, place_str, post_str, pin_str, email_str, phone_str, username_str, password_str, url, title;
    SharedPreferences sh;
    String PathHolder = "";
    byte[] filedt = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_design);

        firstname = findViewById(R.id.FISTNAME);
        place = findViewById(R.id.place);
        post = findViewById(R.id.post);
        pin = findViewById(R.id.pin);
        phone = findViewById(R.id.phone);
        email = findViewById(R.id.email);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        photo = findViewById(R.id.image);
        male = findViewById(R.id.radioButton);
        female = findViewById(R.id.radioButton2);

        butt_signup = findViewById(R.id.button14);
        butt_photo = findViewById(R.id.button15);
        sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());


        butt_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("*/*");
//            intent.setType("application/pdf");
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                startActivityForResult(intent, 7);


            }
        });

        butt_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Name_str = firstname.getText().toString();
                place_str = place.getText().toString();
                post_str = post.getText().toString();
                pin_str = pin.getText().toString();
                phone_str = phone.getText().toString();
                email_str = email.getText().toString();
                username_str = username.getText().toString();
                password_str = password.getText().toString();
                photo_str = photo.getText().toString();

                gender_str = "";
                if (male.isChecked()) {
                    gender_str = male.getText().toString();
                }else{
                    gender_str = female.getText().toString();
                }

                if (Name_str.equalsIgnoreCase("")) {
                    firstname.setError("Enter Your Name");
                } else if (!Name_str.matches("^[a-zA-Z ]*$")) {
                    firstname.setError("characters allowed");
                }else if (place_str.equalsIgnoreCase("")) {
                    place.setError("Enter Your place");
                } else if (!place_str.matches("^[a-z A-Z 0-9]*$")) {
                    place.setError("characters allowed");
                }else if (post_str.equalsIgnoreCase("")) {
                    post.setError("Enter Your post");
                } else if (!post_str.matches("^[a-z A-Z 0-9]*$")) {
                    post.setError("characters allowed");
                }else if (pin_str.equalsIgnoreCase("")) {
                    pin.setError("Enter Your pin");
                }else if (pin_str.length() != 6) {
                    pin.setError("Invalid pincode");
                } else if (phone_str.equalsIgnoreCase("")) {
                    phone.setError("Enter Your phone no");
                }else if (phone_str.length() != 10) {
                    phone.setError("Invalid Phone number");
                }
                else if (email_str.equalsIgnoreCase("")) {
                    email.setError("Enter your Email");
                }else if(!Patterns.EMAIL_ADDRESS.matcher(email_str).matches())
                {
                    email.setError("Enter Valid Email");
                    email.requestFocus();
                }
                else if (photo_str.equalsIgnoreCase("")) {
                    photo.setError("please select your photo");
                }
                else if (username_str.equalsIgnoreCase("")) {
                    username.setError("Enter Your username");
                }else if (!username_str.matches("^[a-z A-Z ]*$")) {
                    username.setError("characters allowed");
                } else if (password_str.equalsIgnoreCase("")) {
                    password.setError("Enter Your password");
                }
                else {
                    uploadBitmap(title);
                }
            }
        });

    }
    ProgressDialog pd;
    private void uploadBitmap(final String title)
    {
//        Toast.makeText(getApplicationContext(), "IIIIIIIIIIIIIIIIIIIII", Toast.LENGTH_LONG).show();
        RequestQueue queue = Volley.newRequestQueue(Registration.this);pd=new ProgressDialog(Registration.this);
        url = "http://" + sh.getString("ip","") + ":5000/user_register";
        pd.setMessage("Uploading....");
        pd.show();
        VolleyMultipartRequest volleyMultipartRequest = new VolleyMultipartRequest(Request.Method.POST, url,
                new Response.Listener<NetworkResponse>() {
                    @Override
                    public void onResponse(NetworkResponse response1) {
                        pd.dismiss();
                        String x=new String(response1.data);
                        try {
                            JSONObject obj = new JSONObject(new String(response1.data));
//                        Toast.makeText(Upload_agreement.this, "Report Sent Successfully", Toast.LENGTH_LONG).show();
                            if (obj.getString("task").equalsIgnoreCase("success")) {

                                Toast.makeText(Registration.this, "Successfully uploaded", Toast.LENGTH_LONG).show();
                                Intent i=new Intent(getApplicationContext(),login.class);
                                startActivity(i);
                            } else {
                                Toast.makeText(getApplicationContext(), "failed", Toast.LENGTH_LONG).show();
                            }

                        } catch (Exception e) {
                            Toast.makeText(getApplicationContext(), "Error" + e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();

                params.put("Name", Name_str);
                params.put("Place", place_str);
                params.put("Post", post_str);
                params.put("Pin", pin_str);
                params.put("gender", gender_str);
                params.put("email", email_str);
                params.put("Phone", phone_str);
                params.put("Username", username_str);
                params.put("Password", password_str);
                return params;
            }

            @Override
            protected Map<String, DataPart> getByteData() {
                Map<String, DataPart> params = new HashMap<>();
                long imagename = System.currentTimeMillis();
                params.put("photo", new DataPart(PathHolder , filedt ));
                return params;
            }
        };
        Volley.newRequestQueue(Registration.this).add(volleyMultipartRequest);
    }
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onActivityResult(int requestCode,int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 7:
                if (resultCode == RESULT_OK) {
                    Uri uri = data.getData();
                    Log.d("File Uri", "File Uri: " + uri.toString());
                    // Get the path
                    try {
                        PathHolder = FileUtils.getPathFromURI(Registration.this, uri);
//                        PathHolder = data.getData().getPath();
//                        Toast.makeText(this, PathHolder, Toast.LENGTH_SHORT).show();

                        filedt = getbyteData(PathHolder);
                        Log.d("filedataaa", filedt + "");
//                        Toast.makeText(this, filedt+"", Toast.LENGTH_SHORT).show();
                        photo.setText(PathHolder);
                    }
                    catch (Exception e){
                        Toast.makeText(Registration.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
                break;
        }
    }
    private byte[] getbyteData(String pathHolder) {
        Log.d("path", pathHolder);
        File fil = new File(pathHolder);
        int fln = (int) fil.length();
        byte[] byteArray = null;
        try {
            InputStream inputStream = new FileInputStream(fil);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] b = new byte[fln];
            int bytesRead = 0;

            while ((bytesRead = inputStream.read(b)) != -1) {
                bos.write(b, 0, bytesRead);
            }
            byteArray = bos.toByteArray();
            inputStream.close();
        } catch (Exception e) {
        }
        return byteArray;
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent ik = new Intent(getApplicationContext(), login.class);
        startActivity(ik);
    }


}