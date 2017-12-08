/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.royole.www.tabhosttest;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.TabHost;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "zhang,main";

    private TabHost mTabHost;
    private Context mContext;
    private Toast mToast;
    private Drawable mDefaultNavigationIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        mToast = Toast.makeText(mContext,"",Toast.LENGTH_SHORT);
        //setContentView(R.layout.main);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.setSubtitle("Sub Title");
        setSupportActionBar(toolbar);
        String title = toolbar.getTitle().toString();
        Log.d(TAG, "onCreate: title" + title);
        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();


        toolbar.setNavigationIcon(R.drawable.nav_icon);
        toolbar.setNavigationContentDescription("返回");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 mToast.setText("nav onclick");
                mToast.show();
            }
        });

        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(false);

        mTabHost = (TabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup();
        LayoutInflater inflater = LayoutInflater.from(this);
        //关于LayoutInflater详细，请看我的另外一篇转载的总结
        inflater.inflate(R.layout.tab1, mTabHost.getTabContentView());
        inflater.inflate(R.layout.tab2, mTabHost.getTabContentView());
        inflater.inflate(R.layout.tab3, mTabHost.getTabContentView());
        mTabHost.addTab(mTabHost.newTabSpec("tab01")
                .setIndicator("鼠标")
                .setContent(R.id.linearLayout1));//添加第一个标签页
        mTabHost.addTab(mTabHost.newTabSpec("tab02")
                .setIndicator("滑动")
                .setContent(R.id.linearLayout2));//添加第二个标签页
        mTabHost.addTab(mTabHost.newTabSpec("tab03")
                .setIndicator("手写笔")
                .setContent(R.id.linearLayout3));//添加第三个标签页
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.gravity = Gravity.LEFT | Gravity.TOP;
        //MyWindowManager.addBoundView(mContext,layoutParams,500,18,30,0);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.settings:
                mToast.setText(R.string.settings_name);
                mToast.show();
                break;
            case R.id.scoreApp:
                mToast.setText(R.string.score_settings);
                mToast.show();
                break;
            case R.id.appinfo:
                mToast.setText(R.string.app_info);
                mToast.show();
                break;
            case R.id.moreTrigger:
                mToast.setText(R.string.more_trigger);
                mToast.show();
            default:
                break;
        }
        return true;
    }

    public void onClick(View view){
        switch (view.getId()){
            case R.id.toFirstActivity:
                Intent intent = new Intent(mContext,FirstActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
