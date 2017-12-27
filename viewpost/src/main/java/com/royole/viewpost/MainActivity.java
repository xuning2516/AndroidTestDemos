/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.royole.viewpost;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends Activity {
    private TextView mMyTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mMyTextView = (TextView) findViewById(R.id.my_textView);
        Log.d("test", "1 post前 : " + mMyTextView.getMeasuredWidth() + " - height : " +  mMyTextView.getMeasuredHeight());
        new Thread(new Runnable() {

            @Override
            public void run() {
                Log.d("test", "Thread 被执行了");
                mMyTextView.post(new Runnable() {

                    @Override
                    public void run() {
                        Log.d("test", "3 post Runnable : " + mMyTextView.getMeasuredWidth() + " - height : " +  mMyTextView.getMeasuredHeight());
                    }
                });
                try {
                    Thread.sleep(1000);
                }catch (InterruptedException ex){
                    ex.printStackTrace();
                }

                Log.d("test", "Thread 被停止了");
            }
        }).start();
        Log.d("test", "2 post后 : " + mMyTextView.getMeasuredWidth() + " - height : " +  mMyTextView.getMeasuredHeight());
    }

    @Override
    protected void onResume() {
        Log.d("test", "onResume: ");
        super.onResume();

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        Log.d("test", "onWindowFocusChanged: ");
        super.onWindowFocusChanged(hasFocus);
    }
}
