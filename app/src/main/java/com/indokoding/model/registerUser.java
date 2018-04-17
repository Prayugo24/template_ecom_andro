package com.indokoding.model;

/**
 * Created by Admin on 20/02/2018.
 */

public class registerUser {
    private String name;
    private String email;
    private String password;
    private String c_password;
    private String phone;
    private String country_code_phone;

    public registerUser(String name, String email, String password1,
                        String password2, String phoneNumber, String country_code_phone) {
        this.name = name;
        this.email = email;
        this.password = password1;
        this.c_password = password2;
        this.phone = phoneNumber;
        this.country_code_phone = country_code_phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getC_password() {
        return c_password;
    }

    public void setC_password(String c_password) {
        this.c_password = c_password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCountry_code_phone() {
        return country_code_phone;
    }

    public void setCountry_code_phone(String country_code_phone) {
        this.country_code_phone = country_code_phone;
    }
}
