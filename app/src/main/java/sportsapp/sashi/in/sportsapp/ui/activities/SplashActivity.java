package sportsapp.sashi.in.sportsapp.ui.activities;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import sportsapp.sashi.in.sportsapp.R;
import sportsapp.sashi.in.sportsapp.utils.Constants;

public class SplashActivity extends AppCompatActivity {

    private static final String TAG = SplashActivity.class.getSimpleName();

    private ImageView appLogoIV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        init();

    }

    private void init() {
        appLogoIV = findViewById(R.id.appLogoIV);

//        Picasso.with(SplashActivity.this)
//                .load(Constants.LOGO_URL)
//                .networkPolicy(NetworkPolicy.OFFLINE)
//                .into(appLogoIV);

        new Handler()
                .postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // TODO: 9/22/2018 Add Activity Transitions
                        startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                        finish();
                    }
                }, 4500);

    }
}
