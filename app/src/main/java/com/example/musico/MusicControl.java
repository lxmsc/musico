package com.example.musico;

import android.app.Activity;
import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;

public class MusicControl{
    Music mMusic;
    Activity mActivity;
    MediaPlayer mediaPlayer;
    public MusicControl(Activity activity) {
        this.mActivity = activity;
        initMediaPlayer();
    }

    List<Music> mMusicList;
    public Music getmMusic() {
        return mMusic;
    }
    private void initMediaPlayer() {
        if (mediaPlayer == null)
            mediaPlayer = new MediaPlayer();
        // 设置音量，参数分别表示左右声道声音大小，取值范围为0~1
        mediaPlayer.setVolume(0.5f, 0.5f);
        // 设置是否循环播放
        mediaPlayer.setLooping(false);
    }
    public void setmMusic(Music mMusic) {
        this.mMusic = mMusic;
        TextView barName = mActivity.findViewById(R.id.bar_name);
        TextView barAutor = mActivity.findViewById(R.id.bar_autor);
        barName.setText(this.mMusic.mName);
        barAutor.setText(this.mMusic.mAuthor);
        try {
            if (mediaPlayer == null)
                initMediaPlayer();
            else {
                mediaPlayer.reset();
                mediaPlayer.setDataSource(mMusic.mPath);
                mediaPlayer.prepare();
                mediaPlayer.start();
            }
        } catch (IOException e) {
            Log.e("MusicAdapter", "Player Error" + mMusic.mPath);
        }

    }
}
