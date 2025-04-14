package fpoly.hainvph63639.nguyenvanhai_ph63639_duanmau.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    public DbHelper(Context context){
        super(context, "DKMH", null, 3);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Tạo bảng THUTHU
        String dbTHUTHU = "CREATE TABLE THUTHU (" +
                "matt TEXT PRIMARY KEY, " +
                "hoten TEXT, " +
                "matkhau TEXT, " +
                "loaitaikhoan TEXT)";
        db.execSQL(dbTHUTHU);

        // Tạo bảng THANHVIEN
        String dbTHANHVIEN = "CREATE TABLE THANHVIEN (" +
                "matv INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "hoten TEXT, " +
                "namsinh TEXT)";
        db.execSQL(dbTHANHVIEN);

        // Tạo bảng LOAISACH
        String dbLoai = "CREATE TABLE LOAISACH (" +
                "maloai INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "tenloai TEXT)";
        db.execSQL(dbLoai);

        // Tạo bảng SACH
        String dbSach = "CREATE TABLE SACH (" +
                "masach INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "tensach TEXT, " +
                "giathue INTEGER, " +
                "maloai INTEGER, " +
                "FOREIGN KEY (maloai) REFERENCES LOAISACH(maloai) ON DELETE CASCADE ON UPDATE CASCADE)";
        db.execSQL(dbSach);

        // Tạo bảng PHIEUMUON
        String dbPhieuMuon = "CREATE TABLE PHIEUMUON (" +
                "mapm INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "matv INTEGER, " +
                "matt TEXT, " +
                "masach INTEGER, " +
                "ngay TEXT, " +
                "trasach INTEGER, " +
                "tienthue INTEGER, " +
                "FOREIGN KEY (matv) REFERENCES THANHVIEN(matv) ON DELETE CASCADE ON UPDATE CASCADE, " +
                "FOREIGN KEY (matt) REFERENCES THUTHU(matt) ON DELETE CASCADE ON UPDATE CASCADE, " +
                "FOREIGN KEY (masach) REFERENCES SACH(masach) ON DELETE CASCADE ON UPDATE CASCADE)";
        db.execSQL(dbPhieuMuon);

        // Chèn dữ liệu mẫu
        db.execSQL("INSERT INTO LOAISACH (maloai, tenloai) VALUES (1, 'Thiếu nhi'), (2, 'Tình cảm'), (3, 'Giáo khoa')");
        db.execSQL("INSERT INTO SACH (masach, tensach, giathue, maloai) VALUES (1, 'Hãy đợi đấy', 2500, 1), (2, 'Mong chờ', 1000, 1), (3, 'Lập trình Android', 2000, 3)");
        db.execSQL("INSERT INTO THUTHU (matt, hoten, matkhau, loaitaikhoan) VALUES ('admin', 'Pham Van Hao', 'abc123', 'admin'), ('thuthu', 'Pham Van Hao', 'abc123', 'thuthu')");
        db.execSQL("INSERT INTO THANHVIEN (matv, hoten, namsinh) VALUES (1, 'Nguyễn Huy', '2000'), (2, 'Trần Kim Lan', '2000')");

        // Dữ liệu hợp lệ cho bảng PHIEUMUON (sửa lỗi matt không tồn tại)
        db.execSQL("INSERT INTO PHIEUMUON (mapm, matv, matt, masach, ngay, trasach, tienthue) VALUES " +
                "(1, 1, 'thuthu', 1, '19/07/2024', 1, 2500), " +
                "(2, 1, 'admin', 3, '19/07/2024', 0, 2000), " +
                "(3, 2, 'thuthu', 1, '19/07/2024', 1, 2000)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS THUTHU");
        db.execSQL("DROP TABLE IF EXISTS THANHVIEN");
        db.execSQL("DROP TABLE IF EXISTS LOAISACH");
        db.execSQL("DROP TABLE IF EXISTS SACH");
        db.execSQL("DROP TABLE IF EXISTS PHIEUMUON");
        onCreate(db);
    }
}
