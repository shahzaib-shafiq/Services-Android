package com.example.service;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.security.Provider;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    public static final String MESSAGE_KEY = "";
    public Button playbutton,forwardbutton,backwardbutton,stopbutton;
    private static final String TAG="";
    private MusicPlayer musicPlayer;
    private boolean mbound=false;
    private ServiceConnection mservicecon = new ServiceConnection() {

        int CurrentIndex=0;
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {

            Log.d(TAG, "onServiceConnected: ");
            MusicPlayer.MyServiceBinder myServiceBinder = (MusicPlayer.MyServiceBinder) iBinder;
            musicPlayer=myServiceBinder.getService();
            mbound=true;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Log.d(TAG, "onServiceDisconnected: ");
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);
        playbutton=findViewById(R.id.btnplay);
        forwardbutton=findViewById(R.id.btnforward);
        backwardbutton=findViewById(R.id.btnbackward);
        stopbutton=findViewById(R.id.btnstop);


        ArrayList<Integer> music = new ArrayList<>();
        music.add(0,R.raw.music1);
        music.add(1,R.raw.music2);
        music.add(2,R.raw.music3);
        music.add(3,R.raw.music4);


    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = new Intent(this,MusicPlayer.class);
        bindService(intent,mservicecon, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(mbound)
        {
            unbindService(mservicecon);
        }
    }

    public void PlayMusic(View view) {
        if (mbound){
            if(musicPlayer.isPlaying()){
                musicPlayer.Pause();
            playbutton.setText("Playing");
            }
            else {
                Intent intent = new Intent(MainActivity.this,MusicPlayer.class);
                startService(intent);
                musicPlayer.Play();
                playbutton.setText("Pause");

            }
        }
    }

    public void StopMusic(View view) {
        musicPlayer.stopSelf();
    }

    public void ForwardMusic(View view) {

    }

    public void BackwardMusic(View view) {
    }
}