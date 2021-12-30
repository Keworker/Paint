package com.samsung.paint;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class Painter extends View  {
    double wight, height;
    static double side;
    static boolean flag = false, line = false, brush = true, erase = false, flag0 = true;
    static Paint paint, eraser;
    static int brushMode = 0;
    Bitmap bitmapChange, bitmapCircle, bitmapRect, bitmapTriangle, bitmapEye;
    Actor change, circle, rect, triangle, eye;
    static List<Line> lines = new ArrayList<>();
    final Paint systemPaint = new Paint();
    float pointX, pointY;

    public Painter(Context context) {
        super(context);
        wight = MainActivity.screenWidth;
        height = MainActivity.screenHeight;
        side = wight / 5;
        if (flag0) {
            paint = new Paint();
            eraser = new Paint();
            eraser.setARGB(255, 255, 255, 255);
            flag0 = false;
        }
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
        Log.d("My", String.valueOf(paint.getStrokeWidth()));
        canvas.drawColor(getResources().getColor(R.color.white));
        for (int i = 0; i < lines.size(); i++) {
            canvas.drawLine(lines.get(i).x1, lines.get(i).y1,
                    lines.get(i).x2, lines.get(i).y2, lines.get(i).getBrush());
        }
        if (!flag) {
            canvas.drawBitmap(bitmapChange, 0, 0, systemPaint);
            canvas.drawBitmap(bitmapCircle, (int) side, 0, systemPaint);
            canvas.drawBitmap(bitmapRect, (int) side * 2, 0, systemPaint);
            canvas.drawBitmap(bitmapTriangle, (int) side * 3, 0, systemPaint);
            canvas.drawBitmap(bitmapEye, (int) side * 4, 0, systemPaint);
        }
        super.onDraw(canvas);
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
                                pointX = event.getX();
                                pointY = event.getY();
                                brush = true;
                                line = false;
                                erase = false;
                                break;
                            }
                            case 1:{
                                line = true;
                                brush = false;
                                erase = false;
                                lines.add(new Line(event.getX(), event.getY()));
                                break;
                            }
                            case 2:{
                                pointX = event.getX();
                                pointY = event.getY();
                                erase = true;
                                line = false;
                                brush = false;
                                break;
                            }
                        }
                    }
                }
                break;
            }
            case MotionEvent.ACTION_MOVE:{
                if (brush && !(change.bound(event.getX(), event.getY()) ||
                        rect.bound(event.getX(), event.getY()) ||
                        circle.bound(event.getX(), event.getY()) ||
                        triangle.bound(event.getX(), event.getY()) ||
                        eye.bound(event.getX(), event.getY()))) {
                    lines.add(new Line(pointX, pointY, event.getX(), event.getY()));
                    lines.get(lines.size() - 1).setEndXY(event.getX(), event.getY());
                    lines.get(lines.size() - 1).brush.setColor(paint.getColor());
                    lines.get(lines.size() - 1).brush.setStrokeWidth(paint.getStrokeWidth());
                    pointX = event.getX();
                    pointY = event.getY();
                    invalidate();
                }
                else if (erase && !(change.bound(event.getX(), event.getY()) ||
                        rect.bound(event.getX(), event.getY()) ||
                        circle.bound(event.getX(), event.getY()) ||
                        triangle.bound(event.getX(), event.getY()) ||
                        eye.bound(event.getX(), event.getY()))) {
                    lines.add(new Line(pointX, pointY, event.getX(), event.getY()));
                    lines.get(lines.size() - 1).setEndXY(event.getX(), event.getY());
                    lines.get(lines.size() - 1).brush.setColor(eraser.getColor());
                    lines.get(lines.size() - 1).brush.setStrokeWidth(eraser.getStrokeWidth());
                    pointX = event.getX();
                    pointY = event.getY();
                    invalidate();
                }
                break;
            }
            case MotionEvent.ACTION_UP:{
                if (line && !(change.bound(event.getX(), event.getY()) ||
                        rect.bound(event.getX(), event.getY()) ||
                        circle.bound(event.getX(), event.getY()) ||
                        triangle.bound(event.getX(), event.getY()) ||
                        eye.bound(event.getX(), event.getY()))) {
                    lines.get(lines.size() - 1).setEndXY(event.getX(), event.getY());
                    lines.get(lines.size() - 1).brush.setColor(paint.getColor());
                    lines.get(lines.size() - 1).brush.setStrokeWidth(paint.getStrokeWidth());
                    invalidate();
                }
                break;
            }
        }
        return true;
    }
}
