/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.royole.www.tabhosttest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.TabHost;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private TabHost mTabHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        setContentView(R.layout.main);
        mTabHost = (TabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup();
        LayoutInflater inflater = LayoutInflater.from(this);
        //关于LayoutInflater详细，请看我的另外一篇转载的总结
        inflater.inflate(R.layout.tab1, mTabHost.getTabContentView());
        inflater.inflate(R.layout.tab2, mTabHost.getTabContentView());
        inflater.inflate(R.layout.tab3, mTabHost.getTabContentView());
        mTabHost.addTab(mTabHost.newTabSpec("tab01")
                .setIndicator("标签页一")
                .setContent(R.id.linearLayout1));//添加第一个标签页
        mTabHost.addTab(mTabHost.newTabSpec("tab02")
                .setIndicator("标签页二")
                .setContent(R.id.linearLayout2));//添加第二个标签页
        mTabHost.addTab(mTabHost.newTabSpec("tab03")
                .setIndicator("标签页三")
                .setContent(R.id.linearLayout3));//添加第三个标签页
    }
}
