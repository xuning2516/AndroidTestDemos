/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit. 
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan. 
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna. 
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus. 
 * Vestibulum commodo. Ut rhoncus gravida arcu. 
 */

package com.royole.servicebestpractice;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Binder;
import android.os.Environment;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import java.io.File;

public class DownloadService extends Service {
    private static final String TAG = "DownloadService";

    private Context mContext;
    private DownloadAsyncTask downloadTask;
    private String downloadUrl;
    
    public DownloadService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        //throw new UnsupportedOperationException("Not yet implemented");
//        return downloadBinder.asBinder();
        return downloadBinder;
    }

//    private IDownloadInterface downloadBinder = new IDownloadInterface.Stub(){
//        @Override
//        public void startDownload(Uri uri) throws RemoteException {
//
//        }
//
//        @Override
//        public void pauseDownload() throws RemoteException {
//
//        }
//
//        @Override
//        public void cancelDownload() throws RemoteException {
//
//        }
//    };

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: ");
        mContext = this;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand: startId "+ startId);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy: ");
        super.onDestroy();
    }

    private DownloadListener downloadListenr = new DownloadListener() {
        @Override
        public void onProgress(int progress) {
            getNotificationManager().notify(1,getNotification("Downloading",progress));
        }

        @Override
        public void onSuccess() {
            downloadTask = null;
            stopForeground(true);
            getNotificationManager().notify(1,getNotification("Download Success",100));
            Toast.makeText(mContext,"Download Sucess",Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onFailed() {
            downloadTask = null;
            stopForeground(true);
            getNotificationManager().notify(1,getNotification("Download Failed",-1));
            Toast.makeText(mContext,"Download Sucess",Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onPaused() {
            downloadTask = null;
            stopForeground(true);
            Toast.makeText(mContext,"Download Sucess",Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCanceled() {
            downloadTask = null;
            stopForeground(true);
            Toast.makeText(mContext,"Download Sucess",Toast.LENGTH_SHORT).show();
        }
    };

    private DownloadBinder downloadBinder = new DownloadBinder();

    class DownloadBinder extends Binder {
        public void startDownload(String url){
            if(downloadTask == null){
                downloadTask = new DownloadAsyncTask(downloadListenr);
                downloadTask.execute(url);
                startForeground(1,getNotification("Downloading...",0));
                Toast.makeText(mContext,"Downloading ...",Toast.LENGTH_SHORT).show();
            }
        }

        public void pauseDownload(){
            if(downloadTask != null){
                downloadTask.pauseDownload();
            }
        }

        public void cancelDownload(){
            if(downloadTask != null){
                downloadTask.cancelDownload();
            }else {
                if(downloadUrl != null){
                    // 取消下载需将文件删除，并将通知关闭
                    String fileName = downloadUrl.substring(downloadUrl.lastIndexOf('/'));
                    String directory = Environment.getExternalStoragePublicDirectory(
                            Environment.DIRECTORY_DOWNLOADS).getPath();
                    File file = new File(directory+fileName);
                    if(file.exists()){
                        file.delete();
                    }
                    getNotificationManager().cancel(1);
                    stopForeground(true);
                    Toast.makeText(mContext,"Canceled",Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    private NotificationManager getNotificationManager(){
        return (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
    }

    private Notification getNotification(String msg, int progress){
        Notification notification = new NotificationCompat.Builder(mContext)
                .setContentTitle("下载进度")
                .setContentText(msg+ " "+ progress)
                .setSmallIcon(R.mipmap.ic_launcher)
                .build();
        return notification;
    }
}
