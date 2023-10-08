package com.example.assi_duanmau_ph32668.DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.assi_duanmau_ph32668.database.DBHelper;
import com.example.assi_duanmau_ph32668.model.Sach;

import java.util.ArrayList;

public class SachDAO {
    DBHelper dbHelper;

    public SachDAO(Context context) {
        dbHelper = new DBHelper(context);
    }

    //lấy toàn bộ đầu sách trong thư viện
    public ArrayList<Sach> getDSDauSAch() {
        ArrayList<Sach> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM SACH", null);
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            do {
                list.add(new Sach(cursor.getInt(0), cursor.getString(1), cursor.getInt(2), cursor.getInt(3)));
            } while (cursor.moveToNext());
        }
        return list;
    }
}
