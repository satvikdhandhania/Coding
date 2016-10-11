package com.thenewboston.travis;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TabHost;
import android.widget.TextView;

/**
 * Created by satvik on 27/7/15.
 */
public class Tabs extends Activity implements View.OnClickListener {

    TabHost th;
    TextView showResults;
    long start, stop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tabs);
        start = 0;
        th = (TabHost) findViewById(R.id.tabhost);
        Button newTab = (Button) findViewById(R.id.bAddTab);
        Button bStart = (Button) findViewById(R.id.bStartWatch);
        Button bStop = (Button) findViewById(R.id.bStopWatch);
        showResults = (TextView) findViewById(R.id.tvShowResults);
        newTab.setOnClickListener(this);
        bStart.setOnClickListener(this);
        bStop.setOnClickListener(this);

        th.setup();

        TabHost.TabSpec specs = th.newTabSpec("tag1");
        specs.setContent(R.id.tab1);
        specs.setIndicator("StopWatch");
        th.addTab(specs);

        specs = th.newTabSpec("tag2");
        specs.setContent(R.id.tab2);
        specs.setIndicator("Tab 2");
        th.addTab(specs);

        specs = th.newTabSpec("tag3");
        specs.setContent(R.id.tab3);
        specs.setIndicator("Add a Tab");
        th.addTab(specs);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bAddTab:
                TabHost.TabSpec ourSpec = th.newTabSpec("Tag1");
                ourSpec.setContent(new TabHost.TabContentFactory() {
                    @Override
                    public View createTabContent(String tag) {
                        TextView text = new TextView(Tabs.this);
                        text.setText("You Have created a new tab !");
                        return (text);
                    }
                });
                ourSpec.setIndicator("New");
                th.addTab(ourSpec);
                break;
            case R.id.bStartWatch:
                start = System.currentTimeMillis();
                break;
            case R.id.bStopWatch:
                stop = System.currentTimeMillis();

                if (start != 0) {
                    long result = stop - start;

                    int millis = (int) result;
                    int seconds = (int) result / 1000;
                    int minutes = seconds / 60;
                    millis = millis % 100;
                    seconds = seconds % 60;
                    showResults.setText(String.format("%d:%02d:%02d", minutes, seconds, millis));
                }
                break;
        }
    }


}
