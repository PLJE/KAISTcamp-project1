package com.example.p1;

import android.graphics.drawable.Drawable;

public class cats {  //gallayAdapter를 위한 cats class
    private Drawable cats;

    public cats(Drawable cats) {
        this.cats = cats;
    }

    public void setCats(Drawable cats) {
        this.cats = cats;
    }

    public Drawable getCats() {
        return cats;
    }

}
