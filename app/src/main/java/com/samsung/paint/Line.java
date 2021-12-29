package com.samsung.paint;

public class Line {
    public float x1, y1, x2, y2;

    public Line(float x1, float y1) {
        this.x1 = x1;
        this.y1 = y1;
    }

    public void setXY2(float x, float y) {
        x2 = x;
        y2 = y;
    }
}
