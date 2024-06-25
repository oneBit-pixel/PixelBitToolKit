package com.onBit.pixelDemo.utls.app

import android.app.Activity
import android.app.usage.NetworkStats
import android.app.usage.NetworkStatsManager
import android.content.Context
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.net.ConnectivityManager
import android.os.Build
import android.text.format.Formatter
import com.blankj.utilcode.util.LogUtils
import java.util.Calendar

object AppUtils {
    fun Context.getApplicationInfo(packageName: String): ApplicationInfo {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            packageManager.getApplicationInfo(
                packageName,
                PackageManager.ApplicationInfoFlags.of(0)
            )
        } else {
            packageManager.getApplicationInfo(packageName, 0)
        }
    }

    /**
     * 查询各个app 流量使用情况
     */
    fun Activity.getAppNetWorkUser(packageName: String) {
        val applicationInfo = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            packageManager.getApplicationInfo(
                packageName,
                PackageManager.ApplicationInfoFlags.of(0)
            )
        } else {
            packageManager.getApplicationInfo(packageName, 0)
        }
        val appName = applicationInfo.loadLabel(packageManager)
        val calendar = Calendar.getInstance()
        val endTime = calendar.timeInMillis
        calendar.add(Calendar.DAY_OF_YEAR, -7) // 查询最近一周的数据
        val startTime = calendar.timeInMillis
        val uid = applicationInfo.uid
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val networkStatsManager =
                getSystemService(Context.NETWORK_STATS_SERVICE) as NetworkStatsManager

            val networkStats = networkStatsManager.queryDetailsForUid(
                ConnectivityManager.TYPE_WIFI,
                null,
                startTime,
                endTime,
                uid
            )

            var rxBytes = 0L
            var txBytes = 0L
            val bucket = NetworkStats.Bucket()
            while (networkStats.hasNextBucket()) {
                networkStats.getNextBucket(bucket)

                rxBytes += bucket.rxBytes
                txBytes += bucket.txBytes
            }
            networkStats.close()
            val rxString = Formatter.formatFileSize(this, rxBytes)
            val txString = Formatter.formatFileSize(this, txBytes)

            LogUtils.d("appName==>${appName} rxString==>${rxString} txString==>${txString}")
        }
    }

}