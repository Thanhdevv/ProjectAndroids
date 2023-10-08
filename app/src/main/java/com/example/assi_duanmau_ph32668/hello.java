package com.example.assi_duanmau_ph32668;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class hello extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello);
        ImageView ivLogo = findViewById(R.id.ivLogo);

        Glide.with(this).load(R.drawable.book).into(ivLogo);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity( new Intent(hello.this, login.class));
            }
        },3000);
    }
}