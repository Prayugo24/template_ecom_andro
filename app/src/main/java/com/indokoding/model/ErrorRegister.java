package com.indokoding.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Admin on 05/03/2018.
 */

public class ErrorRegister {
    @SerializedName("email")
    @Expose
    private String errorEmail;

    @SerializedName("phone")
    @Expose
    private String errorPhone;

    @SerializedName("c_password")
    @Expose
    private String c_password;

    public String getErrorEmail() {
        return errorEmail;
    }

    public void setErrorEmail(String errorEmail) {
        this.errorEmail = errorEmail;
    }

    public String getErrorPhone() {
        return errorPhone;
    }

    public void setErrorPhone(String errorPhone) {
        this.errorPhone = errorPhone;
    }

    public String getC_password() {
        return c_password;
    }

    public void setC_password(String c_password) {
        this.c_password = c_password;
    }

    @Override
    public String toString() {
        return "ErrorRegister{" +
                "errorEmail='" + errorEmail + '\'' +
                ", errorPhone='" + errorPhone + '\'' +
                ", c_password='" + c_password + '\'' +
                '}';
    }
}
