package com.samsung.paint;

import android.graphics.Bitmap;
import android.graphics.Paint;

public class Actor {
    float x, y;
    Bitmap bitmap;
    android.graphics.Paint paint;

    public Actor(float x, float y, Bitmap bitmap, Paint paint) {
        this.x = x;
        this.y = y;
        this.bitmap = bitmap;
        this.paint = paint;
    }

    public boolean bound(float x, float y) {
        if (x > this.x && x < this.x + bitmap.getWidth()) {
            if (y > this.y && y < this.y + bitmap.getHeight()) {
                return true;
            }
        }
        return false;
    }
}
