package com.onBit.pixelDemo.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.studyProject.studyKotlin.Mule
import com.example.studyProject.studyKotlin.getType
import com.onBit.pixelDemo.utls.ImageProcessing
import kotlinx.coroutines.launch


class MViewModel(context: Context) : AndroidViewModel(context as Application) {


    fun test() {

    }

    fun <T> copyAbc(a: T): Int {
        return 1
    }

    private fun sum(): Int.(Int) -> Int = { other ->
        plus(other)
    }


}

