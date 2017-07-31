/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.royole.broadcasttest;

import android.app.Application;

import java.util.ArrayList;

/**
 * Created by nixu on 2017-07-31.
 */
public class MyApplication extends Application{
        private ArrayList<BaseActivity> activities;

    @Override
    public void onCreate() {
        super.onCreate();
        activities = new ArrayList<BaseActivity>();
    }

    public void addActivity( BaseActivity activity){
        activities.add(activity);
    }

    public void removeActivity(BaseActivity activity){
        activities.remove(activity);
    }

    public void finishAll(){
        for(BaseActivity activity:activities){
            activity.finish();
        }
    }
}
