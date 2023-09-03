package com.onBit.pixelDemo.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.lifecycle.lifecycleScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.room.Room
import androidx.room.RoomDatabase
import com.blankj.utilcode.util.LogUtils
import com.onBit.PixelBitToolKit.R
import com.onBit.PixelBitToolKit.databinding.ActivityRoomBinding
import com.onBit.lib_base.base.BaseActivity
import com.onBit.pixelDemo.room.database.AppDatabase
import com.onBit.pixelDemo.room.entity.RoomEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class RoomActivity : BaseActivity<ActivityRoomBinding>() {
    override val bindingInflater: (LayoutInflater) -> ActivityRoomBinding
        get() = ActivityRoomBinding::inflate

    private val isCreated = MutableStateFlow(false)

    override fun initListener() {
        super.initListener()
    }

    override fun initEvent() {
        super.initEvent()
    }

    private val dao by lazy {
        Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "m.db"
        ).build().userDao()
    }

    override fun initView() {
        super.initView()


        lifecycleScope.launch(Dispatchers.IO) {

            for (i in 1..10_000) {
                dao.insertAll(
                    RoomEntity("$i", name = "zxy$i")
                )
            }
            isCreated.emit(true)
        }



        lifecycleScope.launch(Dispatchers.Main) {
            isCreated.collect {
                LogUtils.d("isCreated==>${isCreated.value}")
            }
        }

        mBinding.button4.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO) {
               val selectAll = dao.selectAll()
                LogUtils.d("selectAll==>$selectAll")
            }
        }
    }


}