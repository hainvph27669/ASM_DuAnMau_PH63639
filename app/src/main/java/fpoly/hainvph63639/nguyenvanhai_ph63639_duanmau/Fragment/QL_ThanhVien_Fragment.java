package fpoly.hainvph63639.nguyenvanhai_ph63639_duanmau.Fragment;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import fpoly.hainvph63639.nguyenvanhai_ph63639_duanmau.Adapter.ThanhVienAdapter;
import fpoly.hainvph63639.nguyenvanhai_ph63639_duanmau.DAO.ThanhVienDAO;
import fpoly.hainvph63639.nguyenvanhai_ph63639_duanmau.Model.ThanhVien;
import fpoly.hainvph63639.nguyenvanhai_ph63639_duanmau.R;


public class QL_ThanhVien_Fragment extends Fragment {
    ThanhVienDAO thanhVienDAO;
    RecyclerView recyclerThanhVien;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_q_l__thanh_vien_
                ,container,false);

        recyclerThanhVien = view.findViewById(R.id.recThanhVien);
        FloatingActionButton floatAdd = view.findViewById(R.id.floatAdd);

        thanhVienDAO = new ThanhVienDAO(getContext());

        loadData();

        floatAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });
        return view;

    }

    private void loadData(){
        ArrayList<ThanhVien> list = thanhVienDAO.getDSThanhVien();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerThanhVien.setLayoutManager(linearLayoutManager);
        ThanhVienAdapter adapter = new ThanhVienAdapter(getContext(),list, thanhVienDAO);
        recyclerThanhVien.setAdapter(adapter);

    }

    private void showDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_themtv,null);
        builder.setView(view);

        EditText edtHoten = view.findViewById(R.id.edtHotentv);
        EditText edtNamsinh = view.findViewById(R.id.edtNamsinhtv);

        builder.setNegativeButton("Thêm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String hoten = edtHoten.getText().toString();
                String namsinh = edtNamsinh.getText().toString();

                boolean check = thanhVienDAO.themThanhvien(hoten,namsinh);
                if(check){
                    Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                    loadData();

                }else{
                    Toast.makeText(getContext(), "Thêm thất bại", Toast.LENGTH_SHORT).show();

                }
            }
        });

        builder.setPositiveButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

}