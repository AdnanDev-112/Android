package com.example.validations;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {
    //Variables

    Button next;
    TextView titleText, slideText;

//    Data Variables
    TextInputLayout fullName, userName , email,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//        Hooks for Variables

        fullName = findViewById(R.id.signup_full_name);
        userName = findViewById(R.id.signup_username);
        email = findViewById(R.id.signup_email);
        password = findViewById(R.id.signup_password);

        //Hooks for animation

        next = findViewById(R.id.signup_next_button);
        titleText = findViewById(R.id.signup_title_text);
        slideText = findViewById(R.id.signup_slide_text);
    }


    public void callNextSignupScreen(View view) {
        Intent intent = new Intent(getApplicationContext(), SignUp2ndClass.class);
        //Add Shared Animation
        Pair[] pairs = new Pair[3];
        pairs[0] = new Pair<View,String>(next, "transition_next_btn");
        pairs[1] = new Pair<View,String>(titleText, "transition_title_text");
        pairs[2] = new Pair<View,String>(slideText, "transition_slide_text");

        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this, pairs);
        startActivity(intent, options.toBundle());



    }



//    Validation Functions

    private boolean validateFullName(){
        String val = fullName.getEditText().getText().toString().trim();
        if(val.isEmpty()){
            fullName.setError("Field cannot be Empty");
            return false;
        }else{
            fullName.setError(null);
            fullName.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateUserName(){
        String val = userName.getEditText().getText().toString().trim();
        String checkspaces = "\\A\\w{1,12}\\z";
        if(val.isEmpty()){
            userName.setError("Field cannot be Empty");
            return false;
        }else if(val.length()>12){
            userName.setError("Username cannot be more than 12 Characters");
            return false;
        }else if(val.matches(checkspaces)){
            userName.setError("No White Spaces Allowed");
        }
        else{
            userName.setError(null);
            userName.setErrorEnabled(false);
            return true;
        }
    }

}