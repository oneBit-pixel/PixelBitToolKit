package com.onBit.lib_base.base.utils

import android.app.Activity
import android.view.Gravity
import android.view.View
import android.widget.PopupWindow

object PopUpWindowTools {
    fun buildCustomPopUpWindow(
        context: Activity,
        customView: View,
        cancelAble: Boolean = true
    ): PopupWindow {
        val popupWindow = PopupWindow(context)
        popupWindow.apply {
            contentView = customView
            isOutsideTouchable = cancelAble
        }
        popupWindow.setOnDismissListener {
            restore(context)
        }
        return popupWindow
    }


    /**
     * popUnWindow 扩展
     * 展示在控件上方
     *
     */
    fun PopupWindow.showAsUp(
        view: View,
        isAlpha: Boolean = true,
        alpha: Float = 0.6f,
    ) {
        //测量大小
        contentView.measure(
            View.MeasureSpec.UNSPECIFIED,
            View.MeasureSpec.UNSPECIFIED
        )
        val location = intArrayOf(0, 0)
        view.getLocationOnScreen(location)
        if (isAlpha) {
            WindowUtils.setAlpha(view.context, alpha)
        }
        showAtLocation(
            view,
            Gravity.NO_GRAVITY,
            location[0],
            location[1] - contentView.measuredHeight
        )
    }

    /**
     * 显示在控件底部 右边对其
     */
    fun PopupWindow.showAsDropByRight(
        view: View,
        isAlpha: Boolean = true,
        alpha: Float = 0.6f,
    ) {
        //测量大小
        contentView.measure(
            View.MeasureSpec.UNSPECIFIED,
            View.MeasureSpec.UNSPECIFIED
        )
        val location = intArrayOf(0, 0)
        view.getLocationOnScreen(location)
        if (isAlpha) {
            WindowUtils.setAlpha(view.context, alpha)
        }
        showAtLocation(
            view,
            Gravity.NO_GRAVITY,
            location[0] - contentView.measuredWidth,
            location[1] + view.measuredHeight
        )
    }

    fun PopupWindow.showAsDropByLeft(
        view: View,
        isAlpha: Boolean = true,
        alpha: Float = 0.6f,
    ) {
        //测量大小
        contentView.measure(
            View.MeasureSpec.UNSPECIFIED,
            View.MeasureSpec.UNSPECIFIED
        )
        val location = intArrayOf(0, 0)
        view.getLocationOnScreen(location)
        if (isAlpha) {
            WindowUtils.setAlpha(view.context, alpha)
        }
        showAtLocation(
            view,
            Gravity.NO_GRAVITY,
            location[0],
            location[1] + view.measuredHeight
        )
    }

    //恢复
    private fun restore(context: Activity) {
        WindowUtils.setAlpha(context, 1f)
    }

}