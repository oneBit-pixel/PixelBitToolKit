package com.codeboy.mediafacer.mediaHolders;

import java.util.ArrayList;

public class VideoFolderContent {

    private ArrayList<VideoContent> videoFiles;
    private String folderName;
    private String folderPath;
    private int bucket_id;

    public VideoFolderContent(){
        videoFiles = new ArrayList<>();
    }

    public VideoFolderContent(String folderPath, String folderName) {
        this.folderName = folderName;
        this.folderPath = folderPath;
        videoFiles = new ArrayList<>();
    }

    public ArrayList<VideoContent> getVideoFiles() {
        return videoFiles;
    }

    public void setVideoFiles(ArrayList<VideoContent> videoFiles) {
        this.videoFiles = videoFiles;
    }

    public String getFolderName() {
        return folderName;
    }

    public void setFolderName(String folderName) {
        this.folderName = folderName;
    }

    public String getFolderPath() {
        return folderPath;
    }

    public void setFolderPath(String folderPath) {
        this.folderPath = folderPath;
    }

    public int getBucket_id() {
        return bucket_id;
    }

    public void setBucket_id(int bucket_id) {
        this.bucket_id = bucket_id;
    }
}
