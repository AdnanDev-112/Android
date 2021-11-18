package com.example.validations;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.Calendar;

public class SignUp2ndClass extends AppCompatActivity {


//    Variables

    Button registerBtn;
    RadioGroup radioGroup;
    DatePicker datePicker;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up2nd_class);


        //        Hooks
        registerBtn = findViewById(R.id.register_button);
        radioGroup = findViewById(R.id.radio_group);
        datePicker = findViewById(R.id.age_picker);
       getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        getWindow().setNavigationBarColor(getResources().getColor(R.color.navBarColor));
    }


    public void callRegister(View view) {
        if(!validateGender() | !validateAge() ){
            return;
        }else{
//            Toast.makeText(this, "User Registered Successfully", Toast.LENGTH_SHORT).show();
            Intent last_page =new Intent(getApplicationContext(),Register_page.class);
            startActivity(last_page);
        }

    }

    private boolean validateGender() {
        if (radioGroup.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, "Please Select Gender", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }


    private boolean validateAge() {
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        int userAge = datePicker.getYear();

        int isAgeValid = currentYear - userAge;


        if (isAgeValid < 13) {
            Toast.makeText(this, "You are not Eligible to Apply Minimum Age is 13", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }


}