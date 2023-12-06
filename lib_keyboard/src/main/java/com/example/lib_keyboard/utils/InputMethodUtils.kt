package com.example.lib_keyboard.utils

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.provider.Settings
import android.view.inputmethod.InputMethodManager

object InputMethodUtils {
    fun isEnabled(context: Context) = (context.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager)?.let { manager->
        manager.enabledInputMethodList?.firstOrNull { it.packageName == context.packageName }
    } != null

    //判读输入法是否select
    fun isDefault(context: Context) = Settings.Secure.getString(context.contentResolver, Settings.Secure.DEFAULT_INPUT_METHOD)?.let {
        it.contains(context.packageName)
    } ?: true

    //设置默认键盘
    fun enableInputMethod(context: Context): Boolean {
        return try {
            context.startActivity(Intent(Settings.ACTION_INPUT_METHOD_SETTINGS).also {
                it.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                it.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
            }.also { intent->
                (context.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager)?.let { manager->
                    manager.inputMethodList?.firstOrNull { it.packageName == context.packageName }
                }?.let {
                    it.id
                }?.also {
                    intent.putExtra(":settings:fragment_args_key", it)
                    intent.putExtra(":settings:show_fragment_args", Bundle().apply { putString(":settings:fragment_args_key", it) })
                }
            })
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    fun setDefaultInputMethod(context: Context) = (context.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager)?.apply {
        showInputMethodPicker()
    }

    abstract class InputMethodBroadcastReceiver : BroadcastReceiver() {
        fun register(context: Context) {
            register(context, Intent.ACTION_INPUT_METHOD_CHANGED)
        }

        override fun onReceive(context: Context?, intent: Intent?) {
            context?.also {
                onInputMethodChange(isDefault(it))
            }
        }

        abstract fun onInputMethodChange(isDefault: Boolean)

    }
}

