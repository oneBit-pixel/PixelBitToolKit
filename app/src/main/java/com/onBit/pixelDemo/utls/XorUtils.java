package com.onBit.pixelDemo.utls;

import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.util.Base64;

import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.ConvertUtils;
import com.blankj.utilcode.util.DeviceUtils;
import com.blankj.utilcode.util.GsonUtils;
import com.blankj.utilcode.util.LanguageUtils;
import com.blankj.utilcode.util.NetworkUtils;
import com.blankj.utilcode.util.ScreenUtils;
import com.blankj.utilcode.util.Utils;

import org.jetbrains.annotations.NotNull;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

public class XorUtils {
    public static byte[] xor(String key, byte[] bs) {
        final byte[] keyBytes = key.getBytes();
        for (int i = 0; i < bs.length; i++) {
            for (byte b : keyBytes) {
                bs[i] = (byte) (bs[i] ^ b);
            }
        }
        return bs;
    }

    public static String decode(String key, String content) {
        try {
            return new String(xor(key, Base64.decode(content, Base64.NO_WRAP)), "utf-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public static String encode(String key, String content) {
        try {
            return Base64.encodeToString(xor(key, content.getBytes("utf-8")), Base64.NO_WRAP);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    private static String ts; // 必须/请求时间戳（毫秒）
    private static String app; // 必须/应用包名


    public static Map<String, String> toMap() {
        Map<String, String> map = new HashMap<>();
        Context context = Utils.getApp(); // Initialize Utils
        map.put("ts", ts != null ? ts : String.valueOf(System.currentTimeMillis()));
        map.put("app", app != null ? app : AppUtils.getAppPackageName());

        return map;
    }

    @NotNull
    public static Map<String, String> toData() {
        Map<String,String> map = new HashMap<>();
        map.put("content","请你假设你是以为塔罗牌占卜师");
        return map;
    }
}