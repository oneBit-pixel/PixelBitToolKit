package com.example.lib_base.base

import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import androidx.viewbinding.ViewBinding
import java.lang.Integer.min
import java.util.Timer
import java.util.TimerTask

/**
 * 开屏页基类
 *
 * @param null
 * @return
 * @author zhangxuyang
 * @create 2023/8/4
 **/

abstract class BaseSplashActivity<VB : ViewBinding> : BaseActivity<VB>() {
    val totalTime = 4000.0
    val intervalsTime = 100L
    var currentTime = 10

    override fun initEvent() {
        super.initEvent()
        //工作任务
        val timerTask = object : TimerTask() {

            override fun run() {
                val percent = min(((currentTime / totalTime) * 100).toInt(), 100)
                currentTime += intervalsTime.toInt()
                runOnUiThread {
                    //通知Ui改变
                    onProgressUpdate(percent)
                }
            }

        }
        val timer = Timer()
        timer.schedule(timerTask, 10, intervalsTime)

        Handler(Looper.getMainLooper()).postDelayed({
            timer.cancel()
            onFinishTime()
        }, totalTime.toLong())
    }

    abstract fun onFinishTime()

    abstract fun onProgressUpdate(percent: Int)


}