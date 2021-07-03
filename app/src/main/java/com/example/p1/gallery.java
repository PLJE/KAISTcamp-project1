package com.example.p1;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.content.res.AppCompatResources;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class gallery extends Fragment {


    private ArrayList<folders> foldersList;
    private RecyclerView recyclerView;
    private int ThumbNailCount = 2;
    private ArrayList<String> folderNames = new ArrayList<>();


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    private void setFoldersInfo() {
        String fileN="thimage";
        ArrayList<Drawable> imgList=new ArrayList<Drawable>();
        int id=0;
        for(int i=0; i<ThumbNailCount; i++){
            id=getResources().getIdentifier(fileN+i, "drawable", getActivity().getPackageName());
            if(id!=0){
                imgList.add(AppCompatResources.getDrawable(getContext(), id));
            }
            else{
                imgList.add(new ColorDrawable(Color.WHITE));
            }
        }
        for(int i=0; i<ThumbNailCount; i++){
            foldersList.add(new folders(imgList.get(i), folderNames.get(i)));
        }
    }

    private void setAdapter() {
        foldersAdapter adapter = new foldersAdapter(foldersList);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity().getApplicationContext(), 1);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new foldersAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(getContext(), imagesActivity.class);
                Bundle b = new Bundle();
                b.putInt("key", position); //Your id
                intent.putExtras(b); //Put your id to your next Intent
                startActivity(intent);
            }
        });
    }





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.activity_images, container, false);
        recyclerView = v.findViewById(R.id.galleryRecyclerView);
        foldersList= new ArrayList<>();                           //리사이클러뷰 기능 관련 함수

        folderNames.add("cats");
        folderNames.add("dogs");

        setFoldersInfo();
        setAdapter();                                           //리사이클러뷰 함수
        return v;

    }
}
