package fpoly.hainvph63639.nguyenvanhai_ph63639_duanmau.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import java.util.ArrayList;

import fpoly.hainvph63639.nguyenvanhai_ph63639_duanmau.DB.DbHelper;
import fpoly.hainvph63639.nguyenvanhai_ph63639_duanmau.Model.LoaiSach;

public class LoaiSachDAO {

    DbHelper dbhelper;
    public LoaiSachDAO(Context context){
        dbhelper = new DbHelper(context);
    }

    public ArrayList<LoaiSach> getDSLoaiSach(){
        ArrayList<LoaiSach> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbhelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM LOAISACH", null);
        if(cursor.getCount()!=0){
            cursor.moveToFirst();
            do {
                list.add(new LoaiSach(cursor.getInt(0),cursor.getString(1)));
            }while (cursor.moveToNext());
        }
        return list;
    }

    public boolean themLoaiSach(String tenLoai){
        SQLiteDatabase sqLiteDatabase = dbhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("tenLoai", tenLoai);
        long check = sqLiteDatabase.insert("LOAISACH",null,contentValues);
        if(check == -1)
            return false;
        return true;
    }

    //xóa loại sách

    public int xoaLoaiSach(int id){
        SQLiteDatabase sqLiteDatabase = dbhelper.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM SACH WHERE maloai = ?", new String[]{String.valueOf(id)});
        if(cursor.getCount() != 0){
            return -1;
        }

        long check = sqLiteDatabase.delete("LOAISACH", "maloai = ?", new String[]{String.valueOf(id)});
        if(check == -1)
            return 0;
        return 1;
    }
}
