package com.codeboy.mediafacer.mediaHolders;

import android.net.Uri;

import java.lang.ref.PhantomReference;

public class AudioContent {


    private String name;
    private String title;
    private String filePath;
    private String artist;
    private String album;
    private String genre;
    private String composer;
    private Uri art_uri;
    private long musicSize;
    private long duration;
    private long musicId;
    private String musicUri;
    private long dateAdd;

    private long dateTaken;

    private long dateModify;

    public long getDateModify() {
        return dateModify;
    }

    public void setDateModify(long dateModify) {
        this.dateModify = dateModify;
    }

    public long getDateTaken() {
        return dateTaken;
    }

    public void setDateTaken(long dateTaken) {
        this.dateTaken = dateTaken;
    }

    public long getDateAdd() {
        return dateAdd;
    }

    public void setDateAdd(long dateAdd) {
        this.dateAdd = dateAdd;
    }

    public AudioContent(){ }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getComposer() {
        return composer;
    }

    public void setComposer(String composer) {
        this.composer = composer;
    }

    public Uri getArt_uri() {
        return art_uri;
    }

    public void setArt_uri(Uri art_uri) {
        this.art_uri = art_uri;
    }

    public long getMusicSize() {
        return musicSize;
    }

    public void setMusicSize(long musicSize) {
        this.musicSize = musicSize;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public long getMusicId() {
        return musicId;
    }

    public void setMusicId(long musicId) {
        this.musicId = musicId;
    }

    public String getMusicUri() {
        return musicUri;
    }

    public void setMusicUri(String musicUri) {
        this.musicUri = musicUri;
    }
}
