package com.onBit.pixelDemo.ui.activity

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import androidx.datastore.preferences.preferencesDataStore
import com.onBit.PixelBitToolKit.databinding.ActivityViewBinding
import com.onBit.lib_base.base.BaseViewModelActivity
import com.onBit.pixelDemo.utls.asm.Student
import com.onBit.pixelDemo.viewmodel.MViewModel
import com.trustlook.sdk.cloudscan.CloudScanClient
import com.trustlook.sdk.cloudscan.CloudUtil
import org.objectweb.asm.ClassReader
import org.objectweb.asm.ClassVisitor
import org.objectweb.asm.ClassWriter
import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes
import java.io.FileInputStream
import java.io.FileOutputStream


private val Context.dataStore by preferencesDataStore(
    name = "mine"
)

const val asb = "123"

class ViewActivity : BaseViewModelActivity<ActivityViewBinding, MViewModel>(
) {
    override val viewModel: Class<MViewModel>
        get() = MViewModel::class.java
    override val bindingInflater: (LayoutInflater) -> ActivityViewBinding
        get() = ActivityViewBinding::inflate


    companion object{
        fun test(): String {
            return "1234"
        }
    }

    override fun initView() {
        super.initView()
        mBinding.root.setOnClickListener {


        }
    }

    fun getListenerInfo() {

    }


}



