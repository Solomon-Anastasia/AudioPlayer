package com.example.laborator1_audioplayer;

import android.media.MediaPlayer;
import android.widget.ImageView;

public class Music {
    private MediaPlayer mediaPlayer;
    private String name;

    public Music(MediaPlayer mediaPlayer, String name) {
        this.mediaPlayer = mediaPlayer;
        this.name = name;
    }

    public MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }

    public String getName() {
        return name;
    }
}
