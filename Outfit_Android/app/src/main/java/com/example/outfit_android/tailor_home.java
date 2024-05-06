package com.example.outfit_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class tailor_home extends AppCompatActivity {

    ImageView b1, b2, b3, b5;
//    Button b1, b2, b3, b5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_t_home);

        b1 = findViewById(R.id.button);
        b2 = findViewById(R.id.button2);
        b3 = findViewById(R.id.button3);
        b5 = findViewById(R.id.button8);


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i1 = new Intent(getApplicationContext(), view_assigned_work.class);
                startActivity(i1);


            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i1 = new Intent(getApplicationContext(), chat_with_user.class);
                startActivity(i1);


            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i1 = new Intent(getApplicationContext(), view_custom_design.class);
                startActivity(i1);


            }
        });
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i1 = new Intent(getApplicationContext(), login.class);
                startActivity(i1);

            }
        });
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent ik = new Intent(getApplicationContext(), login.class);
        startActivity(ik);
    }

}