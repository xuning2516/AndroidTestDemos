/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.royole.broadcasttest;

import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends BaseActivity {
    private long lastTime ;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view){
        switch(view.getId()){
            case R.id.toA_button:
                Intent intent = new Intent(MainActivity.this,AActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed(); //back to top
//        Log.d(TAG, "onBackPressed: ",new Throwable("here"));

        long currentTime = System.currentTimeMillis();
        Log.d(TAG, "onBackPressed: lastTime "+lastTime+" ,currentTime:"+currentTime);
        long currentThreadTime = SystemClock.currentThreadTimeMillis();
        Log.d(TAG, "onBackPressed: currentThreadTime "+ currentThreadTime);
        long uptTime = SystemClock.uptimeMillis();
        Log.d(TAG, "onBackPressed: uptTime "+uptTime);
        long elapsedTime = SystemClock.elapsedRealtime();
        Log.d(TAG, "onBackPressed: elapsedTime "+ elapsedTime);
        Log.d(TAG, "onBackPressed: refFormatNowDate "+ refFormatNowDate());
        if(lastTime == 0 || currentTime - lastTime < 2000){
//            Toast.makeText(MainActivity.this,"再按一次退出",Toast.LENGTH_SHORT).show();
            Log.d(TAG, "onBackPressed: less");
        }else{
            Log.d(TAG, "onBackPressed: more");
            MyApplication myApplication = (MyApplication) getApplication();
//            myApplication.finishAll();

        }
        lastTime = currentTime;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: "+this);
    }

    public String refFormatNowDate() {
        Date nowTime = new Date(System.currentTimeMillis());
        SimpleDateFormat sdFormatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
        String retStrFormatNowDate = sdFormatter.format(nowTime);
        return retStrFormatNowDate;
    }
}
