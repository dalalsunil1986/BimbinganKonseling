package com.example.bimbingankonseling.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponsePoinSiswa {

    @SerializedName("data")
    @Expose
    private List<DataPoinSiswa> data = null;

    public List<DataPoinSiswa> getData() {
        return data;
    }

    public void setData(List<DataPoinSiswa> data) {
        this.data = data;
    }


}
