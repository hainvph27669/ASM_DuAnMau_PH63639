package fpoly.hainvph63639.nguyenvanhai_ph63639_duanmau.DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;




import java.util.ArrayList;

import fpoly.hainvph63639.nguyenvanhai_ph63639_duanmau.DB.DbHelper;
import fpoly.hainvph63639.nguyenvanhai_ph63639_duanmau.Model.Sach;


public class ThongKeDAO {
    DbHelper dbhelper;
    public ThongKeDAO(Context context){
        dbhelper = new DbHelper(context);
    }
    public ArrayList<Sach> getTop10(){
        ArrayList<Sach> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbhelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT sc.masach, sc.tensach, COUNT(pm.masach) " +
                "FROM SACH sc " +
                "INNER JOIN PHIEUMUON pm ON sc.masach = pm.masach " +
                "GROUP BY sc.masach, sc.tensach " +
                "ORDER BY COUNT(pm.masach) DESC " +
                "LIMIT 10", null);
        if(cursor.getCount()!=0){
            cursor.moveToFirst();
            do {
                list.add(new Sach(cursor.getInt(0),cursor.getString(1),cursor.getInt(2)));

            }while (cursor.moveToNext());
        }

        return list;
    }

    public int getDoanhThu(String ngaybatdau, String ngayketthuc){
        ngaybatdau = ngaybatdau.replace("/","");
        ngayketthuc = ngayketthuc.replace("/","");
        SQLiteDatabase sqLiteDatabase = dbhelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT SUM(tienthue) \n" +
                "FROM PHIEUMUON \n" +
                "WHERE substr(ngay,7)||substr(ngay,4,2)||substr(ngay,1,2) between ? and ?", new String[]{ngaybatdau, ngayketthuc});
        if (cursor.getCount() != 0){
            cursor.moveToFirst();
            return cursor.getInt(0);
        }
        return 0;
    }
}
