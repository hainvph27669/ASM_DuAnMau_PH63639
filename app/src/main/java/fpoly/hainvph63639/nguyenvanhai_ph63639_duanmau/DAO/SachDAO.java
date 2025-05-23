package fpoly.hainvph63639.nguyenvanhai_ph63639_duanmau.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;



import java.util.ArrayList;

import fpoly.hainvph63639.nguyenvanhai_ph63639_duanmau.DB.DbHelper;
import fpoly.hainvph63639.nguyenvanhai_ph63639_duanmau.Model.Sach;


public class SachDAO {
    DbHelper dbhelper;

    public SachDAO(Context context) {
        dbhelper = new DbHelper(context);
    }

    public ArrayList<Sach> getDSDauSach() {
        ArrayList<Sach> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbhelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT sc.masach, sc.tensach, sc.giathue, sc.maloai , lo.tenloai  FROM SACH sc , LOAISACH lo WHERE sc.maloai = lo.maloai", null);
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            do {
                list.add(new Sach(cursor.getInt(0), cursor.getString(1), cursor.getInt(2), cursor.getInt(3), cursor.getString(4)));

            } while (cursor.moveToNext());
        }

        return list;
    }

    public boolean themSachMoi(String tensach, int giatien, int maloai) {
        SQLiteDatabase sqLiteDatabase = dbhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("tensach", tensach);
        contentValues.put("giathue", giatien);
        contentValues.put("maloai", maloai);
        long check = sqLiteDatabase.insert("SACH", null, contentValues);
        if (check == -1)
            return false;
        return true;
    }

    public boolean capNhatThongTinSach(int masach,String tensach, int giathue, int maloai) {
        SQLiteDatabase sqLiteDatabase = dbhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("tensach", tensach);
        contentValues.put("giathue", giathue);
        contentValues.put("maloai", maloai);
        long check = sqLiteDatabase.update("sach", contentValues, "masach = ?",
                new String[]{String.valueOf(masach)});
        if (check == -1)
            return false;
        return true;
    }

    //1:xóa thành công , 0:xóa thất bại , -1: không được phép xóa khi sách có trong phiếu mượn
    public int xoaSach(int masach) {
        SQLiteDatabase sqLiteDatabase = dbhelper.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT*FROM PHIEUMUON WHERE masach = ?",
                new String[]{String.valueOf(masach)});
        if (cursor.getCount() != 0) {
            return -1;
        }
        long check = sqLiteDatabase.delete("SACH",
                "masach=?", new String[]{String.valueOf(masach)});
        if (check == -1)
            return 0;
        return 1;

    }
}
