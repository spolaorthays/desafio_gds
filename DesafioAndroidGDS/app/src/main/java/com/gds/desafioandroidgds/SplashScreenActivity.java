package com.gds.desafioandroidgds;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                irParaHome();
            }
        },2000);
    }

    private void irParaHome() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
