package com.example.lib_keyboard.service

import android.annotation.SuppressLint
import android.inputmethodservice.InputMethodService
import android.view.View
import com.example.lib_keyboard.databinding.LayoutKeyboardBinding

class KeyboardService : InputMethodService() {


    private val mBinding by lazy {
        LayoutKeyboardBinding.inflate(layoutInflater)
    }


    @SuppressLint("MissingInflatedId")
    override fun onCreateInputView(): View {
        return mBinding.root
    }



//    override fun onKey(primaryCode: Int, keyCodes: IntArray) {
//        val ic = currentInputConnection
//        val styleSpan = StyleSpan(Typeface.ITALIC)
//        val spannableString = SpannableString("1234").apply {
//            setSpan(StyleSpan(Typeface.ITALIC), 0, 4, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
//            setSpan(ForegroundColorSpan(Color.RED),0,4, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
//        }
//        ic.commitText(spannableString,1)
//    }

}
