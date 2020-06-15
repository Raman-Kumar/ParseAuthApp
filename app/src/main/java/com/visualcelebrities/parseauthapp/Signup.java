package com.visualcelebrities.parseauthapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class Signup extends AppCompatActivity {

    EditText username;
    EditText email;
    EditText password;
    Button signup;
    TextView login_banner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

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
        email = findViewById(R.id.email);
        signup = findViewById(R.id.login);
        login_banner =  findViewById(R.id.signup_banner);
    }

    public void signup(View view) {
        if(username.getText().toString().trim().isEmpty()){
            Toast.makeText(this, "User is Empty" , Toast.LENGTH_SHORT).show();
            return;
        }

        if(email.getText().toString().trim().isEmpty()){
            Toast.makeText(this, "Email is Empty" , Toast.LENGTH_SHORT).show();
            return;
        }

        if(password.getText().toString().isEmpty()){
            Toast.makeText(this, "Password is Empty" , Toast.LENGTH_SHORT).show();
            return;
        }

        ParseUser user = new ParseUser();
        user.setUsername(username.getText().toString().trim());
        user.setPassword(password.getText().toString());
        user.setEmail(email.getText().toString().trim());

        user.signUpInBackground(new SignUpCallback() {
            public void done(ParseException e) {
                if (e == null) {
                    Toast.makeText(getApplicationContext(), "Sign up Completed" , Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Signup.this, Login.class);
                    startActivity(intent);
                } else {
                    // Sign up didn't succeed. Look at the ParseException
                    // to figure out what went wrong
                    Toast.makeText(getApplicationContext(), "Sign up Failed Retry" , Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void jumpToLogin(View view) {

        Intent intent = new Intent(Signup.this, Login.class);
        startActivity(intent);
        finish();
    }
}
