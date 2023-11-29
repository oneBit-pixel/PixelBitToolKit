package com.codeboy.mediafacer.mediaGet

import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import android.provider.MediaStore
import com.blankj.utilcode.util.LogUtils
import com.codeboy.mediafacer.mediaGet.base.MediaContent
import com.codeboy.mediafacer.mediaHolders.DocContent

/**
 * 文件查找,输出类型 返回对应的类型的文件
 */
class FileGet private constructor(val context: Context) {
    companion object {
        @SuppressLint("StaticFieldLeak")
        @Volatile
        private var instance: FileGet? = null
        fun getInstance(context: Context): FileGet {
            return instance ?: synchronized(this) {
                instance ?: FileGet(context.applicationContext).also {
                    instance = it
                }
            }
        }

        //文档
        val DOCUMENT_MIME_TYPE = arrayOf(
            "application/pdf",         // 对于 PDF
            "text/plain",              // 对于 TXT
//        "application/vnd.android.package-archive", //.apk
//        "application/zip" //.zip
            "application/msword",      // 对于 Word (.doc)
            "application/vnd.ms-excel" // 对于 Excel (.xls)
        )
    }

    private val projection = arrayOf(
        MediaStore.Files.FileColumns._ID,
        //文件名 123.txt
        MediaStore.Files.FileColumns.DISPLAY_NAME,
        //文件名 123
        MediaStore.Files.FileColumns.TITLE,
        //文件路径
        MediaStore.Files.FileColumns.DATA,
        //文件大小 bytes
        MediaStore.Files.FileColumns.SIZE,
        //实际创建时间
        MediaStore.Files.FileColumns.DATE_TAKEN,
        //该副本创建时间
        MediaStore.Files.FileColumns.DATE_ADDED,
        //文件类型
        MediaStore.Files.FileColumns.MIME_TYPE,
    )


    private val externalContentUri: Uri by lazy { MediaStore.Files.getContentUri("external") }

    /**
     * 获取所有文件类型
     */
    fun getAllDocContent(vararg mimeTypes: String): List<MediaContent> {
        val selection = if (mimeTypes.isEmpty()) {
            null
        } else {
            "${MediaStore.Files.FileColumns.MIME_TYPE} IN (${mimeTypes.joinToString(",") { "'$it'" }})"
        }
        val docContents = mutableListOf<MediaContent>()
        val cursor = context.contentResolver.query(
            externalContentUri,
            projection,
            selection,
            null,
            null
        )!!
        try {
            cursor.moveToFirst()
            do {
                val content = MediaContent(
                    cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns._ID)),
                    cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.DISPLAY_NAME)),
                    cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.TITLE)),
                    cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.DATA)),
                    cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.SIZE)),
                    cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.DATE_ADDED)),
                    cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.DATE_TAKEN)),
                )
                docContents.add(content)
            } while (cursor.moveToNext())
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return docContents
    }
}