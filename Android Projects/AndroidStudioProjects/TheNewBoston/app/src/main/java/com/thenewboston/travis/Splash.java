package com.thenewboston.travis;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.PreferenceManager;

/**
 * Created by satvik on 14/6/15.
 */
public class Splash extends Activity {

    MediaPlayer ourSong;

    @Override
    protected void onCreate(Bundle TravisLoveBacon) {
        super.onCreate(TravisLoveBacon);
        setContentView(R.layout.splash);
        ourSong = MediaPlayer.create(Splash.this, R.raw.splashsound);
        SharedPreferences getPrefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        Boolean music = getPrefs.getBoolean("checkbox", false);
        if (music == true)
            ourSong.start();
        Thread timer = new Thread() {
            public void run() {
                try {
                    sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    Intent openStartingPoint = new Intent("com.thenewboston.travis.MENU");
                    startActivity(openStartingPoint);
                }
            }
        };
        timer.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        ourSong.release();
        finish();
    }

}
