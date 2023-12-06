package com.onBit.pixelDemo.service

import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.media.Image
import android.media.MediaPlayer
import android.net.Uri
import android.service.wallpaper.WallpaperService
import android.view.SurfaceHolder
import com.onBit.lib_base.base.init.appContext
import android.service.wallpaper.WallpaperService.Engine
import android.view.MotionEvent
import android.widget.ImageView
import androidx.appcompat.widget.DrawableUtils
import androidx.core.graphics.drawable.DrawableCompat
import com.blankj.utilcode.util.LogUtils
import com.bumptech.glide.Glide
import com.onBit.PixelBitToolKit.R
import com.onBit.pixelDemo.ui.floatview.FloatView
import com.xuexiang.xfloatview.XFloatView
import java.util.logging.Handler

class MyWallpaperService : WallpaperService() {
    override fun onCreateEngine(): Engine {
        val myWallpaperEngine = MyWallpaperEngine()
        myWallpaperEngine.setTouchEventsEnabled(true)

        return myWallpaperEngine
    }

    inner class MyWallpaperEngine : Engine(), MediaPlayer.OnPreparedListener {

        private var mPlayer: MediaPlayer? = null

        private var isPapered = false

        private val drawRunner = Runnable { draw() }
        override fun onCreate(surfaceHolder: SurfaceHolder?) {
            super.onCreate(surfaceHolder)

        }

        override fun onVisibilityChanged(visible: Boolean) {
//            if (visible) {
//                mPlayer?.start()
//            }else{
//                mPlayer?.pause()
//            }

        }



        var x=200f
        var y=200f
        override fun onTouchEvent(event: MotionEvent?) {
            super.onTouchEvent(event)
            when (event?.action) {
                MotionEvent.ACTION_DOWN -> {
                    x=event.x
                    y=event.y
                    draw()
                }

                else -> {}
            }
        }

        init {
            val handler = android.os.Handler()
            handler.post(drawRunner)
        }

        private fun draw() {
            LogUtils.d("drawdrawdraw")
            val lockCanvas = surfaceHolder.lockCanvas()
            try {
                lockCanvas?.let { canvas ->
                    canvas.drawColor(Color.BLUE)
                    val bitmap =
                        Glide.with(this@MyWallpaperService).asBitmap().load(R.mipmap.mp_logo)
                            .override(300)
                            .submit().get()
                    canvas.drawBitmap(bitmap,x,y,null)
                }
            } catch (e: Exception) {
                LogUtils.e("出错了==>${e.toString()}")
            } finally {
                if (lockCanvas != null) {
                    surfaceHolder.unlockCanvasAndPost(lockCanvas)
                }
            }
        }


        private fun initMediaPlayer(holder: SurfaceHolder) {
//            mPlayer = MediaPlayer.create(applicationContext, R.raw.live_wallpaper).apply {
//                setSurface(holder.surface)
//                isLooping = true
//                mPlayer?.start()
//            }
        }

        override fun onSurfaceDestroyed(holder: SurfaceHolder?) {
            super.onSurfaceDestroyed(holder)
//            if (mPlayer?.isPlaying == true) {
//                mPlayer?.stop()
//            }
//            mPlayer?.release()
//            mPlayer = null
        }


        override fun onPrepared(mp: MediaPlayer?) {
//            isPapered = true
//            mPlayer?.start()
        }
    }
}