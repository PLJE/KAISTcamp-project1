//package com.example.p1;
//
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//public class PhoneAdapter extends RecyclerView.Adapter<PhoneAdapter.ViewHolder> {
//
//    private String[] localDataSet;
//
//    public static class ViewHolder extends RecyclerView.ViewHolder{
//        private final TextView textView;
//        public ViewHolder(View view){
//            super(view);
//            textView = (TextView) view.findViewById(R.id.tv_phone);
//        }
//        public TextView getTextView(){
//            return textView;
//        }
//    }
//
//    public PhoneAdapter(String[] dataSet) {
//        localDataSet = dataSet;
//    }
//
//    public PhoneAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.)
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull PhoneAdapter.ViewHolder holder, int position) {
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return 0;
//    }
//}
