package com.example.p1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Fragment frag_phone;
    Fragment frag_gallery;
    Fragment frag_free;
    FragmentManager manager;
    FragmentTransaction ft;

    ArrayList<String> numbook = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        manager = getSupportFragmentManager();

        Button bt_phone = findViewById(R.id.bt_phone);
        Button bt_gallery = findViewById(R.id.bt_gallery);
        Button bt_free = findViewById(R.id.bt_free);

        frag_phone = new phone(numbook);
        frag_gallery = new gallery();
        frag_free = new free();

        ft = manager.beginTransaction();
        ft.add(R.id.fragment_container , frag_phone);
        ft.addToBackStack(null);
        ft.commit();

        bt_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,frag_phone).commit();
            }
        });
        bt_gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container ,frag_gallery).commit();
            }
        });
        bt_free.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,frag_free).commit();
            }
        });

        //bring the phone numbers
        ContentResolver cr = getContentResolver();
        Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI , null ,null, null, null);

        if(cur.getCount()>0){
            String line = "";
            while(cur.moveToNext()){
                int id = cur.getInt(cur.getColumnIndex(ContactsContract.Contacts._ID));
                line = String.format("%4d",id);
                String name = cur.getString(cur.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                line += " " + name;

                if(("1").equals(cur.getString(cur.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER)))) {
                    Cursor pCur = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=?", new String[]{String.valueOf(id)}, null);
                    int i = 0;
                    int pCount = pCur.getCount();
                    String[] phoneNum = new String[pCount];
                    String[] phoneType = new String[pCount];

                    while (pCur.moveToNext()) {
                        phoneNum[i] = pCur.getString(pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                        line += " " + phoneNum[i];
                        phoneType[i] = pCur.getString(pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.TYPE));
                        i++;
                    }
                }
                    numbook.add(line);
                    line ="";
                }
                //TextView textView = (TextView)findViewById(R.id.tv_phone);
                //for (String item:numbook) textView.append("\n" + item);
            }
        }
}