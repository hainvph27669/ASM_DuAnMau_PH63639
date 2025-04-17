package fpoly.hainvph63639.nguyenvanhai_ph63639_duanmau.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import fpoly.hainvph63639.nguyenvanhai_ph63639_duanmau.Adapter.Top10Adapter;
import fpoly.hainvph63639.nguyenvanhai_ph63639_duanmau.DAO.ThongKeDAO;
import fpoly.hainvph63639.nguyenvanhai_ph63639_duanmau.Model.Sach;
import fpoly.hainvph63639.nguyenvanhai_ph63639_duanmau.R;


public class ThongKeTop10_Fragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_thong_ke_top10_,container,false);

        RecyclerView recyclerViewTop10 = view.findViewById(R.id.rcvTop10);

        ThongKeDAO thongKeDAO = new ThongKeDAO(getContext());
        ArrayList<Sach> list = thongKeDAO.getTop10();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerViewTop10.setLayoutManager(linearLayoutManager);
        Top10Adapter adapter = new Top10Adapter(getContext(),list);
        recyclerViewTop10.setAdapter(adapter);

        return view;
    }
}