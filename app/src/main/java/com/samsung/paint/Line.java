package com.samsung.paint;

import android.graphics.Paint;

public class Line {
    public float x1, y1, x2, y2;
    public Paint brush;

    public Line(float x1, float y1) {
        this.x1 = x1;
        this.y1 = y1;
        brush = new Paint();
    }

    public Line(float x1, float y1, float x2, float y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        brush = new Paint();
    }

    public void setEndXY(float x, float y) {
        x2 = x;
        y2 = y;
    }

    public Paint getBrush() {
        return this.brush;
    }
}
