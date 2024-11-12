package com.example.duantt;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.duantt.FRAGMENT.DoanhThuFragment;
import com.example.duantt.FRAGMENT.NguoiDungFragment;
import com.example.duantt.FRAGMENT.QuanLiHoaDon;
import com.example.duantt.FRAGMENT.QuanLySanPhamFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;


public class MainActivity extends AppCompatActivity {
    DrawerLayout drawerLayout ;
    Toolbar toolbar ;
    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.jl_toolbar);
        bottomNavigationView = findViewById(R.id.jl_btton_nav);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Trang chủ");
        callFragment(new QuanLySanPhamFragment());



        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.bt_quanly){
                    callFragment(new QuanLySanPhamFragment());
                    toolbar.setTitle("Sản phẩm");
                }
                if (item.getItemId() == R.id.bt_doanhthu){
                    callFragment(new DoanhThuFragment());
                    toolbar.setTitle("Doanh thu");
                }
                if (item.getItemId() == R.id.bt_donhang){
                    callFragment(new QuanLiHoaDon());
                    toolbar.setTitle("Hóa đơn");
                }
                if (item.getItemId() == R.id.bt_nguoidung){
                    callFragment(new NguoiDungFragment());
                    toolbar.setTitle("Người dùng");
                }
                return true;
            }


        });



    }


    private void callFragment(Fragment fragment) {
        FragmentTransaction manager = getSupportFragmentManager().beginTransaction();
        manager.replace(R.id.jl_fragment,fragment).commit();
        //   drawerLayout.close();
    }
}
