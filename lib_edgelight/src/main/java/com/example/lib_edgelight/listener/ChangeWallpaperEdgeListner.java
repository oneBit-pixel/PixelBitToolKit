package com.example.lib_edgelight.listener;

import android.annotation.SuppressLint;
import android.app.WallpaperManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.lib_edgelight.R;
import com.example.lib_edgelight.edgeLight.EdgeBorderBorderLightAnimate;
import com.example.lib_edgelight.utils.Const;
import com.example.lib_edgelight.utils.MySharePreferencesEdge;

import java.io.File;

public class ChangeWallpaperEdgeListner {
    private static final String TAG = "LisenerChangeWallpaper";
    private EdgeBorderBorderLightAnimate animate;
    private Context context;
    private int height;
    private int width;

    public ChangeWallpaperEdgeListner(EdgeBorderBorderLightAnimate edgeBorderLightAnimate, Context context2, int i, int i2) {
        this.animate = edgeBorderLightAnimate;
        this.context = context2;
        this.width = i;
        this.height = i2;
    }

    public void lisenerChangeColor(String str) {
        String str2;
        String str3;
        String str4;
        String str5;
        String str6;
        String str7;
        if (str.equals(Const.Action_DemoLiveWallpaper)) {
            str2 = MySharePreferencesEdge.getString(MySharePreferencesEdge.COLOR1, this.context);
            str7 = MySharePreferencesEdge.getString(MySharePreferencesEdge.COLOR2, this.context);
            str6 = MySharePreferencesEdge.getString(MySharePreferencesEdge.COLOR3, this.context);
            str5 = MySharePreferencesEdge.getString(MySharePreferencesEdge.COLOR4, this.context);
            str4 = MySharePreferencesEdge.getString(MySharePreferencesEdge.COLOR5, this.context);
            str3 = MySharePreferencesEdge.getString(MySharePreferencesEdge.COLOR6, this.context);
        } else {
            str2 = MySharePreferencesEdge.getString(MySharePreferencesEdge.FINISH_COLOR1, this.context);
            str7 = MySharePreferencesEdge.getString(MySharePreferencesEdge.FINISH_COLOR2, this.context);
            str6 = MySharePreferencesEdge.getString(MySharePreferencesEdge.FINISH_COLOR3, this.context);
            str5 = MySharePreferencesEdge.getString(MySharePreferencesEdge.FINISH_COLOR4, this.context);
            str4 = MySharePreferencesEdge.getString(MySharePreferencesEdge.FINISH_COLOR5, this.context);
            str3 = MySharePreferencesEdge.getString(MySharePreferencesEdge.FINISH_COLOR6, this.context);
        }
        if (str2 == null) {
            str2 = "#EB1111";
        }
        if (str7 == null) {
            str7 = "#1A11EB";
        }
        if (str6 == null) {
            str6 = "#EB11DA";
        }
        if (str5 == null) {
            str5 = "#11D6EB";
        }
        if (str4 == null) {
            str4 = "#EBDA11";
        }
        if (str3 == null) {
            str3 = "#11EB37";
        }
        this.animate.changeColor(new int[]{Color.parseColor(str2), Color.parseColor(str7), Color.parseColor(str6), Color.parseColor(str5), Color.parseColor(str4), Color.parseColor(str3), Color.parseColor(str2)});
    }

    public void lisenerChangeBorder(String str) {
        int i;
        int i2;
        int i3;
        int i4;
        if (str.equals(Const.Action_DemoLiveWallpaper)) {
            i = MySharePreferencesEdge.getInt(MySharePreferencesEdge.SPEED, this.context);
            i4 = MySharePreferencesEdge.getInt(MySharePreferencesEdge.SIZE, this.context);
            i3 = MySharePreferencesEdge.getInt(MySharePreferencesEdge.RADIUSTOP, this.context);
            i2 = MySharePreferencesEdge.getInt(MySharePreferencesEdge.RADIUSBOTTOM, this.context);
        } else {
            i = MySharePreferencesEdge.getInt(MySharePreferencesEdge.FINISH_SPEED, this.context);
            i4 = MySharePreferencesEdge.getInt(MySharePreferencesEdge.FINISH_SIZE, this.context);
            i3 = MySharePreferencesEdge.getInt(MySharePreferencesEdge.FINISH_RADIUSTOP, this.context);
            i2 = MySharePreferencesEdge.getInt(MySharePreferencesEdge.FINISH_RADIUSBOTTOM, this.context);
        }
        this.animate.changeSpeed((float) i);
        this.animate.changeSize(i4);
        this.animate.changeRadius(i3, i2);
    }

    public void lisenerChangeNotch(String str) {
        boolean z;
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        if (str.equals(Const.Action_DemoLiveWallpaper)) {
            z = MySharePreferencesEdge.getBooleanValue(MySharePreferencesEdge.CHECKNOTCH, this.context);
            i5 = MySharePreferencesEdge.getInt(MySharePreferencesEdge.NOTCHTOP, this.context);
            i4 = MySharePreferencesEdge.getInt(MySharePreferencesEdge.NOTCHRADIUSTOP, this.context);
            i3 = MySharePreferencesEdge.getInt(MySharePreferencesEdge.NOTCHRADIUSBOTTOM, this.context);
            i2 = MySharePreferencesEdge.getInt(MySharePreferencesEdge.NOTCHBOTTOM, this.context);
            i = MySharePreferencesEdge.getInt(MySharePreferencesEdge.NOTCHHEIGHT, this.context);
        } else {
            z = MySharePreferencesEdge.getBooleanValue(MySharePreferencesEdge.FINISH_CHECKNOTCH, this.context);
            i5 = MySharePreferencesEdge.getInt(MySharePreferencesEdge.FINISH_NOTCHTOP, this.context);
            i4 = MySharePreferencesEdge.getInt(MySharePreferencesEdge.FINISH_NOTCHRADIUSTOP, this.context);
            i3 = MySharePreferencesEdge.getInt(MySharePreferencesEdge.FINISH_NOTCHRADIUSBOTTOM, this.context);
            i2 = MySharePreferencesEdge.getInt(MySharePreferencesEdge.FINISH_NOTCHBOTTOM, this.context);
            i = MySharePreferencesEdge.getInt(MySharePreferencesEdge.FINISH_NOTCHHEIGHT, this.context);
        }
        this.animate.changeNotch(z, i5, i2, i, i4, i3);
    }

    @SuppressLint("MissingPermission")
    public void lisenerChangeBackground(String str) {
        int i;
        String str2;
        String str3;
        int i2;
        if (str.equals(Const.Action_DemoLiveWallpaper)) {
            i = MySharePreferencesEdge.getInt(MySharePreferencesEdge.BACKGROUND, this.context);
            str3 = MySharePreferencesEdge.getString(MySharePreferencesEdge.BACKGROUNDCOLOR, this.context);
            str2 = MySharePreferencesEdge.getString(MySharePreferencesEdge.BACKGROUNDLINK, this.context);
        } else {
            i = MySharePreferencesEdge.getInt(MySharePreferencesEdge.FINISH_BACKGROUND, this.context);
            str3 = MySharePreferencesEdge.getString(MySharePreferencesEdge.FINISH_BACKGROUNDCOLOR, this.context);
            str2 = MySharePreferencesEdge.getString(MySharePreferencesEdge.FINISH_BACKGROUNDLINK, this.context);
        }
        if (i == 1) {
            this.animate.setBitmap(Bitmap.createScaledBitmap(drawableToBitmap(WallpaperManager.getInstance(this.context).getDrawable()), this.width, this.height, false));
        } else if (i != 2) {
            int i3 = this.width;
            if (i3 > 0 && (i2 = this.height) > 0) {
                Bitmap createBitmap = Bitmap.createBitmap(i3, i2, Bitmap.Config.ARGB_8888);
                Canvas canvas = new Canvas(createBitmap);
                if (str3 == null) {
                    canvas.drawColor(ContextCompat.getColor(this.context, R.color.color_000000));
                } else {
                    canvas.drawColor(Color.parseColor(str3));
                }
                this.animate.setBitmap(createBitmap);
            }
        } else if (str2 != null) {
            if (new File(str2).exists()) {
                ((RequestBuilder) Glide.with(this.context).asBitmap().load(str2).override(this.width, this.height)).into(new CustomTarget<Bitmap>() {
                    

                    @Override 
                    public void onLoadCleared(Drawable drawable) {
                    }






                    public void onResourceReady(Bitmap bitmap, Transition<? super Bitmap> transition) {
                        ChangeWallpaperEdgeListner.this.animate.setBitmap(bitmap);
                    }
                });
                return;
            }
            Bitmap createBitmap2 = Bitmap.createBitmap(this.width, this.height, Bitmap.Config.ARGB_8888);
            Canvas canvas2 = new Canvas(createBitmap2);
            if (str3 == null) {
                canvas2.drawColor(ContextCompat.getColor(this.context, R.color.color_000000));
            } else {
                canvas2.drawColor(Color.parseColor(str3));
            }
            this.animate.setBitmap(createBitmap2);
        }
    }

    public void lisenerChangeType(String str) {
        String str2;
        if (str.equals(Const.Action_DemoLiveWallpaper)) {
            str2 = MySharePreferencesEdge.getString(MySharePreferencesEdge.SHAPE, this.context);
        } else {
            str2 = MySharePreferencesEdge.getString(MySharePreferencesEdge.FINISH_SHAPE, this.context);
        }
        //类型判断
        if (str2!=null){
            if (str2.equals(Const.LINE)) {
                //线性
            }else {
                //图形
            }
        }
//        if (str2 != null) {
//            if (str2.equals(Const.LINE)) {
//                this.animate.changeShape(str2, null);
//            } else if (str2.equals(Const.HEART)) {
//                this.animate.changeShape(str2, BitmapFactory.decodeResource(this.context.getResources(), R.drawable.heart_100px));
//            } else if (str2.equals(Const.DOT)) {
//                this.animate.changeShape(str2, BitmapFactory.decodeResource(this.context.getResources(), R.drawable.dots));
//            } else if (str2.equals(Const.SUN)) {
//                this.animate.changeShape(str2, BitmapFactory.decodeResource(this.context.getResources(), R.drawable.sun_100px));
//            } else if (str2.equals(Const.MOON)) {
//                this.animate.changeShape(str2, BitmapFactory.decodeResource(this.context.getResources(), R.drawable.moon_100px));
//            } else if (str2.equals(Const.SNOW)) {
//                this.animate.changeShape(str2, BitmapFactory.decodeResource(this.context.getResources(), R.drawable.snow_100px));
//            } else if (str2.equals(Const.PINE)) {
//                this.animate.changeShape(str2, BitmapFactory.decodeResource(this.context.getResources(), R.drawable.pine_100px));
//            } else if (str2.equals(Const.FLOWERART)) {
//                this.animate.changeShape(str2, BitmapFactory.decodeResource(this.context.getResources(), R.drawable.ic_7));
//            } else if (str2.equals(Const.BIRD)) {
//                this.animate.changeShape(str2, BitmapFactory.decodeResource(this.context.getResources(), R.drawable.ic_23));
//            } else if (str2.equals(Const.MOON1)) {
//                this.animate.changeShape(str2, BitmapFactory.decodeResource(this.context.getResources(), R.drawable.ic_3));
//            } else if (str2.equals(Const.FLOWERART1)) {
//                this.animate.changeShape(str2, BitmapFactory.decodeResource(this.context.getResources(), R.drawable.ic_5));
//            } else if (str2.equals(Const.FLOWERART2)) {
//                this.animate.changeShape(str2, BitmapFactory.decodeResource(this.context.getResources(), R.drawable.ic_8));
//            } else if (str2.equals(Const.DOLPHIN)) {
//                this.animate.changeShape(str2, BitmapFactory.decodeResource(this.context.getResources(), R.drawable.ic_4));
//            } else if (str2.equals(Const.TREE)) {
//                this.animate.changeShape(str2, BitmapFactory.decodeResource(this.context.getResources(), R.drawable.ic_10));
//            } else if (str2.equals(Const.EMOJI)) {
//                this.animate.changeShape(str2, BitmapFactory.decodeResource(this.context.getResources(), R.drawable.ic_1));
//            } else if (str2.equals(Const.CLOUDS)) {
//                this.animate.changeShape(str2, BitmapFactory.decodeResource(this.context.getResources(), R.drawable.ic_12));
//            } else if (str2.equals(Const.CHRISMISTREE)) {
//                this.animate.changeShape(str2, BitmapFactory.decodeResource(this.context.getResources(), R.drawable.ic_14));
//            } else if (str2.equals(Const.SKULL)) {
//                this.animate.changeShape(str2, BitmapFactory.decodeResource(this.context.getResources(), R.drawable.ic_24));
//            } else if (str2.equals(Const.BUTTERFLY)) {
//                this.animate.changeShape(str2, BitmapFactory.decodeResource(this.context.getResources(), R.drawable.ic_11));
//            } else if (str2.equals(Const.DRAGON)) {
//                this.animate.changeShape(str2, BitmapFactory.decodeResource(this.context.getResources(), R.drawable.ic_9));
//            } else if (str2.equals(Const.HAND)) {
//                this.animate.changeShape(str2, BitmapFactory.decodeResource(this.context.getResources(), R.drawable.ic_2));
//            } else if (str2.equals(Const.FOOT)) {
//                this.animate.changeShape(str2, BitmapFactory.decodeResource(this.context.getResources(), R.drawable.ic_6));
//            } else if (str2.equals(Const.SPACESHIP)) {
//                this.animate.changeShape(str2, BitmapFactory.decodeResource(this.context.getResources(), R.drawable.ic_15));
//            } else if (str2.equals(Const.STAR)) {
//                this.animate.changeShape(str2, BitmapFactory.decodeResource(this.context.getResources(), R.drawable.ic_18));
//            } else if (str2.equals(Const.BLADE)) {
//                this.animate.changeShape(str2, BitmapFactory.decodeResource(this.context.getResources(), R.drawable.ic_22));
//            } else if (str2.equals(Const.ART1)) {
//                this.animate.changeShape(str2, BitmapFactory.decodeResource(this.context.getResources(), R.drawable.ic_21));
//            } else if (str2.equals(Const.ART2)) {
//                this.animate.changeShape(str2, BitmapFactory.decodeResource(this.context.getResources(), R.drawable.ic_17));
//            } else if (str2.equals(Const.ART3)) {
//                this.animate.changeShape(str2, BitmapFactory.decodeResource(this.context.getResources(), R.drawable.ic_20));
//            } else if (str2.equals(Const.ART4)) {
//                this.animate.changeShape(str2, BitmapFactory.decodeResource(this.context.getResources(), R.drawable.ic_19));
//            } else if (str2.equals(Const.SKULL2)) {
//                this.animate.changeShape(str2, BitmapFactory.decodeResource(this.context.getResources(), R.drawable.ic_16));
//            } else {
//                this.animate.changeShape(str2, null);
//            }
//        }
    }

    public void lisenerChangeHole(String str) {
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        boolean equals = str.equals(Const.Action_DemoLiveWallpaper);
        String str2 = Const.NO;
        if (equals) {
            String string = MySharePreferencesEdge.getString(MySharePreferencesEdge.HOLESHARP, this.context);
            if (string != null) {
                str2 = string;
            }
            i = MySharePreferencesEdge.getInt(MySharePreferencesEdge.HOLEX, this.context);
            i5 = MySharePreferencesEdge.getInt(MySharePreferencesEdge.HOLEY, this.context);
            i4 = MySharePreferencesEdge.getInt(MySharePreferencesEdge.HOLERADIUS, this.context);
            i3 = MySharePreferencesEdge.getInt(MySharePreferencesEdge.HOLERADIUSY, this.context);
            i2 = MySharePreferencesEdge.getInt(MySharePreferencesEdge.HOLECORNER, this.context);
        } else {
            String string2 = MySharePreferencesEdge.getString(MySharePreferencesEdge.FINISH_HOLESHARP, this.context);
            if (string2 != null) {
                str2 = string2;
            }
            i = MySharePreferencesEdge.getInt(MySharePreferencesEdge.FINISH_HOLEX, this.context);
            i5 = MySharePreferencesEdge.getInt(MySharePreferencesEdge.FINISH_HOLEY, this.context);
            i4 = MySharePreferencesEdge.getInt(MySharePreferencesEdge.FINISH_HOLERADIUS, this.context);
            i3 = MySharePreferencesEdge.getInt(MySharePreferencesEdge.FINISH_HOLERADIUSY, this.context);
            i2 = MySharePreferencesEdge.getInt(MySharePreferencesEdge.FINISH_HOLECORNER, this.context);
        }
        this.animate.changeHole(str2, i, i5, i4, i3, i2);
    }

    public void lisenerChangeInfility(String str) {
        int i;
        int i2;
        int i3;
        int i4;
        boolean equals = str.equals(Const.Action_DemoLiveWallpaper);
        String str2 = Const.NO;
        if (equals) {
            String string = MySharePreferencesEdge.getString(MySharePreferencesEdge.INFILITYSHARP, this.context);
            if (string != null) {
                str2 = string;
            }
            i = MySharePreferencesEdge.getInt(MySharePreferencesEdge.INFILITYWIDTH, this.context);
            i4 = MySharePreferencesEdge.getInt(MySharePreferencesEdge.INFILITYHEIGHT, this.context);
            i3 = MySharePreferencesEdge.getInt(MySharePreferencesEdge.INFILITYRADIUS, this.context);
            i2 = MySharePreferencesEdge.getInt(MySharePreferencesEdge.INFILITYRADIUSB, this.context);
        } else {
            String string2 = MySharePreferencesEdge.getString(MySharePreferencesEdge.FINISH_INFILITYSHARP, this.context);
            if (string2 != null) {
                str2 = string2;
            }
            i = MySharePreferencesEdge.getInt(MySharePreferencesEdge.FINISH_INFILITYWIDTH, this.context);
            i4 = MySharePreferencesEdge.getInt(MySharePreferencesEdge.FINISH_INFILITYHEIGHT, this.context);
            i3 = MySharePreferencesEdge.getInt(MySharePreferencesEdge.FINISH_INFILITYRADIUS, this.context);
            i2 = MySharePreferencesEdge.getInt(MySharePreferencesEdge.FINISH_INFILITYRADIUSB, this.context);
        }
        this.animate.changeInfility(str2, i, i4, i3, i2);
    }

    public static Bitmap drawableToBitmap(Drawable drawable) {
        Bitmap bitmap;
        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            if (bitmapDrawable.getBitmap() != null) {
                return bitmapDrawable.getBitmap();
            }
        }
        if (drawable.getIntrinsicWidth() <= 0 || drawable.getIntrinsicHeight() <= 0) {
            bitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888);
        } else {
            bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        }
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmap;
    }
}
