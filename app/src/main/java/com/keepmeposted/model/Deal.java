package com.keepmeposted.model;

/**
 * Created by ChanukiG on 10/29/2016.
 */

public class Deal {
    private String name;
    private String date;
    private int thumbnail;

    public Deal() {
    }

    public Deal(String name, String expires, int thumbnail) {
        this.name = name;
        this.date = expires;
        this.thumbnail = thumbnail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String numOfSongs) {
        this.date = numOfSongs;
    }

    public int getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }
}
