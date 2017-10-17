package com.example.sivasankar.background_music;

import android.app.Service;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;

import java.io.IOException;

/**
 * Created by Sivasankar on 10/16/2017.
 */

public class BackgroundOne extends Service implements MediaPlayer.OnCompletionListener, MediaPlayer.OnPreparedListener, MediaPlayer.OnErrorListener {
    MediaPlayer mediaPlayer;
    AssetFileDescriptor descriptor;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        System.out.println("mmm onCreate");

    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        System.out.println("mmm onStartCommand");
        if(intent!=null){
            String start = intent.getStringExtra("start");

            if (start.equals("start")) {
                System.out.println("mmm onStartCommand start");

                Media.getInstance().prepare();
                //Media.getInstance().Play();
            }
        }
        else {
            Media.getInstance().Seek(7050);
        }


        return START_STICKY;
    }


    @Override
    public void onTaskRemoved(Intent rootIntent) {
        super.onTaskRemoved(rootIntent);
        System.out.println("mmm onTaskRemoved Pos="+Media.getInstance().getPos());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //  mediaPlayer.stop();
        System.out.println("mmm onDestroy");
    }

    @Override
    public void onCompletion(MediaPlayer mediaPlayer) {
        System.out.println("mmm onCompletion");
    }

    @Override
    public void onPrepared(MediaPlayer mediaPlayer) {
        System.out.println("mmm onPrepared");
    }

    @Override
    public boolean onError(MediaPlayer mediaPlayer, int i, int i1) {
        System.out.println("mmm onError");
        return false;
    }
}
