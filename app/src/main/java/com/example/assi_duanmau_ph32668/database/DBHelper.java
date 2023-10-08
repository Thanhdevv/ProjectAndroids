package com.example.assi_duanmau_ph32668.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context) {
        super(context, "QLSach", null, 5);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // bảng thủ thư
        String dbThuThu = "CREATE TABLE THUTHU(matt text primary key, " +
                "hoten text, " +
                "matkhau text)";
        db.execSQL(dbThuThu);
        //bảng thành viên
        String dbThanhVien = "CREATE TABLE THANHVIEN(matv integer primary key autoincrement, " +
                "hoten text, " +
                "namsinh text)";
        db.execSQL(dbThanhVien);
        //bảng loại sách
        String dbLoaisach = "CREATE TABLE LOAISACH(maloai integer primary key autoincrement, " +
                "tenloai text)";
        db.execSQL(dbLoaisach);
        //bảng sách
        String dbSach = "CREATE TABLE SACH(masach integer primary key autoincrement, " +
                "tensach text, " +
                "giathue integer," +
                " maloai integer references LOAISACH(maloai))";
        db.execSQL(dbSach);
        //bảng phiếu mượn
        String dbPhieumuon = "CREATE TABLE PHIEUMUON(mapm integer primary key autoincrement, " +
                "matv integer references THANHVIEN(matv)," +
                " matt text references THUTHU(matt), " +
                "masach integer references SACH(masach), " +
                "ngay text, " +
                "trasach integer, " +
                "tienthue integer)";
        db.execSQL(dbPhieumuon);

        //insert data
        db.execSQL("INSERT INTO LOAISACH VALUES (1, 'Thiếu nhi'), (2,'Tình cảm'),(3, 'Giáo dục')");
        db.execSQL("INSERT INTO SACH VALUES (1, 'Thỏ con không chịu ngủ', 3000, 1), (2, 'Hãy đợi đấy', 2000, 1), (3, 'Lập trình game', 5000, 3)");
        db.execSQL("INSERT INTO THUTHU VALUES ('thuthu01', 'Chu Vũ', 'abc12'), ('thuthu02', 'Minh Dương', 'abc123'), ('thuthu03', 'Duy Ạnh', 'abc1234')");
        db.execSQL("INSERT INTO THANHVIEN VALUES (1, 'Khuất Anh', '2004'), (2, 'Khuất Em', '2007'), (3, 'Dương Đoàn', '2004') ,(4, 'Hoàng Thanh', '2002')");
        //1 da tra 0 chua tra
        db.execSQL("INSERT INTO PHIEUMUON VALUES (1, 1, 'thuthu01',2, '07/10/2023', 1, 2500), (2,1, 'thuthu01',3, '07/10/2023', 0, 2000), (3,2, 'thuthu02', 1, '07/10/2023',1,2000),(4, 3,'thuthu03', 3, '06/10/2023', 0, 4000)");
    }

    //(3,2, 'thuthu02', 1, '07/10/2023',1,2000)
    //(4, 3,'thuthu03', 3, '06/10/2023', 0, 4000)

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {
            db.execSQL("DROP TABLE IF EXISTS THUTHU");
            db.execSQL("DROP TABLE IF EXISTS THANHVIEN");
            db.execSQL("DROP TABLE IF EXISTS LOAISACH");
            db.execSQL("DROP TABLE IF EXISTS SACH");
            db.execSQL("DROP TABLE IF EXISTS PHIEUMUON");
            onCreate(db);
        }
    }
}
