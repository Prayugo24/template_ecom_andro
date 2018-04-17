package com.indokoding.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Admin on 18/02/2018.
 */

public class FeedLogin {
    @SerializedName("data")
    @Expose
    private DataToken data;

    public DataToken getData() {
        return data;
    }

    public void setData(DataToken data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "FeedLogin{" +
                "data=" + data +
                '}';
    }
}
