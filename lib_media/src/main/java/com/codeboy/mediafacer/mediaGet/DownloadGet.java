package com.codeboy.mediafacer.mediaGet;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;


import com.blankj.utilcode.util.FileUtils;
import com.codeboy.mediafacer.mediaHolders.DownLoadContents;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DownloadGet {

    private static DownloadGet downloadGet;
    private final Context downloadContext;


    private DownloadGet(Context contx) {
        downloadContext
                = contx.getApplicationContext();
    }

    public static DownloadGet getInstance(Context contx) {
        if (downloadGet == null) {
            downloadGet = new DownloadGet(contx);
        }
        return downloadGet;
    }

    private final String[] projections = {
            MediaStore.Downloads._ID,
            MediaStore.Downloads.TITLE,
            MediaStore.Downloads.DATA,
            MediaStore.Downloads.SIZE,
            MediaStore.Downloads.DISPLAY_NAME,
            MediaStore.Downloads.MIME_TYPE,
    };


    public ArrayList<DownLoadContents> getAllDownloadGet() {
        ArrayList<DownLoadContents> downloads = new ArrayList<>();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            Cursor cursor = downloadContext.getContentResolver().query(
                    MediaStore.Downloads.EXTERNAL_CONTENT_URI, projections, null, null
            );
            try {
                assert cursor != null;
                cursor.moveToFirst();
                do {
                    String fileName = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Downloads.DISPLAY_NAME));
                    int fileId = cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Downloads._ID));
                    Uri uri = Uri.withAppendedPath(MediaStore.Downloads.EXTERNAL_CONTENT_URI, String.valueOf(fileId
                    ));
                    String filePath = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Downloads.DATA));
                    String fileType = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Downloads.MIME_TYPE));
                    long fileSize = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Downloads.SIZE));
                    if (fileType == null) {
                        continue;
                    }
                    DownLoadContents loadContents = new DownLoadContents(fileName, uri, filePath, fileId, fileSize, fileType);

                    downloads.add(loadContents);
                } while (cursor.moveToNext());
            } catch (Exception e) {
                System.out.println("出错了...");
                e.printStackTrace();
            } finally {
                cursor.close();
            }
            return downloads;
        } else {
            File downloadFolder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
            List<File> files = FileUtils.listFilesInDir(downloadFolder);
            addFile(downloads,files);
            return downloads;
        }
    }

    private void addFile(ArrayList<DownLoadContents> downloads, List<File> files) {
        for (File file : files) {
            if (file.isDirectory()) {
                addFile(downloads,FileUtils.listFilesInDir(file));
                continue;
            }
            String filePath = file.getAbsolutePath();
            String fileName = file.getName();
            Uri fileUri = Uri.parse(file.toURI().toString());
            long fileSize = file.length();
            int lastDot = fileName.lastIndexOf(".");
            String fileType = "";
            if (lastDot != -1) {
                fileType = fileName.substring(lastDot + 1);
            }
            DownLoadContents loadContents = new DownLoadContents(
                    fileName,
                    fileUri,
                    filePath,
                    0,
                    fileSize,
                    fileType
            );
            downloads.add(
                    loadContents
            );
        }
    }


}
