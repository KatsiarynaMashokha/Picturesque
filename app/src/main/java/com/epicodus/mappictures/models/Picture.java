package com.epicodus.mappictures.models;

/**
 * Created by katsiarynamashokha on 11/8/17.
 */

public class Picture {
    private String url;

    public Picture(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
