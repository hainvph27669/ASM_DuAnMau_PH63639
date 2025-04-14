package fpoly.hainvph63639.nguyenvanhai_ph63639_duanmau;



import static fpoly.hainvph63639.nguyenvanhai_ph63639_duanmau.R.id.mQLPM;
import static fpoly.hainvph63639.nguyenvanhai_ph63639_duanmau.R.id.mQLloaiSach;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.navigation.NavigationView;

import fpoly.hainvph63639.nguyenvanhai_ph63639_duanmau.DAO.ThuThuDAO;
import fpoly.hainvph63639.nguyenvanhai_ph63639_duanmau.Fragment.QL_LoaiSach_Fragment;
import fpoly.hainvph63639.nguyenvanhai_ph63639_duanmau.Fragment.QL_PhieuMuon_Fragment;
import fpoly.hainvph63639.nguyenvanhai_ph63639_duanmau.Fragment.QL_Sach_Fragment;
import fpoly.hainvph63639.nguyenvanhai_ph63639_duanmau.Fragment.QL_ThanhVien_Fragment;
import fpoly.hainvph63639.nguyenvanhai_ph63639_duanmau.Fragment.ThemAdmin_Fragment;
import fpoly.hainvph63639.nguyenvanhai_ph63639_duanmau.Fragment.ThongKeTop10_Fragment;
import fpoly.hainvph63639.nguyenvanhai_ph63639_duanmau.Fragment.ThongKe_Fragment;
import fpoly.hainvph63639.nguyenvanhai_ph63639_duanmau.Login.Activity_Login;

public class MainActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolBar);
        FrameLayout frameLayout = findViewById(R.id.frameLayout);
        NavigationView navigationView = findViewById(R.id.navView);
        drawerLayout = findViewById(R.id.drwLayout);

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.menu);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = new Fragment();
                int itemId = item.getItemId();

                if (itemId == mQLPM) {
                    fragment = new QL_PhieuMuon_Fragment();
                }else if(itemId==R.id.mQLSach){
                    fragment = new QL_Sach_Fragment();
                }else if(itemId==R.id.mQLThanhVien){
                    fragment = new QL_ThanhVien_Fragment();
                }else if(itemId==R.id.mDoanhThu){
                    fragment = new ThongKe_Fragment();
                }else if(itemId==R.id.mTop10){
                    fragment = new ThongKeTop10_Fragment();
                } else if (itemId == R.id.mThoat) {
                    Intent intent = new Intent(MainActivity.this, Activity_Login.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivities(new Intent[]{intent});
                }else if (itemId == R.id.mDoiMK){
                    showDialogDoiMK();
                }else if (itemId == mQLloaiSach) {
                    fragment = new QL_LoaiSach_Fragment();
                } else {
                    fragment = new ThemAdmin_Fragment();
                }
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.frameLayout,fragment)
                        .commit();
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });
        //hien thi mot so chuc nang cho admin
        SharedPreferences sharedPreferences = getSharedPreferences("THONGTIN", MODE_PRIVATE);
        String loaiTK = sharedPreferences.getString("loaitaikhoan","");
        if(loaiTK.equals("thuthu")){
            Menu menu = navigationView.getMenu();
            menu.findItem(R.id.mDoanhThu).setVisible(false);
            menu.findItem(R.id.mThemNguoiDung).setVisible(false);
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()== android.R.id.home){
            drawerLayout.openDrawer(GravityCompat.START);
        }
        return super.onOptionsItemSelected(item);
    }

    private void showDialogDoiMK(){
        AlertDialog.Builder builder= new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_doimk,null );
        EditText edtOldPass = view.findViewById(R.id.edPassOld);
        EditText edtNewPass = view.findViewById(R.id.edtPassNew);
        EditText edtReNewPass = view.findViewById(R.id.edtRePassNew);

        builder.setView(view);

        builder.setPositiveButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.setNegativeButton("Cập nhập", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String oldpass = edtOldPass.getText().toString();
                String newpass = edtNewPass.getText().toString();
                String reNewPass = edtReNewPass.getText().toString();

                if(newpass.equals(reNewPass)){
                    SharedPreferences sharedPreferences = getSharedPreferences("THONGTIN",MODE_PRIVATE);
                    String matt = sharedPreferences.getString("matt","");
                    ThuThuDAO thuThuDAO = new ThuThuDAO(MainActivity.this);
                    boolean check = thuThuDAO.CapNhapMKMoi(matt,oldpass,newpass);
                    if(check){
                        Toast.makeText(MainActivity.this, "Cập nhập mk thành công", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this,Activity_Login.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    }else {
                        Toast.makeText(MainActivity.this, "Cập nhập thất bại", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(MainActivity.this, "Mật khẩu không trùng  khớp", Toast.LENGTH_SHORT).show();
                }
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();


    }
}