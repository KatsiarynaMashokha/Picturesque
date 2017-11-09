package com.epicodus.mappictures.models;

import org.parceler.Parcel;

/**
 * Created by katsiarynamashokha on 11/8/17.
 */
@Parcel
public class Picture {
    public String url;

    public Picture() {
    }

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
