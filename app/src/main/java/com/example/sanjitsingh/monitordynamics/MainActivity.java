package com.example.sanjitsingh.monitordynamics;

import android.app.ProgressDialog;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.view.View;
import android.content.Intent;
import android.app.Activity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.LoginManager;
import com.facebook.login.widget.LoginButton;
import com.facebook.AccessToken;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;




public class MainActivity extends AppCompatActivity {

    private CallbackManager callbackManager;
    private TextView txtEmail, txtBirthday, txtFriends;
    private ProgressDialog mDialog;
    private ImageView imgAvatar;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        callbackManager = CallbackManager.Factory.create();
        txtBirthday = (TextView) findViewById(R.id.txtBirthday);
        txtEmail = (TextView) findViewById(R.id.txtEmail);
        txtFriends = (TextView) findViewById(R.id.txtFriends);

        imgAvatar = (ImageView)findViewById(R.id.avatar);


        Button body = (Button) findViewById(R.id.body);
        Button sleep = (Button) findViewById(R.id.sleep);
        body.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, BodyPosture.class);
                startActivity(intent);
            }

        });

        sleep.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v2) {
                Intent intent = new Intent(MainActivity.this, SleepAnalysis.class);
                startActivity(intent);
            }

        });


        LoginButton loginButton = (LoginButton) findViewById(R.id.signin);
        loginButton.setReadPermissions(Arrays.asList("public_profile", "email", "user_birthday", "user_friends"));

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                // App
                mDialog = new ProgressDialog(MainActivity.this);
                mDialog.setMessage("Retrieving data...");
                mDialog.show();


                String accesstoken = loginResult.getAccessToken().getToken();
                GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        mDialog.dismiss();
                        Log.d("response", response.toString());
                        getData(object);
                    }
                });

                //Request Graph APU
                Bundle parameters = new Bundle();
                parameters.putString("fields","id,email,birthday,friends");
                request.setParameters(parameters);
                request.executeAsync();

            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });

        //if Already login
        if(AccessToken.getCurrentAccessToken() != null)
        {
            txtEmail.setText(AccessToken.getCurrentAccessToken().getUserId());
        }

    }

    private void getData(JSONObject object) {
        try{
            URL profile_picture = new URL("https://www.facebook.com/groups/1942590749181704/" + object.getString("id") + "/picture?width=250&height=250" );

            Picasso.with(this).load(profile_picture.toString()).into(imgAvatar);

            txtEmail.setText(object.getString("email"));
            txtBirthday.setText(object.getString("birthday"));
            txtFriends.setText("Friends: " + object.getJSONObject("freinds").getJSONObject("summary").getString("total_count"));


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    private void printKeyHash() {
        try{
            PackageInfo info = getPackageManager().getPackageInfo("com.example.sanjitsingh.monitordynamics", PackageManager.GET_SIGNATURES);
            for(Signature signature:info.signatures)
            {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
            }

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

    }



}
