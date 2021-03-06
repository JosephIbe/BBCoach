package sportsapp.sashi.in.sportsapp.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alespero.expandablecardview.ExpandableCardView;
import com.github.clans.fab.FloatingActionMenu;
import com.squareup.picasso.Picasso;

import sportsapp.sashi.in.sportsapp.R;

public class ViewSessionActivity extends AppCompatActivity {

    private static final String TAG = ViewSessionActivity.class.getSimpleName();

    private AppBarLayout appBarLayout;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private Toolbar toolbar;
    private TextView sessionNameTxt, sessionDescTxt, focusPointsTV;
    private ExpandableCardView focusCardLayout;
    private ImageView backIV, sessionCoverImg;

    private FloatingActionMenu actionMenu;
    private com.github.clans.fab.FloatingActionButton attendanceFAB, sessionFAB;

    private Intent intent = null;
    private String name_session = null, progSessId = null, prg_id = null,
            desc = null, cover_img = null, focus_points = null,
            video_link = null, start_time = null, end_time = null, duration = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sessions);

        init();
        setSupportActionBar(toolbar);

        setMeta();

        backIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startHomeActivity();
            }
        });

        attendanceFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(ViewSessionActivity.this, MarkAttendanceActivity.class);
                intent.putExtra("prg_sess_id", progSessId);
                intent.putExtra("prg_id", prg_id);
                intent.putExtra("session_name", name_session);
                intent.putExtra("start_time", start_time);
                intent.putExtra("end_time", end_time);
                intent.putExtra("duration", duration);
                startActivity(intent);
            }
        });

        sessionFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(ViewSessionActivity.this, StartSessionActivity.class);
                intent.putExtra("prg_sess_id", progSessId);
                intent.putExtra("prg_id", prg_id);
                intent.putExtra("session_name", name_session);
                intent.putExtra("start_time", start_time);
                intent.putExtra("end_time", end_time);
                intent.putExtra("duration", duration);
                intent.putExtra("has_marked_attendance", false);
                startActivity(intent);
                finish();
            }
        });

    }

    private void setMeta() {
        progSessId = getIntent().getExtras().getString("prg_sess_id");
        prg_id = getIntent().getExtras().getString("prg_id");
        name_session = getIntent().getExtras().getString("session_name");
        desc = getIntent().getExtras().getString("session_desc");
        cover_img = getIntent().getExtras().getString("session_cover_image");
        focus_points = getIntent().getExtras().getString("session_focus_pts");
        video_link = getIntent().getExtras().getString("session_video_link");
        start_time = getIntent().getExtras().getString("session_start_time");
        end_time = getIntent().getExtras().getString("session_end_time");
        duration = getIntent().getExtras().getString("session_duration");

        sessionNameTxt.setText(name_session);
        sessionDescTxt.setText(desc);
//        Log.d(TAG, "FPtxt:\t" + focus_points);
//        if (!TextUtils.isEmpty(focus_points)){
//            focusCardLayout.setVisibility(View.VISIBLE);
//            focusPointsTV.setText(focus_points);
//        } else {
//            focusCardLayout.setVisibility(View.GONE);
//        }
        Picasso.with(this)
                .load(cover_img)
                .placeholder(R.drawable.basketball)
                .into(sessionCoverImg);

    }

    private void init() {
        appBarLayout = findViewById(R.id.appBar);
        collapsingToolbarLayout = findViewById(R.id.collapsingToolbar);
        toolbar = findViewById(R.id.toolbar);
        sessionCoverImg = findViewById(R.id.sessionCoverImg);
        backIV = findViewById(R.id.backIV);
        sessionNameTxt = findViewById(R.id.sessionNameTxt);
        sessionDescTxt = findViewById(R.id.sessionDescTxt);
        focusPointsTV = findViewById(R.id.focusPointsTV);
        focusCardLayout = findViewById(R.id.focusCardLayout);
        actionMenu = findViewById(R.id.menuFAB);
        attendanceFAB = findViewById(R.id.attendanceFAB);
        sessionFAB = findViewById(R.id.sessionFAB);

        collapsingToolbarLayout.setTitle("");

    }

    @Override
    protected void onPause() {
        super.onPause();
        if (actionMenu.isOpened()) {
            actionMenu.close(true);
        } else {
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (actionMenu.isOpened()) {
            actionMenu.close(true);
        } else {
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startHomeActivity();
    }

    private void startHomeActivity() {
        startActivity(new Intent(ViewSessionActivity.this, HomeActivity.class));
        finish();
    }

}
