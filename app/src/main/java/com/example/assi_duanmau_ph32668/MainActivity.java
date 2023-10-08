package com.example.assi_duanmau_ph32668;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.example.assi_duanmau_ph32668.DAO.PhieuMuonDAO;
import com.example.assi_duanmau_ph32668.fragment.QLLoaiSachFragment;
import com.example.assi_duanmau_ph32668.fragment.QLPhieuMuonFragment;
import com.example.assi_duanmau_ph32668.model.PhieuMuon;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolBar);
        FrameLayout frameLayout = findViewById(R.id.frameLayout);
        NavigationView navigationView = findViewById(R.id.navigationView);
        drawerLayout = findViewById(R.id.drawerLayout);



        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.menu);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                if (item.getItemId() == R.id.mDangxuat){
                    finish();
                } else if (item.getItemId() == R.id.mQLPhieumuon) {
                    fragment = new QLPhieuMuonFragment();
                } else if (item.getItemId() == R.id.mQLLoaiSach) {
                    fragment = new QLLoaiSachFragment();
                }else {
                    fragment = new QLPhieuMuonFragment();
                }
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.frameLayout, fragment)
                        .commit();

                drawerLayout.closeDrawer(GravityCompat.START);
                toolbar.setTitle(item.getTitle());
                return false;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            drawerLayout.openDrawer(GravityCompat.START);
        }
        return super.onOptionsItemSelected(item);
    }
}