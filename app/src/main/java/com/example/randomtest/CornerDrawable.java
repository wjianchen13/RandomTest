package com.example.randomtest;


import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * 实现圆角ColorDrawable，这个可以用在glide的placeholder（drawable)中
 */
public class CornerDrawable extends Drawable {

    /**
     * 默认值
     */
    private static int round = 20;
    private static int color = Color.BLUE;

    private RectF rectF = new RectF();
    private Paint paint = new Paint();

    public CornerDrawable(int c) {
        
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        paint.setAntiAlias(true);
        paint.setColor(color);
        canvas.drawRoundRect(new RectF(getBounds().left, getBounds().top, getBounds().right, getBounds().bottom), round, round, paint);
    }

    @Override
    public void setAlpha(int i) {

    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {

    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }


    private static int getRandomColor() {
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < 6; i++) {
            buffer.append(arr[(int) (Math.random() * arr.length)]);
        }
        return Color.parseColor("#" + buffer.toString());
    }

    private static String[] arr = new String[]{
            "0",
            "1",
            "2",
            "3",
            "4",
            "5",
            "6",
            "7",
            "8",
            "9",
            "a",
            "b",
            "c",
            "d",
            "e",
            "f"
    };

}