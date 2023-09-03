package com.onBit.lib_base.base.window.dao

import android.view.View

interface WindowDao {

    fun show(view: View, xoff: Int = 0, yoff: Int = 0)
    fun setCancelable(b: Boolean)

    fun setAnimationStyle(animate: Int)

    fun dismiss()

    fun initView()

    fun initListener()

}