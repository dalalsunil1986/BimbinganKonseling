package com.example.bimbingankonseling.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataSP {
    private String namaSP;

    public DataSP(String namaSP) {
        this.namaSP = namaSP;
    }

    public String getNamaSP() {
        return namaSP;
    }

    public void setNamaSP(String namaSP) {
        this.namaSP = namaSP;
    }
}
