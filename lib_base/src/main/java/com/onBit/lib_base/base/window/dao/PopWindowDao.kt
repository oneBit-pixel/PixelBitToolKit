package com.onBit.lib_base.base.window.dao

interface PopWindowDao {

    fun showAsDrop()
    fun setCancelable(b: Boolean)

    fun setAnimationStyle(animate: Int)

    fun dismiss()

    fun initView()

    fun initListener()

}