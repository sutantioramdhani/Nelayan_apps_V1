package com.sr.sutantio.nelayan_apps_v1.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.sr.sutantio.nelayan_apps_v1.Input_Barang_Sql;
import com.sr.sutantio.nelayan_apps_v1.R;

public class ProfilFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profil, container, false);
        Button Btn_mulai = (Button) view.findViewById(R.id.BTN_mulai_berjualan);
        Btn_mulai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Input_Barang_Sql.class);
                startActivity(intent);
            }
        });
        return view;
    }

}