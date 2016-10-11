package com.thenewboston.travis;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by satvik on 30/6/15.
 */
public class Camera extends Activity implements View.OnClickListener {

    final static int cameraData = 0;
    ImageView iv;
    ImageButton ib;
    Button b;
    Intent i;
    Bitmap bmp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.photo);
        initialize();
        InputStream is = getResources().openRawResource(R.drawable.img);
        bmp = BitmapFactory.decodeStream(is);
    }

    private void initialize() {
        iv = (ImageView) findViewById(R.id.ivReturnedPicture);
        ib = (ImageButton) findViewById(R.id.ibTakePic);
        b = (Button) findViewById(R.id.bSetWall);
        b.setOnClickListener(this);
        ib.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.bSetWall:
                try {
                    getApplicationContext().setWallpaper(bmp);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.ibTakePic:
                i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(i, cameraData);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            bmp = (Bitmap) extras.get("data");
            iv.setImageBitmap(bmp);

        }

    }
}
