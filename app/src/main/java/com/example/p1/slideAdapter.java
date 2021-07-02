package com.example.p1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import org.jetbrains.annotations.NotNull;

public class slideAdapter extends PagerAdapter {
    Context context;
    LayoutInflater inflater;

    //list of images
    public int[] lst_images = new int[(new gloval()).catImgN];

    public slideAdapter(Context context){
        this.context = context;
    }

    @Override
    public int getCount() {
        return lst_images.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull @NotNull View view, @NonNull @NotNull Object object) {
        return (view==(LinearLayout)object);
    }

    @NonNull
    @NotNull
    @Override
    public Object instantiateItem(@NonNull @NotNull ViewGroup container, int position) {
        inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.slide,container,false);
        ImageView imgslide = (ImageView) view.findViewById(R.id.slideimg);
        container.addView(view);
        imgslide.setImageResource(lst_images[position]);
        return view;

    }

    @Override
    public void destroyItem(@NonNull @NotNull ViewGroup container, int position, @NonNull @NotNull Object object) {
        container.removeView((LinearLayout)object);
    }

    public void setLst_images(int[] array){
        lst_images=array;
    }
}
