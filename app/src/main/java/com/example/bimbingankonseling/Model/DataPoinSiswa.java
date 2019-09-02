package com.example.bimbingankonseling.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataPoinSiswa {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("id_siswa")
    @Expose
    private String idSiswa;
    @SerializedName("id_pelanggaran")
    @Expose
    private String idPelanggaran;
    @SerializedName("poin")
    @Expose
    private String poin;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdSiswa() {
        return idSiswa;
    }

    public void setIdSiswa(String idSiswa) {
        this.idSiswa = idSiswa;
    }

    public String getIdPelanggaran() {
        return idPelanggaran;
    }

    public void setIdPelanggaran(String idPelanggaran) {
        this.idPelanggaran = idPelanggaran;
    }

    public String getPoin() {
        return poin;
    }

    public void setPoin(String poin) {
        this.poin = poin;
    }
}
