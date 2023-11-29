package com.codeboy.mediafacer.mediaGet.base

interface IMediaContent {
    val id: Long
    val fileName: String
    val fileTitle: String?
    val filePath: String
    val sizeBytes: Long
    val addTime: Long
    val dataTaken: Long
    val sizeKB: Double
    val sizeMB: Double
    val sizeGB: Double
}