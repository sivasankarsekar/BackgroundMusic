package com.example.sivasankar.background_music;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView imgPlay, imgStop, imgNext, imgPrev;
    private TextView txtOne, txtTwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtOne = (TextView) findViewById(R.id.txtOne);
        txtTwo = (TextView) findViewById(R.id.txtTwo);
        imgPlay = (ImageView) findViewById(R.id.imgPlay);
        imgStop = (ImageView) findViewById(R.id.imgStop);
        imgNext = (ImageView) findViewById(R.id.imgNext);
        imgPrev = (ImageView) findViewById(R.id.imgPrev);

        txtOne.setOnClickListener(this);
        txtTwo.setOnClickListener(this);
        imgPlay.setOnClickListener(this);
        imgStop.setOnClickListener(this);
        imgNext.setOnClickListener(this);
        imgPrev.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(MainActivity.this, BackgroundService.class);
        intent.putExtra("start", "start");
        //Intent intentService = new Intent(MainActivity.this, IntentServises.class);

        switch (view.getId()) {
            case R.id.imgPlay:
                intent.putExtra("value", "play");
                startService(intent);
                imgPlay.setVisibility(View.GONE);
                imgStop.setVisibility(View.VISIBLE);
                break;
            case R.id.imgStop:
                intent.putExtra("value", "stop");
                startService(intent);
                imgPlay.setVisibility(View.VISIBLE);
                imgStop.setVisibility(View.GONE);
                break;
            case R.id.imgNext:
            case R.id.imgPrev:
                intent.putExtra("value", "next");
                startService(intent);
                imgPlay.setVisibility(View.GONE);
                imgStop.setVisibility(View.VISIBLE);
                break;
            case R.id.txtOne:
                intent.putExtra("value", "one");
                startService(intent);
                imgPlay.setVisibility(View.GONE);
                imgStop.setVisibility(View.VISIBLE);
                break;
            case R.id.txtTwo:
                intent.putExtra("value", "two");
                startService(intent);
                imgPlay.setVisibility(View.GONE);
                imgStop.setVisibility(View.VISIBLE);
                break;
            default:
                break;
        }
    }

    private void setImage() {
        if (BackgroundService.mediaPlayer.isPlaying()) {

        }

    }



}
