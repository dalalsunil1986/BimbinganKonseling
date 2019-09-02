package com.example.bimbingankonseling.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataPoin {
    private String jenis_pelanggaran;
    private String poin;

    public DataPoin(String jenis_pelanggaran, String poin) {
        this.jenis_pelanggaran = jenis_pelanggaran;
        this.poin = poin;
    }

    public String getJenis_pelanggaran() {
        return jenis_pelanggaran;
    }

    public void setJenis_pelanggaran(String jenis_pelanggaran) {
        this.jenis_pelanggaran = jenis_pelanggaran;
    }

    public String getPoin() {
        return poin;
    }

    public void setPoin(String poin) {
        this.poin = poin;
    }
}
