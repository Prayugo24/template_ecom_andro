package com.indokoding.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Admin on 05/03/2018.
 */

public class DataRegister {
    @SerializedName("data")
    @Expose
    private String dataRegister;

    public String getDataRegister() {
        return dataRegister;
    }

    public void setDataRegister(String dataRegister) {
        this.dataRegister = dataRegister;
    }

    @Override
    public String toString() {
        return "DataRegister{" +
                "dataRegister='" + dataRegister + '\'' +
                '}';
    }
}
