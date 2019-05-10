package com.example.studybuddy2;

import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    Timer timer;
    int SLEEP_TIMER = 4000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ImageView logoImageView = findViewById(R.id.logoImageView);

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() { //necessary to avoid error message
                    @Override
                    public void run() {
                        Intent toLoginActivity = new Intent(MainActivity.this, LoginActivity.class);
                        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(MainActivity.this,
                                logoImageView, getString(R.string.transition_name_1));
                        startActivity(toLoginActivity, options.toBundle());
                        finish();
                    }
                });
            }
        }, SLEEP_TIMER);
    }
}
