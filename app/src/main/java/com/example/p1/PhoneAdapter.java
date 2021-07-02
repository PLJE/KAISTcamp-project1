package com.example.p1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PhoneAdapter extends RecyclerView.Adapter<PhoneAdapter.MyViewHolder> {

    private ArrayList<String> numbook;
    private ArrayList<String> namebook;

    public PhoneAdapter(ArrayList<String> numbook ,ArrayList<String> namebook){ //
        this.namebook = namebook; //
        this.numbook = numbook;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView phonenum;
        private TextView name;
        private ImageButton call;

        public MyViewHolder(final View view){
            super(view);
            phonenum = view.findViewById(R.id.tv_phone);
            name = view.findViewById(R.id.tv_name);
            call = view.findViewById(R.id.ib_call);

            call.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    String callnum = numbook.get(position);
                    Toast.makeText(v.getContext() , "number" + callnum , Toast.LENGTH_SHORT).show();


                }
            });
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

        String name = namebook.get(position); //
        holder.name.setText(name); //
    }

    @Override
    public int getItemCount() {
        return numbook.size();
    }
}
