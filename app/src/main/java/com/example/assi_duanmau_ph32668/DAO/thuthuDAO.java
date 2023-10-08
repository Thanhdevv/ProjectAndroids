package com.example.assi_duanmau_ph32668.DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.assi_duanmau_ph32668.database.DBHelper;

public class thuthuDAO {
    DBHelper dbHelper;

    public thuthuDAO(Context context) {
        dbHelper = new DBHelper(context);
    }

    //check đăng nhập
    public boolean checkDangNhap(String matt, String matkhau) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM THUTHU WHERE matt = ? AND matkhau = ?", new String[]{matt, matkhau});
        if (cursor.getCount() != 0) {
            return true;
        } else {
            return false;
        }
    }
}
