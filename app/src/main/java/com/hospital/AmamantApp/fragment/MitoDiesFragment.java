package com.hospital.AmamantApp.fragment;


import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hospital.AmamantApp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MitoDiesFragment extends Fragment implements View.OnClickListener {

    LinearLayout slc_opction_si;
    LinearLayout slc_opction_no;
    ImageButton btn_repetir;
    FloatingActionButton btn_siguiente;
    ImageButton btn_informacion;

    View mView;
    Dialog dialog ;

    MediaPlayer mp;

    public MitoDiesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_mito_dies, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mView = view;
        slc_opction_si = mView.findViewById(R.id.slc_opction_si);
        slc_opction_no = mView.findViewById(R.id.slc_opction_no);

        mp = new MediaPlayer();

        slc_opction_si.setOnClickListener(this);
        slc_opction_no.setOnClickListener(this);
    }

    private void play(){
        mp.seekTo(0);
        mp.start();
    }
    private void pause(){
        mp.seekTo(0);
        mp.pause();
    }

    private void mostrarDialog(String seleccion){
        dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // before
        dialog.setContentView(R.layout.dialog_respuesta);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setCancelable(false);

        ImageView imagen = dialog.findViewById(R.id.img_descripcion);
        TextView titulo = dialog.findViewById(R.id.txt_titulo);
        if(seleccion == "no"){
            imagen.setImageResource(R.drawable.baby_happy);
            titulo.setText("¡Felicidades!");
            titulo.setTextColor(getResources().getColor(R.color.green_500));
            try {
                mp.setAudioStreamType(AudioManager.STREAM_MUSIC);
                mp = MediaPlayer.create(getActivity(), R.raw.risas);
                mp.prepare();
            } catch (Exception e) {}
        }
        else{
            imagen.setImageResource(R.drawable.baby_sad);
            titulo.setText("Lo sentimos");
            titulo.setTextColor(getResources().getColor(R.color.red_900));
            try {
                mp.setAudioStreamType(AudioManager.STREAM_MUSIC);
                mp = MediaPlayer.create(getActivity(), R.raw.llanto);
                mp.prepare();
            } catch (Exception e) {}
        }

        btn_repetir = dialog.findViewById(R.id.btn_repetir);
        btn_siguiente = dialog.findViewById(R.id.btn_siguiente);
        btn_informacion = dialog.findViewById(R.id.btn_informacion);

        btn_repetir.setOnClickListener(this);
        btn_siguiente.setOnClickListener(this);
        btn_informacion.setOnClickListener(this);

        dialog.show();
        play();
    }

    private void ocultarDialog()
    {
        pause();
        dialog.hide();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.slc_opction_si:
                mostrarDialog("si");
                break;
            case R.id.slc_opction_no:
                mostrarDialog("no");
                break;
            case R.id.btn_repetir:
                ocultarDialog();
                break;
            case R.id.btn_siguiente:
                //
                ocultarDialog();
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.ll_content, new GraciasFragment())
                        .commit();
                break;
            case R.id.btn_informacion:
                ocultarDialog();
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.ll_content, new MitoRespuestaDiesFragment())
                        .commit();
                break;
        }
    }

}