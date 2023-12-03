package com.onBit.pixelDemo.ui.activity

import android.animation.ValueAnimator
import android.graphics.Color
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.blankj.utilcode.util.AppUtils
import com.blankj.utilcode.util.BarUtils
import com.chad.library.adapter4.BaseQuickAdapter
import com.chad.library.adapter4.QuickAdapterHelper
import com.chad.library.adapter4.dragswipe.QuickDragAndSwipe
import com.chad.library.adapter4.dragswipe.listener.OnItemDragListener
import com.chad.library.adapter4.dragswipe.setItemDragListener
import com.chad.library.adapter4.dragswipe.setItemSwipeListener
import com.chad.library.adapter4.viewholder.QuickViewHolder
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
class RecyclewActivity : BaseActivity<ActivityRecyclewBinding>(), OnItemDragListener,
    BaseQuickAdapter.OnItemLongClickListener<AppUtils.AppInfo> {


    val viewModel: MViewModel by viewModels()

    @Inject
    lateinit var retrofit: Retrofit

    @Inject
    @ManType
    lateinit var man: Human

    @Inject
    @WomanType
    lateinit var woman: Human

    @Inject
    @WomanType
    lateinit var woman2: Human

    private val appAdapter by lazy { AppAdapter().apply {

    } }

    override val bindingInflater: (LayoutInflater) -> ActivityRecyclewBinding
        get() = ActivityRecyclewBinding::inflate


    fun doSomeThing() {


    }

    val quickDragAndSwipe by lazy {
        QuickDragAndSwipe()
    }

    val helper by lazy {
        QuickAdapterHelper.Builder(appAdapter)
            .build()
    }

    override fun initView() {
        super.initView()
        mBinding.apply {
            recyclerview.adapter = appAdapter
            recyclerview.layoutManager=LinearLayoutManager(this@RecyclewActivity)
        }


        quickDragAndSwipe.apply {
            attachToRecyclerView(mBinding.recyclerview)
            setDataCallback(appAdapter)
        }
            .setDragMoveFlags(ItemTouchHelper.UP or ItemTouchHelper.DOWN)//可进行上下拖动，交换位置。 ItemTouchHelper.LEFT 允许向左拖动，ItemTouchHelper.RIGHT 允许向右拖动
            .setSwipeMoveFlags(ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT);//可进行左右滑动删除

        appAdapter.setOnItemLongClickListener(this)


    }

    override fun initEvent() {
        super.initEvent()
        lifecycleScope.launch(Dispatchers.IO) {
            withContext(Dispatchers.Main) {
                appAdapter.isStateViewEnable = true
                appAdapter.setStateViewLayout(
                    this@RecyclewActivity,
                    com.onBit.PixelBitToolKit.R.layout.layout_dialog
                )
            }
            val appsInfo = AppUtils.getAppsInfo()
            delay(3000)
            withContext(Dispatchers.Main) {
                viewModel.say()
                appAdapter.submitList(appsInfo)
                helper.apply {
                    addBeforeAdapter(BottomAdapter())
                    addAfterAdapter(BottomAdapter())
                    addBeforeAdapter(1, BottomAdapter())
                }

            }
        }
    }

    override fun onResume() {
        super.onResume()
        BarUtils.setStatusBarColor(this, Color.TRANSPARENT)
        BarUtils.transparentNavBar(this)
    }

    override fun onItemDragStart(viewHolder: RecyclerView.ViewHolder?, pos: Int) {

    }

    override fun onItemDragMoving(
        source: RecyclerView.ViewHolder,
        from: Int,
        target: RecyclerView.ViewHolder,
        to: Int
    ) {

    }

    override fun onItemDragEnd(viewHolder: RecyclerView.ViewHolder, pos: Int) {

    }

    override fun onLongClick(
        adapter: BaseQuickAdapter<AppUtils.AppInfo, *>,
        view: View,
        position: Int
    ): Boolean {
        quickDragAndSwipe.startDrag(position)
        return false
    }
}

