package com.example.p1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PhoneAdapter extends RecyclerView.Adapter<PhoneAdapter.MyViewHolder> {
    //private ArrayList<Numbers> userList;
    private ArrayList<String> numbook;

    public PhoneAdapter(ArrayList<String> numbook){
        this.numbook = numbook;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView phonenum;

        public MyViewHolder(final View view){
            super(view);
            phonenum = view.findViewById(R.id.tv_phone);
        }
    }

    @NonNull
    @Override
    public PhoneAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.phone_recycler, parent,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PhoneAdapter.MyViewHolder holder, int position) {
        String number = numbook.get(position);
        holder.phonenum.setText(number);
    }

    @Override
    public int getItemCount() {
        return numbook.size();
    }
}
