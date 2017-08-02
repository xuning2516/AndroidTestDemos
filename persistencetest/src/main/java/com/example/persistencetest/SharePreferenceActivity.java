/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.example.persistencetest;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

public class SharePreferenceActivity extends AppCompatActivity {
    private EditText userNameText;
    private EditText userPasswordText;
    private Context mContext;
    private SharedPreferences mPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_preference);
        userNameText = (EditText)findViewById(R.id.user_name);
        userPasswordText = (EditText)findViewById(R.id.user_password);
        mContext = this;
        mPrefs = getPreferences(MODE_PRIVATE);
    }

    public void onClick(View view){
        switch (view.getId()){
            case R.id.save_to_sharepreference:
                saveToSharePreference();
            break;
            case R.id.read_from_sharepreference:
                readFromSharePreference();
                break;
        }
    }

    private void saveToSharePreference(){
        String userName = userNameText.getText().toString();
        String userPassword = userPasswordText.getText().toString();
        if(TextUtils.isEmpty(userPassword)){
            userPasswordText.setError("密码不能为空");
        }
        SharedPreferences.Editor editor = getSharedPreferences("data",MODE_PRIVATE).edit();
        editor.putString("userName",userName);
        editor.putString("userPassword",userPassword);
        editor.commit();

        SharedPreferences.Editor defaultEditor = PreferenceManager.getDefaultSharedPreferences(SharePreferenceActivity.this).edit();
        defaultEditor.putString("userName",userName);
        defaultEditor.putString("userPassword",userPassword);
        defaultEditor.commit();

        SharedPreferences.Editor activityEditor = mPrefs.edit();
        activityEditor.putString("userName",userName);
        activityEditor.putString("userPassword",userPassword);
        activityEditor.commit();
    }

    private void readFromSharePreference(){
        SharedPreferences sharedPreferences = getSharedPreferences("data",MODE_PRIVATE);
        String useName = sharedPreferences.getString("userName","");
        String userPassword = sharedPreferences.getString("userPassword","");
        userNameText.setText(useName);
        userPasswordText.setText(userPassword);
    }
}
