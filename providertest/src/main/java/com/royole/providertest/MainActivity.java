/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.royole.providertest;

import android.content.ContentValues;
import android.content.res.Configuration;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Process;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "zhanghao";
    private static final Uri BOOK_URI =
            Uri.parse("content://com.royole.databasetest.provider"+"/book");
    private static final Uri CATEGORY_URI =
            Uri.parse("content://com.royole.databasetest.provider"+"/category");

    private TextView textView;

    private static final String NAME_BASE = "据了解，北京我爱出行服务科技网络公司简称“我爱出行”成立于2017年4月1日。核心团队来自各大互联网精英，其中包括新浪、去哪儿、乐视、同程、360等。\n" +
            "其核心价值“爱出行 爱分享”便围绕两大功能，让更多用户在未来感受人工智能出行服务的便利。\n" +
            "出行功能云智能“千人千版”，通过用户习惯采集，对每个用户进行数据反馈、分析，最后给用户精准推送有效出行内容，最终实现千人使用千个版面。其精华推送包含出行提示、交通、天气预报等常规信息;也涵盖通过算法获取用户旅游等出行需求，然后自动调取OTA最具竞争力旅游产品进行垂直推送。\n" +
            "分享功能“10秒分享”，通过对用户出行照片、视频上传进行智能识别，在10秒内生成妙趣横生的文字进行智能匹配，并可根据素材风格生成对应文字，用户只需根据自己个性稍作修改，便可以快速发到各大社交平台。\n" +
            "这两大核心功能只是我爱出行，出行服务功能体系中的一部分，而在后期全球可用的“出行地图“，全球全景可视的”分享推荐“，甚至不用在担心出国语言问题的”语言通“都将收录在”我爱出行“功能玩法里。\n" +
            "我爱出行公司CEO傅阳曾表示：“有一天，我要拿着手机，里面只要有我爱出行，就可以独闯欧洲，美洲，全世界各个角落。”而这正是当前出行市场痛点;语言限制、国外地图支持、救援等功能服务，在目前市场并没有哪家做到整合使用。\n" +
            "我爱出行将真正改变的出行方式，任何时间想出国玩，害怕语言等硬性条件的无法实现，旅途中把快乐快速分享的愿望，都将在未来得到出行人工智能解决。";
    private static final int NAME_LENGTH = NAME_BASE.length();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView)findViewById(R.id.showContent);
        /**             *
         * 只有调用了该方法，TextView才能不依赖于ScrollView而实现滚动的效果。
         * 要在XML中设置TextView的textcolor，否则，当TextView被触摸时，会灰掉。
         */

        textView.setMovementMethod(ScrollingMovementMethod.getInstance());

            Log.d(TAG, "zhanghao onCreate: pid " + Process.myPid()
                    + " tid "+ Process.myTid()+" uid "+Process.myUid());

    }

    public void onClick(View view){
        switch (view.getId()){
            case R.id.create_table:

                break;
            case R.id.add_data:
                addData();
                break;
            case R.id.update_data:
                updataData();
                break;
            case R.id.delete_data:
                deleteData();
                break;
            case R.id.query_data:
                queryData();
                break;
            default:
                break;
        }
    }

    private void addData(){
        try {
            ContentValues contentValues = new ContentValues();
            Random pageRandom = new Random();
            int pages = pageRandom.nextInt(500);
            contentValues.put("name", getBookName());
            contentValues.put("author", "dan nin");
            contentValues.put("pages", pages);
            contentValues.put("price", 16.96);
            getContentResolver().insert(BOOK_URI, contentValues);
            contentValues.clear();
            contentValues.put("name", getBookName());
            contentValues.put("author", "housidon");
            pages = pageRandom.nextInt(500);
            contentValues.put("pages", pages);
            contentValues.put("price", 23.33);
            getContentResolver().insert(BOOK_URI, contentValues);
        }catch (IllegalArgumentException ex){
            ex.printStackTrace();
        }
    }

    private void updataData(){
        try {
            ContentValues values = new ContentValues();
            values.put("price", 10.99);
            getContentResolver().update(BOOK_URI, values, "name = ?", new String[]{"love the moon"});
        }catch (IllegalArgumentException ex){
            ex.printStackTrace();
        }

    }

    private void deleteData(){
        try {
        getContentResolver().delete(BOOK_URI,"name = ?",new String[]{"love the moon"});
        }catch (IllegalArgumentException ex){
            ex.printStackTrace();
        }
    }
    private void queryData(){

        Cursor cursor = getContentResolver().query(BOOK_URI, null, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("id\t author\t\t price\t pages\t name\n");
            do {
                int id = cursor.getInt(cursor.getColumnIndex("id"));
                String name = cursor.getString(cursor.getColumnIndex("name"));
                String author = cursor.getString(cursor.getColumnIndex("author"));
                int pages = cursor.getInt(cursor.getColumnIndex("price"));
                double price = cursor.getDouble(cursor.getColumnIndex("pages"));
                stringBuilder.append(id);
                stringBuilder.append("\t " + name);
                stringBuilder.append("\t " + author);
                stringBuilder.append("\t " + pages);
                stringBuilder.append("\t " + price);
                stringBuilder.append("\n");
                if (ifLog()) Log.d(TAG, "queryData: " + id);
            } while (cursor.moveToNext());
            textView.setText(stringBuilder.toString().trim());
        }
        if (cursor != null) {
            cursor.close();
        }

    }

    private String getBookName(){
        Random rand = new Random();
        int randNum = rand.nextInt(NAME_LENGTH -2);
        return NAME_BASE.substring(randNum,randNum + 2);
    }

    private boolean ifLog(){
        return Log.isLoggable(TAG,Log.DEBUG);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        Log.d(TAG, "onConfigurationChanged: "+ newConfig);
        super.onConfigurationChanged(newConfig);
    }
}
