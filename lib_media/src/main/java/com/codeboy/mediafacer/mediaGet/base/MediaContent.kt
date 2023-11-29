package com.codeboy.mediafacer.mediaGet.base

import com.blankj.utilcode.constant.MemoryConstants
import com.blankj.utilcode.util.ConvertUtils

data class MediaContent(
    override val id: Long,
    override val fileName: String,
    override val fileTitle: String?,
    override val filePath: String = "",
    override val sizeBytes: Long = 0,
    override val addTime: Long,
    override val dataTaken: Long,
):IMediaContent {
    override val sizeKB = ConvertUtils.byte2MemorySize(sizeBytes, MemoryConstants.KB)
    override val sizeMB = ConvertUtils.byte2MemorySize(sizeBytes, MemoryConstants.MB)
    override val sizeGB = ConvertUtils.byte2MemorySize(sizeBytes, MemoryConstants.GB)
}

