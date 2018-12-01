package com.example.sanjitsingh.monitordynamics;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.view.View;
import android.content.Intent;
import android.app.Activity;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button body = (Button)findViewById(R.id.body);
        Button sleep = (Button)findViewById(R.id.sleep);
        Button signin = (Button)findViewById(R.id.signin);
        body.setOnClickListener(new View.OnClickListener() {

            @Override
            public  void onClick(View v) {
                Intent i = new Intent(MainActivity.this, BodyPosture.class);
                startActivity(i);
                Toast.makeText(getApplicationContext(), "Welcome", Toast.LENGTH_LONG)
                        .show();
            }

        });

    }

//    @Override
//    public void onClick(View view) {
//        switch (view.getId())
//        {
//            case R.id.button1:
//                Intent myIntent = new Intent(this, BodyPosture.class);
//                startActivity(myIntent);
//                break;
//
//            case R.id.button2:
//                Intent myIntent2 = new Intent(this, SleepAnalysis.class);
//                startActivity(myIntent2);
//                break;
//        }
//
//    }
}
