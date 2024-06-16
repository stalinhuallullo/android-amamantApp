package com.hospital.AmamantApp.activity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;
import android.widget.VideoView;

import com.hospital.AmamantApp.R;

public class InicioActivity extends AppCompatActivity implements View.OnClickListener {

    VideoView surface_view_1;
    VideoView surface_view_2;
    VideoView surface_view_3;

    CardView btn_ternicas;
    CardView btn_mitos;
    CardView btn_preguntas_frecuentes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //setTheme(R.style.SplashTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        surface_view_1 = findViewById(R.id.surface_view_1);
        surface_view_2 = findViewById(R.id.surface_view_2);
        surface_view_3 = findViewById(R.id.surface_view_3);

        btn_ternicas = findViewById(R.id.btn_ternicas);
        btn_mitos = findViewById(R.id.btn_mitos);
        btn_preguntas_frecuentes = findViewById(R.id.btn_preguntas_frecuentes);

        btn_ternicas.setOnClickListener(this);
        btn_mitos.setOnClickListener(this);
        btn_preguntas_frecuentes.setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        reproducirVideos();
        super.onStart();
    }

    private void reproducirVideos(){

        // VIDEO 1
        Uri uri_1 = Uri.parse("android.resource://com.hospital.AmamantApp/" + R.raw.inicio_tecnica);
        //surface_view.setMediaController(new MediaController(this));
        surface_view_1.setVideoURI(uri_1);
        surface_view_1.start();
        surface_view_1.requestFocus();

        surface_view_1.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setVolume(0, 0);
                mp.setLooping(true);
            }
        });


        // VIDEO 2
        Uri uri_2 = Uri.parse("android.resource://com.hospital.AmamantApp/" + R.raw.inicio_mito);
        surface_view_2.setVideoURI(uri_2);
        surface_view_2.start();
        surface_view_2.requestFocus();

        surface_view_2.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setVolume(0, 0);
                mp.setLooping(true);
            }
        });


        // VIDEO 3
        Uri uri_3 = Uri.parse("android.resource://com.hospital.AmamantApp/" + R.raw.inicio_preguntas_frecuentes);
        surface_view_3.setVideoURI(uri_3);
        surface_view_3.start();
        surface_view_3.requestFocus();

        surface_view_3.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setVolume(0, 0);
                mp.setLooping(true);
            }
        });


        Display display_1 = getWindowManager().getDefaultDisplay();
        int width_1 = display_1.getWidth();
        int height_1 = display_1.getHeight();

        surface_view_1.setLayoutParams(new FrameLayout.LayoutParams(width_1, height_1));
        surface_view_2.setLayoutParams(new FrameLayout.LayoutParams(width_1, height_1));
        surface_view_3.setLayoutParams(new FrameLayout.LayoutParams(width_1, height_1));


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search_setting, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        } else {
            Toast.makeText(getApplicationContext(), item.getTitle(), Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_ternicas:
                startActivity(new Intent(this, ListaDeTecnicasActivity.class));
                break;
            case R.id.btn_mitos:
                startActivity(new Intent(this, MitosActivity.class));
                break;
            case R.id.btn_preguntas_frecuentes:
                startActivity(new Intent(this, PreguntasFrecuentesActivity.class));
                break;
        }
    }
}
