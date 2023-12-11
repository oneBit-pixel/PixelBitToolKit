package com.onBit.pixelDemo.viewmodel

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.blankj.utilcode.util.AppUtils
import com.blankj.utilcode.util.AppUtils.AppInfo
import com.blankj.utilcode.util.LogUtils
import com.example.studyProject.studyKotlin.Mule
import com.example.studyProject.studyKotlin.getType
import com.onBit.lib_base.base.extension.toFlowAsync
import com.onBit.pixelDemo.hit.module.Man
import com.onBit.pixelDemo.model.User
import com.onBit.pixelDemo.utls.ImageProcessing
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.concurrent.thread


@HiltViewModel
class MViewModel @Inject constructor(
    private val man: Man
) : ViewModel() {

    private val _appsInfo = MutableLiveData<List<AppInfo>>(emptyList())
    val appInfo: LiveData<List<AppInfo>> = _appsInfo


    @Inject
    lateinit var scope: User
    fun say() {
        thread {

        }
    }


    fun request() {
        viewModelScope.launch {
            man.request().collect {
                it.onSuccess {
                    LogUtils.d("success==>${it}")
                }
                val appInfo1 = appInfo.value!![1]
                appInfo.value?.getOrElse(1){

                }
            }
        }
    }


    fun requestData() {
        viewModelScope.launch(Dispatchers.IO) {
            val appsInfo = AppUtils.getAppsInfo()

            val appInfos = _appsInfo.value!!.toMutableList()
            appInfos.addAll(appsInfo)
            _appsInfo.postValue(appInfos)
        }
    }

}

