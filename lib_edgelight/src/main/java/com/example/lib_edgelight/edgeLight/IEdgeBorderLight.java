package com.example.lib_edgelight.edgeLight;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public interface IEdgeBorderLight {
    void changeColor(int[] iArr);

    void onDraw(Canvas canvas);

    void onLayout(int i, int i2);

    void setBitmap(Bitmap bitmap);
}
