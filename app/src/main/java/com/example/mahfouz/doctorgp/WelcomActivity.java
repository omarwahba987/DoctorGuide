package com.example.mahfouz.doctorgp;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class WelcomActivity extends AppCompatActivity {
    private static int splash_time=3000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcom);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent= new Intent(WelcomActivity.this,MainActivity.class);
                startActivity(intent);
                finish();

            }
        },splash_time);



    }
}
