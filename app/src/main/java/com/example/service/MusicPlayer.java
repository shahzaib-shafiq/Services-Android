package com.example.service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

public class MusicPlayer  extends Service {

    private static final String TAG="";
    private MediaPlayer mediaPlayer;
private final Binder mbinder = new MyServiceBinder();



    @Override
    public void onCreate() {
        Log.d(TAG, "onCreate: ");
        super.onCreate();
        mediaPlayer=MediaPlayer.create(this,R.raw.music1);
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            stopSelf();
        }
    });
    }


    public class MyServiceBinder extends Binder{
        public MusicPlayer getService(){
            return  MusicPlayer.this;
        }
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand: ");
        return START_NOT_STICKY;

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind: ");
        return mbinder;

        
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG, "onUnbind: ");
        return true;
    }

    @Override
    public void onRebind(Intent intent) {
        Log.d(TAG, "onRebind: ");
        super.onRebind(intent);

    }


    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy: ");
        super.onDestroy();
        mediaPlayer.release();
    }

    public boolean isPlaying()
    {
        return mediaPlayer.isPlaying();
    }
    public void Play()
    {
        mediaPlayer.start();
    }
    public void Pause()
    {
        mediaPlayer.pause();
    }

}
