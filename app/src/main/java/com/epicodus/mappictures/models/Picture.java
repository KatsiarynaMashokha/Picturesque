package com.epicodus.mappictures.models;

import org.parceler.Parcel;

/**
 * Created by katsiarynamashokha on 11/8/17.
 */
@Parcel
public class Picture {
    public String url;
    public String title;

    public Picture() {
    }

    public Picture(String url, String title) {
        this.url = url;
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
