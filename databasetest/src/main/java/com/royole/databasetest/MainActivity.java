/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.royole.databasetest;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private MyDatabaseHelper myDatabaseHelper;
    private int version = 2;
    private TextView textView;
    private static final String TAG = "zhanghao";
    private static final boolean DEBUG = Log.isLoggable(TAG,Log.DEBUG);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDatabaseHelper = new MyDatabaseHelper(this,"Book.db",null,version);
        textView = (TextView)findViewById(R.id.showContent);
        /**             *
         * 只有调用了该方法，TextView才能不依赖于ScrollView而实现滚动的效果。
         * 要在XML中设置TextView的textcolor，否则，当TextView被触摸时，会灰掉。
         */

        textView.setMovementMethod(ScrollingMovementMethod.getInstance());
    }

    public void onClick(View view){
        switch (view.getId()){
            case R.id.create_table:
                myDatabaseHelper.getWritableDatabase();
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
        SQLiteDatabase sqLiteDatabase = myDatabaseHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name","Han meimei");
        contentValues.put("author","dan nin");
        contentValues.put("pages",454);
        contentValues.put("price",16.96);
        sqLiteDatabase.insert("Book",null,contentValues);
        contentValues.clear();
        contentValues.put("name","love the moon");
        contentValues.put("author","housidon");
        contentValues.put("pages",566);
        contentValues.put("price",23.33);
        sqLiteDatabase.insert("Book",null,contentValues);
    }

    private void updataData(){
        SQLiteDatabase db = myDatabaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("price",10.99);
        db.update("Book",values,"name = ?",new String[]{"love the moon"});

    }

    private void deleteData(){
        SQLiteDatabase db = myDatabaseHelper.getWritableDatabase();
        db.delete("Book","name = ?",new String[]{"love the moon"});
    }
    private void queryData(){
        SQLiteDatabase db = myDatabaseHelper.getReadableDatabase();
        Cursor cursor = db.query("Book",null,null,null,null,null,null,null);
        if(cursor != null && cursor.moveToFirst()){
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("id\t author\t\t price\t pages\t name\n");
            do{
                int id = cursor.getInt(cursor.getColumnIndex("id"));
                String name = cursor.getString(cursor.getColumnIndex("name"));
                String author = cursor.getString(cursor.getColumnIndex("author"));
                int pages = cursor.getInt(cursor.getColumnIndex("price"));
                double price = cursor.getDouble(cursor.getColumnIndex("pages"));
                stringBuilder.append(id);
                stringBuilder.append("\t "+name);
                stringBuilder.append("\t "+author);
                stringBuilder.append("\t "+pages);
                stringBuilder.append("\t "+price);
                stringBuilder.append("\n");
                if(ifLog()) Log.d(TAG, "queryData: "+id);
            }while (cursor.moveToNext());
            textView.setText(stringBuilder.toString().trim());
        }
        if(cursor != null){
            cursor.close();
        }

    }

    private boolean ifLog(){
        return Log.isLoggable(TAG,Log.DEBUG);
    }
}
