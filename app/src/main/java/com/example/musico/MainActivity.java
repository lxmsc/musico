package com.example.musico;

import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.MessageQueue;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<Music> musicList = new ArrayList<>();
    MusicAdapter musicAdapter = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = findViewById(R.id.list_view);

        DatabaseHelper databaseHelper = new DatabaseHelper(this,"music.db",null,1);
        musicList = databaseHelper.queryMusic(databaseHelper.getReadableDatabase());
        musicAdapter = new MusicAdapter(musicList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(musicAdapter);
        MusicControl mMusicControl = new MusicControl(this);
        musicAdapter.setmMusicControl(mMusicControl);

        final MyHandler myHandler = new MyHandler();
        final SearchFile searchFile = new SearchFile(databaseHelper, myHandler,this);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                searchFile.findMusic();
            }
        });
        thread.start();


    }

    class MyHandler extends Handler{
        @Override
        public void handleMessage(Message msg) { //zhuxiancheng
            super.handleMessage(msg);
            if (msg.obj == null) {
                return;
            }
            musicList.add((Music) msg.obj);
            musicAdapter.notifyDataSetChanged();
        }
    }
}
