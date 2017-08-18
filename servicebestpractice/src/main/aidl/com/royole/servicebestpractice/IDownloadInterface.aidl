// IDownloadInterface.aidl
package com.royole.servicebestpractice;

// Declare any non-default types here with import statements

interface IDownloadInterface {
    void startDownload(in Uri uri);
    void pauseDownload();
    void cancelDownload();
}
