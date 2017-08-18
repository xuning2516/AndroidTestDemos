/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.royole.servicebestpractice;

import android.Manifest;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.IBinder;
import android.os.PersistableBundle;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private IDownloadInterface downloadService;
    private DownloadService.DownloadBinder downloadBinder;
    private Context mContext;

    ServiceConnection downloadConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d(TAG, "onServiceConnected: name "+ name+ " " + service);
//            downloadService = IDownloadInterface.Stub.asInterface(service);
            downloadBinder = (DownloadService.DownloadBinder)service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            downloadService = null;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
    }

    public  void onClick(View view){
        switch (view.getId()){
            case R.id.start_download:
                startDownload();
                break;
            case R.id.pause_download:
                pauseDownload();
                break;
            case R.id.cancel_download:
                cancelDownload();
                break;
            default:
                break;
        }
    }

    private void startDownload(){
//        if(downloadService != null){
//            Uri downUri = Uri.parse("http://www.baidu.com");
//            try {
//                downloadService.startDownload(downUri);
//            }catch (RemoteException ex){
//                ex.printStackTrace();
//            }
//        }
        String url = "https://raw.githubusercontent.com/guolindev/eclipse/master/eclipse-inst-win64.exe";
        if(downloadBinder != null) {
            downloadBinder.startDownload(url);
        }
    }

    private void pauseDownload(){
        if(downloadBinder != null){
            downloadBinder.pauseDownload();
        }

    }

    private void cancelDownload(){
        if (downloadBinder != null){
            downloadBinder.cancelDownload();
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        if(ContextCompat.checkSelfPermission(mContext, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 1:
                if(grantResults.length > 0 && grantResults[0] != PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(mContext,"拒绝权限将无法使用程序",Toast.LENGTH_SHORT).show();
                    finish();
                }else {

                }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent download = new Intent(mContext,DownloadService.class);
        bindService(download,downloadConnection,BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unbindService(downloadConnection);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
