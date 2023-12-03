package com.onBit.pixelDemo.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.blankj.utilcode.util.LogUtils
import com.example.studyProject.studyKotlin.Mule
import com.example.studyProject.studyKotlin.getType
import com.onBit.pixelDemo.hit.module.Man
import com.onBit.pixelDemo.model.User
import com.onBit.pixelDemo.utls.ImageProcessing
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MViewModel @Inject constructor(
    private val man: Man
) : ViewModel() {

    @Inject
    lateinit var scope: User
    fun say() {
        man.sex()
    }


}

