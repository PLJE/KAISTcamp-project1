package com.example.p1;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.appcompat.content.res.AppCompatResources;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import java.util.ArrayList;
import java.util.Random;



public class images extends Fragment {
    private ArrayList<cats> catslist;
    private ArrayList<Integer> IDlist = new ArrayList<>();
    private RecyclerView recyclerView;
    private Animation fade_in;
    private View blackBoard;
    private View xButton;
    private View animatedView;
    private ArrayList<Integer> foldersCount = new ArrayList<>();

    private int WhichFolder;

    public images(int whichFolder){
        this.WhichFolder = whichFolder;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void setAdapter() {
        imagesAdapter adapter = new imagesAdapter(catslist);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity().getApplicationContext(), 2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new imagesAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                ((ViewPager) animatedView).setCurrentItem(position, false);
                blackBoard.setVisibility(View.VISIBLE);
                animatedView.startAnimation(fade_in);
                xButton.setVisibility(View.VISIBLE);
                animatedView.setVisibility(View.VISIBLE);
            }
        });
    }


    private void setCatsInfo() {
        String fileN="catsimage";
        gloval k = new gloval();
        ArrayList<Drawable> imgList=new ArrayList<Drawable>();
        int id=0;
        for(int i=0; i<foldersCount.get(0); i++){
            id=getResources().getIdentifier(fileN+i, "drawable", getActivity().getPackageName());
            IDlist.add(id);
            if(id!=0){
                imgList.add(AppCompatResources.getDrawable(getContext(), id));
            }
            else{
                imgList.add(new ColorDrawable(Color.WHITE));
            }
        }
        for(int i=0; i<foldersCount.get(0); i++){
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
        foldersCount.add(10);
        foldersCount.add(12);

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_images, container, false);
        IDlist = new ArrayList<>();
        recyclerView = v.findViewById(R.id.galleryRecyclerView);
        catslist = new ArrayList<>();                           //리사이클러뷰 기능 관련 함수

        fade_in = AnimationUtils.loadAnimation(getContext(), R.anim.fade_in);
        blackBoard = v.findViewById(R.id.blackBorad);
        xButton = v.findViewById(R.id.xButton);
        animatedView = v.findViewById(R.id.viewpager);
        ViewPager viewPager = (ViewPager) animatedView;
        slideAdapter myadapter = new slideAdapter(getContext());             //슬라이드 기능 관련 변수

        setCatsInfo();
        setAdapter();                                           //리사이클러뷰 함수

        myadapter.setLst_images(convertIntegers(IDlist));
        viewPager.setAdapter(myadapter);
        xButton.setOnClickListener(new View.OnClickListener() {      //이미지 클릭시 슬라이드 기능 제공
            @Override
            public void onClick(View v) {
                xButton.setVisibility(View.GONE);
                animatedView.setVisibility(View.GONE);
                blackBoard.setVisibility(View.GONE);
            }
        });
        return v;

    }
}