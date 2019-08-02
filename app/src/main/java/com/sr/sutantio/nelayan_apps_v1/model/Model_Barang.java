package com.sr.sutantio.nelayan_apps_v1.model;

public class Model_Barang {
    private String kode_barang;
    private String nama_barang;
    private String harga_barang;
    private String img_barang;
    private String deskripsi_barang;
    private String nama_penjual;
    private String no_telp;
    public Model_Barang(){

    }
    public Model_Barang(String name, String kode, String harga, String deskripsi, String nama_p, String telp, String image) {
        this.nama_barang = name;
        this.kode_barang = kode;
        this.harga_barang= harga;
        this.deskripsi_barang= deskripsi;
        this.nama_penjual= nama_p;
        this.no_telp = telp;
        this.img_barang = image;
    }

    public String getNama_penjual() {
        return nama_penjual;
    }

    public void setNama_penjual(String nama_penjual) {
        this.nama_penjual = nama_penjual;
    }

    public String getNo_telp() {
        return no_telp;
    }

    public void setNo_telp(String no_telp) {
        this.no_telp = no_telp;
    }



    public String getKode_barang() {
        return kode_barang;
    }

    public void setKode_barang(String kode_barang) {
        this.kode_barang = kode_barang;
    }


    public String getNama_barang() {
        return nama_barang;
    }

    public void setNama_barang(String nama_barang) {
        this.nama_barang = nama_barang;
    }

    public String getHarga_barang() {
        return harga_barang;
    }

    public void setHarga_barang(String harga_barang) {
        this.harga_barang = harga_barang;
    }

    public String getImg_barang() {
        return img_barang;
    }

    public void setImg_barang(String img_barang) {
        this.img_barang = img_barang;
    }

    public String getDeskripsi_barang() {
        return deskripsi_barang;
    }

    public void setDeskripsi_barang(String deskripsi_barang) {
        this.deskripsi_barang = deskripsi_barang;
    }
}
