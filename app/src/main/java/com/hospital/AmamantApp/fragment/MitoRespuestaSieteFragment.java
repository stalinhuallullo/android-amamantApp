package com.hospital.AmamantApp.fragment;


import android.graphics.PorterDuff;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatSeekBar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import com.hospital.AmamantApp.R;
import com.hospital.AmamantApp.utils.MusicUtils;

/**
 * A simple {@link Fragment} subclass.
 */
public class MitoRespuestaSieteFragment extends Fragment implements View.OnClickListener, SeekBar.OnSeekBarChangeListener, MediaPlayer.OnCompletionListener {

    View parent_view;

    TextView tv_song_current_duration;
    TextView tv_song_total_duration;

    ImageButton btn_repeat;
    ImageButton btn_prev;
    //    FloatingActionButton btn_play;
    ImageButton btn_play;
    ImageButton btn_next;
    ImageButton btn_shuffle;
    AppCompatSeekBar seek_song_progressbar;
    AppCompatButton btn_siguiente;

    MediaPlayer mp;
    Handler mHandler = new Handler();
    MusicUtils utils;


    public MitoRespuestaSieteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_mito_respuesta_siete, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        parent_view = view.findViewById(R.id.parent_view);

        tv_song_current_duration = view.findViewById(R.id.tv_song_current_duration);
        tv_song_total_duration = view.findViewById(R.id.tv_song_total_duration);


        btn_repeat = view.findViewById(R.id.btn_repeat);
        btn_prev = view.findViewById(R.id.btn_prev);
        btn_play = view.findViewById(R.id.btn_play);
        btn_next = view.findViewById(R.id.btn_next);
        btn_shuffle = view.findViewById(R.id.btn_shuffle);
        btn_siguiente = view.findViewById(R.id.btn_siguiente);

        seek_song_progressbar = view.findViewById(R.id.seek_song_progressbar);


        btn_repeat.setOnClickListener(this);
        btn_prev.setOnClickListener(this);
        btn_play.setOnClickListener(this);
        btn_next.setOnClickListener(this);
        btn_shuffle.setOnClickListener(this);
        btn_siguiente.setOnClickListener(this);

        mp = new MediaPlayer();
        mp.setOnCompletionListener(this);

        try {
            mp.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mp = MediaPlayer.create(getActivity(), R.raw.mito_7);
            mp.prepare();
        } catch (Exception e) {
            //
        }

        utils = new MusicUtils();


        seek_song_progressbar.setProgress(0);
        seek_song_progressbar.setMax(MusicUtils.MAX_PROGRESS);

        seek_song_progressbar.setOnSeekBarChangeListener(this);

        updateTimerAndSeekbar();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_repeat: {
                toggleButtonColor((ImageButton) v);
                break;
            }
            case R.id.btn_prev: {
                toggleButtonColor((ImageButton) v);
                break;
            }
            case R.id.btn_play: {
                play();
                break;
            }
            case R.id.btn_next: {
                toggleButtonColor((ImageButton) v);
                break;
            }
            case R.id.btn_shuffle: {
                toggleButtonColor((ImageButton) v);
                break;
            }
            case R.id.btn_siguiente: {
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.ll_content, new MitoOchoFragment())
                        .commit();
                break;
            }
        }
    }

    private void play(){
        if (mp.isPlaying()) {
            mp.pause();
            btn_play.setImageResource(R.drawable.ic_play_arrow);
        } else {
            mp.start();
            btn_play.setImageResource(R.drawable.ic_pause);
            mHandler.post(mUpdateTimeTask);
        }
    }

    private boolean toggleButtonColor(ImageButton bt) {
        String selected = (String) bt.getTag(bt.getId());
        if (selected != null) { // selected
            bt.setColorFilter(getResources().getColor(R.color.red_500), PorterDuff.Mode.SRC_ATOP);
            bt.setTag(bt.getId(), null);
            return false;
        } else {
            bt.setTag(bt.getId(), "selected");
            bt.setColorFilter(getResources().getColor(R.color.red_500), PorterDuff.Mode.SRC_ATOP);
            return true;
        }
    }

    /**
     * Background Runnable thread
     */
    private Runnable mUpdateTimeTask = new Runnable() {
        public void run() {
            updateTimerAndSeekbar();
            // Running this thread after 10 milliseconds
            if (mp.isPlaying()) {
                mHandler.postDelayed(this, 100);
            }
        }
    };

    // stop player when destroy
    @Override
    public void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacks(mUpdateTimeTask);
        mp.release();
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        mHandler.removeCallbacks(mUpdateTimeTask);
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        mHandler.removeCallbacks(mUpdateTimeTask);
        int totalDuration = mp.getDuration();
        int currentPosition = utils.progressToTimer(seekBar.getProgress(), totalDuration);

        // forward or backward to certain seconds
        mp.seekTo(currentPosition);

        // update timer progress again
        mHandler.post(mUpdateTimeTask);
    }

    private void updateTimerAndSeekbar() {
        long totalDuration = mp.getDuration();
        long currentDuration = mp.getCurrentPosition();

        // Displaying Total Duration time
        tv_song_total_duration.setText(utils.milliSecondsToTimer(totalDuration));
        // Displaying time completed playing
        tv_song_current_duration.setText(utils.milliSecondsToTimer(currentDuration));

        // Updating progress bar
        int progress = (int) (utils.getProgressSeekBar(currentDuration, totalDuration));
        seek_song_progressbar.setProgress(progress);
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        btn_play.setImageResource(R.drawable.ic_play_arrow);
    }
}