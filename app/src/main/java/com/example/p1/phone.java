package com.example.p1;

import android.content.ContentResolver;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class phone extends Fragment {

    //private ArrayList<Numbers> userList;
    private RecyclerView recyclerView;
    private ArrayList<String> numbook;

    public phone(ArrayList<String> numbook){
        this.numbook = numbook;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    private void setAdapter() {
        PhoneAdapter adapter = new PhoneAdapter(numbook);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_phone, container, false);
        recyclerView = v.findViewById(R.id.phone_recycler);
        //userList = new ArrayList<>();
        //setUserInfo();
        setAdapter();
        return v;
    }
}