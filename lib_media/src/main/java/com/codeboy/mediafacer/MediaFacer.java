package com.codeboy.mediafacer;

import android.content.Context;

import com.codeboy.mediafacer.mediaGet.AudioGet;
import com.codeboy.mediafacer.mediaGet.FileGet;
import com.codeboy.mediafacer.mediaGet.DownloadGet;
import com.codeboy.mediafacer.mediaGet.PictureGet;
import com.codeboy.mediafacer.mediaGet.VideoGet;

public class MediaFacer {

    /**
     * Returns a static instance of {@link VideoGet}
     */
    public static VideoGet withVideoContex(Context contx) {
        return VideoGet.getInstance(contx);
    }

    /**
     * Returns a static instance of {@link PictureGet}
     */
    public static PictureGet withPictureContex(Context contx) {
        return PictureGet.getInstance(contx);
    }

    /**
     * Returns a static instance of {@link AudioGet}
     */
    public static AudioGet withAudioContex(Context contx) {
        return AudioGet.getInstance(contx);
    }

    public static DownloadGet withDownLoadGet(Context contx) {
        return DownloadGet.getInstance(contx);
    }

    public static FileGet withDocContex(Context context) {
        return FileGet.Companion.getInstance(context);
    }

    public static void Initialize() {

    }

    /**
     * scans all media content on device
     */
    private void ScanAllMedia() {

    }

    /**
     * save general information about all media on de vice in
     */
    private void UpdateGeneralMediaInfo() {

    }

    public static void DeleteMedia(String mediaId, String mediaType) {

    }

    public static void RenameMedia(String mediaId, String mediaType) {

    }

}
