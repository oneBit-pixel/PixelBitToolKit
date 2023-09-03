package com.onBit.pixelDemo.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch


class MViewModel(context: Context) : AndroidViewModel(context as Application) {

    fun test(){
        viewModelScope.launch {

        }
    }
}