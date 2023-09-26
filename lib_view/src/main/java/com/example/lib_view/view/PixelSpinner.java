package com.example.lib_view.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.lib_view.R;
import com.example.lib_view.databinding.LayoutSpinnerBinding;

public class PixelSpinner extends FrameLayout {

    public PixelSpinner(@NonNull Context context) {
        super(context);
    }

    public PixelSpinner(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    //默认方向
    public static int DIRECTION_TOP = 0;
    public static int DIRECTION_BOTTOM = 1;
    public static int DIRECTION_LEFT = 2;
    public static int DIRECTION_RIGHT = 3;
    private int currentDirection = DIRECTION_TOP;

    private int DEFAULT_WIDTH = 200;
    private int DEFAULT_HEIGHT = 100;
    private int popWidth = DEFAULT_WIDTH;
    private int popHeight = DEFAULT_HEIGHT;

    /**
     * xml 是从该构造方法传入传入
     * R.styleable.PixelSpinner 直接从该属性获取获取，所以第二个构造方法传Null不影响
     **/
    public PixelSpinner(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        //初始化属性
        initArr(context, attrs);
        //添加view
        initView(context);
        initListener();
    }

    private void initListener() {
        mBinding.getRoot().setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean selected = !mBinding.getRoot().isSelected();
                mBinding.getRoot().setSelected(selected);
                mBinding.imageView.setSelected(selected);
            }
        });
    }

    private LayoutSpinnerBinding mBinding = null;

    private void initView(Context context) {

        mBinding = LayoutSpinnerBinding.inflate(LayoutInflater.from(context), this, false);
        addView(mBinding.getRoot());
    }

    private void initArr(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.PixelSpinner);
        try {
            currentDirection = a.getInt(R.styleable.PixelSpinner_pop_direction, currentDirection);
            popWidth = a.getDimensionPixelOffset(R.styleable.PixelSpinner_pop_width, DEFAULT_WIDTH);
            popHeight = a.getDimensionPixelOffset(R.styleable.PixelSpinner_pop_width, DEFAULT_HEIGHT);
        } finally {
            a.recycle();
        }
    }
}
