package com.onBit.pixelDemo.ui.activity

import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import com.blankj.utilcode.util.LogUtils
import com.example.studyProject.factory.abs.TrousersFactory.TROUSERS_JEANS
import com.example.studyProject.factory.normal.Clothes
import com.example.studyProject.factory.normal.WardrobeFactory
import com.example.studyProject.observer.Student
import com.example.studyProject.observer.Teacher
import com.example.studyProject.prototype.Sheep
import com.onBit.PixelBitToolKit.databinding.ActivityRecyclewBinding
import com.onBit.lib_base.base.BaseActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.flow

class RecyclewActivity : BaseActivity<ActivityRecyclewBinding>() {
    override val bindingInflater: (LayoutInflater) -> ActivityRecyclewBinding
        get() = ActivityRecyclewBinding::inflate


    private val student by lazy {
        Student()
    }

    var num = 1

    override fun initView() {
        super.initView()

        mBinding.button3.setOnClickListener {
            student.setData("写作业...$num")
            num += 1
        }
    }


    override fun initListener() {
        super.initListener()
        val teacher = Teacher("teacher1")
        student.addObserver(teacher)
        student.addObserver { o, arg ->
            val arg1 = arg as String
            println("$arg")
        }
    }

}