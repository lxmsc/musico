package com.example.musico;

import java.io.Serializable;

public class Music implements Serializable {
    Music() {
    }

    public String getmName() {
        return mName;
    }

    public Music(String mName, String mAuthor, String mPath) {
        this.mName = mName;
        this.mAuthor = mAuthor;
        this.mPath = mPath;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmAuthor() {
        return mAuthor;
    }

    public void setmAuthor(String mAuthor) {
        this.mAuthor = mAuthor;
    }

    public String getmPath() {
        return mPath;
    }

    public void setmPath(String mPath) {
        this.mPath = mPath;
    }

    String mName;
    String mAuthor;
    String mPath;
}
