package com.samsung.paint;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

import com.samsung.paint.MainActivity;

public class Example extends View {
    public static Paint paint;

    public Example(Context context) {
        super(context);
        paint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        paint.setARGB(Change.alphaValue, Change.redValue, Change.greenValue, Change.blueValue);
        canvas.drawCircle(MainActivity.screenWidth / 2, MainActivity.screenWidth / 4,
                MainActivity.screenWidth / 4, paint);
        super.onDraw(canvas);
    }
}