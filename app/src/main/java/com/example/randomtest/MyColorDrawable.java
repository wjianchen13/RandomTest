package com.example.randomtest;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;

import androidx.annotation.NonNull;

/**
 * A specialized Drawable that fills the Canvas with a specified color.
 * Note that a MyColorDrawable ignores the ColorFilter.
 *
 * <p>It can be defined in an XML file with the <code>&lt;color></code> element.</p>
 *
 * @attr ref android.R.styleable#ColorDrawable_color
 */
public class MyColorDrawable extends ColorDrawable {

    public MyColorDrawable() {
    }

    public MyColorDrawable(int color) {
        super(color);
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        Rect rect = getBounds();
        super.draw(canvas);
    }
}

