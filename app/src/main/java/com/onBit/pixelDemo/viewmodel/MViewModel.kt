package com.onBit.pixelDemo.viewmodel

import android.app.Application
import android.content.Context
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
import com.onBit.pixelDemo.hit.module.Man
import com.onBit.pixelDemo.model.User
import com.onBit.pixelDemo.utls.ImageProcessing
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class MViewModel @Inject constructor(
    private val man: Man
) : ViewModel() {

    private val _appsInfo = MutableLiveData<List<AppInfo>>(emptyList())
    val appInfo:LiveData<List<AppInfo>> = _appsInfo


    @Inject
    lateinit var scope: User
    fun say() {
        man.sex()
    }


    fun requestData(){
        viewModelScope.launch(Dispatchers.IO) {
            delay(2000)
            val appsInfo = AppUtils.getAppsInfo()

            val appInfos = _appsInfo.value!!.toMutableList()
            appInfos.addAll(appsInfo)
            _appsInfo.postValue(appInfos)
        }
    }

}

