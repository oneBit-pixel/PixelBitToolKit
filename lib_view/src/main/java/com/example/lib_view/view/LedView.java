package com.example.lib_view.view;

import static android.content.Context.WINDOW_SERVICE;

import static com.example.lib_view.view.Constants.LED_BORDER_SIZE;
import static com.example.lib_view.view.Constants.LED_BOTTOM_RADIUS;
import static com.example.lib_view.view.Constants.LED_CURRENT_SPEED;
import static com.example.lib_view.view.Utils.createBorderBitmap;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.SweepGradient;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.WindowManager;

import androidx.annotation.NonNull;


import com.example.lib_view.R;
import com.orhanobut.hawk.Hawk;

import java.util.ArrayList;
import java.util.List;

public class LedView extends SurfaceView implements SurfaceHolder.Callback {

    private float mWidth;
    private float mHeight;
    private float scannerRadius;
    //颜色列表
    private List<Integer> colorList = new ArrayList<>();
    //画笔
    private Paint mPaintSector;
    private Paint mPaintRectCenter;
    private Paint mPaint;
    private SurfaceHolder mHolder;
    //线程是否在执行
    private boolean isRunning = false;
    //任务是否已经开始
    private boolean isStart = false;
    //记录并设置旋转角度
    private int start = 0;
    private Matrix matrix;  //矩阵
    private boolean visible = true;
    private Canvas canvas = null;
    private SweepGradient shader;
    // led颜色
    public int[] colors = null;
    // 边框花纹
    private Bitmap repeatBitmap;
    // 边框最终
    private Bitmap borderBitmap = null;
    // led速度
    public int speed = 10;
    // led宽度
    private int borderWidth = 30;
    // 边框圆角
    private float mRadius = 20.0f;//round
    private float createType;

    private MyThread my = new MyThread();
    private Thread thread = new Thread(my);

    public LedView(Context context) {
        super(context);
        // service中创建
        initPaint();
        createType = 2;
    }

    public LedView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint();
        // 布局中创建
        createType = 1;
    }

    public LedView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    public void setGradient(List<Integer> colorList) {
        this.colors = Utils.getGradientColors(colorList);
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setRadius(float radius) {
        this.mRadius = radius;
    }

    public void setBorderWidth(int borderWidth) {
        this.borderWidth = borderWidth;
        if (repeatBitmap != null) {
            borderBitmap = createBorderBitmap((int) mWidth, (int) mHeight, repeatBitmap, borderWidth);
        }
    }

    public void setRepeatBitmap(Bitmap bitmap) {
        this.repeatBitmap = bitmap;
        if (repeatBitmap != null) {
            borderBitmap = createBorderBitmap((int) mWidth, (int) mHeight, repeatBitmap, borderWidth);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //获得控件的宽度   宽度<=高度
        if (createType == 1 ){
            mWidth = getMeasuredWidth();
            mHeight = getMeasuredHeight();
        } else {
            WindowManager wm = (WindowManager) getContext().getSystemService(WINDOW_SERVICE);
            Point outSize = new Point();
            wm.getDefaultDisplay().getRealSize(outSize);
            mWidth = outSize.x;
            mHeight = outSize.y;
        }
        scannerRadius = (float) Math.sqrt(mWidth / 2 * mWidth / 2 + mHeight / 2 * mHeight / 2);

    }


    public void onDestroy() {
        mHolder.removeCallback(this);
//                super.onDetachedFromWindow();
    }
//    public void onDestroy() {
//        super.onDetachedFromWindow();
//    }

    public void initPaint() {

        //获取SurfaceHolder
        mHolder = getHolder();
        mHolder.addCallback(this);

        //禁用硬件加速
//        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        //绘制中间遮罩的画笔
        mPaintRectCenter = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintRectCenter.setAntiAlias(true);
        mPaintRectCenter.setColor(getResources().getColor(R.color.defaultLedCenterBg));
        //实心
        mPaintRectCenter.setStyle(Paint.Style.FILL);
        //四周画笔
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setAntiAlias(true);
        mPaint.setColor(getResources().getColor(R.color.defaultLedCenterBg));
        //绘画圆渐变色的画笔
        mPaintSector = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintSector.setAntiAlias(true);
        mPaintSector.setStyle(Paint.Style.FILL);

        // 首次进入设置界面的默认值
        // 默认颜色
        colorList.add(getResources().getColor(R.color.ledGradient2Color1left));
        colorList.add(getResources().getColor(R.color.ledGradient2Color1right));
        colors = Utils.getGradientColors(colorList);
        if (Hawk.get(LED_CURRENT_SPEED) != null) {
            speed = Hawk.get(LED_CURRENT_SPEED);
        }
        if (Hawk.get(LED_BORDER_SIZE) != null) {
            borderWidth = Hawk.get(LED_BORDER_SIZE);
        }
        if (Hawk.get(LED_BOTTOM_RADIUS) != null) {
            mRadius = (float)(Hawk.get(LED_BOTTOM_RADIUS));
        }
    }

    public void initParameters(int[] colors, Bitmap bitmap, boolean isSetBitmap, int speed, int borderWidth,float mRadius) {

//        this.mHolder = surfaceHolder;
//        mHolder.addCallback(this);
        WindowManager wm = (WindowManager) getContext().getSystemService(WINDOW_SERVICE);
        Point outSize = new Point();
        wm.getDefaultDisplay().getRealSize(outSize);
        mWidth = outSize.x;
        mHeight = outSize.y;
        scannerRadius = (float) Math.sqrt(mWidth / 2 * mWidth / 2 + mHeight / 2 * mHeight / 2);

        if (colors != null) {
            this.colors = colors;
        }
        if (isSetBitmap) {
            this.repeatBitmap = bitmap;
            if (repeatBitmap != null) {
                borderBitmap = createBorderBitmap((int) mWidth, (int) mHeight, repeatBitmap, this.borderWidth);
            }
        }
        if (speed >= 0) {
            this.speed = speed;
        }
        if (borderWidth >= 0) {
            this.borderWidth = borderWidth;
            if (repeatBitmap != null) {
                borderBitmap = createBorderBitmap((int) mWidth, (int) mHeight, repeatBitmap, borderWidth);
            }
        }
        if (mRadius >= 0) {
            this.mRadius = mRadius;
        }

    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
        Utils.d("LedView#onSurfaceCreated " + "holder : " + holder);
        if (thread.isAlive()) {
            my.resumeThread();
        } else {
            thread.start();
        }
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {
        my.pauseThread();
    }

    private void draw() {
        if (visible) {
            try {
                canvas = mHolder.lockCanvas();
                if (canvas != null) {
                    drawLed(canvas);
                }
            } finally {
                if (canvas != null)
                    try {
                        // 画布内容的提交
                        mHolder.unlockCanvasAndPost(canvas);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
            }
        }
        if (!visible) {
//            my.resumeThread();
            my.pauseThread();
        }
    }

    private void drawLed(Canvas canvas) {
//        canvas.drawColor(Color.RED);
        //初始化一个颜色渲染器
        shader = new SweepGradient(mWidth / 2.0f, mHeight / 2.0f, colors, null);
        //mPaintSector设置颜色渐变渲染器
        mPaintSector.setShader(shader);
        canvas.save();
        //把画布的多有对象与matrix联系起来
        if (matrix != null) {
            canvas.concat(matrix);
        }
        //绘制颜色渐变圆
        canvas.drawCircle(mWidth / 2.0f, mHeight / 2.0f, scannerRadius, mPaintSector);
        canvas.restore();
        //使用离屏绘制
        int layerID = canvas.saveLayer(0, 0, mWidth, mHeight, mPaint, Canvas.ALL_SAVE_FLAG);
        // 黑色背景
        canvas.drawRect(0, 0, mWidth, mHeight, mPaint);
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.XOR));
        if (repeatBitmap == null) {
            // 黑色背景镂空
            canvas.drawRoundRect(
                    0,
                    0,
                    mWidth,
                    mHeight,
                    mRadius,
                    mRadius,
                    mPaint);
            mPaint.setXfermode(null);

            //绘制中间圆角遮罩
            canvas.drawRoundRect(
                    borderWidth,
                    borderWidth,
                    mWidth - borderWidth,
                    mHeight - borderWidth,
                    mRadius,
                    mRadius,
                    mPaintRectCenter);
        } else {
            if (borderBitmap != null) {
                canvas.drawBitmap(borderBitmap, 0, 0, mPaint);
            }
            mPaint.setXfermode(null);
        }
        canvas.restoreToCount(layerID);
    }

    public void checkVisible(boolean visible) {
        this.visible = visible;
        if (visible) {
            my.resumeThread();
        } else {
            my.pauseThread();
        }
    }

    public void checkAlive() {
        if (thread.isAlive()) {
            my.resumeThread();
        } else {
            thread.start();
        }
    }

    public void pauseThread() {
        my.pauseThread();
    }

    class MyThread extends Thread {
        private final Object lock = new Object();
        private boolean pause = false;

        /**
         * 调用这个方法实现暂停线程
         */
        void pauseThread() {
            pause = true;
        }

        /**
         * 调用这个方法实现恢复线程的运行
         */
        void resumeThread() {
            pause = false;
            synchronized (lock) {
                lock.notifyAll();
            }
        }

        /**
         * 注意：这个方法只能在run方法里调用，不然会阻塞主线程，导致页面无响应
         */
        void onPause() {
            synchronized (lock) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        @Override
        public void run() {
            super.run();
            try {
                while (true) {
                    // 让线程处于暂停等待状态
                    while (pause || !visible) {
                        onPause();
                    }

                    start += speed;
                    //创建一个矩阵
                    matrix = new Matrix();
                    //矩阵设置旋转
                    matrix.preRotate(start * 1, mWidth / 2.0f, mHeight / 2.0f);
                    //重画
                    if (visible) {
                        draw();
                    }
                    try {
                        Thread.sleep(5);
                    } catch (InterruptedException e) {
                        //捕获到异常之后，执行break跳出循环
                        break;
                    }
                }
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        }
    }

}
