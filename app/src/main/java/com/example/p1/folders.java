package com.example.p1;

import android.graphics.drawable.Drawable;

public class folders {
    private Drawable thumbNail;
    private String folderName;

    public folders(Drawable thumbNail, String folderName) {
        this.thumbNail = thumbNail;
        this.folderName = folderName;
    }

    public Drawable getThumbNail() {
        return thumbNail;
    }

    public void setThumbNail(Drawable thumbNail) {
        this.thumbNail = thumbNail;
    }

    public String getFolderName() {
        return folderName;
    }

    public void setFolderName(String folderName) {
        this.folderName = folderName;
    }
}
