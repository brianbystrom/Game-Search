/*
Assignment #: Homework 05
File Name: Data.java
Group Members: Brian Bystrom, Mohamed Salad
*/

package com.example.brianbystrom.gamesearch;

import java.util.ArrayList;

/**
 * Created by brianbystrom on 2/6/17.
 */

public class Data {
    String title, releaseDate, platform, urlToImage, id, overview, publisher, trailer;
    ArrayList<String> genres, similar;

    public ArrayList<String> getSimilar() {
        return similar;
    }

    public void setSimilar(ArrayList<String> similar) {
        this.similar = similar;
    }

    public String getTrailer() {
        return trailer;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    public ArrayList<String> getGenres() {
        return genres;
    }

    public void setGenres(ArrayList<String> genres) {
        this.genres = genres;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getTitle() {
        return title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Data{" +
                "title='" + title + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                ", platform='" + platform + '\'' +
                ", urlToImage='" + urlToImage + '\'' +
                ", id='" + id + '\'' +
                ", overview='" + overview + '\'' +
                ", publisher='" + publisher + '\'' +
                ", youtube='" + trailer + '\'' +
                ", genres=" + genres.size() + '\'' +
                ", similar=" + similar.size() +
                '}';
    }

    public Data() {

    }
}
