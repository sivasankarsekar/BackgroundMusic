package com.example.sivasankar.background_music;

import android.app.IntentService;
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

public class IntentServises extends IntentService {

    MediaPlayer mediaPlayer;
    AssetFileDescriptor descriptor;

    public IntentServises() {
        super(IntentServises.class.getName());
    }

    @Override
    public void onCreate() {
        super.onCreate();
        System.out.println("mmm onCreate");
        mediaPlayer=new MediaPlayer();
        try {
            descriptor = getApplicationContext().getAssets().openFd("c.mp3");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            mediaPlayer.setDataSource(descriptor.getFileDescriptor(),descriptor.getStartOffset(),descriptor.getLength());
            mediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        System.out.println("mmm onStartCommand");
        Play();

    }

    private void Play() {
        mediaPlayer.start();
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        super.onTaskRemoved(rootIntent);
        System.out.println("mmm onTaskRemoved");
    }
}
