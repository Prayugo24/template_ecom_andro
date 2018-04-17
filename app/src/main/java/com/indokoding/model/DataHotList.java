package com.indokoding.model;

/**
 * Created by Admin on 26/02/2018.
 */

public class DataHotList {
    private String titleProduct;
    private int price;
    private int thumbnail;

    public DataHotList(String titleProduct,int price,int thumbnail){
        this.titleProduct=titleProduct;
        this.price=price;
        this.thumbnail=thumbnail;
    }


    public String getTitleProduct() {
        return titleProduct;
    }

    public void setTitleProduct(String titleProduct) {
        this.titleProduct = titleProduct;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }
}
