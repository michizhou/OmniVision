package com.example.sanjitsingh.monitordynamics;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.content.Intent;
import android.app.Activity;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
//import com.facebook.login.widget.LoginButton;



public class MainActivity extends AppCompatActivity{

    private  CallbackManager callbackManager;
    private  TextView textView;
    //private  LoginButton loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
        setContentView(R.layout.activity_main);
        Button body = (Button)findViewById(R.id.body);
        Button sleep = (Button)findViewById(R.id.sleep);
        Button signin = (Button)findViewById(R.id.signin);
        body.setOnClickListener(new View.OnClickListener() {

            @Override
            public  void onClick(View v) {
                Intent i = new Intent(MainActivity.this, BodyPosture.class);
                startActivity(i);
            }

        });

        sleep.setOnClickListener(new View.OnClickListener() {

            public  void onClick(View v2) {
            Intent intent = new Intent(MainActivity.this, SleepAnalysis.class);
            startActivity(intent);
        }

    });

//        callbackManager = CallbackManager.Factory.create();
//
//        loginButton = (LoginButton)findViewById(R.id.signin);
//        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
//            @Override
//            public void onSuccess(LoginResult loginResult) {
//                info.setText(
//                        "User ID: "
//                                + loginResult.getAccessToken().getUserId()
//                                + "\n" +
//                                "Auth Token: "
//                                + loginResult.getAccessToken().getToken()
//                );
//            }
//
//            @Override
//            public void onCancel() {
//                info.setText("Login attempt canceled");
//            }
//
//            @Override
//            public void onError(FacebookException error) {
//                info.setText("Login attempt failed.");
//            }
//        });

        signin.setOnClickListener(new View.OnClickListener() {

            public  void onClick(View v2) {
                Intent intent = new Intent(MainActivity.this, SleepAnalysis.class);
                startActivity(intent);
            }

        });

       }
    }

