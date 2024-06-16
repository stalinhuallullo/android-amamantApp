package com.hospital.AmamantApp.activity;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.hospital.AmamantApp.R;
import com.hospital.AmamantApp.adapter.AdapterListaTecnicas;
import com.hospital.AmamantApp.data.DataGenerados;
import com.hospital.AmamantApp.model.Tecnica;
import com.hospital.AmamantApp.utils.Tools;

import java.util.List;

public class ListaDeTecnicasActivity extends AppCompatActivity{


    private View parent_view;

    private RecyclerView recyclerView;
    private AdapterListaTecnicas mAdapter;

    MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_de_tecnicas);

        parent_view = findViewById(android.R.id.content);

        mp = new MediaPlayer();

        initToolbar();
        initComponent();
        reproducirTitulo();
    }

    private void reproducirTitulo(){
        try {
            mp.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mp = MediaPlayer.create(this, R.raw.titulo_tecnica);
            mp.seekTo(0);
            mp.start();
        } catch (Exception e) {}
    }


    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Tecnicas de lactancia materna");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Tools.setSystemBarLight(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
//            finish();
            super.onBackPressed();
        } else {
            //Toast.makeText(getApplicationContext(), item.getTitle(), Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    private void initComponent() {
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        List<Tecnica> items = DataGenerados.getListaTecnicasData(this, 10);

        mAdapter = new AdapterListaTecnicas(this, items, R.layout.item_tecnica);
        recyclerView.setAdapter(mAdapter);


        mAdapter.setOnItemClickListener(new AdapterListaTecnicas.OnItemClickListener() {
            @Override
            public void onItemClick(View view, Tecnica obj, int position) {
                mostrarMito(obj.id);
            }
        });

    }

    private void mostrarMito(String id){
        Bundle bundle = new Bundle();
        bundle.putString("id", id);

        Intent intent = new Intent(this, TecnicaActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }



}





















