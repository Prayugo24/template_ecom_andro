package com.indokoding.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Admin on 20/02/2018.
 */

public class DeliveryAddress {
    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("name_address")
    @Expose
    private String nameAddress;

    @SerializedName("phone_number")
    @Expose
    private String phoneNumber;

    @SerializedName("city")
    @Expose
    private String city;

    @SerializedName("block")
    @Expose
    private String block;

    @SerializedName("street")
    @Expose
    private String street;

    @SerializedName("house_number")
    @Expose
    private String houseNumber;

    @SerializedName("apartment")
    @Expose
    private String apartment;

    @SerializedName("address_apartment")
    @Expose
    private String addressApartement;

    @SerializedName("floor")
    @Expose
    private String floor;

    @SerializedName("jadda")
    @Expose
    private String jadda;

    @SerializedName("descriptions")
    @Expose
    private String description;

    @SerializedName("primary")
    @Expose
    private String primary;

    @SerializedName("user_id")
    @Expose
    private String userId;

    @SerializedName("created_at")
    @Expose
    private String createdAt;

    @SerializedName("updated_at")
    @Expose
    private String updateAt;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNameAddress() {
        return nameAddress;
    }

    public void setNameAddress(String nameAddress) {
        this.nameAddress = nameAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getBlock() {
        return block;
    }

    public void setBlock(String block) {
        this.block = block;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getApartment() {
        return apartment;
    }

    public void setApartment(String apartment) {
        this.apartment = apartment;
    }

    public String getAddressApartement() {
        return addressApartement;
    }

    public void setAddressApartement(String addressApartement) {
        this.addressApartement = addressApartement;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getJadda() {
        return jadda;
    }

    public void setJadda(String jadda) {
        this.jadda = jadda;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrimary() {
        return primary;
    }

    public void setPrimary(String primary) {
        this.primary = primary;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    @Override
    public String toString() {
        return "DeliveryAddress{" +
                "id='" + id + '\'' +
                ", nameAddress='" + nameAddress + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", city='" + city + '\'' +
                ", block='" + block + '\'' +
                ", street='" + street + '\'' +
                ", houseNumber='" + houseNumber + '\'' +
                ", apartment='" + apartment + '\'' +
                ", addressApartement='" + addressApartement + '\'' +
                ", floor='" + floor + '\'' +
                ", jadda='" + jadda + '\'' +
                ", description='" + description + '\'' +
                ", primary='" + primary + '\'' +
                ", userId='" + userId + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", updateAt='" + updateAt + '\'' +
                '}';
    }
}

