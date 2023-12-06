package com.onBit.pixelDemo.ui.activity

import android.app.WallpaperManager
import android.content.ComponentName
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import com.onBit.PixelBitToolKit.databinding.ActivityWallpaperBinding
import com.onBit.lib_base.base.BaseActivity
import com.onBit.pixelDemo.service.MyWallpaperService


class WallpaperActivity: BaseActivity<ActivityWallpaperBinding>() {
    override val bindingInflater: (LayoutInflater) -> ActivityWallpaperBinding
        get() = ActivityWallpaperBinding::inflate

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val intent = Intent(WallpaperManager.ACTION_CHANGE_LIVE_WALLPAPER)
        intent.putExtra(
            WallpaperManager.EXTRA_LIVE_WALLPAPER_COMPONENT, ComponentName(
                this,
                MyWallpaperService::class.java
            )
        )
        startActivity(intent)
    }
}