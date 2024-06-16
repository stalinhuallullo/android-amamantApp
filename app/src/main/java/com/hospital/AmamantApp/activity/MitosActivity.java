package com.hospital.AmamantApp.activity;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.hospital.AmamantApp.R;
import com.hospital.AmamantApp.fragment.GraciasFragment;
import com.hospital.AmamantApp.fragment.MitoUnoFragment;
import com.hospital.AmamantApp.utils.Tools;

public class MitosActivity extends AppCompatActivity {

    MediaPlayer mp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mitos);

        mp = new MediaPlayer();

        initToolbar();
        mostraroContenido();
        reproducirTitulo();
    }

    private void reproducirTitulo(){
        try {
            mp.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mp = MediaPlayer.create(this, R.raw.titulo_mito);
            mp.seekTo(0);
            mp.start();
        } catch (Exception e) {}
    }


    private void mostraroContenido() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.ll_content, new MitoUnoFragment())
//                .replace(R.id.ll_content, new GraciasFragment())
                .commit();
    }

    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Mitos de la lactancia materna");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //Tools.setSystemBarColor(this, R.color.grey_5);
        Tools.setSystemBarLight(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Tools.changeMenuIconColor(menu, getResources().getColor(R.color.grey_60));
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            //finish();
            super.onBackPressed();
        } else {
            //Toast.makeText(getApplicationContext(), item.getTitle(), Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

}
