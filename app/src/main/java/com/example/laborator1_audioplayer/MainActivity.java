package com.example.laborator1_audioplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private int contor = 0;
    private int index = 0;
    private TextView name;
    private ImageView imageView;
    private SeekBar seekBar;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<Music> music = new ArrayList<>();
        music.add(new Music((MediaPlayer.create(getApplicationContext(), R.raw.never_gonna_give_u_up)), "Never gonna give you up"));
        music.add(new Music((MediaPlayer.create(getApplicationContext(), R.raw.oggy_ending)), "Oggy and cockroaches"));
        music.add(new Music((MediaPlayer.create(getApplicationContext(), R.raw.epicsaxguy)), "Epic sax guy"));
        music.add(new Music((MediaPlayer.create(getApplicationContext(), R.raw.dj_smash_volna)), "Smash volna"));
        music.add(new Music((MediaPlayer.create(getApplicationContext(), R.raw.another_one_queen)), "Another one bites the dust"));

        Button playPause = findViewById(R.id.playPauseButton);
        Button next = findViewById(R.id.nextButton);
        Button previous = findViewById(R.id.previousButton);
        Button replay = findViewById(R.id.resetButton);
        name = findViewById(R.id.nameTextView);
        imageView = findViewById(R.id.coverImageView);
        seekBar = findViewById(R.id.seekBar);

        start(playPause, music.get(index).getMediaPlayer());
        View.OnClickListener elem = v -> {
            switch (v.getId()) {
                case R.id.playPauseButton: {
                    change(playPause, music.get(index).getMediaPlayer());
                    break;
                }
                case R.id.nextButton: {
                    resetProgress(music.get(index).getMediaPlayer());

                    if (index < music.size() - 1) {
                        index++;
                        setNameCover(music.get(index).getName());
                    }
                    start(playPause, music.get(index).getMediaPlayer());
                    break;
                }
                case R.id.previousButton: {
                    resetProgress(music.get(index).getMediaPlayer());

                    if (index > 0) {
                        index--;
                        setNameCover(music.get(index).getName());
                    }
                    start(playPause, music.get(index).getMediaPlayer());
                    break;
                }
                case R.id.resetButton: {
                    stop(playPause, music.get(index).getMediaPlayer());
                    resetProgress(music.get(index).getMediaPlayer());
                }
            }
        };

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if (b) {
                    music.get(index).getMediaPlayer().seekTo(i);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                seekBar.setProgress(music.get(index).getMediaPlayer().getCurrentPosition());
                handler.postDelayed(this, 100);
            }
        };
        handler.postDelayed(runnable, 100);

        playPause.setOnClickListener(elem);
        next.setOnClickListener(elem);
        previous.setOnClickListener(elem);
        replay.setOnClickListener(elem);
    }

    public void change(Button b, MediaPlayer m) {
        if (contor == 0) {
            m.start();
            b.setText("||");
            b.setTextSize(22);
            /** **/
            seekBar.setMax(m.getDuration());
            /** **/
            contor = 1;
        } else if (contor == 1) {
            m.pause();
            b.setText("▷");
            b.setTextSize(22);
            contor = 0;
        }
    }

    public void start(Button b, MediaPlayer m) {
        m.start();
        b.setText("||");
        b.setTextSize(22);
        /** **/
        seekBar.setMax(m.getDuration());
        /** **/
        contor = 1;
    }

    public void stop (Button b, MediaPlayer m) {
        m.pause();
        b.setText("▷");
        b.setTextSize(22);
        contor = 0;
    }

    public void resetProgress(MediaPlayer m) {
        m.seekTo(0);
        m.pause();
    }

    public void setNameCover(String nameList) {
        name.setText(nameList);

        switch (index) {
            case 0: {
                imageView.setImageResource(R.drawable.i1);
                break;
            }
            case 1: {
                imageView.setImageResource(R.drawable.i2);
                break;
            }
            case 2: {
                imageView.setImageResource(R.drawable.i3);
                break;
            }
            case 3: {
                imageView.setImageResource(R.drawable.i4);
                break;
            }
            default: imageView.setImageResource(R.drawable.i5);
        }
    }
}