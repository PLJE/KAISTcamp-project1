package com.example.p1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.app.ActionBar;
import android.content.ContentResolver;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> numbook = new ArrayList<>();
    ArrayList<String> namebook = new ArrayList<>();
    ArrayList<Bitmap> photobook = new ArrayList<>();

    private ViewPager2 viewPager;
    private PagerAdapter pagerAdapter;
    private phone frag_phone;
    private gallery frag_gallery;
    private free frag_free;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createFragment();
        createViewpager();
        settingTabLayout();

        //bring the phone numbers
        ContentResolver cr = getContentResolver();
        Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI , null ,null, null, null);

        if(cur.getCount()>0){
            String line = "";
            String line2 =""; //
            while(cur.moveToNext()){
                int id = cur.getInt(cur.getColumnIndex(ContactsContract.Contacts._ID));
                //line = String.format("%4d",id);
                String name = cur.getString(cur.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                //line += " " + name;
                line2 += name; //

                if(("1").equals(cur.getString(cur.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER)))) {
                    Cursor pCur = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=?", new String[]{String.valueOf(id)}, null);
                    int i = 0;
                    int pCount = pCur.getCount();
                    String[] phoneNum = new String[pCount];
                    String[] phoneType = new String[pCount];

                    while (pCur.moveToNext()) {
                        phoneNum[i] = pCur.getString(pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                        line += phoneNum[i];
                        phoneType[i] = pCur.getString(pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.TYPE));
                        i++;
                    }
                }

                int photoid = cur.getInt(cur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.PHOTO_ID)); //
                Bitmap bitmap = queryContactImage(photoid);
                photobook.add(bitmap);

                numbook.add(line);
                line ="";

                namebook.add(line2);
                line2 = "";
            }
        }
    }
    private Bitmap queryContactImage(int imageDataRow){
        Cursor c = getContentResolver().query(ContactsContract.Data.CONTENT_URI, new String[] {
                ContactsContract.CommonDataKinds.Photo.PHOTO
        }, ContactsContract.Data._ID + "=?", new String[] {
                Integer.toString(imageDataRow)
        }, null);
        byte[] imageBytes = null;
        if (c != null) {
            if (c.moveToFirst()) {
                imageBytes = c.getBlob(0);
            }
            c.close();
        }

        if (imageBytes != null) {
            return BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
        } else {
            return null;
        }
    }
    private void createFragment() {
        frag_phone = new phone(numbook,namebook,photobook);
        frag_gallery = new gallery();
        frag_free = new free();
    }
    private void createViewpager(){
        viewPager = (ViewPager2)findViewById(R.id.viewpager_control);
        pagerAdapter = new PagerAdapter(getSupportFragmentManager(), getLifecycle());
        pagerAdapter.addFragment(frag_phone);
        pagerAdapter.addFragment(frag_gallery);
        pagerAdapter.addFragment(frag_free);

        viewPager.setAdapter(pagerAdapter);

        viewPager.setUserInputEnabled(false);
    }
    private void settingTabLayout(){
        tabLayout = (TabLayout)findViewById(R.id.tablayout_control);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int pos = tab.getPosition();
                switch(pos){
                    case 0:
                        viewPager.setCurrentItem(0);
                        break;
                    case 1:
                        viewPager.setCurrentItem(1);
                        break;
                    case 2:
                        viewPager.setCurrentItem(2);
                        break;
                }
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}