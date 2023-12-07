package com.onBit.pixelDemo.ui.activity

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.activity.viewModels
import androidx.appcompat.view.ContextThemeWrapper
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.blankj.utilcode.util.AppUtils
import com.blankj.utilcode.util.BarUtils
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.ToastUtils
import com.chad.library.adapter4.BaseQuickAdapter
import com.chad.library.adapter4.QuickAdapterHelper
import com.chad.library.adapter4.dragswipe.QuickDragAndSwipe
import com.chad.library.adapter4.loadState.LoadState
import com.chad.library.adapter4.loadState.leading.LeadingLoadStateAdapter
import com.chad.library.adapter4.loadState.trailing.TrailingLoadStateAdapter
import com.example.lib_keyboard.utils.InputMethodUtils
import com.onBit.PixelBitToolKit.R
import com.onBit.PixelBitToolKit.databinding.ActivityRecyclewBinding
import com.onBit.lib_base.base.BaseActivity
import com.onBit.pixelDemo.ui.adapter.AppAdapter
import com.onBit.pixelDemo.viewmodel.MViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class RecyclewActivity : BaseActivity<ActivityRecyclewBinding>(),
    BaseQuickAdapter.OnItemClickListener<AppUtils.AppInfo>,
    BaseQuickAdapter.OnItemChildClickListener<AppUtils.AppInfo> {


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

        appAdapter.addOnItemChildClickListener(R.id.button123,this)

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



    override fun onItemClick(
        adapter: BaseQuickAdapter<AppUtils.AppInfo, *>,
        view: View,
        position: Int
    ) {
        val themeWrapper = ContextThemeWrapper(this, R.style.PopupMenuTheme)
        PopupMenu(themeWrapper,view,Gravity.RIGHT,0,R.style.PopupMenuStyle ).apply {
            menuInflater.inflate(R.menu.bottom,this.menu)

        }.show()
    }


}

