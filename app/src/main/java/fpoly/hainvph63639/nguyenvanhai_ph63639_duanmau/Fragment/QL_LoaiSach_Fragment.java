package fpoly.hainvph63639.nguyenvanhai_ph63639_duanmau.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import fpoly.hainvph63639.nguyenvanhai_ph63639_duanmau.Adapter.LoaiSachAdapter;
import fpoly.hainvph63639.nguyenvanhai_ph63639_duanmau.DAO.LoaiSachDAO;
import fpoly.hainvph63639.nguyenvanhai_ph63639_duanmau.Model.LoaiSach;
import fpoly.hainvph63639.nguyenvanhai_ph63639_duanmau.R;

public class QL_LoaiSach_Fragment extends Fragment {
    RecyclerView recyclerViewLS;
    LoaiSachDAO dao;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_q_l__loai_sach_, container, false);

        recyclerViewLS = view.findViewById(R.id.rcvLoaiSach);
        EditText edtLoaiSach = view.findViewById(R.id.edtLoaiSach);
        Button btnThem = view.findViewById(R.id.btnThem);
        dao = new LoaiSachDAO(getContext());


        dao = new LoaiSachDAO(getContext());

        loadData();


        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenLoai = edtLoaiSach.getText().toString();

                if (dao.themLoaiSach(tenLoai)) {
                    loadData();
                    edtLoaiSach.setText("");
                } else {
                    Toast.makeText(getContext(), "Thêm loại sách không thành công", Toast.LENGTH_SHORT).show();
                }

            }
        });
        return view;
    }

    private void loadData() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerViewLS.setLayoutManager(linearLayoutManager);

        ArrayList<LoaiSach> list = dao.getDSLoaiSach();
        LoaiSachAdapter adapter = new LoaiSachAdapter(getContext(), list);
        recyclerViewLS.setAdapter(adapter);

    }

}