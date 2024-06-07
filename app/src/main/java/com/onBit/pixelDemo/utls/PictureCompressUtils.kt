package com.onBit.pixelDemo.utls


import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.io.ByteArrayOutputStream
import java.io.File

object PictureCompressUtils {

    const val MIN_QUALITY = 60
    const val START_QUALITY = 90

    /**
     * 压缩图片
     * 1.将图片转为webp格式
     * 2.对图片大小进行检查
     * 3.降低图片质量直到内存小于原始图片
     */
    fun compressImgToWebp(imagePath: String): ByteArray {
        val imageFile = File(imagePath)
        return binarySearchCompression(imageFile, START_QUALITY, MIN_QUALITY)
    }

    fun binarySearchCompression(imageFile: File, maxQuality: Int, minQuality: Int): ByteArray {
        var low = minQuality
        var high = maxQuality
        var bestBytes: ByteArray? = null
        val bitmap = BitmapFactory.decodeFile(imageFile.absolutePath)

        var times = 0
        while (low <= high) {
            times++
            val mid = (low + high) / 2
            val currentBytes = compressBitmapToWebp(bitmap, mid)
            if (currentBytes.size < imageFile.length()) {  // 根据需求定义isDesiredSize函数
                bestBytes = currentBytes
                high = mid - 1  // 尝试更低的质量以查找更小的压缩文件
            } else {
                low = mid + 1  // 增加质量以达到期望大小
            }
        }
        return bestBytes ?: compressBitmapToWebp(bitmap, minQuality)
    }

    fun compressBitmapToWebp(bitmap: Bitmap, quality: Int): ByteArray {
        val outputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.WEBP, quality, outputStream)
        return outputStream.toByteArray()
    }


}