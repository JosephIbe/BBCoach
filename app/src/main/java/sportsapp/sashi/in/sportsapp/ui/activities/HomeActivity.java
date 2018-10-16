package sportsapp.sashi.in.sportsapp.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.github.jhonnyx2012.horizontalpicker.DatePickerListener;
import com.github.jhonnyx2012.horizontalpicker.HorizontalPicker;
import com.google.android.youtube.player.YouTubePlayerView;

import org.joda.time.DateTime;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import sportsapp.sashi.in.sportsapp.R;
import sportsapp.sashi.in.sportsapp.adapters.ItemsDrawerAdapter;
import sportsapp.sashi.in.sportsapp.interfaces.RecyclerClickListener;
import sportsapp.sashi.in.sportsapp.models.drawer.ItemsDrawer;
import sportsapp.sashi.in.sportsapp.models.drawer.ItemsHeader;
import sportsapp.sashi.in.sportsapp.ui.fragments.TodayProgramsFragment;
import sportsapp.sashi.in.sportsapp.utils.Constants;
import sportsapp.sashi.in.sportsapp.utils.RecyclerItemTouchListener;

public class HomeActivity extends AppCompatActivity implements DatePickerListener {

    private static final String TAG = HomeActivity.class.getSimpleName();

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private RecyclerView rvDrawer;

    private List<Object> itemsDrawerList = new ArrayList<>();
    private ItemsDrawerAdapter drawerAdapter;

    private YouTubePlayerView playerView;
    private String mToday;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initViews();
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorAccent));

        setUpDrawer();

        initYTPlayer();

        boolean isChecked = PreferenceManager.getDefaultSharedPreferences(this).getBoolean(Constants.VIDEO_OF_DAY_PREFS_KEY, false);
        Toast.makeText(this, "Show VOD Value:\t" + isChecked, Toast.LENGTH_SHORT).show();

        setUpHomeView();

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        mToday = sdf.format(calendar.getTime());
        showPrograms(mToday);

    }

    private void initYTPlayer() {
        fetchVideo();
    }

    private void fetchVideo() {
        AndroidNetworking.get(Constants.VID_OF_DAY_URL)
                .setTag("Fetch Video Of Day")
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {

                    }

                    @Override
                    public void onError(ANError anError) {

                    }
                });
    }

    private void setUpHomeView() {
        HorizontalPicker picker = findViewById(R.id.datePicker);
        picker.setListener(this).init();
    }

    private void setUpDrawer() {
        rvDrawer.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rvDrawer.setLayoutManager(llm);
        rvDrawer.addItemDecoration(new DividerItemDecoration(this, llm.getOrientation()));

        ItemsHeader header = new ItemsHeader("http://devsports.copycon.in/includes/images/logo.png",
                "SportsEco");
        itemsDrawerList.add(header);

        ItemsDrawer item = new ItemsDrawer(R.drawable.profile_head, "My Profile");
        itemsDrawerList.add(item);

        ItemsDrawer item1 = new ItemsDrawer(R.drawable.attendance, "Attendance");
        itemsDrawerList.add(item1);

        ItemsDrawer item2 = new ItemsDrawer(R.drawable.calendar, "Calendar");
        itemsDrawerList.add(item2);

        ItemsDrawer item3 = new ItemsDrawer(R.drawable.basketball_players, "All Players");
        itemsDrawerList.add(item3);

//        ItemsDrawer item4 = new ItemsDrawer(R.drawable.programs, "Programs");
//        itemsDrawerList.add(item4);

        ItemsDrawer item5 = new ItemsDrawer(R.drawable.feedback, "Feedback");
        itemsDrawerList.add(item5);

        ItemsDrawer item6 = new ItemsDrawer(R.drawable.feedback, "My Library");
        itemsDrawerList.add(item6);

        ItemsDrawer item7 = new ItemsDrawer(R.drawable.basketball, "Legal Disclaimer");
        itemsDrawerList.add(item7);

        ItemsDrawer item8 = new ItemsDrawer(R.drawable.basketball, "About Us");
        itemsDrawerList.add(item8);

        ItemsDrawer item9 = new ItemsDrawer(R.drawable.settings, "Settings");
        itemsDrawerList.add(item9);

        drawerAdapter = new ItemsDrawerAdapter(this, itemsDrawerList);
        rvDrawer.setAdapter(drawerAdapter);

        handleDrawerEvents();

    }

    private void handleDrawerEvents() {
        toolbar.setNavigationIcon(R.drawable.nav_icon);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                } else {
                    drawerLayout.openDrawer(GravityCompat.START);
                }
            }
        });
        setDrawerClickListener();
    }

    private void setDrawerClickListener() {
        rvDrawer.addOnItemTouchListener(new RecyclerItemTouchListener(this, rvDrawer, new RecyclerClickListener() {
            @Override
            public void onClick(View view, int position) {
                switch (position) {
                    case 1:
                        startActivity(new Intent(HomeActivity.this, CoachProfileActivity.class));
                        break;

                    case 2:
                        Toast.makeText(HomeActivity.this, "Attendance Charts Clicked", Toast.LENGTH_SHORT).show();
                        break;

                    case 3:
                        startActivity(new Intent(HomeActivity.this, SessionsCalendarActivity.class));
                        break;
                    case 4:
//                        startActivity(new Intent(HomeActivity.this, AllPlayersActivity.class)); disabled
                        Toast.makeText(HomeActivity.this, "All Players Clicked", Toast.LENGTH_SHORT).show();
                        break;
//                    case 4:
//                        Toast.makeText(HomeActivity.this, "Programs Clicked", Toast.LENGTH_SHORT).show();
//                        break;
                    case 5:
                        Toast.makeText(HomeActivity.this, "Feedback Clicked", Toast.LENGTH_SHORT).show();
                        break;
                    case 6:
                        Toast.makeText(HomeActivity.this, "My Library Clicked", Toast.LENGTH_SHORT).show();
                        break;
                    case 7:
                        Toast.makeText(HomeActivity.this, "Disclaimer Clicked", Toast.LENGTH_SHORT).show();
                        break;
                    case 8:
                        Toast.makeText(HomeActivity.this, "About Clicked", Toast.LENGTH_SHORT).show();
                        break;
                    case 9:
                        startActivity(new Intent(HomeActivity.this, SettingsActivity.class));
                        break;
                }
            }
        }));
    }

    private void initViews() {
        toolbar = findViewById(R.id.toolbar);
        drawerLayout = findViewById(R.id.drawerLayout);
        rvDrawer = findViewById(R.id.rvDrawer);
//        playerView = findViewById(R.id.playerView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.ACTION_DOWN) {
            super.onKeyDown(keyCode, event);
            return true;
        }
        return false;

    }

    @Override
    public void onDateSelected(DateTime dateSelected) {
        Log.d(TAG, "Selected date is " + dateSelected.toString().substring(0, 10));
        Toast.makeText(this, "Selected date is " + dateSelected.toString().substring(0, 10), Toast.LENGTH_SHORT).show();
        showPrograms(dateSelected.toString().substring(0, 10));
    }

    private void showPrograms(String date) {
        TodayProgramsFragment programsFragment = new TodayProgramsFragment();

        Bundle bundle = new Bundle();
        bundle.putString("today_date", mToday);
        bundle.putString("sel_date", date);
        programsFragment.setArguments(bundle);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.containerFrag, programsFragment).commit();
    }
}
