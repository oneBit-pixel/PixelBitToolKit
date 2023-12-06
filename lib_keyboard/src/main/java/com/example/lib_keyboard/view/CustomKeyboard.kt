@file:Suppress("DEPRECATION")

package com.example.lib_keyboard.view

import android.content.Context
import android.graphics.Typeface
import android.inputmethodservice.Keyboard
import android.inputmethodservice.KeyboardView
import android.text.Spannable
import android.text.SpannableString
import android.text.style.CharacterStyle
import android.text.style.StyleSpan
import android.text.style.TypefaceSpan
import android.util.AttributeSet
import android.view.KeyEvent
import androidx.core.content.res.ResourcesCompat
import androidx.core.text.getSpans
import com.example.lib_keyboard.R
import com.example.lib_keyboard.service.KeyboardService
import com.example.lib_keyboard.typface.CustomTypefaceSpan
import com.example.lib_keyboard.utils.KeyboardUtils

class CustomKeyboard @JvmOverloads constructor(context: Context, attrs: AttributeSet) :
    KeyboardView(context, attrs), KeyboardView.OnKeyboardActionListener {

    private val keyboardNum by lazy { Keyboard(context, R.xml.num) }

    private val keyboardLetter by lazy { Keyboard(context, R.xml.keyboard_2_small) }

    private val keyboardSymbol by lazy { Keyboard(context, R.xml.symbol) }

    private var mTypeface: Typeface =
        Typeface.createFromAsset(context.assets, "abril_fatface_regular.ttf")

    //是否为大写
    private var isUpper = false

    init {
        onKeyboardActionListener = this
        keyboard = keyboardLetter
    }


    override fun onPress(primaryCode: Int) {
        isPreviewEnabled = false
    }

    override fun onRelease(primaryCode: Int) {

    }

    @Suppress("DEPRECATION")
    override fun onKey(primaryCode: Int, keyCodes: IntArray?) {
        val keyboardService = context as KeyboardService
        val ic = keyboardService.currentInputConnection
        when (primaryCode) {
            Keyboard.KEYCODE_DELETE -> {
                val keyCode: Int = KeyEvent.KEYCODE_DEL
                ic.sendKeyEvent(
                    KeyEvent(KeyEvent.ACTION_DOWN, keyCode)
                )
            }

            Keyboard.KEYCODE_DONE -> ic.sendKeyEvent(
                KeyEvent(
                    KeyEvent.ACTION_DOWN,
                    KeyEvent.KEYCODE_ENTER
                )
            )

            Keyboard.KEYCODE_SHIFT -> {
                KeyboardUtils.switchUpperOrLowerCase(isUpper, keyboardLetter)
                isUpper = !isUpper
                keyboard = keyboardLetter
            }

            KeyboardUtils.CODE_TYPE_NUM -> keyboard = keyboardNum
            KeyboardUtils.CODE_TYPE_QWERTY -> keyboard = keyboardLetter
            KeyboardUtils.CODE_TYPE_SYMBOL -> keyboard = keyboardSymbol
            else -> {
                //todo：设置字体
                keyboard.keys.map {
                    if (it.codes.contains(primaryCode)) {
                        ic.commitText(it.label,1)
                    }
                }
            }

        }
    }

    override fun onText(text: CharSequence?) {

    }

    override fun swipeDown() {

    }

    override fun swipeLeft() {}
    override fun swipeRight() {}
    override fun swipeUp() {}


}