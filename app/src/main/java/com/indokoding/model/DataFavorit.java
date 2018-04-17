package com.indokoding.model;

/**
 * Created by Admin on 26/02/2018.
 */

public class DataFavorit {
    private String titleFavorit;
    private int price;
    private int thumbnail;

    public DataFavorit(String titleFavorit, int price, int thumbnail){
        this.titleFavorit=titleFavorit;
        this.price=price;
        this.thumbnail=thumbnail;
    }


    public String getTitleFavorit() {
        return titleFavorit;
    }

    public void setTitleFavorit(String titleFavorit) {
        this.titleFavorit = titleFavorit;
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
