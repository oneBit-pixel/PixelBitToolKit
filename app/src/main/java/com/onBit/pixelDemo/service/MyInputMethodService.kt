package com.onBit.pixelDemo.service

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.Paint.Style
import android.graphics.Typeface
import android.inputmethodservice.InputMethodService
import android.inputmethodservice.Keyboard
import android.inputmethodservice.KeyboardView
import android.os.Build
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.view.View
import android.view.inputmethod.TextAttribute
import com.onBit.PixelBitToolKit.R

class MyInputMethodService : InputMethodService(), KeyboardView.OnKeyboardActionListener {

    @SuppressLint("MissingInflatedId")
    override fun onCreateInputView(): View {
        val rootView = layoutInflater.inflate(R.layout.keyborad, null)
        val keyboard = Keyboard(this, R.xml.keyborad_layout)
        val keyboardView = rootView.findViewById<KeyboardView>(R.id.KeyboardView)
        keyboardView.apply {
            this.keyboard = keyboard
            this.setOnKeyboardActionListener(this@MyInputMethodService)
        }

        return rootView
    }

    override fun onPress(primaryCode: Int) {

    }

    override fun onRelease(primaryCode: Int) {

    }

    override fun onKey(primaryCode: Int, keyCodes: IntArray) {
        val ic = currentInputConnection
        val styleSpan = StyleSpan(Typeface.ITALIC)
        val spannableString = SpannableString("1234").apply {
            setSpan(StyleSpan(Typeface.ITALIC), 0, 4, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            setSpan(ForegroundColorSpan(Color.RED),0,4,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        }
        ic.commitText(spannableString,1)
    }

    override fun onText(text: CharSequence?) {

    }

    override fun swipeLeft() {

    }

    override fun swipeRight() {

    }

    override fun swipeDown() {

    }

    override fun swipeUp() {

    }

}