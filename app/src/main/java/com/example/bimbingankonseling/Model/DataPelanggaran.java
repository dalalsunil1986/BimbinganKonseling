package com.example.bimbingankonseling.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataPelanggaran {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("jenis_pelanggaran")
    @Expose
    private String jenisPelanggaran;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJenisPelanggaran() {
        return jenisPelanggaran;
    }

    public void setJenisPelanggaran(String jenisPelanggaran) {
        this.jenisPelanggaran = jenisPelanggaran;
    }

}
