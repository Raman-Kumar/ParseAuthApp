package com.visualcelebrities.parseauthapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseUser;

public class Login extends AppCompatActivity {

    EditText username;
    EditText password;
    Button login;
    TextView signup_banner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initViewIds();

//        ParseUser user = new ParseUser();
        Parse.initialize(new Parse.Configuration.Builder(this)
                        .applicationId("myappID")
                        // if defined
//                .clientKey("0oTwMir6DDwA")
//                .clientKey("")
                        .server("http://52.66.209.219/parse")
                        .build()
        );

    }

    private void initViewIds() {
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);
        signup_banner =  findViewById(R.id.signup_banner);
    }

    public void login(View view) {

        if(username.getText().toString().trim().isEmpty()){
            Toast.makeText(this, "User is Empty" , Toast.LENGTH_SHORT).show();
            return;
        }

        ParseUser.logInInBackground(username.getText().toString().trim(), password.getText().toString(), new LogInCallback() {
            public void done(ParseUser user, ParseException e) {
                if (user != null) {
                    Intent intent = new Intent(Login.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    // Signup failed. Look at the ParseException to see what happened.
                }
            }
        });
    }

    public void jumpToSignup(View view) {

        Intent intent = new Intent(Login.this, Signup.class);
        startActivity(intent);
        finish();
    }
}
