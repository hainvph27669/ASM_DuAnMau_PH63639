package fpoly.hainvph63639.nguyenvanhai_ph63639_duanmau.Adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import fpoly.hainvph63639.nguyenvanhai_ph63639_duanmau.DAO.ThanhVienDAO;
import fpoly.hainvph63639.nguyenvanhai_ph63639_duanmau.Model.ThanhVien;
import fpoly.hainvph63639.nguyenvanhai_ph63639_duanmau.R;


public class ThanhVienAdapter extends RecyclerView.Adapter<ThanhVienAdapter.ViewHoder> {

    private Context context;
    private ArrayList<ThanhVien> list;

    private ThanhVienDAO thanhVienDAO;

    public ThanhVienAdapter(Context context, ArrayList<ThanhVien> list, ThanhVienDAO thanhVienDAO){
        this.context = context;
        this.list = list;
        this.thanhVienDAO = thanhVienDAO;
    }

    @NonNull
    @Override
    public ViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_rcv_thanhvien,parent,false);
        return new ViewHoder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoder holder, int position) {
        holder.txtMatv.setText("Mã TV: " + list.get(position).getMatv());
        holder.txtHoten.setText("Họ tên: " + list.get(position).getHoten());
        holder.txtNamSinh.setText("Năm sinh: " + list.get(position).getNamsinh());


        holder.ivEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogCapNhap(list.get(holder.getAdapterPosition()));
            }
        });



        holder.ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int check = thanhVienDAO.xoaThongTinTV(list.get(holder.getAdapterPosition()).getMatv());
                switch (check){
                    case 1:
                        Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                        loadData();
                        break;
                    case 0:
                        Toast.makeText(context, "Xóa thất bại", Toast.LENGTH_SHORT).show();
                        break;
                    case -1:
                        Toast.makeText(context, "Thành viên này không xóa được", Toast.LENGTH_SHORT).show();
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

    public class ViewHoder extends RecyclerView.ViewHolder{

        TextView txtMatv, txtHoten, txtNamSinh;
        ImageView ivEdit, ivDelete;
        public ViewHoder(@NonNull View itemView) {
            super(itemView);

            txtMatv = itemView.findViewById(R.id.txtmMatv);
            txtNamSinh = itemView.findViewById(R.id.txtmNamsinh);
            txtHoten = itemView.findViewById(R.id.txtmHoten);
            ivEdit = itemView.findViewById(R.id.ivEdit);
            ivDelete = itemView.findViewById(R.id.ivDelete);
        }
    }

    private void showDialogCapNhap(ThanhVien thanhVien){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater layoutInflater = ((Activity)context).getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.dialog_chinhtv, null);
        builder.setView(view);

        TextView txtmatvS = view.findViewById(R.id.txtmatvS);
        EditText edtHotenS = view.findViewById(R.id.edtHotenS);
        EditText edtNamsinhS = view.findViewById(R.id.edtNamsinhS);

        txtmatvS.setText("Mã TV: " + thanhVien.getMatv());
        edtHotenS.setText(  thanhVien.getHoten());
        edtNamsinhS.setText(thanhVien.getNamsinh());

        builder.setNegativeButton("Cập nhập", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String hoten = edtHotenS.getText().toString();
                String namsinh = edtNamsinhS.getText().toString();
                int id = thanhVien.getMatv();

                boolean check = thanhVienDAO.capnhapThongTin(id, hoten, namsinh);
                if(check){
                    Toast.makeText(context, "Cập nhập thông tin thành công", Toast.LENGTH_SHORT).show();
                    loadData();
                }else{
                    Toast.makeText(context, "Cập nhập thông tin thất bại", Toast.LENGTH_SHORT).show();
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

    private  void loadData(){
        list.clear();
        list = thanhVienDAO.getDSThanhVien();
        notifyDataSetChanged();
    }
}
