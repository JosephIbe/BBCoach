package sportsapp.sashi.in.sportsapp.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import sportsapp.sashi.in.sportsapp.R;
import sportsapp.sashi.in.sportsapp.ui.fragments.SettingsFragment;

public class SettingsActivity extends AppCompatActivity implements View.OnClickListener {

    private Toolbar toolbar;
    private ImageView backIV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        init();
        setSupportActionBar(toolbar);

        backIV.setOnClickListener(this);

        getFragmentManager()
                .beginTransaction()
                .add(R.id.settings_frag_holder, new SettingsFragment()).commit();

        PreferenceManager.setDefaultValues(this, R.xml.preferences, false);

    }

    private void init() {
        toolbar = findViewById(R.id.toolbar);
        backIV = findViewById(R.id.backIV);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.backIV:
                startHomeActivity();
                break;
        }
    }

    private void startHomeActivity() {
        // TODO: 9/22/2018 Add activity transitions
        startActivity(new Intent(SettingsActivity.this, HomeActivity.class));
        finish();
    }

}
