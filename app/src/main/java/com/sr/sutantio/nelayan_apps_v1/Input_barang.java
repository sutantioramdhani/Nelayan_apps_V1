package com.sr.sutantio.nelayan_apps_v1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class Input_barang extends AppCompatActivity implements View.OnClickListener {
    private EditText ET_kode_barang;
    private EditText ET_nama_barang;
    private EditText ET_harga_barang;
    private EditText ET_deskripsi_barang;
    private ImageView img_barang;
    private Button BTN_simpan_barang;
    private Button Btn_choose;

    private String name;
    private String kode;
    private String harga;
    private String deskripsi;
    // variable yang merefers ke Firebase Realtime Database
    private Uri imgPath;

    private DatabaseReference databaseRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_barang);

        //Inisialisasi dari View
        ET_kode_barang = (EditText) findViewById(R.id.ET_kode_barang);
        ET_nama_barang = (EditText) findViewById(R.id.ET_nama_barang);
        ET_harga_barang = (EditText) findViewById(R.id.ET_harga_barang);
        ET_deskripsi_barang = (EditText) findViewById(R.id.ET_deskripsi_barang);
        img_barang = (ImageView) findViewById(R.id.Tambah_gambar);
        BTN_simpan_barang = (Button) findViewById(R.id.BTN_simpan_barang);
        Btn_choose = (Button) findViewById(R.id.Btn_choose);

        // mengambil referensi ke Firebase Database
        BTN_simpan_barang.setOnClickListener(this);
        Btn_choose.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.BTN_simpan_barang:
                name = ET_nama_barang.getText().toString();
                kode = ET_kode_barang.getText().toString();
                harga = ET_harga_barang.getText().toString();
                deskripsi = ET_deskripsi_barang.getText().toString();
                final StorageReference storageRef = FirebaseStorage
                        .getInstance()
                        .getReference("images");
                databaseRef = FirebaseDatabase
                        .getInstance()
                        .getReference("Barang")
                        .push();

                storageRef.putFile(imgPath)
                        .continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                            @Override
                            public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                                if (!task.isSuccessful()) {
                                    throw task.getException();
                                }
                                return storageRef.getDownloadUrl();
                            }
                        })
                        .addOnCompleteListener(new OnCompleteListener<Uri>() {
                            @Override
                            public void onComplete(@NonNull Task<Uri> task) {
                                if (task.isSuccessful()) {
                                    Uri downloadUri = task.getResult();
                                    databaseRef.child("image").setValue(downloadUri.toString());
                                    databaseRef.child("name").setValue(name);
                                    databaseRef.child("kode").setValue(kode);
                                    databaseRef.child("harga").setValue(name);
                                    databaseRef.child("deskripsi").setValue(name);


                                    Toast.makeText(Input_barang.this, "Add Image Successfully", Toast.LENGTH_SHORT).show();

                                    finish();
                                }
                            }
                        })
                        .addOnFailureListener(this, new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.d("AddImageActivity", e.getMessage());
                            }
                        });
                break;
            case R.id.Btn_choose:
                Intent iImg = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(iImg, 0);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            imgPath = data.getData();
        }
    }



    }
