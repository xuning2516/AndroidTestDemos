/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.royole.broadcasttest;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class AActivity extends BaseActivity {
    private static final String TAG = "AActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a);
    }

    public void onClick(View view){
        switch (view.getId()){
            case R.id.toMain_Button:
                Intent intent = new Intent(AActivity.this,MainActivity.class);
                startActivity(intent);
                break;
            case R.id.finishAll:
                finishAllActivity();
                break;
            default:
                break;
        }
    }

    private void finishAllActivity(){
        MyApplication myApplication = (MyApplication)getApplication();
        myApplication.finishAll();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: "+this);
    }
}
