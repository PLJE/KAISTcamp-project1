package com.example.p1;

import android.content.Intent;
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
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;



public class gallery extends Fragment {
    private ArrayList<cats> catslist;
    private ArrayList<Integer> IDlist = new ArrayList<>();
    private RecyclerView recyclerView;
    private Animation fade_in;

    private View xButton;
    private View animatedView;
    private ViewPager viewPager;
    private slideAdapter myadapter;



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

        adapter.setOnItemClickListener(new galleryAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                ((ViewPager) animatedView).setCurrentItem(position, false);
                animatedView.startAnimation(fade_in);
                xButton.setVisibility(View.VISIBLE);
                animatedView.setVisibility(View.VISIBLE);
            }
        });
    }

    private ArrayList<Integer> makeRand(int high){
        ArrayList<Integer> randL= new ArrayList<Integer>();
        while(randL.size()<=20){
            Random random = new Random();
            int k =random.nextInt(high+1);
            if(!randL.contains(k)){
                randL.add(k);
            }
        }
        return new ArrayList<Integer>(randL);
    }

    private void setCatsInfo() {
        String fileN="image";
        gloval k = new gloval();
        ArrayList<Integer> randList = makeRand(k.catImgN);
        ArrayList<Drawable> imgList=new ArrayList<Drawable>();
        int id=0;
        for(int i=0; i<20; i++){
            id=getResources().getIdentifier(fileN+randList.get(i), "drawable", getActivity().getPackageName());
            IDlist.add(id);
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

    public static int[] convertIntegers(ArrayList<Integer> integers)
    {
        int[] ret = new int[integers.size()];
        for (int i=0; i < ret.length; i++)
        {
            ret[i] = integers.get(i).intValue();
        }
        return ret;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_gallery, container, false);
        recyclerView = v.findViewById(R.id.galleryRecyclerView);
        animatedView = v.findViewById(R.id.viewpager);
        xButton = v.findViewById(R.id.xButton);
        catslist = new ArrayList<>();
        fade_in = AnimationUtils.loadAnimation(getContext(), R.anim.fade_in);
        viewPager = (ViewPager) animatedView;
        myadapter = new slideAdapter(getContext());

        setCatsInfo();
        setAdapter();

        myadapter.setLst_images(convertIntegers(IDlist));
        viewPager.setAdapter(myadapter);

        xButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xButton.setVisibility(View.GONE);
                animatedView.setVisibility(View.GONE);
            }
        });
        return v;

    }
}