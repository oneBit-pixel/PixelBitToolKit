package com.onBit.lib_base.base.utils

import android.content.Context
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AlertDialog
import com.onBit.lib_base.R

object AlerDialogTool {

    /**
     * 自定义从下网上弹出的Dialog,可自定义View
     * 动画自下而上
     */
    fun buildCustomBottomPopupDialog(
        context: Context,
        view: View,
        cancelable: Boolean = true
    ): AlertDialog {
        return buildCustomStylePopupDialogGravity(
            context,
            view,
            Gravity.BOTTOM,
            R.style.DialogBottomPopup,
            cancelable
        )
    }

    fun buildCustomTopPopupDialog(
        context: Context, view: View,
        cancelable: Boolean = true
    ): AlertDialog {
        return buildCustomStylePopupDialogGravity(
            context,
            view,
            Gravity.TOP,
            R.style.DialogTopPopup,
            cancelable
        )
    }

    //自定义位置的 自定义ViewDialog
    open fun buildCustomStylePopupDialogGravity(
        context: Context,
        view: View,
        gravity: Int,
        anim: Int,
        cancelable: Boolean = true,
        dialogStyle: Int = R.style.WhiteRoundDialog
    ): AlertDialog {
        val dialog = AlertDialog.Builder(context, dialogStyle)
            .setView(view)
            .setCancelable(cancelable)
            .create()

        dialog.window?.apply {
            setGravity(gravity)
            decorView.setPadding(0, 0, 0, 0)

            val layoutParams = this.attributes.apply {
                width = WindowManager.LayoutParams.MATCH_PARENT
                height = WindowManager.LayoutParams.WRAP_CONTENT
            }
            this.attributes = layoutParams
            this.setWindowAnimations(anim)
        }
        return dialog
    }

}