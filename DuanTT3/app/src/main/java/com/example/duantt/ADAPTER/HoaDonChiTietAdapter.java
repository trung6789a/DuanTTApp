package com.example.duantt.ADAPTER;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.duantt.DTO.HoaDonChiTiet;
import com.example.duantt.DTO.SanPham;
import com.example.duantt.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class HoaDonChiTietAdapter extends RecyclerView.Adapter<HoaDonChiTietAdapter.HolderHoaDonChiTietAdapter> {

    ArrayList<HoaDonChiTiet> list ;
    Context context ;

    public HoaDonChiTietAdapter(ArrayList<HoaDonChiTiet> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public HolderHoaDonChiTietAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_hoa_don_chi_tiet,parent,false);
        return new HolderHoaDonChiTietAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderHoaDonChiTietAdapter holder, int position) {




        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        reference.child("SanPham").child(list.get(position).getIdSP()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){

                    SanPham sanPham = snapshot.getValue(SanPham.class);



                    Glide.with(context).load(sanPham.getAnhSP()).into(holder.imgAnhHoaDonChiTiet);
                    holder.tvTenSpHoaDonChiTiet.setText(sanPham.getTenSP());
                    holder.tvGiaHoaDonCHiTiet.setText(sanPham.getGiaSP()) ;
                    holder.tvSoLuongHoaDonChiTiet.setText(list.get(position).getSoLuong()) ;
                    holder.tvTongTienHoaDonCHiTiet.setText(String.valueOf(Integer.parseInt(list.get(position).getSoLuong()) * Integer.parseInt(sanPham.getGiaSP()) ));

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class HolderHoaDonChiTietAdapter extends RecyclerView.ViewHolder {

        ImageView imgAnhHoaDonChiTiet ;
        TextView tvTenSpHoaDonChiTiet,tvSoLuongHoaDonChiTiet,tvGiaHoaDonCHiTiet,tvTongTienHoaDonCHiTiet;

        public HolderHoaDonChiTietAdapter(@NonNull View itemView) {
            super(itemView);
            imgAnhHoaDonChiTiet = itemView.findViewById(R.id.imgAnhHoaDonChiTiet) ;
            tvTenSpHoaDonChiTiet = itemView.findViewById(R.id.tvTenSpHoaDonChiTiet);
            tvSoLuongHoaDonChiTiet = itemView.findViewById(R.id.tvSoLuongHoaDonChiTiet);
            tvGiaHoaDonCHiTiet = itemView.findViewById(R.id.tvGiaHoaDonCHiTiet);
            tvTongTienHoaDonCHiTiet = itemView.findViewById(R.id.tvTongTienHoaDonCHiTiet);
        }
    }


}
