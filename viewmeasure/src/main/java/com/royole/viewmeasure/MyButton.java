/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.royole.viewmeasure;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.Button;

/**
 * Created by nixu on 2017/11/28.
 */

public class MyButton extends Button {
    private static final String TAG = "MyViewButton";

    public MyButton(Context context) {
        super(context);
    }

    public MyButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public MyButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchKeyEventPreIme(KeyEvent event) {
        Log.d(TAG, "zhanghao dispatchKeyEventPreIme: "+event);
        return super.dispatchKeyEventPreIme(event);
    }

    @Override
    public boolean onKeyPreIme(int keyCode, KeyEvent event) {
        Log.d(TAG, "zhanghao onKeyPreIme: "+event);
        return super.onKeyPreIme(keyCode, event);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Log.d(TAG, "zhanghao onKeyDown: "+event);
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onKeyLongPress(int keyCode, KeyEvent event) {
        Log.d(TAG, "zhanghao onKeyLongPress: "+event);
        return super.onKeyLongPress(keyCode, event);
    }

    @Override
    public boolean onKeyMultiple(int keyCode, int repeatCount, KeyEvent event) {
        Log.d(TAG, "zhanghao onKeyMultiple: "+event);
        return super.onKeyMultiple(keyCode, repeatCount, event);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        Log.d(TAG, "zhanghao onKeyUp: "+event);
        return super.onKeyUp(keyCode, event);
    }

    @Override
    public boolean dispatchKeyShortcutEvent(KeyEvent event) {
        Log.d(TAG, "zhanghao dispatchKeyShortcutEvent: "+ event);
        return super.dispatchKeyShortcutEvent(event);
    }

    @Override
    public boolean onKeyShortcut(int keyCode, KeyEvent event) {
        Log.d(TAG, "zhanghao onKeyUp: "+event);
        return super.onKeyShortcut(keyCode, event);
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        Log.d(TAG, "zhanghao dispatchKeyEvent: "+event);
        return super.dispatchKeyEvent(event);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Log.d(TAG, "onMeasure: ");
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    @Override
    public void layout(int l, int t, int r, int b) {
        Log.d(TAG, "layout: ");
        super.layout(l, t, r, b);

    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        Log.d(TAG, "onLayout: ");
        super.onLayout(changed, left, top, right, bottom);

    }

    @Override
    public void draw(Canvas canvas) {
        Log.d(TAG, "draw: ");
        super.draw(canvas);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        Log.d(TAG, "onDraw: ");
        super.onDraw(canvas);

    }




    @Override
    protected void dispatchDraw(Canvas canvas) {
        Log.d(TAG, "dispatchDraw: ");
        super.dispatchDraw(canvas);

    }
}
