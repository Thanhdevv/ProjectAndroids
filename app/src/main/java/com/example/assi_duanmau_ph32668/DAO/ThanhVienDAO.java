package com.example.assi_duanmau_ph32668.DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.assi_duanmau_ph32668.database.DBHelper;
import com.example.assi_duanmau_ph32668.model.ThanhVien;

import java.util.ArrayList;

public class ThanhVienDAO {
    DBHelper dbHelper;

    public ThanhVienDAO(Context context){
        dbHelper = new DBHelper(context);
    }

    public ArrayList<ThanhVien> getDSThanhVien(){
        ArrayList<ThanhVien> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM THANHVIEN", null);

        if (cursor.getCount() != 0){
            cursor.moveToFirst();
            do {
                list.add(new ThanhVien(cursor.getInt(0),cursor.getString(1),cursor.getString(2)));
            }while (cursor.moveToNext());
        }
        return list;
    }
}
