package fpoly.hainvph63639.nguyenvanhai_ph63639_duanmau.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;



import java.util.ArrayList;

import fpoly.hainvph63639.nguyenvanhai_ph63639_duanmau.DAO.LoaiSachDAO;
import fpoly.hainvph63639.nguyenvanhai_ph63639_duanmau.Model.LoaiSach;
import fpoly.hainvph63639.nguyenvanhai_ph63639_duanmau.R;

public class LoaiSachAdapter extends RecyclerView.Adapter<LoaiSachAdapter.ViewHoder> {

    private Context context;
    private ArrayList<LoaiSach> list;

    public LoaiSachAdapter(Context context, ArrayList<LoaiSach> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_rcv_loaisach, parent, false);

        return new ViewHoder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoder holder, int position) {
        holder.txtTenLoai.setText("Tên loại:"+list.get(position).getTenLoai());
        holder.txtMaloai.setText("Mã loại: "+String.valueOf(list.get(position).getId()));
        holder.ivDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoaiSachDAO loaiSachDAO = new LoaiSachDAO(context);

                int check = loaiSachDAO.xoaLoaiSach(list.get(holder.getAdapterPosition()).getId());

                switch (check){
                    case 1:
                        list.clear();
                        list = loaiSachDAO.getDSLoaiSach();
                        notifyDataSetChanged();
                        break;
                    case -1:
                        Toast.makeText(context, "Không thể xóa sách này", Toast.LENGTH_SHORT).show();
                        break;
                    case 0:
                        Toast.makeText(context, "Xóa không thành công", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;
                }

            }
        });
    }

    @Override
    public int getItemCount() {

        return list.size();
    }

    public class ViewHoder extends RecyclerView.ViewHolder {
        TextView txtMaloai, txtTenLoai;

        ImageView ivDel;
        public ViewHoder(@NonNull View itemView) {
            super(itemView);
            txtMaloai = itemView.findViewById(R.id.txtMaloai);
            txtTenLoai = itemView.findViewById(R.id.txtTenloai);
            ivDel = itemView.findViewById(R.id.ivDel);
        }
    }
}
