package fpoly.hainvph63639.nguyenvanhai_ph63639_duanmau.DAO;

import static android.content.Context.MODE_PRIVATE;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;



import fpoly.hainvph63639.nguyenvanhai_ph63639_duanmau.DB.DbHelper;


public class ThuThuDAO {
    DbHelper dbhelper;
    SharedPreferences sharedPreferences;
    public ThuThuDAO(Context context){
        sharedPreferences = context.getSharedPreferences("THONGTIN", MODE_PRIVATE);
        dbhelper = new DbHelper(context);
    }
    public boolean checkDN(String matt, String matkhau){
        SQLiteDatabase sqLiteDatabase = dbhelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM THUTHU WHERE matt =? AND matkhau = ?",new String[]{matt, matkhau});
        if(cursor.getCount()!=0){
            cursor.moveToFirst();
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("matt", cursor.getString(0));
            editor.putString("hoten", cursor.getString(1));
            editor.putString("loaitaikhoan", cursor.getString(3));
            editor.commit();
            return true;
        }else {
            return false;
        }
    }

    public boolean CapNhapMKMoi(String user, String oldPass, String newPass){
        SQLiteDatabase sqLiteDatabase = dbhelper.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM THUTHU WHERE matt =? AND matkhau =?", new String[]{user, oldPass});
        if(cursor.getCount()>0){
            ContentValues contentValues = new ContentValues();
            contentValues.put("matkhau", newPass);
            long check =  sqLiteDatabase.update("THUTHU",contentValues,"matt=?", new String[]{user} );
            if(check==-1)
                return false;
            return true;
        }
        return false;
    }
    public boolean kiemTraTaiKhoanTonTai(String tenDangNhap) {
        SQLiteDatabase sqLiteDatabase = dbhelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM THUTHU WHERE matt = ?", new String[]{tenDangNhap});
        return cursor.getCount() > 0;
    }

    public boolean themTaiKhoan(String tenDangNhap, String ten, String matKhau, String loaiTaiKhoan) {
        SQLiteDatabase sqLiteDatabase = dbhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("matt", tenDangNhap);
        contentValues.put("hoten", ten);
        contentValues.put("matkhau", matKhau);
        contentValues.put("loaitaikhoan", loaiTaiKhoan);

        long result = sqLiteDatabase.insert("THUTHU", null, contentValues);

        return result != -1;
    }

}
