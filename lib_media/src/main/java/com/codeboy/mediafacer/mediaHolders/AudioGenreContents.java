package com.codeboy.mediafacer.mediaHolders;

import java.util.ArrayList;

public class AudioGenreContents {

    private ArrayList<AudioContent> audioFiles;
    private String genreName;
    private String genreId;

    public AudioGenreContents(){
        audioFiles = new ArrayList<>();
    }

    public ArrayList<AudioContent> getAudioFiles() {
        return audioFiles;
    }

    public void setAudioFiles(ArrayList<AudioContent> audioFiles) {
        this.audioFiles = audioFiles;
    }

    public String getGenreName() {
        return genreName;
    }

    public void setGenreName(String genreName) {
        this.genreName = genreName;
    }

    public String getGenreId() {
        return genreId;
    }

    public void setGenreId(String genreId) {
        this.genreId = genreId;
    }

    public void addGenreMusic(AudioContent music){
        audioFiles.add(music);
    }

}
