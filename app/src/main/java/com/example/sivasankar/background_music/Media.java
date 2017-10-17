package com.example.sivasankar.background_music;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;

import java.io.IOException;

/**
 * Created by Sivasankar on 10/16/2017.
 */

public class Media implements MediaPlayer.OnPreparedListener, MediaPlayer.OnSeekCompleteListener {
    Context context;
    public static Media media;
    String url = "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-1.mp3";

    private Media() {

    }

    MediaPlayer mediaPlayer;
    AssetFileDescriptor descriptor;

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public static Media getInstance() {
        if (media == null) {
            media = new Media();
        }
        return media;
    }

    public void prepare() {
        mediaPlayer = new MediaPlayer();
        try {
            descriptor = context.getAssets().openFd("c.mp3");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            mediaPlayer.setDataSource(descriptor.getFileDescriptor(), descriptor.getStartOffset(), descriptor.getLength());
            mediaPlayer.prepare();
            mediaPlayer.setOnPreparedListener(this);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /*public void prepare() {
        mediaPlayer = new MediaPlayer();

        try {
            mediaPlayer.setDataSource(url);
            mediaPlayer.prepare();
            mediaPlayer.setOnPreparedListener(this);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }*/

    public void Seek(final int pos) {
        mediaPlayer = new MediaPlayer();

        try {
            mediaPlayer.setDataSource(url);

            mediaPlayer.prepare();
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mediaPlayer) {
                    mediaPlayer.seekTo(pos);
                    Play();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public void Play() {
        mediaPlayer.start();
    }

    public int getPos() {
        return mediaPlayer.getCurrentPosition();
    }

    @Override
    public void onPrepared(MediaPlayer mediaPlayer) {
        Play();
    }

    @Override
    public void onSeekComplete(MediaPlayer mediaPlayer) {
        mediaPlayer.start();
    }
}
