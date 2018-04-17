package com.indokoding.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Admin on 05/03/2018.
 */

public class DataError {
    @SerializedName("error")
    @Expose
    private String dataError;

    public String getDataError() {
        return dataError;
    }

    public void setDataError(String dataError) {
        this.dataError = dataError;
    }

    @Override
    public String toString() {
        return "DataError{" +
                "dataError='" + dataError + '\'' +
                '}';
    }
}
