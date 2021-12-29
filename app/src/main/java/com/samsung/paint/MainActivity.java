package com.samsung.paint;

import static com.samsung.paint.R.color.brush;
import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import com.samsung.paint.R;

public class MainActivity extends AppCompatActivity {
    static int screenWidth, screenHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        screenWidth = size.x;
        screenHeight = size.y;
        ;
        setContentView(new Painter(this));
    }
}