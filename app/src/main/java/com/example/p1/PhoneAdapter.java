package com.example.p1;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Currency;

public class PhoneAdapter extends RecyclerView.Adapter<PhoneAdapter.MyViewHolder> implements ItemTouchHelperListener {

    private ArrayList<String> numbook;
    private ArrayList<String> namebook;
    private ArrayList<Bitmap> photobook;

    public PhoneAdapter(ArrayList<String> numbook , ArrayList<String> namebook , ArrayList<Bitmap> photobook){
        this.namebook = namebook;
        this.numbook = numbook;
        this.photobook = photobook;
    }

    @Override
    public boolean onItemMove(int from_position, int to_position) {
        String name = namebook.get(from_position);
        String num = numbook.get(from_position);
        Bitmap photo = photobook.get(from_position);

        namebook.remove(from_position);
        numbook.remove(from_position);
        photobook.remove(from_position);

        namebook.add(to_position , name);
        numbook.add(to_position, num);
        photobook.add(to_position, photo);

        notifyItemMoved(from_position,to_position);
        return true;
    }

    @Override
    public void onItemSwipe(int position) {
        String number = numbook.get(position);

        namebook.remove(position);
        numbook.remove(position);
        photobook.remove(position);

        notifyItemRemoved(position);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView phonenum;
        private TextView name;
        private ImageButton call;
        private ImageView photo;

        public MyViewHolder(final View view){
            super(view);
            phonenum = view.findViewById(R.id.tv_phone);
            name = view.findViewById(R.id.tv_name);
            call = view.findViewById(R.id.ib_call);
            photo = view.findViewById(R.id.iv_photo);

            call.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    String callnum = numbook.get(position);
                    Toast.makeText(v.getContext() , "클릭한 번호로 전화를 겁니다" , Toast.LENGTH_SHORT).show();

                    String calling = callnum.replace("-","");
                    Intent intent = new Intent(Intent.ACTION_DIAL , Uri.parse("tel:" + calling));
                    intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK);
                    v.getContext().startActivity(intent);
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

        String name = namebook.get(position);
        holder.name.setText(name);

        Bitmap bitmap = photobook.get(position);//
        if(bitmap != null)
            holder.photo.setImageBitmap(bitmap);
    }

    @Override
    public int getItemCount() {
        return numbook.size();
    }


    public static long getContactIDFromNumber(ContentResolver contacthelper , String number){
        long rawContactId = -1;
        Uri contactUri = Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI , Uri.encode(number));
        String [] projection = {ContactsContract.PhoneLookup._ID};
        Cursor cursor = null;

        try{
            cursor = contacthelper.query(contactUri,projection,null,null,null);
            if(cursor.moveToFirst()){
                rawContactId = cursor.getLong(cursor.getColumnIndex(ContactsContract.PhoneLookup._ID));
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            if(cursor!=null){
                cursor.close();
            }
        }
        return rawContactId;
    }
}
