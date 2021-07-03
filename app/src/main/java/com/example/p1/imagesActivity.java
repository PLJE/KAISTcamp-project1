package com.example.p1;

import android.app.Activity;
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
import java.util.Arrays;
import java.util.Random;



public class imagesActivity extends Activity {
    private ArrayList<cats> catslist;
    private ArrayList<Integer> IDlist = new ArrayList<>();
    private RecyclerView recyclerView;
    private Animation fade_in;
    private View blackBoard;
    private View xButton;
    private View animatedView;

    private ArrayList<String> folderNames = new ArrayList<>();
    private ArrayList<Integer> folderCount = new ArrayList<>(Arrays.asList(10, 12));

    private int position;

    public void setPosition() {
        Bundle b = getIntent().getExtras();
        int po = -1; // or other values
        if(b != null)
            po = b.getInt("key");
        this.position = po;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_images);

        folderNames.add("cats");
        folderNames.add("dogs");

        // Inflate the layout for this fragment
        IDlist = new ArrayList<>();
        recyclerView = findViewById(R.id.galleryRecyclerView);
        catslist = new ArrayList<>();                           //리사이클러뷰 기능 관련 함수
        setPosition();
        fade_in = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in);
        blackBoard =findViewById(R.id.blackBorad);
        xButton = findViewById(R.id.xButton);
        animatedView = findViewById(R.id.viewpager);
        ViewPager viewPager = (ViewPager) animatedView;
        slideAdapter myadapter = new slideAdapter(getApplicationContext());             //슬라이드 기능 관련 변수

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
    }

    private void setAdapter() {
        imagesAdapter adapter = new imagesAdapter(catslist);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 2);
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
        String fileN=folderNames.get(position)+"image";
        ArrayList<Drawable> imgList=new ArrayList<Drawable>();
        int id=0;
        for(int i=0; i<folderCount.get(position); i++){
            id=getResources().getIdentifier(fileN+i, "drawable", getPackageName());
            IDlist.add(id);
            if(id!=0){
                imgList.add(AppCompatResources.getDrawable(getApplicationContext(), id));
            }
            else{
                imgList.add(new ColorDrawable(Color.WHITE));
            }
        }
        for(int i=0; i<folderCount.get(position); i++){
            catslist.add(new cats(imgList.get(i)));
        }
    }

    public static int[] convertIntegers(ArrayList<Integer> integers)
    {
        int[] ret = new int[integers.size()];
        for (int i=0; i < ret.length; i++)
        {
            ret[i] = integers.get(i);
        }
        return ret;
    }


}