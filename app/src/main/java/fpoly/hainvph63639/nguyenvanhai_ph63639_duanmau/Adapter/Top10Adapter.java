package fpoly.hainvph63639.nguyenvanhai_ph63639_duanmau.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;



import java.util.ArrayList;

import fpoly.hainvph63639.nguyenvanhai_ph63639_duanmau.Model.Sach;
import fpoly.hainvph63639.nguyenvanhai_ph63639_duanmau.R;


public class Top10Adapter extends RecyclerView.Adapter<Top10Adapter.ViewHoder> {

    private Context context;
    private ArrayList<Sach> list;

    public Top10Adapter(Context context, ArrayList<Sach> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_rcv_top10, parent,false);
        return new ViewHoder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoder holder, int position) {
        holder.txtMasach.setText("Thứ tự:"+String.valueOf(list.get(position).getMasach()));
        holder.txtTenSach.setText("Tên sách: "+list.get(position).getTensach());
        holder.txtSLMuon.setText("Số lượng đã mượn: "+String.valueOf(list.get(position).getSldamuon()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHoder extends RecyclerView.ViewHolder{

        TextView txtMasach, txtTenSach, txtSLMuon;
        public ViewHoder(@NonNull View itemView) {
            super(itemView);
            txtMasach = itemView.findViewById(R.id.txtMaSach);
            txtTenSach = itemView.findViewById(R.id.txtTenSach);
            txtSLMuon = itemView.findViewById(R.id.txtSLMuon);
        }
    }

    ;
}
