package com.sr.sutantio.nelayan_apps_v1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.sr.sutantio.nelayan_apps_v1.fragment.BerandaFragment;
import com.sr.sutantio.nelayan_apps_v1.fragment.IkanFragment;
import com.sr.sutantio.nelayan_apps_v1.fragment.ProfilFragment;
import com.sr.sutantio.nelayan_apps_v1.fragment.TransaksiFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Home extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // kita set default nya Home Fragment
        loadFragment(new BerandaFragment());

// inisialisasi BottomNavigaionView
        BottomNavigationView bottomNavigationView = findViewById(R.id.bn_bawah);

// beri listener pada saat item/menu bottomnavigation terpilih
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
    }

    private boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fl_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }
    // method listener untuk logika pemilihan
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;

        switch (item.getItemId()){
            case R.id.home_menu:
                fragment = new BerandaFragment();
                break;
            case R.id.notif_menu:
                fragment = new IkanFragment();
                break;
            case R.id.transaksi_menu:
                fragment = new TransaksiFragment();
                break;
            case R.id.profile_menu:
                fragment = new ProfilFragment();
                break;
        }
        return loadFragment(fragment);
    }
}
