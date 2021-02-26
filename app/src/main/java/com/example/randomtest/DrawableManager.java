package com.example.randomtest;

import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static com.example.randomtest.MainActivity.println;

/**
 * 实现recyclerview随机背景，保留生成drawable一定的数量
 * 超过一定数量直接从缓存取
 */
public class DrawableManager {

    /**
     * 随机生成颜色起始值
     */
    private static final int COLOR_START = 100;

    /**
     * 随机生成颜色起始值
     */
    private static final int COLOR_END = 201;

    private static DrawableManager INSTANCE;
    
    /**
     * 最多生成20个
     */
    private static final int MAX = 10;

    /**
     * 圆角-drawable集合
     */
    private Map<Integer, DrawableBean> mDrawableMap;

    public static DrawableManager getInstance() {
        if (INSTANCE == null) {
            synchronized (DrawableManager.class) {
                if (INSTANCE == null) {
                    INSTANCE = new DrawableManager();
                }
            }
        }
        return INSTANCE;
    }
    
    public DrawableManager() {
        this.mDrawableMap = new HashMap<>();
    }

    /**
     * 随机生成drawable
     * @return
     */
    public Drawable getDrawable() {
        return getDrawable(0);
    }

    /**
     * 获取drawable
     * @param angle
     * @return
     */
    public Drawable getDrawable(int angle) {
        println("=======================================================================> getDrawable");
        if(mDrawableMap == null)
            mDrawableMap = new HashMap<>();
        
        if(!mDrawableMap.containsKey(angle))
            mDrawableMap.put(angle, new DrawableBean(angle));
        return mDrawableMap.get(angle).getDrawable();
    }

    public static class DrawableBean {

        /**
         * 有效数据数量
         */
        private int mCount;
        
        /**
         * 圆角角度
         */
        private float mAngle;

        /**
         * 保存颜色值
         */
        private String[] mColors;
        
        /**
         * 保存颜色值和drawable映射
         * #ff00ff00-drawable
         */
        private Map<String, Drawable> mDrawables;

        public DrawableBean() {
            mAngle = 0;
            mColors = new String[MAX];
            mCount = 0;
            mDrawables = new HashMap<>();
        }

        public DrawableBean(int angle) {
            mAngle = angle;
            mColors = new String[MAX];
            mCount = 0;
            mDrawables = new HashMap<>();
        }

        /**
         * 判断随机生成的Drawable是否达到一定数量，如果不是，继续生成，否则随机从生成的列表取出返回
         * @return
         */
        public Drawable getDrawable() {
            try {
                if (mCount < MAX) {
                    println("数量小于最大值，可以继续生成");
                    String color = getRandomColor();
                    if(mDrawables.containsKey(color)) { // 预防生成的颜色之前已经生成过
                        println("生成颜色值重复");
                        return getDrawable(); 
                    }
                    mColors[mCount] = color;
                    mCount++;
                    Drawable drawable = createDrawable(color);
                    mDrawables.put(color, drawable);
                    return drawable;

                } else {
                    println("数量大于等于最大值，不能继续生成");
                    int random = getRandom(0, MAX);
                    return mDrawables.get(mColors[random]);
                }
            } catch (Exception e){
                e.printStackTrace();
            }
            return createDrawable("#ff646464");
        }

        /**
         * 创建drawable
         * 这个可以用在glide的placeholder（drawable)中，没有效果，暂时不知道什么原因，使用ColorDrawable可以
         * @return
         */
        private ShapeDrawable createDrawable(String color) {
            // 外部矩形的8个圆角半径,为什么是8个? 因为这个居然是一个角2个半圆组成的(太精细了...)
            float[] round = {mAngle, mAngle, mAngle, mAngle, mAngle, mAngle, mAngle, mAngle};
            ShapeDrawable shapeDrawable = new ShapeDrawable(new RoundRectShape(round, null, null));
            shapeDrawable.getPaint().setColor(Color.parseColor(color));
            int angle = Math.round(mAngle);
            Rect rect = new Rect();
            rect.top = 0;
            rect.left = 0;
            rect.bottom = angle * 2;
            rect.right = angle * 2;
            shapeDrawable.setBounds(rect);
            return shapeDrawable;
        }

        /**
         * 生成随机颜色 
         * 例如：#ff00ff00
         * @return
         */
        public String getRandomColor() {
            StringBuilder sb = new StringBuilder("#ff");
            sb.append(Integer.toHexString(getRandom(COLOR_START, COLOR_END)));
            sb.append(Integer.toHexString(getRandom(COLOR_START, COLOR_END)));
            sb.append(Integer.toHexString(getRandom(COLOR_START, COLOR_END)));
            return sb.toString();
        }
        
        /**
         * 随机返回100-200的数+
         * @param start
         * @param end
         * @return
         */
        public int getRandom(int start, int end) {
            Random random = new Random();
            return random.nextInt(end - start) + start;
        }
    }
    
}
