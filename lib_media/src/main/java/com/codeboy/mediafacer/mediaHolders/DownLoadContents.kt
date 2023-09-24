package com.codeboy.mediafacer.mediaHolders

import android.net.Uri

data class DownLoadContents(
    val fileName: String,
    val fileUri: Uri,
    val filePath: String,
    val fileId: Int = 0,
    val fileSize: Long,
    val fileType:String=""
)