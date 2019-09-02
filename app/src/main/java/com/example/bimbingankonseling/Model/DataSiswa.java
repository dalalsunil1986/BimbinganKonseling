package com.example.bimbingankonseling.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataSiswa {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("nama")
    @Expose
    private String nama;
    @SerializedName("nis")
    @Expose
    private String nis;
    @SerializedName("kelas")
    @Expose
    private String kelas;
    @SerializedName("jurusan")
    @Expose
    private String jurusan;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("notelp")
    @Expose
    private String notelp;
    @SerializedName("nama_ayah")
    @Expose
    private String namaAyah;
    @SerializedName("nama_ibu")
    @Expose
    private String namaIbu;
    @SerializedName("alamat")
    @Expose
    private String alamat;
    @SerializedName("notelp_orangtua")
    @Expose
    private String notelpOrangtua;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNis() {
        return nis;
    }

    public void setNis(String nis) {
        this.nis = nis;
    }

    public String getKelas() {
        return kelas;
    }

    public void setKelas(String kelas) {
        this.kelas = kelas;
    }

    public String getJurusan() {
        return jurusan;
    }

    public void setJurusan(String jurusan) {
        this.jurusan = jurusan;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNotelp() {
        return notelp;
    }

    public void setNotelp(String notelp) {
        this.notelp = notelp;
    }

    public String getNamaAyah() {
        return namaAyah;
    }

    public void setNamaAyah(String namaAyah) {
        this.namaAyah = namaAyah;
    }

    public String getNamaIbu() {
        return namaIbu;
    }

    public void setNamaIbu(String namaIbu) {
        this.namaIbu = namaIbu;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getNotelpOrangtua() {
        return notelpOrangtua;
    }

    public void setNotelpOrangtua(String notelpOrangtua) {
        this.notelpOrangtua = notelpOrangtua;
    }
}
