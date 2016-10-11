package com.thenewboston.travis;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.view.View;

/**
 * Created by satvik on 7/7/15.
 */
public class MyBringBack extends View {
    Bitmap gball;
    float changingY;
    float changingX, temp; // MyModification
    Typeface face;

    public MyBringBack(Context context) {
        super(context);
        gball = BitmapFactory.decodeResource(getResources(), R.drawable.greenball);
        changingY = 0;
        changingX = 0; //MyModification
        temp = 10;     // MyModification
        face = Typeface.createFromAsset(context.getAssets(), "G-Unit.TTF");
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.LTGRAY);

        Paint textPaint = new Paint();
        textPaint.setARGB(50, 254, 10, 50);
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setTextSize(50);
        textPaint.setTypeface(face);
        canvas.drawText("myBringBack", canvas.getWidth() / 2, 200, textPaint);

        /*
        canvas.drawBitmap(gball, (canvas.getWidth() / 2), changingY, null);
        if (changingY < canvas.getHeight()) {
            changingY += 10;
        } else {
            changingY = 0;
        }
        */

        canvas.drawBitmap(gball, changingX, 230, null);     //MyModification
        if (changingX + gball.getWidth() > canvas.getWidth()) {
            temp = -10;
        } else if (changingX < 0) {
            temp = 10;
        }
        changingX += temp;

        Rect middleRect = new Rect();
        middleRect.set(0, 400, canvas.getWidth(), 550);
        Paint ourBlue = new Paint();
        ourBlue.setColor(Color.BLUE);
        canvas.drawRect(middleRect, ourBlue);
        invalidate();
    }
}