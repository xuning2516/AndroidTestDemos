/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.royole.viewmeasure;

import android.app.Activity;
import android.app.ActivityOptions;
import android.app.ActivityManager;
import android.hardware.usb.UsbManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.UserHandle;
import java.io.File;
import android.net.Uri;
import android.util.Log;
import android.text.format.Formatter;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.regex.*;
import java.io.File;

public class PrivacyLoadingActivity extends Activity {

    private static final String TAG = "zhanghaop";

    IntentFilter filter;
    BroadcastReceiver receiver;
    Activity mContext;
    ImageView loading;
    TextView tip;

    UsbManager mUsbManager;
    
    private static long TIME_TARGET;
    private static final int TIME_MINIMUM = 1000 ;

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TIME_TARGET = System.currentTimeMillis()+TIME_MINIMUM;
        Log.i(TAG, " onCreate was called!");
        mContext = this;

        mUsbManager = (UsbManager) mContext.getSystemService(Context.USB_SERVICE);
        int mode = this.getIntent().getIntExtra("type",0);
        //if exit PrivateMode when now is private mode
        //mode = 2;
        if (mode == 2)
            finish();
        else {
            addListeners();

            Log.i(TAG, " onCreate mode:" + mode);
            //set window attribute
            getWindow().setType(WindowManager.LayoutParams.TYPE_KEYGUARD_DIALOG);
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            if (mode == 1)
                setContentView(R.layout.loading_enter);
            else
                setContentView(R.layout.loading);
//            WindowManager.LayoutParams attrs = getWindow().getAttributes();
//            attrs.setTitle("DISABLE_HOME_KEY");
//            this.getWindow().setAttributes(attrs);

            //set animation
            RotateAnimation loading_animation = (RotateAnimation) AnimationUtils.loadAnimation(this, R.anim.loading_image);
            loading = (ImageView) findViewById(R.id.loading_pic);
            tip = (TextView) findViewById(R.id.textView);
            //loading.startAnimation(loading_animation);

            //excute switch mode

        }
    }

    private void addListeners(){
        filter = new IntentFilter();
        filter.addAction(Intent.ACTION_MEDIA_SCANNER_STARTED);
        filter.addAction(Intent.ACTION_MEDIA_SCANNER_FINISHED);
        filter.addDataScheme("file");

        IntentFilter testfilter = new IntentFilter();
        testfilter.addAction("test.privacy.loading.broadcast");
        receiver = new BroadcastReceiver(){
            @Override
            public void onReceive(Context context, Intent intent) {
                Log.i("DEBUG LEZH","onReceive");
                if (Intent.ACTION_MEDIA_SCANNER_STARTED.equals(intent.getAction())){
                    Message m = new Message();
                    m.what=1;
                    handler.sendMessage(m);
                }else if (Intent.ACTION_MEDIA_SCANNER_FINISHED.equals(intent.getAction())){
                    Message m = new Message();
                    m.what=2;
                    handler.sendMessage(m);
                }else if ("test.privacy.loading.broadcast".equals(intent.getAction())){
                    Message m = new Message();
                    m.what=2;
                    handler.sendMessage(m);
                }
            }
        };
        mContext.registerReceiver(receiver,filter);
        mContext.registerReceiver(receiver,testfilter);
    }



    
    @Override
    public void onAttachedToWindow() {
        Log.d(TAG, "onAttachedToWindow: ");
        //this.getWindow().setType(WindowManager.LayoutParams.TYPE_KEYGUARD_DIALOG);
        super.onAttachedToWindow();
    }

    @Override
    protected void onPause() {
        Log.d(TAG, "onStop: " + getWindow().getAttributes());
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.d(TAG, "onStop: " + getWindow().getAttributes());
        super.onStop();
        mContext.unregisterReceiver(receiver);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Log.i(TAG, " keyCode  " + keyCode);
        if(keyCode == KeyEvent.KEYCODE_BACK){
            return true;
        }else if(keyCode==KeyEvent.KEYCODE_HOME){
            return false;
        }else if(keyCode==KeyEvent.KEYCODE_VOLUME_UP){
            return true;
        }else if(keyCode==KeyEvent.KEYCODE_VOLUME_DOWN){
            return true;
        }else {
            return super.onKeyDown(keyCode, event);
        }
    }

}
