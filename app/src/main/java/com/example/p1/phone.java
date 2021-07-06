package com.example.p1;

import android.content.ContentResolver;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.ContactsContract;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class phone extends Fragment {

    private RecyclerView recyclerView;
    private ArrayList<String> numbook;
    private ArrayList<String> namebook;
    private ArrayList<Bitmap> photobook;

    PhoneAdapter adapter;
    ItemTouchHelper helper;

    public phone(){

    }
    public phone(ArrayList<String> numbook , ArrayList<String> namebook , ArrayList<Bitmap> photobook){
        this.namebook = namebook;
        this.numbook = numbook;
        this.photobook = photobook;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void setAdapter() {
        adapter = new PhoneAdapter(numbook , namebook , photobook);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }
    @Nullable
    @Override
    public View onCreateView(@Nullable LayoutInflater inflater, @Nullable  ViewGroup container,
                            @Nullable Bundle savedInstanceState) {
        ViewGroup v = (ViewGroup)inflater.inflate(R.layout.fragment_phone, container, false);
        recyclerView = v.findViewById(R.id.phone_recycler);

        setAdapter();

        helper = new ItemTouchHelper(new ItemTouchHelperCallback(adapter));
        helper.attachToRecyclerView(recyclerView);

        return v;
    }
}