package com.example.shardulnegi12.newsrss;

public class News {

    private String title;
    private String discription;
    private  String pubdate;
    private String image;


    public News(String title, String discription, String pubdate, String image) {
        this.title = title;
        this.discription = discription;
        this.pubdate = pubdate;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public String getPubdate() {
        return pubdate;
    }

    public void setPubdate(String pubdate) {
        this.pubdate = pubdate;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
