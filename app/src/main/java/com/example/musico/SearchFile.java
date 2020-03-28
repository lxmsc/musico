package com.example.musico;

import android.database.sqlite.SQLiteDatabase;
import android.media.MediaMetadataRetriever;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class SearchFile {
    DatabaseHelper databaseHelper;
    Handler mHandler;
    MediaMetadataRetriever mmr = new MediaMetadataRetriever();
    MainActivity mainActivity;
    SearchFile(DatabaseHelper databaseHelper, Handler mHandler,MainActivity mainActivity){
        this.databaseHelper = databaseHelper;
        this.mHandler = mHandler;
        this.mainActivity = mainActivity;
    }

    void findMusicByFile(File file){
        if(!file.canRead()){
            return;
        }

        File[] files = file.listFiles();
        for(File f:files){
            if(f.isDirectory()){
                findMusicByFile(f);
            }
            else{
                String fileSuffix = f.getName().substring(f.getName().lastIndexOf(".") + 1);
                if(fileSuffix.equals("mp3")){
                    // TODO
                    Log.d("tex",""+f.getPath());
                    try {
                        mmr.setDataSource(f.getPath());
                        Message message = new Message();
                        String title = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_TITLE);
                        String album = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ALBUM);
                        String artist = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST);
                        String duration = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION);// 播放时长单位为毫秒
                        // byte[] pic = mmr.getEmbeddedPicture();  // 图片，可以通过BitmapFactory.decodeByteArray转换为bitmap图片
                        if(databaseHelper.searchPath(databaseHelper.getReadableDatabase(),f.getPath())) {
                            Music music = new Music(f.getName(), mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST), f.getPath());
                            message.obj = music;
                            databaseHelper.addMusic(databaseHelper.getWritableDatabase(), music);
                            mHandler.sendMessage(message);
                        }
                    }catch (Exception e){
                        //Toast.makeText(mainActivity,"error",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }
    }
    void findMusic(){
        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            File path = Environment.getExternalStorageDirectory();
            findMusicByFile(path);
        }
    }
}
