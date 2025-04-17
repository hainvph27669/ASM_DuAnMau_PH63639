package fpoly.hainvph63639.nguyenvanhai_ph63639_duanmau.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;



import java.util.ArrayList;

import fpoly.hainvph63639.nguyenvanhai_ph63639_duanmau.DAO.ThuThuDAO;
import fpoly.hainvph63639.nguyenvanhai_ph63639_duanmau.R;

public class ThemAdmin_Fragment extends Fragment {
    ThuThuDAO thuThuDAO;
    Spinner spinner;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_them_admin_, container, false);
        EditText edtTenDN = view.findViewById(R.id.edtTenDangNhap);
        EditText edtTenTK = view.findViewById(R.id.edtTenTK);
        EditText edtMK = view.findViewById(R.id.edtMatKhau);
        spinner = view.findViewById(R.id.spnLoaiTK);
        Button btnThem = view.findViewById(R.id.btnThemAdmin);
        ArrayList<String> listLoaiTK = new ArrayList<>();
        listLoaiTK.add("Admin");
        listLoaiTK.add("Thủ thư");
        ArrayAdapter adapter = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1, listLoaiTK);
        spinner.setAdapter(adapter);
        ThuThuDAO dao = new ThuThuDAO(getContext());
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tenDangNhap = edtTenDN.getText().toString();
                String ten = edtTenTK.getText().toString();
                String matKhau = edtMK.getText().toString();
                String loaiTaiKhoan = spinner.getSelectedItem().toString();

                // Tạo một đối tượng ThuThuDAO để kiểm tra tài khoản tồn tại
                thuThuDAO = new ThuThuDAO(getContext());

                // Kiểm tra xem tên đăng nhập đã tồn tại chưa
                if (thuThuDAO.kiemTraTaiKhoanTonTai(tenDangNhap)) {
                    Toast.makeText(getContext(), "Tài khoản đã tồn tại", Toast.LENGTH_SHORT).show();
                } else {
                    // Tạo một đối tượng ThuThuDAO để thêm tài khoản
                    thuThuDAO = new ThuThuDAO(getContext());

                    // Thực hiện thêm tài khoản vào cơ sở dữ liệu
                    boolean themTaiKhoanThanhCong = thuThuDAO.themTaiKhoan(tenDangNhap, ten, matKhau, loaiTaiKhoan);

                    if (themTaiKhoanThanhCong) {
                        Toast.makeText(getContext(), "Thêm tài khoản thành công", Toast.LENGTH_SHORT).show();
                        // Reset các trường nhập liệu sau khi thêm thành công
                        edtTenDN.setText("");
                        edtTenTK.setText("");
                        edtMK.setText("");
                    } else {
                        Toast.makeText(getContext(), "Thêm tài khoản thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        return view;
    }
}