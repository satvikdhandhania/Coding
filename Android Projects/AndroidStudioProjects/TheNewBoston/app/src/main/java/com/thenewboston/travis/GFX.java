package com.thenewboston.travis;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.PowerManager;

/**
 * Created by satvik on 7/7/15.
 */
public class GFX extends Activity {

    MyBringBack ourView;
    PowerManager.WakeLock wL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //wake-lock
        PowerManager pM = (PowerManager) getSystemService(Context.POWER_SERVICE);
        wL = pM.newWakeLock(PowerManager.FULL_WAKE_LOCK, "CommentWhatever");

        super.onCreate(savedInstanceState);
        ourView = new MyBringBack(this);
        setContentView(ourView);
    }

    @Override
    protected void onPause() {
        super.onPause();
        wL.release();
    }

    @Override
    protected void onResume() {
        super.onResume();
        wL.acquire();
    }
}