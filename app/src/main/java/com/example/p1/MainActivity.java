package com.example.p1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    Fragment frag_phone;
    Fragment frag_gallery;
    Fragment frag_free;
    FragmentManager manager;
    FragmentTransaction ft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        manager = getSupportFragmentManager();

        Button bt_phone = findViewById(R.id.bt_phone);
        Button bt_gallery = findViewById(R.id.bt_gallery);
        Button bt_free = findViewById(R.id.bt_free);

        frag_phone = new phone();
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
    }
}