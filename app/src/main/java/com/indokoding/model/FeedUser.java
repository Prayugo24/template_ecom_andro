package com.indokoding.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Admin on 20/02/2018.
 */

public class FeedUser {
    @SerializedName("data")
    @Expose
    private DataProfil dataProfil;

    public DataProfil getDataProfil() {
        return dataProfil;
    }

    public void setDataProfil(DataProfil dataProfil) {
        this.dataProfil = dataProfil;
    }

    @Override
    public String toString() {
        return "FeedUser{" +
                "dataProfil=" + dataProfil +
                '}';
    }
}
