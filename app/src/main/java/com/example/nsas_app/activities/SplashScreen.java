package com.example.nsas_app.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.nsas_app.R;

public class SplashScreen extends AppCompatActivity {
    SharedPreferencemanager prefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        prefManager = new SharedPreferencemanager(getApplicationContext());
        Thread timerThread = new Thread() {
            public void run() {
                try {
                    sleep(2000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    if (prefManager.getISLogged_IN()) {
                        Intent intent = new Intent(SplashScreen.this, HomePage.class);
                        intent.putExtra("callAPI", 1);
                        startActivity(intent);
                        finish();
                    } else {
                        Intent intent = new Intent(SplashScreen.this, LoginActivity.class);
                        intent.putExtra("callAPI", 1);
                        startActivity(intent);
                        finish();
                    }
                }
            }
        };
        timerThread.start();
    }
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }
}
