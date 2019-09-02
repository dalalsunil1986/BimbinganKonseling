package com.example.bimbingankonseling.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseSiswa {

    @SerializedName("data")
    @Expose
    private List<DataSiswa> data = null;

    public List<DataSiswa> getData() {
        return data;
    }

    public void setData(List<DataSiswa> data) {
        this.data = data;
    }

}
