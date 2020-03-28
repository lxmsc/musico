package com.example.musico;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static Integer Version = 1;
    Context mContext;
    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table music(name varchar(64),path varchar(64),autor varchar(64))";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public void addMusic(SQLiteDatabase db,Music music){
        ContentValues values = new ContentValues();
        values.put("name",music.mName);
        values.put("path",music.mPath);
        values.put("autor",music.mAuthor);
        db.insert("music",null,values);
    }

    public List<Music> queryMusic(SQLiteDatabase db){
        List<Music> musicList = null;
        Cursor cursor = db.rawQuery("select * from music", null);
        while(cursor.moveToNext()){
            musicList.add(new Music(cursor.getString(0),cursor.getString(1),cursor.getString(2)));
        }
        cursor.close();
        return musicList;
    }

    public Boolean searchPath(SQLiteDatabase db, String path){
        Cursor cursor = db.query("music",new String[]{"*"},"path = ?",new String[]{path},null,null,null,null);
        if(cursor.moveToNext()){
            return false;
        }
        else {
            return true;
        }

    }
}
