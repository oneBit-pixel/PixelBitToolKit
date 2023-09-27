package com.onBit.pixelDemo.ui.activity

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.provider.Settings
import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import com.blankj.utilcode.util.LogUtils
import com.codeboy.mediafacer.AudioGet
import com.codeboy.mediafacer.DownloadGet
import com.codeboy.mediafacer.MediaFacer
import com.example.studyProject.factory.abs.TrousersFactory.TROUSERS_JEANS
import com.example.studyProject.factory.normal.Clothes
import com.example.studyProject.factory.normal.WardrobeFactory
import com.example.studyProject.observer.Student
import com.example.studyProject.observer.Teacher
import com.example.studyProject.prototype.Sheep
import com.onBit.PixelBitToolKit.databinding.ActivityRecyclewBinding
import com.onBit.lib_base.base.BaseActivity
import com.tbruyelle.rxpermissions3.RxPermissions
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.flow

class RecyclewActivity : BaseActivity<ActivityRecyclewBinding>() {
    override val bindingInflater: (LayoutInflater) -> ActivityRecyclewBinding
        get() = ActivityRecyclewBinding::inflate

    override fun isFullScreen(): Boolean {
        return true
    }


}