package com.example.p1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PhoneAdapter extends RecyclerView.Adapter<PhoneAdapter.ViewHolder> {

    private ArrayList<Numbers> ndata = null;

    public PhoneAdapter(ArrayList<Numbers> data){
        ndata = data;
    }

    @NonNull
    @Override
    public PhoneAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.phone_recycler , parent, false);
        PhoneAdapter.ViewHolder vh = new PhoneAdapter.ViewHolder(view);

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull PhoneAdapter.ViewHolder holder, int position) {
        Numbers item = ndata.get(position);

        holder.number.setText(item.getNumber());
    }

    @Override
    public int getItemCount() {
        return ndata.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        //TextView name;
        TextView number;

        ViewHolder(View item){
            super(item);
            number = item.findViewById(R.id.tv_phone);
        }
    }
}
