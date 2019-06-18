package com.example.fishfly;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.net.Inet4Address;

public class SplashActivity extends AppCompatActivity {

    Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        b1=findViewById(R.id.b1);


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent= new Intent(SplashActivity.this,MainActivity.class);
                startActivity(intent);

            }
        });

//
//        Thread thread= new Thread() {
//            @Override
//            public void run() {
//                try {
//                    sleep(5000);
//
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//
//                finally
//                {
//                    Intent intent= new Intent(SplashActivity.this,MainActivity.class);
//                    startActivity(intent);
//                }
//
//            }
//
//
//        };
//
//            thread.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
