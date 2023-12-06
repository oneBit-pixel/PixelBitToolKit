package com.example.lib_keyboard.utils

import android.content.BroadcastReceiver
import android.content.Context
import android.content.IntentFilter

fun BroadcastReceiver.register(context: Context, action: String?): Boolean {
    try {
        context.registerReceiver(this, IntentFilter(action))
        return true
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return false
}

fun BroadcastReceiver.unregister(context: Context): Boolean {
    try {
        context.unregisterReceiver(this)
        return true
    } catch (e: Exception) {
    }
    return false
}