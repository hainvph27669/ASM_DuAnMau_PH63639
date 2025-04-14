package fpoly.hainvph63639.nguyenvanhai_ph63639_duanmau.Fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;

import fpoly.hainvph63639.nguyenvanhai_ph63639_duanmau.Adapter.SachAdapter;
import fpoly.hainvph63639.nguyenvanhai_ph63639_duanmau.DAO.LoaiSachDAO;
import fpoly.hainvph63639.nguyenvanhai_ph63639_duanmau.DAO.SachDAO;
import fpoly.hainvph63639.nguyenvanhai_ph63639_duanmau.Model.LoaiSach;
import fpoly.hainvph63639.nguyenvanhai_ph63639_duanmau.Model.Sach;
import fpoly.hainvph63639.nguyenvanhai_ph63639_duanmau.R;


public class QL_Sach_Fragment extends Fragment {
    SachDAO sachDAO;
    RecyclerView recyclerSach;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_q_l__sach_, container, false);

        recyclerSach = view.findViewById(R.id.recSach);
        FloatingActionButton floatadd = view.findViewById(R.id.floatAdd);
        sachDAO = new SachDAO(getContext());
        loaddata();

        floatadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showdialog();

            }
        });

        return view;
    }
    private void loaddata(){
        ArrayList<Sach> list = sachDAO.getDSDauSach();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerSach.setLayoutManager(linearLayoutManager);

        SachAdapter adapter = new SachAdapter(getContext(),
                list,getDSLoaiSach(),sachDAO);
        recyclerSach.setAdapter(adapter);
    }

    private void showdialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater layoutInflater = getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.dialog_themsach, null);
        builder.setView(view);
        EditText edtTenSach = view.findViewById(R.id.edtTenSach);
        EditText edttien = view.findViewById(R.id.edtTien);
        Spinner spnloaisach = view.findViewById(R.id.spnLoaiSach);
        SimpleAdapter simpleAdapter= new SimpleAdapter(getContext(),
                getDSLoaiSach(), android.R.layout.simple_list_item_1,
                new String[]{"tenloai"},new int[]{android.R.id.text1});
        spnloaisach.setAdapter(simpleAdapter);

        builder.setPositiveButton("Thêm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String tensach = edtTenSach.getText().toString();
                int tien = Integer.parseInt(edttien.getText().toString());
                HashMap<String, Object> hs = (HashMap<String, Object>) spnloaisach.getSelectedItem();
                int maloai = (int) hs.get("maloai");
                boolean check = sachDAO.themSachMoi(tensach, tien, maloai);
                if (check) {
                    Toast.makeText(getContext(), "Thêm sách thành công", Toast.LENGTH_SHORT).show();
                    // Sau khi thêm sách, cập nhật dữ liệu trên RecyclerView
                    loaddata();
                } else {
                    Toast.makeText(getContext(), "Thêm không thành công", Toast.LENGTH_SHORT).show();
                }
            }
        });
        builder.setNegativeButton("hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        AlertDialog alertDialog=builder.create();
        alertDialog.show();
    }

    private ArrayList<HashMap<String, Object>> getDSLoaiSach() {
        LoaiSachDAO loaiSachDAO = new LoaiSachDAO(getContext());
        ArrayList<LoaiSach> list = loaiSachDAO.getDSLoaiSach();
        ArrayList<HashMap<String, Object>> listHM = new ArrayList<>();
        for (LoaiSach loaiSach : list) {
            HashMap<String, Object> hs = new HashMap<>();
            hs.put("maloai", loaiSach.getId());
            hs.put("tenloai", loaiSach.getTenLoai());
            listHM.add(hs);
        }
        return listHM;
    }
}