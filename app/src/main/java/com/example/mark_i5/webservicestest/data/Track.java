package com.example.mark_i5.webservicestest.data;

import java.io.Serializable;

/**
 * Created by mark-i5 on 20/08/2014.
 */
public class Track implements Serializable {


    private String name;
    private String artist;
    private String trackUrl;
    private String artistUrl;
    private String imageUrl;
    private int rank;

    public Track(){

    }
    public Track(String name, String artist, String trackUrl, String artistUrl, String imageUrl, int rank){
        super();
        this.name = name;
        this.artist = artist;
        this.trackUrl = trackUrl;
        this.artistUrl = artistUrl;
        this.imageUrl = imageUrl;
        this.rank = rank;

    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public void setTrackUrl(String trackUrl) {
        this.trackUrl = trackUrl;
    }

    public void setArtistUrl(String artistUrl) {
        this.artistUrl = artistUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getArtist() {
        return artist;
    }
    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getArtistUrl() {

        return artistUrl;
    }

    public String getTrackUrl() {

        return trackUrl;
    }
}
