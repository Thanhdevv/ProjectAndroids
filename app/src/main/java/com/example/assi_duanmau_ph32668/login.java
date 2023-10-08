package com.example.assi_duanmau_ph32668;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.assi_duanmau_ph32668.DAO.thuthuDAO;

public class login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        EditText edtuser = findViewById(R.id.edtusername);
        EditText edtpass = findViewById(R.id.edtpassword);
        Button btnlogin = findViewById(R.id.btnlogin);
        thuthuDAO thuthuDAO = new thuthuDAO(this);
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = edtuser.getText().toString();
                String pass = edtpass.getText().toString();
                if (thuthuDAO.checkDangNhap(user, pass)) {
                    SharedPreferences sharedPreferences = getSharedPreferences("THONGTIN", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("matt", user);
                    editor.commit();

                    startActivity(new Intent(login.this, MainActivity.class));
                } else {
                    Toast.makeText(login.this, "User hoặc mật khẩu không đúng", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}