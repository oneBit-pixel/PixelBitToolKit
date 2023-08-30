package com.codeboy.mediafacer.mediaHolders;

import java.util.ArrayList;

public class PictureFolderContent {

    private String folderPath;
    private String folderName;
    private ArrayList<PictureContent> photos;
    private int bucket_id;

    public PictureFolderContent(){
        photos = new ArrayList<>();
    }

    public PictureFolderContent(String path, String folderName) {
        this.folderPath = path;
        this.folderName = folderName;
        photos = new ArrayList<>();
    }

    public String getFolderPath() {
        return folderPath;
    }

    public void setFolderPath(String path) {
        this.folderPath = path;
    }

    public String getFolderName() {
        return folderName;
    }

    public void setFolderName(String folderName) {
        this.folderName = folderName;
    }

    public ArrayList<PictureContent> getPhotos() {
        return photos;
    }

    public void setPhotos(ArrayList<PictureContent> photos) {
        this.photos = photos;
    }

    public int getBucket_id() {
        return bucket_id;
    }

    public void setBucket_id(int bucket_id) {
        this.bucket_id = bucket_id;
    }
}
