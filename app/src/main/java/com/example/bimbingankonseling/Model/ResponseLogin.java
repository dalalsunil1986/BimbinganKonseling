package com.example.bimbingankonseling.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseLogin {

    @SerializedName("data")
    @Expose
    private DataLogin data;

    public DataLogin getData() {
        return data;
    }

    public void setData(DataLogin data) {
        this.data = data;
    }

}
