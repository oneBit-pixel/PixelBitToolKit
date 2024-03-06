package com.codeboy.mediafacer.utils

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.webkit.MimeTypeMap
import com.blankj.utilcode.util.UtilsFileProvider
import java.io.File

object MediaUtils {

    fun getMineTypeByFileType(fileType: String): String {
        return MimeTypeMap.getSingleton().getMimeTypeFromExtension(fileType) ?: "*/*"
    }

    @SuppressLint("QueryPermissionsNeeded")
    fun startAction(context: Context, mineType: String, fileUri: Uri): Boolean {
        val intent = Intent("android.intent.action.VIEW").apply {
            addCategory("android.intent.category.DEFAULT")
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_GRANT_READ_URI_PERMISSION
            setDataAndType(fileUri, mineType)
        }
        if (intent.resolveActivity(context.packageManager) != null) {
            context.startActivity(intent)
            return true
        } else {
            return false
        }
    }

    fun getUriByFile(context: Context, file: File): Uri? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            UtilsFileProvider.getUriForFile(context, context.packageName+".utilcode.fileprovider", file)
        } else {
            Uri.fromFile(file)
        }
    }
}