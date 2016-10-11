package com.thenewboston.travis;

import android.os.Bundle;
import android.preference.PreferenceActivity;

/**
 * Created by satvik on 5/7/15.
 */
public class Prefs extends PreferenceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.prefs);
    }
}
