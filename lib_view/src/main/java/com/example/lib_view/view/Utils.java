package com.example.lib_view.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.Log;

import java.util.List;

public class Utils {
    public static void d(String msg, Object... params) {
        final String sTag = "WallPaper";
        Log.d(sTag, String.format(msg, params));

    }
    public static int dp2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    public static int[] getGradientColors(List<Integer> colorList) {

        int[] colors = null;
        int color1 = colorList.get(0);
        int color2 = colorList.get(1);
        int color3 = color1;
        int color4 = color2;
        switch (colorList.size()) {
            case 2:
                // 4份颜色 1/8
//                postions = new float[]{0f, 0.125f, 0.375f, 0.625f, 0.875f};
                int[] colors22 = new int[]{color1, color2, color1, color2, color1};
                return colors22;
            case 3:
                color3 = colorList.get(2);
                // 6份颜色 1/12
//                per3 = 1/12;
//                postions = new float[]{ 0f, per3, per3*3, per3*5, per3*7, per3*9, per3*11};
                int[] colors33 = new int[]{color1, color2, color3, color1, color2, color3, color1};
                return colors33;
            case 4:
                color3 = colorList.get(2);
                color4 = colorList.get(3);
                // 8份颜色 1/16=0.0625
//                per4 = 1/16;
//                postions = new float[]{ 0f, per4, per4*3, per4*5, per4*7, per4*9, per4*11, per4*13, per4*15};
                int[] colors44 = new int[]{color1, color2, color3, color4, color1, color2, color3, color4, color1};
                return colors44;
            default:
                return colors;
        }
    }

    public static Bitmap createBorderBitmap(int width, int height, Bitmap repeatBitmap, int borderWidth) {
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        Matrix matrix = new Matrix();
        Canvas canvas = new Canvas(bitmap);
        // 上边框
        repeatBitmap = Utils.changeBitmapSize(repeatBitmap, borderWidth, borderWidth);
        int numX = width / borderWidth;
        float spaceX = (width - (numX / 2) * borderWidth) / ((numX / 2) + 1);
        int numY = height / borderWidth;
        float spaceY = (height - (numY / 2) * borderWidth) / ((numY / 2) + 1);
        // 上
        for (int i = 0; i < (numX / 2); i++) {
            matrix.setTranslate(spaceX + (spaceX + borderWidth) * i, 0);
            canvas.drawBitmap(repeatBitmap, matrix, paint);
        }
        // 下边框
        for (int i = 0; i < (numX / 2); i++) {
            matrix.setTranslate(spaceX + (spaceX + borderWidth) * i, height - borderWidth);
            canvas.drawBitmap(repeatBitmap, matrix, paint);
        }
        // 左边框
        for (int i = 0; i < (numY / 2); i++) {
            matrix.setTranslate(0, spaceY + (spaceY + borderWidth) * i);
            canvas.drawBitmap(repeatBitmap, matrix, paint);
        }
        // 右边框
        for (int i = 0; i < (numY / 2); i++) {
            matrix.setTranslate(width - borderWidth, spaceY + (spaceY + borderWidth) * i);
            canvas.drawBitmap(repeatBitmap, matrix, paint);
        }
        return bitmap;
    }

    public static Bitmap changeBitmapSize(Bitmap bitmap, int newWidth, int newHeight) {
        //计算压缩的比率
        float scaleWidth = ((float) newWidth) / bitmap.getWidth();
        float scaleHeight = ((float) newHeight) / bitmap.getHeight();
        //获取想要缩放的matrix
        Matrix matrix = new Matrix();
        matrix.setTranslate(newWidth, 0);
        matrix.postScale(scaleWidth, scaleHeight);
//        if (isX){
//            matrix.setTranslate(borderWidth,0);
//        } else {
//            matrix.setTranslate(0,borderWidth);
//        }
        //获取新的bitmap
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }
}
