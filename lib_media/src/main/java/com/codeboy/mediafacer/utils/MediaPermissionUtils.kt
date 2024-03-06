package com.codeboy.mediafacer.utils

import android.os.Build
import android.os.Environment
import com.blankj.utilcode.util.PermissionUtils

object MediaPermissionUtils {

    fun checkPermission(): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            Environment.isExternalStorageManager()
        } else {
            PermissionUtils.isGranted(android.Manifest.permission.READ_EXTERNAL_STORAGE)
        }
    }
}