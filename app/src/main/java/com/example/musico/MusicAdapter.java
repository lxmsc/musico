package com.example.musico;


import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;

public class MusicAdapter extends RecyclerView.Adapter<MusicAdapter.ViewHolder> {
    List<Music> musicList;
    MusicControl mMusicControl;

    public MusicControl getmMusicControl() {
        return mMusicControl;
    }
    public MusicAdapter(List<Music> musicList) {
        this.musicList = musicList;
    }

    public void setmMusicControl(MusicControl mMusicControl) {
        this.mMusicControl = mMusicControl;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.music_list,parent,
                false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Music music = musicList.get(position);
        holder.mMusicAutor.setText(music.mAuthor);
        holder.mMusicName.setText(music.mName);
        holder.itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                mMusicControl.setmMusic(music);
            }
        });
    }

    @Override
    public int getItemCount() {
        return musicList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView mMusicName,mMusicAutor;

        public ViewHolder(View itemView) {
            super(itemView);
            mMusicName = itemView.findViewById(R.id.music_name);
            mMusicAutor = itemView.findViewById(R.id.music_autor);
        }
    }
}
