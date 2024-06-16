package com.hospital.AmamantApp.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hospital.AmamantApp.R;
import com.hospital.AmamantApp.activity.InicioActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class GraciasFragment extends Fragment implements View.OnClickListener {

    View mView;
    AppCompatButton btn_finalizar;

    public GraciasFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_gracias, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mView = view;
        btn_finalizar = mView.findViewById(R.id.btn_finalizar);
        btn_finalizar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_finalizar:
                startActivity(new Intent(getActivity(), InicioActivity.class));
                break;
        }
    }
}
