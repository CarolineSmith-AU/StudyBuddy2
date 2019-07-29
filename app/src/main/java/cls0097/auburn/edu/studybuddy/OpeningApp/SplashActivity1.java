package cls0097.auburn.edu.studybuddy.OpeningApp;

import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.studybuddy2.R;

import java.util.Timer;
import java.util.TimerTask;

import cls0097.auburn.edu.studybuddy.Login.LoginActivity;

public class SplashActivity1 extends AppCompatActivity {

    Timer timer;
    int SLEEP_TIMER = 4000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash1);

        final ImageView logoImageView = findViewById(R.id.logoImageView);

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() { //necessary to avoid error message
                    @Override
                    public void run() {
                        Intent toLoginActivity = new Intent(SplashActivity1.this, LoginActivity.class);
                        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(SplashActivity1.this,
                                logoImageView, getString(R.string.transition_name_1));
                        startActivity(toLoginActivity, options.toBundle());
                        finish();
                    }
                });
            }
        }, SLEEP_TIMER);
    }
}
