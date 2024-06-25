package com.onBit.pixelDemo.utls.permission

import android.app.AppOpsManager
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Process
import android.provider.Settings

/**
 *  检测用户 使用情况
 *   <uses-permission
 *         android:name="android.permission.PACKAGE_USAGE_STATS"
 *         tools:ignore="ProtectedPermissions" />
 *
 *                 if (UsageAccessPermissionUtils.hasUsageStatsPermission(this)) {
 *             if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
 *                 val calendar = Calendar.getInstance()
 *                 val endTime = calendar.timeInMillis
 *                 calendar.add(Calendar.WEEK_OF_MONTH, -1)
 *                 val startTime = calendar.timeInMillis
 *                 val usageStatsManager =
 *                     getSystemService(Context.USAGE_STATS_SERVICE) as UsageStatsManager
 *                 //INTERVAL_BEST 精准事件查询
 *                 usageStatsManager.queryUsageStats(
 *                     UsageStatsManager.INTERVAL_BEST,
 *                     startTime,
 *                     endTime
 *                 ).let {
 *                     it.forEach {
 *                         if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
 *                             val hour = TimeUnit.MILLISECONDS.toHours(it.totalTimeInForeground)
 *                             val minutes =
 *                                 TimeUnit.MILLISECONDS.toMinutes(it.totalTimeInForeground) % 60
 *                             val seconds =
 *                                 TimeUnit.MILLISECONDS.toSeconds(it.totalTimeInForeground) % 60
 *
 *                             if (it.lastTimeUsed >= startTime) {
 *
 * //                                LogUtils.d("appName==>$appName userTime==>${hour}:${minutes}:${seconds}")
 *                             }
 *                         }
 *                     }
 *                 }
 *             }
 *         } else {
 *             UsageAccessPermissionUtils.requestUsageStatsPermission(this)
 *         }
 */
object UsageAccessPermissionUtils {
    // Check if permission is granted
    // 检查是否有权限
    fun hasUsageStatsPermission(context: Context): Boolean {
        val appOpsManager = context.getSystemService(Context.APP_OPS_SERVICE) as AppOpsManager
        val checkOp = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            appOpsManager.unsafeCheckOp(
                AppOpsManager.OPSTR_GET_USAGE_STATS,
                Process.myUid(),
                context.packageName
            )
        } else {
            appOpsManager.checkOp(
                AppOpsManager.OPSTR_GET_USAGE_STATS,
                Process.myUid(),
                context.packageName
            )
        }
        return checkOp == 0
    }

    // Navigate user to the settings page to enable permission
    fun requestUsageStatsPermission(context: Context) {
        if (!hasUsageStatsPermission(context)) {
            val intent = Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)
        }
    }

}