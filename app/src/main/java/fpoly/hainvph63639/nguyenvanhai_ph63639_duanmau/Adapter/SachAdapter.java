package fpoly.hainvph63639.nguyenvanhai_ph63639_duanmau.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;



import java.util.ArrayList;
import java.util.HashMap;

import fpoly.hainvph63639.nguyenvanhai_ph63639_duanmau.DAO.SachDAO;
import fpoly.hainvph63639.nguyenvanhai_ph63639_duanmau.Model.Sach;
import fpoly.hainvph63639.nguyenvanhai_ph63639_duanmau.R;

public class SachAdapter extends RecyclerView.Adapter<SachAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Sach> list;
    private ArrayList<HashMap<String,Object>>listHM;
    private SachDAO sachDAO;

    public SachAdapter(Context context, ArrayList<Sach> list,
                       ArrayList<HashMap<String, Object>> listHM, SachDAO sachDAO) {
        this.context = context;
        this.list = list;
        this.listHM = listHM;
        this.sachDAO = sachDAO;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = ((Activity)context).getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.item_rec_sach,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.txtMaSach.setText("Mã Sách: " +list.get(position).getMasach());
        holder.txtTenSach.setText("Tên Sách: " +list.get(position).getTensach());
        holder.txtGiaThue.setText("Giá Thuê: " +list.get(position).getGiathue());
        holder.txtMaLoai.setText("Mã Loại: " +list.get(position).getMaloai());
        holder.txtTenLoai.setText("Tên Loại: " +list.get(position).getTenloai());
        holder.ivEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDigLog(list.get(holder.getAdapterPosition()));
            }
        });
        holder.ivDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int check = sachDAO.xoaSach(list.get(holder.getAdapterPosition()).getMasach());
                switch ( check){
                    case 1:
                        Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                        loaddata();
                        break;
                    case 0:
                        Toast.makeText(context, "Xóa không thành công", Toast.LENGTH_SHORT).show();
                        break;
                    case -1:
                        Toast.makeText(context, "không có được sách này vì sách có trong phiếu mượn", Toast.LENGTH_SHORT).show();
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

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivEdit ,ivDel;
        TextView txtMaSach, txtTenSach, txtGiaThue, txtMaLoai, txtTenLoai;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtMaSach = itemView.findViewById(R.id.txtMaSach);
            txtTenSach = itemView.findViewById(R.id.txtTenSach);
            txtGiaThue = itemView.findViewById(R.id.txtGiaThue);
            txtMaLoai = itemView.findViewById(R.id.txtMaLoai);
            txtTenLoai = itemView.findViewById(R.id.txtTenLoai);
            ivDel= itemView.findViewById(R.id.ivDel);
            ivEdit=itemView.findViewById(R.id.ivEdit);
        }
    }
    private void showDigLog(Sach sach){
//alert diglog
        AlertDialog.Builder builder =new AlertDialog.Builder(context);
        LayoutInflater inflater =((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_suasach,null);
        builder.setView(view);
        EditText edtTenSach = view.findViewById(R.id.edtTenSach);
        EditText edtTien = view.findViewById(R.id.edtTien);
        TextView txtMaSach = view.findViewById(R.id.txtMaSach);
        Spinner spnLoaiSach =view.findViewById(R.id.spnLoaiSach);
        txtMaSach.setText("Mã sách: "+sach.getMasach());
        edtTenSach.setText(sach.getTensach());
        edtTien.setText(String.valueOf(sach.getGiathue()));
        SimpleAdapter simpleAdapter = new SimpleAdapter(context,
                listHM, android.R.layout.simple_list_item_1,
                new String[]{"tenloai"},new int[]{android.R.id.text1});
        spnLoaiSach.setAdapter(simpleAdapter);
        int index = 0;
        int postion = -1;
        for (HashMap<String,Object>item :listHM){
            if ((int)item.get("maloai")==sach.getMaloai()){
                postion= index;
            }
            index++;
        }
        spnLoaiSach.setSelection(postion);
        builder.setNegativeButton("Cập nhật", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String  tensach= edtTenSach.getText().toString();
                int tien = Integer.parseInt(edtTien.getText().toString());
                HashMap<String,Object>hs = (HashMap<String, Object>) spnLoaiSach.getSelectedItem();
                int maloai =(int) hs.get("maloai");
                boolean check = sachDAO.capNhatThongTinSach(sach.getMasach(),tensach,tien,maloai);
                if (check){
                    Toast.makeText(context, "Cập nhật sách thành công", Toast.LENGTH_SHORT).show();
                    loaddata();
                }else {
                    Toast.makeText(context, "cập nhật không thành công", Toast.LENGTH_SHORT).show();
                }
            }
        });
        builder.setPositiveButton("HỦY", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        AlertDialog alertDialog =builder.create();
        alertDialog.show();
    }
    private void loaddata(){
        list.clear();
        list= sachDAO.getDSDauSach();
        notifyDataSetChanged();
    }
}