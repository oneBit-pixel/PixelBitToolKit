package com.example.lib_keyboard.utils

import android.content.Context
import android.content.Intent
import android.inputmethodservice.Keyboard
import android.os.Bundle
import android.provider.Contacts
import android.provider.Settings
import android.text.TextUtils
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat.getSystemService

object KeyboardUtils {
    //表情
    const val CODE_EMOJI = -100

    //数字键盘切换
    const val CODE_TYPE_NUM = -101

    //字母
    const val CODE_TYPE_QWERTY = -102

    //符号
    const val CODE_TYPE_SYMBOL = -103

    //切换输入法
    const val CODE_TYPE_SWITCH_INPUT = -105

    //space
    const val CODE_SPACE = 32

    fun switchUpperOrLowerCase(isUpper: Boolean, keyboard: Keyboard) {
        val keyList = keyboard.keys
        if (isUpper) { // 大写切换小写
            for (key in keyList) {
                val label = key.label
                if (!TextUtils.isEmpty(label) && key.codes[0] != CODE_SPACE && key.codes[0] != Keyboard.KEYCODE_DONE) {
                    val str = label.toString()
                    if (key.label != null && isLetter(str)) {
                        key.label = str.toLowerCase()
                        key.codes[0] = key.codes[0] + 32
                    }
                } else if (key.sticky && key.codes[0] == Keyboard.KEYCODE_SHIFT) {
                    key.pressed = false
                }
            }
        } else { // 小写切换大写
            for (key in keyList) {
                val label = key.label
                if (!TextUtils.isEmpty(label) && key.codes[0] != CODE_SPACE && key.codes[0] != Keyboard.KEYCODE_DONE) {
                    val str = label.toString()
                    if (key.label != null && isLetter(str)) {
                        key.label = str.toUpperCase()
                        key.codes[0] = key.codes[0] - 32
                    }
                } else if (key.sticky && key.codes[0] == Keyboard.KEYCODE_SHIFT) {
                    key.pressed = true
                }
            }
        }
    }

    private fun isLetter(str: String): Boolean {
        if (!TextUtils.isEmpty(str)) {
            val c = str[0]
            return c in 'a'..'z' || c in 'A'..'Z'
        }
        return false
    }

    //设置默认键盘
    fun setDefault(context: Context) {
        context.startActivity(Intent(Settings.ACTION_INPUT_METHOD_SETTINGS).also {
            it.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            it.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
        }.also { intent ->
            (context.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager)?.let { manager ->
                manager.inputMethodList.firstOrNull { it.packageName == context.packageName }
            }?.let {
                it.id
            }?.also {
                intent.putExtra(":settings:fragment_args_key", it)
                intent.putExtra(
                    ":settings:show_fragment_args",
                    Bundle().apply { putString(":settings:fragment_args_key", it) })
            }
        })
    }

    fun showKeyboard(context: Context){
        (context.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager)?.apply {
            showInputMethodPicker()
        }
    }
}