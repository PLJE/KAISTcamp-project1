package com.example.p1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.Manifest;
import android.app.ActionBar;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import org.opencv.android.OpenCVLoader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> numbook = new ArrayList<>();
    ArrayList<String> namebook = new ArrayList<>();
    ArrayList<Bitmap> photobook = new ArrayList<>();

    private static String TAG = "MainActivity";
    static {
        if (OpenCVLoader.initDebug()){
            Log.d(TAG, "Opencv installed successfully");
        }
        else{
            Log.d(TAG, "opencv not installed");
        }
    }

    private final int PERMISSIONS_REQUEST_RESULT = 1;

    private ViewPager2 viewPager;
    private PagerAdapter pagerAdapter;
    private phone frag_phone;
    private gallery frag_gallery;
    private free frag_free;
    private TabLayout tabLayout;
    private String[] PERMISSIONS = new String[]
            {Manifest.permission.CAMERA,
            Manifest.permission.READ_CONTACTS,
            Manifest.permission.WRITE_CONTACTS,
            Manifest.permission.CALL_PHONE,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        if(hasPermissions(this, PERMISSIONS)) {

            createFragment();
            createViewpager();
            settingTabLayout();

            //bring the phone numbers
            ContentResolver cr = getContentResolver();
            Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);


            if (cur.getCount() > 0) {
                String line = "";
                String line2 = ""; //
                while (cur.moveToNext()) {
                    int id = cur.getInt(cur.getColumnIndex(ContactsContract.Contacts._ID));
                    //line = String.format("%4d",id);
                    String name = cur.getString(cur.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                    //line += " " + name;
                    line2 += name; //

                    if (("1").equals(cur.getString(cur.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER)))) {
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
                    line = "";

                    namebook.add(line2);
                    line2 = "";
                }
            }

            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
        else{
            ActivityCompat.requestPermissions(this, PERMISSIONS, 1000);
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


    public boolean hasPermissions(Context context, String... permissions) {
        if (context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }
}