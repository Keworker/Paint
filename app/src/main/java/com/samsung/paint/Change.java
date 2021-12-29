package com.samsung.paint;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;

public class Change extends Activity
        implements View.OnClickListener, SeekBar.OnSeekBarChangeListener {
    SeekBar width, red, green, blue, alpha;
    Button accept;
    static int redValue = 0, greenValue = 0, blueValue = 0, alphaValue = 255;
    static float widthValue = 10.0f;
    static int widthPercent = 10, redPercent = 0, greenPercent = 0, bluePercent = 0,
            alphaPercent = 100;
    Example example;
    RadioGroup radio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change);
        width = findViewById(R.id.width);
        width.setProgress(widthPercent);
        red = findViewById(R.id.red);
        red.setProgress(redPercent);
        red.setOnSeekBarChangeListener(this);
        green = findViewById(R.id.green);
        green.setProgress(greenPercent);
        green.setOnSeekBarChangeListener(this);
        blue = findViewById(R.id.blue);
        blue.setProgress(bluePercent);
        blue.setOnSeekBarChangeListener(this);
        alpha = findViewById(R.id.alpha);
        alpha.setProgress(alphaPercent);
        alpha.setOnSeekBarChangeListener(this);
        radio = findViewById(R.id.radio);
        accept = findViewById(R.id.accept);
        accept.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.accept:{
                widthPercent = width.getProgress();
                widthValue = (float) widthPercent;
                Painter.paint.setStrokeWidth(widthValue);
                switch (radio.getCheckedRadioButtonId()) {
                    case R.id.otrezok:{
                        Painter.brushMode = 1;
                        break;
                    }
                    case R.id.lastik:{
                        Painter.brushMode = 2;
                        break;
                    }
                    default:{
                        Painter.brushMode = 0;
                    }
                }
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
            }
        }
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        switch (seekBar.getId()) {
            case R.id.red:{
                redPercent = red.getProgress();
                redValue = redPercent * 255 / 100;
                Painter.paint.setARGB(Painter.paint.getAlpha(), redValue, greenValue, blueValue);
                break;
            }
            case R.id.green:{
                greenPercent = green.getProgress();
                greenValue = greenPercent * 255 / 100;
                Painter.paint.setARGB(Painter.paint.getAlpha(), redValue, greenValue, blueValue);
                break;
            }
            case R.id.blue:{
                bluePercent = blue.getProgress();
                blueValue = bluePercent * 255 / 100;
                Painter.paint.setARGB(Painter.paint.getAlpha(), redValue, greenValue, blueValue);
                break;
            }
            case R.id.alpha:{
                alphaPercent = alpha.getProgress();
                alphaValue = alphaValue * 255 / 100;
                Painter.paint.setARGB(alphaValue, redValue, greenValue, blueValue);
                break;
            }
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    public static class Example extends View {
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
}