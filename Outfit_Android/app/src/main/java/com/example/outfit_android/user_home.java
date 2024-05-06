package com.example.outfit_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class user_home extends AppCompatActivity {

//    Button b1, b5, b6, b7, b8, b9, b10;
    ImageView b1, b5, b6, b7, b8, b9, b10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        b1 = findViewById(R.id.button);
        b5 = findViewById(R.id.button2);
        b6 = findViewById(R.id.button3);
        b7 = findViewById(R.id.button6);
        b8 = findViewById(R.id.button7);
        b9 = findViewById(R.id.button8);
        b10 = findViewById(R.id.button4);


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i1 = new Intent(getApplicationContext(), view_designs.class);
                startActivity(i1);


            }
        });

        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i1 = new Intent(getApplicationContext(), chat_with_tailor.class);
                startActivity(i1);


            }
        });


        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i1 = new Intent(getApplicationContext(), chat_with_designers.class);
                startActivity(i1);


            }
        });


        b7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1 = new Intent(getApplicationContext(), send_rating.class);
                startActivity(i1);


            }
        });


        b8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i1 = new Intent(getApplicationContext(), send_complaint.class);
                startActivity(i1);

            }
        });


        b9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1 = new Intent(getApplicationContext(), login.class);
                startActivity(i1);

            }
        });


        b10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1 = new Intent(getApplicationContext(), view_user_custom_design.class);
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