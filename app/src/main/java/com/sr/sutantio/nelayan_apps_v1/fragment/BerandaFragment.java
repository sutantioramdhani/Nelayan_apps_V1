package com.sr.sutantio.nelayan_apps_v1.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sr.sutantio.nelayan_apps_v1.R;
import com.sr.sutantio.nelayan_apps_v1.model.Model_Barang;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class BerandaFragment extends Fragment {

    DatabaseReference barangDatabase;
    RecyclerView rvBarang;

    public BerandaFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_beranda, container, false);
        barangDatabase = FirebaseDatabase.getInstance().getReference("Barang");
        rvBarang = view.findViewById(R.id.RecyclerView_Barang);
        rvBarang.setHasFixedSize(true);
        rvBarang.setLayoutManager(new LinearLayoutManager(getContext()));

        tampilDataBarang();

        return view;
    }
//    public void onCreate(Bundle savedInstanceState){
//        super.onCreate(savedInstanceState);
//        ImageView cat = (ImageView)getView().findViewById(R.id.Chat_beranda);
//        ImageView notifi = (ImageView)getView().findViewById(R.id.notifikasi_beranda);
//        ImageView cart = (ImageView)getView().findViewById(R.id.chart);
//        cat.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent catintent = new Intent(getActivity(), Chatting.class);
//                startActivity(catintent);
//            }
//        });
//        notifi.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent notifiintent = new Intent(getActivity(), Notifikasi.class);
//                startActivity(notifiintent);
//            }
//        });
//        cart.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent cartintent = new Intent(getActivity(), Shopping_Cart.class);
//                startActivity(cartintent);
//            }
//        });
//    }



    private void tampilDataBarang() {

        FirebaseRecyclerAdapter<Model_Barang, BarangViewHolder> adapter = new FirebaseRecyclerAdapter<Model_Barang, BarangViewHolder>(
                Model_Barang.class,
                R.layout.item_barang,
                BarangViewHolder.class,
                barangDatabase
        ) {
            @Override
            protected void populateViewHolder(BarangViewHolder barangViewHolder, Model_Barang model_barang, int i) {
                barangViewHolder.setDetails(getContext().getApplicationContext(), model_barang.getNama_barang(),
                        model_barang.getDeskripsi_barang(), model_barang.getHarga_barang(), model_barang.getImg_barang(),
                        model_barang.getKode_barang(),model_barang.getNama_penjual(),model_barang.getNo_telp());
            }
        };
        rvBarang.setAdapter(adapter);

    }

    public static class BarangViewHolder extends RecyclerView.ViewHolder {
        View view;

        public BarangViewHolder(@NonNull View itemView) {
            super(itemView);

            view = itemView;
        }

        public void setDetails(Context ctx, String namaBarang, String deskripsiBarang, String harga_barang, String img_barang, String kode_barang, String namaPenjual, String no_telp) {

            TextView txt_nama_barang = view.findViewById(R.id.txt_nama_barang);
//            TextView txt_deskripsi_barang = view.findViewById(R.id.)
            TextView txt_harga_barang = view.findViewById(R.id.txt_harga_barang);
            TextView txt_no_telp =view.findViewById(R.id.txt_notelp);
            ImageView img_barang_beranda = view.findViewById(R.id.img_barang);

            txt_nama_barang.setText(namaBarang);
            txt_harga_barang.setText(harga_barang);
            txt_no_telp.setText(no_telp);

            Glide.with(ctx).load(img_barang).into(img_barang_beranda);

        }
    }
}
