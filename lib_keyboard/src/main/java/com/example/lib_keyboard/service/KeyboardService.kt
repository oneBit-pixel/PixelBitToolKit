package com.example.lib_keyboard.service

import android.annotation.SuppressLint
import android.content.IntentFilter
import android.inputmethodservice.InputMethodService
import android.view.View
import com.blankj.utilcode.util.LogUtils
import com.example.lib_keyboard.databinding.LayoutKeyboardBinding

class KeyboardService : InputMethodService() {


    private val mBinding by lazy {
        LayoutKeyboardBinding.inflate(layoutInflater)
    }


    @SuppressLint("UnspecifiedRegisterReceiverFlag")
    override fun onCreate() {
        super.onCreate()

    }

    @SuppressLint("MissingInflatedId")
    override fun onCreateInputView(): View {
        return mBinding.root
    }

    override fun onDestroy() {
        super.onDestroy()
    }



}
