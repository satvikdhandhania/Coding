package com.thenewboston.travis;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by satvik on 1/8/15.
 */
public class SharedPrefs extends Activity implements View.OnClickListener {
    EditText sharedData;
    TextView dataResult;
    public static String filename = "MySharedString";
    SharedPreferences someData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sharedpreferences);
        setupVariables();
        someData = getSharedPreferences(filename, 0);
    }

    private void setupVariables(){
        Button save = (Button) findViewById(R.id.bSave);
        Button load = (Button)findViewById(R.id.bLoad);
        sharedData = (EditText) findViewById(R.id.etSharedPrefs);
        dataResult = (TextView) findViewById(R.id.tvLoadSharedPrefs);
        save.setOnClickListener(this);
        load.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.bSave:
                String data = sharedData.getText().toString();

                break;
            case R.id.bLoad:

                break;
        }
    }
}
