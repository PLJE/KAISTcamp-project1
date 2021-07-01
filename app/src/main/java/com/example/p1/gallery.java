package com.example.p1;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;

import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class gallery extends Fragment {
    private ArrayList<cats> catslist;
    private RecyclerView recyclerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void setAdapter() {
        galleryAdapter adapter = new galleryAdapter(catslist);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity().getApplicationContext(), 2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    private ArrayList<Integer> makeRand(int high){
        HashSet<Integer> set=new HashSet<Integer>();
        while(set.size()<=20){
            int k=(int)(Math.random()*10000)%high;
            set.add(k);
        }
        return new ArrayList<Integer>(set);
    }

    private void setCatsInfo() {
        int catImgN=23;
        String fileN="image";
        ArrayList<Integer> randList = makeRand(catImgN);
        ArrayList<Drawable> imgList=new ArrayList<Drawable>();
        int id=0;
        for(int i=0; i<20; i++){
            id=getResources().getIdentifier(fileN+randList.get(i), "drawable", getActivity().getPackageName());
            if(id!=0){
                imgList.add(getContext().getDrawable(id));
            }
            else{
                imgList.add(new ColorDrawable(Color.WHITE));
            }
        }
        for(int i=0; i<20; i++){
            catslist.add(new cats(imgList.get(i)));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_gallery, container, false);
        recyclerView = v.findViewById(R.id.galleryRecyclerView);
        catslist = new ArrayList<>();
        setCatsInfo();
        setAdapter();
        return v;

    }
}