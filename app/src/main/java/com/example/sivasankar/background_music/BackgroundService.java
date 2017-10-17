package com.example.sivasankar.background_music;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.IBinder;

import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Sivasankar on 10/16/2017.
 */

public class BackgroundService extends Service implements MediaPlayer.OnCompletionListener, MediaPlayer.OnPreparedListener, MediaPlayer.OnErrorListener {
    public static MediaPlayer mediaPlayer;
    AssetFileDescriptor descriptor;
    NotificationManager notificationManager;
    public static int NOTIFICATION_ID = 100, POS = 0;
    ArrayList<String> SongList = new ArrayList<>();
    private Intent prevIntent, nextIntent, pauseIntent, playIntent;
    private PendingIntent pendingPrevIntent, pendingNextIntent, pendingPauseIntent, pendingPlayIntent;
    private RemoteViews stopviews, views;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        System.out.println("mmm onCreate");
        prevIntent = new Intent(this, BackgroundService.class);
        prevIntent.setAction("prev");
        pendingPrevIntent = PendingIntent.getService(this, 100, prevIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        nextIntent = new Intent(this, BackgroundService.class);
        nextIntent.setAction("next");
        pendingNextIntent = PendingIntent.getService(this, 100, nextIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        pauseIntent = new Intent(this, BackgroundService.class);
        pauseIntent.setAction("stop");
        pendingPauseIntent = PendingIntent.getService(this, 100, pauseIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        playIntent = new Intent(this, BackgroundService.class);
        pauseIntent.setAction("play");
        pendingPlayIntent = PendingIntent.getService(this, 100, pauseIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        stopviews = new RemoteViews(this.getPackageName(), R.layout.notification_stop);
        stopviews.setOnClickPendingIntent(R.id.imgPrev, pendingPrevIntent);
        stopviews.setOnClickPendingIntent(R.id.imgNext, pendingNextIntent);
        stopviews.setOnClickPendingIntent(R.id.imgPlay, pendingPlayIntent);

        views = new RemoteViews(this.getPackageName(), R.layout.notification);
        views.setOnClickPendingIntent(R.id.imgPrev, pendingPrevIntent);
        views.setOnClickPendingIntent(R.id.imgNext, pendingNextIntent);
        views.setOnClickPendingIntent(R.id.imgStop, pendingPauseIntent);

        SongList.add("a.mp3");
        SongList.add("c.mp3");
        mediaPlayer = new MediaPlayer();
        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);


    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        System.out.println("mmm onStartCommand");
        if (intent != null) {
            String value = intent.getStringExtra("value");
            String value1 = intent.getAction();

            if (value != null) {
                views.setTextViewText(R.id.txtSong, SongList.get(POS));
                stopviews.setTextViewText(R.id.txtSong, SongList.get(POS));
                if (value.equals("play")) {
                    Notification notification =
                            new Notification.Builder(this)
                                    .setContentTitle("Two")
                                    .setContentText("One")
                                    .setSmallIcon(R.drawable.ic_menu)
                                    .setTicker("setTicker")
                                    .setContent(views)
                                    .setPriority(Notification.PRIORITY_MAX)
                                    .setAutoCancel(false)
                                    .build();
                    notificationManager.notify(100, notification);
                    Play();

                } else if (value.equals("stop")) {

                    Notification notification =
                            new Notification.Builder(this)
                                    .setContentTitle("Two")
                                    .setContentText("One")
                                    .setSmallIcon(R.drawable.ic_menu)
                                    .setTicker("setTicker")
                                    .setContent(stopviews)
                                    .setPriority(Notification.PRIORITY_MAX)
                                    .setAutoCancel(false)
                                    .build();
                    notificationManager.notify(100, notification);
                    stop();
                } else if (value.equals("next") || value.equals("prev")) {
                    Notification notification =
                            new Notification.Builder(this)
                                    .setContentTitle("Two")
                                    .setContentText("One")
                                    .setSmallIcon(R.drawable.ic_menu)
                                    .setTicker("setTicker")
                                    .setContent(views)
                                    .setPriority(Notification.PRIORITY_MAX)
                                    .setAutoCancel(false)
                                    .build();
                    notificationManager.notify(100, notification);
                    PlayNext();
                    Play();
                } else if (value.equals("one")) {
                    Notification notification =
                            new Notification.Builder(this)
                                    .setContentTitle("Two")
                                    .setContentText("One")
                                    .setSmallIcon(R.drawable.ic_menu)
                                    .setTicker("setTicker")
                                    .setContent(views)
                                    .setPriority(Notification.PRIORITY_MAX)
                                    .setAutoCancel(false)
                                    .build();
                    notificationManager.notify(100, notification);
                    POS = 0;
                    Play();

                } else if (value.equals("two")) {
                    Notification notification =
                            new Notification.Builder(this)
                                    .setContentTitle("Two")
                                    .setContentText("One")
                                    .setSmallIcon(R.drawable.ic_menu)
                                    .setTicker("setTicker")
                                    .setContent(views)
                                    .setPriority(Notification.PRIORITY_MAX)
                                    .setAutoCancel(false)
                                    .build();
                    notificationManager.notify(100, notification);
                    POS = 1;
                    Play();
                }
            }

            if (value1 != null) {
                views.setTextViewText(R.id.txtSong, SongList.get(POS));
                stopviews.setTextViewText(R.id.txtSong, SongList.get(POS));

                if (value1.equals("stop")) {
                    Notification notification =
                            new Notification.Builder(this)
                                    .setContentTitle("Two")
                                    .setContentText("One")
                                    .setSmallIcon(R.drawable.ic_menu)
                                    .setTicker("setTicker")
                                    .setContent(stopviews)
                                    .setPriority(Notification.PRIORITY_MAX)
                                    .setAutoCancel(false)
                                    .build();
                    notificationManager.notify(100, notification);
                    mediaPlayer.pause();
                    stop();
                } else if (value1.equals("next") || value1.equals("prev")) {

                    Notification notification =
                            new Notification.Builder(this)
                                    .setContentTitle("Two")
                                    .setContentText("One")
                                    .setSmallIcon(R.drawable.ic_menu)
                                    .setTicker("setTicker")
                                    .setContent(views)
                                    .setPriority(Notification.PRIORITY_MAX)
                                    .setAutoCancel(false)
                                    .build();
                    notificationManager.notify(100, notification);
                    PlayNext();
                    Play();
                } else if (value1.equals("play")) {
                    mediaPlayer.start();
                    Notification notification =
                            new Notification.Builder(this)
                                    .setContentTitle("Two")
                                    .setContentText("One")
                                    .setSmallIcon(R.drawable.ic_menu)
                                    .setTicker("setTicker")
                                    .setContent(views)
                                    .setPriority(Notification.PRIORITY_MAX)
                                    .setAutoCancel(false)
                                    .build();
                    notificationManager.notify(100, notification);
                    Play();
                }
            }

        }
        return START_STICKY;
    }

    private void PlayNext() {
        POS = POS == 0 ? 1 : 0;
        System.out.println("mmm POS=" + POS);
    }


    private void Play() {
        mediaPlayer.reset();
        try {
            descriptor = getApplicationContext().getAssets().openFd(SongList.get(POS));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            mediaPlayer.setDataSource(descriptor.getFileDescriptor(), descriptor.getStartOffset(), descriptor.getLength());
            mediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mediaPlayer.start();
    }

    private void stop() {
        mediaPlayer.stop();
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        super.onTaskRemoved(rootIntent);
        System.out.println("mmm onTaskRemoved");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mediaPlayer.stop();
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
