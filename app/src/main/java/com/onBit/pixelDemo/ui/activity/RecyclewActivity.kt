package com.onBit.pixelDemo.ui.activity

import android.animation.ValueAnimator
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.Config
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.ConcatAdapter.Config
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.blankj.utilcode.util.AppUtils
import com.blankj.utilcode.util.BarUtils
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.ToastUtils
import com.chad.library.adapter4.BaseQuickAdapter
import com.chad.library.adapter4.QuickAdapterHelper
import com.chad.library.adapter4.dragswipe.QuickDragAndSwipe
import com.chad.library.adapter4.dragswipe.listener.OnItemDragListener
import com.chad.library.adapter4.dragswipe.setItemDragListener
import com.chad.library.adapter4.dragswipe.setItemSwipeListener
import com.chad.library.adapter4.loadState.LoadState
import com.chad.library.adapter4.loadState.leading.LeadingLoadStateAdapter
import com.chad.library.adapter4.loadState.trailing.TrailingLoadStateAdapter
import com.chad.library.adapter4.viewholder.QuickViewHolder
import com.example.lib_keyboard.service.KeyboardService
import com.example.lib_keyboard.utils.InputMethodUtils
import com.example.lib_keyboard.utils.KeyboardUtils
import com.onBit.PixelBitToolKit.R
import com.onBit.PixelBitToolKit.databinding.ActivityRecyclewBinding
import com.onBit.PixelBitToolKit.databinding.LayoutDialogBinding
import com.onBit.lib_base.base.BaseActivity
import com.onBit.pixelDemo.hit.module.Human
import com.onBit.pixelDemo.hit.module.ManType
import com.onBit.pixelDemo.hit.module.WomanType
import com.onBit.pixelDemo.ui.adapter.AppAdapter
import com.onBit.pixelDemo.ui.adapter.BottomAdapter
import com.onBit.pixelDemo.viewmodel.MViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import javax.inject.Inject


@AndroidEntryPoint
class RecyclewActivity : BaseActivity<ActivityRecyclewBinding>(),
    BaseQuickAdapter.OnItemClickListener<AppUtils.AppInfo> {


    val viewModel: MViewModel by viewModels()


    private val appAdapter by lazy {
        AppAdapter()
    }

    private val adapterHelper by lazy {
        QuickAdapterHelper.Builder(appAdapter)
            .setLeadingLoadStateAdapter(object : LeadingLoadStateAdapter.OnLeadingListener {
                override fun onLoad() {
                    viewModel.requestData()
                    LogUtils.d("上滑加载更多")
                }

                override fun isAllowLoading(): Boolean {
                    return true
                }

            })
            .setTrailingLoadStateAdapter(object : TrailingLoadStateAdapter.OnTrailingListener {
                override fun onFailRetry() {

                }

                override fun onLoad() {
                    LogUtils.d("底部加载更多")
                    viewModel.requestData()
                }

            })
            .build()
    }

    override val bindingInflater: (LayoutInflater) -> ActivityRecyclewBinding
        get() = ActivityRecyclewBinding::inflate


    override fun initView() {
        super.initView()
        mBinding.apply {
            recyclerview.adapter = adapterHelper.adapter
            recyclerview.layoutManager = LinearLayoutManager(this@RecyclewActivity)
            appAdapter.setOnItemClickListener(this@RecyclewActivity)
        }

        QuickDragAndSwipe().apply {
            attachToRecyclerView(mBinding.recyclerview)
            setDataCallback(appAdapter)
            setDragMoveFlags(ItemTouchHelper.UP or ItemTouchHelper.DOWN)//可进行上下拖动，交换位置。 ItemTouchHelper.LEFT 允许向左拖动，ItemTouchHelper.RIGHT 允许向右拖动
            setSwipeMoveFlags(ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT);//可进行左右滑动删除
        }
        appAdapter.stateView

        LogUtils.d("是否启用键盘==>${InputMethodUtils.isEnabled(this)}")
        LogUtils.d("是否为默认键盘${InputMethodUtils.isDefault(this)}")

        InputMethodUtils.enableInputMethod(this)
    }

    override fun initEvent() {
        super.initEvent()
        viewModel.requestData()
        adapterHelper.trailingLoadState = LoadState.NotLoading(false)
        adapterHelper.leadingLoadState = LoadState.NotLoading(false)
    }

    override fun initListener() {
        super.initListener()
        mBinding.apply {
//            swipLayout.setOnRefreshListener {
//                viewModel.requestData()
//            }
        }
        viewModel.appInfo.observe(this) {
            appAdapter.submitList(it)
//            mBinding.swipLayout.isRefreshing = false
            mBinding.text.text = it.size.toString()
            when (adapterHelper.leadingLoadState) {
                is LoadState.Error -> {
                    ToastUtils.showLong("Error")
                }

                LoadState.Loading -> {
                    ToastUtils.showLong("Loading")
                }

                LoadState.None -> {
                    LogUtils.d("None")
                }

                is LoadState.NotLoading -> {
                    LogUtils.d("NotLoading")
                }
            }
//            adapterHelper.trailingLoadState=LoadState.NotLoading(true)
        }

    }

    override fun onResume() {
        super.onResume()
        BarUtils.setStatusBarColor(this, Color.TRANSPARENT)
        BarUtils.transparentNavBar(this)
    }

    override fun onClick(
        adapter: BaseQuickAdapter<AppUtils.AppInfo, *>,
        view: View,
        position: Int
    ) {
        LogUtils.d("点击事件")
        val intent = Intent()
        intent.action="com.example.CHANGE_KEYBOARD_STYLE"
        intent.setComponent(ComponentName(packageName,"com.example.lib_keyboard.boardCast.KeyBoardReceiver"))
        sendBroadcast(intent)


        (getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager)?.let {
            manager->
            manager.inputMethodList.forEach {

            }
        }

    }


}

