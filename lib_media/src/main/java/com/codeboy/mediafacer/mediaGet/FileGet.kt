package com.codeboy.mediafacer.mediaGet

import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import android.provider.MediaStore
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
        MediaStore.Files.FileColumns.DISPLAY_NAME,
        MediaStore.Files.FileColumns.SIZE,
        MediaStore.Files.FileColumns.DATE_TAKEN,
        MediaStore.Files.FileColumns.DATE_ADDED,
    )


    private val externalContentUri: Uri by lazy { MediaStore.Files.getContentUri("external") }

    /**
     * 获取所有文件类型
     */
    fun getAllDocContent(vararg mimeTypes: String): List<DocContent> {
        val selection =
            if (mimeTypes.isEmpty()) {
                null
            } else {
                "${MediaStore.Files.FileColumns.MIME_TYPE} IN (${mimeTypes.joinToString(",") { "'$it'" }})"
            }
        val docContents = mutableListOf<DocContent>()
        val cursor =
            context.contentResolver.query(
                externalContentUri,
                projection,
                selection,
                null,
                null
            )!!
        try {
            cursor.moveToFirst()
            do {
                val content = DocContent(
                    cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.DISPLAY_NAME))
                )
                docContents.add(content)
            } while (cursor.moveToNext())
        } catch (e: Exception) {

        }
        return docContents
    }
}