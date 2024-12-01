package com.example.up_011;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class OnBoardingScreen extends AppCompatActivity {


    Integer step = 0;
    Integer start_x = -1;
    Integer end_x = -1;

    LaunchScreen launchScreen = new LaunchScreen();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_boarding_screen1);
    }


    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                start_x = (int) event.getX();
                break;
            case MotionEvent.ACTION_UP:
                end_x = (int) event.getX();
        }
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                start_x = (int) event.getX();
                break;
            case MotionEvent.ACTION_UP:
                end_x = (int) event.getX();
        }

        if(start_x != -1 && end_x != -1) {
            if(Math.abs(start_x - end_x) > 10) {
                if(start_x < end_x) {
                    if(step != 0) {
                        setContentView(R.layout.activity_on_boarding_screen1);
                        step = 0;
                    }
                } else {
                    if(step != 1) {
                        setContentView(R.layout.activity_on_boarding_screen2);
                        if(!launchScreen.isOnline(this)) {
                            TextView skip = findViewById(R.id.textView3);
                            skip.setVisibility(View.VISIBLE);
                        }
                        step = 1;
                    }
                }
            }
            start_x = -1;
            end_x =-1;
        }

        return false;
    }

    public void SignIn(View view) {
        Intent intent = new Intent(this, SignInScreen.class);
        startActivity(intent);
    }

    public void SingUp(View view) {
        Intent intent = new Intent(this, SignUpScreen.class);
        startActivity(intent);
    }
}