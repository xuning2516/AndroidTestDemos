/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.royole.broadcasttest;

import java.util.ArrayList;

/**
 * Created by nixu on 2017-07-31.
 */
public class ActivityController {
    private static ArrayList<BaseActivity> activities = new ArrayList<BaseActivity>();

    public static void addActivity(BaseActivity activity){
        activities.add(activity);
    }

    public static void removeActivity(BaseActivity activity){
        activities.remove(activity);
    }

    public static void  finishAll(){
        for(BaseActivity activity :activities){
            activity.finish();
        }
    }

}
