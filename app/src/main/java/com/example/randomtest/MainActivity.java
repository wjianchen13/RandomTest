package com.example.randomtest;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.StateListDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.graphics.drawable.shapes.RoundRectShape;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ImageView imgvTest;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imgvTest = findViewById(R.id.imgv_test);
        Drawable drawable = createDrawableSelector(this);
        imgvTest.setBackground(getRoundRectDrawable());
    }

    public void onTest(View v) {
        Toast.makeText(this, "test", Toast.LENGTH_SHORT).show();
//        imgvTest.setEnabled(false);
        imgvTest.setBackground(DrawableManager.getInstance().getDrawable(20));
        
//        println("3.21: " + Math.round(3.21) + "  3.65: " + Math.round(3.65)); 
    }
    


    public void onTest1(View v) {
        String color = getRandomColor();
        println("color: " + color);
    }
    
    public String getRandomColor() {
//        String color = "0xff";
        StringBuilder sb = new StringBuilder("#ff");
        sb.append(Integer.toHexString(getRandom(100, 201)));
        sb.append(Integer.toHexString(getRandom(100, 201)));
        sb.append(Integer.toHexString(getRandom(100, 201)));
        return sb.toString();
    }
    

    public int getRandom(int start, int end) {
        if(end < start)
            return start;
        Random random = new Random();
        int num = random.nextInt(end - start) + start;
        if(num < 100 || num > 200) {
            println("生成数据出现错误了");
        }
        return num;
    }

    /**
     * 生成圆角drawable
     * @return
     */
    private ShapeDrawable getRoundRectDrawable() {
        float[] externalRound = {58, 58, 8, 8, 8, 8, 8, 8};//外部矩形的8个圆角半径,为什么是8个? 因为这个居然是一个角2个半圆组成的(太精细了...)
        RectF distanceRectF = new RectF(10, 10, 10, 10); //内部矩形与外部矩形的距离
        float[] insideRound = {10, 10, 10, 10, 10, 10, 10, 10}; //内部矩形的8个圆角半径值
        ShapeDrawable shapeDrawable = new ShapeDrawable(new RoundRectShape(externalRound, null, null));
//        shapeDrawable.getPaint().setColor(Color.parseColor());
        Rect rect = new Rect();
        rect.top = 0;
        rect.left = 0;
        rect.bottom = 16;
        rect.right = 16;
        shapeDrawable.setBounds(rect);
        return shapeDrawable;
    }

    /**
     * 生成矩形drawable
     * @return
     */
    private ShapeDrawable getRectDrawable() {
        ShapeDrawable shapeDrawable = new ShapeDrawable(new RectShape());
        shapeDrawable.getPaint().setColor(Color.RED);
//        Rect rect = new Rect();
//        rect.top = 0;
//        rect.left = 0;
//        rect.bottom = 50;
//        rect.right = 50;
//        shapeDrawable.setBounds(rect);
        return shapeDrawable;
    }

    /**
     * 生成drawable
     * @param context
     * @return
     */
    private StateListDrawable createDrawableSelector(Context context) {
        Drawable checked = new ColorDrawable(getResources().getColor(R.color.disnable));
        Drawable unchecked = new ColorDrawable(getResources().getColor(R.color.colorPrimaryDark));
        Drawable disabled = new ColorDrawable(getResources().getColor(R.color.colorAccent));
        StateListDrawable stateList = new StateListDrawable();
        int statePressed = android.R.attr.state_pressed;
        int stateChecked = android.R.attr.state_checked;
        int stateFocused = android.R.attr.state_focused;
        int stateensable = android.R.attr.state_enabled;
        stateList.addState(new int[] {stateensable}, disabled);
        stateList.addState(new int[] {stateChecked}, checked);
        stateList.addState(new int[] {statePressed}, checked);
        stateList.addState(new int[] {stateFocused}, checked);
        stateList.addState(new int[] {}, unchecked);
        return stateList;
    }
    
    public static void println(String str) {
        System.out.println("===============================>" + str);
    }
    
    
}