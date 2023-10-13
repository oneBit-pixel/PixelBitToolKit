package com.example.lib_edgelight.view;

import android.annotation.SuppressLint;
import android.app.WallpaperManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;


import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.lib_edgelight.R;
import com.example.lib_edgelight.asynctask.DecodeEdgeResource;
import com.example.lib_edgelight.edgeLight.EdgeBorderBorderLightAnimate;
import com.example.lib_edgelight.listener.ChangeWallpaperEdgeListner;
import com.example.lib_edgelight.utils.Const;

import java.io.File;

public class EdgeBorderLightView extends View {
    private static final String TAG = "EdgeLightView";
    private EdgeBorderBorderLightAnimate animate;
    private ChangeWallpaperEdgeListner changeWallpaper;

    public EdgeBorderLightView(Context context) {
        super(context);
        init();
    }

    public EdgeBorderLightView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public EdgeBorderLightView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        EdgeBorderBorderLightAnimate edgeBorderLightAnimate = this.animate;
        if (edgeBorderLightAnimate != null) {
            edgeBorderLightAnimate.onLayout(getWidth(), getHeight());
            invalidate();
        }
    }

    public void onDraw(Canvas canvas) {
        EdgeBorderBorderLightAnimate edgeBorderLightAnimate = this.animate;
        if (edgeBorderLightAnimate != null) {
            edgeBorderLightAnimate.onDraw(canvas);
            postInvalidateDelayed(30);
        }
    }

    private void init() {
        EdgeBorderBorderLightAnimate edgeBorderLightAnimate = new EdgeBorderBorderLightAnimate(getContext());
        this.animate = edgeBorderLightAnimate;
        this.changeWallpaper = new ChangeWallpaperEdgeListner(edgeBorderLightAnimate, getContext(), getContext().getResources().getDisplayMetrics().widthPixels, getContext().getResources().getDisplayMetrics().heightPixels);
    }

    public void changeColor(String str) {
        this.changeWallpaper.lisenerChangeColor(str);
    }

    public void changeSpeed(String str) {
        this.changeWallpaper.lisenerChangeBorder(str);
    }

    public void changeSize(String str) {
        this.changeWallpaper.lisenerChangeBorder(str);
    }

    public void changeRadius(String str) {
        this.changeWallpaper.lisenerChangeBorder(str);
    }

    public void changeNotch(String str) {
        this.changeWallpaper.lisenerChangeNotch(str);
    }

    public void changeHole(String str) {
        this.changeWallpaper.lisenerChangeHole(str);
    }

    public void changeInfility(String str) {
        this.changeWallpaper.lisenerChangeInfility(str);
    }

    public void setBitmap() {
        this.changeWallpaper.lisenerChangeBackground(Const.Action_DemoLiveWallpaper);
    }

    public void setShape(String str) {
        this.changeWallpaper.lisenerChangeType(str);
    }

    public void changeCustomTemplate(int i, int i2, int i3, int[] iArr) {
        this.animate.changeColor(iArr);
        this.animate.changeSpeed((float) i);
        this.animate.changeRadius(i3, i3);
        this.animate.changeSize(i2);
    }

    public void changeCustomTheme(int i, int i2, int i3, int[] iArr, final String str) {
        this.animate.changeColor(iArr);
        this.animate.changeSpeed((float) i);
        this.animate.changeRadius(i3, i3);
        this.animate.changeSize(i2);
        new DecodeEdgeResource(getContext(), str, new DecodeEdgeResource.CallBack() {

            @Override 
            public void decodeDone(Bitmap bitmap) {
                EdgeBorderLightView.this.animate.changeShape(str, bitmap);
            }
        }).execute(new Void[0]);
    }

    public void changeColor(int[] iArr) {
        this.animate.changeColor(iArr);
    }

    public void changeSize(int i) {
        this.animate.changeSize(i);
    }

    public void changeSpeed(int i) {
        this.animate.changeSpeed((float) i);
    }

    public void changeBorder(int i, int i2) {
        this.animate.changeRadius(i, i2);
    }

    public void changeNotch(boolean z, int i, int i2, int i3, int i4, int i5) {
        this.animate.changeNotch(z, i, i2, i3, i4, i5);
    }

    public void changeHole(String str, int i, int i2, int i3, int i4, int i5) {
        this.animate.changeHole(str, i, i2, i3, i4, i5);
    }

    public void changeInfility(String str, int i, int i2, int i3, int i4) {
        this.animate.changeInfility(str, i, i2, i3, i4);
    }

    public void changeType(final String str) {
        new DecodeEdgeResource(getContext(), str, new DecodeEdgeResource.CallBack() {

            @Override 
            public void decodeDone(Bitmap bitmap) {
                EdgeBorderLightView.this.animate.changeShape(str, bitmap);
            }
        }).execute(new Void[0]);
    }

    @SuppressLint("MissingPermission")
    public void changeBackground(int i, String str, String str2, int i2, int i3) {
        if (i == 1) {
            this.animate.setBitmap(ChangeWallpaperEdgeListner.drawableToBitmap(WallpaperManager.getInstance(getContext()).getDrawable()));
        } else if (i != 2) {
            Bitmap createBitmap = Bitmap.createBitmap(i2, i3, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(createBitmap);
            if (str == null) {
                canvas.drawColor(ContextCompat.getColor(getContext(), R.color.color_000000));
            } else {
                canvas.drawColor(Color.parseColor(str));
            }
            this.animate.setBitmap(createBitmap);
        } else if (str2 != null) {
            if (new File(str2).exists()) {
                ((RequestBuilder) Glide.with(getContext()).asBitmap().load(str2).override(i2, i3)).into(new CustomTarget<Bitmap>() {

                    @Override 
                    public void onLoadCleared(Drawable drawable) {
                    }

                    public void onResourceReady(Bitmap bitmap, Transition<? super Bitmap> transition) {
                        EdgeBorderLightView.this.animate.setBitmap(bitmap);
                    }
                });
                return;
            }
            Bitmap createBitmap2 = Bitmap.createBitmap(i2, i3, Bitmap.Config.ARGB_8888);
            Canvas canvas2 = new Canvas(createBitmap2);
            if (str == null) {
                canvas2.drawColor(ContextCompat.getColor(getContext(), R.color.color_000000));
            } else {
                canvas2.drawColor(Color.parseColor(str));
            }
            this.animate.setBitmap(createBitmap2);
        }
    }

    public void changeRotate(boolean z) {
        this.animate.changeRotate(z);
    }
}
