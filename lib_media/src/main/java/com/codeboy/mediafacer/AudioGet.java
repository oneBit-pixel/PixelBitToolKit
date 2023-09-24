package com.codeboy.mediafacer;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import com.codeboy.mediafacer.mediaHolders.AudioAlbumContent;
import com.codeboy.mediafacer.mediaHolders.AudioArtistContent;
import com.codeboy.mediafacer.mediaHolders.AudioContent;
import com.codeboy.mediafacer.mediaHolders.AudioFolderContent;
import com.codeboy.mediafacer.mediaHolders.AudioGenreContents;

import java.io.File;
import java.util.ArrayList;

public class AudioGet {

    private static  AudioGet audioGet;
    private final Context AudioContext;
    public static final Uri externalContentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
    public static final Uri internalContentUri = MediaStore.Audio.Media.INTERNAL_CONTENT_URI;
    public static final String externalVolume = "external";
    public static final String internalVolume = "internal";
    private static Cursor cursor;

    private AudioGet(Context context){
        AudioContext = context.getApplicationContext();
    }

    static AudioGet getInstance(Context context){
        if(audioGet == null){
            audioGet = new AudioGet(context);
        }
        return audioGet;
    }

    String Selection = android.provider.MediaStore.Audio.Media.IS_MUSIC + " != 0";
    @SuppressLint("InlinedApi") private final String[] Projections = {
            MediaStore.Audio.Media.TITLE,
            MediaStore.Audio.Media.DISPLAY_NAME,
            MediaStore.Audio.Media.ARTIST,
            MediaStore.Audio.Media.ARTIST_ID,
            MediaStore.Audio.Media.ALBUM,
            MediaStore.Audio.Media.ALBUM_ID,
            MediaStore.Audio.Media.COMPOSER,
            MediaStore.Audio.Media.SIZE,
            MediaStore.Audio.Media._ID,
            MediaStore.Audio.Media.DATA,
            MediaStore.Audio.Media.DURATION,
            MediaStore.Audio.Media.BUCKET_ID,

    };

    /**Returns an Arraylist of {@link AudioContent} */
    @SuppressLint("InlinedApi")
    public ArrayList<AudioContent> getAllAudioContent(Uri contentLocation) {
        ArrayList<AudioContent> allAudioContent = new ArrayList<>();

        cursor = AudioContext.getContentResolver().query(contentLocation
                ,Projections
                ,Selection,
                null,
                "LOWER ("+MediaStore.Audio.Media.TITLE + ") ASC");//"LOWER ("+MediaStore.Audio.Media.TITLE + ") ASC"
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    AudioContent audioContent = new AudioContent();

                    audioContent.setName(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DISPLAY_NAME)));

                    audioContent.setTitle(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE)));

                    long id = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media._ID));
                    audioContent.setMusicId(id);
                    Uri contentUri = Uri.withAppendedPath(contentLocation, String.valueOf(id));
                    audioContent.setMusicUri(contentUri.toString());

                    String path = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA));
                    audioContent.setFilePath(path);
                    audioContent.setMusicSize(cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.SIZE)));

                    audioContent.setAlbum(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM)));

                    audioContent.setDuration(cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION)));

                    long album_id = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM_ID));
                    Uri sArtworkUri = Uri.parse("content://media/external/audio/albumart");
                    Uri imageUri = Uri.withAppendedPath(sArtworkUri, String.valueOf(album_id));
                    audioContent.setArt_uri(imageUri);

                    audioContent.setArtist(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST)));

                    audioContent.setComposer(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.COMPOSER)));

                    audioContent.setGenre(GetGenre(cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media._ID)),"external"));

                    allAudioContent.add(audioContent);
                } while (cursor.moveToNext());
            }
            cursor.close();
        }
        return allAudioContent;
    }
    
    private String GetGenre(int media_id,String volumeName){
        String[] genresProj = {
                MediaStore.Audio.Genres.NAME,
                MediaStore.Audio.Genres._ID
        };

        Uri uri = MediaStore.Audio.Genres.getContentUriForAudioId(volumeName, media_id );
        Cursor genresCursor = AudioContext.getContentResolver().query(uri, genresProj , null, null, null);
        int genreIndex = genresCursor.getColumnIndexOrThrow(MediaStore.Audio.Genres.NAME);

        String genre = "";
        while (genresCursor.moveToNext()) {
            genre = genresCursor.getString(genreIndex);
        }
        genresCursor.close();
    return genre;
    }

    /**Returns an ArrayList of {@link AudioAlbumContent} */
    public ArrayList<AudioAlbumContent> getAllAlbums(Uri contentLocation) {
        ArrayList<AudioAlbumContent> AudioAlbumContents = new ArrayList<>();
        cursor = AudioContext.getContentResolver().query(contentLocation,Projections,Selection, null, "LOWER ("+MediaStore.Audio.Media.ALBUM + ") ASC");
        ArrayList<String> albumNames = new ArrayList<>();
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    AudioAlbumContent album; AudioContent audioContent = new AudioContent();

                    audioContent.setName(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DISPLAY_NAME)));

                    audioContent.setTitle(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE)));

                    long id = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media._ID));
                    audioContent.setMusicId(id);

                    audioContent.setDuration(cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION)));

                    String album_name = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM));
                    audioContent.setAlbum(album_name);

                    long album_id = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM_ID));
                    Uri sArtworkUri = Uri.parse("content://media/external/audio/albumart");
                    Uri imageUri = Uri.withAppendedPath(sArtworkUri, String.valueOf(album_id));
                    audioContent.setArt_uri(imageUri);

                    audioContent.setMusicUri(Uri.withAppendedPath(contentLocation, String.valueOf(id)).toString());

                    String path = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA));
                    audioContent.setFilePath(path);
                    audioContent.setMusicSize(cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.SIZE)));

                    audioContent.setGenre(GetGenre(cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media._ID)),"external"));

                    String artist_name = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST));
                    audioContent.setArtist(artist_name);

                    if(!albumNames.contains(String.valueOf(album_id))){
                        albumNames.add(String.valueOf(album_id));
                        album = new AudioAlbumContent(album_name,album_id,imageUri,artist_name);
                        album.addAudioContent(audioContent);
                        album.addNumberOfSongs();
                        AudioAlbumContents.add(album);
                    }else{
                        for(AudioAlbumContent albumX : AudioAlbumContents){
                            if(String.valueOf(albumX.getAlbumId()).equals(String.valueOf(album_id))){
                                albumX.addAudioContent(audioContent);
                                albumX.addNumberOfSongs();
                            }
                        }
                    }
                } while (cursor.moveToNext());
            }
            cursor.close();
        }
        return AudioAlbumContents;
    }

    /**Returns an ArrayList of String representing artist ids in the {@link MediaStore} */
    private ArrayList<AudioAlbumContent> getAllAlbumsByArtistId(String artist_id, Uri contentLocation) {
        ArrayList<AudioAlbumContent> AllAlbums = new ArrayList<>();
        cursor = AudioContext.getContentResolver().query(contentLocation,Projections,
                MediaStore.Audio.Artists.ARTIST + " like ? ",
                new String[] {"%"+artist_id+"%"}, "LOWER ("+MediaStore.Audio.Artists.ARTIST + ") ASC");
        ArrayList<String> albumNames = new ArrayList<>();
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    AudioAlbumContent album; AudioContent audioContent = new AudioContent();

                    String song_name = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DISPLAY_NAME));
                    audioContent.setName(song_name);

                    String songTitle = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE));
                    audioContent.setTitle(songTitle);

                    long id = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media._ID));
                    audioContent.setMusicId(id);

                    audioContent.setDuration(cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION)));

                    String album_name = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM));
                    audioContent.setAlbum(album_name);

                    Uri contentUri = Uri.withAppendedPath(contentLocation, String.valueOf(id));
                    audioContent.setMusicUri(contentUri.toString());

                    long album_id = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM_ID));
                    Uri sArtworkUri = Uri.parse("content://media/external/audio/albumart");
                    Uri imageUri = Uri.withAppendedPath(sArtworkUri, String.valueOf(album_id));
                    audioContent.setArt_uri(imageUri);

                    String path = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA));
                    audioContent.setFilePath(path);
                    audioContent.setMusicSize(cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.SIZE)));

                    audioContent.setGenre(GetGenre(cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media._ID)),"external"));

                    String artist_name = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST));
                    audioContent.setArtist(artist_name);

                    if(!albumNames.contains(String.valueOf(album_id))){
                        albumNames.add(String.valueOf(album_id));
                        album = new AudioAlbumContent(album_name,album_id,imageUri,artist_name);
                        album.addAudioContent(audioContent);
                        album.addNumberOfSongs();
                        AllAlbums.add(album);
                    }else{
                        for(AudioAlbumContent albumX : AllAlbums){
                            if(String.valueOf(albumX.getAlbumId()).equals(String.valueOf(album_id))){
                                albumX.addAudioContent(audioContent);
                                albumX.addNumberOfSongs();
                            }
                        }
                    }
                } while (cursor.moveToNext());
            }
            cursor.close();
        }
        return AllAlbums;
    }

    /** Returns an ArrayList of Strings which represent names of all artist with music in the {@link MediaStore}
     * database of the device */
    public ArrayList<String> getAllArtistIds(Uri contentLocation){
        ArrayList<String> allArtistIds = new ArrayList<>();
        String[] projection = {MediaStore.Audio.Media.ARTIST,MediaStore.Audio.Media.ARTIST_ID,MediaStore.Audio.Artists.ARTIST};
        cursor = AudioContext.getContentResolver().query(contentLocation,projection, Selection, null, "LOWER ("+MediaStore.Audio.Artists.ARTIST + ") ASC");
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    String artistId = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Artists.ARTIST));
                    if(!allArtistIds.contains(String.valueOf(artistId))){
                        allArtistIds.add(String.valueOf(artistId));
                    }
                } while (cursor.moveToNext());
            }
            cursor.close();
        }
        return allArtistIds;
    }

    /** Returns and ArrayList of Artists as {@link AudioArtistContent}
     * objects from the android {@link MediaStore} database */
    public ArrayList<AudioArtistContent> getAllArtists(ArrayList<String> ids, Uri contentLocation){
        ArrayList<AudioArtistContent> AudioArtistContents = new ArrayList<>();
        for(String id : ids){
            AudioArtistContent artist = new AudioArtistContent();
            ArrayList<AudioAlbumContent> artistAlbums = getAllAlbumsByArtistId(id,contentLocation);
            artist.setAlbums(artistAlbums);
            artist.setArtistName(id);
            AudioArtistContents.add(artist);
        }
        return AudioArtistContents;
    }

    /** Returns and ArrayList of {@link AudioFolderContent} from the android MediaStore */
    public ArrayList<AudioFolderContent> getAllAudioFolderContent(Uri contentLocation){
        ArrayList<AudioFolderContent> musicFolders = new ArrayList<>();
        cursor = AudioContext.getContentResolver().query(contentLocation, Projections, Selection, null, "LOWER ("+MediaStore.Audio.Media.TITLE + ") ASC");
        ArrayList<Integer> folders = new ArrayList<>();
        if (cursor != null) {
            if(cursor.moveToFirst()){
                do{
                    AudioFolderContent audioFolder = new AudioFolderContent(); AudioContent audioContent = new AudioContent();

                    String dataPath = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA));
                    File path = new File(dataPath);
                    File parent = new File(path.getParent());
                    String parentName = parent.getName();
                    String parentPath = parent.getAbsolutePath();

                    audioContent.setFilePath(dataPath);
                    audioContent.setMusicSize(cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.SIZE)));

                    int bucket_id = cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.BUCKET_ID));

                    audioContent.setName(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DISPLAY_NAME)));

                    audioContent.setTitle(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE)));

                    long id = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media._ID));
                    audioContent.setMusicId(id);

                    audioContent.setMusicUri(Uri.withAppendedPath(externalContentUri, String.valueOf(id)).toString());

                    audioContent.setAlbum(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM)));

                    long dur = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION));
                    audioContent.setDuration(dur);

                    long album_id = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM_ID));
                    Uri sArtworkUri = Uri.parse("content://media/external/audio/albumart");
                    Uri imageUri = Uri.withAppendedPath(sArtworkUri, String.valueOf(album_id));
                    audioContent.setArt_uri(imageUri);

                    audioContent.setGenre(GetGenre(cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media._ID)),"external"));

                    audioContent.setArtist(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST)));

                    if (!folders.contains(bucket_id)) {
                        folders.add(bucket_id);
                        audioFolder.setBucket_id(bucket_id);
                        audioFolder.setFolderName(parentName);
                        audioFolder.setFolderPath(parentPath);
                        audioFolder.getMusicFiles().add(audioContent);
                        musicFolders.add(audioFolder);
                    }else{
                         for (AudioFolderContent folderX : musicFolders){
                             if(folderX.getFolderName().equals(parentName)){
                                 folderX.getMusicFiles().add(audioContent);
                             }
                         }
                    }
                }while(cursor.moveToNext());
            }
        }
        cursor.close();
        for(int i = 0;i < musicFolders.size();i++){
            Log.d("audio folders",musicFolders.get(i).getFolderName()+" and path = "+musicFolders.get(i).getFolderPath()+" "+musicFolders.get(i).getNumberOfSongs());
        }
        return musicFolders;
    }

    public AudioContent getMusicMetaData(long audio_id){
        AudioContent audioContent = new AudioContent();
        cursor = AudioContext.getContentResolver().query(externalContentUri,Projections,
                MediaStore.Audio.Media._ID + " like ? ", new String[] {"%"+audio_id+"%"}, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    audioContent.setName(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DISPLAY_NAME)));

                    audioContent.setTitle(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE)));

                    long id = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media._ID));
                    audioContent.setMusicId(id);

                    audioContent.setMusicUri(Uri.withAppendedPath(externalContentUri, String.valueOf(id)).toString());

                    String path = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA));
                    audioContent.setFilePath(path);
                    audioContent.setMusicSize(cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.SIZE)));

                    audioContent.setAlbum(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM)));

                    audioContent.setDuration(cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION)));

                    long album_id = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM_ID));
                    Uri sArtworkUri = Uri.parse("content://media/external/audio/albumart");
                    Uri imageUri = Uri.withAppendedPath(sArtworkUri, String.valueOf(album_id));
                    audioContent.setArt_uri(imageUri);

                    audioContent.setGenre(GetGenre(cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media._ID)),"external"));

                    audioContent.setArtist(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST)));

                } while (cursor.moveToNext());
            }
            cursor.close();
        }
        return audioContent;
    }

    /** returns an ArrayList of audioContent whose names all match the search string */
    public ArrayList<AudioContent> searchMusic(String audioTitle){
        ArrayList<AudioContent> AudioContents = new ArrayList<>();
        cursor = AudioContext.getContentResolver().query(externalContentUri,Projections,
                MediaStore.Audio.Media.TITLE + " like ? ", new String[] {"%"+audioTitle+"%"}, "LOWER ("+MediaStore.Audio.Media.TITLE + ") ASC");
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    AudioContent audioContent = new AudioContent();

                    audioContent.setName(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DISPLAY_NAME)));

                    audioContent.setTitle(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE)));

                    long id = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media._ID));
                    audioContent.setMusicId(id);

                    audioContent.setMusicUri(Uri.withAppendedPath(externalContentUri, String.valueOf(id)).toString());

                    String path = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA));
                    audioContent.setFilePath(path);
                    audioContent.setMusicSize(cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.SIZE)));

                    audioContent.setAlbum(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM)));

                    audioContent.setDuration(cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION)));

                    long album_id = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM_ID));
                    Uri sArtworkUri = Uri.parse("content://media/external/audio/albumart");
                    Uri imageUri = Uri.withAppendedPath(sArtworkUri, String.valueOf(album_id));
                    audioContent.setArt_uri(imageUri);

                    audioContent.setGenre(GetGenre(cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media._ID)),"external"));

                    audioContent.setArtist(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST)));

                    AudioContents.add(audioContent);

                } while (cursor.moveToNext());
            }
            cursor.close();
        }
        return AudioContents;
    }

    /** returns an ArrayList of albumHolder whose names all match the search string */
    public ArrayList<AudioContent> searchAlbum(String albumName){
        ArrayList<AudioContent> AudioContents = new ArrayList<>();
        cursor = AudioContext.getContentResolver().query(externalContentUri,Projections,
                MediaStore.Audio.Media.ALBUM + " like ? ", new String[] {"%"+albumName+"%"}, "LOWER ("+MediaStore.Audio.Media.TITLE + ") ASC");
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    AudioContent audioContent = new AudioContent();

                    audioContent.setName(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DISPLAY_NAME)));

                    audioContent.setTitle(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE)));

                    long id = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media._ID));
                    audioContent.setMusicId(id);

                    String path = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA));
                    audioContent.setFilePath(path);
                    audioContent.setMusicSize(cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.SIZE)));

                    audioContent.setMusicUri(Uri.withAppendedPath(externalContentUri, String.valueOf(id)).toString());

                    String album_name = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM));
                    audioContent.setAlbum(album_name);

                    audioContent.setDuration(cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION)));

                    long album_id = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM_ID));
                    Uri sArtworkUri = Uri.parse("content://media/external/audio/albumart");
                    Uri imageUri = Uri.withAppendedPath(sArtworkUri, String.valueOf(album_id));
                    audioContent.setArt_uri(imageUri);

                    audioContent.setGenre(GetGenre(cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media._ID)),"external"));

                    audioContent.setArtist(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST)));

                    AudioContents.add(audioContent);

                } while (cursor.moveToNext());
            }
            cursor.close();
        }
        return AudioContents;
    }

    /** returns an ArrayList of artistHolder whose names all match the search string */
    public ArrayList<AudioContent> searchArtist(String artistName){
        ArrayList<AudioContent> AudioContents = new ArrayList<>();
        cursor = AudioContext.getContentResolver().query(externalContentUri,Projections,
                MediaStore.Audio.Media.ARTIST + " like ? ", new String[] {"%"+artistName+"%"}, "LOWER ("+MediaStore.Audio.Media.TITLE + ") ASC");
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    AudioContent audioContent = new AudioContent();

                    audioContent.setName(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DISPLAY_NAME)));

                    audioContent.setTitle(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE)));

                    long id = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media._ID));
                    audioContent.setMusicId(id);

                    audioContent.setMusicUri(Uri.withAppendedPath(externalContentUri, String.valueOf(id)).toString());

                    String path = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA));
                    audioContent.setFilePath(path);
                    audioContent.setMusicSize(cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.SIZE)));

                    audioContent.setAlbum(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM)));

                    audioContent.setDuration(cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION)));

                    long album_id = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM_ID));
                    Uri sArtworkUri = Uri.parse("content://media/external/audio/albumart");
                    Uri imageUri = Uri.withAppendedPath(sArtworkUri, String.valueOf(album_id));
                    audioContent.setArt_uri(imageUri);

                    audioContent.setGenre(GetGenre(cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media._ID)),"external"));

                    audioContent.setArtist(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST)));

                    AudioContents.add(audioContent);

                } while (cursor.moveToNext());
            }
            cursor.close();
        }
        return AudioContents;
    }

    public ArrayList<AudioGenreContents> getAudioContentsByGenre(Uri contentLocation){
        ArrayList<AudioGenreContents> allGenreContent = new ArrayList<>();
        cursor = AudioContext.getContentResolver().query(contentLocation,Projections, Selection, null, "LOWER ("+MediaStore.Audio.Media.TITLE + ") ASC");
        ArrayList<String> genreNames = new ArrayList<>();
        if(cursor != null){
            if(cursor.moveToFirst()){
                do{
                    AudioContent audioContent = new AudioContent();

                    audioContent.setName(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DISPLAY_NAME)));

                    audioContent.setTitle(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE)));

                    long id = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media._ID));
                    audioContent.setMusicId(id);
                    Uri contentUri = Uri.withAppendedPath(contentLocation, String.valueOf(id));
                    audioContent.setMusicUri(contentUri.toString());

                    String path = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA));
                    audioContent.setFilePath(path);
                    audioContent.setMusicSize(cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.SIZE)));

                    audioContent.setAlbum(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM)));

                    audioContent.setDuration(cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION)));

                    long album_id = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM_ID));
                    Uri sArtworkUri = Uri.parse("content://media/external/audio/albumart");
                    Uri imageUri = Uri.withAppendedPath(sArtworkUri, String.valueOf(album_id));
                    audioContent.setArt_uri(imageUri);

                    audioContent.setArtist(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST)));

                    audioContent.setComposer(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.COMPOSER)));

                    String genre = ""; String genreId = "";
                    String[] genresProj = {
                            MediaStore.Audio.Genres.NAME,
                            MediaStore.Audio.Genres._ID
                    };

                    Uri uri = MediaStore.Audio.Genres.getContentUriForAudioId("external", cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media._ID)) );
                    Cursor genresCursor = AudioContext.getContentResolver().query(uri, genresProj , null, null, null);
                    int genreIndex = genresCursor.getColumnIndexOrThrow(MediaStore.Audio.Genres.NAME);
                    if(genresCursor.moveToFirst()){
                        do{
                            genreId = genresCursor.getString(genresCursor.getColumnIndexOrThrow(MediaStore.Audio.Genres._ID));
                            genre = genresCursor.getString(genreIndex);
                            Log.d("Genre Debug ",cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE))+" "+genre);
                        }while (genresCursor.moveToNext());
                    }

                    audioContent.setGenre(genre);
                    genresCursor.close();

                    if(!genreNames.contains(genre)){
                        genreNames.add(genre);
                        AudioGenreContents Genre = new AudioGenreContents();
                        Genre.setGenreName(genre); Genre.setGenreId(genreId);
                        Genre.addGenreMusic(audioContent);
                        allGenreContent.add(Genre);
                    }else {
                        for(AudioGenreContents genreX : allGenreContent){
                            if(genre.equals(genreX.getGenreName())){
                                genreX.addGenreMusic(audioContent);
                            }
                        }
                    }
                }while (cursor.moveToNext());
            }
        }
        cursor.close();
        for(AudioGenreContents genreX : allGenreContent){
            if(genreX.getGenreName() == null || genreX.getGenreName().trim().isEmpty()){
                genreX.setGenreName("Unspecified");
            }
        }
        return allGenreContent;
    }

}
