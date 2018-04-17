package com.indokoding.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Admin on 20/02/2018.
 */

public class DataProfil {
    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("surname")
    @Expose
    private String surname;

    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("mobile_number")
    @Expose
    private String mobileNumber;

    @SerializedName("uploads_dir")
    @Expose
    private String upliadDir;

    @SerializedName("is_shop")
    @Expose
    private String isShop;

    @SerializedName("created_at")
    @Expose
    private String createdAt;

    @SerializedName("updated_at")
    @Expose
    private String updateAt;

    @SerializedName("fb_uid")
    @Expose
    private String fbUId;

    @SerializedName("google_uid")
    @Expose
    private String googleUId;

    @SerializedName("deliveryaddress")
    @Expose
    private DeliveryAddress deliveryAddress;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getUpliadDir() {
        return upliadDir;
    }

    public void setUpliadDir(String upliadDir) {
        this.upliadDir = upliadDir;
    }

    public String getIsShop() {
        return isShop;
    }

    public void setIsShop(String isShop) {
        this.isShop = isShop;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(String updateAt) {
        this.updateAt = updateAt;
    }

    public String getFbUId() {
        return fbUId;
    }

    public void setFbUId(String fbUId) {
        this.fbUId = fbUId;
    }

    public String getGoogleUId() {
        return googleUId;
    }

    public void setGoogleUId(String googleUId) {
        this.googleUId = googleUId;
    }

    public DeliveryAddress getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(DeliveryAddress deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    @Override
    public String toString() {
        return "DataProfil{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", mobileNumber='" + mobileNumber + '\'' +
                ", upliadDir='" + upliadDir + '\'' +
                ", isShop='" + isShop + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", updateAt='" + updateAt + '\'' +
                ", fbUId='" + fbUId + '\'' +
                ", googleUId='" + googleUId + '\'' +
                ", deliveryAddress=" + deliveryAddress +
                '}';
    }
}
