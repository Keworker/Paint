package com.samsung.paint;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class Painter extends View  {
    double wight, height;
    static double side;
    static boolean flag = false, line = false;
    static Paint paint;
    static int brushMode = 0;
    Bitmap bitmapChange, bitmapCircle, bitmapRect, bitmapTriangle, bitmapEye;
    Actor change, circle, rect, triangle, eye;
    List<Line> lines = new ArrayList<>();

    public Painter(Context context) {
        super(context);
        wight = MainActivity.screenWidth;
        height = MainActivity.screenHeight;
        side = wight / 5;
        paint = new Paint();
        bitmapChange = BitmapFactory.decodeResource(getResources(), R.drawable.without_name);
        bitmapChange = Bitmap.createScaledBitmap(bitmapChange,
                (int) side, (int) side, true);
        this.change = new Actor(0f, 0f, bitmapChange, paint);
        bitmapCircle = BitmapFactory.decodeResource(getResources(), R.drawable.circle);
        bitmapCircle = Bitmap.createScaledBitmap(bitmapCircle,
                (int) side, (int) side, true);
        this.circle = new Actor((float) side, 0f, bitmapCircle, paint);
        bitmapRect = BitmapFactory.decodeResource(getResources(), R.drawable.rect);
        bitmapRect = Bitmap.createScaledBitmap(bitmapRect,
                (int) side, (int) side, true);
        this.rect = new Actor((float) side * 2, 0f, bitmapRect, paint);
        bitmapTriangle = BitmapFactory.decodeResource(getResources(), R.drawable.treangle);
        bitmapTriangle = Bitmap.createScaledBitmap(bitmapTriangle,
                (int) side, (int) side, true);
        this.triangle = new Actor((float) side * 3, 0f, bitmapTriangle, paint);
        bitmapEye = BitmapFactory.decodeResource(getResources(), R.drawable.eye);
        bitmapEye = Bitmap.createScaledBitmap(bitmapEye,
                (int) side, (int) side, true);
        this.eye = new Actor((float) side * 4, 0f, bitmapEye, paint);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (flag) {
            canvas.drawColor(getResources().getColor(R.color.white));
            super.onDraw(canvas);
        }
        else {
            canvas.drawColor(getResources().getColor(R.color.white));
            for (int i = 0; i < lines.size(); i++) {
                canvas.drawLine(lines.get(i).x1, lines.get(i).y1,
                        lines.get(i).x2, lines.get(i).y2, paint);
                Log.d("My", "Нарисовал");
            }
            canvas.drawBitmap(bitmapChange, 0, 0, paint);
            canvas.drawBitmap(bitmapCircle, (int) side, 0, paint);
            canvas.drawBitmap(bitmapRect, (int) side * 2, 0, paint);
            canvas.drawBitmap(bitmapTriangle, (int) side * 3, 0, paint);
            canvas.drawBitmap(bitmapEye, (int) side * 4, 0, paint);
            super.onDraw(canvas);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:{
                if (flag) {
                    flag = false;
                }
                else {
                    if (change.bound(event.getX(), event.getY())) {
                        Intent intent = new Intent(getContext(), Change.class);
                        getContext().startActivity(intent);
                    }
                    else if (circle.bound(event.getX(), event.getY())) {

                    }
                    else if (rect.bound(event.getX(), event.getY())) {

                    }
                    else if (triangle.bound(event.getX(), event.getY())) {

                    }
                    else if (eye.bound(event.getX(), event.getY())) {
                        flag = true;
                    }
                    else {
                        switch (brushMode) {
                            case 0:{
                                line = false;
                                break;
                            }
                            case 1:{
                                Log.d("My", "Начало линии");
                                line = true;
                                lines.add(new Line(event.getX(), event.getY()));
                                break;
                            }
                            case 2:{
                                line = false;
                                break;
                            }
                        }
                    }
                }
                break;
            }
            case MotionEvent.ACTION_UP:{
                if (line) {
                    lines.get(lines.size() - 1).setXY2(event.getX(), event.getY());
                    Log.d("My", "Конец линии");
                }
            }
        }
        return true;
    }
}
