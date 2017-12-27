/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.royole.viewmeasure;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by lezh on 11/29/2017.
 */

public class PrivacyLoadingReceiver extends BroadcastReceiver {

    public static final String ENTER_PRIVACY_MODE = "com.royole.loading.test";
    public static final String EXIT_PRIVACY_MODE = "com.royole.loading.untest";


    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(ENTER_PRIVACY_MODE)){
            //呼出Activity
            Intent it = new Intent(context,PrivacyLoadingActivity.class);
            it.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            it.putExtra("type",1);
            context.startActivity(it);
        }else if (intent.getAction().equals(EXIT_PRIVACY_MODE)){
            Intent it = new Intent(context,PrivacyLoadingActivity.class);
            it.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            it.putExtra("type",2);
            context.startActivity(it);
        }
    }
}
