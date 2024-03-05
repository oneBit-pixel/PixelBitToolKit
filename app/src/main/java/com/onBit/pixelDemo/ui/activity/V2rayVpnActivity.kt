package com.onBit.pixelDemo.ui.activity

import android.net.VpnService
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import com.blankj.utilcode.util.LogUtils
import com.onBit.PixelBitToolKit.R
import com.onBit.PixelBitToolKit.databinding.ActivityV2rayVpnBinding
import com.onBit.lib_base.base.BaseActivity
import com.tencent.mmkv.MMKV
import com.v2ray.ang.AppConfig
import com.v2ray.ang.extension.toast
import com.v2ray.ang.service.V2RayServiceManager
import com.v2ray.ang.util.AngConfigManager
import com.v2ray.ang.util.MmkvManager
import com.v2ray.ang.util.Utils
import com.v2ray.ang.viewmodel.MainViewModel

class V2rayVpnActivity : BaseActivity<ActivityV2rayVpnBinding>() {
    override val bindingInflater: (LayoutInflater) -> ActivityV2rayVpnBinding
        get() = ActivityV2rayVpnBinding::inflate

    private val requestVpnPermission = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        if (it.resultCode == RESULT_OK) {
            startV2Ray()
        }
    }

    val mainViewModel: MainViewModel by viewModels()
    private val mainStorage by lazy { MMKV.mmkvWithID(MmkvManager.ID_MAIN, MMKV.MULTI_PROCESS_MODE) }
    private val settingsStorage by lazy { MMKV.mmkvWithID(MmkvManager.ID_SETTING, MMKV.MULTI_PROCESS_MODE) }
    override fun initView() {
        super.initView()
        importClipboard()
    }

    override fun initListener() {
        super.initListener()
        mBinding.button.setOnClickListener {
            importClipboard()
            mainViewModel.serverList.forEach {
                guid->
            }
//            startV2ray()
        }
    }

    private fun startV2ray() {
        if (mainViewModel.isRunning.value == true) {
            Utils.stopVService(this)
        } else if (settingsStorage?.decodeString(AppConfig.PREF_MODE) ?: "VPN" == "VPN") {
            val intent = VpnService.prepare(this)
            if (intent == null) {
                startV2Ray()
            } else {
                requestVpnPermission.launch(intent)
            }
        } else {
            startV2Ray()
        }
    }

    fun startV2Ray() {
        mainStorage?.encode(MmkvManager.KEY_SELECTED_SERVER,"")
//        toast(R.string.toast_services_start)
        V2RayServiceManager.startV2Ray(this)
    }

    fun importClipboard()
            : Boolean {
        try {
            val clipboard = Utils.getClipboard(this)
            importBatchConfig(clipboard)
        } catch (e: Exception) {
            e.printStackTrace()
            return false
        }
        return true
    }

    fun importBatchConfig(server: String?, subid: String = "") {
        val subid2 = if(subid.isNullOrEmpty()){
            mainViewModel.subscriptionId
        }else{
            subid
        }
        val append = subid.isNullOrEmpty()

        var count = AngConfigManager.importBatchConfig(server, subid2, append)
        if (count <= 0) {
            count = AngConfigManager.importBatchConfig(Utils.decode(server!!), subid2, append)
        }
        if (count <= 0) {
            count = AngConfigManager.appendCustomConfigServer(server, subid2)
        }
        if (count > 0) {
            toast(com.v2ray.ang.R.string.toast_success)
            mainViewModel.reloadServerList()
        } else {
            toast(com.v2ray.ang.R.string.toast_failure)
        }
    }
}