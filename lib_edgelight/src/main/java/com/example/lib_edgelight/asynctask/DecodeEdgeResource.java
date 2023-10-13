package com.example.lib_edgelight.asynctask;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import com.example.lib_edgelight.utils.Const;


public class DecodeEdgeResource extends AsyncTask<Void, Void, Bitmap> {
    private CallBack callBack;
    private Context context;
    private String shape;

    public interface CallBack {
        void decodeDone(Bitmap bitmap);
    }

    public DecodeEdgeResource(Context context2, String str, CallBack callBack2) {
        this.context = context2;
        this.shape = str;
        this.callBack = callBack2;
    }

    public Bitmap doInBackground(Void... voidArr) {
        return getBitMap(this.shape);
    }

    public void onPostExecute(Bitmap bitmap) {
        this.callBack.decodeDone(bitmap);
    }

    private Bitmap getBitMap(String str) {
        if (str.equals(Const.LINE)) {
            return null;
        }
        //非线性

        return null;
    }
}
