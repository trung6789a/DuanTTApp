package com.example.duantt.FRAGMENT;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duantt.ADAPTER.NguoiDungAdapter;
import com.example.duantt.DTO.NguoiDung;
import com.example.duantt.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;



public class NguoiDungFragment extends Fragment {

   View view;
   RecyclerView rcNguoiDung;
   ArrayList<NguoiDung> list = new ArrayList<>();
   NguoiDungAdapter nguoiDungAdapter ;

   DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_nguoi_dung, container, false);
        anhXa();
        setListNguoiDung();
        rcNguoiDung.setLayoutManager(new LinearLayoutManager(getActivity()));
        nguoiDungAdapter = new NguoiDungAdapter(list,getActivity());

        rcNguoiDung.setAdapter(nguoiDungAdapter);

        // Inflate the layout for this fragment
        return view;
    }

    private void anhXa (){
        rcNguoiDung = view.findViewById(R.id.rcNguoiDung);

    }

    private void setListNguoiDung(){
        reference.child("NguoiDung").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    list.clear();
                    for (DataSnapshot dataND : snapshot.getChildren()){
                        NguoiDung nguoiDung = dataND.getValue(NguoiDung.class);
                        list.add(nguoiDung);
                    }
                    nguoiDungAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}