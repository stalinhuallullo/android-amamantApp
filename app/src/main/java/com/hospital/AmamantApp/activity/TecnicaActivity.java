package com.hospital.AmamantApp.activity;

import com.hospital.AmamantApp.R;
import com.hospital.AmamantApp.fragment.TecnicaContenidoCincoFragment;
import com.hospital.AmamantApp.fragment.TecnicaContenidoCuatroFragment;
import com.hospital.AmamantApp.fragment.TecnicaContenidoDosFragment;
import com.hospital.AmamantApp.fragment.TecnicaContenidoTresFragment;
import com.hospital.AmamantApp.fragment.TecnicaContenidoUnoFragment;
import com.hospital.AmamantApp.utils.Tools;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

public class TecnicaActivity extends AppCompatActivity {

    TextView txt_titulo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tecnica);

        initToolbar();

        txt_titulo = findViewById(R.id.txt_titulo);

        Bundle b = getIntent().getExtras();
        String id = b.getString("id");
        mostrarContenido(id);
    }

    private void initToolbar(){
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
            //
        }
        return super.onOptionsItemSelected(item);
    }

    private void mostrarContenido(String id){
        switch (id){
            case "1":
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.ll_content, new TecnicaContenidoUnoFragment())
                        .commit();
                break;
            case "2":
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.ll_content, new TecnicaContenidoDosFragment())
                        .commit();
                break;
            case "3":
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.ll_content, new TecnicaContenidoTresFragment())
                        .commit();
                break;
            case "4":
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.ll_content, new TecnicaContenidoCuatroFragment())
                        .commit();
                break;
            case "5":
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.ll_content, new TecnicaContenidoCincoFragment())
                        .commit();
                break;
        }

    }



}
