package com.codeboy.mediafacer;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import com.codeboy.mediafacer.mediaHolders.VideoContent;
import com.codeboy.mediafacer.mediaHolders.VideoFolderContent;
import java.util.ArrayList;

public class VideoGet {
    private static  VideoGet videoGet;
    private final Context videoContext;
    public static final Uri externalContentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
    public static final Uri internalContentUri = MediaStore.Video.Media.INTERNAL_CONTENT_URI;
    private static Cursor cursor;

    private VideoGet(Context contx){
        videoContext = contx.getApplicationContext();
    }

    static VideoGet getInstance(Context contx){
        if(videoGet == null){
            videoGet = new VideoGet(contx);
        }
        return videoGet;
    }

    @SuppressLint("InlinedApi") String[] Projections = {
            MediaStore.Video.Media.DATA,
            MediaStore.Video.Media.DISPLAY_NAME,
            MediaStore.Video.Media.DURATION,
            MediaStore.Video.Media.BUCKET_DISPLAY_NAME,
            MediaStore.Video.Media.BUCKET_ID,
            MediaStore.Video.Media.SIZE,
            MediaStore.Video.Media._ID,
            MediaStore.Video.Media.ALBUM,
            MediaStore.Video.Media.DATE_TAKEN,
            MediaStore.Video.Media.ARTIST};


    /**Returns an Arraylist of {@link VideoContent}  */
    @SuppressLint("InlinedApi")
    public ArrayList<VideoContent> getAllVideoContent(Uri contentLocation) {
        ArrayList<VideoContent> allVideo = new ArrayList<>();
        cursor = videoContext.getContentResolver().query(contentLocation, Projections, null, null, "LOWER ("+MediaStore.Video.Media.DATE_TAKEN+") DESC");//DESC ASC
        try {
            cursor.moveToFirst();
            do{
                VideoContent videoContent = new VideoContent();

                videoContent.setVideoName(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DISPLAY_NAME)));

                videoContent.setPath(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATA)));

                videoContent.setVideoDuration(cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DURATION)));

                videoContent.setVideoSize(cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.SIZE)));

                int id = cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Video.Media._ID));
                videoContent.setVideoId(id);
                Uri contentUri = Uri.withAppendedPath(contentLocation, String.valueOf(id));
                videoContent.setVideoUri(contentUri.toString());

                videoContent.setAlbum(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.ALBUM)));

                videoContent.setArtist(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.ARTIST)));

                allVideo.add(videoContent);
            }while(cursor.moveToNext());

            cursor.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return allVideo;
    }

    /**Returns an Arraylist of {@link VideoContent} in a specific folder  */
    @SuppressLint("InlinedApi")
    public ArrayList<VideoContent> getAllVideoContentByBucket_id(int bucket_id){
        ArrayList<VideoContent> VideoContents = new ArrayList<>();
       cursor = videoContext.getContentResolver().query(externalContentUri, Projections,
                MediaStore.Video.Media.BUCKET_ID + " like ? ", new String[] {"%"+bucket_id+"%"}, "LOWER ("+MediaStore.Video.Media.DATE_TAKEN+") DESC");//DESC
        try {
            cursor.moveToFirst();
            do{
                VideoContent videoContent = new VideoContent();

                videoContent.setVideoName(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DISPLAY_NAME)));

                videoContent.setPath(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATA)));

                videoContent.setVideoDuration(cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DURATION)));

                videoContent.setVideoSize(cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.SIZE)));

                int id = cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Video.Media._ID));
                videoContent.setVideoId(id);

                Uri contentUri = Uri.withAppendedPath(externalContentUri, String.valueOf(id));
                videoContent.setVideoUri(contentUri.toString());

                videoContent.setAlbum(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.ALBUM)));

                videoContent.setArtist(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.ARTIST)));

                VideoContents.add(videoContent);
            }while(cursor.moveToNext());
            cursor.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return VideoContents;
    }

    /**Returns an Arraylist of {@link VideoFolderContent} with each videoFolderContent having an Arraylist of all it videoContent*/
    @SuppressLint("InlinedApi")
    public ArrayList<VideoFolderContent> getAllVideoFolders(Uri contentLocation){
        ArrayList<VideoFolderContent> allVideoFolders = new ArrayList<>();
        ArrayList<Integer> videoPaths = new ArrayList<>();
        cursor = videoContext.getContentResolver().query(contentLocation, Projections,
                null, null, "LOWER ("+MediaStore.Video.Media.DATE_TAKEN+") DESC");//DESC
        try{
            cursor.moveToFirst();
            do{
                VideoFolderContent videoFolder = new VideoFolderContent();
                VideoContent videoContent = new VideoContent();

                videoContent.setVideoName(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DISPLAY_NAME)));

                videoContent.setPath(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATA)));

                videoContent.setVideoDuration(cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DURATION)));

                videoContent.setVideoSize(cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.SIZE)));

                int id = cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Video.Media._ID));
                videoContent.setVideoId(id);

                Uri contentUri = Uri.withAppendedPath(contentLocation, String.valueOf(id));
                videoContent.setVideoUri(contentUri.toString());

                videoContent.setAlbum(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.ALBUM)));

                videoContent.setArtist(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.ARTIST)));

                String folder = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.BUCKET_DISPLAY_NAME));
                String datapath = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATA));

                int bucket_id = cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.BUCKET_ID));

                String folderpaths = datapath.substring(0, datapath.lastIndexOf(folder+"/"));
                folderpaths = folderpaths+folder+"/";

                if (!videoPaths.contains(bucket_id)) {
                    videoPaths.add(bucket_id);
                    videoFolder.setBucket_id(bucket_id);
                    videoFolder.setFolderPath(folderpaths);
                    videoFolder.setFolderName(folder);
                    videoFolder.getVideoFiles().add(videoContent);
                    allVideoFolders.add(videoFolder);
                }else{
                    for(int i = 0; i < allVideoFolders.size();i++){
                        if(allVideoFolders.get(i).getBucket_id() == bucket_id){
                            allVideoFolders.get(i).getVideoFiles().add(videoContent);
                        }
                    }
                }
            }while (cursor.moveToNext());
            cursor.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return allVideoFolders;
    }

}
