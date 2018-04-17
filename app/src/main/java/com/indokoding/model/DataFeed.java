package com.indokoding.model;

/**
 * Created by Admin on 26/02/2018.
 */

public class DataFeed {
    private String titleFeed;
    private int price;
    private int thumbnail;


    public DataFeed(String titleFeed, int price, int thumbnail){
        this.titleFeed=titleFeed;
        this.price=price;
        this.thumbnail=thumbnail;
    }

    public String getTitleFeed() {
        return titleFeed;
    }

    public void setTitleFeed(String titleFeed) {
        this.titleFeed = titleFeed;
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
