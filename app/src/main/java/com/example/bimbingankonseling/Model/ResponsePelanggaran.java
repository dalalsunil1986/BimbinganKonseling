package com.example.bimbingankonseling.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponsePelanggaran {

    @SerializedName("data")
    @Expose
    private List<DataPelanggaran> data = null;

    public List<DataPelanggaran> getData() {
        return data;
    }

    public void setData(List<DataPelanggaran> data) {
        this.data = data;
    }

}
