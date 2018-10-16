package sportsapp.sashi.in.sportsapp.ui.fragments;

import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.support.annotation.Nullable;
import android.util.Log;

import sportsapp.sashi.in.sportsapp.R;
import sportsapp.sashi.in.sportsapp.utils.Constants;

public class SettingsFragment extends PreferenceFragment {

    private static final String TAG = SettingsFragment.class.getSimpleName();

//    private SharedPreferences.OnSharedPreferenceChangeListener changeListener;

    private CheckBoxPreference vodPrefs;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.preferences);

        vodPrefs = (CheckBoxPreference) findPreference(Constants.VIDEO_OF_DAY_PREFS_KEY);
        vodPrefs.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object o) {

                boolean isChecked = Boolean.valueOf(o.toString());
                Log.d(TAG, "VOD Status:\t" + isChecked);

                return true;
            }
        });

//        changeListener = new SharedPreferences.OnSharedPreferenceChangeListener() {
//            @Override
//            public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
//                if (key.equals(VOD_KEY)){
//                    vodPrefs = findPreference(key);
//                }
//            }
//        };


    }

}
